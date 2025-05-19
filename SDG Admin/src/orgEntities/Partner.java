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
public class Partner {
    
    private String pid;
    private String namn;
    private String kontaktperson;
    private String kontaktepost;
    private String telefon;
    private String adress;
    private String branch;
    private String stad;
    
    
    public Partner(HashMap<String, String> partner)
    {
        pid = partner.get("pid");
        namn = partner.get("namn");
        kontaktperson = partner.get("kontaktperson");
        kontaktepost = partner.get("kontaktepost");
        telefon = partner.get("telefon");
        adress = partner.get("adress");
        branch = partner.get("branch");
        stad = partner.get("stad");
        
    }
    
    
    public String getPid()
    {
        return pid;
    }
    
    public String getNamn()
    {
        return namn;
    }
    
    public String getKostaktperson()
    {
        return kontaktperson;
    }
    
    public String getKontaktepost()
    {
        return kontaktepost;
    }
    
    public String getTelefon()
    {
        return telefon;
    }
    
    public String getAdress()
    {
        return adress;
    }
    
    public String getBenach()
    {
        return branch;
    }
    
    public String getStad()
    {
        return stad; 
    }
    
    
}
