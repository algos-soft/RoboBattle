package it.robobattle.bots;

import it.robobattle.Bot;
import it.robobattle.BotAlgorithms;

import java.awt.*;
import java.math.BigInteger;

public class MegaBot implements Bot {


	@Override
	public String getNome() {
		return "MegaBot";
	}

	@Override
	public String getSlogan() {
		return "Veni, vidi, vici";
	}

	@Override
	public Color getColore() {
		return Color.ORANGE;
	}

	@Override
	public String invertWord(String in) {
		return BotAlgorithms.invertWordErr(in);
	}

	@Override
	public int calcChecksum(String in) {
		return BotAlgorithms.calcChecksumSlow(in);
	}

	@Override
	public String decryptWord(String in, String key) {
		return BotAlgorithms.decryptWordErr(in, key);
	}

	@Override
	public String sortWord(String in) {
		return BotAlgorithms.sortWord(in);
	}
}
