/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;
import oru.inf.InfDB;
import java.util.HashMap;
/**
 * 
 * @author theow
 * 
 */



 public class Anstalld {
    
   private String fornamn;
   private String efternamn;
   private String adress;
   private String telefonnummer;
   private String losenord;
   private String ePost;
   private String anstallningsdatum; 
   private String aid;
   
   
   
    public Anstalld (HashMap<String, String> anstalld)
    
    {
        
        fornamn = anstalld.get("fornamn");
        efternamn = anstalld.get("efternamn");
        adress = anstalld.get("adress");
        telefonnummer = anstalld.get("telefonnummer");
        losenord = anstalld.get("losenord");
        ePost = anstalld.get("losenord");
        anstallningsdatum = anstalld.get("anstallningsdatum");
        aid = anstalld.get(aid);
        
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
        
        public String getTelefonnummer()
        {
            return telefonnummer;
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
        
    
    
 }
    


