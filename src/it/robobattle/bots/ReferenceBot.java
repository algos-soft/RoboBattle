package it.robobattle.bots;

import it.robobattle.Bot;
import it.robobattle.BotAlgorithms;

import java.awt.*;

/**
 * Created by alex on 28/06/16.
 */
public class ReferenceBot implements Bot {

    @Override
    public String getNome() {
        return "Reference bot";
    }

    @Override
    public String getSlogan() {
        return "Meglio un giorno da leoni";
    }

    @Override
    public Color getColore() {
        return new Color(204,0,102);
    }

    @Override
    public String invertWord(String in) {
        return BotAlgorithms.invertWord(in);
    }

    @Override
    public int calcChecksum(String in) {
        return BotAlgorithms.calcChecksum(in);
    }

    @Override
    public String decryptWord(String in, String key) {
        return BotAlgorithms.decryptWord(in, key);
    }

    @Override
    public String sortWord(String in) {
        return BotAlgorithms.sortWord(in);
    }
}
