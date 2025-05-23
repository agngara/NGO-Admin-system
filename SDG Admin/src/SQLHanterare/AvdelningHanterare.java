/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import oru.inf.InfException;


/**
 *
 * @author theow
 */
public class AvdelningHanterare {
    
   private InfDB idb;
   private HashMap<String, String> avdelning;
   private String query;
   private String avdid;
   
   public AvdelningHanterare(String avdid) {
       
       this.avdid = avdid;
       query = "SELECT * FROM anstalld WHERE avdid = " + "'" + avdid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           avdelning = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
   public HashMap getAvdelning() {
       
       return avdelning;
   }
    
  //Metoden nedan är avsedd för att kunna lägga till en ny avdelning. 
   public class AvdelningTest {
    
    
    public boolean laggTillAvdelning(String avdid, String namn, String beskrivning)
    {
        
          if (avdid.isEmpty() || namn == null || beskrivning == null || avdid.isEmpty() || namn.isEmpty() || beskrivning.isEmpty()) {
          
           System.out.println("Du har glömt att fylla i ett eller fler fält. Avdelning kan inte läggas till");
           return false;
           
       }
        
        
        try {
            String nyAvdelning = "INSERT INTO avdelning (avdid, namn, beskrivning) VALUES ('" + avdid + "', '" + namn + "', '" + beskrivning + "')";
            idb.insert(nyAvdelning);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
   /**
 * Metoderna nedan är avsedda för att ändra uppgifter om avdelning
 * Koderna använder sig av validerig som säkerställer att uppgifter om avdid fylls i samt fältet som önskas ändras-
 * Om fälten är ifyllda korrekt, så uppdateras avdelning med de nya uppgifterna
 * 
 */ 
    
    public boolean andraNamn(String avdid, String nyttNamn)
    {
        {
          if (avdid == null || nyttNamn == null || avdid.isEmpty() || nyttNamn.isEmpty()){
          
           System.out.println("avdid eller namn får inte vara tommna.");
           return false;
        }
        
        
        try {
            String namn = "UPDATE avdelning SET namn = '" + nyttNamn + "'WHERE avdid'" + avdid  + "'";
            idb.update(namn);
            return true;
          }  
         catch (InfException e)
                 {
                 e.printStackTrace();
                 return false;
                 }
                 
        
    }
    }
    
    public boolean andraBeskrivning(String avdid, String nyBeskrivning)
    {
        
         {
          if (avdid == null || nyBeskrivning == null || avdid.isEmpty() || nyBeskrivning.isEmpty()){
          
           System.out.println("avdid eller beskrivning får inte vara tommna.");
           return false;
        }
        try {
            String beskrivning = "UPDATE avdelning SET beskrivning = '" + nyBeskrivning + "'WHERE avdid'" + avdid + "'"; 
            idb.update(beskrivning);
            return true;
            
            } catch (InfException e) {
                    
              e.printStackTrace();
              return false;
                    }
        
    }
    
    
    }
    
    public boolean andraAdress(String avdid, String nyAdress)
    {
        {
          if (avdid == null || nyAdress == null || avdid.isEmpty() || nyAdress.isEmpty()){
          
           System.out.println("avdid eller adress får inte vara tom.");
           return false;
        }
        
        
        try{
            String adress = "UPDATE avdelning SET adress = '" + nyAdress + "'WHERE aid'" + avdid + "'";
            idb.update(adress);
            return true;
            
            } catch (InfException e)
                    {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    }  
    
    public boolean andraEpost(String avdid, String nyEpost)
    {
         {
          if (avdid == null || nyEpost == null || avdid.isEmpty() || nyEpost.isEmpty()){
          
           System.out.println("avdid eller epost får inte vara tom.");
           return false;
        }
  
        try{
          String epost = "UPDATE avdelning SET epost = '" + nyEpost + "'WHERE aid'" + avdid + "'";
            idb.update(epost);
            return true;  
        }
            
            
            catch (InfException e) {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    }
   }
    
    public boolean andraTelefon(String avdid, String nyttTelefonnummer) {
            
            
         {
          if (avdid == null || nyttTelefonnummer == null || avdid.isEmpty() || nyttTelefonnummer.isEmpty()){
          
           System.out.println("avdid eller telefon får inte vara tom.");
           return false;
        }
    {
        try{
            String telefonnummer = "UPDATE avdelning SET telefon = '" + nyttTelefonnummer + "WHERE aid" + avdid + "'";
            idb.update(telefonnummer);
            return true;
            
           } catch (InfException e) {
               
           
                    e.printStackTrace();
                    return false;
           }
        
    }
    
   }
         
    }
    
   }
         
    
    
            
    
    
    
   
    

    
   
              