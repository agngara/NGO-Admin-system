/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.HallbarhetsmålHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Hallbarhetsmål {
    
    private String hid;
    private String namn;
    private String malnummer;
    private String beskrivning;
    private String prioritet;
    
    
    public Hallbarhetsmål (HallbarhetsmålHanterare hallbarhetsmålHanterare)
    {
        HashMap<String, String> hallbarhetsmal = hallbarhetsmålHanterare.getHallbarhetsmal();
        
        hid = hallbarhetsmal.get("hid");
        namn = hallbarhetsmal.get("namn");
        malnummer = hallbarhetsmal.get("malnummer");
        beskrivning = hallbarhetsmal.get("beskrivning");
        prioritet = hallbarhetsmal.get("prioritet");
    }
    
    
    public String getHid()
    {
        return hid;
    }
    
    public String getNamn()
    {
        return namn;
    }
    
    public String getMalnummer()
    {
        return malnummer;
    }
    
    public String getBeskrivning()
    {
        return beskrivning;
    }
    
    public String getPrioritet()
    {
        return prioritet;
    }
    
    
}
