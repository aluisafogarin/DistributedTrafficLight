package client;

import common.Infos;
import gui.TrafficLight;
import gui.DialogWindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.ComponentCreator;
/**
 * Class responsible to build the panel of server window
 */
public class TrafficLightClientPanel extends JFrame implements ActionListener
{
    private Infos infos = new Infos();

    private ComponentCreator componentCreator = new ComponentCreator();
    private JPanel mainPanel;
    private JPanel panelBottom;

    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemClose;
    private JMenuItem menuItemDisclaimer;
    private JMenuItem menuItemHelp;

    private TrafficLight trafficLight;

    private int state;

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor, calls all needed methods.
     */
    TrafficLightClientPanel() 
    {
        super("Traffic Light Client");
        setWindow();
        setMenus();
        setPanel();
        bindMenus();
        bindPanel();
        setupWindowsListener();
    }

    
    /** 
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        trafficLight = new TrafficLight(g, mainPanel.getSize());
        trafficLight.drawRectangle();
        trafficLight.setLightColor(state);
        trafficLight.drawLights();
    } 

    
    /** 
     * @param state
     */
    public void setState(int state) 
    {
        this.state = state;
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
            new DialogWindow(this, "Sobre", infos.getAbout());
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
            new DialogWindow(this, infos.languageInfos.getHelp() + Infos.getLongVersion(), 
                Infos.getTextFromFile("AjudaCliente.txt"));
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
    
    /**
     * Exits the window.
     */
    public void exit()
    {
        System.exit(0);
    }

    /**
     * Make components visible.
     */
    public void start() 
    {
        this.setVisible(true);
    }

    /**
     * Set window specifications.
     */
    private void setWindow() 
    {
        this.setSize(250, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(1,1));
        this.setResizable(false);
    }

    /**
     * Set window menu bar. 
     */
    private void setMenus()
    {
        menuFile = componentCreator.createMenu(infos.languageInfos.getFile(), 'F');
        menuHelp = componentCreator.createMenu(infos.languageInfos.getHelp(), 'H'); 

        menuItemAbout = componentCreator.createMenuItem(infos.languageInfos.getAbout(), 'A');
        menuItemClose = componentCreator.createMenuItem(infos.languageInfos.getExit(), 'X');
        menuItemDisclaimer = componentCreator.createMenuItem(infos.languageInfos.getDisclaimer(), 'D');
        menuItemHelp = componentCreator.createMenuItem(infos.languageInfos.getHelp(), 'H');

        menuFile.add(menuItemDisclaimer);
        menuFile.add(menuItemClose);

        menuHelp.add(menuItemAbout);
        menuHelp.add(menuItemHelp);

        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        this.setJMenuBar(menuBar);
    }

    /**
     * Set panel.
     */
    private void setPanel()
    {
        mainPanel = componentCreator.createPanel(1, 1, 1);
        panelBottom = componentCreator.createPanelBottom();
        
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
    }

    /**
     * Set listener when window is closed.
     */
    private void setupWindowsListener()
    {
        this.addWindowListener(new java.awt.event.WindowAdapter()
            {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    ClientUDP.finish();
                    exit();
                }
            });
    }
}
