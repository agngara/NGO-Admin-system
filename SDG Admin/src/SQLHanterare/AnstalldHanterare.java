/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;

import db.DatabaseInterface;
import java.util.ArrayList;
import orgEntities.Anstalld;
import oru.inf.InfDB;
import java.util.HashMap;
import java.util.UUID;
import logicComponents.User.UserType;
import oru.inf.InfException;
import java.util.UUID;
import java.util.HashMap;

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
       if (adminFetch.equals("null")) {
           
           // If not admin, the code tries projectchef
           String projchefQuery = "SELECT projektchef FROM projekt WHERE projektchef = " + "'" + aid + "'";
           try {
            projchefFetch = idb.fetchSingle(projchefQuery);
           } 
           catch (InfException exception) {
               
           }
           if (projchefFetch.equals(aid)) {
   
               userType = UserType.projektchef;
               } 
           
           // If not admin nor projektchef, the userType must be handläggare.
            else {
               
               userType = UserType.handlaggare;
               
           }
           
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
   
   
   // metoden nedan är avsedd för att kunna hämta totala projektkostnaden
   
   
   
  /**
 * Denna kod är avsedd för att handläggaren ska kunna söka efter en specifik
 * handläggare på avdelningen, genom namn eller epost. 
 *
     * @param avdid 
     * @param sok
     
 */ 
   
   
   
 
   public ArrayList<HashMap<String, String>> sokHandlaggare(String avdid, String sok)
   {
       try{
           String sokning = "SELECT * FROM anstalld " +
                   "WHERE avdid = '" + avdid + "' " +
                   "AND (fornamn LIKE '%" + sok + "%' " +
                   "OR efternamn LIKE '%" + sok + "%' " +
                   "OR epost LIKE '%" + sok + "%')";
           return idb.fetchRows(sokning);
  
       }
       
      catch (InfException e) {
          e.printStackTrace();
          return new ArrayList<>();
       
      }
     
   }
   
   
   
   
   /**
 * Denna kod är avsedd för att lägga till en ny anställd
 * Den skapar ävet ett slumpmässigt lösenord genom UUID
 * och ett slumpmässigt aid
 */
   
   
   public boolean laggTillAnstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    
   {
        
       
        {
        
          if (fornamn == null || efternamn == null || adress == null || epost == null || telefon == null || anstallningsdatum == null || fornamn.isEmpty() || efternamn.isEmpty() || adress.isEmpty() || epost.isEmpty() ||  telefon.isEmpty() || anstallningsdatum.isEmpty()) {
          
           System.out.println("Du har glömt att fylla i ett eller fler fält. Anställd kan inte läggas till");
           return false;
           
       }
        
        try {
             
         String losenord = UUID.randomUUID().toString().substring(0, 9);
         String aid = UUID.randomUUID().toString();
         
         String laggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord) " + 
         "VALUES ('" + aid + "', '" + fornamn + "', '" + efternamn + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "')";
         idb.insert(laggTill);


           System.out.println("Skapat lösenord: " + losenord); 
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
   }

// koderna under avsedda för att ändra mina uppgifter.

public boolean andraEpost(String aid, String nyEpost)
{
    {
        if (aid == null || nyEpost == null || aid.isEmpty() || nyEpost.isEmpty()) {
            System.out.println("aid eller epost får inte vara tom");
            return false;
        }
    }
           
    
    
    try {
        String ePost = "UPDATE anstalld SET epost = '" + nyEpost + "' WHERE aid = '" + aid + "'";
        idb.update(ePost);
        return true;
} 
    catch (InfException e) {
    e.printStackTrace();
    return false; 
}
    
}

    public boolean andraLosenord(String aid, String nyttLosenord)
{
    
     {
        if (aid == null || nyttLosenord == null || aid.isEmpty() || nyttLosenord.isEmpty()) {
            System.out.println("aid eller losenird får inte vara tom");
            return false;
        }
    }
    
    
    
    try {
          String losenord = "UPDATE anstalld SET losenord = '" + nyttLosenord + "' WHERE aid = '" + aid + "'";
          idb.update(losenord);
          return true;
}
    catch (InfException e) {
    
        e.printStackTrace();
        return false;
}


}
    
    
 

public boolean andraFornamn(String aid, String nyttFornamn)
{
   {
        if (aid == null || nyttFornamn == null || aid.isEmpty() || nyttFornamn.isEmpty()) {
            System.out.println("aid eller förnamn får inte vara tom");
            return false;
        }
    }
    
    
    try {

           String fornamn = "UPDATE anstalld SET fornamn = '" + nyttFornamn + "' WHERE aid = '" + aid + "'";
           idb.update(fornamn);
           return true;
}
    
        catch (InfException e)

{
        e.printStackTrace();
        return false;
}


}



public boolean andraEfternamn(String aid, String nyttEfternamn)
{
     {
        if (aid == null || nyttEfternamn == null || aid.isEmpty() || nyttEfternamn.isEmpty()) {
            System.out.println("aid eller efternamn får inte vara tom");
            return false;
        }
    }
    
    
    
    try {

           String efternamn = "UPDATE anstalld SET efternamn = ' " + nyttEfternamn + " ' WHERE aid = ' " + aid + "'";
           idb.update(efternamn);
           return true;
}
    
        catch (InfException e)

{
        e.printStackTrace();
        return false;
}


}

public boolean andraAdress(String aid, String nyAdress)
{
    {
        if (aid == null || nyAdress == null || aid.isEmpty() || nyAdress.isEmpty()) {
            System.out.println("aid eller adress får inte vara tom");
            return false;
        }
    }
    
    try {

           String adress = "UPDATE anstalld SET adress = ' " + nyAdress + " ' WHERE aid = ' " + aid + "'";
           idb.update(adress);
           return true;
}
    
        catch (InfException e)

{
        e.printStackTrace();
        return false;
}



}

/**
 * Denna klass raderar en anställd från databasen baserat på deras aid.
 * Valdideringen sker genom att i if-satsen kollar den att aid inte
 * är null eller tom
 * 
 */

    public boolean taBortAnstalld (Anstalld a)
{
try {
    
    String aid = a.getAid();
    
    if (aid == null || aid.isEmpty()) {
        System.out.println("Aid är tom");
        return false;
    }
    
    String taBort = "DELETE FROM anstalld WHERE aid = '" + a.getAid() + "'";
    idb.delete(taBort);
    System.out.println("Anställd borttagen: "  + a.getFornamn() + " " + a.getEfternamn());
    return true;    
}

catch (InfException e) {
    
    e.printStackTrace();
    
    return false;
    
    }

}

}
       
       
       
       
     

