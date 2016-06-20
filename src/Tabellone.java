import javax.swing.*;

/**
 * Created by alex on 20-06-2016.
 */
public class Tabellone extends JTable {

    private RoboBattle battle;

    public Tabellone(RoboBattle battle) {
        this.battle = battle;

        setModel(new TabelloneModel(battle));

        setDefaultRenderer(Bot.class, new BotCompRendererer());

        setRowHeight(100);

        getColumnModel().getColumn(0).setPreferredWidth(300);
        int numCols=getColumnModel().getColumnCount();
//        for(int i=0; i<numCols-1; i++){
//            getColumnModel().getColumn(i+1).setPreferredWidth(200);
//
//        }

    }




}
