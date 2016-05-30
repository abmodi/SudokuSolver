package com.abmodi.sudoku.view;

import com.abmodi.sudoku.controller.SetValueListener;
import com.abmodi.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by abmodi on 29/05/16.
 */
public class SudokuPanel extends JPanel {
    private SudokuFrame frame;
    private SudokuPuzzle model;
    private SudokuPuzzleView puzzleView;

    public SudokuPanel(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
        this.puzzleView = new SudokuPuzzleView(this.model);
        createPartControl();
    }

    private void createPartControl() {
        new JPanel();
        int width = model.getDrawWidth() * model.getPuzzleWidth() + 1;
        addMouseListener(new SetValueListener(frame, model));
        setPreferredSize(new Dimension(width, width));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        puzzleView.draw(g);
    }
}
