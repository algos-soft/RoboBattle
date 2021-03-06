package it.robobattle;

import java.util.Random;

/**
 * Tester delle implementazioni di Bot.
 * </p>
 * L'esempio seguente istanzia un tester con un dato bot, ed esegue
 * un test con 100 blocchi da 1000 parole ognuno:
 * </p>
 * <code>
 * Bot bot = new MyBot();<br>
 * BotTester tester=new BotTester(bot);<br>
 * tester.startTest(Tests.CALC_CKECKSUM, 100);<br>
 * </code>
 */
public class BotTester {

    private Bot bot;
    int logLevel = 0;
    private Random random = new Random();    // random generator


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
     * @param test          il test da eseguire
     * @param quantiBlocchi il numero di blocchi di parole da testare (1000 parole per blocco)
     */
    public void startTest(Tests test, int quantiBlocchi) {

        int quanteElab = 0;
        int quanteOK = 0;
        long totNanos = 0;
        long i = 0;
        String[] strings = creaStringhe(quantiBlocchi);
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
                    String key = getRandomString();
                    String encrypted = BotAlgorithms.encryptWord(word, key);
                    expected = word;
                    jobStartNanos = System.nanoTime();
                    obtained = bot.decryptWord(encrypted, key);
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
     * Ritorna una stringa random dal pool delle stringhe.
     */
    private String getRandomString() {
        int rnd = random.nextInt(Data.STRINGHE.length);
        return Data.STRINGHE[rnd];
    }


    /**
     * Regola il livello di log.
     * </p>
     * 0=normale (stampa solo 1 riga di log alla fine del test)<br>
     * 1=info (stampa una riga di log per ogni errore e la riga di log finale)<br>
     * 2=debug (stampa il log di tutte le operazioni e la riga di log)<br>
     * il livello di default è 1 (info)
     *
     * @param i il livello di log
     */
    public void setLogLevel(int i) {
        this.logLevel = i;
    }
}
