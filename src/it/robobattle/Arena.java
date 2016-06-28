package it.robobattle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Arena extends CenteredFrame {

    public static int MAX_REQ_PER_SESSION = 9000000;
    public static int MIN_REQ_PER_SESSION = 1000000;

    private Bot bot;
    private RoboBattle battle;
    private JSlider speedSlider;
    private JLabel labelSpeed;
    private HashMap<Tests, BotTestComponent> components=new HashMap();
    private ArenaSettings settings;

    int compInTest=0;

    public Arena(Bot bot, RoboBattle battle, ArenaSettings settings) {
        super();

        this.bot=bot;
        this.battle=battle;
        this.settings=settings;

        setTitle(bot.getNome());

        labelSpeed = new JLabel("", SwingConstants.CENTER);
        labelSpeed.setAlignmentX(CENTER_ALIGNMENT);
        labelSpeed.setFont(new Font("", Font.BOLD, 14));
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

//        panTests.setBackground(Color.blue);

        BoxLayout layout = new BoxLayout(panTests, BoxLayout.Y_AXIS);
        panTests.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panTests.setLayout(layout);

        for(Tests test : Tests.values()){
            BotTestComponent comp = new BotTestComponent(bot, test, this);
            components.put(test, comp);

            comp.setTestEnabled(settings.isTestEnabled(test));

            panTests.add(comp);
        }

        return panTests;
    }



    private void updateSliderText() {
        String snum = NumberFormat.getIntegerInstance().format(+speedSlider.getValue());
        labelSpeed.setText("Test: " + snum + " words");
    }


    /**
     * Crea il pannello con i comandi.
     *
     * @return il pannello comandi
     */
    private Component creaPanComandi() {
        JPanel panel = new JPanel();

//        panel.setBackground(Color.green);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        speedSlider = new JSlider();
        speedSlider.setMinimum(MIN_REQ_PER_SESSION);
        speedSlider.setMaximum(MAX_REQ_PER_SESSION);
        speedSlider.setMajorTickSpacing((MAX_REQ_PER_SESSION - MIN_REQ_PER_SESSION) / 2);
        speedSlider.setMinorTickSpacing((MAX_REQ_PER_SESSION - MIN_REQ_PER_SESSION) / 40);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        speedSlider.setValue(settings.getSliderValue());
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
                startRecursiveSync();
            }
        });


        JButton bStartAsync=new JButton("Start all (async)");
        bStartAsync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (BotTestComponent comp : components.values()) {
                    if(comp.isTestEnabled()){
                        comp.startOrStop();
                    }
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

        panel.add(labelSpeed);
        panel.add(speedSlider);
        panel.add(panBottoni);

        return panel;
    }


    /**
     * Avvia tutti i test ricorsivamente.
     * Attende la fine di un test per avviare il successivo.
     */
    private void startRecursiveSync(){
        if(compInTest<Tests.values().length-1){
            compInTest++;
            Tests test=Tests.values()[compInTest];
            BotTestComponent comp = components.get(test);

            if(comp.isTestEnabled()){
                comp.setFinishedListener(new BotTestComponent.TestFinished() {
                    @Override
                    public void testFinished() {
                        startRecursiveSync();
                    }
                });
                comp.startOrStop();
            }else{
                startRecursiveSync();
            }


        }
    }

    public JSlider getSpeedSlider() {
        return speedSlider;
    }

    public BotTestComponent getTestComponent(Tests test){
        return components.get(test);
    }


}
