/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;
import SQLHanterare.LandHanterare;
import java.util.HashMap;

/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 */
public class Land {
    
    private String lid;
    private String namn;
    private String sprak;
    private String valuta;
    private String tidszon;
    private String politiskStruktur;
    private String ekonomi;
    
    public Land (LandHanterare landHanterare)
    {
        HashMap<String, String> land = landHanterare.getLand();
        
        lid = land.get("lid");
        namn = land.get("namn");
        sprak = land.get("sprak");
        valuta = land.get("valuta");
        tidszon = land.get("tidszon");
        politiskStruktur = land.get("politiskStruktur");
        ekonomi = land.get("ekonomi");
        
    }
    
   
    
    public String getLid()
    {
        return lid;
    }
    
    public String getNamn()
    {
        return namn;
    }
    
  
    
    public String getSprak()
    {
        return sprak;
    }
    
    public String getValuta()
    {
       return valuta;
    }
    
    public String getTidszon()
    {
        return tidszon;
    }
    
    public String getPolitiskStruktur()
    {
        return politiskStruktur;
    }
    
    public String getEkonomi()
    {
        return ekonomi;
    }
    
}
