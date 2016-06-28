package it.robobattle;

/**
 * Tester delle implementazioni di Bot.
 * </p>
 * Questo esempio istanzia un tester con un dato bot, ed esegue un test:
 * </p>
 * <code>
 * BotTester tester=new BotTester(new MyBot());<br>
 * tester.startTest(Tests.CALC_CKECKSUM, 100);<br>
 * </code>
 */
public class BotTester {

    private Bot bot;
    int logLevel = 0;

    /**
     * Costruttore.
     *
     * @param bot il bot da testare
     */
    public BotTester(Bot bot) {
        this.bot = bot;
        logLevel = 1; // max = 2
    }

    /**
     * Avvia una sessione di test.
     * </p>
     *
     * @param test         il test da eseguire
     * @param quanteParole il numero di blocchi di parole da testare (1000 parole per blocco)
     */
    public void startTest(Tests test, int quanteParole) {

        int quanteElab = 0;
        int quanteOK = 0;
        long totNanos = 0;
        long i = 0;
        String[] strings = creaStringhe(quanteParole);
        for (String word : strings) {
            i++;


            long jobStartNanos = 0;
            long jobLengthNano = 0;
            Object expected = null;
            Object obtained = null;


            switch (test) {

                case SORT_WORD:
                    expected = BotAlgorithms.sortWord(word);
                    jobStartNanos = System.nanoTime();
                    obtained = bot.sortWord(word);
                    jobLengthNano = System.nanoTime() - jobStartNanos;
                    break;

                case INVERT_WORD:
                    expected = BotAlgorithms.invertWord(word);
                    jobStartNanos = System.nanoTime();
                    obtained = bot.invertWord(word);
                    jobLengthNano = System.nanoTime() - jobStartNanos;
                    break;

                case CALC_CKECKSUM:
                    expected = BotAlgorithms.calcChecksum(word);
                    jobStartNanos = System.nanoTime();
                    obtained = bot.calcChecksum(word);
                    jobLengthNano = System.nanoTime() - jobStartNanos;
                    break;

                case DECRYPT_WORD:
                    String key = "abcd";
                    expected = BotAlgorithms.decryptWord(word, key);
                    jobStartNanos = System.nanoTime();
                    obtained = bot.decryptWord(word, key);
                    jobLengthNano = System.nanoTime() - jobStartNanos;
                    break;

            }

            totNanos += jobLengthNano;

            boolean passed = false;
            if (obtained != null) {
                passed = (obtained.equals(expected));
            }

            if (passed) {
                quanteOK++;
                if (logLevel >= 2) {
                    System.out.println(i + " " + word + " OK - " + jobLengthNano + " nanos");
                }
            } else {
                if (logLevel >= 1) {
                    System.out.println("atteso: " + expected + " ricevuto: " + obtained);
                }
            }

            quanteElab++;
        }

        long totMillis = totNanos / 1000000;
        long quantePerSec = quanteElab / totMillis * 1000;
        String s = bot.getNome() + " " + test.getTestName() + " " + quanteElab + " elaborate, " + quanteOK + " OK, " + totMillis + " ms, " + quantePerSec + " wps";
        System.out.println(s);

    }

    private String[] creaStringhe(int quantiBlocchi) {
        String[] stringhe = new String[Data.STRINGHE.length * quantiBlocchi];
        for (int i = 0; i < quantiBlocchi; i++) {
            for (int j = 0; j < Data.STRINGHE.length; j++) {
                int k = (i * Data.STRINGHE.length) + j;
                stringhe[k] = Data.STRINGHE[j];
            }
        }
        return stringhe;
    }


    /**
     * Regola il livello di log.
     * </p>
     * 0=normale (solo log finale)<br>
     * 1=info (log dei soli errori e log finale, questo è il default)<br>
     * 2=debug (log di tutte le operazioni e log finale)<br>
     *
     * @param i il livello di log
     */
    public void setLogLevel(int i) {
        this.logLevel = i;
    }
}
