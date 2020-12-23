package common;

import java.io.Serializable;

/**
 * Class whose object is send to/from client and server.
 */
public class NetworkParams implements Serializable
{

    private static final long serialVersionUID = 1L;
    private boolean online;
    private int state;
    private boolean canChange;
    private int numClients;
    private int id;

    /**
     * Class constructor.
     */
    public NetworkParams()
    {
        this.online = false;
        this.state = 3;
        this.canChange = false;
    }

    /**
     * Sets ID of traffic light.
     * @param id Traffic light id.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    
    /** 
     * Get id of traffic light.
     * @return int id traffic light.
     */
    public int getId()
    {
        return id;
    }
    
    
    /** 
     * Set number of clients online.
     * @param numClients number of clients.
     */
    public void setNumClients(int numClients)
    {
        this.numClients = numClients;
    }

    
    /** 
     * Get number of clients.
     * @return int number of clients.
     */
    public int getNumClients()
    {
        return numClients;
    }

    
    /** 
     * Get can change.
     * @return boolean that says if light can change or not.
     */
    public boolean getCanChange()
    {
        return canChange;
    }

    
    /** 
     * Get can change.
     * @return boolean that says if light can change or not.
     */
    public boolean getStatus()
    {
        return online;
    }

    
    /** 
     * Get state of traffic light. 
     * @return int state of traffic light. 1 - Green, 2 - Yellow, 3 - Red.
     */
    public int getState()
    {
        return state;
    }

    
    /** 
     * Set state of traffic light.
     * @param state int state of traffic light. 1 - Green, 2 - Yellow, 3 - Red.
     */
    public void setState(int state)
    {
        this.state = state;
    }

    /**
     * Set traffic light online.
     */
    public void setOnline()
    {
        this.online = true;
    }

    /**
     * Set traffic light offline.
     */
    public void setOffline()
    {
        this.online = false;
    }

    
    /** 
     * Set can change.
     * @param e true or false.
     */
    public void setCanChange(boolean e)
    {
        this.canChange = e;
    }
}
