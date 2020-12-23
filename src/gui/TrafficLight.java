package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Creates traffic light draw.
 */
public class TrafficLight extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private Graphics2D canvas;
    private Dimension dimensionPanel;

    /**
     * Constructor of the graphic traffic light.
     * @param g 
     * @param dimension dimension of jpanel.
     */
    public TrafficLight(Graphics g, Dimension dimension) 
    {
        this.canvas = (Graphics2D) g;
        this.dimensionPanel = dimension;
    }

    /**
     * Draw the black rectangle
     */
    public void drawRectangle() 
    {
        int xPoint = 78;
        int yPoint = 87;

        int width = 100;
        int height = 200;

        this.canvas.setColor(Color.BLACK);
        canvas.drawRect((int) (xPoint - 5), (int) (yPoint - 5), width + 10, height + 10);

        canvas.setColor(Color.BLACK);
        canvas.drawRect(xPoint, yPoint, width, height);
        canvas.fillRect(xPoint, yPoint, width, height);

    }

    /**
     * Draw light circles
     */
    public void drawLights()
    {
        canvas.setColor(Color.GREEN);
        canvas.drawOval(103, 100, 50, 50);

        canvas.setColor(Color.YELLOW);
        canvas.drawOval(103, 162, 50, 50);

        canvas.setColor(Color.RED);
        canvas.drawOval(103, 224, 50, 50);
    }

    
    /** 
     * Fill light collor
     * @param state 1 = Green, 2 = Yellow, 3 = Red
     */
    public void setLightColor(int state)
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
    }
}
