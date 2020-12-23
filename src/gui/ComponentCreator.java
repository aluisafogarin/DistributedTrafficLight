package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Component creator is a class to make it easier to create components in both server and client GUI.
 */
public class ComponentCreator extends JFrame
{

    private JPanel panelBottom;
    private JLabel labelBottom;

    private static final long serialVersionUID = 1L;
    private static String shortVersion = "Controlador de Semáforos";

    /** 
     * Creates menu
     * @param text text to menu
     * @param shortcut shortcut to menu button
     * @return JMenu object to be build.
     */
    public JMenu createMenu(String text, char shortcut)
    {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(shortcut);

        return menu;
    }
    
    /** 
     * Creates menu item
     * @param text text on menu item
     * @param shortcut shortcut to menu button
     * @return JMenuItem object to be build.
     */
    public JMenuItem createMenuItem(String text, char shortcut)
    {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setMnemonic(shortcut);

        return menuItem;
    }


    
    /** 
     * Creates JPanel
     * @param typeLayout type of panel layout
     * @param numRow number of rows of layout
     * @param numCol number of columns of layout
     * @return JPanel object to be build.
     */
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
                break;
            case 3:
                panel.setLayout(new FlowLayout());
                break;
        }

        return panel;
    }

    
    /** 
     * Creates text area
     * @return JTextArea object to be build.
     */
    public JTextArea createTextArea()
    {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBorder(BorderFactory.createEtchedBorder());
        textArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        return textArea;
    }

    
    /** 
     * Creates bottom panel
     * @return JPanel object to be build.
     */
    public JPanel createPanelBottom() 
    {   
        panelBottom = new JPanel();
        panelBottom.setBackground(Color.GRAY);
        panelBottom.setSize(10,10);

        labelBottom = createLabel(getShortVersion(), 12, false, "CENTER");
        
        panelBottom.add(labelBottom);

        return panelBottom;
    }
    
    
    /** 
     * Creates label
     * @param text text to be displayed
     * @param size font size
     * @param bold true = bold, false = regular
     * @param align alignment based on swing constants
     * @return JLabel object to be build.
     */
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

    
    /** 
     * Get short version
     * @return String object to be build.
     */
    public String getShortVersion()
    {
        return shortVersion;
    }

}
