/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicComponents.User;

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
    
    
    

    
    
}
