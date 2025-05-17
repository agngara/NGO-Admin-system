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
public class Handlaggare 
{
    
    private String ansvarighetsomraden; 
    
    
    
    public Handlaggare (HashMap<String, String> handlaggare)
    {
        
        ansvarighetsomraden = handlaggare.get(ansvarighetsomraden);
        
        
    }
    
    public String getAnsvarighetsområden()
    {
        return ansvarighetsomraden;
    }
}
