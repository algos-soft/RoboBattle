package it.robobattle.bots;

import it.robobattle.Bot;
import it.robobattle.BotAlgorithms;

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
		return BotAlgorithms.invertWordSlow(in);
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
