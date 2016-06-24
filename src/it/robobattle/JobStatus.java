package it.robobattle;

/**
 * Wrapper per trasportare le informazioni di stato avanzamento
 */
public class JobStatus {
    long numRequests;
	long elapsedMillis;
	long numErrors;

    public JobStatus(long numRequests, long elapsedMillis, long numErrors) {
        this.numRequests = numRequests;
        this.elapsedMillis = elapsedMillis;
		this.numErrors=numErrors;
    }

	public long getNumRequests() {
		return numRequests;
	}

	public long getElapsedMillis() {
		return elapsedMillis;
	}

	public long getNumErrors() {
		return numErrors;
	}

	public String getElapsedStringSecs(){
		long intero = elapsedMillis/1000;
		long resto = elapsedMillis % 1000;
		return intero+":"+resto;
	}
}
