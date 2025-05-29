/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents;

import javax.swing.JOptionPane;
import orgEntities.Anstalld;
import orgEntities.Avdelning;
import orgEntities.Projekt;
import db.DatabaseInterface;
import java.util.HashMap;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author theow
 * Denna fil innehåller metoder som kallas på vid behov av datavalidering
 */
public class Validering {
    
    private static InfDB idb = DatabaseInterface.databaseConnection();
    
 public static boolean tomFalt(String text, String faltnamn) {
     if (text == null || text.trim().isEmpty()) {
         JOptionPane.showMessageDialog(null, faltnamn + " får inte vara tom. ");
         return false;
     }
     
     return true;
 }
 
 public static boolean giltigEpost(String epost) {
     //er gamla regex !epost.matches("^\\w.-]+@[\\w.-]+\\.\\w+$")
     if (epost == null)  {
         JOptionPane.showMessageDialog(null, "Ogilitig ePost-adress. ");
         return false;
     }
     
    return true;
 }

// public static boolean giltigtTelefonnummer(String telefon) {
//     if (telefon == null || !telefon.matches("^\\d{7,15}$")) {
//     JOptionPane.showMessageDialog(null, "Ogilitig telefonnummer. ");
//     return false;
// }
//     
//     
//     return true; 
// }
 
public static boolean giltigtTelefonnummer(String telefon) {
    if (telefon == null) return false;
    String cleaned = telefon.replaceAll("[\\s\\-]", "");
    if (!cleaned.matches("^\\d{7,15}$")) {
        JOptionPane.showMessageDialog(null, "Ogiltigt telefonnummer.");
        return false;
    }
    return true;
}
      
  public static boolean giltigtDatum(String datum) {
      if (datum == null || !datum.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
         JOptionPane.showMessageDialog(null, "Datum måste vara u formatet ÅÅÅÅ-MM-DD.");
         return false;
      }
      
     return true; 
     
  }
     
  
  
    
    
    public static boolean giltigDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, " måste vara ett tal (t.ex 123,54");
          return false;
        }
    } 

    
    
    public static boolean finnsHandlaggare(String aid) {
        String varde = "";
        String query = "SELECT aid FROM handlaggare WHERE aid = " + aid + ";";
        try {
            varde = idb.fetchSingle(query);
            }
        catch (InfException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        
        if (varde == null) {
            System.out.println("Handlaggaren finns inte i databasen");
            return false;
        } 
        
        return true;
    }
    
    public static boolean finnsHandlaggareIprojekt(String aid, String pid) {
        
        String query = "SELECT * FROM ans_proj WHERE pid = " + pid + " AND aid = " + aid;
        System.out.println(query);
        HashMap<String, String> row = new HashMap();
        
        try {
            row = idb.fetchRow(query);
        } catch (InfException e) {
            
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        if (row.get("pid").equals(pid) && row.get("pid").equals(pid)) {
             
            return true;
        }
        
        
        return false;
    }
      

    }
    
    
 
                   
                   
                   
       
       
       
    
    
    

