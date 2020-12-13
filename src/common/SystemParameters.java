package common;

public class SystemParameters 
{
    private final static String Hostname = "localhost";
    private final static int Port = 17;
    private final static int Time = 3000;

    public static String getHostname() {
        return Hostname;
    }

    public static int getPort() {
        return Port;
    }

    public static int getTime()
    {
        return Time;
    }
}
