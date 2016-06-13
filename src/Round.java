import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


/**
 * Una singola sfida tra due bot
 */
public class Round extends JPanel{
	private Bot bot1;
	private Bot bot2;
	
	public Round(Bot bot1, Bot bot2) {
		super();
		
		this.bot1 = bot1;
		this.bot2 = bot2;
		
		JButton bStart = new JButton("Go!");
		bStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Battle b = new Battle(Round.this.bot1, Round.this.bot2);
				b.pack();
				b.setVisible(true);
			}
		});
		
		add(new BotComponent(bot1));
		add(new JLabel("vs."));
		add(new BotComponent(bot2));
		
		add(bStart);


	}
	
	public String toString(){
		return bot1.getNome()+" vs. "+bot2.getNome();
	}
	
}
