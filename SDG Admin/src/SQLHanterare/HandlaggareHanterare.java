/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import java.util.ArrayList;
import oru.inf.InfDB;
import java.util.HashMap;
import logicComponents.Validering;
import orgEntities.Handlaggare;
import oru.inf.InfException;
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
   
   /**
 * Denna kod är avsedd för att handläggaren ska kunna söka efter en specifik
 * handläggare på avdelningen, genom namn eller epost. 
 *
     * @param avdid 
     * @param sok
     * @return 
     
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
   
   
   // koderna under avsedda för att ändra mina uppgifter.

public boolean andraEpost(String aid, String nyEpost)
{
    {
        if (!Validering.tomFalt(nyEpost, "epost") &&
        Validering.giltigEpost(nyEpost)) {
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

           String adress = "UPDATE anstalld SET adress = '" + nyAdress +  "WHERE aid = ' " + aid + "'";
           idb.update(adress);
           return true;
}
    
        catch (InfException e)

{
        e.printStackTrace();
        return false;
}



}


//lägg till handläggare
public boolean laggTillHandlaggare(String aid, String ansvarighetsomrade) {
    
   if (!Validering.tomFalt(aid, "aid") && Validering.tomFalt(ansvarighetsomrade, "ansvarighetsomrade")) {
       return false;
   }
    
    
    try {
    String laggTill = "INSERT INTO handlaggare (aid, ansvarighetsomrade) VALUES ('" + aid + "', '" + ansvarighetsomrade + "')";
    idb.insert(laggTill);
    return true;    
    }
    
    catch (InfException e) {
        e.getStackTrace();
        return false;
    }
}

/*public void getHandlaggareByProjekt(String projektID) {
    
          ArrayList<HashMap<String, String>> rows = new ArrayList<>();
          String byProjectQuery = "SELECT * FROM partner WHERE partner.pid IN (SELECT partner_pid FROM projekt_partner WHERE projekt_partner.pid = " + projektID + ");";
            

          System.out.println(byProjectQuery);
          
          try {
             rows = idb.fetchRows(byProjectQuery);
          } catch (InfException e) {
              System.out.println(e);
          }
          
          return rows;
    
}*/
    
}
