package client;

/**
 * Responsible to set the client window
 */
public class TrafficLightClientWindow 
{
    public static void main(String[] args) 
    {
        try 
        {
            TrafficLightClientPanel clientWindow = new TrafficLightClientPanel();
            clientWindow.start();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(1);
        }
    }
    
}
