package it.robobattle.renderers;

import it.robobattle.ResultComponent;
import it.robobattle.TestSessionResult;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by alex on 21/06/16.
 * Renderer per le colonne dei risultati
 */
public class ResultCompRendererer implements TableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp=null;
        if(value instanceof TestSessionResult) {
            TestSessionResult result = (TestSessionResult) value;
            comp = new ResultComponent(result);
        }else{
            comp = new ResultComponent(null);
        }
        return comp;
    }
}
