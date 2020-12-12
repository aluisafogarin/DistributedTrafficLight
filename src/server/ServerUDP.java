package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerUDP 
{
    private DatagramSocket dataSocket;
    private List<String> status = new ArrayList<String>();
    private Random random;

    public ServerUDP(int port) throws SocketException
    {
        this.dataSocket = new DatagramSocket(port);
        random = new Random();
    }

    protected void service() throws IOException
    {
        while(true)
        {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            dataSocket.receive(request);

            ByteBuffer bb = ByteBuffer.allocate(4);
            bb.putInt(3);
            byte[] buffer = bb.array();

            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            dataSocket.send(response);

            System.out.println("MANO TO NO SERVIDOR");
            System.out.println(response.toString());
        }
    }
}
