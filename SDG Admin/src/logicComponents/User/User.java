/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents.User;
import SQLHanterare.AnstalldHanterare;
import db.DatabaseInterface;
import java.util.UUID;
import orgEntities.Anstalld;
import oru.inf.InfDB;
import orgEntities.Anstalld;

/**
 * The purpose of the user class is to temporarely store information about the logged in user. 
 * The object is allocated an anstalldobject, stores a sessionID, stores user priveledges, and tracks logged in status.
 * @author theow
 */
public class User {
    
    
    private InfDB idb;
    private boolean isLoggedIn;
    private Anstalld allocatedAnstalld;
    private String sessionID;
    private UserType userType;
    private String usrEmail;
    
    
    public User (Anstalld anstalld) {
        
        InfDB idb = DatabaseInterface.databaseConnection();
        isLoggedIn = true;
        allocatedAnstalld = anstalld;
        UUID uuid = UUID.randomUUID();
        sessionID = uuid.toString();
        setUserType();
        
    }
    
    private void setUserType() {
        
        AnstalldHanterare anstalldHanterare = new AnstalldHanterare();
        String aid = allocatedAnstalld.getAid();
        userType = anstalldHanterare.fetchRole(aid);
        
    }
    
    public Anstalld getAnstalld() {
        
        return allocatedAnstalld;
    }
    
    public UserType getUserType() {
        
        return userType;
    }
    
    
    
    
    
}
