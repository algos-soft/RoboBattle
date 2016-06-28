package it.robobattle;

import it.robobattle.bots.*;

/**
 * Created by alex on 28/06/16.
 */
public class DemoTester {

    public static void main(String[] args){

        // sample bot test
        BotTester tester=new BotTester(new KiloBot());
        tester.startTest(Tests.CALC_CKECKSUM, 100);

    }

}
