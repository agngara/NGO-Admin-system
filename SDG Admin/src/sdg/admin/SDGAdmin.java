/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sdg.admin;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author agnesgaramvolgyi
 */
public class SDGAdmin {
    private static InfDB idb;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            idb = new InfDB("sdgsweden", "3306", "dbAdmin2024", "dbAdmin2024PW");
            new inloggning(idb).setVisible(true);
        } catch (InfException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
