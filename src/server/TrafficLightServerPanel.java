package server;

import common.Infos;

import gui.ComponentCreator;
import gui.DialogWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.net.SocketException;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * Class responsible to the panel of server
 */
public class TrafficLightServerPanel extends JFrame implements ActionListener {

    private ComponentCreator componentCreator = new ComponentCreator();
   
    private JPanel mainPanel;
    private JPanel panelBottom;

    private JLabel labelOnlineLights;
    private JTextArea clientInfo;

    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenuBar menuBar;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemClose;
    private JMenuItem menuItemDisclaimer;
    private JMenuItem menuItemHelp;

    private ArrayList<Integer> clientIds = new ArrayList<Integer>();
    private ArrayList<Integer> clientStates = new ArrayList<Integer>();

    private Infos infos = new Infos();

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
     * 
     * @param event Triggered event
     */
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        if (event.getSource() == this.menuItemAbout) 
        {
            new DialogWindow(this, Infos.languageInfos.getAbout(), Infos.getAbout());
        }

        if (event.getSource() == this.menuItemClose)
            exit();

        if (event.getSource() == this.menuItemDisclaimer) 
        {
            new DialogWindow(
                this,
                "Direitos" + Infos.getLongVersion(),
                 Infos.getTextFromFile(infos.DisclaimerFile));
        }

        if (event.getSource() == this.menuItemHelp) 
        {
            new DialogWindow(
                this,
                "Ajuda" + Infos.getLongVersion(),
                infos.getTextFromFile("AjudaServidor.txt"));
        }
    }

    /**
     * Makes easier to get events from items.
     * 
     * @param listener Event triggered.
     * @param menu     Name of menu.
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
     * 
     * @param listener Event.
     * @param panel    Name of panel.
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
    public void bindPanel() {
        for (Component c : this.getContentPane().getComponents()) 
        {
            if (c instanceof JPanel)
                bindItemsPanel(this, (JPanel) c);
        }
    }

    /**
     * Finishes server window.
     */
    public void exit() 
    {
        System.exit(0);
    }

    /**
     * Makes server window visible.
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
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(1, 1));

    }

    /**
     * Set window menu bar. 
     */
    private void setMenus() 
    {
        //menuFile = componentCreator.createMenu(infos.languageInfos.getFile(), 'F');
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
     * Set window panels.
    */
    private void setPanel() 
    {
        labelOnlineLights = 
            componentCreator.createLabel(infos.languageInfos.getTitle(), 16, true, "CENTER");

        mainPanel = componentCreator.createPanel(1, 1, 1);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JPanel conteiner = componentCreator.createPanel(2, 1, 2);

        clientInfo = componentCreator.createTextArea();
        clientInfo.setText(infos.languageInfos.getBaseMsg());
        conteiner.add(clientInfo);

        mainPanel.add(labelOnlineLights, BorderLayout.NORTH);
        mainPanel.add(conteiner, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        panelBottom = componentCreator.createPanelBottom();
        this.add(panelBottom, BorderLayout.SOUTH);
    }

    /**
     * Manage when the window is closed.
     */
    private void setupWindowsListener() 
    {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try 
                {
                    ServerUDP.finish();
                } 
                catch (SocketException e) 
                {
                    e.printStackTrace();
                }
                    exit();
                }
            });
    }

    
    /** 
     * Update traffic lights infos according to client.
     * @param id Traffic light id.
     * @param state traffic light current state.
     */
    public void updateTrafficLightsInfos(int id, int state)
    {
        StringBuffer lightStatus = new StringBuffer();
        if (!clientIds.contains(id))
        {
            clientIds.add(id++);
            clientStates.add(state);
        }
        else
        {
            clientStates.set(id, state);
        }
        
        for (int i: clientIds)
        {
            lightStatus.append(Infos.languageInfos.getSysNameClient() + ": ");
            int num = clientIds.get(i);
            num++;
            lightStatus.append(num);
            lightStatus.append(" - " + Infos.languageInfos.getState() + ": ");
            // 1 - Green, 2 - Yellow, 3 - Red
            switch (clientStates.get(i))
            {
                case 1:
                    lightStatus.append(Infos.languageInfos.getGreen());
                    break;
                case 2:
                    lightStatus.append(Infos.languageInfos.getYellow());
                    break;
                case 3:
                    lightStatus.append(Infos.languageInfos.getRed());
                    break;
                default:
                    lightStatus.append(Infos.languageInfos.getRed());
                    break;
            }
            lightStatus.append("\n");
        }
        
        clientInfo.setText(lightStatus.toString());
    }
}
