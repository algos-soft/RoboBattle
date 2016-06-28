package it.robobattle.renderers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by alex on 28-06-2016.
 * Renderer per le celle di header della JTable
 */
public class HeaderRenderer implements TableCellRenderer {

    DefaultTableCellRenderer renderer;

    public HeaderRenderer(JTable table) {
        renderer = (DefaultTableCellRenderer)
                table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setPreferredSize(new Dimension(0,35));
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
    }

}
