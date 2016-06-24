package it.robobattle;

import java.awt.*;
import java.math.BigInteger;

public class GigaBot extends BotAdapter {

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


	public String rispondi(String in) {
		for(int i=0;i<10000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
