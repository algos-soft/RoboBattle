package it.robobattle;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by alex on 21/06/16.
 */
public class BotCompRendererer implements TableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Bot bot = (Bot)value;
        Component comp = new BotComponent(bot);
        return comp;
    }
}
