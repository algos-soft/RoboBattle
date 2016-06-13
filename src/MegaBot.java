import java.math.BigInteger;

public class MegaBot implements Bot {

	@Override
	public String getNome() {
		return "MegaBot";
	}
	
	@Override
	public String rispondi(String in) {
		for(int i=0;i<20000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
