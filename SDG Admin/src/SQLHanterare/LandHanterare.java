/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Land;
import java.util.ArrayList;
import logicComponents.Validering;
import oru.inf.InfException;
/**
 *
 * @author theow
 */
public class LandHanterare {
    
   private InfDB idb;
   private HashMap<String, String> land;
   private String query;
   private String lid;
   
   public LandHanterare() {
       
       idb = DatabaseInterface.databaseConnection();

       
       
   }
   
   public LandHanterare(String lid) {
       
       this.lid = lid;
       query = "SELECT * FROM land WHERE lid = " + "'" + lid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           land = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
      public HashMap getLand() {
       
       return land;
   }
   
      public ArrayList fetchAllLand() 
      {
          ArrayList<HashMap<String,String>> lander = new ArrayList<>();
          
          String query = "SELECT * FROM land";
          
          try {
          lander = idb.fetchRows(query);
          }
          catch (InfException e) {
              
          }
          
          return lander;
      }
    
    
    public boolean laggTillLand(String lid, String namn, String sprak, String valuta, String tidszon, String politiskStruktur, String ekonomi)
    {       
            if (!Validering.tomFalt(lid, "Land-Id") &&
                Validering.tomFalt(namn, "Namn")   &&
                Validering.tomFalt(sprak, "Språk") &&
                Validering.tomFalt(valuta, "Valuta") &&
                Validering.tomFalt(tidszon, "Tidszon") &&
                Validering.tomFalt(politiskStruktur, "PolitiskStruktur") &&
                Validering.tomFalt(ekonomi, "Ekonomi")) {
                return false; 
    }
             try {
            String uppdateradeUppgifter = "INSERT INTO land (namn, sprak, valuta, tidszon, politisk_struktur, ekonomi) VALUES ('" + namn + "', '" + sprak + "', '" + valuta + "', '" + tidszon + "', '" + politiskStruktur + "', '" + ekonomi + "')";
            idb.insert(uppdateradeUppgifter);
            return true;
                
        }
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
    }    
    
    
    
    
    public boolean andraLid (String nyLid) {
        if (!Validering.tomFalt(nyLid, "land-id")) {
            return false;
        }
       try {
           String landNy = "UPDATE land SET lid = '" + nyLid + "' WHERE lid = '" + lid + "'"; 
           idb.update(landNy);
           return true;
       }
       
       catch (InfException e) {
           e.printStackTrace();
           return false;
       }
    }
    
    
    public boolean andraNamn (String nyttNamn) {
        if (!Validering.tomFalt(nyttNamn, "namn")) {
            return false;
        }
       try {
           String namn = "UPDATE land SET namn = '" + nyttNamn + "' WHERE lid = '" + lid + "'"; 
           idb.update(namn);
           return true;
       }
       
       catch (InfException e) {
           e.printStackTrace();
           return false;
       }
    }
    
    public boolean andraSprak(String nyttSprak) {
        if (!Validering.tomFalt(nyttSprak, "Språk")) {
            
            return false;
        }
        
        try {
            String sprak = "UPDATE land SET sprak = '" + nyttSprak + "'WHERE lid = '" + lid + "'";
            idb.update(sprak);
            return true;
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
//    public boolean andraValuta(String nyValuta) {
//     
////  if(!Validering.giltigDouble(nyValuta, "Valuta") && Validering.tomFalt(nyValuta, "Valuta")) {
//            return false;
//        }
//        
//        try {
//            String valuta = "UPDATE land SET valuta = '" + nyValuta + "'WHERE lid = '" + lid + "'";
//            idb.update(valuta);
//            return true;
//            
//        }
//        
//        catch (InfException e) {
//            e.printStackTrace();
//            return false; 
//        }
//    }
    
    
    
    public boolean andraTidszon(String nyTidszon) {
        if(!Validering.tomFalt(nyTidszon, "Tidszon")) {
            return false;
        }
        
        try {
            String tidszon = "UPDATE land SET tidszon = '" + nyTidszon + "'WHERE lid = '" + lid + "'";
            idb.update(tidszon);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    public boolean andraPolitiskStruktur(String nyPolitiskStruktur) {
        
        if(!Validering.tomFalt(nyPolitiskStruktur, "Politisksturktur")) {
            return false;
        }
        
        try {
            String politiskStruktur = "UPDATE land SET politisk_struktur = '" + nyPolitiskStruktur + "'WHERE lid = '" + lid + "'";
            idb.update(politiskStruktur);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    
   public boolean andraEkonomi(String nyEkonomi) {
        
        if(!Validering.tomFalt(nyEkonomi, "Ekonomi")) {
            return false;
        }
        
        try {
            String ekonomi = "UPDATE land SET ekonomi = '" + nyEkonomi + "'WHERE lid = '" + lid + "'";
            idb.update(ekonomi);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
}
