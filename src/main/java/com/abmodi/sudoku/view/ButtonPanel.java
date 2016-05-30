package com.abmodi.sudoku.view;

import com.abmodi.sudoku.controller.SolvePuzzleRunnable;
import com.abmodi.sudoku.controller.ToggleListener;
import com.abmodi.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by abmodi on 29/05/16.
 */
public class ButtonPanel {
    protected static final Insets buttonInsets    = new Insets(10, 10, 0, 10);
    private JToggleButton resetPuzzleButton;
    private JToggleButton setValuesButton;
    private JToggleButton solvePuzzleButton;

    private JPanel holderPanel;
    private JPanel panel;

    private SudokuFrame frame;
    private SudokuPuzzle model;

    boolean isSolvePuzzleButtonFirstPress;

    public ButtonPanel(SudokuFrame frame, SudokuPuzzle model) {
        this.frame = frame;
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        holderPanel = new JPanel();
        holderPanel.setLayout(new FlowLayout());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridY = 0;

        ToggleListener tListener = new ToggleListener();

        resetPuzzleButton = new JToggleButton("Reset Puzzle");
        resetPuzzleButton.addChangeListener(tListener);
        resetPuzzleButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(resetPuzzleButton.isSelected()) {
                    model.init();
                    frame.repaintSudokuPanel();
                    resetPuzzleButton.setSelected(false);
                    setValuesButton.setSelected(true);
                    isSolvePuzzleButtonFirstPress = true;
                }
            }
        });
        addComponent(panel, resetPuzzleButton, 0, gridY++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        setValuesButton = new JToggleButton("Set Initial Values");
        setValuesButton.addChangeListener(tListener);
        setValuesButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                model.setSetValues(setValuesButton.isSelected());
            }
        });
        addComponent(panel, setValuesButton, 0, gridY++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        solvePuzzleButton = new JToggleButton("Solve It");
        solvePuzzleButton.addChangeListener(tListener);
        solvePuzzleButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                if (isSolvePuzzleButtonFirstPress && solvePuzzleButton.isSelected()) {
                    isSolvePuzzleButtonFirstPress = false;
                    SolvePuzzleRunnable runnable = new SolvePuzzleRunnable(
                            frame, model);
                    new Thread(runnable).start();
                }
            }
        });
        addComponent(panel, solvePuzzleButton, 0, gridY++, 1, 1, buttonInsets,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        tListener.setToggleButtons(resetPuzzleButton, setValuesButton, solvePuzzleButton);
        setValuesButton.setSelected(true);
        isSolvePuzzleButtonFirstPress = true;

        holderPanel.add(panel);
    }

    private void addComponent(Container container, Component component,
                              int gridX, int gridY, int gridWidth, int gridHeight, Insets insets,
                              int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY,
                gridWidth, gridHeight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    public JPanel getPanel() {
        return holderPanel;
    }
}
