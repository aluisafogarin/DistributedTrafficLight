package server;

import common.NetworkParams;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import common.SystemParameters;
import gui.TrafficLight;

public class ServerUDP 
{
    private DatagramSocket dataSocket;
    private byte[] incomingData;
    private final long TIME = (1000 * 3);
    
    public ServerUDP() throws SocketException
    {
        this.dataSocket = new DatagramSocket(SystemParameters.getPort());
        this.incomingData = new byte[1024];
    }

    public void execute()
    {
        System.out.println("Execute");
        Timer timer = null;
        if (timer == null) 
        {
            timer = new Timer();
            TimerTask task = new TimerTask()
            {
                public void run()
                {
                    try
                    {
                        server();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(task, TIME, TIME);
        }
    }

    public NetworkParams receive(DatagramPacket incomingPacket)
        throws IOException, ClassNotFoundException
    {
        dataSocket.receive(incomingPacket); 
        
        byte[] data = incomingPacket.getData();
       
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        ObjectInputStream objectInput = new ObjectInputStream(input);

        NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
        System.out.println("Received from client: " + receivedObject);
        System.out.println("Current state: " + receivedObject.getState());
        receivedObject.setOnline();

        return receivedObject;
    }

    public boolean server()
    {
        boolean check = false;
        try 
        {
            System.out.println("--- SERVER ---");
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);

/*             InetAddress clientAddress = incomingPacket.getAddress();
            int clientPort = incomingPacket.getPort(); */
            NetworkParams receivedObject = receive(incomingPacket);

            receivedObject.setState(changeState(receivedObject.getState()));
            System.out.println("New state: " + receivedObject.getState());

            send(receivedObject, incomingPacket);
            if (incomingPacket != null)
                check = true;
        }
        catch (IOException e)
        {   
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
        return check;
    }

    public void send(NetworkParams receivedObject, DatagramPacket incomingPacket) throws IOException
    {
        /* Sending object to client */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(receivedObject);

        byte[] dataOut = outputStream.toByteArray();
        DatagramPacket sendPacket = 
            new DatagramPacket(dataOut, dataOut.length, incomingPacket.getAddress(), incomingPacket.getPort());
        dataSocket.send(sendPacket);
        System.out.println("Send to client: " + dataOut);
    }

    public int changeState(int currentState)
    {
        int state = 0;
        System.out.println("Current state: " + currentState);
        switch (currentState)
        {
            case 3:
                state = 1;
                break;
            case 2:
                state = 3;
                break;
            case 1:
                state = 2;
                break;
            default:
                state = 3;
                break;
        }
        System.out.println("Mudei o estado! Agora é: " + state);
        return state;
    }
    
}
