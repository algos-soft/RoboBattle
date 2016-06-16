import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotWorker extends SwingWorker<Void, JobStatus> {

	private Arena arena; // la Battle di riferimento
	private Bot bot; // il bot impegnato
	private JProgressBar bar; // la progress bar della coda
	private JLabel labelStatus; // label per lo status del bot
	private int totRequests;	// numero totale di richieste da inviare in una sessione di test
	private Random random = new Random();	// random generator
	private boolean finished;	// se l'elaborazione è terminata


	public BotWorker(final Arena arena, Bot bot, JProgressBar bar, JLabel labelStatus) {
		super();
		this.arena = arena;
		this.bot = bot;
		this.bar = bar;
		this.labelStatus = labelStatus;

		bar.setStringPainted(true);

		totRequests = arena.getSpeedSlider().getValue();
		arena.getSpeedSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				totRequests = arena.getSpeedSlider().getValue();
			}
		});

	}

	@Override
	protected Void doInBackground() throws Exception {

		boolean stop = false;
		long totRichiesteElaborate=0;
		long lastUpdateMillis = 0;
		long totTimeNanos=0;

		while (!stop) {

			// fa eseguire una operazione al bot
			JobResults results = new JobResults();
			long jobStartNanos=System.nanoTime();
			doJob(results);
			long jobLengthNano=System.nanoTime()-jobStartNanos;
			totTimeNanos+=jobLengthNano;



			//amplifica il tempo trascorso per dare importanza al tempo impiegato
			//Thread.sleep(jobLengthNano/10000);

			totRichiesteElaborate++;

			// ogni tanto pubblica lo stato di avanzamento
			long elapsedSinceLastUpdate = System.currentTimeMillis() - lastUpdateMillis;
			if (elapsedSinceLastUpdate > 250) {
				JobStatus status = new JobStatus(totRichiesteElaborate, totTimeNanos/1000000);
				publish(status);
				lastUpdateMillis = System.currentTimeMillis();
			}

			// quando ha elaborato tutte le righieste previste si ferma
			if (totRichiesteElaborate >= totRequests) {
				finished=true;
				JobStatus status = new JobStatus(totRichiesteElaborate,  totTimeNanos/1000000);
				publish(status);
				stop = true;
			}

			// se la battaglia è finita, si ferma
			if (arena.isFinished()) {
				finished=true;
				stop = true;
			}

		}

		return null;
	}

	/**
	 * Esegue un job sul bot
	 * esegue solo i task abilitati
	 * @param results un oggetto JobResults da riempire con i risultati dei singoli task
	 */
	private void doJob(JobResults results){
		long t1;
		String request;
		String response;
		int sum=0;

		if (arena.isCheck1()){
			request=getRandomString();
			t1 =System.nanoTime();
			response = bot.sortWord(request);
			results.put(Tests.SORT_WORD, System.nanoTime()-t1, request, response);
		}

		if (arena.isCheck2()){
			request=getRandomString();
			t1 =System.nanoTime();
			response = bot.invertWord(request);
			results.put(Tests.SORT_WORD, System.nanoTime()-t1, request, response);

		}

		if (arena.isCheck3()){
			request=getRandomString();
			t1 =System.nanoTime();
			sum = bot.calcChecksum(request);
			results.put(Tests.SORT_WORD, System.nanoTime()-t1, request, sum);

		}
		if (arena.isCheck4()){
			request=getRandomString();
			t1 =System.nanoTime();
			response = bot.decryptWord(request, "ABCD");
			sum = bot.calcChecksum(request);
		}

	}

	/**
	 * Controlla i risultati di un Job
	 * @param results l'oggetto JobResults da controllare
	 */
	private void checkJob(JobResults results){

	}

	/**
	 * Verifica se una risposta è corretta.
	 * @param request la richiesta
	 * @param response la risposta
	 * @return true se corretta
	 */
	private boolean checkResponse(String request, String response) {
		boolean ok=false;
		if(response!=null){
			ok=response.equals(request);
		}
		return ok;
	}


	@Override
	protected void process(List<JobStatus> chunks) {
		for (JobStatus s : chunks) {
			int percent =(int)(100 * s.getNumRequests() / totRequests);
			bar.setValue(percent);
			bar.setString(percent+"%");
			String snum = NumberFormat.getIntegerInstance().format(+s.getNumRequests());
			labelStatus.setText("tot: " + snum +" - CPU time: "+s.getElapsedString());

			Color c;
			if (percent < 75) {
				c = Color.green;
			} else {
				c = Color.orange;
			}
			bar.setForeground(c);

		}
	}

	@Override
	protected void done() {
		bar.setForeground(Color.red);
		bar.setString("Terminato!");
		arena.workerFinished(this);
	}

//	/**
//	 * Aggiunge alla coda un blocco di parole prese a caso.
//	 *
//	 * @param blockSize
//	 *            quante parole aggiungere
//	 */
//	private void addBlock(int blockSize) {
//		for (int i = 0; i < blockSize; i++) {
//			int rnd = random.nextInt(Bot.STRINGHE.length);
//			queue.add(Bot.STRINGHE[rnd]);
//		}
//	}

	/**
	 * Ritorna una stringa random dal pool delle stringhe.
	 */
	private String getRandomString() {
		int rnd = random.nextInt(Bot.STRINGHE.length);
		return Bot.STRINGHE[rnd];
	}


	public boolean isFinished() {
		return finished;
	}

	public Bot getBot() {
		return bot;
	}

	/**
	 * Classe che rappresenta i risultati di un test
	 */
	private class JobResults {

		private ArrayList<Task> tasks;

		public void put(Tests test, long nanos, Object request, Object response){
			Task task = new Task(test, nanos, request, response);
			tasks.add(task);
		}
	}

	/**
	 * Classe che rappresenta i risultati di un task
	 */
	private class Task{

		private Tests test;
		private long nanos;
		private Object request;
		private Object response;

		public Task(Tests test, long nanos, Object request, Object response) {
			this.test = test;
			this.nanos = nanos;
			this.request = request;
			this.response = response;
		}

		public Tests getTest() {
			return test;
		}

		public long getNanos() {
			return nanos;
		}

		public Object getRequest() {
			return request;
		}

		public Object getResponse() {
			return response;
		}
	}
}
