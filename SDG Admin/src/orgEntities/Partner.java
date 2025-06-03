/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;

import SQLHanterare.AnstalldHanterare;
import SQLHanterare.PartnerHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
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
    
    private PartnerHanterare partnerHanterare;
    private String partner;
    
    public Partner() {}
    
    
    
    
    public Partner(PartnerHanterare partnerHanterare)
    {
        HashMap<String, String> partner = partnerHanterare.getPartner();
        
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
    
    public String getBranch()
    {
        return branch;
    }
    
    public String getStad()
    {
        return stad; 
    }
    
    
}
