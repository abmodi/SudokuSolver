package com.abmodi.sudoku.view;

import com.abmodi.sudoku.model.SudokuPuzzle;

import java.awt.*;

/**
 * Created by abmodi on 30/05/16.
 */
public class SudokuPuzzleView {
    SudokuPuzzle model;
    SudokuCellView[][] cellViews;

    private int[][]         cellPosition    = { { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
            { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 },
            { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
            { 3, 6, 9, 3, 6, 9, 3, 6, 9 }, { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
            { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 } };

    public SudokuPuzzleView(SudokuPuzzle model) {
        this.model = model;
        int puzzleWidth = this.model.getPuzzleWidth();
        this.cellViews = new SudokuCellView[puzzleWidth][puzzleWidth];
        init(puzzleWidth);
    }

    private void init(int puzzleWidth){
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                this.cellViews[i][j] = new SudokuCellView(this.model.getCell(i,j));
            }
        }
    }

    public void draw(Graphics g) {
        int x = 0;
        int puzzleWidth = this.model.getPuzzleWidth();
        int drawWidth = this.model.getDrawWidth();
        for (int i = 0; i < puzzleWidth; i++) {
            int y = 0;
            for (int j = 0; j < puzzleWidth; j++) {
                Rectangle r = new Rectangle(x, y, drawWidth, drawWidth);
                cellViews[i][j].setBounds(r);
                cellViews[i][j].draw(g, x, y, drawWidth, cellPosition[i][j]);
                y += drawWidth;
            }
            x += drawWidth;
        }
    }
}
