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
	public String rispondi(String in) {
		for(int i=0;i<10000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
