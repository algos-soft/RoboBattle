import java.math.BigInteger;

public class SuperBot implements Bot {

	@Override
	public String getNome() {
		return "SuperBot";
	}
	
	@Override
	public String rispondi(String in) {
		for(int i=0;i<30000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
