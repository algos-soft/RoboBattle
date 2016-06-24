package it.robobattle;

import java.awt.*;

/**
 * Created by alex on 15-06-2016.
 */
public class BotAdapter implements Bot{

    @Override
    public String getNome() {
        return "BotAdapter";
    }

    @Override
    public String getSlogan() {
        return "Bot slogan";
    }

    @Override
    public Color getColore() {
        return Color.GREEN;
    }

    @Override
    public String invertWord(String in) {
        return "";
    }

    @Override
    public int calcChecksum(String in) {
        return 0;
    }

    @Override
    public String decryptWord(String in, String key) {
        return "";
    }

    @Override
    public String sortWord(String in) {
        return "";
    }
}
