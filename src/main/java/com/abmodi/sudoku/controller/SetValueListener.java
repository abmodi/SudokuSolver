package com.abmodi.sudoku.controller;

import com.abmodi.sudoku.model.SudokuCell;
import com.abmodi.sudoku.model.SudokuPuzzle;
import com.abmodi.sudoku.view.SudokuFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by abmodi on 30/05/16.
 */
public class SetValueListener implements MouseListener{
    private SudokuFrame frame;
    private SudokuPuzzle model;

    public SetValueListener(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
    }

    public void mouseClicked(MouseEvent event) {

    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {
        if (model.isSetValues()) {
            SudokuCell sudokuCell = model.getSudokuCellFromLocation(event
                    .getPoint());
            if (sudokuCell != null) {
                int value = getValue(sudokuCell);
                if (value > 0) {
                    sudokuCell.setValue(value);
                    sudokuCell.setIsInitial(true);
                    model.removePossibleValue(sudokuCell);
                    sudokuCell.clearPossibleValues();
                    frame.repaintSudokuPanel();
                }
            }
        }
    }

    private int getValue(SudokuCell sudokuCell) {
        int value = 0;
        while (value == 0) {
            String inputValue = JOptionPane.showInputDialog(frame.getFrame(),
                    "Type a value from 1 to 9");

            if (inputValue == null) { // Cancel button
                return 0;
            }

            try {
                value = Integer.parseInt(inputValue);
                value = testValue(sudokuCell, value);
            } catch (NumberFormatException e) {
                value = 0;
            }
        }
        return value;
    }

    private int testValue(SudokuCell sudokuCell, int value) {
        if (value < 1 || value > 9) {
            value = 0;
        } else if (!sudokuCell.isPossibleValue(value)) {
            value = 0;
        }
        return value;
    }


}
