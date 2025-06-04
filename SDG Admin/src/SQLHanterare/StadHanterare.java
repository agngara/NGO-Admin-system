/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import java.util.ArrayList;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Stad;
import oru.inf.InfException;
/**
 *
 * @author theow
 */
public class StadHanterare {
    
   private InfDB idb;
   private HashMap<String, String> stad;
   private String query;
   private String sid;
   
   
   public StadHanterare() {
       
       idb = DatabaseInterface.databaseConnection();
       
   }
   
   
   public StadHanterare(String sid) {
       
       this.sid = sid;
       query = "SELECT * FROM stad WHERE sid = " + "'" + sid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           stad = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   

     public HashMap getStad() {
       
       return stad;
   }
   
    public ArrayList fetchAllStad() 
      {
          ArrayList<HashMap<String,String>> stader = new ArrayList<>();
          
          String query = "SELECT * FROM stad";
          
          try {
          stader = idb.fetchRows(query);
          }
          catch (InfException e) {
              
          }
          
          return stader;
      }
    
//     public ArrayList<HashMap<String, String>> getAllStad()
//  {
//  
//      ArrayList<HashMap<String, String>> rader = new ArrayList<HashMap<String, String>>();
//      try {
//          
//          String sql = "SELECT * FROM stad"; 
//          
//            rader = idb.fetchRows(sql);
//            return rader;
//            
//      } catch (InfException e) {
//          e.printStackTrace();
//          System.out.println(e);
//      }
//        
//     return rader;
//    
//    
//   }
    
}


