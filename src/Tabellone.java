import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by alex on 20-06-2016.
 */
public class Tabellone extends JTable {

    private RoboBattle battle;

    public Tabellone(final RoboBattle battle) {
        this.battle = battle;

        setModel(new TabelloneModel(battle));

        setDefaultRenderer(Bot.class, new BotCompRendererer());
        setDefaultRenderer(JButton.class, new ButtonCompRendererer());

        setRowHeight(100);

        setIntercellSpacing(new Dimension(20,20));

        //setRowSelectionAllowed(false);

        getColumnModel().getColumn(0).setPreferredWidth(300);

//        int numCols=getColumnModel().getColumnCount();
//        for(int i=0; i<numCols-1; i++){
//            getColumnModel().getColumn(i+1).setPreferredWidth(200);
//
//        }


        getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                boolean enable=getSelectedRows().length==1;
                battle.getbStart().setEnabled(enable);
            }
        });

    }




}
