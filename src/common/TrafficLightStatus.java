package common;

import java.io.Serializable;

public class TrafficLightStatus implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String status;

    public TrafficLightStatus()
    {
        this.status = "RED";
    }

    public void changeStatus(String currentStatus)
    {
        switch (currentStatus)
        {
            case "RED":
                this.status = "GREEN";
                break;
            case "YELLOW":
                this.status = "RED";
                break;
            case "GREEN":
                this.status = "YELLOW";
                break;
            default:
                this.status = "RED";
                break;
        }
    }

    public String getStatus()
    {
        return status;
    }
}
