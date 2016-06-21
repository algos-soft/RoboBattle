import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * Created by alex on 20-06-2016.
 */
public class TabelloneModel extends AbstractTableModel {

    private RoboBattle battle;
    private int totColumns;
    private int botsColumn;
    private  int buttonsColumn;

    public TabelloneModel(RoboBattle battle) {
        this.battle = battle;
        totColumns=Tests.values().length+2;
        botsColumn=0;
        buttonsColumn=totColumns-1;
    }

    @Override
    public int getRowCount() {
        return battle.getBots().size();
    }

    @Override
    public int getColumnCount() {
        return Tests.values().length + 2;
    }

    @Override
    public Object getValueAt(int row, int col) {

        if (col == botsColumn) {
            return battle.getBots().get(row);
        }

        if (col == buttonsColumn) {
            return new JButton("Start!");
        }

        return "ciao";
    }

    @Override
    public String getColumnName(int column) {
        if (column == botsColumn) {
            return "Bot";
        }

        if (column == buttonsColumn) {
            return "";
        }

        Tests test=Tests.values()[column - 1];
        return test.getTestName();

    }

    @Override
    public Class<?> getColumnClass(int col) {

        if (col == botsColumn) {
            return Bot.class;
        }

        if (col == buttonsColumn) {
            return JButton.class;
        }

        return String.class;
    }
}
