import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Arena extends CenteredFrame {

    public static int DEFAULT_REQ_PER_SESSION = 10000000;

    private Bot bot;
    private JProgressBar bar1;
    private JLabel labelStatus1;
    private JSlider speedSlider;
    private JLabel labelSpeed;

    public Arena(Bot bot) {
        super();
        this.bot=bot;

        setTitle(bot.getNome());

        bar1 = new JProgressBar(0, 100);

        labelStatus1 = new JLabel();

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
        panTests.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panTests.setLayout(layout);

        for(Tests test : Tests.values()){
            Component comp = new BotTestComponent(bot, test, this);
            panTests.add(comp);
        }

        return panTests;
    }

    private Component creaPanBattle() {
        JPanel panBattle = new JPanel();
        panBattle.setBackground(Color.YELLOW);
        panBattle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        BoxLayout layout = new BoxLayout(panBattle, BoxLayout.Y_AXIS);
        panBattle.setLayout(layout);

        JPanel pBots = new JPanel();
        pBots.setLayout(new GridLayout(0, 2, 10, 10));
        pBots.add(new BotComponent(bot));
        pBots.add(bar1);
        pBots.add(new CompStatus(labelStatus1, bot));

        speedSlider.setValue(DEFAULT_REQ_PER_SESSION / 2);
        updateSliderText();

        panBattle.add(pBots);
        panBattle.add(labelSpeed);
        panBattle.add(speedSlider);

        return panBattle;
    }

    /**
     * Componente con label status e bottone info
     */
    private class CompStatus extends JPanel{
        public CompStatus(JLabel label, final Bot bot) {
            BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
            setLayout(layout);
            add(label);

            JButton button = new JButton("info");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BotWorker worker=null;
                    if(worker!=null){
                        //String info = worker.getSessionInfo();
                        JOptionPane pane = new JOptionPane("Qui le informazioni");
                        JDialog dialog = new JDialog();
                        dialog.add(pane);
                        dialog.setModal(false);
                        dialog.pack();
                        dialog.setVisible(true);
                    }
                }
            });

            add(button);
            label.setPreferredSize(new Dimension(220, 20));
            label.setMaximumSize(new Dimension(1000,10));
        }
    }


    private void updateSliderText() {
        String snum = NumberFormat.getIntegerInstance().format(+speedSlider.getValue());
        labelSpeed.setText("Tot. " + snum + " richieste");
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

//        bStart.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                if (!running) {
//                    running = true;
//                    stopped = false;
//                    bStart.setText("Stop");
//                    worker1 = new BotWorker(Arena.this, bot, bar1, labelStatus1);
//                    worker1.execute();
//                } else {
//                    stopped = true;
//                }
//
//            }
//        });

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

        JButton bStartSync=new JButton("Start all Sync");
        JButton bStartAsync=new JButton("Start all Async");
        JButton bConfResult=new JButton("Conferma risultati");


        JPanel panBottoni=new JPanel();
        panBottoni.add(bStartSync);
        panBottoni.add(bStartAsync);
        panBottoni.add(bConfResult);

        panel.add(speedSlider);
        panel.add(panBottoni);



        return panel;
    }





    public JSlider getSpeedSlider() {
        return speedSlider;
    }


}
