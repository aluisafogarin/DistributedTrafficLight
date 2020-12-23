package common;

 public enum LanguagePattern 
 {
    ENGLISH("Traffic Light Controler", "Traffic Light", "Traffic Lights",
            "December, 2020","File", "Disclaimer", "Exit", "Help", "About", 
            "No traffic light started", "State", "Green", "Yellow", "Red"),
    PORTUGUESE("Controlador de Semáforos", "Semáforo", "Semáforos", 
            "Dezembro, 2020" ,"Início", "Avisos Legais", "Sair", "Ajuda", "Sobre", 
            "Nenhum semáforo iniciado", "Estado", "Verde", "Amarelo", "Vermelho");

    private String sysNameServer;
    private String sysNameClient;
    private String title;
    private String date;
    private String file;
    private String disclaimer;
    private String exit;
    private String help;
    private String about;
    private String baseMsg;
    private String state;
    private String green;
    private String yellow;
    private String red;


    private LanguagePattern (
        String sysNameServer, String sysNameClient, String title, String date, String file, 
        String disclaimer, String exit, String help, String about, String baseMsg, String state,
        String green, String yellow, String red) 
    {
        this.sysNameServer = sysNameServer;
        this.sysNameClient = sysNameClient;
        this.title = title;
        this.date = date;
		this.file = file;
		this.disclaimer = disclaimer;
		this.exit = exit;
		this.help = help;
        this.about = about;
        this.baseMsg = baseMsg;
        this.state = state;
        this.green = green;
        this.yellow = yellow;
        this.red = red;
    }

    public String getRed()
    {
        return red;
    }

    public String getGreen()
    {
        return green;
    }

    public String getYellow()
    {
        return yellow;
    }

    public String getSysNameServer()
    {
        return sysNameServer;
    }

    public String getSysNameClient()
    {
        return sysNameClient;
    }
    
    public String getState()
    {
        return state;
    }

    public String getFile()
    {
        return file;
    }

    public String getDisclaimer() 
    {
        return disclaimer;
    }

    public String getExit() 
    {
        return exit;
    }

    public String getHelp() 
    {
        return help;
    }

    public String getAbout() 
    {
        return about;
    }

    public String getDate() 
    {
        return date;
    }

    public String getTitle()
    {
        return title;
    }

    public String getBaseMsg()
    {
        return baseMsg;
    }
 }
 
