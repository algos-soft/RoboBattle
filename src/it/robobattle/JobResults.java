package it.robobattle;

/**
 * Classe che rappresenta i risultati di un test
 */
public class JobResults {

    private Bot bot;
    private Tests test;
    private long nanos;
    private Object request;
    private Object response;
    private boolean valid;
    private String error;
    private int errCount = 0;

    public JobResults(Bot bot) {
        this.bot = bot;
    }

    public void setData(Tests test, long nanos, Object request, Object response, String error) {
        this.test = test;
        this.nanos = nanos;
        this.request = request;
        this.response = response;
        this.error = error;
    }

    /**
     * Convalida tutti i risultati.
     * Esegue i task con gli algoritmi verificati.
     * Accende il flag valido su ogni task valido.
     * Regola il testo d errore su ogni task non valido.
     */
    public void validate() {
        String botname = bot.getNome();


        String request;
        String taskname;
        switch (test) {


            case SORT_WORD:
                taskname = test.getTestName();
                request = (String) getRequest();
                if (error == null) {
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.sortWord(request);
                        String checkResponse = (String) getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid = true;
                        } else {
                            error = botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error = botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                }
                break;


            case INVERT_WORD:
                taskname = test.getTestName();
                request = (String) getRequest();
                if (error == null) {
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.invertWord(request);
                        String checkResponse = (String) getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid = true;
                        } else {
                            error = botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error = botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                }
                break;


            case CALC_CKECKSUM:
                taskname = test.getTestName();
                request = (String) getRequest();
                if (error == null) {
                    if (getResponse() != null) {
                        int goodResponse = BotAlgorhitms.calcChecksum(request);
                        int checkResponse = (Integer) getResponse();
                        if (checkResponse == goodResponse) {
                            valid = true;
                        } else {
                            error = botname + " : " + taskname + ": risposta non valida: req->" + request + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error = botname + " : " + taskname + ": risposta nulla: req->" + request + " resp->null";
                    }
                }
                break;

            case DECRYPT_WORD:
                taskname = test.getTestName();
                String[] strings = (String[]) getRequest();
                String word = strings[0];
                String key = strings[1];
                String wordkey = word + "," + key;
                if (error == null) {
                    if (getResponse() != null) {
                        String goodResponse = BotAlgorhitms.decryptWord(word, key);
                        String checkResponse = (String) getResponse();
                        if (checkResponse.equals(goodResponse)) {
                            valid = true;
                        } else {
                            error = botname + " : " + taskname + ": risposta non valida: req->" + wordkey + " resp->" + checkResponse + " good->" + goodResponse;
                        }
                    } else {
                        error = botname + " : " + taskname + ": risposta nulla: req->" + wordkey + " resp->null";
                    }
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
        return valid == true;
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
