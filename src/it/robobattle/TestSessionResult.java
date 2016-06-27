package it.robobattle;

/**
 * Created by alex on 24/06/16.
 * Risultato complessivo di una sessione un singolo test.
 */
public class TestSessionResult {
    private Tests test;
    private Bot bot;
    private int points;

    private long wordcount;
    private long errcount;
    private long nanos;

    public TestSessionResult(Bot bot, Tests test) {
        this.bot=bot;
        this.test = test;
    }

    public void addNanos(long n){
        this.nanos+=n;
    }

    public void addWordCount(int n){
        this.wordcount+=n;
    }


    public void addErrCount(int n){
        this.errcount+=n;
    }

    @Override
    public String toString() {
        String s = "words: "+wordcount;
        s+=" err: "+errcount;
        s+=" millis: "+nanos/1000;

        return s;
    }

    public Tests getTest() {
        return test;
    }

    public Bot getBot() {
        return bot;
    }

    public long getWordcount() {
        return wordcount;
    }

    public long getErrcount() {
        return errcount;
    }

    public long getNanos() {
        return nanos;
    }

    public long getMillis(){
        return nanos/1000000;
    }

    public float getSeconds(){
        return nanos/1000000000f;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
