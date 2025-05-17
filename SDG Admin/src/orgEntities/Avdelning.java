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
public class Avdelning {
    
    private String avdid;
    private String namn;
    private String beskrivning;
    private String adress;
    private String ePost;
    private String telefon;
    
    
    
    public Avdelning (HashMap<String, String> avdelning)
    {
        avdid = avdelning.get("avdid");
        namn = avdelning.get("namn");
        beskrivning = avdelning.get("beskrivning");
        adress = avdelning.get("adress");
        ePost = avdelning.get("ePost");
        telefon = avdelning.get("telefon");
    }
    
    
    public String getAvdid()
    {
        return avdid;
    }
    
    public String getNamn()
    {
        return namn;
    }
    
    
    public String getBeskrivning()
    {
        return beskrivning;
    }
    
    public String getAdress()
    {
        return adress;
    }
    
    public String getEpost()
    {
        return ePost;
    }
    
    public String getTelefon()
    {
        return telefon;
    }
    
}
