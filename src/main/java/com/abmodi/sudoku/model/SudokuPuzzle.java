package com.abmodi.sudoku.model;

import java.awt.*;

/**
 * Created by abmodi on 29/05/16.
 */
public class SudokuPuzzle {
    private boolean isSetValues;

    private int drawWidth;
    private int puzzleWidth;

    private SudokuCell[][] cells;

    public SudokuPuzzle() {
        this.drawWidth = 80;
        this.puzzleWidth = 9;
        this.cells = new SudokuCell[puzzleWidth][puzzleWidth];
        set(puzzleWidth);
    }

    private void set(int puzzleWidth) {
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                this.cells[i][j] = new SudokuCell(puzzleWidth);
                this.cells[i][j].setCellLocation(new Point(i,j));
            }
        }
    }

    public void init() {
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                this.cells[i][j].init(puzzleWidth);
            }
        }
    }

    public boolean isSetValues() {
        return isSetValues;
    }

    public void setSetValues(boolean isSetValues) {
        this.isSetValues = isSetValues;
    }

    public int getDrawWidth() {
        return drawWidth;
    }

    public int getPuzzleWidth() {
        return puzzleWidth;
    }

    public SudokuCell getCell(int i, int j) {
        return cells[i][j];
    }

    public SudokuCell getSudokuCellFromLocation(Point p){
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                if(cells[i][j].contains(p)){
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    public void removePossibleValue(SudokuCell cell) {
        int value = cell.getValue();
        Point point = cell.getCellLocation();

        for (int i = 0; i < puzzleWidth; i++) {
            cells[i][point.y].removePossibleValue(value);
        }
        for (int j = 0; j < puzzleWidth; j++) {
            cells[point.x][j].removePossibleValue(value);
        }

        int ii = point.x / 3;
        int jj = point.y / 3;
        for (int i = ii * 3; i < (ii + 1) * 3; i++) {
            for (int j = jj * 3; j < (jj + 1) * 3; j++) {
                cells[i][j].removePossibleValue(value);
            }
        }
    }

    public boolean isSolved() {
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                if(cells[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuCell findUnassignedLocation() {
        for(int i=0;i<puzzleWidth;++i) {
            for(int j=0;j<puzzleWidth;++j) {
                if(this.getCell(i,j).getValue() == 0){
                    return this.getCell(i,j);
                }
            }
        }
        return null;
    }

    public boolean isSafe(SudokuCell cell, int value) {
        if(!cell.getPossibleValues().contains((Integer)(value))) {
            return false;
        }
        Point point = cell.getCellLocation();
        for (int i = 0; i < puzzleWidth; i++) {
            if(cells[i][point.y].getValue() == value){
                return false;
            }
        }
        for (int j = 0; j < puzzleWidth; j++) {
            if(cells[point.x][j].getValue() == value) {
                return false;
            }
        }

        int ii = point.x / 3;
        int jj = point.y / 3;
        for (int i = ii * 3; i < (ii + 1) * 3; i++) {
            for (int j = jj * 3; j < (jj + 1) * 3; j++) {
                if(cells[i][j].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
