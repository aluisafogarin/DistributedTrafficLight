package client;

/**
 * Responsible to set the client window
 */
public class TrafficLightClientWindow 
{
    private TrafficLightClientPanel clientWindow;

    /**
     * Class constructor, builds the GUI.
     */
    public TrafficLightClientWindow()
    {
        try 
        {
            this.clientWindow = new TrafficLightClientPanel();
            clientWindow.start();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(1);
        }
    }

    
    /** 
     * Get panel client so it can be possible to change text.
     * @return TrafficLightClientPanel client window.
     */
    public TrafficLightClientPanel getPanelClient()
    {
        return clientWindow;
    }
    
}
