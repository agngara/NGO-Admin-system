/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Hallbarhetsmål;
/**
 *
 * @author theow
 */
public class HallbarhetsmålHanterare {
    
   private InfDB idb;
   private HashMap<String, String> hallbarhetsmål;
   private String query;
   private String hid;
  
   public HallbarhetsmålHanterare(String hid) {
       
       this.hid = hid;
       query = "SELECT * FROM anstalld WHERE hid = " + "'" + hid + "'";
       idb = DatabaseInterface.databaseConnection();
       
       try {
           
           hallbarhetsmål = idb.fetchRow(query);
           
       } catch (Exception exception) {
           
       }
        
       
   }
   
   public HashMap getHallbarhetsmal() {
       
       return hallbarhetsmål;
   }
   
   
   
  public boolean laggTillUppgifterHallbarhetsmal(String namn, String malnummer, String beskrivning, String prioritet)
    {
        
        try
        {
            String nyttHallbarhetsmal = "INSERT INTO hallbarhetsmal (namn, malnummer, beskrivning, prioritet) VALUES ('" + namn + "', '" + malnummer + "', '" + beskrivning + "', '" + prioritet + "')";
            idb.insert(nyttHallbarhetsmal);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
    
    
    
    public boolean andraNamn(String hid, String nyttNamn)
    {
        try{
            String namn = "UPDATE hallbarhetsmal SET namn = '" + nyttNamn + "WHERE hid = '" + "'";
            idb.update(namn);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean andraMalnummer(String hid, String nyttMalnummer)
    {
        try{
            String malnummer = "UPDATE hallbarhetsmal SET malnummer = '" + nyttMalnummer + "WHERE hid = '" + "'";
            idb.update(malnummer);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean andraBeskrivning(String hid, String nyBeskrivning)
    {
        try{
            String beskrivning = "UPDATE hallbarhetsmal SET beskrivning = '" + nyBeskrivning + "WHERE hid = '" + "'";
            idb.update(beskrivning);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    
     public boolean andraPrioritet (String hid, String nyPrioritet)
    {
        try{
            String prioritet = "UPDATE hallbarhetsmal SET prioritet = '" + nyPrioritet + "WHERE hid = '" + "'";
            idb.update(prioritet);
            return true;
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean andraUppgiferHallbarhetsmal(Hallbarhetsmål h)
    {
        try 
        {
            String andraHallbarhetsmal = "UPDATE hallbarhetsmal SET " + "namn" + h.getNamn() +  "', " +
                    "malnummer = '" + h.getMalnummer() + "', " +
                    "beskrivning = '" + h.getBeskrivning() + "', "  +
                    "prioritet = '" + h.getPrioritet() + "', " +
                    "WHERE hid = '" + h.getHid() + "'";
            
                    idb.update(andraHallbarhetsmal);
                    return true;
                    
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
            
      public boolean taBortHallbarhetsmal(Hallbarhetsmål h)
{
try {
    String taBort = "DELETE FROM hallbarhetsmal WHERE hid = '" + h.getHid() + "'";
    idb.delete(taBort);
    return true;
}

    catch (Exception e) {
        
    e.printStackTrace();
    return false;
}

}    
    
}
