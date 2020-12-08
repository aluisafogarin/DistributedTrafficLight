package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ComponentCreator extends JFrame
{

    private static final long serialVersionUID = 1L;
    
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
}
