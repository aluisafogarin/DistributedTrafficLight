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
       
        try 
        {
            ClientUDP server = new ClientUDP();
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
