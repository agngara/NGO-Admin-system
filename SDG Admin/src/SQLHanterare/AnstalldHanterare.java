/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;

import db.DatabaseInterface;
import orgEntities.Anstalld;
import oru.inf.InfDB;
import java.util.HashMap;
import logicComponents.User.UserType;
import oru.inf.InfException;

/**
 * This class handles database communication with the anstalld table.
 * @author theow
 */
public class AnstalldHanterare {
    
   private InfDB idb;
   private HashMap<String, String> anstalld;
   private String query;
   private String email;
   
   /**
    * This empty constructor can be used for when mehtods not related to the class fields is to be called. 
    */
   public AnstalldHanterare() {
       
       
   }
   
   /**
    * This constructor takes an email and fetches info to populate the anstalld hashmap.
    * @param email to be entered as query.
    */
   public AnstalldHanterare(String email) {
       
       this.email = email;
       query = "SELECT * FROM anstalld WHERE epost = " + "'" + email + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           anstalld = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
       
   }
   
   
   /**
    * Returns anstalld HashMap. The method was made for the login process at inloggning.java.
    * @return HashMap with anstalld info in it.
    */
   public HashMap getAnstalld() {
       
       return anstalld;
       
   } 
   
   
   public UserType fetchRole(String inAid) {
      
       UserType userType = UserType.handlaggare;
       String roll = "";
       String aid = inAid;
       String checkAdminQuery = "SELECT behorighetsniva FROM admin WHERE admin.aid = (SELECT aid FROM anstalld WHERE aid= " + "'" + aid + "'" + ")";
       
       try {
       // Checks for admin role.    
       if (idb.fetchSingle(checkAdminQuery).equals("null")) {
           
           // If not admin, the code tries projectchef
           String projchefQuery = "SELECT projektchef FROM projekt WHERE projektchef = " + "'" + aid + "'";
           if (idb.fetchSingle(projchefQuery).equals(aid)) {
               
               userType = UserType.projektchef;
           }
           // If not admin nor projektchef, the userType must be handläggare.
           /*else {
               
               userType = UserType.handlaggare;
               
           }*/
       }
       // Checks for admin type 1.
       else if (idb.fetchSingle(checkAdminQuery).equals("1")) {
           
           userType = UserType.admin1;
           
       }
       // Checks for admin type 2. 
       else if (idb.fetchSingle(checkAdminQuery).equals("2")) {
           
           userType = UserType.admin2;
       }
       
       } catch (InfException ex) {
           
       }
     
       return userType;
   }
    
    
}
