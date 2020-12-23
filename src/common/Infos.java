package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manages infos that are displayed on GUI.
 */
public class Infos 
{
    public static String sysServerName;
    public static String sysClientName;
    public static String version = "1.0";
    public static String date;
    public static String shortVersion;
    public static String longVersion;
    public static LanguagePattern languageInfos;

    public static final String Authors = "Ana Luisa Fogarin, Larissa Correia, Vinicius e Verussa de Alencar";
    public static final String DisclaimerFile = "Direitos.txt";
    public static final String Sep = System.getProperty("file.separator");
    public static final String Path = System.getProperty("user.dir") + Sep + "TrafficLight" + Sep + "src"
            + "/resources/";

    public Infos() 
    {
        languageInfos = defineLanguage();
        this.sysServerName = LanguagePattern.PORTUGUESE.getSysNameServer();
        this.sysClientName = LanguagePattern.PORTUGUESE.getSysNameClient();
        this.date = LanguagePattern.PORTUGUESE.getDate();
        longVersion = sysServerName + " - " + version + date;
        shortVersion = sysClientName + " - " + date;
    }
    
    /**
     * Get about text.
     * @return String about text. 
     */
    public static String getAbout() {
        StringBuilder aboutText = new StringBuilder();

        aboutText.append("\n");
        aboutText.append(sysClientName + "\n");
        aboutText.append(date + "- ");
        aboutText.append(version + "\n");
        aboutText.append(Authors + "\n" + "\n");
        return aboutText.toString();
    }

        
    /** 
     * Define what is the language that will be used and which file.
     * @return String Language file.
     */
    public LanguagePattern defineLanguage() {
        String language = System.getProperty("user.language");
        String languageFile = "";

        if (language.equals("pt")) 
        {
            languageInfos = LanguagePattern.PORTUGUESE;
        } else 
        {
            languageInfos = LanguagePattern.ENGLISH;
        }
        return languageInfos;
    }

    
    /** 
     * Get disclaimer text from file.
     * @return String Disclaimer text.
     */
    public static String getDisclaimerText() {
        return (getTextFromFile(DisclaimerFile));
    }

    
    /** 
     * Get help text from file.
     * @param file Help File.
     * @return String Help text.
     */
    public static String getHelpText(String file) {
        return (getTextFromFile(file));
    }

    
    /** 
     * Get text from file.
     * @param fileName name of the file.
     * @return String text from the file.
     */
    public static String getTextFromFile(String fileName) {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(Path + fileName))) {
            String buffer = "";

            while ((buffer = reader.readLine()) != null) {
                fileText.append((buffer + "\n"));
            }
        } catch (NullPointerException e) {
            System.out.println(getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println(getLongVersion() + "\nError in loading file " + fileName + "\n" + e.getMessage());
        }

        return fileText.toString();
    }

    
    /** 
     * Get short version
     * @return String shortversion
     */
    public static String getShorVersion() {
        return shortVersion;
    }

    
    /** 
     * Get long version
     * @return String longversion
     */
    public static String getLongVersion() {
        return longVersion;
    }
}
