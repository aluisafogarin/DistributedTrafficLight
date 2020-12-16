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

import common.TrafficLightState;
import gui.TrafficLight;

public class ClientUDP 
{
    private final String hostname;
    private int port;
    private static int state;
    private byte[] incomingData;

    public ClientUDP(int port) throws IOException 
    {
        this.hostname = "localhost";
        this.port = port;
        this.incomingData = new byte[1024];
        cliente();
    }

    public void cliente() {
        try {
            TrafficLightClientWindow clientWindow = new TrafficLightClientWindow();
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println(address);
            DatagramSocket dataSocket = new DatagramSocket();
            byte[] incomingData = new byte[1024];
            
            TrafficLightState light = new TrafficLightState();

            state = light.getState();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(light);

            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);

            dataSocket.send(sendPacket);
            System.out.println("Message sent from client: " + sendPacket);

            /* Reading object send from Server */
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            dataSocket.receive(incomingPacket);

            //InetAddress clientAddress = incomingPacket.getAddress();
            //int clientPort = incomingPacket.getPort();

            byte[] dataIncoming = incomingPacket.getData();
            ByteArrayInputStream input = new ByteArrayInputStream(dataIncoming);
            ObjectInputStream objectInput = new ObjectInputStream(input);

            TrafficLightState receivedObject = (TrafficLightState) objectInput.readObject();
            System.out.println("Object received from Server: " + receivedObject);
            System.out.println("Estado do objeto recebido: " + receivedObject.getStatus());
/*             DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            dataSocket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
            Thread.sleep(1000); */

/*             ServerSocket socket = new ServerSocket(port);
            Socket sock = socket.accept();
            ObjectInputStream is = new ObjectInputStream(
                new BufferedInputStream(sock.getInputStream()));
            TrafficLightState receivedObject = (TrafficLightState) is.readObject();
            System.out.println("Recebi algo:" + receivedObject);
            System.out.println("Estado do objeto recebido: " + receivedObject.getStatus());
            socket.close(); */

            /* ByteArrayInputStream input = new ByteArrayInputStream(incomingData);
            ObjectInputStream objectInput = new ObjectInputStream(input);
            TrafficLightState receivedObject = (TrafficLightState) objectInput.readObject();
            System.out.println("RECEBI ALGO: " + receivedObject);
            System.out.println("Esse é o estado do objeto que recebi:" + receivedObject.getStatus()); */

/*             System.out.println("status:" + light.getStatus());
            while (light.getStatus() == true)
            {
                try 
                {
                    Thread.sleep(3000);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                light.changeState(state);
                state = light.getState();
                updateLight(clientWindow);
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
/*         catch (InterruptedException e) 
        {
            e.printStackTrace();
        } */
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void updateLight(TrafficLightClientWindow clientWindow)
    {
        TrafficLightClientPanel clientGUI = clientWindow.getPanelClient();
        clientGUI.repaint();
    }

    public static int getState() {
        return state;
    }
}   
