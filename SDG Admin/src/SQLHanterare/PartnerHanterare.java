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
   
   public PartnerHanterare(String pid) {
       
       this.pid = pid;
       query = "SELECT * FROM anstalld WHERE pid = " + "'" + pid + "'";
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
       if (pid == null || nyttNamn == null || pid.isEmpty() || nyttNamn.isEmpty()){
          
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
         if (pid == null || nyKontaktperson == null || pid.isEmpty() || nyKontaktperson.isEmpty()){
          
           System.out.println("pid eller kontaktperson får inte vara tommna.");
           return false;
       }
        
        try{
            String kontaktperson = "UPDATE partner SET kontaktperson = '" + nyKontaktperson + "WHERE pid = '" + "'";
            idb.update(kontaktperson);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false; 
        }
    }
    
    
    
     public boolean andraKontaktEpost(String pid, String nyKontaktEpost)
    {
         if (pid == null || nyKontaktEpost == null || pid.isEmpty() || nyKontaktEpost.isEmpty()){
          
           System.out.println("pid eller kontaktepost får inte vara tommna.");
           return false;
       }
        try{
            String kontaktEpost = "UPDATE partner SET kontaktepost = '" + nyKontaktEpost + "WHERE pid = '" + "'";
            idb.update(kontaktEpost);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false; 
        }
    }
     
     
     
    public boolean andraTelefon(String pid, String nyTelefon)
    {
        
        if (pid == null || nyTelefon == null || pid.isEmpty() || nyTelefon.isEmpty()){
          
           System.out.println("pid eller telefon får inte vara tommna.");
           return false;
        }
        try{
            String telefon = "UPDATE partner SET telefon = '" + nyTelefon + "WHERE pid = '" + "'";
            idb.update(telefon);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false; 
        }
    }  
    
    
     
   public boolean andraAdress(String pid, String nyAdress)
    {
          if (pid == null || nyAdress == null || pid.isEmpty() || nyAdress.isEmpty()){
          
           System.out.println("pid eller adress får inte vara tommna.");
           return false;
        }
        try{
            String adress = "UPDATE partner SET adress = '" + nyAdress + "WHERE pid = '" + "'";
            idb.update(adress);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false; 
        }
    }  
   
   public boolean andraBranch(String pid, String nyBranch)
    {
       {
          if (pid == null || nyBranch == null || pid.isEmpty() || nyBranch.isEmpty()){
          
           System.out.println("pid eller branch får inte vara tommna.");
           return false;
        }
        try{
            String branch = "UPDATE partner SET branch = '" + nyBranch + "WHERE pid = '" + "'";
            idb.update(branch);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false; 
        }
    }   
   
    }
       // metoden nedan är avsedd för att kunna lägga till en ny partner
   
   
   
   

   
   // en bättre versom av lägg till partner 
   
   public boolean laggTillPartner(String pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch)
   {
       if  (!Validering.tomFalt(pid, "pid") ||
             !Validering.tomFalt(namn, "namn") ||
             !Validering.tomFalt(kontaktperson, "kontaktperson") ||
             !Validering.giltigEpost(kontaktepost) ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.tomFalt(branch, "branch"))
              {
           System.out.println("Du har glömt att fylla i ett eller fler fält. Partner kan inte läggas till");
           return false;
       }
       
       try {
            String nyPartner = "INSERT INTO partner (pid, namn, kontaktperson, kontaktepost, telefon, adress, branch) VALUES ('" + pid + "', '" + namn + "', '" + kontaktperson + "', '" + kontaktepost + "', '" + telefon + "', '" + adress + "', '" + branch + "')";
           idb.insert(nyPartner);
           return true;
       } catch (InfException e ){
          System.out.println("Något gick fel" + e.getMessage());
           e.printStackTrace();
           return false;
   }
}
       
     public ArrayList<HashMap<String, String>> getAllPartners() {
         
         try {
             String sql = "SELECT * FROM partner";
              ArrayList<HashMap<String, String>> rader = idb.fetchRows(sql);
              return rader;
         }
         
         catch (InfException e) {
             
             e.printStackTrace();
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
         
          
      }
  }
 
}
   
   
   
  
