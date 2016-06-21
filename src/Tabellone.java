import javax.swing.*;
import java.awt.*;

/**
 * Created by alex on 20-06-2016.
 */
public class Tabellone extends JTable {

    private RoboBattle battle;

    public Tabellone(RoboBattle battle) {
        this.battle = battle;

        setModel(new TabelloneModel(battle));

        setDefaultRenderer(Bot.class, new BotCompRendererer());
        setDefaultRenderer(JButton.class, new ButtonCompRendererer());

        setRowHeight(100);

        setIntercellSpacing(new Dimension(20,20));

        setRowSelectionAllowed(false);

        getColumnModel().getColumn(0).setPreferredWidth(300);

//        int numCols=getColumnModel().getColumnCount();
//        for(int i=0; i<numCols-1; i++){
//            getColumnModel().getColumn(i+1).setPreferredWidth(200);
//
//        }

    }




}
