package common;

import java.io.Serializable;

public class NetworkParams implements Serializable
{

    private static final long serialVersionUID = 1L;
    private boolean online;
    private int state;
    private boolean canChange;

    public NetworkParams()
    {
        this.online = false;
        this.state = 3;
        this.canChange = false;
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
