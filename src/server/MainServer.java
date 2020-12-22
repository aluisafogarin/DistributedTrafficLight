package server;

import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

public class MainServer 
{

    public static void main(String[] args) 
    {
        start();
    }

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

    public static void exit() 
    {
        System.exit(0);
    }

}
