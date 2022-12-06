package edu.mephi.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import edu.mephi.classes.Food;
import edu.mephi.classes.Human;

public class ComboBoxListRendererCustom extends DefaultListCellRenderer  {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof Human) {
            value = ((Human)value).getName();
        } else if (value instanceof Food) {
            value = ((Food)value).get_name();
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
