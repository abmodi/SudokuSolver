package com.abmodi.sudoku.view;

import com.abmodi.sudoku.model.SudokuPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by abmodi on 29/05/16.
 */
public class SudokuFrame {
    private ButtonPanel buttonPanel;
    private JFrame frame;
    private SudokuPanel sudokuPanel;
    private SudokuPuzzle model;

    public SudokuFrame(SudokuPuzzle model){
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        sudokuPanel = new SudokuPanel(this, model);
        buttonPanel = new ButtonPanel(this, model);

        frame = new JFrame();
        frame.setTitle("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
                System.exit(0);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        // Add child panels
        mainPanel.add(sudokuPanel);
        mainPanel.add(buttonPanel.getPanel());

        frame.setLayout(new FlowLayout());
        frame.add(mainPanel);
        frame.pack();
        frame.setBounds(getBounds());
        frame.setVisible(true);
    }

    protected Rectangle getBounds() {
        Rectangle f = frame.getBounds();
        Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
        f.x = (w.width - f.width) / 2;
        f.y = (w.height - f.height) / 2;
        return f;
    }

    public void repaintSudokuPanel() {
        sudokuPanel.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}
