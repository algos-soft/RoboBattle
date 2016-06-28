package it.robobattle;

import javax.swing.table.AbstractTableModel;

/**
 * Created by alex on 20-06-2016.
 */
public class TabelloneModel extends AbstractTableModel {

    private RoboBattle battle;
    private int totColumns;
    private int botsColumn;
    private int pointsColumn;

    public TabelloneModel(RoboBattle battle) {
        this.battle = battle;
        totColumns=Tests.values().length+2;
        botsColumn=0;
        pointsColumn=totColumns-1;
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

        if (col == pointsColumn) {
            return results.getScore();
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

        if (column == pointsColumn) {
            return "Score";
        }


        Tests test=Tests.values()[column - 1];
        return test.getTestName();

    }

    @Override
    public Class<?> getColumnClass(int col) {

        if (col == botsColumn) {
            return Bot.class;
        }

        if (col == pointsColumn) {
            return Integer.class;
        }

        return TestSessionResult.class;
    }
}
