/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import java.util.ArrayList;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Handlaggare;
import oru.inf.InfException;
/**
 *
 * @author theow
 */
public class HandlaggareHanterare {
    
   private InfDB idb;
   private HashMap<String, String> handlaggare;
   private String query;
   private String aid;
   
   public HandlaggareHanterare(String email) {
       
       this.aid = aid;
       query = "SELECT * FROM anstalld WHERE aid = " + "'" + aid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           handlaggare = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
   public HashMap getHandlaggare() {
       
       return handlaggare;
   }
   
   /**
 * Denna kod är avsedd för att handläggaren ska kunna söka efter en specifik
 * handläggare på avdelningen, genom namn eller epost. 
 *
     * @param avdid 
     * @param sok
     
 */ 
   
   
   
 
   public ArrayList<HashMap<String, String>> sokHandlaggare(String avdid, String sok)
   {
       try{
           String sokning = "SELECT * FROM anstalld " +
                   "WHERE avdid = '" + avdid + "' " +
                   "AND (fornamn LIKE '%" + sok + "%' " +
                   "OR efternamn LIKE '%" + sok + "%' " +
                   "OR epost LIKE '%" + sok + "%')";
           return idb.fetchRows(sokning);
  
       }
       
      catch (InfException e) {
          e.printStackTrace();
          return new ArrayList<>();
       
      }
     
   }  
    
}
