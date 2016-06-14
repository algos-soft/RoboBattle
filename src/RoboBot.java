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
	public String rispondi(String in) {
		for(int i=0;i<50000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}



}
