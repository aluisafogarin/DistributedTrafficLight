package server;

import common.NetworkParams;
import common.SystemParameters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServerUDP {
    private static DatagramSocket dataSocket;
    private static byte[] incomingData;
    private final long TIME = (1000 * 3);
    public static int numClients = 0;
    private int readyClients;
    private static ArrayList<Integer> arrayStates = new ArrayList<Integer>();
    private ArrayList<NetworkParams> nt = new ArrayList<NetworkParams>();
    private static TrafficLightServerWindow serverWindow;

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

    public void finish() throws SocketException 
    {
        ServerUDP.dataSocket.setReuseAddress(true);
    }

    public void init() 
    {

        System.out.println("Execute");
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
                        System.out.println("Task");
                        System.out.println("Num clients" + numClients);
                        ClientHandler clientSocket = new ClientHandler();
                    
                        if(numClients == 0 || numClients == 1) {
                            new Thread(clientSocket).start(); 
                        }
                        else {
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
     * public void init() { while (true) {
     * 
     * } }
     */
    private static class ClientHandler implements Runnable {
        private final DatagramPacket clientDataSocket;

        public ClientHandler()
        {
            this.clientDataSocket = 
                new DatagramPacket(ServerUDP.getIncommingData(), ServerUDP.getIncommingData().length);
        }

        @Override
        public void run() 
        {
           
            synchronized(this) 
            {
                /* System.out.println("run class handler");
                System.out.println("To numa thread: " + clientDataSocket.toString()); */
                try
                {
                    //NetworkParams receivedObject = receive(clientDataSocket);

                    dataSocket.receive(clientDataSocket);

                    byte[] data = clientDataSocket.getData();

                    ByteArrayInputStream input = new ByteArrayInputStream(data);
                    ObjectInputStream objectInput = new ObjectInputStream(input);

                    NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
                
                   /*  System.out.println("Received from client: " + receivedObject);
                    System.out.println("Current state: " + receivedObject.getState()); */
                    
                    if (receivedObject.getStatus() == false)
                    {
                        receivedObject.setId(numClients);
                        numClients++;
                        arrayStates.add(0);
                    }

                    receivedObject.setOnline();
                    receivedObject.setCanChange(true);

                    receivedObject.setState(changeState(receivedObject.getState()));
                    //System.out.println("New state: " + receivedObject.getState());

                    arrayStates.set(receivedObject.getId(), receivedObject.getState());
                    printTrafficLightInfos(receivedObject.getId(),  receivedObject.getState());

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
                    // System.out.println("Send to client: " + dataOut);
                } 
                catch (IOException e) 
                {
                    System.out.println(e.getMessage());
                } 
                catch (ClassNotFoundException e) 
                {
                    System.out.println(e.getMessage());
                }
            }
        }

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

    public static byte[] getIncommingData() 
    {
        return incomingData;
    }

    public NetworkParams receive(DatagramPacket incomingPacket) throws IOException, ClassNotFoundException {
        dataSocket.receive(incomingPacket);

        byte[] data = incomingPacket.getData();

        ByteArrayInputStream input = new ByteArrayInputStream(data);
        ObjectInputStream objectInput = new ObjectInputStream(input);

        NetworkParams receivedObject = (NetworkParams) objectInput.readObject();
        nt.add(receivedObject);
        /*
         * System.out.println("Received from client: " + receivedObject);
         * System.out.println("Current state: " + receivedObject.getState());
         */
        receivedObject.setOnline();
        receivedObject.setNumClients(numClients);
        return receivedObject;
    }

    public boolean server(DatagramPacket incomingPacket) {
        try {
            System.out.println("--- SERVER ---");
            // DatagramPacket incomingPacket = new DatagramPacket(incomingData,
            // incomingData.length);

            /*
             * InetAddress clientAddress = incomingPacket.getAddress(); int clientPort =
             * incomingPacket.getPort();
             */
            NetworkParams receivedObject = receive(incomingPacket);
            if (readyClients < numClients) 
            {
                readyClients++;
                // System.out.println("Ready clients: " + readyClients + " Num clients: " +
                // numClients);
                receivedObject.setCanChange(false);
                // System.out.println("Ready < Client: " + receivedObject.getCanChange());
            }
            if (readyClients == numClients) 
            {
                receivedObject.setCanChange(true);
                // System.out.println("Ready == Client: " + receivedObject.getCanChange());
                readyClients--;
            }

            if (receivedObject.getCanChange() == true)
                receivedObject.setState(changeState(receivedObject.getState()));

            // System.out.println("New state: " + receivedObject.getState());

            send(receivedObject, incomingPacket);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        // System.out.println("Num clients " + numClients);
        return true;
    }

    public void send(NetworkParams receivedObject, DatagramPacket incomingPacket) throws IOException {
        /* Sending object to client */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(receivedObject);

        byte[] dataOut = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(dataOut, dataOut.length, incomingPacket.getAddress(),
                incomingPacket.getPort());

        dataSocket.send(sendPacket);
        // System.out.println("Send to client: " + dataOut);

    }

    public int changeState(int currentState) {
        int state = 0;
        // 1 - Green, 2 - Yellow, 3 - Red
        switch (currentState) {
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
        // System.out.println("Mudei o estado! Agora é: " + state);
        return state;
    }

    public static void printTrafficLightInfos(int id, int state) 
    {

        TrafficLightServerPanel serverGUI = serverWindow.getPanelServer();
        serverGUI.updateTrafficLightsInfos(id, state);
       
    }

}
