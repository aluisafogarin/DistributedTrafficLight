package client;

import java.io.IOException;
import java.net.SocketException;

/**
 * Main class of client.
 */
public class MainClient 
{
    
    /** 
     * Main method, start's UDP client.
     * @param args
     */
    public static void main(String[] args) 
    {
       start();
    }

    /**
     * Start's client connection.
     */
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

    /**
     * Closes the connection.
     */
    public static void exit()
    {
        System.exit(0);
    }
}
