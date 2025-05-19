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
public class Stad {
    
    private String sid;
    private String namn;
    
    
    public Stad(HashMap<String, String> stad)
    {
        
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
