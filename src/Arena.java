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
    //	private JLabel labelWinner;
    private boolean running;
    private boolean stopped;

    private BotWorker worker1;

    private JButton bStart;

    private Bot winner;
    private JCheckBox check1;
    private JCheckBox check2;
    private JCheckBox check3;
    private JCheckBox check4;

    public Arena(Bot bot) {
        super();
        this.bot=bot;

        setTitle(bot.getNome());

        bar1 = new JProgressBar(0, 100);

        speedSlider = new JSlider();
        speedSlider.setMaximum(DEFAULT_REQ_PER_SESSION);
        speedSlider.setMajorTickSpacing(speedSlider.getMaximum() / 2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));
        speedSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateSliderText();
            }
        });

        labelStatus1 = new JLabel();

        labelSpeed = new JLabel("", SwingConstants.CENTER);
        labelSpeed.setAlignmentX(CENTER_ALIGNMENT);
        labelSpeed.setFont(new Font("", Font.BOLD, 16));
        labelSpeed.setForeground(Color.BLUE);
        labelSpeed.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));

//		labelWinner=new JLabel("",SwingConstants.CENTER);
//		labelWinner.setAlignmentX(CENTER_ALIGNMENT);
//		labelWinner.setFont(new Font("", Font.BOLD, 16));
//		labelWinner.setForeground(Color.RED);
//		labelWinner.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        bStart = new JButton("Start!");

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
            Component comp = new BotTestComponent(bot, test);
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
        panBattle.add(creaPanTest());

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
        panel.add(bStart);


        return panel;
    }

    /**
     * Invocato ogni volta che un worker finisce il suo lavoro
     */
    public void workerFinished(BotWorker worker) {

        // se entrambi i worker sono terminati la sfida è terminata
        if (worker1.isFinished()) {
            running = false;
            bStart.setText("Start");
        }

//		// se è un overflow assegna il trofeo all'altro bot
//		if(worker.isOverflow()){
//			if(winner==null){
//				if(worker.equals(worker1)){
//					winner=worker2.getBot();
//				}else{
//					winner=worker1.getBot();
//				}
//				labelWinner.setText("Winner: "+winner.getNome());
//			}
//		}

    }


    /**
     * Pannello con i test
     */
    private Component creaPanTest() {
        JPanel pan = new JPanel();
        check1 = new JCheckBox(Tests.SORT_WORD.getTestName());
        check2 = new JCheckBox(Tests.INVERT_WORD.getTestName());
        check3 = new JCheckBox(Tests.CALC_CKECKSUM.getTestName());
        check4 = new JCheckBox(Tests.DECRYPT_WORD.getTestName());

        pan.add(check1);
        pan.add(check2);
        pan.add(check3);
        pan.add(check4);

        return pan;

    }


    public JSlider getSpeedSlider() {
        return speedSlider;
    }


    public boolean isStopped() {
        return stopped;
    }


    public Bot getWinner() {
        return winner;
    }

    public boolean isCheck1() {
        return check1.isSelected();
    }

    public boolean isCheck2() {
        return check2.isSelected();
    }

    public boolean isCheck3() {
        return check3.isSelected();
    }

    public boolean isCheck4() {
        return check4.isSelected();
    }

}
