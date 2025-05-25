/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import java.util.UUID;
import orgEntities.Admin;
import orgEntities.Anstalld;
/**
 *
 * @author theow
 */
public class AdminHanterare {
    
   private InfDB idb;
   private HashMap<String, String> admin;
   private String query;
   private String aid;
   
   public AdminHanterare(String aid) {
       
       this.aid = aid;
       query = "SELECT * FROM admin WHERE aid = " + "'" + aid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           admin = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
    public HashMap getAdmin() {
        
        return admin;
        
    }
    
    
     public boolean laggTillAnstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    {
        
            
          if 
            (fornamn == null || efternamn == null || adress == null || epost == null || telefon == null || anstallningsdatum == null || fornamn.isEmpty() || efternamn.isEmpty() || adress.isEmpty() || epost.isEmpty() || telefon.isEmpty() || anstallningsdatum.isEmpty()) {
           
              System.out.println("Du har glömt att fylla i ett eller fler fält. Anställd kan inte läggas till");
              return false;
            
            
        }
       
        
        
        try
        {
             
         String losenord = UUID.randomUUID().toString().substring(0, 9);
         String aid = UUID.randomUUID().toString();
         
         String laggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord) " + 
         "VALUES ('" + aid + "', '" + fornamn + "', '" + efternamn + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "')";
         idb.insert(laggTill);


           System.out.println("Anställd har skapats." + laggTill + "Med lösenord: " + losenord); 
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
    
    
 
    
    
    
}
