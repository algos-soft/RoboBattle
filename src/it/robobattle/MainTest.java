package it.robobattle;

/**
 * Created by alex on 15-06-2016.
 */
public class MainTest {

    public static void main(String[] args){
        BotTester tester;



//        tester=new BotTester(new RoboBot());
//        tester.setLogLevel(1);
//        tester.startTest(Tests.SORT_WORD, 100);

        tester=new BotTester(new KiloBot());
        tester.setLogLevel(1);
        tester.startTest(Tests.INVERT_WORD, 1000);


        //new BotTester(new KiloBot()).startTest();
    }

}
