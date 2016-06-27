package it.robobattle;

import java.awt.*;
import java.math.BigInteger;

public class RoboBot implements Bot {

	@Override
	public String getNome() {
		return "RoboBot";
	}

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	public String getSlogan(){
		return "Meglio un giorno da leoni...";
	}


	@Override
	public Color getColore() {
		return Color.MAGENTA;
	}

	@Override
	public String invertWord(String in) {
		return BotAlgorhitms.invertWord(in);
	}

	@Override
	public int calcChecksum(String in) {
		Object s = "ciao";
		Integer b=(Integer)s;
		return b;

//		int a = 5;
//		int b=0;
//		int c=a/b;
//		return c;

//		int checksum=0;
//		char[] chars = in.toCharArray();
//		for(char c: chars){
//			checksum+=(int)c;
//		}
//		return checksum;

	}

	@Override
	public String decryptWord(String in, String key) {
		return null;
	}

	@Override
	public String sortWord(String in) {
		return BotAlgorhitms.sortWord(in);
	}

	public String rispondi(String in) {
		for(int i=0;i<50000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}



}
