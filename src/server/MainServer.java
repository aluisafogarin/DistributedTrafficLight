package server;

import java.io.IOException;
import java.net.SocketException;

/**
 *  Main class of the server. It starts the udp server.
 *  @author Ana Luísa Fogarin, Larissa Correira, Verussa de Alencar and Vinícius Lourenço
 *  @serial V1.0
 *  @since December, 2020
 */
public class MainServer 
{
    
    /** 
     * Server main method.
     * @param args
     */
    public static void main(String[] args) 
    {
        start();
    }

    /**
     * Open server connection and treats exceptions related to the connection and i/o.
     */
    public static void start() 
    {
        try 
        {
            ServerUDP server = new ServerUDP();
            server.init();
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
     * Finishes the server.
     */
    public static void exit() 
    {
        System.exit(0);
    }

}
