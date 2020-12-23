package common;

/**
 * Class that define common system parameters.
 */
public class SystemParameters 
{
    private final static String Hostname = "localhost";
    private final static int Port = 17;
    private final static int Time = 3000;

    
    /** 
     * Get hostname.
     * @return String hostname.
     */
    public static String getHostname() {
        return Hostname;
    }

    
    /** 
     * Get port;
     * @return int port;
     */
    public static int getPort() {
        return Port;
    }

    
    /** 
     * Get time.
     * @return int time.
     */
    public static int getTime()
    {
        return Time;
    }
}
