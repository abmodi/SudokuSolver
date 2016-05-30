package com.abmodi.sudoku.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abmodi on 30/05/16.
 */
public class SudokuCell {
    private boolean isInitial;

    private int maxValue;
    private int value;
    private Point cellLocation;
    private Rectangle bounds;

    private List<Integer> possibleValues;

    public SudokuCell(int puzzleWidth) {
        this.maxValue = puzzleWidth;
        this.possibleValues = new ArrayList<Integer>(maxValue);
        init(maxValue);
    }

    public void init(int maxValue) {
        this.value = 0;
        this.possibleValues.clear();
        this.isInitial = false;
        add(maxValue);
    }

    private void add(int maxValue) {
        for(int i=1;i<=maxValue;++i) {
            this.possibleValues.add(i);
        }
    }

    public List<Integer> getPossibleValues() {
        return this.possibleValues;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean getIsInitial() {
        return isInitial;
    }

    public Point getCellLocation() {
        return this.cellLocation;
    }

    public void setCellLocation(Point cellLocation) {
        this.cellLocation = cellLocation;
    }

    public boolean isPossibleValue(int value) {
        return possibleValues.contains(value);
    }

    public void setIsInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }

    public void clearPossibleValues() {
        this.possibleValues.clear();
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean contains(Point point) {
        return bounds.contains(point);
    }

    public void removePossibleValue(int value) {
        this.possibleValues.remove((Integer) value);
    }
}
