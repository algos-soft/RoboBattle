package it.robobattle.renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by alex on 28-06-2016.
 * Renderer per la colonna dei punti totali
 */
public class ScoreCompRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label=new JLabel("",SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 28));
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        if(value!=null && value instanceof Integer){
            Integer i = (Integer)value;
            label.setText(""+i);
        }
        return label;
    }
}
