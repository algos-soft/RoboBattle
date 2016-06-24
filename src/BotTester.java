import java.util.Arrays;

/**
 * Tester delle implementazioni di Bot.
 */
public class BotTester {

    private Bot bot;
    int logLevel=0;

    public BotTester(Bot bot) {
        this.bot = bot;
        logLevel=2; // max
    }

    public void startTest(Tests test){

        int quanteElab=0;
        int quanteOK=0;
        long totNanos=0;
        long i=0;
        String[] strings = creaStringhe();
        for(String word : strings){
            i++;


            long jobStartNanos=0;
            long jobLengthNano=0;
            Object expected=null;
            Object obtained=null;
            switch (test){

                case SORT_WORD:
                    expected = BotAlgorhitms.sortWord(word);
                    jobStartNanos=System.nanoTime();
                    obtained = bot.sortWord(word);
                    jobLengthNano=System.nanoTime()-jobStartNanos;
                    break;

                case INVERT_WORD:
                    expected = BotAlgorhitms.invertWord(word);
                    jobStartNanos=System.nanoTime();
                    obtained = bot.invertWord(word);
                    jobLengthNano=System.nanoTime()-jobStartNanos;
                    break;

                case CALC_CKECKSUM:
                    expected = BotAlgorhitms.calcChecksum(word);
                    jobStartNanos=System.nanoTime();
                    obtained = bot.calcChecksum(word);
                    jobLengthNano=System.nanoTime()-jobStartNanos;
                    break;

                case DECRYPT_WORD:
                    String key = "abcd";
                    expected = BotAlgorhitms.decryptWord(word, key);
                    jobStartNanos=System.nanoTime();
                    obtained = bot.decryptWord(word, key);
                    jobLengthNano=System.nanoTime()-jobStartNanos;
                    break;

            }

            totNanos+=jobLengthNano;

            boolean passed=false;
            if(obtained!=null){
                passed=(obtained.equals(expected));
            }

            if(passed) {
                quanteOK++;
                if(logLevel>=2){
                    System.out.println(i+" "+word+" OK - "+jobLengthNano+" nanos");
                }
            }else{
                if(logLevel>=1){
                    System.out.println("atteso: "+expected+" ricevuto: "+obtained);
                }
            }

            quanteElab++;
        }

        long totMillis=totNanos/1000000;
        long quantePerSec = quanteElab/totMillis*1000;
        String s=bot.getNome()+" "+quanteElab+" elaborate, "+quanteOK+" OK, "+totMillis+" ms, "+quantePerSec+" wps";
        System.out.println(s);

    }

    private String[] creaStringhe(){
        int quantiBlocchi=1000;
        String[] stringhe = new String[Bot.STRINGHE.length*quantiBlocchi];
        for(int i=0; i<quantiBlocchi;i++){
            for(int j=0; j<Bot.STRINGHE.length;j++){
                int k=(i*Bot.STRINGHE.length)+j;
                stringhe[k]=Bot.STRINGHE[j];
            }
        }
        return stringhe;
    }


    public void setLogLevel(int i) {
        this.logLevel=i;
    }
}
