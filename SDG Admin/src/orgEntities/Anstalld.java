/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;
import oru.inf.InfDB;
import java.util.HashMap;
import oru.inf.InfDB;
import db.DatabaseInterface;
import SQLHanterare.AnstalldHanterare;
import logicComponents.User.CurrentUser;
import logicComponents.User.UserType;
/**
 * This class stores information from corresponding table in the application database. Objects must be instantiated-
 * with an "hanterar"-class as argument, as the fields are populated through a HashMap in the "hanterar"-type class.
 * @author theow
 * 
 */



 public class Anstalld {

    
   private String fornamn;
   private String efternamn;
   private String adress;
   private String telefon;
   private String losenord;
   private String ePost;
   private String anstallningsdatum; 
   private String aid;
   
   private AnstalldHanterare anstalldHanterare;
    private String avdelning;
   
   
   /**
    * This constructor constructs an object of the anstalld class by taking an insance of anstalldhanterare.
    * @param anstalldHanterare 
    */
    
    //Skapade en tom konstruktor, kan tas bort om anstalld klassen inte används i LäggTillAnstalld
    public Anstalld() {}
    
   public Anstalld(AnstalldHanterare anstalldHanterare) {
       
       HashMap<String, String> anstalldInfo = anstalldHanterare.getAnstalld();
       
       
        fornamn = anstalldInfo.get("fornamn");
        efternamn = anstalldInfo.get("efternamn");
        adress = anstalldInfo.get("adress");
        telefon = anstalldInfo.get("telefon");
        losenord = anstalldInfo.get("losenord");
        ePost = anstalldInfo.get("losenord");
        anstallningsdatum = anstalldInfo.get("anstallningsdatum");
        aid = anstalldInfo.get("aid");
        
        this.anstalldHanterare = anstalldHanterare;
       
       
       
       
   }
   
    public Anstalld(HashMap<String, String> anstalld)
    
    {
        
        fornamn = anstalld.get("fornamn");
        efternamn = anstalld.get("efternamn");
        adress = anstalld.get("adress");
        telefon = anstalld.get("telefon");
        losenord = anstalld.get("losenord");
        ePost = anstalld.get("ePost");
        anstallningsdatum = anstalld.get("anstallningsdatum");
        aid = anstalld.get("aid");
        
    }  
        
        public String getFornamn()
        {
            return fornamn; 
        }
        
        public String getEfternamn()
        {
            return efternamn;
        }
        
        public String getAdress()
        {
            return adress;
        }
        
        public String getTelefon()
        {
            return telefon;
        }
        
        
        public String getLosenord()
        {
            return losenord;
        }
        
        public String getEpost()
        {
            return ePost;
        }
        
        public String getAid()
        {
            return aid;
        }
        
        public String getAnstallningsdatum() 
        {
            return anstallningsdatum;
        }
        
        public String getAvdelning() 
        {
            return avdelning; 
        }
        
        
        // för att kunna ändra uppgifter för Anställd
        
        public void setFornamn(String nyttFornamn)
        {
            this.fornamn = nyttFornamn;
        }
        
        public void setEfternamn(String nyttEfternamn)
        {
            this.efternamn = nyttEfternamn;
        }
        
        public void setAdress(String nyAdress)
        {
            this.adress = nyAdress;
        }
        
        public void setTelefon(String nyttTelefon)
        {
            this.telefon = nyttTelefon;
        }
        
        public void setLosenord(String nyttLosenord)
        {
            this.losenord = nyttLosenord;
        }
        
        public void setEpost(String nyEpost)
        {
            this.ePost = nyEpost;
        }
                  
       
        // Extra
        
        public UserType getRole(String aid) {
            
            
            UserType userType = anstalldHanterare.fetchRole(aid);
            
            
            return userType;
            
          
        }



 }
    


