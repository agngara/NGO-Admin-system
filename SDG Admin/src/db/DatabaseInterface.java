/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 * This file includes a static method which establishes a connection to the database through the InfDB class. This class exists to ease
 * communication with the database.
 * @author theow
 */
public class DatabaseInterface {
    
    private static InfDB idb;

    
    public DatabaseInterface() {
    
    
}
    
    public static InfDB databaseConnection() {
        // TODO code application logic here
        try {
            idb = new InfDB("sdgsweden", "3306", "dbAdmin2024", "dbAdmin2024PW");
            // TESTKOD
        } catch (InfException ex){
            System.out.println(ex.getMessage());
            System.err.println("Fel vid databasanslutning: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return idb;
    }
    
}
