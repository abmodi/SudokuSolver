package com.abmodi.sudoku.solver;

import com.abmodi.sudoku.model.SudokuPuzzle;
import com.abmodi.sudoku.view.SudokuFrame;

import javax.swing.SwingUtilities;

/**
 * Created by abmodi on 29/05/16.
 */
public class Solver implements Runnable {
    public void run() {
        new SudokuFrame(new SudokuPuzzle());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Solver());
    }
}
