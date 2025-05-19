/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.AdminHanterare;
import java.util.HashMap;


/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Admin {
    
    private String aid;
    private String behorighetsniva;

    
     public void Admin (AdminHanterare adminHanterare)
    {
        
        HashMap<String, String> admin = adminHanterare.getAdmin();
        
        aid = admin.get("aid");
        behorighetsniva = admin.get("behorighetsnivå");
        
    }
    
    
    public String getAid()
    {
        return aid;
    }
    
    public String getBehorighetsniva()
    {
        return behorighetsniva;
    }
    
}
    
    
    