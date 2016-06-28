package it.robobattle;

import it.robobattle.bots.*;

/**
 * Created by alex on 15-06-2016.
 */
public class DemoBattle {

    public static void main(String[] args){



//        tester=new BotTester(new RoboBot());
//        tester.setLogLevel(1);
//        tester.startTest(Tests.SORT_WORD, 100);

//        BotTester tester;
//        tester=new BotTester(new KiloBot());
//        tester.setLogLevel(1);
//        tester.startTest(Tests.INVERT_WORD, 1000);


        //new BotTester(new KiloBot()).startTest();

        new RoboBattle(new ReferenceBot(), new KiloBot(),new MegaBot(),  new GigaBot(),  new Robot2LaVendetta());

    }

}
