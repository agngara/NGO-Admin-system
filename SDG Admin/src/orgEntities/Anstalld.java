/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;
import java.util.HashMap;
import oru.inf.InfDB;
import db.DatabaseInterface;
/**
 * 
 * @author theow
 * 
 */



 public class Anstalld {
    
    private HashMap<String, String> anstalld; 
    private InfDB idb;
    private String usrEmail;
    private String query = "SELECT * FROM anstalld WHERE aid = " + "'" + usrEmail + "'";
    
/**
 * This constructor constructs an object of anstalld and populates the anstalld hashmap with info from the database matching the parameter input-
 * @param usrEmail email adress to be used in SQL query. 
 */
    public Anstalld(String usrEmail) {
        
        try {
    
    this.usrEmail = usrEmail;
    anstalld = idb.fetchRow(query);
  
    
        } catch (Exception ex) {
    
            System.out.println(ex);
    
        }
    
    }    
 /**
  * Constructs an anstalldobject.
  * @param anstalld takes an hashmap, this hashmap is meant to be a row from the anstalldtable in the SQL database.
  */  
    
    public Anstalld(HashMap anstalld) {
    
    this.anstalld = anstalld;
    idb = DatabaseInterface.databaseConnection();
    
    }
    
    /**
     * Returns the anstalld hashmap.
     * @return ^^
     */
    public HashMap getAnstalldInfo() {
        
        return anstalld;
        
    }
    
/**
 * This method returns a row in the anstalld table as a hashmap, which has the email entered as parameter. The information is also stored in a field of the class.
 * @param usrEmail takes an email to send in the SQL query.
 * @return the anstalld hashmap that has been fetched from the database.
 */
    public HashMap getAnstalldInfoByEmail(String usrEmail) {
    
    try {
    
    this.usrEmail = usrEmail;
    anstalld = idb.fetchRow(query);
  
    
 } catch (Exception ex) {
    
    System.out.println(ex);
    
        }
    
    return anstalld;
    
    }

 

    
    
 }


