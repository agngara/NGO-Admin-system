/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLHanterare;
import db.DatabaseInterface;
import oru.inf.InfDB;
import java.util.HashMap;
import javax.swing.JOptionPane;
import logicComponents.Validering;
import orgEntities.Hallbarhetsmål;
import oru.inf.InfException;
/**
 * Denna klass ansvarar för att hantera funktionalitet kopplad till 
 * hållbarhetsmål i systemet.
 * Klassen sökter kontakten med sql och metoder är tillgängliga här för att
 * lägga till hållabrhetsmål, ändra uppgifter om hållbarhetsmål samt ta bort hållbarhetsmål (OBS dessa metoder används ej). 
 * 
 * 
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
            if (!Validering.tomFalt(namn, "namn") &&
            //Validering.giltigInt(malnummer, "malnummer") &&
            Validering.tomFalt(malnummer, "malnummer") &&
            Validering.tomFalt(beskrivning, "beskrivning") &&
            Validering.tomFalt(prioritet, "prioritet")) {
         // if (namn == null || malnummer == null || beskrivning == null || prioritet == null || namn.isEmpty() || malnummer.isEmpty() || beskrivning.isEmpty() || prioritet.isEmpty()) {
          
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
         if (!Validering.tomFalt(nyttNamn, "namn")) {
//(hid == null || nyttNamn == null || hid.isEmpty() || nyttNamn.isEmpty()) {
             
             
             
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
    
    
//    public boolean andraMalnummer(int nyttMalnummer, int )
//    {
//         {
//         if (!Validering.giltigInt(nyttMalnummer)) {
////(hid == null || nyttMalnummer == null || hid.isEmpty() || nyttMalnummer.isEmpty()) {
//             
//             
//             
//             System.out.println("hid eller målnummer får inte vara tomt");
//             return false;
//         }
//        
//        
//        
//        
//        try{
//            String malnummer = "UPDATE hallbarhetsmal SET malnummer = '" + nyttMalnummer + "WHERE hid = '" + "'";
//            idb.update(malnummer);
//            return true;
//        }
//        
//        catch (InfException e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    
//    }
    
    
    
    
    public boolean andraBeskrivning(String hid, String nyBeskrivning)
    {
         {
         if (!Validering.tomFalt(nyBeskrivning, "beskrivning")) {
//(hid == null || nyBeskrivning == null || hid.isEmpty() || nyBeskrivning.isEmpty()) {
             
             
             
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
         if (!Validering.tomFalt(nyPrioritet, "nyPrioritet")) {
//(hid == null || nyPrioritet == null || hid.isEmpty() || nyPrioritet.isEmpty()) {
             
             
             
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
   
    
    String hid = h.getHid();
    
    
    if (!Validering.tomFalt(hid, "hid")) {
        return false;
    }
    
    try {
    String taBort = "DELETE FROM hallbarhetsmal WHERE hid = '" + h.getHid() + "'";
    idb.delete(taBort);
    
    JOptionPane.showMessageDialog(null,"Hållbarhetsmål borttagen: " + h.getNamn());
    return true;
    
}

    catch (InfException e) {
        
    e.printStackTrace();
    return false;
}

}    
    
}
