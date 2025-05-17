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
public class Hallbarhetsmål {
    
    private String hid;
    private String namn;
    private String malnummer;
    private String beskrivning;
    private String prioritet;
    
    
    public Hallbarhetsmål (HashMap<String, String> hallbarhetsmal)
    {
        hid = hallbarhetsmal.get("hid");
        namn = hallbarhetsmal.get("namn");
        malnummer = hallbarhetsmal.get("malnummer");
        beskrivning = hallbarhetsmal.get("beskrivning");
        prioritet = hallbarhetsmal.get("prioritet");
    }
    
    
    public String gethid()
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
