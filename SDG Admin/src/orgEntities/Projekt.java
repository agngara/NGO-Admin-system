/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import java.util.HashMap;

/**
 *
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
    
    
    
    
    public Projekt (HashMap<String, String> projekt)
    {
        pid = projekt.get("pid");
        projektnamn = projekt.get("projektnamn");
        beskrivning = projekt.get("beskrivning");
        startdatum = projekt.get("startdatum");
        slutdatum = projekt.get("slutdatum");
        kostnad = projekt.get("kostnad");
        status = projekt.get("status");
        prioritet = projekt.get("prioritet");
        
    }
}
