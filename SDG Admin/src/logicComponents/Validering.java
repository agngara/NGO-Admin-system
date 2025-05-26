/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents;

import javax.swing.JOptionPane;

/**
 *
 * @author theow
 * Denna fil innehåller metoder som kallas på vid behov av datavalidering
 */
public class Validering {
    
 public static boolean tomFalt(String text, String faltnamn) {
     if (text == null || text.trim().isEmpty()) {
         JOptionPane.showMessageDialog(null, faltnamn + " får inte vara tom. ");
         return false;
     }
     
     return true;
 }
 
 public static boolean giltigEpost(String epost) {
     if (!epost.matches("^[\\\\w.-]+@[\\\\w.-]+\\\\.\\\\w+$")) {
         JOptionPane.showMessageDialog(null, "Ogilitig ePost-adress. ");
         return false;
     }
     
    return true;
 }

 public static boolean giltigtTelefonnummer(String telefon) {
     if (!telefon.matches("^\\d{7,15}$")) {
     JOptionPane.showMessageDialog(null, "Ogilitig telefonnummer. ");
     return false;
 }
     
     
     return true; 
 }
      
  public static boolean giltigtDatum(String datum) {
      if (!datum.matches("^\\\\d{4}-\\\\d{2}-\\\\d{2}$")) {
         JOptionPane.showMessageDialog(null, "Datum måste vara u formatet ÅÅÅÅ-MM-DD.");
         return false;
      }
      
     return true; 
     
  }
     
  
    public static boolean giltigInt (String text, String faltnamn) {
        try {
            Integer.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, faltnamn + " måste vara ett helttal.m ");
            return false;
        }
        
    }
    
    
    public static boolean giltigDouble(String text, String faltnamn) {
        try {
            Double.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, faltnamn + " måste vara ett tal (t.ex 123,54");
          return false;
        }
    } 
    
    

    public static boolean giltigDouble(String kostnad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
 
                   
                   
                   
       
       
       
    
    
    
}
