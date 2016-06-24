package it.robobattle;

import java.util.HashMap;

/**
 * Created by alex on 24/06/16.
 */
public class BotResults {

    private Bot bot;
    private HashMap<Tests, TestSessionResult> results;

    public BotResults(Bot bot) {
        this.bot = bot;
        results=new HashMap<Tests, TestSessionResult>();
    }

    public void putResult(Tests test, TestSessionResult result){
        results.put(test, result);
    }

    public TestSessionResult getResult(Tests test){
        return results.get(test);
    }

    public Bot getBot() {
        return bot;
    }
}
