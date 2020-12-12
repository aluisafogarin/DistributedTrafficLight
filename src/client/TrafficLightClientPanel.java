package client;

import gui.Infos;
import gui.TrafficLight;
import gui.DialogWindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
public class TrafficLightClientPanel extends JFrame implements ActionListener
{

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

    private static final long serialVersionUID = 1L;

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

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        TrafficLight createTraffic = new TrafficLight(g);
        createTraffic.drawRectangle(mainPanel.getSize());
        //componentCreator.drawRectangle(g, mainPanel.getSize());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        createTraffic.drawCircle(1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        createTraffic.drawCircle(2);
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
        /* this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5)); */
        this.setSize(250, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(1,1));
        this.setResizable(false);
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
        mainPanel = componentCreator.createPanel(1, 1, 1);
        panelBottom = componentCreator.createPanelBottom();
        
        this.add(mainPanel, BorderLayout.CENTER);
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
