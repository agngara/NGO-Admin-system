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
import orgEntities.Partner;
import oru.inf.InfException;



/**
 *
 * @author theow
 */
public class PartnerHanterare {
    
   private InfDB idb;
   private HashMap<String, String> partner;
   private String query;
   private String pid;
   
   
   public PartnerHanterare() {
       
       idb = DatabaseInterface.databaseConnection();
       
   }
   
   public PartnerHanterare(String pid) {
       
       this.pid = pid;
       query = "SELECT * FROM partner WHERE pid = " + "'" + pid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           partner = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
      public HashMap getPartner() {
       
       return partner;
   }
   
    
   public boolean andraNamn(String pid, String nyttNamn)
    {
       if (!Validering.tomFalt(nyttNamn, "namn")) {

          
           System.out.println("pid eller namn får inte vara tommna.");
           return false;
       }
        
        
        try{
            String namn = "UPDATE partner SET namn = '" + nyttNamn + "WHERE pid = '" + "'";
            idb.update(namn);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
                
    }
    
    
    
    
    public boolean andraKontaktperson(String pid, String nyKontaktperson)
    {
         if (Validering.tomFalt(nyKontaktperson, "kontaktperson")) {

          
           System.out.println("pid eller kontaktperson får inte vara tommna.");
           return false;
       }
        
        try{
            String kontaktperson = "UPDATE partner SET kontaktperson = '" + nyKontaktperson + "WHERE pid = '" + "'";
            idb.update(kontaktperson);
            return true;
        }
        catch(InfException e)
        {
            e.printStackTrace();
            return false; 
        }
    }
    
    
    
     public boolean andraKontaktEpost(String pid, String nyKontaktEpost)
    {
         if (!Validering.tomFalt(nyKontaktEpost, "kontaktepost")) {

          
           System.out.println("pid eller kontaktepost får inte vara tommna.");
           return false;
       }
        try{
            String kontaktEpost = "UPDATE partner SET kontaktepost = '" + nyKontaktEpost + "WHERE pid = '" + "'";
            idb.update(kontaktEpost);
            return true;
        }
        catch(InfException e)
        {
            e.printStackTrace();
            return false; 
        }
    }
     
     
     
    public boolean andraTelefon(String pid, String nyTelefon)
    {
        
        if (!Validering.tomFalt(nyTelefon, "telefon") && Validering.giltigtTelefonnummer(nyTelefon)) {
          
           System.out.println("pid eller telefon får inte vara tommna.");
           return false;
        }
        try{
            String telefon = "UPDATE partner SET telefon = '" + nyTelefon + "WHERE pid = '" + "'";
            idb.update(telefon);
            return true;
        }
        catch(InfException e)
        {
            e.printStackTrace();
            return false; 
        }
    }  
    
    
     
   public boolean andraAdress(String pid, String nyAdress)
    {
          if (!Validering.tomFalt(nyAdress, "adress")) {
          
           System.out.println("pid eller adress får inte vara tommna.");
           return false;
        }
        try{
            String adress = "UPDATE partner SET adress = '" + nyAdress + "WHERE pid = '" + "'";
            idb.update(adress);
            return true;
        }
        catch(InfException e)
        {
            e.printStackTrace();
            return false; 
        }
    }  
   
   public boolean andraBranch(String pid, String nyBranch)
    {
       {
          if (!Validering.tomFalt(nyBranch, "branch")) {
          
           System.out.println("pid eller branch får inte vara tommna.");
           return false;
        }
        try{
            String branch = "UPDATE partner SET branch = '" + nyBranch + "WHERE pid = '" + "'";
            idb.update(branch);
            return true;
        }
        catch(InfException e)
        {
            e.printStackTrace();
            return false; 
        }
    }   
   
    }
       
   
   //ta bort partner OBS vet ej om denna fungerar
   
   public boolean taBortPartner(String pid) {
       
       
           if (!Validering.tomFalt(pid, "PartnerPid")) {
        
        return false;
       
      }
       try {
           
       String taBortPP = "DELETE FROM projekt_partner WHERE partner_pid = '" + pid + "'";
       idb.delete(taBortPP);
       
       String taBortP = "DELETE FROM partner WHERE pid = '" + pid + "'";
       idb.delete(taBortP);
       
       return true;
           
       } catch (InfException e) {
           
           e.printStackTrace();
           return false;
       }
      
       
       
       
   
   }


   // en bättre versom av lägg till partner + med nyttPid som skapas genom SELECT som tar max pid och sedan lägger till + 1. 
   
   public boolean laggTillPartner(String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch)
   {
       
       if   (!Validering.tomFalt(namn, "namn") ||
             !Validering.tomFalt(kontaktperson, "kontaktperson") ||
             !Validering.giltigEpost(kontaktepost) ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.tomFalt(branch, "branch"))    {
             
              
           System.out.println("Du har glömt att fylla i ett eller fler fält. Partner kan inte läggas till");
           return false;
       }
       
       try {
            
         String korrektPid = "SELECT MAX(pid) FROM partner";
         String maxPid = idb.fetchSingle(korrektPid);
         
         int nyttPid = 1;
         if (maxPid != null) {
             nyttPid = Integer.parseInt(maxPid) + 1; 
           
         }
             
             
         String nyPartner = "INSERT INTO partner (pid, namn, kontaktperson, kontaktepost, telefon, adress, branch)" + "VALUES ('" + nyttPid + "', '" + namn + "', '" + kontaktperson + "', '" + kontaktepost + "', '" + telefon + "', '" + adress + "', '" + branch + "')";
         idb.insert(nyPartner);
         return true;
         
       }
           
         catch (InfException e ){
          System.out.println("Något gick fel" + e.getMessage());
           e.printStackTrace();
           return false;
   }
}
       
   
  public ArrayList<HashMap<String, String>> getAllPartners()
  {
  
      try {
          
          String sql = "SELECT * FROM partner"; 
          
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(sql);
            return rader;
            
     
      }
      
      
      catch (InfException e) {
        
          e.printStackTrace();
          return new ArrayList<>();
         
          
      }
  
  }
  
      public ArrayList getPartnerByProject(String projektID) {
          
          ArrayList<HashMap<String, String>> rows = new ArrayList<>();
          String byProjectQuery = "SELECT * FROM partner WHERE partner.pid IN (SELECT partner_pid FROM projekt_partner WHERE projekt_partner.pid = " + projektID + ");";
          
          try {
             rows = idb.fetchRows(byProjectQuery);
          } catch (InfException e) {
              System.out.println(e);
          }
          
          return rows;

}
      
      //validering så att partner ska finnas och partnern ska inte redan vara tillag till projeket. 
      public boolean addPartnerToProject(String projektID, String partnerID) {
          
          String query = "INSERT INTO projekt_partner VALUES(" + projektID + "," + partnerID + ");";
          System.out.println(query);

          try {
              idb.insert(query);
              return true;
          } catch (InfException e) {
              e.getStackTrace();
              System.out.println(e);
          }
          
          return false;
      }
      
      
      public boolean removePartnerFromProject(String projektID, String partnerID) {
          
          String query = "DELETE FROM projekt_partner WHERE pid = " + projektID + " AND partner_pid = " + partnerID + ";";
          try {
              idb.delete(query);
              return true;
          } catch (InfException e) {
              e.printStackTrace();
              System.out.println(e);
          }
          
          return false;
          
      }
 
}
  

   
   
