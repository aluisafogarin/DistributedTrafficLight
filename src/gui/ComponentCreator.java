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


public class ComponentCreator extends JFrame
{

    private JPanel mainPanel;
    private JPanel panelBottom;

    private JLabel labelBottom;
    private JLabel labelOnlineLights;
    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemClose;
    private JMenuItem menuItemDisclaimer;
    private JMenuItem menuItemHelp;

    private enum LocalShapes
    {
       OVAL,
       RECTANGLE,
       EMPTY
    }
    private LocalShapes shape;
    private Color foreground;
    private Color background;

    private static final long serialVersionUID = 1L;
    private static String shortVersion = "Traffic Light Controler";

    public JMenu createMenu(String text, char shortcut)
    {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(shortcut);

        return menu;
    }


    public JMenuItem createMenuItem(String text, char shortcut)
    {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setMnemonic(shortcut);

        return menuItem;
    }

    public JPanel createPanel(int typeLayout, int numCol, int numLin)
    {
        // 1 = BorderLayout,  2 = GridLayout, 3 = FlowLayout,
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBackground(Color.LIGHT_GRAY);
        switch(typeLayout) 
        {
            case 1:
                panel.setLayout(new BorderLayout());
            case 2: 
                panel.setLayout(new GridLayout(numCol, numLin));
            case 3:
                panel.setLayout(new FlowLayout());
        }

        return panel;
    }

    public JPanel createPanelBottom() 
    {   
        panelBottom = new JPanel();
        panelBottom.setBackground(Color.GRAY);
        panelBottom.setSize(10,10);

        labelBottom = createLabel(getShortVersion(), 12, false);
        
        panelBottom.add(labelBottom);

        return panelBottom;
    }
    
    public JLabel createLabel(String text, int size, boolean bold)
    {
        JLabel label = new JLabel(text);
        if (bold)
            label.setFont(new Font("Verdana", Font.BOLD, size));
        else 
        label.setFont(new Font("Verdana", Font.PLAIN, size));
        return label;
    }

    public String getShortVersion()
    {
        return shortVersion;
    }

    public void drawRectangle(Graphics g, Dimension dimension)
    {   
        Graphics2D canvas = (Graphics2D) g;
        System.out.println(dimension);

        int widthPanel = (int) dimension.getWidth();
        int heightPanel = (int) dimension.getHeight();
        
        /* int xPoint = (int) (widthPanel / 3);
        int yPoint = (int) (heightPanel * .33); */
        int xPoint = 78;
        int yPoint = 87;

        /* int width = (int) (widthPanel * .4);
        int height = (int) (heightPanel * .7); */
        int width = 100;
        int height = 200;

        canvas.setColor(Color.BLACK);
        canvas.drawRect((int) (xPoint - 5), (int) (yPoint - 5), width + 10, height + 10);

        canvas.setColor(Color.BLACK);
        canvas.drawRect(xPoint, yPoint, width, height);
        canvas.fillRect(xPoint, yPoint, width, height);

        drawCircle(g, dimension);
    }

    public void drawCircle(Graphics g, Dimension dimension)
    {
        Graphics2D canvas = (Graphics2D) g;

        canvas.setColor(Color.GREEN);
        canvas.drawOval(103, 100, 50, 50);
        canvas.fillOval(103, 100, 51, 51);

        canvas.setColor(Color.YELLOW);
        canvas.drawOval(103, 162, 50, 50);
        canvas.fillOval(103, 162, 51, 51);

        canvas.setColor(Color.RED);
        canvas.drawOval(103, 224, 50, 50);
        canvas.fillOval(103, 224, 51, 51);
    }
}
