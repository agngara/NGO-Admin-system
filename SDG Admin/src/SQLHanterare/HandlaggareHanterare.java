/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Handlaggare;
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
   
    
    public boolean taBortHandlaggare()
    
}
