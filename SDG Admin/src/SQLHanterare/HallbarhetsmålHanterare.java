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
public class HallbarhetsmålHanterare {
    
   private InfDB idb;
   private HashMap<String, String> hallbarhetsmål;
   private String query;
   private String hid;
  
   public HallbarhetsmålHanterare(String hid) {
       
       this.hid = hid;
       query = "SELECT * FROM anstalld WHERE aid = " + "'" + hid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           hallbarhetsmål = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
    
    
    
}
