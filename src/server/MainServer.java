package server;

import java.io.IOException;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        TrafficLightServerWindow.startGUI();
        try 
        {
            ServerUDP server = new ServerUDP(17);
            server.service();
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
