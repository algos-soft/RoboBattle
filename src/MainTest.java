/**
 * Created by alex on 15-06-2016.
 */
public class MainTest {

    public static void main(String[] args){
        Bot bot = new RoboBot();
        BotTester tester = new BotTester(bot);
        tester.startTest();
    }

}
