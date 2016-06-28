package it.robobattle;

import it.robobattle.renderers.BotCompRendererer;
import it.robobattle.renderers.HeaderRenderer;
import it.robobattle.renderers.ScoreCompRenderer;
import it.robobattle.renderers.ResultCompRendererer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 20-06-2016.
 */
public class Tabellone extends JTable {

    private RoboBattle battle;
    private final TabelloneModel model;


    public Tabellone(final RoboBattle battle) {
        this.battle = battle;

        model = new TabelloneModel(battle);
        setModel(model);

        setDefaultRenderer(Bot.class, new BotCompRendererer());
        setDefaultRenderer(TestSessionResult.class, new ResultCompRendererer());
        setDefaultRenderer(Integer.class, new ScoreCompRenderer());


        setRowHeight(120);

        setIntercellSpacing(new Dimension(20, 20));

        getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        getTableHeader().setDefaultRenderer(new HeaderRenderer(this));

        getColumnModel().getColumn(0).setPreferredWidth(160);


        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                boolean enable = getSelectedRows().length == 1;
                battle.getbStart().setEnabled(enable);
            }
        });


    }


    public void refresh() {
        model.fireTableDataChanged();
    }
}
