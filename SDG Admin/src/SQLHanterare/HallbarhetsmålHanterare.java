/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import orgEntities.Hallbarhetsmål;
import oru.inf.InfException;
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
           
       } catch (InfException exception) {
           
       }
        
       
   }
   
   public HashMap getHallbarhetsmal() {
       
       return hallbarhetsmål;
   }
   
   
   
  public boolean laggTillUppgifterHallbarhetsmal(String namn, String malnummer, String beskrivning, String prioritet)
    {
       
         {
        
          if (namn == null || malnummer == null || beskrivning == null || prioritet == null || namn.isEmpty() || malnummer.isEmpty() || beskrivning.isEmpty() || prioritet.isEmpty()) {
          
           System.out.println("Du har glömt att fylla i ett eller fler fält. Hållbarhetsmål kan inte läggas till");
           return false;
           
       }
        
        
        try
        {
            String nyttHallbarhetsmal = "INSERT INTO hallbarhetsmal (namn, malnummer, beskrivning, prioritet) VALUES ('" + namn + "', '" + malnummer + "', '" + beskrivning + "', '" + prioritet + "')";
            idb.insert(nyttHallbarhetsmal);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
        
        
   
    }
    }  
    
    
    public boolean andraNamn(String hid, String nyttNamn)
            
     {
         if (hid == null || nyttNamn == null || hid.isEmpty() || nyttNamn.isEmpty()) {
             
             
             
             System.out.println("hid eller namn får inte vara tomt");
             return false;
         }
        
     
    
        try{
            String namn = "UPDATE hallbarhetsmal SET namn = '" + nyttNamn + "WHERE hid = '" + "'";
            idb.update(namn);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean andraMalnummer(String hid, String nyttMalnummer)
    {
         {
         if (hid == null || nyttMalnummer == null || hid.isEmpty() || nyttMalnummer.isEmpty()) {
             
             
             
             System.out.println("hid eller målnummer får inte vara tomt");
             return false;
         }
        
        
        
        
        try{
            String malnummer = "UPDATE hallbarhetsmal SET malnummer = '" + nyttMalnummer + "WHERE hid = '" + "'";
            idb.update(malnummer);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    }
    
    
    
    
    public boolean andraBeskrivning(String hid, String nyBeskrivning)
    {
         {
         if (hid == null || nyBeskrivning == null || hid.isEmpty() || nyBeskrivning.isEmpty()) {
             
             
             
             System.out.println("hid eller beskrivning får inte vara tomt");
             return false;
         }
        
        
        
        
        try{
            String beskrivning = "UPDATE hallbarhetsmal SET beskrivning = '" + nyBeskrivning + "WHERE hid = '" + "'";
            idb.update(beskrivning);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    }  
    
     public boolean andraPrioritet (String hid, String nyPrioritet)
             
     {
         if (hid == null || nyPrioritet == null || hid.isEmpty() || nyPrioritet.isEmpty()) {
             
             
             
             System.out.println("hid eller prioritet får inte vara tomt");
             return false;
         }
            
     
     
     {
        try{
            String prioritet = "UPDATE hallbarhetsmal SET prioritet = '" + nyPrioritet + "WHERE hid = '" + "'";
            idb.update(prioritet);
            return true;
        }
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
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
        
        catch (InfException e)
        {
            e.printStackTrace();
            return false;
        }
    }
            
    
    
      public boolean taBortHallbarhetsmal(Hallbarhetsmål h)
{
    try {
    
    String hid = h.getHid();
    
    
    if (hid == null || hid.isEmpty()) {
        System.out.println("Hid är tom");
        return false;
    }
    
    
    String taBort = "DELETE FROM hallbarhetsmal WHERE hid = '" + h.getHid() + "'";
    idb.delete(taBort);
    
    System.out.println("Hållbarhetsmål borttagen: " + h.getNamn());
    return true;
    
}

    catch (InfException e) {
        
    e.printStackTrace();
    return false;
}

}    
    
}
