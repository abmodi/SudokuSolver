package com.abmodi.sudoku.view;

import com.abmodi.sudoku.model.SudokuCell;


import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abmodi on 30/05/16.
 */
public class SudokuCellView {
    Rectangle bounds;
    SudokuCell cell;

    public SudokuCellView(SudokuCell cell) {
        this.cell = cell;
    }

    private List<String> concatenatePossibleValues() {
        List<String> list = new ArrayList<String>();
        List<Integer> possibleValues = cell.getPossibleValues();
        StringBuilder builder = new StringBuilder();

        int stringIndex = 0;

        for (int index = 0; index < possibleValues.size(); index++) {
            builder.append(possibleValues.get(index));
            if (stringIndex < 2) {
                builder.append(' ');
                stringIndex++;
            } else {
                list.add(builder.toString());
                builder.delete(0, builder.length());
                stringIndex = 0;
            }
        }
        if (builder.length() > 0) {
            list.add(builder.toString());
        }

        return list;
    }

    public void draw(Graphics g, int x, int y, int width, int cellPosition) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, width);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, width);
        drawBorder(g, x, y, width, cellPosition);
        Font font = g.getFont();
        FontRenderContext frc = new FontRenderContext(null, true, true);
        int value = cell.getValue();
        if (value > 0) {
            String s = Integer.toString(value);

            BufferedImage image = createImage(font, frc, width, s);

            int xx = x + (width - image.getWidth()) / 2;
            int yy = y + (width - image.getHeight()) / 2;
            g.drawImage(image, xx, yy, null);

        } else {
            List<String> list = concatenatePossibleValues();

            double imageWidth = 0.0D;
            double imageHeight = 0.0D;
            double stringHeight = 0.0D;
            for (String s : list) {
                Rectangle2D r = font.getStringBounds(s, frc);
                imageWidth = Math.max(imageWidth, r.getWidth());
                imageHeight += r.getHeight();
                stringHeight = Math.max(stringHeight, r.getHeight());
            }

            BufferedImage image = createImage(list, imageWidth, imageHeight,
                    stringHeight);

            int xx = x + (width - image.getWidth()) / 2;
            int yy = y + (width - image.getHeight()) / 2;
            g.drawImage(image, xx, yy, null);
        }
    }

    private void drawBorder(Graphics g, int x, int y, int width,
                            int cellPosition) {
        switch (cellPosition) {
            case 1:
                drawLeftBorder(g, x, y, width);
                drawTopBorder(g, x, y, width);
                break;
            case 2:
                drawTopBorder(g, x, y, width);
                break;
            case 3:
                drawTopBorder(g, x, y, width);
                drawRightBorder(g, x, y, width);
                break;
            case 4:
                drawLeftBorder(g, x, y, width);
                break;
            case 6:
                drawRightBorder(g, x, y, width);
                break;
            case 7:
                drawLeftBorder(g, x, y, width);
                drawBottomBorder(g, x, y, width);
                break;
            case 8:
                drawBottomBorder(g, x, y, width);
                break;
            case 9:
                drawBottomBorder(g, x, y, width);
                drawRightBorder(g, x, y, width);
                break;
        }
    }

    private void drawTopBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x, y + 1, x + width, y + 1);
        g.drawLine(x, y + 2, x + width, y + 2);
    }

    private void drawRightBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x + width - 1, y, x + width - 1, y + width);
        g.drawLine(x + width - 2, y, x + width - 2, y + width);
    }

    private void drawBottomBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x, y + width - 1, x + width, y + width - 1);
        g.drawLine(x, y + width - 2, x + width, y + width - 2);
    }

    private void drawLeftBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x + 1, y, x + 1, y + width);
        g.drawLine(x + 2, y, x + 2, y + width);
    }

    private BufferedImage createImage(List<String> list, double imageWidth,
                                      double imageHeight, double stringHeight) {
        int margin = 6;
        double extra = (double) margin + margin;
        BufferedImage image = new BufferedImage((int) Math.round(imageWidth
                + extra), (int) Math.round(imageHeight + extra),
                BufferedImage.TYPE_INT_RGB);
        Graphics gg = image.getGraphics();
        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, image.getWidth(), image.getHeight());

        gg.setColor(Color.DARK_GRAY);
        int x = margin;
        int y = margin / 2 + (int) Math.round(stringHeight);
        for (String s : list) {
            gg.drawString(s, x, y);
            y += (int) Math.round(stringHeight);
        }
        gg.dispose();
        return image;
    }

    private BufferedImage createImage(Font font, FontRenderContext frc,
                                      int width, String s) {
        int margin = 6;
        double extra = (double) margin + margin;

        Font largeFont = font.deriveFont((float) (width * 2 / 3));
        Rectangle2D r = largeFont.getStringBounds(s, frc);

        BufferedImage image = new BufferedImage((int) Math.round(r.getWidth()
                + extra), (int) Math.round(extra - r.getY()),
                BufferedImage.TYPE_INT_RGB);
        Graphics gg = image.getGraphics();
        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, image.getWidth(), image.getHeight());

        if (cell.getIsInitial()) {
            gg.setColor(Color.BLUE);
        } else {
            gg.setColor(Color.CYAN);
        }

        int x = margin;
        int y = -(int) Math.round(r.getY());
        gg.setFont(largeFont);
        gg.drawString(s, x, y);
        gg.dispose();
        return image;
    }

    public void setBounds(Rectangle r) {
        this.cell.setBounds(r);
    }
}
