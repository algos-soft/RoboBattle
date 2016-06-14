import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

public class Arena extends CenteredFrame{

	private Round round;
	private JProgressBar bar1;
	private JProgressBar bar2;
	private JLabel labelStatus1;
	private JLabel labelStatus2;
	private JSlider speedSlider;
	private JLabel labelSpeed;
	private JLabel labelWinner;
	private boolean running;
	private boolean finished;

	private BotWorker worker1;
	private BotWorker worker2;

	private JButton bStart;

	private Bot winner;

	public Arena(Round round) {
		super();
		this.round=round;
		//setPreferredSize(new Dimension(600,400));
		
		setTitle(getBot1().getNome()+" vs. "+getBot1().getNome());
		
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
				updateSliderText();
			}
		});

		labelStatus1=new JLabel();
		labelStatus2=new JLabel();
		
		labelSpeed=new JLabel("",SwingConstants.CENTER);
		labelSpeed.setAlignmentX(CENTER_ALIGNMENT);
		labelSpeed.setFont(new Font("", Font.BOLD, 16));
		labelSpeed.setForeground(Color.BLUE);
		labelSpeed.setBorder(BorderFactory.createEmptyBorder(40,20,10,20));

		labelWinner=new JLabel("",SwingConstants.CENTER);
		labelWinner.setAlignmentX(CENTER_ALIGNMENT);
		labelWinner.setFont(new Font("", Font.BOLD, 16));
		labelWinner.setForeground(Color.RED);
		labelWinner.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		bStart = new JButton("Start!");

		add(creaPanBattle());
		add(creaPanComandi(), BorderLayout.PAGE_END);

	}
	
	
	private Component creaPanBattle() {
		JPanel panBattle = new JPanel();
		panBattle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		BoxLayout layout=new BoxLayout(panBattle, BoxLayout.Y_AXIS);
		panBattle.setLayout(layout);

		JPanel pBots = new JPanel();
		pBots.setLayout(new GridLayout(0, 2, 10, 10));
		pBots.add(new BotComponent(getBot1()));
		pBots.add(new BotComponent(getBot2()));
		pBots.add(bar1);
		pBots.add(bar2);
		pBots.add(labelStatus1);
		pBots.add(labelStatus2);

		speedSlider.setValue(50);
		updateSliderText();
		
		panBattle.add(pBots);
		panBattle.add(labelSpeed);
		panBattle.add(speedSlider);
		panBattle.add(labelWinner);

		return panBattle;
	}

	private void updateSliderText(){
		labelSpeed.setText(speedSlider.getValue()+" richieste/sec");
	}
	

	/**
	 * Crea il pannello con i comandi.
	 * @return il pannello comandi
	 */
	private Component creaPanComandi() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		bStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(finished|running)){
					bStart.setText("Stop");
					worker1=new BotWorker(Arena.this, getBot1(), bar1, labelStatus1);
					worker2=new BotWorker(Arena.this, getBot2(), bar2, labelStatus2);
					worker1.execute();
					worker2.execute();
					running=true;
				}else{
					if(!finished){
						running=false;
						finished=true;
						bStart.setText("Close");

					}else{
						dispose();
					}
				}

			}
		});
		panel.add(bStart);
		

		return panel;
	}


	public JSlider getSpeedSlider() {
		return speedSlider;
	}


	public boolean isFinished() {
		return finished;
	}
	
	
	private Bot getBot1(){
		return round.getBot1();
	}

	private Bot getBot2(){
		return round.getBot2();
	}


	/**
	 * Invocato ogni volta che un worker finisce il suo lavoro
	 */
	public void workerFinished(BotWorker worker) {

		// se entrambi i worker sono terminati la sfida è terminata
		if(worker1.isFinished()){
			if(worker2.isFinished()){
				running=false;
				finished=true;
				bStart.setText("Close");
			}
		}

		// se è un overflow assegna il trofeo all'altro bot
		if(worker.isOverflow()){
			if(winner==null){
				if(worker.equals(worker1)){
					winner=worker2.getBot();
				}else{
					winner=worker1.getBot();
				}
				labelWinner.setText("Winner: "+winner.getNome());
			}
		}

	}

	public Bot getWinner() {
		return winner;
	}
}
