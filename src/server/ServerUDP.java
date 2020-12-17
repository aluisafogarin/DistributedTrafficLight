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
                        listenSocket();
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
    public void listenSocket()
    {
        NetworkParams receivedObject;
        try
        {
            while (true)
            {
                System.out.println("--- SERVER ---");
                /* Receiving object from client */
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                dataSocket.receive(incomingPacket);

                InetAddress clientAddress = incomingPacket.getAddress();
                int clientPort = incomingPacket.getPort();

                byte[] data = incomingPacket.getData();
                ByteArrayInputStream input = new ByteArrayInputStream(data);
                ObjectInputStream objectInput = new ObjectInputStream(input);

                receivedObject = (NetworkParams) objectInput.readObject();
                System.out.println("Received from client: " + receivedObject);
                System.out.println("Current state: " + receivedObject.getState());
                receivedObject.setOnline();

                Thread.sleep(SystemParameters.getTime());
                receivedObject.setState(changeState(receivedObject.getState()));
                System.out.println("New state: " + receivedObject.getState());
                
                /* Sending object to client */
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(receivedObject);
    
                byte[] dataOut = outputStream.toByteArray();
                DatagramPacket sendPacket = 
                    new DatagramPacket(dataOut, dataOut.length, clientAddress, clientPort);
                dataSocket.send(sendPacket);
                System.out.println("Send to client: " + dataOut);
            }
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
/*     protected void service() throws IOException
    {
        while(true)
        {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            dataSocket.receive(request);


            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            String quote = "Mensagem de teste";
            byte[] buffer = quote.getBytes();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            dataSocket.send(response);
            //System.out.println("TO NO SERVIDOR");
        }
    } */

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
