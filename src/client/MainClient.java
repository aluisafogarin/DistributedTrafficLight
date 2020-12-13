package client;

import java.io.IOException;
import java.net.SocketException;

public class MainClient 
{
    public static void main(String[] args) 
    {
       start();
    }

    public static void start()
    {
        TrafficLightClientWindow.startGUI();
        try 
        {
            ClientUDP server = new ClientUDP(17);
        } 
        catch (SocketException e) 
        {
            System.out.println("Socket error: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("IO Error: " + e.getMessage());
        }
    }

    public static void exit()
    {
        System.exit(0);
    }
}