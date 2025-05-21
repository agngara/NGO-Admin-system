/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Partner;

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
