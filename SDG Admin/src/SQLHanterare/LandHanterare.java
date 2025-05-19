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
public class LandHanterare {
    
   private InfDB idb;
   private HashMap<String, String> land;
   private String query;
   private String lid;
   
   public LandHanterare(String lid) {
       
       this.lid = lid;
       query = "SELECT * FROM anstalld WHERE lid = " + "'" + lid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           land = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
      public HashMap getLand() {
       
       return land;
   }
   
    
    
    
}
