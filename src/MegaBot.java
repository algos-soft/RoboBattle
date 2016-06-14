import java.awt.*;
import java.math.BigInteger;

public class MegaBot implements Bot {

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
	public String rispondi(String in) {
		for(int i=0;i<20000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
