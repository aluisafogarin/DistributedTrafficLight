package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ComponentCreator extends JFrame
{

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
