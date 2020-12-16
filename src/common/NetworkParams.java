package common;

public class NetworkParams 
{
    private boolean status;
    private int state;

    public NetworkParams(boolean status, int state)
    {
        this.status = status;
        this.state = state;
    }

    public boolean getStatus()
    {
        return status;
    }

    public int getState()
    {
        return state;
    }
}
