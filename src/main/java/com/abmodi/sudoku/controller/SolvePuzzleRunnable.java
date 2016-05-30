package com.abmodi.sudoku.controller;

import com.abmodi.sudoku.model.SudokuCell;
import com.abmodi.sudoku.model.SudokuPuzzle;
import com.abmodi.sudoku.view.SudokuFrame;

import java.awt.*;

/**
 * Created by abmodi on 30/05/16.
 */
public class SolvePuzzleRunnable implements Runnable {
    private SudokuFrame frame;
    private SudokuPuzzle model;

    public SolvePuzzleRunnable(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
    }

    public void run() {
        if(model.isSolved()) {
            return;
        }
        long startTime = System.currentTimeMillis();
        if(solve()) {
            this.frame.repaintSudokuPanel();
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime));
            return;
        }
    }

    private boolean solve() {
        int puzzleWidth = this.model.getPuzzleWidth();
        SudokuCell cell = this.model.findUnassignedLocation();
        if (cell == null) {
            return true;
        }
        for (int i = 1; i <= puzzleWidth; ++i) {
            if (this.model.isSafe(cell, i)) {
                cell.setValue(i);
                if (solve()) {
                    return true;
                }
                cell.setValue(0);
            }
        }
        return false;
    }
}
