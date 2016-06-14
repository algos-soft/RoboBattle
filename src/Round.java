import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Una singola sfida tra due bot
 */
public class Round extends JPanel{
	private Bot bot1;
	private Bot bot2;

	BotComponent comp1;
	BotComponent comp2;
	
	public Round(Bot bot1, Bot bot2) {
		super();
		
		this.bot1 = bot1;
		this.bot2 = bot2;
		
		JButton bStart = new JButton("Go!");
		bStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final Arena b = new Arena(Round.this);
				b.pack();
				b.setVisible(true);

				// quando la finestra si chiude aggiorno lo stato
				b.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						setWinner(b.getWinner());
					}
				});
			}
		});

		comp1=new BotComponent(bot1);
		comp2=new BotComponent(bot2);
		add(comp1);
		add(new JLabel("vs."));
		add(comp2);
		
		add(bStart);


	}

	/**
	 * regola il vincitore
	 */
	private void setWinner(Bot bot){
		if(bot!=null){
			if(bot.equals(bot1)){
				comp1.setWinner(true);
			}
			if(bot.equals(bot2)){
				comp2.setWinner(true);
			}
		}
	}
	
	public String toString(){
		return bot1.getNome()+" vs. "+bot2.getNome();
	}

	public Bot getBot1() {
		return bot1;
	}

	public Bot getBot2() {
		return bot2;
	}


}
