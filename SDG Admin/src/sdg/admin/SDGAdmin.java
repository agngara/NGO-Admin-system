/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sdg.admin;
import gui.inloggning;
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;




/**
 *
 * @author agnesgaramvolgyi
 * Denna fil innehåller programmets initiala mainmetod.
 */
public class SDGAdmin {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            FlatSpacegrayIJTheme.setup();
            new inloggning().setVisible(true);
        
    
    }
}
