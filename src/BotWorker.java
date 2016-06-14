import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BotWorker extends SwingWorker<Void, Integer> {

	private Arena battle; // la Battle di riferimento
	private Bot bot; // il bot impegnato
	private JProgressBar bar; // la progress bar della coda
	private JLabel labelStatus; // label per lo status del bot
	private List<String> queue; // la coda di stringhe
	private int maxQueueSize = 1000; // max dimensione coda prima di overflow
	private int richiestePerSecondo = 0; // numero di parole inviate al bot per
											// secondo
	private boolean finished;	// se l'elaborazione è terminata
	private boolean overflow;	// se il bot è andato in overflow
	private int totRichiesteElaborate = 0; // numero di richieste elaborate
	private int totRisposteErrate = 0; // numero di risposte errate


	public BotWorker(final Arena battle, Bot bot, JProgressBar bar, JLabel labelStatus) {
		super();
		this.battle = battle;
		this.bot = bot;
		this.bar = bar;
		this.labelStatus = labelStatus;

		bar.setStringPainted(true);

		richiestePerSecondo = battle.getSpeedSlider().getValue();
		battle.getSpeedSlider().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				richiestePerSecondo = battle.getSpeedSlider().getValue();
			}
		});

	}

	@Override
	protected Void doInBackground() throws Exception {
		queue = new ArrayList<String>();

		// addBlock(Bot.STRINGHE.length/10);

		boolean stop = false;
		long lastUpdateMillis = 0;
		long lastBlockAddMillis = 0;

		// long startMillis=System.currentTimeMillis();
		while (!stop) {

			// invia una parola al bot e la rimuove dalla coda
			if (queue.size() > 0) {
				String request = queue.remove(0);
				String response = bot.rispondi(request);
				// verifica la risposta
				boolean ok=checkResponse(request, response);
				if(!ok){
					totRisposteErrate++;
				}
				totRichiesteElaborate++;
			}

			// ogni 100 millis aggiunge un nuovo blocco di parole alla coda
			long elapsedSinceLastBlockAdd = System.currentTimeMillis() - lastBlockAddMillis;
			if (elapsedSinceLastBlockAdd > 100) {
				addBlock(richiestePerSecondo / 10);
				lastBlockAddMillis = System.currentTimeMillis();
			}

			// ogni tanto pubblica lo stato di avanzamento
			long elapsedSinceLastUpdate = System.currentTimeMillis() - lastUpdateMillis;
			if (elapsedSinceLastUpdate > 200) {
				publish(queue.size());
				lastUpdateMillis = System.currentTimeMillis();
			}

			// se la dimensione della coda supera il massimo, ha perso e si
			// ferma
			if (queue.size() > maxQueueSize) {
				finished=true;
				overflow=true;
				publish(queue.size());
				stop = true;
			}

			// se la battaglia è finita, si ferma
			if (battle.isFinished()) {
				finished=true;
				stop = true;
			}

		}

		return null;
	}

	/**
	 * Verifica se una risposta � corretta.
	 * @param request la richiesta
	 * @param response la risposta
	 * @return true se corretta
	 */
	private boolean checkResponse(String request, String response) {
		return true;
	}

	@Override
	protected void process(List<Integer> chunks) {
		for (int i : chunks) {
			int percent = 100 * i / maxQueueSize;
			bar.setValue(percent);
			bar.setString("" + queue.size());
			labelStatus.setText("tot: " + totRichiesteElaborate);

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
		if (queue.size() > maxQueueSize) {
			bar.setForeground(Color.red);
			bar.setString("Overflow!");
			battle.workerFinished(this);
		}
	}

	/**
	 * Aggiunge alla coda un blocco di parole prese a caso.
	 * 
	 * @param blockSize
	 *            quante parole aggiungere
	 */
	private void addBlock(int blockSize) {
		for (int i = 0; i < blockSize; i++) {
			int random = new Random().nextInt(Bot.STRINGHE.length);
			queue.add(Bot.STRINGHE[random]);
		}
	}

	public boolean isOverflow() {
		return overflow;
	}

	public boolean isFinished() {
		return finished;
	}

	public Bot getBot() {
		return bot;
	}
}
