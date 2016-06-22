/**
 * Created by alex on 22/06/16.
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JLabel labelStatus;


    /**
     * Costruttore.
     *
     * @param bot  il bot da testare
     * @param test il test da eseguire
     */
    public BotTestComponent(Bot bot, Tests test) {
        this.bot = bot;
        this.test = test;

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        JLabel labelTest = new JLabel(test.getTestName());
        labelTest.setMinimumSize(new Dimension(120, 0));
        labelTest.setPreferredSize(new Dimension(120, 0));

        bar = new JProgressBar(0, 100);
        bar.setMinimumSize(new Dimension(20, 30));
        bar.setPreferredSize(new Dimension(200, 30));

        JButton bStart = new JButton("Start");
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startOrStop();
            }
        });

        Component compResult=createCompResult();

        labelStatus=new JLabel();

        add(labelTest);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(bar);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(bStart);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(compResult);
        add(labelStatus);

    }

    /**
     * invocato dal bottone start/stop
     */
    private void startOrStop() {
        if(!running){
            BotWorker worker = new BotWorker(this, bot, bar, labelStatus);
            worker.execute();
            running=true;
            stopped=false;
        }else{
            stopped=true;
            running=false;
        }

    }


    private Component createCompResult() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));

        JLabel iterLabel = new JLabel("iter");
        JTextField iterField = new JTextField();
        iterField.setText("50.000.000");
        iterField.setEnabled(false);
        iterField.setMinimumSize(new Dimension(90, 30));
        iterField.setPreferredSize(new Dimension(90, 30));
        iterField.setMaximumSize(new Dimension(90, 30));

        JLabel resLabel = new JLabel("time");

        JTextField resField = new JTextField();
        resField.setText("00:00");
        resField.setEnabled(false);
        resField.setMinimumSize(new Dimension(60, 30));
        resField.setPreferredSize(new Dimension(60, 30));
        resField.setMaximumSize(new Dimension(60, 30));

        pan.add(iterLabel);
        pan.add(iterField);
        pan.add(Box.createRigidArea(new Dimension(10,0)));
        pan.add(resLabel);
        pan.add(resField);

        return pan;
    }

    public boolean isStopped(){

    }

}
