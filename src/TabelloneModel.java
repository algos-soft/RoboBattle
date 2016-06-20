import javax.swing.table.AbstractTableModel;

/**
 * Created by alex on 20-06-2016.
 */
public class TabelloneModel extends AbstractTableModel {

    private RoboBattle battle;

    public TabelloneModel(RoboBattle battle) {
        this.battle = battle;
    }

    @Override
    public int getRowCount() {
        return battle.getBots().size();
    }

    @Override
    public int getColumnCount() {
        return Tests.values().length+1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if(col==0){
            Bot bot = battle.getBots().get(row);
            String s = bot.getNome();
            return s;
        }else{
            return "ciao";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column==0){
            return "Bot";
        }else{
            return Tests.values()[column-1].getTestName();
        }
    }
}
