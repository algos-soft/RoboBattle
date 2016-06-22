import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotWorker extends SwingWorker<Void, JobStatus> {

    private Bot bot; // il bot impegnato;
    private BotTestComponent testComp;  // il componente grafico di testing
    private JProgressBar bar; // la progress bar della coda
    private JLabel labelStatus; // label per lo status del bot
    private int totRequests;    // numero totale di richieste da inviare in una sessione di test
    private Random random = new Random();    // random generator
    private boolean finished;    // se l'elaborazione è terminata
    private JobResults sessionResults;


    public BotWorker(BotTestComponent testComp, Bot bot, JProgressBar bar, JLabel labelStatus) {
        super();
        this.testComp=testComp;
        this.bot = bot;
        this.bar = bar;
        this.labelStatus = labelStatus;

        bar.setStringPainted(true);

//        totRequests = arena.getSpeedSlider().getValue();
//        arena.getSpeedSlider().addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                totRequests = arena.getSpeedSlider().getValue();
//            }
//        });

        totRequests=20000;

    }

    @Override
    protected Void doInBackground() throws Exception {

        boolean stop = false;
        long totRichiesteElaborate = 0;
        long lastUpdateMillis = 0;
        long totTimeNanos = 0;

        // mantiene i risultati globali della sessione di run
        sessionResults = new JobResults();

        while (!stop) {

            // fa eseguire una operazione al bot
            JobResults results = new JobResults();
            doJob(results);
            totTimeNanos += results.getNanos();

            // controlla e convalida i risultati
            results.validate();

            // se i risultati non sono validi, logga gli errori
            if (!results.isValid()) {
                results.logErrors();
            }

            // aggiorna i risultati globali
            for (Task task : results.getTasks()) {
                Tests test=task.getTest();
                long nanos=task.getNanos();
                boolean valid=task.isValid();

                // aggiunge il tempo ai risultati globali
                sessionResults.addNanos(test, nanos);

                // se non valido incrementa il conteggio degli errori.
                // al primo task non valido mette tutto il test non valido.
                if (!valid) {
                    //sessionResults.addErrorCount();
                    sessionResults.getTask(test).setValid(false);
                }
            }

            totRichiesteElaborate++;

            // ogni tanto pubblica lo stato di avanzamento
            long elapsedSinceLastUpdate = System.currentTimeMillis() - lastUpdateMillis;
            if (elapsedSinceLastUpdate > 250) {
                JobStatus status = new JobStatus(totRichiesteElaborate, totTimeNanos / 1000000);
                publish(status);
                lastUpdateMillis = System.currentTimeMillis();
            }

            // quando ha elaborato tutte le righieste previste si ferma
            if (totRichiesteElaborate >= totRequests) {
                finished = true;
                JobStatus status = new JobStatus(totRichiesteElaborate, totTimeNanos / 1000000);
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
        int sum = 0;

        if (arena.isCheck1()) {
            request = getRandomString();
            t1 = System.nanoTime();
            response = bot.sortWord(request);
            results.put(Tests.SORT_WORD, System.nanoTime() - t1, request, response);
        }

        if (arena.isCheck2()) {
            request = getRandomString();
            t1 = System.nanoTime();
            response = bot.invertWord(request);
            results.put(Tests.INVERT_WORD, System.nanoTime() - t1, request, response);

        }

        if (arena.isCheck3()) {
            request = getRandomString();
            t1 = System.nanoTime();
            sum = bot.calcChecksum(request);
            results.put(Tests.CALC_CKECKSUM, System.nanoTime() - t1, request, sum);

        }
        if (arena.isCheck4()) {
            request = getRandomString();
            String key = "abcd";
            t1 = System.nanoTime();
            response = bot.decryptWord(request, key);
            long nanos = System.nanoTime() - t1;
            String[] strings = {request, key};
            results.put(Tests.DECRYPT_WORD, nanos, strings, response);
        }

    }


    @Override
    protected void process(List<JobStatus> chunks) {
        for (JobStatus s : chunks) {
            int percent = (int) (100 * s.getNumRequests() / totRequests);
            bar.setValue(percent);
            bar.setString(percent + "%");
            String snum = NumberFormat.getIntegerInstance().format(+s.getNumRequests());
            labelStatus.setText("tot: " + snum + " - CPU time: " + s.getElapsedStringSecs());

            Color c;
            if (percent < 75) {
                c = Color.green;
            } else {
                c = Color.orange;
            }
            bar.setForeground(c);

        }
    }

    @Override
    protected void done() {
        bar.setForeground(Color.red);
        bar.setString("Terminato!");
        arena.workerFinished(this);
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
        return bot;
    }

    public JobResults getSessionResults() {
        return sessionResults;
    }

    /**
     * Ritorna un testo con i risultati della sessione.
     * @return il testo
     */
    public String getSessionInfo(){
        String text="";
        JobResults results = getSessionResults();
        for(Task task : results.getTasks()){
            task.getNanos();
        }
        return text;
    }

    /**
     * Classe che rappresenta i risultati di un test
     */
    private class JobResults {

        private ArrayList<Task> tasks;

        public JobResults() {
            tasks = new ArrayList();
        }

        public void put(Tests test, long nanos, Object request, Object response) {
            Task task = new Task(test, nanos, request, response);
            tasks.add(task);
        }

        /**
         * Aggiunge tempo a un task esistente.
         * Se il task non esiste aggiunge un Task ora.
         *
         * @param test  il tipo di test
         * @param nanos i nanos da aggiungere
         */
        public void addNanos(Tests test, long nanos) {
            boolean found = false;
            for (Task t : tasks) {
                if (t.getTest().equals(test)) {
                    found = true;
                    t.addNanos(nanos);
                    break;
                }
            }
            // se non trovato aggiunge il task ora con i nanos
            if (!found) {
                put(test, nanos, null, null);
            }
        }


        /**
         * Convalida tutti i risultati.
         * Esegue i task con gli algoritmi verificati.
         * Accende il flag valido su ogni task valido.
         * Regola il testo d errore su ogni task non valido.
         */
        public void validate() {
            String botname = bot.getNome();

            for (Task t : tasks) {

                String request;
                String taskname;
                switch (t.getTest()) {


                    case SORT_WORD:
                        taskname = t.getTest().getTestName();
                        request = (String) t.getRequest();
                        if (t.getResponse() != null) {
                            String goodResponse = BotAlgorhitms.sortWord(request);
                            String checkResponse = (String) t.getResponse();
                            if (checkResponse.equals(goodResponse)) {
                                t.setValid(true);
                            } else {
                                t.setError(botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse);
                            }
                        } else {
                            t.setError(botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null");
                        }
                        break;


                    case INVERT_WORD:
                        taskname = t.getTest().getTestName();
                        request = (String) t.getRequest();
                        if (t.getResponse() != null) {
                            String goodResponse = BotAlgorhitms.invertWord(request);
                            String checkResponse = (String) t.getResponse();
                            if (checkResponse.equals(goodResponse)) {
                                t.setValid(true);
                            } else {
                                t.setError(botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse);
                            }
                        } else {
                            t.setError(botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null");
                        }
                        break;

                    case CALC_CKECKSUM:
                        taskname = t.getTest().getTestName();
                        request = (String) t.getRequest();
                        if (t.getResponse() != null) {
                            int goodResponse = BotAlgorhitms.calcChecksum(request);
                            int checkResponse = (Integer) t.getResponse();
                            if (checkResponse == goodResponse) {
                                t.setValid(true);
                            } else {
                                t.setError(botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse);
                            }
                        } else {
                            t.setError(botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null");
                        }
                        break;

                    case DECRYPT_WORD:
                        taskname = t.getTest().getTestName();
                        String[] strings = (String[]) t.getRequest();
                        String word = strings[0];
                        String key = strings[1];
                        String wordkey = word + "," + key;
                        if (t.getResponse() != null) {
                            String goodResponse = BotAlgorhitms.decryptWord(word, key);
                            String checkResponse = (String) t.getResponse();
                            if (checkResponse.equals(goodResponse)) {
                                t.setValid(true);
                            } else {
                                t.setError(botname + " : " + taskname + ": risposta non valida: req->" + wordkey + " resp->" + checkResponse + " good->" + goodResponse);
                            }
                        } else {
                            t.setError(botname + " : " + taskname + ": risposta nulla: req->" + wordkey + " resp->null");
                        }
                        break;

                }
            }
        }

        /**
         * Controlla se i risultati sono validi.
         *
         * @return true se tutti i risultati sono validi
         */
        public boolean isValid() {
            boolean valid = true;
            for (Task t : tasks) {
                if (!t.isValid()) {
                    valid = false;
                }
            }
            return valid;
        }

        /**
         * Ritorna il tempo totale impiegato per svolgere tutti i tasks
         *
         * @return il tempo totale
         */
        public long getNanos() {
            long nanos = 0;
            for (Task t : tasks) {
                nanos += t.getNanos();
            }
            return nanos;
        }


        /**
         * @param test il test
         * @return il task, null se non trovato
         */
        public Task getTask(Tests test) {
            Task found=null;
            for (Task t : tasks) {
                if(t.getTest().equals(test)){
                    found=t;
                }
            }
            return found;
        }

        /**
         * Logga tutti gli errori
         */
        public void logErrors() {
            for (Task t : tasks) {
                if (!t.isValid()) {
                    System.out.println(t.getError());
                }
            }
        }

        public ArrayList<Task> getTasks() {
            return tasks;
        }
    }

    /**
     * Classe che rappresenta i risultati di un task
     */
    private class Task {

        private Tests test;
        private long nanos;
        private Object request;
        private Object response;
        private boolean valid;
        private String error;
        private int errCount=0;

        public Task(Tests test, long nanos, Object request, Object response) {
            this.test = test;
            this.nanos = nanos;
            this.request = request;
            this.response = response;
        }

        public Tests getTest() {
            return test;
        }

        public long getNanos() {
            return nanos;
        }

        public Object getRequest() {
            return request;
        }

        public Object getResponse() {
            return response;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void addNanos(long nanos) {
            this.nanos = this.nanos + nanos;
        }

        public void addErrCount(){
            this.errCount=this.errCount+1;
        }

    }
}
