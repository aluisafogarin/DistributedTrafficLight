package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;  

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


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


    public JPanel createPanel(int typeLayout, int numRow, int numCol)
    {
        // 1 = BorderLayout,  2 = GridLayout, 3 = FlowLayout,
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setBackground(Color.LIGHT_GRAY);
        switch(typeLayout) 
        {
            case 1:
                panel.setLayout(new BorderLayout(numRow, numCol));
                break;
            case 2: 
                panel.setLayout(new GridLayout(numRow, numCol));
                System.out.println(numRow + numCol);
                break;
            case 3:
                panel.setLayout(new FlowLayout());
                break;
        }

        return panel;
    }

    public JTextArea createTextArea()
    {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBorder(BorderFactory.createEtchedBorder());
        textArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        return textArea;
    }

    public JPanel createPanelBottom() 
    {   
        panelBottom = new JPanel();
        panelBottom.setBackground(Color.GRAY);
        panelBottom.setSize(10,10);

        labelBottom = createLabel(getShortVersion(), 12, false, "CENTER");
        
        panelBottom.add(labelBottom);

        return panelBottom;
    }
    
    public JLabel createLabel(String text, int size, boolean bold, String align)
    {
        JLabel label = new JLabel(text);
        if (bold)
            label.setFont(new Font("Verdana", Font.BOLD, size));
        else 
            label.setFont(new Font("Verdana", Font.PLAIN, size));

        if (align.equals("CENTER"))
            label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public String getShortVersion()
    {
        return shortVersion;
    }

}
