/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orgEntities;
import java.util.HashMap;
import oru.inf.InfDB;
import db.DatabaseInterface;
import SQLHanterare.AnstalldHanterare;
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
   
   
   /**
    * This constructor constructs an object of the anstalld class by taking an insance of anstalldhanterare.
    * @param anstalldHanterare 
    */
   public Anstalld(AnstalldHanterare anstalldHanterare) {
       
       HashMap<String, String> anstalldInfo = anstalldHanterare.getAnstalld();
       
        fornamn = anstalldInfo.get("fornamn");
        efternamn = anstalldInfo.get("efternamn");
        adress = anstalldInfo.get("adress");
        telefonnummer = anstalldInfo.get("telefonnummer");
        losenord = anstalldInfo.get("losenord");
        ePost = anstalldInfo.get("losenord");
        anstallningsdatum = anstalldInfo.get("anstallningsdatum");
        aid = anstalldInfo.get(aid);
       
       
       
       
   }
   
    public Anstalld(HashMap<String, String> anstalld)
    
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
    


