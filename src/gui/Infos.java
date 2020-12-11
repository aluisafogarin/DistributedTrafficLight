package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Infos 
{
    public static String sysName = "Traffic Light Controller";
    public static String version = "1.0";
    public static String date = "December, 2020";
    public static String shortVersion = sysName + " - " + date;
    public static String longVersion = sysName + " - " + version + date;

    //private static final String ResFolder      = "/resources/";
    public static final String Authors = 
        "Ana Luisa Fogarin, Larissa Correia, Vinicius e Verussa de Alencar";
   /*  public static final String HelpFile       = "Ajuda.txt";*/
    public static final String DisclaimerFile = "Direitos.txt"; 
    public static final String Sep = System.getProperty("file.separator");
    public static final String Path = System.getProperty("user.dir") + Sep + "TrafficLight" + Sep 
    + "src" + "/resources/";

    public static String getAbout()
    {
        StringBuilder aboutText = new StringBuilder();

        aboutText.append("\n");
        aboutText.append(sysName + "\n");
        aboutText.append(date + "- ");
        aboutText.append(version + "\n");
        aboutText.append(Authors + "\n" + "\n");

        return aboutText.toString();
    }

    public static String getDisclaimerText()
    {
        return (getTextFromFile(DisclaimerFile));
    }

    public static String getHelpText(String file)
    {
        return (getTextFromFile(file));
    }

    public static String getTextFromFile(String fileName)
    {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(Path + fileName)))
        {
            String buffer = "";

            while ((buffer = reader.readLine()) != null) 
            {
                fileText.append((buffer + "\n"));
            }
        }
        catch (NullPointerException e)
        {
            System.out.println(getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
        }

        return fileText.toString();
    }

    public static String getShorVersion()
    {
        return shortVersion;
    }

    public static String getLongVersion()
    {
        return longVersion;
    }
}
