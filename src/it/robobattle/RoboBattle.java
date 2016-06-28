package it.robobattle;

import it.robobattle.bots.GigaBot;
import it.robobattle.bots.KiloBot;
import it.robobattle.bots.MegaBot;
import it.robobattle.bots.RoboBot;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class RoboBattle extends CenteredFrame {

	private ArrayList<Bot> bots;
	private ArrayList<BotResults> results;
	private JButton bStart;
	private final Tabellone tabellone;
	private ArenaSettings arenaSettings;

	public RoboBattle(Bot... inBots) {
		super();
		setTitle("RoboBattle");
		setPreferredSize(new Dimension(960, 600));
		
		tabellone = new Tabellone(this);
		JScrollPane scroller = new JScrollPane(tabellone);
		tabellone.setFillsViewportHeight(true);
		add(scroller);

		add(creaPanComandi(), BorderLayout.PAGE_END);
		
		bots = new ArrayList<Bot>();

		for(Bot bot : inBots){
			bots.add(bot);
		}

		// lista dei bot con relativi risultati
		results=new ArrayList<BotResults>();
		for(Bot bot : bots){
			results.add(new BotResults(bot));
		}


		// mette il nome del bot nel bottone start quando clicco sulla table
		tabellone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Bot bot = getSelectedBot();
				String text = "Start";
				if (bot != null) {
					text+=" "+bot.getNome();
				}
				bStart.setText(text);

				if(e.getClickCount()==2){
					bStart.doClick();
				}
			}
		});

		pack();
		setVisible(true);


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
					if(arenaSettings==null){
						arenaSettings=new ArenaSettings();
					}

					final Arena arena = new Arena(bot, RoboBattle.this, arenaSettings);

					// quando chiudo la finestra sovrascrive i settings
					arena.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							arenaSettings.setSliderValue(arena.getSpeedSlider().getValue());
							for(Tests test : Tests.values()){
								BotTestComponent comp=arena.getTestComponent(test);
								arenaSettings.setTestEnabled(test,comp.isTestEnabled());
							}
						}
					});
				}
			}
		});
		panel.add(bStart);

		JButton bClassifica = new JButton("Calc scores");
		bClassifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcClassifica();
			}
		});
		panel.add(bClassifica);


		JButton bEsci = new JButton("Quit");
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

			// creo una lista dei risultati di questo test per tutti i bots
			ArrayList<TestSessionResult> sessionResults = new ArrayList<TestSessionResult>();
			for(int i=0; i<results.size(); i++){
				j++;
				BotResults res = results.get(i);
				TestSessionResult sessRes=res.getResult(test);
				if(sessRes!=null){
					sessionResults.add(sessRes);
				}
			}

			// ordino la lista per tempo più veloce (i risultati con errori in fondo)
			Collections.sort(sessionResults);

			// assegno i punteggi ai risultati
			for(int i=0;i<sessionResults.size();i++){
				TestSessionResult res = sessionResults.get(i);
				int pts=0;
				if(res.getErrcount()==0){
					pts=results.size()-i;
				}
				res.setPoints(pts);
			}


		}

		// aggiorno i totali
		for(BotResults result : results){
			int score=0;
			for(Tests test : Tests.values()){
				TestSessionResult sessResult=result.getResult(test);
				if(sessResult!=null){
					score+=sessResult.getPoints();
				}
			}
			result.setScore(score);
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
		int risp = JOptionPane.showConfirmDialog(this, "Are you sure?", "Quit",
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


	public void refreshTable() {
		tabellone.refresh();
	}
}
