package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class ClientUDP 
{
    private final String hostname;
    private int port;

    public ClientUDP(int port) throws IOException
    {
        this.hostname = "localhost";
        this.port = port;
        cliente();
    }

    public void cliente() 
    {
        try
        {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket dataSocket = new DatagramSocket();

            while (true)
            {
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                dataSocket.send(request);

                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                dataSocket.receive(response);

                String quote = new String(buffer, 0, response.getLength());

                System.out.println(quote);
                System.out.println("TO EM CLIENTE");
                Thread.sleep(500);
            }
        }
        catch (SocketTimeoutException e)
        {
            System.out.println("Timeout error: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}   
