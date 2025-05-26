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
import logicComponents.Validering;
import oru.inf.InfDB;

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
   
   /**
    * This method gets the role of an anstalld, which then can be assigned to the user.
    * @param inAid the aid of the anstalld
    * @return returns usertype enum depending on which role was infered.
    */
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
   
   
   // metoden nedan är avsedd för att kunna hämta totala projektkostnaden
   
   public int projektKostnad(Anstalld a) {
      
       int kostnad = 0;
       
       try {
          
            String aid = a.getAid();
            String summa = "SELECT SUM(kostnader) FROM projekt WHERE projektchef = '" + aid + "'";
            String totala = idb.fetchSingle(summa);
            
            
            
          if (totala != null) {
              
           kostnad = Integer.parseInt(totala);
       }  
          
       } catch (InfException e) {
           
           e.printStackTrace();
         
       }
       
       return kostnad;
       
   }
   
   
   
   
   
   /**
 * Denna kod är avsedd för att lägga till en ny anställd
 * Den skapar ävet ett slumpmässigt lösenord genom UUID
 * och ett slumpmässigt aid
 */
   
   
   public boolean laggTillAnstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    
   {
        
       
        if (!Validering.tomFalt(fornamn, "fornamn") ||
             !Validering.tomFalt(efternamn, "efternamn") ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigEpost(epost) ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.giltigEpost(anstallningsdatum)) {
        
          //if (fornamn == null || efternamn == null || adress == null || epost == null || telefon == null || anstallningsdatum == null || fornamn.isEmpty() || efternamn.isEmpty() || adress.isEmpty() || epost.isEmpty() ||  telefon.isEmpty() || anstallningsdatum.isEmpty()) {
          
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
   

// koderna under avsedda för att ändra mina uppgifter.

public boolean andraEpost(String aid, String nyEpost)
{
    {
        if (!Validering.tomFalt(nyEpost, "epost") &&
        !Validering.giltigEpost(nyEpost)) {
    //(aid == null || nyEpost == null || aid.isEmpty() || nyEpost.isEmpty()) {
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
          if (!Validering.tomFalt(nyttLosenord, "losenord")) {
        //if (aid == null || nyttLosenord == null || aid.isEmpty() || nyttLosenord.isEmpty()) {
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
        if (!Validering.tomFalt(nyttFornamn, "fornamn")) {
        //if (aid == null || nyttFornamn == null || aid.isEmpty() || nyttFornamn.isEmpty()) {
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
        if (!Validering.tomFalt(nyttEfternamn, "efternamn")) {
        //if (aid == null || nyttEfternamn == null || aid.isEmpty() || nyttEfternamn.isEmpty()) {
            System.out.println("aid eller efternamn får inte vara tom");
            return false;
       
    
    
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
}

public boolean andraAdress(String aid, String nyAdress)
{
    {
        
        if(!Validering.tomFalt(nyAdress, "adress")) {
        //if (aid == null || nyAdress == null || aid.isEmpty() || nyAdress.isEmpty()) {
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

    public boolean taBortAnstalld (Anstalld a) {

    String aid = a.getAid();
    if (!Validering.tomFalt(aid, aid)) {
        System.out.println("Aid är tom");
        return false;
    }
   try { 
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
       
       
     

