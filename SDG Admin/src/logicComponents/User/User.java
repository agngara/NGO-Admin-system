/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents.User;
import db.DatabaseInterface;
import java.util.UUID;
import orgEntities.Anstalld;
import oru.inf.InfDB;

/**
 * The purpose of the user class is to temporarely store information about the logged in user. 
 * The object is allocated an anstalldobject, stores a sessionID, stores user priveledges, and tracks logged in status.
 * @author theow
 */
public class User {
    
    InfDB idb;
    boolean isLoggedIn;
    Anstalld allocatedAnstalld;
    String sessionID;
    UserType userType;
    String usrEmail;
    
    
    public User(String email) {
        
        InfDB idb = DatabaseInterface.databaseConnection();
        isLoggedIn = true;
        allocatedAnstalld = new Anstalld(email);
        UUID uuid = UUID.randomUUID();
        sessionID = uuid.toString();
        allocatedAnstalld = new Anstalld(email);
        usrEmail = email;
        userType = setUserType();
        
    }
    
    public UserType setUserType() {
        
        String query = "SELECT behorighetsniva FROM admin WHERE admin.aid = (SELECT aid FROM anstalld WHERE anstalld.epost = " + "'" + usrEmail + "'";
        
        try {
            
        String behorighet = idb.fetchSingle(query);
        
        if (behorighet.equals("1")) {
            
            userType = UserType.admin1;
        }
        else if (behorighet.equals("2"))
        {
            userType = UserType.admin2;
        }
               
        
        } catch (Exception ex) {
            
            userType = UserType.handlaggare;
            
        }
        
        return userType;
        
    }
    
    
    
}
