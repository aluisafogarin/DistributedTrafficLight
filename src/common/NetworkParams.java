package common;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;

public class NetworkParams implements Serializable
{

    private static final long serialVersionUID = 1L;
    private boolean online;
    private int state;
    private boolean canChange;
    private int numClients;
    private int id;

    public NetworkParams()
    {
        this.online = false;
        this.state = 3;
        this.canChange = false;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
    
    public void setNumClients(int numClients)
    {
        this.numClients = numClients;
    }

    public int getNumClients()
    {
        return numClients;
    }

    public boolean getCanChange()
    {
        return canChange;
    }

    public boolean getStatus()
    {
        return online;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public void setOnline()
    {
        this.online = true;
    }

    public void setOffline()
    {
        this.online = false;
    }

    public void setCanChange(boolean e)
    {
        this.canChange = e;
    }
}
