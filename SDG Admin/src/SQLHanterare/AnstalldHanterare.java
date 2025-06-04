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
import javax.swing.JOptionPane;
import logicComponents.Validering;
import oru.inf.InfDB;

/**
 * Denna klass ansvarar för att hantera funktionalitet kopplad till 
 * anställda i systemet.
 * Klassen sökter kontakten med sql och metoder är tillgängliga här för att
 * lägga till asntällda, ändra uppgifter om anställd samt ta bort anställd. 
 * Dessa metoder används sedan i klassen anställd, editAnställda och läggTillAnställda. 
 * 
 */

public class AnstalldHanterare {
    
   private InfDB idb;
   private HashMap<String, String> anstalld;
   private String query;
   private String aid;
   private String email;
   
   /**
    * This empty constructor can be used for when mehtods not related to the class fields is to be called. 
    */
   public AnstalldHanterare() {
       
       idb = DatabaseInterface.databaseConnection();
       
   }
   
//   public AnstalldHanterare(String aid) {
//       
//       this.aid = aid;
//       query = "SELECT * FROM anstalld WHERE aid = " + "'" + aid + "'";
//       idb = DatabaseInterface.databaseConnection();
//       
//       try {
//           
//           anstalld = idb.fetchRow(query);
//           
//       } catch (InfException exception) {
//           
//       }
//        
//       
//   }
   
   public AnstalldHanterare(String aid, String filler) {
       
       this.email = email;
       query = "SELECT * FROM anstalld WHERE aid = " + "'" + aid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           anstalld = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
       
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
    String aid = inAid;

    // 1. Kolla om användaren är admin
    String checkAdminQuery = "SELECT behorighetsniva FROM admin WHERE admin.aid = '" + aid + "'";
    String adminFetch = null;
    try {
        adminFetch = idb.fetchSingle(checkAdminQuery);
    } catch (InfException ex) {
        // Logga men fortsätt
    }
    if (adminFetch != null) {
        if (adminFetch.equals("1")) {
            return UserType.admin1;
        } else if (adminFetch.equals("2")) {
            return UserType.admin2;
        }
    }

    // 2. Kolla om användaren är projektchef
    String projchefQuery = "SELECT projektchef FROM projekt WHERE projektchef = '" + aid + "'";
    String projchefFetch = null;
    try {
        projchefFetch = idb.fetchSingle(projchefQuery);
    } catch (InfException ex) {
        // Logga men fortsätt
    }
    if (projchefFetch != null && projchefFetch.equals(aid)) {
        return UserType.projektchef;
    }

    // 3. Om inget av ovan, är användaren handläggare
    return userType;
}
   
   /**
    * This method gets the role of an anstalld, which then can be assigned to the user.
    * @param inAid the aid of the anstalld
    * @return returns usertype enum depending on which role was infered.
    */
//   public UserType fetchRole(String inAid) {
//      
//       UserType userType = UserType.handlaggare;
//       String roll = "";
//       String aid = inAid;
//       String adminFetch = "";
//       String checkAdminQuery = "SELECT behorighetsniva FROM admin WHERE admin.aid = (SELECT aid FROM anstalld WHERE aid= " + "'" + aid + "'" + ")";
//       String projchefFetch = "";
//
//       
//       
//       
//    // Checks for admin role.  
//       try {
//       adminFetch = idb.fetchSingle(checkAdminQuery);
//       } 
//       catch (InfException ex) {
//           
//       }
//       if (adminFetch == null) {
//           
//           // If not admin, the code tries projectchef
//           String projchefQuery = "SELECT projektchef FROM projekt WHERE projektchef = " + "'" + aid + "'";
//           try {
//            projchefFetch = idb.fetchSingle(projchefQuery);
//            
//           } 
//           catch (InfException exception) {
//               
//               if (projchefFetch == null)
//               {
//                   userType = UserType.handlaggare;
//               }
//               else {
//                   
//                   userType = UserType.projektchef;
//               }
//               
//           }
//          /* if (projchefFetch.equals(aid)) {
//   
//               userType = UserType.projektchef;
//               } 
//           
//           // If not admin nor projektchef, the userType must be handläggare.
//            /*else {
//               
//               userType = UserType.handlaggare;
//               
//           }*/
//           
//       }
//   
//       
//       // Checks for admin type 1.
//       else if (adminFetch.equals("1")) {
//           
//           userType = UserType.admin1;
//           
//       }
//       // Checks for admin type 2. 
//       else if (adminFetch.equals("2")) {
//           
//           userType = UserType.admin2;
//       }
//       
//        return userType;
//   
//   
//    }
//   
   
   // ny version av getRoll
   
//   public UserType fetchRRole(String aid) {
//       
//       UserType userType = UserType.handlaggare;
//       
//       
//       
//       
//       try {
//           String checkAdmin = "SELECT behorighetsniva FROM admin WHERE aid = '" + aid + "'";
//           String adminResultat = idb.fetchSingle(checkAdmin);
//           if (adminResultat != null) {
//               if (adminResultat.equals("1")) {
//                   return UserType.admin1;
//               } else if (adminResultat.equals("2")) {
//                   return UserType.admin2; 
//               }
//           }
//           
//       }
//       
//       
//       String checkChef = "SELECT projektchef FROM projekt WHERE projektchef = '" + aid + "'";
//       String chefResultat = idb.fetchSingle(checkChef);
//       if (chefResultat != null && chefResultat.equals(aid)) {
//           return UserType.projektchef;
//       }
//     
//       return UserType.handlaggare;
//   }
//   
   // anställdahnaterare borde ge tbx anställda. En anställdhanterare och 0-m anställda. 
   
   
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
   
   // Metod för att generera lösenord
   
   public String genereraLosenord() {
       return UUID.randomUUID().toString();
   }
  
   
   /**
 * Denna kod är avsedd för att lägga till en ny anställd
 * Den skapar ävet ett slumpmässigt lösenord genom UUID
 * och ett slumpmässigt aid
 * Om lösenord inte inmatas så skapas ett genererat losen ord, via metoden genereraLosenord
 */
   
   
   public boolean laggTillAnstalld(int aid, String losenord, String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    
   {
      if (losenord == null || losenord.isEmpty()) {
       
          losenord = genereraLosenord();
      }
       
        if (
             !Validering.tomFalt(losenord, "lösenord") || 
             !Validering.tomFalt(fornamn, "förnamn") ||
             !Validering.tomFalt(efternamn, "efternamn") ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigEpost(epost) ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.giltigtDatum(anstallningsdatum)) {
        
          
           JOptionPane.showMessageDialog(null,"Du har glömt att fylla i ett eller fler fält. Anställd kan inte läggas till");
           return false;
           
       }
        
        try {
             
         String korrektAid = "SELECT MAX(aid) FROM anstalld";
         String maxAid = idb.fetchSingle(korrektAid);
         
         
         
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
   
 public boolean andraAid(String nyAid, String gammalAid)
{
    {
        if (!Validering.tomFalt(nyAid, "aid")) return false;
        if (!Validering.tomFalt(gammalAid, "gammalAid")) return false;
           
        
    }
               
    try {
       String fraga = "UPDATE anstalld SET aid = " + nyAid + " WHERE pid = " + gammalAid;
        idb.update(fraga);
        return true;
} 
    catch (InfException e) {
    e.printStackTrace();
    return false; 
}
    
}  
   
 
  public boolean andraAvdelning(String aid, String nyAvdelning)
{
    {
        if (!Validering.tomFalt(nyAvdelning, "avdelning")) {
            JOptionPane.showMessageDialog(null,"Avdelning får inte vara tom");
            return false;
        }
    }
           
    try {
        String nyttAvdelning = "UPDATE anstalld " + "SET avdelning = '" + nyAvdelning + " WHERE aid = " + aid;
        idb.update(nyttAvdelning);
        return true;
} 
    catch (InfException e) {
    e.printStackTrace();
    return false; 
}
    
}


 public boolean andraAnstallningsdatum(String aid, String nyAnsdatum)
{
    
    if (!Validering.tomFalt(nyAnsdatum, "anställningsdatum")) return false; 

               
       if (!Validering.giltigtDatum(nyAnsdatum)) return false;
           
    try {
        String nyttAnsdatum = "UPDATE anstalld SET anstallningsdatum = '" + nyAnsdatum + "' WHERE aid = " + aid;
        idb.update(nyttAnsdatum);
        return true;
} 
    catch (InfException e) {
    e.printStackTrace();
    return false; 
}
    
}    
 
public boolean andraEpost(String aid, String nyEpost)
{
    {
        if (!Validering.tomFalt(nyEpost, "epost")) return false;
        if (!Validering.giltigEpost(nyEpost)) return false;
    
        }
    
           
    
    
    try {
        String ePost = "UPDATE anstalld SET epost = '" + nyEpost + "' WHERE aid = " + aid;
        idb.update(ePost);
        return true;
} 
    catch (InfException e) {
    e.printStackTrace();
    return false; 
}
    
}

public boolean andraTelefon(String aid, String nyTelefon)
{
    {
        if (!Validering.tomFalt(nyTelefon, "telefon")) {
            return false;
        }
        if (!Validering.giltigtTelefonnummer(nyTelefon)) {
            return false;
        }
     
    }
    
           
    
    
    try {
        String telefon = "UPDATE anstalld SET telefon = '" + nyTelefon + "' WHERE aid = " + aid;
        idb.update(telefon);
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
          if (!Validering.tomFalt(nyttLosenord, "lösenord")) {
       
            
            return false;
        }
    }
    
    
    
    try {
          String losenord = "UPDATE anstalld SET losenord = '" + nyttLosenord + "' WHERE aid = " + aid;
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
        if (!Validering.tomFalt(nyttFornamn, "förnamn")) {
        
            
            return false;
        }
    }
    
    
    try {

           String fornamn = "UPDATE anstalld SET fornamn = '" + nyttFornamn + "' WHERE aid = " + aid;
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
       
            
            return false;
       
    
    
}
    
    try {

           String efternamn = "UPDATE anstalld SET efternamn = '" + nyttEfternamn + "' WHERE aid = " + aid;
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
        
        if(!Validering.tomFalt(nyAdress, "adress")) return false;
            
            
            
        
    }
    
    try {

           String adress = "UPDATE anstalld SET adress = '" + nyAdress + "' WHERE aid = " + aid;
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
 * Denna klass raderar en anställd från databasen baserat på deras aid.Valdideringen sker genom att i if-satsen kollar den att aid inte
 är null eller tom.
 * UPPDATERAD version för att få bort anställd genom att ta bort främmande nycklar från andra tabeller. 
 *
     * @param a 
     * @return  
 */

    public boolean taBortAnstalld (String aid) {

    
    
    if (!Validering.tomFalt(aid, "anställningsID")) {
           JOptionPane.showMessageDialog(null,"Aid är tom");
        return false;
    }
   try { 
    
       // ta bort från alla tabeller där aid finns som nyckel. 
       
       String taBortAdmin = "DELETE FROM admin WHERE aid = '" + aid + "'";
       idb.delete(taBortAdmin);
       String taBortHand = "DELETE FROM handlaggare WHERE aid = '" + aid + "'";
       idb.delete(taBortHand);
       String taBortAnsPro = "DELETE FROM ans_proj WHERE aid = '" + aid + "'";
       idb.delete(taBortAnsPro);
       String taBort = "DELETE FROM anstalld WHERE aid = '" + aid + "'";
       idb.delete(taBort);
      
    
    
    return true;    
}

    catch (InfException e) {
    
    e.printStackTrace();
    
    return false;
    
    }

}

public HashMap getProjChefByProject(String pid) {
    
    String query = "SELECT * FROM anstalld WHERE anstalld.aid IN (SELECT projektchef FROM projekt WHERE pid = " + pid + ");";
    System.out.println(query);
    HashMap<String, String> row = new HashMap<>();
    try {
    row = idb.fetchRow(query);
    } catch (InfException e) {
        e.getStackTrace();
        System.out.println(e);
    }
    
    return row;
    
}
    
}      
       
       
     

