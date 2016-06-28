package it.robobattle;
/**
 * Created by alex on 22/06/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Componente grafico che rappresenta l'esecuzione di un test
 * e visualizza i risultati per un bot.
 */
public class BotTestComponent extends JPanel {

    private Bot bot;
    private Tests test;
    private boolean running=false;
    private boolean stopped = false;
    private final JProgressBar bar;
    private Arena arena;
    private JTextField iterField;
    private JTextField timeField;
    private BotWorker worker;
    private JTextField errField;

    private TestSessionResult sessionResult;
    private final JButton bStart;
    private TestFinished finishedListener;
    private final JCheckBox checkEnabled;

    /**
     * Costruttore.
     *
     * @param bot  il bot da testare
     * @param test il test da eseguire
     */
    public BotTestComponent(Bot bot, Tests test, Arena arena) {
        this.bot = bot;
        this.test = test;
        this.arena=arena;

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        checkEnabled = new JCheckBox(test.getTestName());
        checkEnabled.setSelected(true);
        checkEnabled.setMinimumSize(new Dimension(140, 0));
        checkEnabled.setPreferredSize(new Dimension(140, 0));

        bar = new JProgressBar(0, 100);
        bar.setMinimumSize(new Dimension(20, 50));
        bar.setPreferredSize(new Dimension(200, 50));
        bar.setStringPainted(true);

        bStart = new JButton("Start");
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startOrStop();
            }
        });

        Component compResult=createCompResult();

        add(checkEnabled);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(bar);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(bStart);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(compResult);

    }

    /**
     * invocato dal bottone start/stop
     */
    public void startOrStop() {
        if(!running){
            bStart.setText("Stop");
            worker = new BotWorker(this);
            worker.execute();
            running=true;
            stopped=false;

        }else{
            bStart.setText("Start");
            stopped=true;
            running=false;
        }

    }


    private Component createCompResult() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));

        JLabel iterLabel = new JLabel("iter ");
        iterField = new JTextField();
        iterField.setEditable(false);
        iterField.setBackground(Color.WHITE);
        iterField.setMinimumSize(new Dimension(90, 30));
        iterField.setPreferredSize(new Dimension(90, 30));
        iterField.setMaximumSize(new Dimension(90, 30));

        JLabel resLabel = new JLabel("time ");

        timeField = new JTextField();
        timeField.setEditable(false);
        timeField.setBackground(Color.WHITE);
        timeField.setMinimumSize(new Dimension(60, 30));
        timeField.setPreferredSize(new Dimension(60, 30));
        timeField.setMaximumSize(new Dimension(60, 30));

        JLabel errLabel = new JLabel("err ");

        errField = new JTextField();
        errField.setEditable(false);
        errField.setBackground(Color.WHITE);
        errField.setMinimumSize(new Dimension(90, 30));
        errField.setPreferredSize(new Dimension(90, 30));
        errField.setMaximumSize(new Dimension(90, 30));

        pan.add(iterLabel);
        pan.add(iterField);
        pan.add(Box.createRigidArea(new Dimension(10,0)));
        pan.add(resLabel);
        pan.add(timeField);
        pan.add(errLabel);
        pan.add(errField);


        return pan;
    }

    /**
     * Invocato ogni volta che un worker finisce il suo lavoro o viene abortito.
     * @param result i risultati della sessione
     */
    public void workerFinished(TestSessionResult result) {
        sessionResult=result;
        running = false;
        bStart.setText("Start");

        if(finishedListener!=null){
            finishedListener.testFinished();
        }
    }


    public boolean isStopped(){
        return stopped;
    }

    public Bot getBot() {
        return bot;
    }

    public JProgressBar getBar() {
        return bar;
    }

    public Tests getTest() {
        return test;
    }

    public Arena getArena() {
        return arena;
    }

    public JTextField getIterField() {
        return iterField;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JTextField getErrField() {
        return errField;
    }

    public TestSessionResult getSessionResult() {
        return sessionResult;
    }

    public void setFinishedListener(TestFinished finishedListener) {
        this.finishedListener = finishedListener;
    }

    public interface TestFinished{
        void testFinished();
    }

    public boolean isTestEnabled(){
        return checkEnabled.isSelected();
    }

    public void setTestEnabled(boolean enabled){
        checkEnabled.setSelected(enabled);
    }



}
