package it.robobattle;

import java.awt.*;
import java.math.BigInteger;

public class MegaBot extends BotAdapter {

	@Override
	public String getNome() {
		return "MegaBot";
	}

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	public String getSlogan(){
		return "Veni, vidi, vici";
	}

	@Override
	public Color getColore() {
		return Color.CYAN;
	}

	@Override
	public String sortWord(String in) {
		return BotAlgorhitms.sortWordErr(in);
	}


	public String rispondi(String in) {
		for(int i=0;i<20000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}