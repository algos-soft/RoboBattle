import javax.swing.*;

/**
 * Created by alex on 20-06-2016.
 */
public class Tabellone extends JTable {

    private RoboBattle battle;

    public Tabellone(RoboBattle battle) {
        this.battle = battle;

        setModel(new TabelloneModel(battle));

    }




}
