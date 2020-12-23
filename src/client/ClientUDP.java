package client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import common.NetworkParams;
import common.SystemParameters;
/**
 * Class responsible to receive and send messages to the server. 
 */
public class ClientUDP 
{
    private final String hostname;
    private int port;
    private byte[] incomingData;
    private NetworkParams params;

    /**
     * Class constructor.
     * @throws IOException Throws IO Exception.
     */
    public ClientUDP() throws IOException 
    {
        this.hostname = SystemParameters.getHostname();
        this.port = SystemParameters.getPort();
        this.incomingData = new byte[1024];
        this.params = new NetworkParams();
        cliente();
    }

    
    /** 
     * Receive object from server.
     * @param dataSocket DatagramSocket 
     * @return NetworkParams Object 
     * @throws IOException Throws IO Exception.
     * @throws ClassNotFoundException If NetworkParams can't be found.
     */
    public NetworkParams receive(DatagramSocket dataSocket) throws IOException, ClassNotFoundException 
    {
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        dataSocket.receive(incomingPacket);

        byte[] dataIncoming = incomingPacket.getData();
        ByteArrayInputStream input = new ByteArrayInputStream(dataIncoming);
        ObjectInputStream objectInput = new ObjectInputStream(input);

        NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
        System.out.println("Datagram received from server: " + receivedObject);

        return receivedObject;
    }

    
    /** 
     * Send object to server.
     * @param dataSocket DatagramSocket.
     * @param address Address of the server. 
     * @throws IOException Throws IO Exception.
     */
    public void send(DatagramSocket dataSocket, InetAddress address) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(params);

        byte[] data = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);

        dataSocket.send(sendPacket);
        System.out.println("Datagram send to server: " + sendPacket);
        params.setOnline();
    }

    /**
     * Manages when send and receives messages from server.
     */
    public void cliente() 
    {
        try {
            TrafficLightClientWindow clientWindow = new TrafficLightClientWindow();

            while (true) 
            {
                System.out.println("--- CLIENT ---");
                DatagramSocket dataSocket = new DatagramSocket();
                InetAddress address = InetAddress.getByName(hostname);
                //DatagramSocket dataSocket = new DatagramSocket();
                byte[] incomingData = new byte[1024];

                send(dataSocket, address);
                NetworkParams receivedObject = receive(dataSocket);

                dataSocket.close();
                params.setState(receivedObject.getState());
                params.setNumClients(receivedObject.getNumClients());
                params.setCanChange(receivedObject.getCanChange());
                params.setId(receivedObject.getId());

                if (params.getCanChange() == true) 
                {
                    updateLight(clientWindow);
                } 
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

    
    /** 
     * Change which light is on.
     * @param clientWindow Object from client window
     */
    public void updateLight(TrafficLightClientWindow clientWindow)
    {
        TrafficLightClientPanel clientGUI = clientWindow.getPanelClient();
        
        clientGUI.setState(params.getState());
        clientGUI.repaint();
    }

    /** 
     * Set free the port, so it can be used again.
     * @throws SocketException  Report if there is any problem with the socket connection.
     */
    public static void finish() 
    {
        System.exit(0);
        MainClient.exit();
    }

}   
