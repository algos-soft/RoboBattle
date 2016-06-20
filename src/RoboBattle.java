import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class RoboBattle extends CenteredFrame {

	private ArrayList<Bot> bots;
	private ArrayList<Round> rounds;
	private JPanel panRounds;

	public RoboBattle() {
		super();
		setTitle("RoboBattle");
		//setPreferredSize(new Dimension(400, 200));
		
		panRounds=creaPanRounds();
		//add(panRounds);

		Tabellone tabellone = new Tabellone(this);
		JScrollPane scroller = new JScrollPane(tabellone);
		tabellone.setFillsViewportHeight(true);
		add(scroller);

		add(creaPanComandi(), BorderLayout.PAGE_END);
		
		bots = new ArrayList<Bot>();

		bots.add(new RoboBot());
		bots.add(new GigaBot());
		bots.add(new MegaBot());
		bots.add(new KiloBot());

		start();
	}

	private void start() {
		createRounds();
//		invalidate();
//		repaint();
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
		for (Round r : rounds) {
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
		pan.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
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
				int risp = JOptionPane.showConfirmDialog(RoboBattle.this, "Sei sicuro?", "Nuova battaglia",
						JOptionPane.YES_NO_OPTION);

				if (risp == JOptionPane.YES_OPTION) {
					start();
					//SwingUtilities.updateComponentTreeUI(RoboBattle.this);
					RoboBattle.this.invalidate();
					RoboBattle.this.validate();
					RoboBattle.this.repaint();
				}
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


	public ArrayList<Bot> getBots() {
		return bots;
	}

	public void setBots(ArrayList<Bot> bots) {
		this.bots = bots;
	}

	public static void main(String[] args) {
		RoboBattle b = new RoboBattle();
		b.pack();
		b.setVisible(true);
	}
}
