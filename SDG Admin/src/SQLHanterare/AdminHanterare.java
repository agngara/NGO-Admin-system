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
 * Denna klass ansvarar för att hantera funktionalitet kopplad till 
 * administratörer i systemet.
 * Klassen sökter kontakten med sql och metoder är tillgängliga här för att
 * lägga till anställda (vilket egentligen bör ligga i anställdhanetraren)
 * 
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

   // SKA DENNA VA MED? VAD HÄNDER OM DENNA TAS BORT?
    public AdminHanterare() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    public HashMap getAdmin() {
        
        return admin;
        
    }
    // metoden skapar ett random lösen och används sedan i metoden laggTillAnstalld
    
    public String genereraLosenord() {
       return UUID.randomUUID().toString();
   }
    /**
 * Metoden nedan lägger till en anställd 
 * 
     * @param losenord
     * @param fornamn
     * @param efternamn 6465
     * @param adress
     * @param epost
     * @param telefon
     * @param anstallningsdatum
     * @return 
     *  Denna kod är avsedd för att lägga till en ny anställd
     * Den skapar ävet ett slumpmässigt lösenord genom UUID
     * och ett slumpmässigt aid
     * Om lösenord inte inmatas så skapas ett genererat losen ord, via metoden genereraLosenord
 */
    
//Denna är korrigerad, så om denna metod finns på annan plats, använd denna!!
    
    public boolean laggTillAnstalld(String losenord, String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum, String avdelning)
    
   {
        
        //om lösenord ej inmatas, skapas ett lösenod genom UUID. 
        if (losenord == null || losenord.isEmpty()) {
       
          losenord = genereraLosenord();
        } 
       
        if (
             !Validering.tomFalt(fornamn, "förnamn") ||
             !Validering.tomFalt(efternamn, "efternamn") ||
             !Validering.tomFalt(adress, "adress") ||
             !Validering.giltigEpost(epost) ||
             !Validering.giltigtTelefonnummer(telefon) ||
             !Validering.giltigtDatum(anstallningsdatum) ||
             !Validering.tomFalt(avdelning, "avdelning")) {
        
          
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
             
         
         
         
            String laggTill = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning) " + 
            "VALUES ('" + nyttAid + "', '" + fornamn + "', '" + efternamn + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "', " + avdelning + ")";
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
    
   
    
    
}
    

