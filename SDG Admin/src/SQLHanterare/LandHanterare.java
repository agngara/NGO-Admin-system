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
import javax.swing.JOptionPane;
import logicComponents.Validering;
import oru.inf.InfException;
/**
 * Denna klass ansvarar för att hantera funktionalitet kopplad till 
 * land i systemet.
 * Klassen sökter kontakten med sql och metoder är tillgängliga här för att
 * lägga till land, ändra uppgifter om land samt ta bort land. 
 * Dessa metoder används sedan i klassen lander, editLand och läggTillLand. 
 * 
 * 
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
    
    
    public boolean laggTillLand(String namn, String sprak, String valuta, String tidszon, String politiskStruktur, String ekonomi)
    {       
            if (!Validering.tomFalt(namn, "Namn")   ||
                !Validering.tomFalt(sprak, "Språk") ||
                !Validering.tomFalt(valuta, "Valuta") ||
                !Validering.tomFalt(tidszon, "Tidszon") ||
                !Validering.tomFalt(politiskStruktur, "PolitiskStruktur") ||
                !Validering.tomFalt(ekonomi, "Ekonomi")) {
                return false; 
    }
             try {
             // genererat nytt lid  
             String korrektLid = "SELECT MAX(lid) FROM land";
             String maxLid = idb.fetchSingle(korrektLid);
             
             int nyttLid = 1;
             if (maxLid != null) {
                  nyttLid = Integer.parseInt(maxLid) + 1; 
            }
            
                 
                 
                                            
            String uppdateradeUppgifter = "INSERT INTO land (lid, namn, sprak, valuta, tidszon, politisk_struktur, ekonomi) " + "VALUES ('" + nyttLid + "', '" + namn + "', '" + sprak + "', '" + valuta + "', '" + tidszon + "', '" + politiskStruktur + "', '" + ekonomi + "')";
            idb.insert(uppdateradeUppgifter);
            return true;
                
        }
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
    }    
    
    
    
    
    public boolean andraLid (String nyLid, String gammalLid) {
        if (!Validering.tomFalt(nyLid, "land-id")) {
            return false;
        }
       try {
           String landNy = "UPDATE land SET lid = " + nyLid + " WHERE lid = " + gammalLid; 
           idb.update(landNy);
           return true;
       }
       
       catch (InfException e) {
           e.printStackTrace();
           return false;
       }
    }
    
    
    public boolean andraNamn (String lid, String nyttNamn) {
        if (!Validering.tomFalt(nyttNamn, "namn")) {
            return false;
        }
       try {
           String namn = "UPDATE land SET namn = '" + nyttNamn + "' WHERE lid = " + lid;  
           idb.update(namn);
           return true;
       }
       
       catch (InfException e) {
           e.printStackTrace();
           return false;
       }
    }
    
    public boolean andraSprak(String lid, String nyttSprak) {
        if (!Validering.tomFalt(nyttSprak, "Språk")) {
            
            return false;
        }
        
        try {
            String sprak = "UPDATE land SET sprak = '" + nyttSprak + "' WHERE lid = " + lid; 
            idb.update(sprak);
            return true;
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean andraValuta(String lid, String nyValuta) {
     
  if(!Validering.tomFalt(lid, "lid") || !Validering.giltigDouble(nyValuta)) {
            return false;
        }
        
        try {
            String valuta = "UPDATE land SET valuta = '" + nyValuta + "' WHERE lid = " + lid;
            idb.update(valuta);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    
    
    public boolean andraTidszon(String lid, String nyTidszon) {
        if(!Validering.tomFalt(nyTidszon, "Tidszon")) {
            return false;
        }
        
        try {
            String tidszon = "UPDATE land SET tidszon = '" + nyTidszon + "' WHERE lid = " + lid;
            idb.update(tidszon);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    public boolean andraPolitiskStruktur(String lid, String nyPolitiskStruktur) {
        
        if(!Validering.tomFalt(nyPolitiskStruktur, "Politisksturktur")) {
            return false;
        }
        
        try {
            String politiskStruktur = "UPDATE land SET tidszon = '" + nyPolitiskStruktur + "' WHERE lid = " + lid;
            idb.update(politiskStruktur);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    
   public boolean andraEkonomi(String lid, String nyEkonomi) {
        
        if(!Validering.tomFalt(nyEkonomi, "Ekonomi")) {
            return false;
        }
        
        try {
            String ekonomi = "UPDATE land SET ekonomi = '" + nyEkonomi + "' WHERE lid = " + lid;
            idb.update(ekonomi);
            return true;
            
        }
        
        catch (InfException e) {
            e.printStackTrace();
            return false; 
        }
    }
   
      public int getLIDbyNamn(String landnamn) {
       
       int lid = 0;
       try {
           String query = "SELECT lid FROM land WHERE namn = " + "'" + landnamn + "'";       
           String response = idb.fetchSingle(query);
           lid = Integer.parseInt(response);
       } catch (InfException e) 
               {
                   e.printStackTrace();
                   System.out.println(e);
               }
       
       return lid;
   }

    public boolean taBortLand(String lid) {
         
        if (!Validering.tomFalt(lid, "landID")) {
        return false;
    }
        try { 
       // ta bort från alla tabeller där aid finns som nyckel.
       
       String taBortProjekt = "DELETE FROM projekt WHERE land = '" + lid + "'";
       idb.delete(taBortProjekt);
       String taBortStad = "DELETE FROM stad WHERE land = '" + lid + "'";
       idb.delete(taBortStad);
       String taBort = "DELETE FROM land WHERE lid = '" + lid + "'";
       idb.delete(taBort);
    
       return true;    
      }
       catch (InfException e) {
    
       e.printStackTrace();
    
    return false;
    
    }
    }

}
   

