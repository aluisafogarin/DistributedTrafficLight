package client;

/**
 * Responsible to set the client window
 */
public class TrafficLightClientWindow 
{
    private TrafficLightClientPanel clientWindow;

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

    public TrafficLightClientPanel getPanelClient()
    {
        return clientWindow;
    }
    
}
