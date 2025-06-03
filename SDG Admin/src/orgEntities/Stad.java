/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.StadHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Stad {
    
    private String sid;
    private String namn;
    
    
    public Stad(StadHanterare stadHanterare)
    {
        
        HashMap<String, String> stad = stadHanterare.getStad();
        
        sid = stad.get("sid");
        namn = stad.get("stad");
    }
    
    
    public String getSid()
    {
        return sid;
    }
    
    public String getNamn()
    {
        return namn; 
    }
    
}
