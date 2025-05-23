/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Projekt;
import oru.inf.InfException;

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
       query = "SELECT * FROM projekt WHERE pid = " + "'" + pid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           projekt = idb.fetchRow(query);
           
       } catch (InfException exception) {
           
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
        
        if (pid == null || projektnamn == null || beskrivning == null || startdatum == null || slutdatum == null || kostnad == null || status == null || prioritet == null || pid.isEmpty() || projektnamn.isEmpty() || beskrivning.isEmpty() || startdatum.isEmpty() ||  slutdatum.isEmpty() || kostnad.isEmpty() || status.isEmpty() || prioritet.isEmpty()) {
          
           System.out.println("Du har glömt att fylla i ett eller fler fält. Projekt kan inte läggas till");
           return false;
           
       }
        try
        {
            String laggTill = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet) VALUES ('" + pid + "', '" + projektnamn + "', '" + beskrivning + "', '" + startdatum + "', '" + slutdatum + "', '" + kostnad + "', '"  + status + "', '"  + prioritet + "')";
            idb.insert(laggTill);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }

// koderna nedan är avsedda för att ändra uppgifter om ett projekt  
    
  public boolean andraProjektnamn(String pid, String nyttNamn)
  {
      
      {
          if (pid == null || nyttNamn == null || pid.isEmpty() || nyttNamn.isEmpty()){
          
           System.out.println("pid eller namn får inte vara tom.");
           return false;
        }
  
      try{
          String namn = "UPDATE projekt SET namn = '" + nyttNamn + "'";
          idb.update(namn);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }
  }  
  
 public boolean andraBeskrivning(String pid, String nyBeskrivning)
  {
      
      {
          if (pid == null || nyBeskrivning == null || pid.isEmpty() || nyBeskrivning.isEmpty()){
          
           System.out.println("pid eller beskrivning får inte vara tom.");
           return false;
        }
      try{
          String beskrivning = "UPDATE projekt SET beskrivning = '" + nyBeskrivning + "'";
          idb.update(beskrivning);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }   
 
  }
 
 public boolean andraStartdatum(String pid, String nyttStartdatum)
  {
      
       {
          if (pid == null || nyttStartdatum == null || pid.isEmpty() || nyttStartdatum.isEmpty()){
          
           System.out.println("pid eller startdatum får inte vara tom.");
           return false;
        }
      try{
          String startdatum = "UPDATE projekt SET beskrivning = '" + nyttStartdatum + "'";
          idb.update(startdatum);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }   
  }   
 public boolean andraSlutdatum(String pid, String nyttSlutdatum)
  {
      {
          if (pid == null || nyttSlutdatum == null || pid.isEmpty() || nyttSlutdatum.isEmpty()){
          
           System.out.println("pid eller slutdatum får inte vara tom.");
           return false;
        }
      
      try{
          String slutdatum = "UPDATE projekt SET slutdatum = '" + nyttSlutdatum + "'";
          idb.update(slutdatum);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }     
 
  }
 
 
 public boolean andraKostnad(String pid, String nyKostnad){
        {
          if (pid == null || nyKostnad == null || pid.isEmpty() || nyKostnad.isEmpty()){
          
           System.out.println("pid eller kostnad får inte vara tom.");
           return false;
        }
      
      try{
          String kostnad = "UPDATE projekt SET kostnad = '" + nyKostnad + "'";
          idb.update(kostnad);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }  
  }
 
 
 public boolean andraStatus(String pid, String nyStatus)
  {
      {
          if (pid == null || nyStatus == null || pid.isEmpty() || nyStatus.isEmpty()){
          
           System.out.println("pid eller status får inte vara tom.");
           return false;
        }
      
      try{
          String status = "UPDATE projekt SET status = '" + nyStatus + "'";
          idb.update(status);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }  

  }
 
 
  public boolean andraPrioritet(String pid, String nyProritet) {
      
      {
          if (pid == null || nyProritet == null || pid.isEmpty() || nyProritet.isEmpty()){
          
           System.out.println("pid eller prioritet får inte vara tom.");
           return false;
        }
      
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
  
  
}







// Denna kod är avsedd för att ta bort ett projekt
  
  public boolean taBortProjekt(Projekt p)
{
try {
    
    String pid = p.getPid();

    if (pid == null || pid.isEmpty()) {
    
    System.out.println("Pid är tom");
    return false; 
    }
    
    String taBort = "DELETE FROM projekt WHERE pid = '" + p.getPid() + "'";
    idb.update(taBort);
    System.out.println("Projekt borttaget: " + p.getProjektnamn());
    return true;
}

        catch (InfException e) {

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
      
      catch (InfException e)
      {
          e.printStackTrace();
          return false;
      }
  }
          
    
}
