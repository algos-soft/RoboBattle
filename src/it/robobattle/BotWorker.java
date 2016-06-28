package it.robobattle;

import javafx.scene.paint.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

public class BotWorker extends SwingWorker<Void, JobStatus> {

    private BotTestComponent testComp;  // il componente grafico di testing, contiene bot e test
    private int totRequests;    // numero totale di richieste da inviare in una sessione di test
    private Random random = new Random();    // random generator
    private boolean finished;    // se l'elaborazione è terminata

    private TestSessionResult sessionResult;


    public BotWorker(BotTestComponent testComp) {
        super();
        this.testComp = testComp;

        final JSlider slider = this.testComp.getArena().getSpeedSlider();
        totRequests = slider.getValue();
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                totRequests = slider.getValue();
            }
        });


    }

    @Override
    protected Void doInBackground() throws Exception {

        boolean stop = false;
        long totRichiesteElaborate = 0;
        long totErrors = 0;

        long lastUpdateMillis = 0;

        sessionResult=new TestSessionResult(testComp.getBot(), testComp.getTest());

        while (!stop) {

            // fa eseguire una operazione al bot
            JobResults result = new JobResults(testComp.getBot());
            doJob(result);

            // controlla e convalida i risultati
            result.validate();

            // se i risultati non sono validi, logga gli errori
            if (!result.isValid()) {
                String err = result.getError();
                //System.out.println(err);
                totErrors++;
            }

            totRichiesteElaborate++;

            // aggiorna i risultati di sessione
            sessionResult.addNanos(result.getNanos());
            sessionResult.addWordCount(1);
            if (!result.isValid()) {
                sessionResult.addErrCount(1);
            }

            // ogni tanto pubblica lo stato di avanzamento
            long elapsedSinceLastUpdate = System.currentTimeMillis() - lastUpdateMillis;
            if (elapsedSinceLastUpdate > 250) {
                JobStatus status = new JobStatus(totRichiesteElaborate, sessionResult.getNanos()/1000000, totErrors);
                publish(status);
                lastUpdateMillis = System.currentTimeMillis();
            }

            // quando ha elaborato tutte le righieste previste si ferma
            if (totRichiesteElaborate >= totRequests) {
                finished = true;
                JobStatus status = new JobStatus(totRichiesteElaborate, sessionResult.getNanos() / 1000000, totErrors);
                publish(status);
                stop = true;
            }

            // se la battaglia è stoppata, si ferma
            if (testComp.isStopped()) {
                finished = true;
                stop = true;
            }

        }

        return null;
    }


    /**
     * Esegue un job sul bot
     * esegue solo i task abilitati
     *
     * @param results un oggetto JobResults da riempire con i risultati dei singoli task
     */
    private synchronized void doJob(JobResults results) {
        long t1, dt;
        String request=null;
        String response=null;
        int sum=0;
        dt=0;
        String error=null;

        switch (getTest()) {

            case SORT_WORD:
                request = getRandomString();
                t1 = System.nanoTime();
                try {
                    response = getBot().sortWord(request);
                }catch (Exception e){
                    error=e.getMessage();
                }
                dt=System.nanoTime()-t1;
                results.setData(Tests.SORT_WORD, dt, request, response, error);
                break;

            case INVERT_WORD:
                request = getRandomString();
                t1 = System.nanoTime();
                try {
                    response = getBot().invertWord(request);
                }catch (Exception e){
                    error=e.getMessage();
                }
                dt=System.nanoTime()-t1;
                results.setData(Tests.INVERT_WORD, dt, request, response, error);
                break;

            case CALC_CKECKSUM:
                request = getRandomString();
                t1 = System.nanoTime();
                try {
                    sum = getBot().calcChecksum(request);
                }catch (Exception e){
                    error=e.getMessage();
                }
                dt=System.nanoTime()-t1;
                results.setData(Tests.CALC_CKECKSUM, dt, request, sum, error);
                break;

            case DECRYPT_WORD:
                request = getRandomString();
                String key = "abcd";
                t1 = System.nanoTime();
                try {
                    response = getBot().decryptWord(request, key);
                }catch (Exception e){
                    error=e.getMessage();
                }
                dt=System.nanoTime()-t1;
                String[] strings = {request, key};
                results.setData(Tests.DECRYPT_WORD, dt, strings, response, error);
                break;

        }

    }


    @Override
    protected void process(List<JobStatus> chunks) {
        for (JobStatus s : chunks) {
            int percent = (int) (100 * s.getNumRequests() / totRequests);
            getBar().setValue(percent);
            getBar().setString(percent + "%");

            String snum = NumberFormat.getIntegerInstance().format(s.getNumRequests());
            String serr = NumberFormat.getIntegerInstance().format(s.getNumErrors());
            String stime=Lib.millisToString(s.getElapsedMillis());

            testComp.getIterField().setText(snum);
            testComp.getTimeField().setText(stime);
            testComp.getErrField().setText(serr);

            // campo num errori in rosso se ce ne sono
            Color bg=Color.white;
            Color fg=Color.black;
            if(s.getNumErrors()>0) {
                bg=Color.red;
                fg= Color.white;
            }
            testComp.getErrField().setBackground(bg);
            testComp.getErrField().setForeground(fg);


        }
    }

    @Override
    protected void done() {
        getBar().setString("Terminato!");
        testComp.workerFinished(sessionResult);
    }


    /**
     * Ritorna una stringa random dal pool delle stringhe.
     */
    private String getRandomString() {
        int rnd = random.nextInt(Data.STRINGHE.length);
        return Data.STRINGHE[rnd];
    }


    public boolean isFinished() {
        return finished;
    }

    public Bot getBot() {
        return testComp.getBot();
    }

    public Tests getTest() {
        return testComp.getTest();
    }

    public JProgressBar getBar() {
        return testComp.getBar();
    }

    public TestSessionResult getSessionResult() {
        return sessionResult;
    }
}
