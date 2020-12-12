package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TrafficLight extends JFrame {
    private static final long serialVersionUID = 1L;
    private Graphics2D canvas;

    public TrafficLight(Graphics g) {
        this.canvas = (Graphics2D) g;
    }

    public void drawRectangle(Dimension dimension) {
        int widthPanel = (int) dimension.getWidth();
        int heightPanel = (int) dimension.getHeight();

        /*
         * int xPoint = (int) (widthPanel / 3); int yPoint = (int) (heightPanel * .33);
         */
        int xPoint = 78;
        int yPoint = 87;

        /*
         * int width = (int) (widthPanel * .4); int height = (int) (heightPanel * .7);
         */
        int width = 100;
        int height = 200;

        this.canvas.setColor(Color.BLACK);
        canvas.drawRect((int) (xPoint - 5), (int) (yPoint - 5), width + 10, height + 10);

        canvas.setColor(Color.BLACK);
        canvas.drawRect(xPoint, yPoint, width, height);
        canvas.fillRect(xPoint, yPoint, width, height);

        drawCircle(3);
    }

    public void drawCircle(int state)
    {
        switch(state)
        {
            // State 1 = Green
            case 1:
                canvas.setColor(Color.GREEN);
                canvas.fillOval(103, 100, 51, 51);
                canvas.setColor(Color.BLACK);
                canvas.fillOval(103, 224, 51, 51);
                break;
            // State 2 = Yellow
            case 2:
                canvas.setColor(Color.YELLOW);
                canvas.fillOval(103, 162, 51, 51);
                canvas.setColor(Color.BLACK);
                canvas.fillOval(103, 100, 51, 51);
                canvas.fillOval(103, 224, 51, 51);
                break;
            // State 3 = Red
            case 3:
                canvas.setColor(Color.RED);
                canvas.fillOval(103, 224, 51, 51);
                canvas.setColor(Color.BLACK);
                canvas.fillOval(103, 100, 51, 51);
                canvas.fillOval(103, 162, 51, 51);
                break;
            default:
                canvas.setColor(Color.RED);
                canvas.fillOval(103, 224, 51, 51);
                canvas.setColor(Color.BLACK);
                canvas.fillOval(103, 100, 51, 51);
                canvas.fillOval(103, 162, 51, 51);
                break;   
        }
        canvas.setColor(Color.GREEN);
        canvas.drawOval(103, 100, 50, 50);

        canvas.setColor(Color.YELLOW);
        canvas.drawOval(103, 162, 50, 50);

        canvas.setColor(Color.RED);
        canvas.drawOval(103, 224, 50, 50);
    }
}
