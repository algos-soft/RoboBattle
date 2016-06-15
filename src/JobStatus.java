/**
 * Wrapper per trasportare le informazioni di stato avanzamento
 */
public class JobStatus {
    long numRequests;
    long elapsedMillis;

    public JobStatus(long numRequests, long elapsedMillis) {
        this.numRequests = numRequests;
        this.elapsedMillis = elapsedMillis;
    }

	public long getNumRequests() {
		return numRequests;
	}

	public long getElapsedMillis() {
		return elapsedMillis;
	}

	public String getElapsedString(){
		long intero = elapsedMillis/1000;
		long resto = elapsedMillis % 1000;
		return intero+":"+resto;

	}
}
