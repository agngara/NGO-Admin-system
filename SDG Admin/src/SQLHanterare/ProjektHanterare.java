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
public class ProjektHanterare {
    
   private InfDB idb;
   private HashMap<String, String> projekt;
   private String query;
   private String pid;
   
   public ProjektHanterare(String pid) {
       
       this.pid = pid;
       query = "SELECT * FROM anstalld WHERE aid = " + "'" + pid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           partner = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
    
    
    
}
