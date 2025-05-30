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
   
   public AvdelningHanterare() {
       
       idb = DatabaseInterface.databaseConnection();
       
   }
   
   
   
   
   public AvdelningHanterare(String avdid) {
       
       this.avdid = avdid;
       query = "SELECT * FROM avdelning WHERE avdid = " + "'" + avdid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           avdelning = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
   public HashMap getAvdelning() {
       
       return avdelning;
   }
   
    public String Avdid(){
       return avdid;
    }

    public ArrayList<HashMap<String, String>> fetchAllAvdelning() {
        ArrayList<HashMap<String, String>> rows = new ArrayList<>();
        String AllQuery = "SELECT * FROM avdelning";
        try {
            rows = idb.fetchRows(query);
        }
        catch (InfException e) {
            System.out.println(e);
        }
        
        return rows;
    }
    
  //Metoden nedan är avsedd för att kunna lägga till en ny avdelning. 
   
    
    
    public boolean laggTillAvdelning(String namn, String beskrivning, String adress, String epost, String telefon, String stad, String chef)
    {
        
         
        
        
        try {
        
        String korrektAvdid = "SELECT MAX(avdid) FROM avdelning";
        String maxAvdid = idb.fetchSingle(korrektAvdid);
        
        int nyttAvdid = 1;
        if (maxAvdid != null) {
        nyttAvdid = Integer.parseInt(maxAvdid) + 1;   
        }   
            
        String nyAvdelning = "INSERT INTO avdelning (avdid, namn, beskrivning, adress, epost, telefon, stad, chef)" + "VALUES ('" + nyttAvdid + "', '" + namn + "', '" + beskrivning + "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + stad + "', '" + chef + "')";
        idb.insert(nyAvdelning);
        return true; }
        
        catch (InfException e)
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
          if (!Validering.tomFalt(nyttNamn, "namn")) {
          
           System.out.println("avdid eller namn får inte vara tommna.");
           return false;
        }
        
        
        try {
            String namn =  "UPDATE avdelning SET namn = '" + nyttNamn + "' WHERE avdid = " + avdid;
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
            if(!Validering.tomFalt(nyBeskrivning, "beskrivning")) {
          
           System.out.println("avdid eller beskrivning får inte vara tommna.");
           return false;
        }
        try {
            String beskrivning = "UPDATE avdelning SET beskrivning = '" + nyBeskrivning + "' WHERE avdid = " + avdid;
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
          if (!Validering.tomFalt(nyAdress, "adress")) {
          
           System.out.println("avdid eller adress får inte vara tom.");
           return false;
        }
        
        
        try{
            String adress = "UPDATE avdelning SET adress = '" + nyAdress + "' WHERE avdid = " + avdid;
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
             
          if (!Validering.giltigEpost(nyEpost) && !Validering.tomFalt(nyEpost, "epost")) {
           System.out.println("avdid eller epost får inte vara tom.");
           return false;
        }
  
        try{
          String epost = "UPDATE avdelning SET epost = '" + nyEpost + "' WHERE avdid = " + avdid;
            idb.update(epost);
            return true;  
        }
            
            
            catch (InfException e) {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    }
   
    
    public boolean andraTelefon(String avdid, String nyttTelefonnummer) {
            
            
         {
          if (!Validering.giltigtTelefonnummer(nyttTelefonnummer)) {
          
           System.out.println("avdid eller telefon får inte vara tom.");
           return false;
        }
    {
        try{
            String telefonnummer = "UPDATE avdelning SET telefon = '" + nyttTelefonnummer + "' WHERE avdid = " + avdid;
            idb.update(telefonnummer);
            return true;
            
           } catch (InfException e) {
               
           
                    e.printStackTrace();
                    return false;
           }
        
    }
    
   }
    }
         
        public boolean andraChef(String avdid, String nyChef) {
            
            
         {
          if (!Validering.tomFalt(nyChef, "chef")) {
          
           System.out.println("avdid eller telefon får inte vara tom.");
           return false;
        }
    {
        try{
            String chefen = "UPDATE avdelning SET chef = '" + nyChef + "' WHERE avdid = " + avdid;
            idb.update(chefen);
            return true;
            
           } catch (InfException e) {
               
           
                    e.printStackTrace();
                    return false;
           }
        
    }
          
         
    }
         
         
         
        }
    
    public ArrayList<HashMap<String, String>> getAllAvdelning()
  {
  
      ArrayList<HashMap<String, String>> rader = new ArrayList<HashMap<String, String>>();
      try {
          
          String sql = "SELECT * FROM avdelning"; 
          
            rader = idb.fetchRows(sql);
            return rader;
            
      } catch (InfException e) {
          e.printStackTrace();
          System.out.println(e);
      }
        
     return rader;
    
    
   }


    public boolean taBortAvdelning (String avdid) {

        if (!Validering.tomFalt(avdid, "avdelningID")) {
        System.out.println("Avdid är tom");
        return false;
    }
        try { 
    
       // ta bort från alla tabeller där avdid finns som nyckel. 
       
       String taBortAnstalld = "DELETE FROM anstalld WHERE avdid = '" + avdid + "'";
       idb.delete(taBortAnstalld);
       String taBortAvd_hallbarhet = "DELETE FROM avd_hallbarhet WHERE avdid = '" + avdid + "'";
       idb.delete(taBortAvd_hallbarhet);
       String taBort = "DELETE FROM avdelning WHERE avdid = '" + avdid + "'";
       idb.delete(taBort);


        return true;    
    }

        catch (InfException e) {
    
        e.printStackTrace();
    
        return false;
    
    }

}
}


    
    
            
    
    
    
   
    

    
   
              