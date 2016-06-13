import java.math.BigInteger;

public class RoboBot implements Bot {

	@Override
	public String getNome() {
		return "RoboBot";
	}
	
	@Override
	public String rispondi(String in) {
		for(int i=0;i<50000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}



}
