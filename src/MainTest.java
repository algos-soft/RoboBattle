/**
 * Created by alex on 15-06-2016.
 */
public class MainTest {

    public static void main(String[] args){
        BotTester tester;



//        tester=new BotTester(new RoboBot());
//        tester.setLogLevel(1);
//        tester.startTest();

        tester=new BotTester(new KiloBot());
        tester.setLogLevel(1);
        tester.startTest();


        //new BotTester(new KiloBot()).startTest();
    }

}
