/**
 * Tester delle implementazioni di Bot.
 */
public class BotTester {
    private Bot bot;

    public BotTester(Bot bot) {
        this.bot = bot;
    }

    public void startTest(){

        int quanteElab=0;
        int quanteOK=0;
        for(String word : Bot.STRINGHE){
            String expected = BotAlgorhitms.sortWord(word);
            String obtained = bot.sortWord(word);
            boolean passed=false;
            if(obtained!=null){
                passed=(obtained.equals(expected));
            }

            if(passed) {
                quanteOK++;
            }else{
                System.out.println("atteso: "+expected+" ricevuto: "+obtained);
            }

            quanteElab++;
        }

        String s="Terminato. "+quanteElab+" elaborate, "+quanteOK+" OK";
        System.out.println(s);

    }




}
