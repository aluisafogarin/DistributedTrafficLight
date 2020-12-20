package client;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

import common.NetworkParams;
import common.SystemParameters;
import gui.TrafficLight;

public class ClientUDP 
{
    private final String hostname;
    private int port;
    private byte[] incomingData;
    private NetworkParams params;

    public ClientUDP() throws IOException 
    {
        this.hostname = SystemParameters.getHostname();
        this.port = SystemParameters.getPort();
        this.incomingData = new byte[1024];
        this.params = new NetworkParams();
        cliente();
    }

    public NetworkParams receive(DatagramSocket dataSocket) throws IOException, ClassNotFoundException
    {
        /* Receiving object from server */
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        dataSocket.receive(incomingPacket);

        byte[] dataIncoming = incomingPacket.getData();
        ByteArrayInputStream input = new ByteArrayInputStream(dataIncoming);
        ObjectInputStream objectInput = new ObjectInputStream(input);

        NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
        System.out.println("Datagram received from server: " + receivedObject);
        System.out.println("State received: " + receivedObject.getState());

        return receivedObject;
    }

    public void send(DatagramSocket dataSocket, InetAddress address) throws IOException
    {
        /* Sending object to server */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(params);

        byte[] data = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);

        dataSocket.send(sendPacket);
        System.out.println("Datagram send to server: " + sendPacket);
        System.out.println("State send: " + params.getState());
    }

    public void cliente() {
        try {
            TrafficLightClientWindow clientWindow = new TrafficLightClientWindow();
            while (true) 
            {
                System.out.println("--- CLIENT ---");
                InetAddress address = InetAddress.getByName(hostname);
                System.out.println(address);
                DatagramSocket dataSocket = new DatagramSocket();
                byte[] incomingData = new byte[1024];

                send(dataSocket, address);
                NetworkParams receivedObject = receive(dataSocket);
                
                dataSocket.close();
                params.setState(receivedObject.getState());
                updateLight(clientWindow);
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
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void updateLight(TrafficLightClientWindow clientWindow)
    {
        TrafficLightClientPanel clientGUI = clientWindow.getPanelClient();
        clientGUI.setState(params.getState());
        clientGUI.repaint();
    }

}   
