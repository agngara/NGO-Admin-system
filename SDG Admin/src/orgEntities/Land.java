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
public class Land {
    
    private String lid;
    private String namn;
    private String sprak;
    private String valuta;
    private String tidszon;
    private String politiskStruktur;
    private String ekonomi;
    
    public Land (HashMap<String, String> land)
    {
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
