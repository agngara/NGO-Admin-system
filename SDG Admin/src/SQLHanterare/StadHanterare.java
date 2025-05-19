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
public class StadHanterare {
    
   private InfDB idb;
   private HashMap<String, String> stad;
   private String query;
   private String sid;
   
   public StadHanterare(String sid) {
       
       this.sid = sid;
       query = "SELECT * FROM anstalld WHERE aid = " + "'" + sid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           stad = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
    
    
    
}
