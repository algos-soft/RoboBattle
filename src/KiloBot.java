import java.math.BigInteger;

public class KiloBot implements Bot {

	@Override
	public String getNome() {
		return "KiloBot";
	}
	
	@Override
	public String rispondi(String in) {
		for(int i=0;i<30000;i++){
            BigInteger.ONE.multiply(BigInteger.valueOf(i));
		}
		return null;
	}


}
