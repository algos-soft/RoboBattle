import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Battle extends CenteredFrame{
	
	private Bot bot1;
	private Bot bot2;
	private JProgressBar bar1;
	private JProgressBar bar2;
	private JLabel labelStatus1;
	private JLabel labelStatus2;
	private JSlider speedSlider;
	private JLabel labelSpeed;
	private boolean running;
	private boolean finished;

	private long bot1LifeMillis;
	private long bot2LifeMillis;

	
	public Battle(Bot bot1, Bot bot2) {
		super();
		this.bot1 = bot1;
		this.bot2 = bot2;
		//setPreferredSize(new Dimension(600,400));
		
		setTitle(bot1.getNome()+" vs. "+bot2.getNome());
		
		bar1=new JProgressBar(0,100);
		bar2=new JProgressBar(0,100);
		
		speedSlider = new JSlider();
		speedSlider.setMaximum(1000);
		speedSlider.setMajorTickSpacing(500);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setBorder(BorderFactory.createEmptyBorder(40,20,10,20));
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				labelSpeed.setText(speedSlider.getValue()+" richieste/sec");
			}
		});

		
		labelStatus1=new JLabel();
		labelStatus2=new JLabel();
		
		labelSpeed=new JLabel("",SwingConstants.CENTER);
		labelSpeed.setAlignmentX(CENTER_ALIGNMENT);
		labelSpeed.setFont(new Font("", Font.BOLD, 16));
		labelSpeed.setForeground(Color.RED);
		labelSpeed.setBorder(BorderFactory.createEmptyBorder(40,20,10,20));

		add(creaPanBattle());
		add(creaPanComandi(), BorderLayout.PAGE_END);
		

	}
	
	
	private Component creaPanBattle() {
		JPanel panBattle = new JPanel();
		panBattle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		BoxLayout layout=new BoxLayout(panBattle, BoxLayout.Y_AXIS);
		panBattle.setLayout(layout);

		JPanel pBots = new JPanel();
		pBots.setLayout(new GridLayout(0,2, 10, 10));
		pBots.add(new BotComponent(bot1));
		pBots.add(new BotComponent(bot2));
		pBots.add(bar1);
		pBots.add(bar2);
		pBots.add(labelStatus1);
		pBots.add(labelStatus2);

		speedSlider.setValue(0);
		
		panBattle.add(pBots);
		panBattle.add(labelSpeed);
		panBattle.add(speedSlider);
		
		return panBattle;
	}
	

	/**
	 * Crea il pannello con i comandi.
	 * @return il pannello comandi
	 */
	private Component creaPanComandi() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		final JButton bNewBattle = new JButton("Start!");
		bNewBattle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(finished|running)){
					bNewBattle.setText("Stop");
					BotWorker worker1=new BotWorker(Battle.this, bot1, bar1, labelStatus1);
					BotWorker worker2=new BotWorker(Battle.this, bot2, bar2, labelStatus2);
					worker1.execute();
					worker2.execute();
					running=true;
				}else{
					if(!finished){
						running=false;
						finished=true;
						bNewBattle.setText("Close");

					}else{
						dispose();
					}
				}

			}
		});
		panel.add(bNewBattle);
		

		return panel;
	}


	public JSlider getSpeedSlider() {
		return speedSlider;
	}


	public boolean isFinished() {
		return finished;
	}
	
	

	

}
