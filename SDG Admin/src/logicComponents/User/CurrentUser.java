/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents.User;

import SQLHanterare.AnstalldHanterare;
import orgEntities.Anstalld;

/**
 * This class simply stores the instance of user being created in the class 'inloggning'. 
 * @author theow
 */
public class CurrentUser {
    
    private static User usr;
    
    
    public static void setUsr(User user) {
        
        usr = user;
        
    }
    
    public static User getUsr() {
        
        return usr;
        
    }
    
    /**
     * Denna metod laddar om nuvarande användare till den vars aid skrivs in som parameter. 
     * Detta innebära att användarens uppgifter hämtas färskt från databsen, och kan därför användas för att uppdatera programmets minne
     * om den redan inloggade anställdes uppgifter.
     * @param aid 
     */
    public static void loadUser(String aid) {
        
        AnstalldHanterare anstalldHanterare = new AnstalldHanterare(aid, "filler");
        Anstalld anstalld = new Anstalld(anstalldHanterare);
        User user = new User(anstalld);
        setUsr(user);
               
        
    }
    
    
    

    
    
}
