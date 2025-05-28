/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import java.util.UUID;
import logicComponents.Validering;
import orgEntities.Admin;
import orgEntities.Anstalld;
import oru.inf.InfException;
/**
 *
 * @author theow
 */
public class AdminHanterare {
    
   private InfDB idb;
   private HashMap<String, String> admin;
   private String query;
   private String aid;
   
   public AdminHanterare(String aid) {
       
       this.aid = aid;
       query = "SELECT * FROM admin WHERE aid = " + "'" + aid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           admin = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
    public HashMap getAdmin() {
        
        return admin;
        
    }
    /**
 * Metoden nedan lägger till en anställd 
 * 
     * @param losenord
     * @param fornamn
     * @param efternamn
     * @param adress
     * @param epost
     * @param telefon
     * @param anstallningsdatum
     * @return 
 */
    //Denna är korrigerad, så om denna metod finns på annan plats, använd denna!!
    
    public boolean laggTillAnstalld(String losenord, String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    
   {
        
        //om lösenord ej inmatas, skapas ett lösenod genom UUID. 
        if (losenord == null) {
            losenord = UUID.randomUUID().toString();
        }
      
       
        if (
             !Validering.tomFalt(losenord, "lösenord") ||
             !Validering.tomFalt(aid, "aid") ||
             !Validering.tomFalt(fornamn, "förnamn") ||
             !Validering.tomFalt(efternamn, "efternamn") ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigEpost(epost) ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.giltigtDatum(anstallningsdatum)) {
        
          //if (fornamn == null || efternamn == null || adress == null || epost == null || telefon == null || anstallningsdatum == null || fornamn.isEmpty() || efternamn.isEmpty() || adress.isEmpty() || epost.isEmpty() ||  telefon.isEmpty() || anstallningsdatum.isEmpty()) {
          
           System.out.println("Du har glömt att fylla i ett eller fler fält. Anställd kan inte läggas till");
           return false;
           
       }
        
        try {
            // här skapas ett aid och för att göra aid "unikt" hämtar den det högsta aid och sedan lägger till +1
         String korrektAid = "SELECT MAX(aid) FROM anstalld";
         String maxAid = idb.fetchSingle(korrektAid);
         
         int nyttAid = 1;
         if (maxAid != null) {
             nyttAid = Integer.parseInt(maxAid) + 1; }
             
         
         
         
         String laggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord) " + 
         "VALUES ('" + nyttAid + "', '" + fornamn + "', '" + efternamn + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "')";
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
    
 // Metoden lägger till ett projekt 
    
    public boolean laggTillProjekt(String projektnamn, String beskrivning, String startdatum, String slutdatum, String kostnad, String status, String prioritet)
    {
        
       // if (pid == null || projektnamn == null || beskrivning == null || startdatum == null || slutdatum == null || kostnad == null || status == null || prioritet == null || pid.isEmpty() || projektnamn.isEmpty() || beskrivning.isEmpty() || startdatum.isEmpty() ||  slutdatum.isEmpty() || kostnad.isEmpty() || status.isEmpty() || prioritet.isEmpty()) {
          if (!Validering.tomFalt(projektnamn, "Projektnamn") ||
             !Validering.tomFalt(beskrivning, "Beskrivning") ||
             !Validering.giltigtDatum(startdatum) ||
             !Validering.giltigtDatum(slutdatum) ||
             !Validering.giltigDouble(kostnad) ||
             !Validering.tomFalt(status, "Status") ||
             !Validering.tomFalt(prioritet, "Prioritet"))
              {

           System.out.println("Du har glömt att fylla i ett eller fler fält. Projekt kan inte läggas till");
           return false;
            }
       
        try
        {
            
            
             String korrektPid = "SELECT MAX(pid) FROM projekt";
            String maxPid = idb.fetchSingle(korrektPid);
            
            int nyttPid = 1;
            if (maxPid != null) {
             nyttPid = Integer.parseInt(maxPid) + 1; }
            
            String laggTill = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet) VALUES ('" + nyttPid + "', '" + projektnamn + "', '" + beskrivning + "', '" + startdatum + "', '" + slutdatum + "', '" + kostnad + "', '"  + status + "', '"  + prioritet + "')";
            idb.insert(laggTill);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
    
    
}
    

