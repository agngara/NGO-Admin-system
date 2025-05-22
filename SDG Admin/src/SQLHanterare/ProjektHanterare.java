/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Projekt;

/**
 *
 * @author theow
 */
public class ProjektHanterare {
    
   private InfDB idb;
   private HashMap<String, String> projekt;
   private String query;
   private String pid;
   
   public ProjektHanterare(String pid) {
       
       this.pid = pid;
       query = "SELECT * FROM anstalld WHERE pid = " + "'" + pid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           projekt = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
      public HashMap getProjekt() {
       
       return projekt;
   }
   
 /**
 * Denna kod är avsedd för att få ut statistik över kostnader för alla projekt,
 * som handelaren är eller har varit projektansvarig för.
 * 
 */
   
 
 
      
      
      
     
     
 // koden nedan är avsedd för att kunna lägga till ett projekt. 
    
    
    public boolean laggTillProjekt(String pid, String projektnamn, String beskrivning, String startdatum, String slutdatum, String kostnad, String status, String prioritet)
    {
        
        try
        {
            String laggTill = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet) VALUES ('" + pid + "', '" + projektnamn + "', '" + beskrivning + "', '" + startdatum + "', '" + slutdatum + "', '" + kostnad + "', '"  + status + "', '"  + prioritet + "')";
            idb.insert(laggTill);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }

// koderna nedan är avsedda för att ändra uppgifter om ett projekt  
    
  public boolean andraProjektnamn(String pid, String nyttNamn)
  {
      try{
          String namn = "UPDATE projekt SET namn = '" + nyttNamn + "'";
          idb.update(namn);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }
    
 public boolean andraBeskrivning(String pid, String nyBeskrivning)
  {
      try{
          String beskrivning = "UPDATE projekt SET beskrivning = '" + nyBeskrivning + "'";
          idb.update(beskrivning);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }   
 
 
 
 public boolean andraStartdatum(String pid, String nyttStartdatum)
  {
      try{
          String startdatum = "UPDATE projekt SET beskrivning = '" + nyttStartdatum + "'";
          idb.update(startdatum);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }   
    
 public boolean andraSlutdatum(String pid, String nyttSlutdatum)
  {
      try{
          String slutdatum = "UPDATE projekt SET slutdatum = '" + nyttSlutdatum + "'";
          idb.update(slutdatum);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }     
 
 
 
 public boolean andraKostnad(String pid, String nyKostnad)
  {
      try{
          String kostnad = "UPDATE projekt SET kostnad = '" + nyKostnad + "'";
          idb.update(kostnad);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }  
 
 
 public boolean andraStatus(String pid, String nyStatus)
  {
      try{
          String status = "UPDATE projekt SET status = '" + nyStatus + "'";
          idb.update(status);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }  

 
 
 
  public boolean andraPrioritet(String pid, String nyProritet)
  {
      try{
          String prioritet = "UPDATE projekt SET prioritet = '" + nyProritet + "'";
          idb.update(prioritet);
          return true;
      }
      
      catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      
  }  
  
  








// Denna kod är avsedd för att ta bort ett projekt
  
  public boolean taBortProjekt(Projekt p)
{
try {
    String taBort = "DELETE FROM projekt WHERE pid = '" + p.getPid() + "'";
    idb.update(taBort);
    return true;
}

    catch (Exception e){

        e.printStackTrace();
        return false;
}

}
    
 // metoden nedan är avsedd för att ta bort handläggare från ett projekt
  
  public boolean taBortHandlaggare (String pid, String aid)
  {
      try {
          String taBort = "DELETE handlaggare FROM projekt WHERE pid = '" + pid + "' AND aid = '" + aid + "'";
          idb.delete(taBort);
          return true;
      }
      
      catch (Exception e)
      {
          e.printStackTrace();
          return false;
      }
  }
          
    
}
