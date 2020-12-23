package server;

import common.NetworkParams;
import common.SystemParameters;

import java.net.BindException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class responsible to receive and send messages to the clients. 
 * Does all the management procedments as saying when and to each state the traffic light should change.
 */
public class ServerUDP 
{
    private static TrafficLightServerWindow serverWindow;

    private static byte[] incomingData;
    private static DatagramSocket dataSocket;
    private final long TIME = (1000 * 3);
    public static int numClients = 0;

    /**
     * Class constructor, it starts server interface and put online the server to communication.
     * @throws SocketException Report if there is any problem with the socket connection.
     */
    public ServerUDP() throws SocketException
    {
        serverWindow = new TrafficLightServerWindow();
        try 
        {
            ServerUDP.dataSocket = new DatagramSocket(SystemParameters.getPort());
            ServerUDP.incomingData = new byte[1024];
        } 
        catch (BindException e) 
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    
    /** 
     * Set free the port, so it can be used again.
     * @throws SocketException  Report if there is any problem with the socket connection.
     */
    public static void finish() throws SocketException 
    {
        ServerUDP.dataSocket.setReuseAddress(true);
    }

    /**
     * Principal method of management. 
     * It set's a timer, and creates threads to receive and send messages.
     * To each client, it creates a new thread, so it can be processed individually but at the same time.
     */
    public void init() 
    {
        Timer timer = null;
        if (timer == null) {
            timer = new Timer();
            TimerTask task = new TimerTask() 
            {
                @Override
                public void run() 
                {
                    try 
                    {
                        System.out.println("---SERVER---");
                        ClientHandler clientSocket = new ClientHandler();
                    
                        if(numClients == 0 || numClients == 1) 
                        {
                            new Thread(clientSocket).start(); 
                        }
                        else 
                        {
                            for (int i = 0; i < numClients; i++)
                            { 
                                new Thread(clientSocket).start(); 
                            }
                        }
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

    /*
     * Internal class to handle and process every client on different threads.
     */
    private static class ClientHandler implements Runnable 
    {
        private final DatagramPacket clientDataSocket;

        public ClientHandler()
        {
            this.clientDataSocket = 
                new DatagramPacket(
                    ServerUDP.getIncommingData(), 
                    ServerUDP.getIncommingData().length);
        }

        @Override
        public void run() 
        {   
            synchronized(this) 
            {
                try
                {
                    // Receive object from client
                    dataSocket.receive(clientDataSocket);
                    byte[] data = clientDataSocket.getData();

                    ByteArrayInputStream input = new ByteArrayInputStream(data);
                    ObjectInputStream objectInput = new ObjectInputStream(input);

                    NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
                
                    System.out.println("Received from client: " + receivedObject);
                    System.out.println("Current state: " + receivedObject.getState());
                    
                    //Check status and modify the object
                    if (receivedObject.getStatus() == false)
                    {
                        receivedObject.setId(numClients);
                        numClients++;
                    }

                    receivedObject.setOnline();
                    receivedObject.setCanChange(true);
                    receivedObject.setState(changeState(receivedObject.getState()));

                    printTrafficLightInfos(receivedObject.getId(),  receivedObject.getState());

                    // Send object to client
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(outputStream);
                    os.writeObject(receivedObject);

                    byte[] dataOut = outputStream.toByteArray();
                    DatagramPacket sendPacket = 
                        new DatagramPacket(
                            dataOut, 
                            dataOut.length, 
                            clientDataSocket.getAddress(),
                            clientDataSocket.getPort());

                    dataSocket.send(sendPacket);
                    System.out.println("Send to client: " + dataOut);
                } 
                catch (IOException e) 
                {
                    System.out.println("IO Error: " + e.getMessage());
                } 
                catch (ClassNotFoundException e) 
                {
                    System.out.println("Class not found: " + e.getClass());
                }
            }
        }

        /**
         * Change state of the object based on the current state.
         * @param currentState current state of the object.
         * @return new state of the object.
         */
        public int changeState(int currentState) 
        {
            int state = 0;
            // 1 - Green, 2 - Yellow, 3 - Red
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
            return state;
        }
    }

    /** 
     * Get byte array from server.
     * @return byte array of incoming data.
     */
    public static byte[] getIncommingData() 
    {
        return incomingData;
    }
    
    /** 
     * Send to the GUI the id and state of each traffic light to print it on the screen.
     * @param id of the traffic light.
     * @param state current state of the correspondent traffic light.
     */
    public static void printTrafficLightInfos(int id, int state) 
    {
        TrafficLightServerPanel serverGUI = serverWindow.getPanelServer();
        serverGUI.updateTrafficLightsInfos(id, state);  
    }

}
