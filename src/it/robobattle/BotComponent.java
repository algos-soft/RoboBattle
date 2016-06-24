package it.robobattle;

import javax.swing.*;
import java.awt.*;

public class BotComponent extends JPanel {
	private Bot bot;
	private JLabel labelWinner;

	public BotComponent(Bot bot) {
		super();
		this.bot = bot;

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 60));


		labelWinner=new JLabel("",SwingConstants.CENTER);
		labelWinner.setPreferredSize(new Dimension(40, 14));
		labelWinner.setBackground(Color.WHITE);
		labelWinner.setOpaque(true);
		labelWinner.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(labelWinner, BorderLayout.LINE_END);
		setWinner(false);

		JLabel labelBotName = new JLabel(bot.getNome());
		labelBotName.setFont(labelBotName.getFont().deriveFont(16.0f));
		add(labelBotName);

		JLabel labelSlogan = new JLabel(bot.getSlogan());
		labelSlogan.setForeground(Color.WHITE);
		labelSlogan.setBackground(Color.BLACK);
		labelSlogan.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		labelSlogan.setOpaque(true);
		labelSlogan.setFont(labelSlogan.getFont().deriveFont(12.0f));
		add(labelSlogan, BorderLayout.PAGE_END);

		setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		setBackground(bot.getColore());
		
	}


	public void setWinner(boolean winner){
		if(winner){
			labelWinner.setOpaque(true);
			labelWinner.setBackground(Color.YELLOW);
			labelWinner.setText("W");
		}else{
			labelWinner.setOpaque(false);
		}
	}


}
