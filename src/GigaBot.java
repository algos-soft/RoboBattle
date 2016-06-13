import java.math.BigInteger;

public class GigaBot implements Bot {

	@Override
	public String getNome() {
		return "GigaBot";
	}

	@Override
	public String rispondi(String in) {
		for(int i=0;i<10000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
