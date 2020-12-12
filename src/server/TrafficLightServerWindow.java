package server;

/**
 * Responsible to set the server window
 */
public class TrafficLightServerWindow 
{
    public static void startGUI()
    {
        try 
        {
            TrafficLightServerPanel serverWindow = new TrafficLightServerPanel();
            serverWindow.start();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(1);
        }
    }
    
}
