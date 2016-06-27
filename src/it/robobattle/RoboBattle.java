package it.robobattle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;

public class RoboBattle extends CenteredFrame {

	private ArrayList<Bot> bots;
	private ArrayList<BotResults> results;
	private JButton bStart;
	private final Tabellone tabellone;

	public RoboBattle() {
		super();
		setTitle("RoboBattle");
		setPreferredSize(new Dimension(900, 560));
		
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

		// lista dei bot con relativi risultati
		results=new ArrayList<BotResults>();
		for(Bot bot : bots){
			results.add(new BotResults(bot));
		}


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
					new Arena(bot, RoboBattle.this);
				}
			}
		});
		panel.add(bStart);

		JButton bClassifica = new JButton("Classifica");
		bClassifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcClassifica();
			}
		});
		panel.add(bClassifica);


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

	/**
	 * Calcola la classifica dei bot e la scrive nei dati
	 */
	private void calcClassifica() {
		int j=0;
		for(Tests test : Tests.values()){
			for(int i=0; i<results.size(); i++){
				j++;
				BotResults res = results.get(i);
				TestSessionResult sessRes=res.getResult(test);
				if(sessRes!=null){
					sessRes.setPoints(j);
				}
			}
		}

		refreshTable();

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

	public ArrayList<BotResults> getResults() {
		return results;
	}


	public BotResults getResult(Bot bot){
		BotResults result=null;
		for(BotResults r : results){
			if(r.getBot().equals(bot)){
				result=r;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		RoboBattle b = new RoboBattle();
		b.pack();
		b.setVisible(true);
	}

	public void refreshTable() {
		tabellone.refresh();
	}
}
