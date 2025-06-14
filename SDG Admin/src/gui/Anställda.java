/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import SQLHanterare.AnstalldHanterare;
import SQLHanterare.ProjektHanterare;
import oru.inf.InfDB;
import oru.inf.InfException;
import db.DatabaseInterface;
import gui.Anställda;
import gui.Meny;
import gui.anställdafiler.LäggTillAnställd1;
import gui.anställdafiler.EditAnställda1;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicComponents.User.CurrentUser;
import logicComponents.User.User;
import logicComponents.User.UserType;
import orgEntities.Anstalld;

/**
 *
 * 
 * @author stina
 */
public class Anställda extends javax.swing.JFrame {
    //test
    private Anstalld anstalld; 
    private InfDB idb;
    private String aid;
    private Anställda anställda;
    /**
     * Konstruktor för klasssen anställda
     */
    public Anställda() {
        initComponents(); 
        setExtendedState(MAXIMIZED_BOTH); // Maximerar förnstret
        setLocationRelativeTo(null); // centrerar fönstret
        anställda = this;
        try {
        idb = DatabaseInterface.databaseConnection();
        fyllTabell(); // fyller tabellen från databasen
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Kunde inte ansluta till databasen");
        }
        
        bnSokHanlaggare.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        bnSokHandlaggareActionPerformed(evt); 
      }
        
    });
         try {
            this.tableMouseEvent(anställda);
        } 
        catch (Exception e) {
        
            System.out.println(e);
        }
    }

   // hämtar anställda från databasen och fyller tabellen
    private void fyllTabell(){
        if (CurrentUser.getUsr().getUserType() != UserType.admin1 && CurrentUser.getUsr().getUserType() != UserType.admin2) {
        try {
            String minAvdelning = CurrentUser.getUsr().getAnstalld().getAvdelning();
            String query = "SELECT aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning FROM anstalld WHERE avdelning = '" + minAvdelning + "'";
            ArrayList<HashMap< String, String >> anstalldLista = idb.fetchRows(query);

            String [] columnNames = {"aid", "fornamn", "efternamn", "adress", "epost", "telefon", "anstallningsdatum", "losenord", "avdelning", "Redigera"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
            if (anstalldLista == null || anstalldLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inga anställda hittades i databasen!");
            }
                
            
            else {
                for (HashMap<String, String> anstalld : anstalldLista){
                String anstalldAid = anstalld.get("aid");
                String fornamn = anstalld.get("fornamn");
                String efternamn = anstalld.get ("efternamn");
                String adress = anstalld.get ("adress");
                String epost = anstalld.get("epost");
                String telefon = anstalld.get("telefon");
                String anstallningsdatum = anstalld.get("anstallningsdatum");
                String losenord = anstalld.get("losenord");
                String avdelning = anstalld.get("avdelning");
                model.addRow(new Object[] {anstalldAid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning, "Redigera"});
        }
    }
        tblAnställda.setModel(model);
        }
       catch (InfException e) {
        JOptionPane.showMessageDialog(this, "Fel vid hämtning av data: " + e.getMessage());
        }  
    }
        else {
                            
                ArrayList<HashMap< String, String >> anstalldListaAll = new ArrayList<>();
                String [] columnNames = {"aid", "fornamn", "efternamn", "adress", "epost", "telefon", "anstallningsdatum", "losenord", "avdelning", "Redigera"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                String query = "SELECT * FROM anstalld";
                try {
                anstalldListaAll = idb.fetchRows(query);
                } catch (InfException e) {
                    
                    e.printStackTrace();
                    System.out.println(e);
                }
                for (HashMap<String, String> anstalld1 : anstalldListaAll){
                String anstalldAid = anstalld1.get("aid");
                String fornamn = anstalld1.get("fornamn");
                String efternamn = anstalld1.get ("efternamn");
                String adress = anstalld1.get ("adress");
                String epost = anstalld1.get("epost");
                String telefon = anstalld1.get("telefon");
                String anstallningsdatum = anstalld1.get("anstallningsdatum");
                String losenord = anstalld1.get("losenord");
                String avdelning = anstalld1.get("avdelning");
                model.addRow(new Object[] {anstalldAid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning, "Redigera"});
                
                }
                    tblAnställda.setModel(model);

        }
    }
    
    private void bnSokHandlaggareActionPerformed(java.awt.event.ActionEvent evt) { // metoden körs när kanppen trycks på
    try {
        String sokHandlaggare = txtSokHandlaggare.getText();
       
        
        if (sokHandlaggare.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ange namn eller epost."); // Om inget eller fel namn eller epost skrivs in kommer ett meddelande upp 
            return; 
        }

            String query = "SELECT * FROM anstalld WHERE fornamn = '" + sokHandlaggare + "' OR efternamn = '" + sokHandlaggare + "' OR epost = '" + sokHandlaggare + "'";
                // söker på anställda utifrån det namn eller epost som anges i sökrutan
            System.out.println("SQL-fråga: " + query);
            ArrayList<HashMap<String, String>> anstalldlista = idb.fetchRows(query); // kör frågan mot databasen för att se vilka namn och epost som matchar, returnerar en lista med handläggare som matchar 
        if (anstalldlista == null || anstalldlista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sök endast på förnamn, efternamn eller epost."); // om inga handläggare hittades med matchande namn eller epost kommer ett meddelande upp med den informationen
        return;
        }
        
    String [] columnNames = {"aid", "fornamn", "efternamn", "adress", "epost", "telefon", "anställningsdatum", "losenord", "avdelning", "redigera"}; // kolumnnamn som ska användas i tabellen
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    for (HashMap<String, String> anstalld : anstalldlista) { // loopar igenom alla anställda
        String aid = anstalld.get("aid");
        String fornamn = anstalld.get("fornamn");
        String efternamn = anstalld.get ("efternamn");
        String adress = anstalld.get("adress");
        String epost = anstalld.get("epost");
        String telefon = anstalld.get("telefon");
        String anstallningsdatum = anstalld.get("anstallningsdatum");
        String losenord = anstalld.get("losenord"); 
        String avdelning = anstalld.get("avdelning");
// hämtar ut fälten från anstaalld
        model.addRow(new Object[]{aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning, "redigera"}); 
        System.out.println("SQL-fråga: " + query);// lägger till en rad för varje del
        
    }
    
    tblAnställda.setModel(model);
    } catch (InfException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fel vid hämtning: " + e.getMessage()); // meddelande som visas om det vart fel
    return;
    }
    }
        
 
  // Lägger till en klick-lyssnare på tabelln, för redigering av en anställd
 public void tableMouseEvent(Anställda anställda) {
        
    tblAnställda.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = tblAnställda.rowAtPoint(evt.getPoint());
            int col = tblAnställda.columnAtPoint(evt.getPoint());
            if (row >= 0 && col == 9) {
                UserType typ = CurrentUser.getUsr().getUserType();
                if (typ == UserType.admin1 || typ == UserType.admin2) {
                    Object varde = tblAnställda.getValueAt(row, 0);
                    String aid = varde.toString();
                    AnstalldHanterare anstalldHanterare = new AnstalldHanterare(aid, "filler");
                    Anstalld anstalld = new Anstalld(anstalldHanterare);
                    EditAnställda1 editAnställda1 = new EditAnställda1(anstalld);
                    editAnställda1.setVisible(true);
    //              anställda.setVisible(true);
                } else { 
                JOptionPane.showMessageDialog(anställda, "Endast admin kan redigera anställda");

            }
        }
    }
    }
    );    
 
            
 
         }
 // Ta bort anställd
 public void removeAnställd(String aid) {
        
        if (new AnstalldHanterare().taBortAnstalld(aid)) {
            JOptionPane.showMessageDialog(rootPane, "Anställd borttagen.");
            fyllTabell();
            
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "Kunde inte ta bort anställd.");

        }
    }
           
    
// Denna kod är avsedd för att handläggaren ska kunna söka efter en specifik
// * handläggare på avdelningen, genom namn eller epost. 
 
 public ArrayList<HashMap<String, String>> sokHandlaggare(String avdid, String sok)
   {
       try{
           String sokning = "SELECT * FROM anstalld " +
                   "WHERE avdid = '" + avdid + "' " +
                   // exsists kollar om selecten inanför parantesterna retunerar minst en rad. 
                   "AND EXISTS(SELECT 1 FROM handlaggare WHERE handlaggare.aid = anstalld.aid)" +
                   "AND (fornamn LIKE '%" + sok + "%' " +
                   "OR efternamn LIKE '%" + sok + "%' " +
                   "OR epost LIKE '%" + sok + "%')";
           return idb.fetchRows(sokning);
  
       }
       
      catch (InfException e) {
          e.printStackTrace();
          return new ArrayList<>();
       
      }
     
   }  

 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ansTillbakaTillMeny = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnställda = new javax.swing.JTable();
        bnLaggTillAnstalld = new javax.swing.JButton();
        bnTaBortAnstalld = new javax.swing.JButton();
        bnSokHanlaggare = new javax.swing.JButton();
        txtSokHandlaggare = new javax.swing.JTextField();
        lblSokHandlaggare = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Anställda:");
        jLabel1.setToolTipText("");

        ansTillbakaTillMeny.setBackground(new java.awt.Color(0, 102, 255));
        ansTillbakaTillMeny.setForeground(new java.awt.Color(255, 255, 255));
        ansTillbakaTillMeny.setText("Tillbaka till meny");
        ansTillbakaTillMeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ansTillbakaTillMenyActionPerformed(evt);
            }
        });

        tblAnställda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Anställnings ID", "Förnamn", "Efternamn", "Adress", "Epost", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning", "Redigera"
            }
        ));
        jScrollPane1.setViewportView(tblAnställda);

        bnLaggTillAnstalld.setBackground(new java.awt.Color(0, 204, 0));
        bnLaggTillAnstalld.setForeground(new java.awt.Color(255, 255, 255));
        bnLaggTillAnstalld.setText("Lägg till anställd");
        bnLaggTillAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnLaggTillAnstalldActionPerformed(evt);
            }
        });

        bnTaBortAnstalld.setBackground(new java.awt.Color(204, 0, 0));
        bnTaBortAnstalld.setForeground(new java.awt.Color(255, 255, 255));
        bnTaBortAnstalld.setText("Ta bort anställd");
        bnTaBortAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortAnstalldActionPerformed(evt);
            }
        });

        bnSokHanlaggare.setText("Sök");
        bnSokHanlaggare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnSokHanlaggareActionPerformed(evt);
            }
        });

        txtSokHandlaggare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSokHandlaggareActionPerformed(evt);
            }
        });

        lblSokHandlaggare.setText("Sök handläggare");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ansTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnTaBortAnstalld)
                        .addGap(43, 43, 43)
                        .addComponent(bnLaggTillAnstalld))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(341, 341, 341)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSokHandlaggare)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSokHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bnSokHanlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblSokHandlaggare)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bnSokHanlaggare)
                        .addComponent(txtSokHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnLaggTillAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ansTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnTaBortAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ansTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ansTillbakaTillMenyActionPerformed
        this.setVisible(false);
        new Meny().setVisible(true);
    }//GEN-LAST:event_ansTillbakaTillMenyActionPerformed

    private void bnLaggTillAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLaggTillAnstalldActionPerformed
        new LäggTillAnställd1().setVisible(true);
//        this.setVisible(false);   

    }//GEN-LAST:event_bnLaggTillAnstalldActionPerformed

    private void bnTaBortAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortAnstalldActionPerformed
       
   // kontrollerar om en rad i tabellen är vald, om ja anropas removeAnställd(aid) dör att ta bort den anställda.      
        int selectedRow = tblAnställda.getSelectedRow();
          //int column = 1; 
          if (selectedRow == -1) {
              System.out.println("Ingen vald rad");
              return;
          }
          
          int columnIndex = 0;
          String aid = (String) tblAnställda.getValueAt(selectedRow, columnIndex);

       
            
          if (JOptionPane.showConfirmDialog(rootPane, "Är du säker på att du vill ta bort den anställda?") == JOptionPane.YES_OPTION ) {
            
                removeAnställd(aid);
            }
            
            
        
        
    }//GEN-LAST:event_bnTaBortAnstalldActionPerformed

    private void txtSokHandlaggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSokHandlaggareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSokHandlaggareActionPerformed

    private void bnSokHanlaggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnSokHanlaggareActionPerformed
      
    }//GEN-LAST:event_bnSokHanlaggareActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Anställda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Anställda().setVisible(true);
            }
        });
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ansTillbakaTillMeny;
    private javax.swing.JButton bnLaggTillAnstalld;
    private javax.swing.JButton bnSokHanlaggare;
    private javax.swing.JButton bnTaBortAnstalld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSokHandlaggare;
    private javax.swing.JTable tblAnställda;
    private javax.swing.JTextField txtSokHandlaggare;
    // End of variables declaration//GEN-END:variables

    public String getAid() 
    {
       return aid;
    }


}
