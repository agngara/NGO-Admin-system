/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import java.util.ArrayList;
import oru.inf.InfDB;
import java.util.HashMap;
import logicComponents.Validering;
import orgEntities.Hallbarhetsmål;
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
   
   public ProjektHanterare() {
       
       idb = DatabaseInterface.databaseConnection();
   }
   
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
      
      public String getPid() {
          return pid;
      }
              
   
 /**
 * Denna kod är avsedd för att få ut statistik över kostnader för alla projekt,
 * som handelaren är eller har varit projektansvarig för.
 * 
 */
   
 
 
      
     /**
 * Metoden nedan är avsedd för att lägga till ett projekt
 * metoden skapar ett pid genom att hitta max pid och sedan addera 1
 * 
 * 
 * 
 * 
 */ 
      
     
     
 
    
    
    public boolean laggTillProjekt(String projektnamn, String beskrivning, String startdatum, String slutdatum, String kostnad, String status, String prioritet, String projektchef, String land)
    {
        
          if (!Validering.tomFalt(projektnamn, "projektnamn") ||
             !Validering.tomFalt(beskrivning, "beskrivning") ||
             !Validering.giltigtDatum(startdatum) ||
             !Validering.giltigtDatum(slutdatum) ||
             !Validering.giltigDouble(kostnad) ||
             !Validering.tomFalt(status, "status") ||
             !Validering.tomFalt(prioritet, "prioritet") ||
             !Validering.tomFalt(projektchef, "Projektchef") ||
             !Validering.tomFalt(land, "Land")) {
            

           System.out.println("Du har glömt att fylla i ett eller fler fält. Projekt kan inte läggas till");
           return false;
            }
       
        try
        {
            String korrektPid = "SELECT MAX(pid) FROM projekt";
            String maxPid = idb.fetchSingle(korrektPid);
            
            int nyttPid = 1;
            if (maxPid != null) {
             nyttPid = Integer.parseInt(maxPid) + 1;
             
            }
            String laggTill = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land) " +
             "VALUES ('" + nyttPid + "', '" + projektnamn + "', '" + beskrivning + "', '" + startdatum + "', '" + slutdatum + "', '" + kostnad + "', '" +
             status + "', '" + prioritet + "', '" + projektchef + "', '" + land + "')";
            idb.insert(laggTill);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
    
    
    
     
    
   

/* The methods below aims to update the rows of a projekt, where the project in question is decided by the first parameter. 
    The new value in the database table is decided by the second parameter.
*/
 
 
  public boolean andraPid(String nyPid,String gammaltpid) {
      
   
      if (!Validering.tomFalt(gammaltpid, "gammal pid")) return false;
      if (!Validering.tomFalt(nyPid, "nyPid")) return false;


      
      try {
          
         
          
          
          String fraga = "UPDATE projekt SET pid = " + nyPid + " WHERE pid = " + gammaltpid;
          idb.update(fraga);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
          
      }
      
  }
    
  public boolean andraProjektnamn(String pid, String nyttNamn)
  {
      
      {
          if (!Validering.tomFalt(nyttNamn, "projektnamn")) {
          
          
           return false;
        }
  
      try{
          String namn = "UPDATE projekt " + "SET projektnamn = '" + nyttNamn + "' WHERE pid = " + pid;
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
              if (!Validering.tomFalt(pid, "pid")) return false;
          
         
             
              if (!Validering.tomFalt(nyBeskrivning, "beskrivning")) return false;  
                 
                 
              
                   
          
          
         
       
          String beskrivning = "UPDATE projekt " + "SET beskrivning = '" + nyBeskrivning + "' WHERE pid = " + pid;
          System.out.println("swl" + beskrivning);
      try {
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
      
       if (!Validering.tomFalt(nyttStartdatum, "startdatum")) return false; 

               
       if (!Validering.giltigtDatum(nyttStartdatum)) return false; 

          
         
         
        

      try {
          
          String startdatum = "UPDATE projekt SET startdatum = '" + nyttStartdatum + "' WHERE pid = " + pid;
          System.out.println(startdatum);
          idb.update(startdatum);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }   
     
 public boolean andraSlutdatum(String pid, String nyttSlutdatum)
  {
      
          if (!Validering.tomFalt(nyttSlutdatum, "slutdatum")) {
              return false;
          }
                  
          if (!Validering.giltigtDatum(nyttSlutdatum)){
          
           
           return false;
        }
      
      try{
          String slutdatum = "UPDATE projekt " + " SET slutdatum = '" + nyttSlutdatum + "' WHERE pid = " + pid;
          System.out.println(slutdatum);
          idb.update(slutdatum);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }     
 
  
 


 public boolean andraKostnad(String pid, String nyKostnad){
        {
          if(!Validering.giltigDouble(nyKostnad)) {
              
              return false; }
              
         if (!Validering.tomFalt(nyKostnad, "kostnad"))  {   
          
          
           return false;
        }
      
      try{
          String kostnad = "UPDATE projekt " + "SET kostnad = '" + nyKostnad + "' WHERE pid = " + pid;
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
            if (!Validering.tomFalt(nyStatus, "status")) {
          
           System.out.println("pid eller status får inte vara tom.");
           return false;
        }
      
      try{
          String status = "UPDATE projekt SET status = '" + nyStatus + "' WHERE pid = " + pid;
          idb.update(status);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }  

  }
 
 
  public boolean andraPrioritet(String pid, String nyPrioritet) {
      
      {
          if (!Validering.tomFalt(nyPrioritet, "prioritering")) {
          
           System.out.println("pid eller prioritet får inte vara tom.");
           return false;
        }
      
      try{
          String prioritet = "UPDATE projekt " + "SET prioritet = '" + nyPrioritet + "' WHERE pid = " + pid;
          idb.update(prioritet);
          return true;
      }
      
      catch (InfException e) {
          e.printStackTrace();
          return false;
      }
      
  }  
  
  
}

  
public boolean andraProjektchef(String pid, String nyProjektchef) {
    
    
    if (!Validering.tomFalt(nyProjektchef, "projektchef")) {
    return false;
}
 try {
        String projektchef =  "UPDATE projekt " + "SET projektchef = '" + nyProjektchef + "' WHERE pid = " + pid;
        idb.update(projektchef);
        return true;
        
    }
    
    catch (InfException e) {
        e.printStackTrace();
        return false;
        
    }
    
}

public boolean andraLand(String pid, int lid) {


    try {
        String land = "UPDATE projekt SET land = " + lid + " WHERE pid = " + pid;
        System.out.println(land);
        idb.update(land);
        return true;
    } 
    
    catch (InfException e) {
        e.printStackTrace();
        return false;
    }
}

    public ArrayList<HashMap<String, String>> fetchAllProjekt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/*
The methods below aim to remove information from the projekt-table. 
*/

//
//  public boolean taBortProjekt(Projekt p)
//{
//try {
//    
//    String pid = p.getPid();
//
//    if (!Validering.tomFalt(pid, pid)){
//    
//    System.out.println("Pid är tom");
//    return false; 
//    }
//    
//    String taBort = "DELETE FROM projekt WHERE pid = '" + p.getPid() + "'";
//    idb.delete(taBort);
//    System.out.println("Projekt borttaget: " + p.getProjektnamn());
//    return true;
//}
//
//        catch (InfException e) {
//
//        e.printStackTrace();
//        return false;
//}
//
//}
//  
    
 // metoden nedan är avsedd för att ta bort handläggare från ett projekt
  
//  public boolean taBortHandlaggare (String pid, String aid)
//  {
//      if (!Validering.tomFalt(pid, "pid") || !Validering.tomFalt(aid, "aid")) {
//          return false;
//      }
//      
//      try {
//          String taBort = "DELETE handlaggare FROM projekt WHERE pid = '" + pid + "' AND aid = '" + aid + "'AND EXISTS(SELECT 1 FROM handlaggare WHERE handlaggare.aid = anstalld.aid) '" + "'";
//          idb.delete(taBort);
//          return true;
//      }
//      
//      catch (InfException e)
//      {
//          e.printStackTrace();
//          return false;
//      }
//  }
//          
    // Metoden nedan är avsedd att lägga till uppgifter om vem som är projektansvarig
  
 
  
  




  

    
