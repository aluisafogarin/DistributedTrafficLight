package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

import common.TrafficLightState;
import gui.TrafficLight;

public class ClientUDP 
{
    private final String hostname;
    private int port;
    private static int state;

    public ClientUDP(int port) throws IOException 
    {
        this.hostname = "localhost";
        this.port = port;
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
            System.out.println("Message sent from client");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            light.changeState(state);
            state = light.getState();
            updateLight(clientWindow);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            light.changeState(state);
            updateLight(clientWindow);
            state = light.getState();

            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            dataSocket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
            Thread.sleep(2000);

        } catch (SocketTimeoutException e) {
            System.out.println("Timeout error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
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
