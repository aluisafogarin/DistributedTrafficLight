package server;

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

import common.SystemParameters;
import common.TrafficLightState;
import gui.TrafficLight;

public class ServerUDP 
{
    private DatagramSocket dataSocket;
    private byte[] incomingData;
    
    public ServerUDP() throws SocketException
    {
        this.dataSocket = new DatagramSocket(SystemParameters.getPort());
        this.incomingData = new byte[1024];
    }

    public void listenSocket()
    {
        TrafficLightState receivedObject;
        try
        {
            while (true)
            {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                dataSocket.receive(incomingPacket);

                InetAddress clientAddress = incomingPacket.getAddress();
                int clientPort = incomingPacket.getPort();

                byte[] data = incomingPacket.getData();
                ByteArrayInputStream input = new ByteArrayInputStream(data);
                ObjectInputStream objectInput = new ObjectInputStream(input);

                receivedObject = (TrafficLightState) objectInput.readObject();
                System.out.println("Object received from Client: " + receivedObject);
                receivedObject.setOnline();

/*                 String reply = "Thank you";
                byte[] replyBeta = reply.getBytes();
                
                DatagramPacket replyPacket = new DatagramPacket
                    (replyBeta, replyBeta.length, clientAddress, clientPort);
                dataSocket.send(replyPacket);
                Thread.sleep(1000); */

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(receivedObject);
    
                byte[] dataOut = outputStream.toByteArray();
                DatagramPacket sendPacket = 
                    new DatagramPacket(dataOut, dataOut.length, clientAddress, clientPort);
                dataSocket.send(sendPacket);
                System.out.println("Acabei de enviar para o cliente: " + dataOut);

/*                 while (receivedObject.getStatus() == true)
                {
                    System.out.println("to no while");
                    //int state = ((TrafficLightState) objectInput.readObject()).getState();
                    int state = receivedObject.getState();
                    System.out.println("RECEBI ESSE ESTADO: " + state);
                    Thread.sleep(3000);
                    
                } */
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
/*         catch (InterruptedException e)
        {
            e.printStackTrace();
        } */
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
}
