package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

import common.TrafficLightStatus;
import gui.TrafficLight;

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
            System.out.println(address);
            DatagramSocket dataSocket = new DatagramSocket();

            byte[] incomingData = new byte[1024];
            TrafficLightStatus light = new TrafficLightStatus();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(light);

            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);

            dataSocket.send(sendPacket);
            System.out.println("Message sent from client");

            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            dataSocket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
            Thread.sleep(2000);

/*             while (true)
            {
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                dataSocket.send(request);

                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                dataSocket.receive(response);

                String quote = new String(buffer, 0, response.getLength());
                //String quote2 = new String(response.getData(), 0, response.getLength());

                System.out.println(quote);
                //System.out.println("TO EM CLIENTE");
                Thread.sleep(500);
            } */
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
