package com.abmodi.sudoku.controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by abmodi on 30/05/16.
 */
public class ToggleListener implements ChangeListener {

    private JToggleButton[] toggleButtons;

    public void setToggleButtons(JToggleButton... buttons) {
        toggleButtons = new JToggleButton[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            toggleButtons[i] = buttons[i];
        }
    }

    public void stateChanged(ChangeEvent event) {
        JToggleButton toggleButton = (JToggleButton) (event.getSource());
        if(toggleButton.isSelected()) {
            for(JToggleButton button : toggleButtons) {
                if(!button.equals(toggleButton)) {
                    button.setSelected(false);
                }
            }
        }
    }
}
