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
     * @param fornamn
     * @param efternamn
     * @param adress
     * @param epost
     * @param telefon
     * @param anstallningsdatum
     * @return 
 */
    
    public boolean laggTillAnstalld(String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum)
    
   {
        
       
        if (!Validering.tomFalt(fornamn, "fornamn") ||
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
    
 // Metoden lägger till ett projekt 
    
    public boolean laggTillProjekt(String pid, String projektnamn, String beskrivning, String startdatum, String slutdatum, String kostnad, String status, String prioritet)
    {
        
       // if (pid == null || projektnamn == null || beskrivning == null || startdatum == null || slutdatum == null || kostnad == null || status == null || prioritet == null || pid.isEmpty() || projektnamn.isEmpty() || beskrivning.isEmpty() || startdatum.isEmpty() ||  slutdatum.isEmpty() || kostnad.isEmpty() || status.isEmpty() || prioritet.isEmpty()) {
          if (!Validering.tomFalt(pid, "pid") ||
             !Validering.tomFalt(projektnamn, "projektnamn") ||
             !Validering.tomFalt(beskrivning, "beskrivning") ||
             !Validering.giltigtDatum(startdatum) ||
             !Validering.giltigtDatum(slutdatum) ||
             !Validering.giltigDouble(kostnad) ||
             !Validering.tomFalt(status, "status") ||
             !Validering.tomFalt(prioritet, "prioritet")) {

           System.out.println("Du har glömt att fylla i ett eller fler fält. Projekt kan inte läggas till");
           return false;
            }
       
        try
        {
            String laggTill = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet) VALUES ('" + pid + "', '" + projektnamn + "', '" + beskrivning + "', '" + startdatum + "', '" + slutdatum + "', '" + kostnad + "', '"  + status + "', '"  + prioritet + "')";
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
    

