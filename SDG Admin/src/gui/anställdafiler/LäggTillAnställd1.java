/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.anställdafiler;
import java.util.ArrayList;
import orgEntities.*;
import java.util.HashMap;
import SQLHanterare.*;
import gui.Anställda;
import oru.inf.InfDB;
import db.DatabaseInterface;
import javax.swing.JOptionPane;
import oru.inf.InfException;

        

/**
 *
 * @author theow
 */
public class LäggTillAnställd1 extends javax.swing.JFrame {
   
    Anstalld anstalld;
    AnstalldHanterare ah;
    AdminHanterare adh;
    String aid;
    private InfDB idb;

    
      
    /**
     * konstruktor
     */
    public LäggTillAnställd1() {
        anstalld = new Anstalld();
        idb = DatabaseInterface.databaseConnection();
        initComponents();
        fillComboBoxes();
        ah = new AnstalldHanterare(aid);
        adh = new AdminHanterare(aid);
        
    }
        

    // ger comboboxen värden från databasen.
    public void fillComboBoxes() {
        
        //FIll Avdelning
        comboAvdelning1.removeAllItems();
        AvdelningHanterare avdelningHanterare = new AvdelningHanterare();
        ArrayList<HashMap<String,String>> avdelning = avdelningHanterare.getAllAvdelning();
        String namn = "";
 
        for (HashMap<String,String> hashmap : avdelning) {

            namn = hashmap.get("namn");
            comboAvdelning1.addItem(namn);

        }
        String avdelningNamn = anstalld.getAvdelning();
        comboMentor.setSelectedItem(avdelningNamn);
        
        // Fill roll
        comboRoll.removeAllItems();
        comboRoll.addItem("Administratör");
        comboRoll.addItem("Handläggare");
      
}
    
    public void loadHandlaggareComps() {
        
        
        //Fyll mentor
        
        comboMentor.removeAllItems();
        ArrayList<HashMap<String, String>> allaHandlaggare = new HandlaggareHanterare().getAllHandlaggare();
        for (HashMap<String, String> handlaggare : allaHandlaggare) {
            
            String aid = handlaggare.get("aid");
            AnstalldHanterare anstalldHanterare = new AnstalldHanterare(aid, "filler");
            Anstalld anstalld = new Anstalld(anstalldHanterare);
            
            String fornamn = anstalld.getFornamn();
            String efternamn = anstalld.getEfternamn();
            String displayHandlaggare = fornamn + " " + efternamn + " (" + aid + ")"; 
            comboMentor.addItem(displayHandlaggare);
               
        }
        comboMentor.setVisible(true);
        
        //Visa ansvarsområde
        txtAnsvar.setVisible(true);

    }
    
    public void loadAdminComps() {
        
        comboBehorighet.setVisible(true);
        comboBehorighet.addItem("Behörighetsnivå 1");
        comboBehorighet.addItem("Behörighetsnivå 2");
        
        
        
    }
     
    //  sparar den nya datan till databasen   
    private void sparaAnstalld() {
       
        
        String adress = txtAdress1.getText();
        String efternamn = txtEfternamn.getText();
        String fornamn = txtFornamn.getText();
        String losenord = txtLosenord.getText();
        String telefon = txtTelefon.getText();
        String epost = txtEpost.getText();
        String anstallningsdatum = txtAnsDatum.getText();
        String avdelning = (String) comboAvdelning1.getSelectedItem();
        
        String avdelningsId = "1";
        
        try {
            String sqlFraga = "select avdid from avdelning where namn = '" + avdelning + "'";
            avdelningsId = idb.fetchSingle(sqlFraga);
        } catch (InfException ex) {}
        
       
        boolean ok = adh.laggTillAnstalld(losenord, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, avdelningsId);

        
        
        // Lägger till hnadläggare
        if (comboRoll.getSelectedItem().equals("Handläggare")) {
            
           String mentor = (String) comboMentor.getSelectedItem();
           String ansvarsområde = txtAnsvar.getText();
           if  (!new HandlaggareHanterare().laggTillHandlaggare(ansvarsområde, mentor)) {
               
               JOptionPane.showMessageDialog(rootPane, "Kunde inte registrera handläggaren. Var god undersök att inget fält har lämnas tomt..");
               ok = false;
           }
           
           else {
               
               String behorighetsniva = (String) comboBehorighet.getSelectedItem();
               if (!new AdminHanterare().laggTillAdmin(behorighetsniva)) {
                   
                   JOptionPane.showMessageDialog(rootPane, "Kunde inte registrera admin. Var god undersök att inte 'Behörighetsnivå' har lämnats tomt.");
                   ok = false;
                   
               }   
           }
        }
        
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(this, "Anställd sparad!");
                this.setVisible(false);
        }   else {
                javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte spara anställd.");
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

        lblLosenord = new javax.swing.JLabel();
        lblAnsDatum = new javax.swing.JLabel();
        lblAdress = new javax.swing.JLabel();
        lblFornamn = new javax.swing.JLabel();
        lblEfternamn = new javax.swing.JLabel();
        lblAvdelning = new javax.swing.JLabel();
        txtLosenord = new javax.swing.JTextField();
        txtFornamn = new javax.swing.JTextField();
        txtEfternamn = new javax.swing.JTextField();
        txtAnsDatum = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnSparaAnstalld = new javax.swing.JButton();
        comboMentor = new javax.swing.JComboBox<>();
        lblFörklarDatum = new javax.swing.JLabel();
        txtEpost = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        bnGenereraLosen = new javax.swing.JButton();
        btnTillbakaTillAnställd1 = new javax.swing.JToggleButton();
        txtAdress1 = new javax.swing.JTextField();
        txtAnsvar = new javax.swing.JTextField();
        lblAnsvar = new javax.swing.JLabel();
        comboAvdelning1 = new javax.swing.JComboBox<>();
        lblAvdelning1 = new javax.swing.JLabel();
        comboRoll = new javax.swing.JComboBox<>();
        lblMentor = new javax.swing.JLabel();
        comboBehorighet = new javax.swing.JComboBox<>();
        lblBehorighet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblLosenord.setText("Lösenord");

        lblAnsDatum.setText("Anställningsdatum");

        lblAdress.setText("Adress");

        lblFornamn.setText("Förnamn");

        lblEfternamn.setText("Efternamn");

        lblAvdelning.setText("Avdelning");

        txtLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLosenordActionPerformed(evt);
            }
        });

        txtFornamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFornamnActionPerformed(evt);
            }
        });

        txtEfternamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfternamnActionPerformed(evt);
            }
        });

        txtAnsDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnsDatumActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Lägg till Anställd");

        btnSparaAnstalld.setBackground(new java.awt.Color(7, 96, 216));
        btnSparaAnstalld.setForeground(new java.awt.Color(255, 255, 255));
        btnSparaAnstalld.setText("Spara");
        btnSparaAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAnstalldActionPerformed(evt);
            }
        });

        lblFörklarDatum.setText("ÅÅÅÅ-MM-DD");

        txtEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEpostActionPerformed(evt);
            }
        });

        lblEpost.setText("E-post");

        txtTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonActionPerformed(evt);
            }
        });

        lblTelefon.setText("Telefon");

        bnGenereraLosen.setText("Generera Lösenord");

        btnTillbakaTillAnställd1.setBackground(new java.awt.Color(7, 96, 216));
        btnTillbakaTillAnställd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turn-left-small.png"))); // NOI18N
        btnTillbakaTillAnställd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaTillAnställd1ActionPerformed(evt);
            }
        });

        txtAdress1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdress1ActionPerformed(evt);
            }
        });

        txtAnsvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnsvarActionPerformed(evt);
            }
        });

        lblAnsvar.setText("Ansvarighetsområde");

        lblAvdelning1.setText("Roll");

        lblMentor.setText("Mentor");

        lblBehorighet.setText("Brehörighetsnivå");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnTillbakaTillAnställd1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(441, 441, 441)
                        .addComponent(lblLosenord))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(txtLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(193, 193, 193)
                        .addComponent(bnGenereraLosen))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblAnsDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(393, 393, 393)
                        .addComponent(lblAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtAnsDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFörklarDatum)
                        .addGap(95, 95, 95)
                        .addComponent(comboAvdelning1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(453, 453, 453)
                        .addComponent(lblAvdelning1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(comboRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(193, 193, 193)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAnsvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAnsvar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBehorighet, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBehorighet, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(193, 193, 193)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboMentor, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMentor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTillbakaTillAnställd1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFornamn)
                    .addComponent(lblLosenord))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEfternamn)
                        .addGap(6, 6, 6)
                        .addComponent(txtEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(bnGenereraLosen)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblAnsDatum))
                    .addComponent(lblAvdelning))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(txtAnsDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblFörklarDatum))
                    .addComponent(comboAvdelning1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblTelefon))
                    .addComponent(lblAvdelning1))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboRoll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEpost)
                        .addGap(6, 6, 6)
                        .addComponent(txtEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMentor)
                        .addGap(1, 1, 1)
                        .addComponent(comboMentor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress)
                        .addGap(12, 12, 12)
                        .addComponent(txtAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnsvar)
                        .addGap(6, 6, 6)
                        .addComponent(txtAnsvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(lblBehorighet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBehorighet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLosenordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLosenordActionPerformed

    private void txtFornamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornamnActionPerformed

    private void txtEfternamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfternamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfternamnActionPerformed

    private void txtAnsDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnsDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnsDatumActionPerformed

    private void btnSparaAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaAnstalldActionPerformed
          sparaAnstalld();     
    }//GEN-LAST:event_btnSparaAnstalldActionPerformed

    private void txtEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEpostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpostActionPerformed

    private void txtTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonActionPerformed

    private void btnTillbakaTillAnställd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaTillAnställd1ActionPerformed
        this.setVisible(false);
        Anställda anst = new Anställda();
        anst.setVisible(true);
    }//GEN-LAST:event_btnTillbakaTillAnställd1ActionPerformed

    private void txtAdress1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdress1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdress1ActionPerformed

    private void txtAnsvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnsvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnsvarActionPerformed

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
            java.util.logging.Logger.getLogger(LäggTillAnställd1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new EditProjectFields().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnGenereraLosen;
    private javax.swing.JButton btnSparaAnstalld;
    private javax.swing.JToggleButton btnTillbakaTillAnställd1;
    private javax.swing.JComboBox<String> comboAvdelning1;
    private javax.swing.JComboBox<String> comboBehorighet;
    private javax.swing.JComboBox<String> comboMentor;
    private javax.swing.JComboBox<String> comboRoll;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAnsDatum;
    private javax.swing.JLabel lblAnsvar;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblAvdelning1;
    private javax.swing.JLabel lblBehorighet;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblFörklarDatum;
    private javax.swing.JLabel lblLosenord;
    private javax.swing.JLabel lblMentor;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtAdress1;
    private javax.swing.JTextField txtAnsDatum;
    private javax.swing.JTextField txtAnsvar;
    private javax.swing.JTextField txtEfternamn;
    private javax.swing.JTextField txtEpost;
    private javax.swing.JTextField txtFornamn;
    private javax.swing.JTextField txtLosenord;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
