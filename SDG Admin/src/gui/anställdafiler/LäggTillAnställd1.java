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
     * Creates new form EditProjectFields
     */
    public LäggTillAnställd1() {
        anstalld = new Anstalld();
        idb = DatabaseInterface.databaseConnection();
        initComponents();
        fillComboBoxes();
        
        //aid = anstalld.getAid();
        ah = new AnstalldHanterare(aid);
        adh = new AdminHanterare(aid);
        
    }
    
    //Kan tas bort
//    public void setTextBoxes() {
//        
//        txtansID1.setText(anstalld.getAid());
//        txtAdress.setText(anstalld.getAdress());
//        txtEfternamn.setText(anstalld.getEfternamn());
//        txtFornamn.setText(anstalld.getFornamn());
//        txtLosenord.setText(anstalld.getLosenord());
//        txtTelefon.setText(anstalld.getTelefon());
//        txtEpost.setText(anstalld.getEpost());
//        txtAnsDatum.setText(anstalld.getAnstallningsdatum());
////        this.fillComboBoxes();

    //}
        

    public void fillComboBoxes() {
        
        //FIll Avdelning
        comboAvdelning.removeAllItems();
        AvdelningHanterare avdelningHanterare = new AvdelningHanterare();
        ArrayList<HashMap<String,String>> avdelning = avdelningHanterare.getAllAvdelning();
        String namn = "";
//        comboAvdelning.addItem(""); 

        for (HashMap<String,String> hashmap : avdelning) {


            namn = hashmap.get("namn");
            comboAvdelning.addItem(namn);

        }
//        comboAvdelning.setSelectedIndex(0);
        String avdelningNamn = anstalld.getAvdelning();
        comboAvdelning.setSelectedItem(avdelningNamn);
        
}
     // tar bort (this.aid, aid) 
    
//    public boolean setAnstalldInfo() {
//        String aid = txtansID1.getText();
//        if (!ah.andraAid(this.aid, aid)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera anställnings-ID.");
//            return false;
//        }
//
//        String Adress = txtAdress.getText();
//        if (!ah.andraAdress(aid, Adress)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera adress.");
//            return false;
//        }
//
//        String Efternamn = txtEfternamn.getText();
//        if (!ah.andraEfternamn(aid, Efternamn)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera efternamn.");
//            return false;
//        }
//
//        String Losenord = txtLosenord.getText();
//        if (!ah.andraLosenord(aid, Losenord)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera lösenord.");
//            return false;
//        }
//
//        String Fornamn = txtFornamn.getText();
//        if (!ah.andraFornamn(aid, Fornamn)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera förnamn.");
//            return false;
//        }
//
//        String epost = txtEpost.getText();
//        if (!ah.andraEpost(aid, epost)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera e-post.");
//            return false;
//        }
//
//        String telefon = txtTelefon.getText();
//        if (!ah.andraTelefon(aid, telefon)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera telefon.");
//            return false;
//        }
//        
//        String Anstallningsdatum = txtAnsDatum.getText();
//        if (!ah.andraAnstallningsdatum(aid, Anstallningsdatum)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera anställningsdatum.");
//            return false;
//        }
//
//        String Avdelning = (String) comboAvdelning.getSelectedItem();
//        if (!ah.andraAvdelning(aid, Avdelning)) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera avdelning.");
//            return false;
//        }
//
// 
//        javax.swing.JOptionPane.showMessageDialog(this, "Uppgifterna har sparats");
//        
//        return true;
//
//    }
        
    private void sparaAnstalld() {
       
        //aid potentiellt auto increment
        
        //idb.insert(epost);
        //idb.insert("insert into anstalld (aid, adress, efternamn")
        // idb.insert();
        
        String adress = txtAdress.getText();
        String efternamn = txtEfternamn.getText();
        String fornamn = txtFornamn.getText();
        String losenord = txtLosenord.getText();
        String telefon = txtTelefon.getText();
        String epost = txtEpost.getText();
        String anstallningsdatum = txtAnsDatum.getText();
        String avdelning = (String) comboAvdelning.getSelectedItem();
        
        String avdelningsId = "1";
        
        try {
            String sqlFraga = "select avdid from avdelning where namn = '" + avdelning + "'";
            avdelningsId = idb.fetchSingle(sqlFraga);
        } catch (InfException ex) {}
        
       
        boolean ok = adh.laggTillAnstalld(losenord, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, avdelningsId);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(this, "Anställd sparad!");
            this.setVisible(false);
        } else {
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
        txtAdress = new javax.swing.JTextField();
        txtAnsDatum = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnSparaAnstalld = new javax.swing.JButton();
        comboAvdelning = new javax.swing.JComboBox<>();
        lblFörklarDatum = new javax.swing.JLabel();
        txtEpost = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        bnGenereraLosen = new javax.swing.JButton();
        btnTillbakaTillAnställd1 = new javax.swing.JToggleButton();

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

        txtAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTillbakaTillAnställd1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229)
                        .addComponent(lblTitle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFornamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(441, 441, 441))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtTelefon, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblAdress)
                                                .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblAnsDatum)))
                                        .addComponent(txtEfternamn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEfternamn)
                                    .addComponent(lblTelefon)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtAnsDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblFörklarDatum)))
                                .addGap(95, 95, 95)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLosenord, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(comboAvdelning, 0, 265, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLosenord)
                                    .addComponent(lblAvdelning))
                                .addGap(0, 212, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bnGenereraLosen)))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEpost)
                            .addComponent(txtEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(lblLosenord)
                    .addComponent(lblFornamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblEfternamn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(bnGenereraLosen)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAnsDatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnsDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFörklarDatum)
                            .addComponent(comboAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTelefon))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAvdelning)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEpost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(lblAdress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
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

    private void txtAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressActionPerformed

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
    private javax.swing.JComboBox<String> comboAvdelning;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAnsDatum;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblFörklarDatum;
    private javax.swing.JLabel lblLosenord;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtAnsDatum;
    private javax.swing.JTextField txtEfternamn;
    private javax.swing.JTextField txtEpost;
    private javax.swing.JTextField txtFornamn;
    private javax.swing.JTextField txtLosenord;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
