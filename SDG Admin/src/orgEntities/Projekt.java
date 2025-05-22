/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.ProjektHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Projekt {
    
    private String pid;
    private String projektnamn;
    private String beskrivning;
    private String startdatum;
    private String slutdatum;
    private String kostnad;
    private String status;
    private String prioritet;
    private String projektchef;
    
    
    
    
    public Projekt (ProjektHanterare projektHanterare)
    {
        HashMap<String, String> projekt = projektHanterare.getProjekt();
        
        pid = projekt.get("pid");
        projektnamn = projekt.get("projektnamn");
        beskrivning = projekt.get("beskrivning");
        startdatum = projekt.get("startdatum");
        slutdatum = projekt.get("slutdatum");
        kostnad = projekt.get("kostnad");
        status = projekt.get("status");
        prioritet = projekt.get("prioritet");
        projektchef = projekt.get("projektchef");
        
    }
    
    
    public String getPid()
    {
        return pid;
    }
    
    public String getProjektnamn()
    {
        return projektnamn;
    }
    
    public String getBeskrivning()
    {
        return beskrivning;
    }
    
    public String getStartdatum()
    {
        return startdatum;
    }
    
    public String getSlutdatum()
    {
        return slutdatum;
    }
    
    public String getKostnad()
    {
        return kostnad;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public String getPrioritet()
    {
        return prioritet;
    }
    
    public String getProjektchef()
    {
        return projektchef;
    }
    

}
