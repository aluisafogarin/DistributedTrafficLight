package client;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

/**
 * Class responsible to the panel of server window
 */
public class TrafficLightClientPanel extends JFrame implements ActionListener
{

    private static final long serialVersionUID = 1L;

    TrafficLightClientPanel() 
    {
        super("Traffic Light Client");
        setWindow();
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

    private void setWindow() {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));
    }

}
