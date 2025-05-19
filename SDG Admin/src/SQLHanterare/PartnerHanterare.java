/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;

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
   
    
    
    
}
