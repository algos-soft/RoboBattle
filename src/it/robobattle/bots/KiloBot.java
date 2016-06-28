package it.robobattle.bots;

import it.robobattle.Bot;
import it.robobattle.BotAlgorhitms;

import java.awt.*;
import java.math.BigInteger;

public class KiloBot implements Bot {

	@Override
	public String getNome() {
		return "KiloBot";
	}

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	public String getSlogan(){
		return "Hasta la victoria siempre!";
	}


	@Override
	public Color getColore() {
		return Color.GREEN;
	}

	@Override
	public String invertWord(String in) {
		return BotAlgorhitms.invertWordErr(in);
	}

	@Override
	public int calcChecksum(String in) {
		return 0;
	}

	@Override
	public String decryptWord(String in, String key) {
		return null;
	}

	@Override
	public String sortWord(String in) {
		return BotAlgorhitms.sortWordSlow(in);
	}

	public String rispondi(String in) {
		for(int i=0;i<30000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
