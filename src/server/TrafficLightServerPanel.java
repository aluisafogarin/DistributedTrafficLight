package server;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.ComponentCreator;

/**
 * Class responsible to the panel of server window
 */
public class TrafficLightServerPanel extends JFrame implements ActionListener
{

    private ComponentCreator componentCreator = new ComponentCreator();
    private JPanel panelWindow;
    private JPanel panelBottom;

    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemClose;
    private JMenuItem menuItemDisclaimer;
    private JMenuItem menuItemHelp;

    private static final long serialVersionUID = 1L;

    TrafficLightServerPanel() 
    {
        super("Traffic Light Server");
        setWindow();
        setMenus();
    }

    /**
     * Overrides actionPerformed from ActionListener to implement functionalities
     * @param event Triggered event
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {

    }

    public void exit()
    {
        System.exit(0);
    }

    public void start() 
    {
        this.setVisible(true);
    }

    private void setWindow() 
    {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));
    }

    private void setMenus()
    {
        menuFile = componentCreator.createMenu("File", 'F');
        menuHelp = componentCreator.createMenu("Help", 'H');

        menuItemAbout = componentCreator.createMenuItem("About", 'A');
        menuItemClose = componentCreator.createMenuItem("Close", 'X');
        menuItemDisclaimer = componentCreator.createMenuItem("Disclaimer", 'D');
        menuItemHelp = componentCreator.createMenuItem("Help", 'H');

        menuFile.add(menuItemDisclaimer);
        menuFile.add(menuItemClose);

        menuHelp.add(menuItemAbout);
        menuHelp.add(menuItemHelp);

        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        this.setJMenuBar(menuBar);

    }

    private void setLayout()
    {

    }

    private void setPanel()
    {

    }

    private void setPanelBottom()
    {

    }
}
