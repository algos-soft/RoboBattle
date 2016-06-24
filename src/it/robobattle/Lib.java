package it.robobattle;

/**
 * Created by alex on 24/06/16.
 */
public class Lib {

    public static String millisToString(long millis){
        long intero = millis/1000;
        long resto = millis % 1000;
        return intero+":"+resto;
    }

}
