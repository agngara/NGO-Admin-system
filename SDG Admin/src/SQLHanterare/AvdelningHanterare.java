/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;


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
    
    
    public boolean andraNamn(String avdid, String nyttNamn)
    {
        try {
            String namn = "UPDATE avdelning SET namn = '" + nyttNamn + "'WHERE avdid'" + avdid  + "'";
            idb.update(namn);
            return true;
          }  
         catch (Exception e)
                 {
                 e.printStackTrace();
                 return false;
                 }
                 
        
    }
    
    
    public boolean andraBeskrivning(String avdid, String nyBeskrivning)
    {
        try {
            String beskrivning = "UPDATE avdelning SET beskrivning = '" + nyBeskrivning + "'WHERE avdid'" + avdid + "'"; 
            idb.update(beskrivning);
            return true;
            
            } catch (Exception e)
                    {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    
    
    public boolean andraAdress(String avdid, String nyAdress)
    {
        try{
            String adress = "UPDATE avdelning SET adress = '" + nyAdress + "'WHERE aid'" + avdid + "'";
            idb.update(adress);
            return true;
            
            } catch (Exception e)
                    {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    
    public boolean andraEpost(String avdid, String nyEpost)
    {
        try{
          String epost = "UPDATE avdelning SET epost = '" + nyEpost + "'WHERE aid'" + avdid + "'";
            idb.update(epost);
            return true;  
        }
            
            
            catch (Exception e) {
                    e.printStackTrace();
                    return false;
                    }
        
    }
    
    
    
    
    public boolean andraTelefon(String nyttTelefonnummer)
    {
        try{
            String telefonnummer = "UPDATE avdelning SET telefon = '" + nyttTelefonnummer + "WHERE aid" + avdid + "'";
            idb.update(telefonnummer);
            return true;
            
           } catch (Exception e) {
               
           
                    e.printStackTrace();
                    return false;
           }
        
    }
    
    
    
    
    
   
    

  }