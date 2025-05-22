/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;

import db.DatabaseInterface;
import orgEntities.Anstalld;
import oru.inf.InfDB;
import java.util.HashMap;
import java.util.UUID;
import logicComponents.User.UserType;
import oru.inf.InfException;
import java.util.UUID;


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
       
       idb = DatabaseInterface.databaseConnection();
       
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
       String adminFetch = "";
       String checkAdminQuery = "SELECT behorighetsniva FROM admin WHERE admin.aid = (SELECT aid FROM anstalld WHERE aid= " + "'" + aid + "'" + ")";
       String projchefFetch = "";

       
       
       
    // Checks for admin role.  
       try {
       adminFetch = idb.fetchSingle(checkAdminQuery);
       } 
       catch (InfException ex) {
           
       }
       if (adminFetch == null) {
           
           // If not admin, the code tries projectchef
           String projchefQuery = "SELECT projektchef FROM projekt WHERE projektchef = " + "'" + aid + "'";
           try {
            projchefFetch = idb.fetchSingle(projchefQuery);
           } 
           catch (InfException exception) {
               
               if (projchefFetch == null)
               {
                   userType = UserType.handlaggare;
               }
               else {
                   
                   userType = UserType.projektchef;
               }
               
           }
          /* if (projchefFetch.equals(aid)) {
   
               userType = UserType.projektchef;
               } 
           
           // If not admin nor projektchef, the userType must be handläggare.
            /*else {
               
               userType = UserType.handlaggare;
               
           }*/
           
       }
   
       
       // Checks for admin type 1.
       else if (adminFetch.equals("1")) {
           
           userType = UserType.admin1;
           
       }
       // Checks for admin type 2. 
       else if (adminFetch.equals("2")) {
           
           userType = UserType.admin2;
       }
       
        return userType;
   
   
    }
   
   
   /**
 * Denna kod är avsedd för att lägga till en ny anställd
 * Den skapar ävet ett slumpmässigt lösenord genom UUID
 * och ett slumpmässigt aid
 */
   
   
   public boolean laggTillAnstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    {
        
        try
        {
             
         String losenord = UUID.randomUUID().toString().substring(0, 9);
         String aid = UUID.randomUUID().toString();
         
         String laggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord) " + 
         "VALUES ('" + aid + "', '" + fornamn + "', '" + efternamn + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "')";
         idb.insert(laggTill);


           System.out.println("Skapat lösenord: " + losenord); 
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }


// koderna under avsedda för att ändra mina uppgifter.

public boolean andraEpost(String aid, String nyEpost)
{
    try {
        String ePost = "UPDATE anstalld SET epost = '" + nyEpost + "' WHERE aid = '" + aid + "'";
        idb.update(ePost);
        return true;
} 
    catch (Exception e) {
    e.printStackTrace();
    return false; 
}
    
}

    public boolean andraLosenord(String aid, String nyttLosenord)
{
    try {
          String losenord = "UPDATE anstalld SET losenord = '" + nyttLosenord + "' WHERE aid = '" + aid + "'";
          idb.update(losenord);
          return true;
}
    catch (Exception e) {
    
        e.printStackTrace();
        return false;
}


}
    
    
 

public boolean andraFornamn(String aid, String nyttFornamn)
{
    try {

           String fornamn = "UPDATE anstalld SET fornamn = '" + nyttFornamn + "' WHERE aid = '" + aid + "'";
           idb.update(fornamn);
           return true;
}
    
        catch (Exception e)

{
        e.printStackTrace();
        return false;
}


}



public boolean andraEfternamn(String aid, String nyttEfternamn)
{
    try {

           String efternamn = "UPDATE anstalld SET efternamn = ' " + nyttEfternamn + " ' WHERE aid = ' " + aid + "'";
           idb.update(efternamn);
           return true;
}
    
        catch (Exception e)

{
        e.printStackTrace();
        return false;
}


}

public boolean andraAdress(String aid, String nyAdress)
{
    try {

           String adress = "UPDATE anstalld SET adress = ' " + nyAdress + " ' WHERE aid = ' " + aid + "'";
           idb.update(adress);
           return true;
}
    
        catch (Exception e)

{
        e.printStackTrace();
        return false;
}



}

// radera anställd

    public boolean taBortAnstalld (Anstalld a)
{
try {
    String taBort = "DELETE FROM anstalld WHERE aid = '" + a.getAid() + "'";
    idb.delete(taBort);
    return true;
}

catch (Exception e)
{
e.printStackTrace();
return false;
}

}
   
   
   
   

}
       
       
       
       
     

