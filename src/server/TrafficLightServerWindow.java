package server;

/**
 * Responsible to set the server window
 */
public class TrafficLightServerWindow 
{
    private TrafficLightServerPanel serverWindow;

    public TrafficLightServerWindow() 
    {
        try 
        {
            this.serverWindow = new TrafficLightServerPanel();
            serverWindow.start();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public TrafficLightServerPanel getPanelServer() {
        return serverWindow;
    }
}
