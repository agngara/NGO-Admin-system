/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.HandlaggareHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Handlaggare 
{
    private String aid;
    private String ansvarighetsomraden; 
    private String mentor;
    
    
    
    public Handlaggare (HandlaggareHanterare handlaggareHanterare)
    {
        HashMap<String, String>handlaggare  = handlaggareHanterare.getHandlaggare();
        
        aid = handlaggare.get("aid");
        ansvarighetsomraden = handlaggare.get("ansvarighetsomrade");
        mentor = handlaggare.get("mentor");
        
        
    }
    
    public String getAnsvarighetsområden()
    {
        return ansvarighetsomraden;
    }
    
    public String getMentor() {
        
        return mentor;
    }
}
