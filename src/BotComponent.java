import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class BotComponent extends JPanel {
	private Bot bot;

	public BotComponent(Bot bot) {
		super();
		this.bot = bot;

		setPreferredSize(new Dimension(120,30));
		
		JLabel label = new JLabel(bot.getNome());
		add(label);
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
	}
	
}
