package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

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

}
