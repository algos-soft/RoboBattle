import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotWorker extends SwingWorker<Void, JobStatus> {

    private BotTestComponent testComp;  // il componente grafico di testing, contiene bot e test
    private int totRequests;    // numero totale di richieste da inviare in una sessione di test
    private Random random = new Random();    // random generator
    private boolean finished;    // se l'elaborazione è terminata


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
        long totTimeNanos = 0;


        while (!stop) {

            // fa eseguire una operazione al bot
            JobResults result = new JobResults();
            doJob(result);
            totTimeNanos += result.getNanos();

            // controlla e convalida i risultati
            result.validate();

            // se i risultati non sono validi, logga gli errori
            if (!result.isValid()) {
                System.out.println(result.getError());
                totErrors++;
            }

            totRichiesteElaborate++;

            // ogni tanto pubblica lo stato di avanzamento
            long elapsedSinceLastUpdate = System.currentTimeMillis() - lastUpdateMillis;
            if (elapsedSinceLastUpdate > 250) {
                JobStatus status = new JobStatus(totRichiesteElaborate, totTimeNanos / 1000000, totErrors);
                publish(status);
                lastUpdateMillis = System.currentTimeMillis();
            }

            // quando ha elaborato tutte le righieste previste si ferma
            if (totRichiesteElaborate >= totRequests) {
                finished = true;
                JobStatus status = new JobStatus(totRichiesteElaborate, totTimeNanos / 1000000, totErrors);
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
    private void doJob(JobResults results) {
        long t1;
        String request;
        String response;
        int sum;

        switch (getTest()) {

            case SORT_WORD:
                request = getRandomString();
                t1 = System.nanoTime();
                response = getBot().sortWord(request);
                results.put(Tests.SORT_WORD, System.nanoTime() - t1, request, response);

            case INVERT_WORD:
                request = getRandomString();
                t1 = System.nanoTime();
                response = getBot().invertWord(request);
                results.put(Tests.INVERT_WORD, System.nanoTime() - t1, request, response);

            case CALC_CKECKSUM:
                request = getRandomString();
                t1 = System.nanoTime();
                sum = getBot().calcChecksum(request);
                results.put(Tests.CALC_CKECKSUM, System.nanoTime() - t1, request, sum);

            case DECRYPT_WORD:
                request = getRandomString();
                String key = "abcd";
                t1 = System.nanoTime();
                response = getBot().decryptWord(request, key);
                long nanos = System.nanoTime() - t1;
                String[] strings = {request, key};
                results.put(Tests.DECRYPT_WORD, nanos, strings, response);

        }

    }


    @Override
    protected void process(List<JobStatus> chunks) {
        for (JobStatus s : chunks) {
            int percent = (int) (100 * s.getNumRequests() / totRequests);
            getBar().setValue(percent);
            getBar().setString(percent + "%");

            String snum = NumberFormat.getIntegerInstance().format(+s.getNumRequests());
            String serr = NumberFormat.getIntegerInstance().format(+s.getNumErrors());

            testComp.getIterField().setText(snum);
            testComp.getTimeField().setText(s.getElapsedStringSecs());
            testComp.getErrField().setText(serr);

            Color c;
            if (percent < 75) {
                c = Color.green;
            } else {
                c = Color.orange;
            }
            getBar().setForeground(c);

        }
    }

    @Override
    protected void done() {
        getBar().setForeground(Color.red);
        getBar().setString("Terminato!");
        testComp.workerFinished();
    }


    /**
     * Ritorna una stringa random dal pool delle stringhe.
     */
    private String getRandomString() {
        int rnd = random.nextInt(Bot.STRINGHE.length);
        return Bot.STRINGHE[rnd];
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

    public JLabel getLabelStatus() {
        return testComp.getLabelStatus();
    }


    /**
     * Classe che rappresenta i risultati di un test
     */
    public class JobResults {

        private Tests test;
        private long nanos;
        private Object request;
        private Object response;
        private boolean valid;
        private String error;
        private int errCount = 0;

        public JobResults() {
        }

        public void put(Tests test, long nanos, Object request, Object response) {
            this.test = test;
            this.nanos = nanos;
            this.request = request;
            this.response = response;
        }

        /**
         * Aggiunge tempo al task esistente.
         *
         * @param nanos i nanos da aggiungere
         */
        public void addNanos(long nanos) {
            this.nanos+=nanos;
        }


        /**
         * Convalida tutti i risultati.
         * Esegue i task con gli algoritmi verificati.
         * Accende il flag valido su ogni task valido.
         * Regola il testo d errore su ogni task non valido.
         */
        public void validate() {
            String botname = getBot().getNome();


            String request;
            String taskname;
            switch (test) {


                case SORT_WORD:
                    taskname = test.getTestName();
                    request = (String)getRequest();
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.sortWord(request);
                        String checkResponse = (String)getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid=true;
                        } else {
                            error=botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error=botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                    break;


                case INVERT_WORD:
                    taskname = test.getTestName();
                    request = (String)getRequest();
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.invertWord(request);
                        String checkResponse = (String)getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid=true;
                        } else {
                            error=botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error=botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                    break;

                case CALC_CKECKSUM:
                    taskname = test.getTestName();
                    request = (String)getRequest();
                    if (getResponse() != null) {
                        int goodResponse = BotAlgorhitms.calcChecksum(request);
                        int checkResponse = (Integer)getResponse();
                        if (checkResponse == goodResponse) {
                            valid=true;
                        } else {
                            error=botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error=botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                    break;

                case DECRYPT_WORD:
                    taskname = test.getTestName();
                    String[] strings = (String[])getRequest();
                    String word = strings[0];
                    String key = strings[1];
                    String wordkey = word + "," + key;
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.decryptWord(word, key);
                        String checkResponse = (String)getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid=true;
                        } else {
                            error=botname + " : " + taskname + ": risposta non valida: req->" + wordkey + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error=botname + " : " + taskname + ": risposta nulla: req->" + wordkey + " resp->null";
                    }
                    break;

            }
        }

        /**
         * Controlla se il risultato è valido.
         *
         * @return true se valido
         */
        public boolean isValid() {
            return valid==true;
        }

        public Object getResponse() {
            return response;
        }

        public Object getRequest() {
            return request;
        }

        public long getNanos() {
            return nanos;
        }

        public String getError() {
            return error;
        }
    }
}
