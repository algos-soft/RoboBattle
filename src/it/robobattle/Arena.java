package it.robobattle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Arena extends CenteredFrame {

    public static int DEFAULT_REQ_PER_SESSION = 10000000;

    private Bot bot;
    private RoboBattle battle;
    private JSlider speedSlider;
    private JLabel labelSpeed;
    private HashMap<Tests, BotTestComponent> components=new HashMap();

    boolean compFinished=false;
    int compInTest=0;

    public Arena(Bot bot, RoboBattle battle) {
        super();
        this.bot=bot;
        this.battle=battle;

        setTitle(bot.getNome());

        labelSpeed = new JLabel("", SwingConstants.CENTER);
        labelSpeed.setAlignmentX(CENTER_ALIGNMENT);
        labelSpeed.setFont(new Font("", Font.BOLD, 16));
        labelSpeed.setForeground(Color.BLUE);
        labelSpeed.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));

        add(new BotComponent(bot), BorderLayout.PAGE_START);
        add(creaCompTests());
        add(creaPanComandi(), BorderLayout.PAGE_END);

        pack();
        setVisible(true);

    }

    /**
     * Crea e ritorna il componente grafico con i test
     */
    private Component creaCompTests(){
        JPanel panTests = new JPanel();
        BoxLayout layout = new BoxLayout(panTests, BoxLayout.Y_AXIS);
        panTests.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panTests.setLayout(layout);

        for(Tests test : Tests.values()){
            BotTestComponent comp = new BotTestComponent(bot, test, this);
            components.put(test, comp);
            panTests.add(comp);
        }

        return panTests;
    }



    private void updateSliderText() {
        String snum = NumberFormat.getIntegerInstance().format(+speedSlider.getValue());
        labelSpeed.setText("Tot. " + snum + " words");
    }


    /**
     * Crea il pannello con i comandi.
     *
     * @return il pannello comandi
     */
    private Component creaPanComandi() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        speedSlider = new JSlider();
        speedSlider.setMaximum(DEFAULT_REQ_PER_SESSION);
        speedSlider.setMajorTickSpacing(speedSlider.getMaximum() / 2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));
        speedSlider.setValue(DEFAULT_REQ_PER_SESSION / 2);
        speedSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateSliderText();
            }
        });
        updateSliderText();

        JButton bStartSync=new JButton("Start all (sync)");
        bStartSync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compInTest=-1;
                startOneSync();
                //startAllSync();
            }
        });


        JButton bStartAsync=new JButton("Start all (async)");
        bStartAsync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (BotTestComponent comp : components.values()) {
                    comp.startOrStop();
                }
            }
        });


        JButton bConfResult=new JButton("Save results");
        bConfResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotResults r = battle.getResult(bot);
                for(BotTestComponent comp : components.values()){
                    r.putResult(comp.getTest(), comp.getSessionResult());
                }
                dispose();
                battle.refreshTable();
            }
        });

        JButton bCancel=new JButton("Cancel");
        bCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        JPanel panBottoni=new JPanel();
        panBottoni.add(bStartSync);
        panBottoni.add(bStartAsync);
        panBottoni.add(bConfResult);
        panBottoni.add(bCancel);

        panel.add(speedSlider);
        panel.add(panBottoni);

        return panel;
    }


    /**
     * Avvia tutti i test ricorsivamente.
     * Attende la fine di un test per avviare il successivo.
     */
    private void startOneSync(){
        if(compInTest<Tests.values().length-1){
            compInTest++;
            Tests test=Tests.values()[compInTest];
            BotTestComponent comp = components.get(test);
            comp.setFinishedListener(new BotTestComponent.TestFinished() {
                @Override
                public void testFinished() {
                    startOneSync();
                }
            });
            comp.startOrStop();

        }
    }

    public JSlider getSpeedSlider() {
        return speedSlider;
    }


}
