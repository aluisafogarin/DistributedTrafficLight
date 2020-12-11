package server;

import gui.Infos;
import gui.DialogWindow;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.ComponentCreator;

/**
 * Class responsible to the panel of server window
 */
public class TrafficLightServerPanel extends JFrame implements ActionListener
{

    private ComponentCreator componentCreator = new ComponentCreator();
    private JPanel mainPanel;
    private JPanel panelBottom;

    private JLabel labelOnlineLights;
    private JLabel labelAllLights;
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
        setPanel();
        bindMenus();
        bindPanel();
        setupWindowsListener();
    }

    /**
     * Overrides actionPerformed from ActionListener to implement functionalities
     * @param event Triggered event
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        if (event.getSource() == this.menuItemAbout) 
        {
            new DialogWindow(this, "Sobre", Infos.getAbout());
        }

        if (event.getSource() == this.menuItemClose)
            exit();

        if (event.getSource() == this.menuItemDisclaimer) 
        {
            new DialogWindow(this, "Direitos" + Infos.getLongVersion(), 
                Infos.getTextFromFile(Infos.DisclaimerFile));
        }
        
        if (event.getSource() == this.menuItemHelp) 
        {
            new DialogWindow(this, "Ajuda" + Infos.getLongVersion(), 
                Infos.getTextFromFile("AjudaServidor.txt"));
        }
    }

    /** 
     * Makes easier to get events from items. 
     * @param listener Event triggered.
     * @param menu Name of menu.
     */
    public void bindItems(ActionListener listener, JMenu menu) 
    {
        for (Component c : menu.getMenuComponents()) 
        {
            if (c instanceof JMenuItem) 
                ((JMenuItem) c).addActionListener(this);
        }
    }

    /**
     * Build menus
     */
    public void bindMenus() 
    {
        for (Component c : this.getJMenuBar().getComponents()) 
        {
            if (c instanceof JMenu) 
                bindItems(this, (JMenu) c);
        }
    }

    
    /** 
     * Makes easier to get events from items on panels.
     * @param listener Event.
     * @param panel Name of panel.
     */
    public void bindItemsPanel(ActionListener listener, JPanel panel) 
    {
        for (Component c : panel.getComponents()) 
        {
            if (c instanceof JTextField) 
                ((JTextField) c).addActionListener(this);
        }
    } 

    /**
     * Builds panels.
     */
    public void bindPanel() 
    {
        for (Component c : this.getContentPane().getComponents()) 
        {
            if (c instanceof JPanel) 
                bindItemsPanel(this, (JPanel) c);
        }
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
        this.setLayout(new BorderLayout(1,1));
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

    private void setPanel()
    {
        labelOnlineLights = componentCreator.createLabel("On Traffic Lights", 16, true);

        mainPanel = componentCreator.createPanel(3, 0, 0);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        
        mainPanel.add(labelOnlineLights);

        this.add(mainPanel, BorderLayout.CENTER);

        panelBottom = componentCreator.createPanelBottom();
        this.add(panelBottom, BorderLayout.SOUTH);
    }

    private void setupWindowsListener()
    {
        this.addWindowListener(new java.awt.event.WindowAdapter()
            {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    exit();
                }
            });
    }
}
