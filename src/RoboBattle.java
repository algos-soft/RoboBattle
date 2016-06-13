import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RoboBattle extends CenteredFrame {

	private ArrayList<Bot> bots;
	private ArrayList<Round> rounds;
	private JPanel panRounds;

	public RoboBattle() {
		super();
		setTitle("RoboBattle");
		//setPreferredSize(new Dimension(400, 200));
		
		panRounds=creaPanRounds();
		add(panRounds);
		add(creaPanComandi(), BorderLayout.PAGE_END);
		
		bots = new ArrayList<Bot>();

		bots.add(new RoboBot());
		bots.add(new SuperBot());
		bots.add(new MegaBot());
		bots.add(new GigaBot());

		start();
	}

	private void start() {
		createRounds();
		
	}

	/**
	 * Crea l'elenco dei rounds con tutte le permutazioni dei bots
	 */
	private void createRounds() {
		rounds = new ArrayList<Round>();
		Bot[] aBots = bots.toArray(new Bot[0]);
		
		for (int i = 0; i <aBots.length-1 ; i++) {
			Bot bot1 = aBots[i];
			for (int j = i+1; j <aBots.length ; j++) {
				Bot bot2 = aBots[j];
				rounds.add(new Round(bot1, bot2));
			}
		
		}
		
		Collections.shuffle(rounds);

		// mette i rounds graficamente nel pannello
		panRounds.removeAll();
		for(Round r : rounds){
			panRounds.add(r);
		}

	}
	
	
	/**
	 * Crea il pannello dei round.
	 * @return il pannello dei round
	 */
	private JPanel creaPanRounds() {
		JPanel pan = new JPanel();
		BoxLayout layout = new BoxLayout(pan, BoxLayout.Y_AXIS);
		pan.setLayout(layout);
		return pan;
	}

	/**
	 * Crea il pannello con i comandi.
	 * @return il pannello comandi
	 */
	private Component creaPanComandi() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JButton bNewBattle = new JButton("Nuova battaglia");
		bNewBattle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(bNewBattle);
		
		JButton bEsci = new JButton("Esci");
		bEsci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				esci();
			}
		});
		panel.add(bEsci);

		return panel;
	}
	
	
	private void esci() {
		int risp = JOptionPane.showConfirmDialog(this, "Sei sicuro?", "Uscita",
				JOptionPane.YES_NO_OPTION);

		if (risp == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		RoboBattle b = new RoboBattle();
		b.pack();
		b.setVisible(true);
	}
}
