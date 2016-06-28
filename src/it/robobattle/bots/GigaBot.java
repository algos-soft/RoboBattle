package it.robobattle.bots;

import it.robobattle.Bot;
import it.robobattle.BotAlgorithms;

import java.awt.*;
import java.math.BigInteger;

public class GigaBot implements Bot {

	@Override
	public String getNome() {
		return "GigaBot";
	}

	@Override
	public Color getColore() {
		return Color.ORANGE;
	}

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	public String getSlogan(){
		return "La forza sia con noi!";
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
		return BotAlgorithms.decryptWordSlow(in, key);
	}

	@Override
	public String sortWord(String in) {
		return BotAlgorithms.sortWord(in);
	}



}
