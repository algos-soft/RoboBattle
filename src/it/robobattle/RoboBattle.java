package it.robobattle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class RoboBattle extends CenteredFrame {

	private ArrayList<Bot> bots;
	private JButton bStart;
	private final Tabellone tabellone;

	public RoboBattle() {
		super();
		setTitle("RoboBattle");
		setPreferredSize(new Dimension(700, 560));
		
		tabellone = new Tabellone(this);
		JScrollPane scroller = new JScrollPane(tabellone);
		tabellone.setFillsViewportHeight(true);
		add(scroller);

		add(creaPanComandi(), BorderLayout.PAGE_END);
		
		bots = new ArrayList<Bot>();

		bots.add(new RoboBot());
		bots.add(new GigaBot());
		bots.add(new MegaBot());
		bots.add(new KiloBot());

	}



	/**
	 * Crea il pannello con i comandi.
	 * @return il pannello comandi
	 */
	private Component creaPanComandi() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		bStart = new JButton("Start");
		bStart.setEnabled(false);
		bStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Bot bot = getSelectedBot();
				if(bot != null){
					new Arena(bot);
				}
			}
		});
		panel.add(bStart);

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


	private Bot getSelectedBot() {
		Bot bot=null;
		int row = tabellone.getSelectedRow();
		if (row >= 0) {
			bot = getBots().get(row);
		}
		return bot;
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

	public JButton getbStart() {
		return bStart;
	}

	public static void main(String[] args) {
		RoboBattle b = new RoboBattle();
		b.pack();
		b.setVisible(true);
	}
}
