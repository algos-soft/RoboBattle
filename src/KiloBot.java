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
	public String rispondi(String in) {
		for(int i=0;i<30000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
