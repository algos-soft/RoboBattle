package it.robobattle;

import javax.swing.table.AbstractTableModel;

/**
 * Created by alex on 20-06-2016.
 */
public class TabelloneModel extends AbstractTableModel {

    private RoboBattle battle;
    private int totColumns;
    private int botsColumn;

    public TabelloneModel(RoboBattle battle) {
        this.battle = battle;
        totColumns=Tests.values().length+1;
        botsColumn=0;
    }

    @Override
    public int getRowCount() {
        return battle.getResults().size();
    }

    @Override
    public int getColumnCount() {
        return totColumns;
    }

    @Override
    public Object getValueAt(int row, int col) {
        BotResults results = battle.getResults().get(row);

        if (col == botsColumn) {
            return results.getBot();
        }

        Tests test=Tests.values()[col - 1];
        TestSessionResult tr = results.getResult(test);

        return tr;
    }

    @Override
    public String getColumnName(int column) {
        if (column == botsColumn) {
            return "Bot";
        }

        Tests test=Tests.values()[column - 1];
        return test.getTestName();

    }

    @Override
    public Class<?> getColumnClass(int col) {

        if (col == botsColumn) {
            return Bot.class;
        }

        return TestSessionResult.class;
    }
}
