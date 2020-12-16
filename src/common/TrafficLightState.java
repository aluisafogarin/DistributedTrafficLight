package common;

import java.io.Serializable;

public class TrafficLightState implements Serializable
{

    private static final long serialVersionUID = 1L;
    private int state;
    private boolean status;

    public TrafficLightState()
    {
        this.state = 3;
    }

    public void changeState(int currentStatus)
    {
        System.out.println("Current state: " + currentStatus);
        switch (currentStatus)
        {
            case 3:
                this.state = 1;
                break;
            case 2:
                this.state = 3;
                break;
            case 1:
                this.state = 2;
                break;
            default:
                this.state = 3;
                break;
        }
        System.out.println("new state: " + state);
    }

    public int getState()
    {
        return state;
    }

    public boolean getStatus()
    {
        return status;
    }
    
    public void setOnline()
    {
        status = true;
    }

    public void setOffline()
    {
        status = false;
    }
}
