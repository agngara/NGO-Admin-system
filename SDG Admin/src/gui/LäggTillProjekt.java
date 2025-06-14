/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import gui.*;
import java.util.ArrayList;
import orgEntities.*;
import java.util.HashMap;
import SQLHanterare.*;
import oru.inf.InfDB;
import db.DatabaseInterface;
import oru.inf.InfException;

        

/**
 *
 * @author theow
 */
public class LäggTillProjekt extends javax.swing.JFrame {
   
    Projekt projekt;
    ProjektHanterare ph;
    String pid;
    private InfDB idb;
//    private Object landNamn;
 
    /**
     * Creates new form EditProjectFields
     */
    public LäggTillProjekt() {
        projekt = new Projekt();
        idb = DatabaseInterface.databaseConnection();
        initComponents();
        fillComboBoxes();
        ph = new ProjektHanterare(pid);
    }
   
    public void fillComboBoxes() {
        
        // Fill Prioritet
        comboPrioritet.removeAllItems();
        comboPrioritet.addItem("Låg");
        comboPrioritet.addItem("Medel");
        comboPrioritet.addItem("Hög");
        
        String prioritet = projekt.getPrioritet();
        comboPrioritet.setSelectedItem(prioritet);
        
        // Fill Status
        comboStatus.removeAllItems();
        comboStatus.addItem("Planerat");
        comboStatus.addItem("Pågående");
        comboStatus.addItem("Avslutat");
        
        String status = projekt.getStatus();
        comboStatus.setSelectedItem(status);
        
//        // Fill Projektchef
//        comboProjektchef.removeAllItems();
//        ProjektHanterare projektHanterare = new ProjektHanterare();
//        ArrayList<HashMap<String,String>> projektchef = projektHanterare.fetchAllProjekt();
//        String chef = ""; 
//
//        for (HashMap<String,String> hashmap : projektchef) {
//
//            chef = hashmap.get("projektchef");
//            comboProjektchef.addItem(chef);
//        }
//
//        String projektchefchef = projekt.getProjektchef();
//        comboLand.setSelectedItem(projektchefchef);  
//        
        // Fill Land
        comboLand.removeAllItems();
        LandHanterare landHanterare = new LandHanterare();
        ArrayList<HashMap<String,String>> land = landHanterare.fetchAllLand();
        String namn = ""; 

        for (HashMap<String,String> hashmap : land) {

            namn = hashmap.get("namn");
            comboLand.addItem(namn);
        }

        String LandNamn = projekt.getLand();
        comboLand.setSelectedItem(LandNamn);        
}
     
        
    private void sparaProjekt() {
       
        // DETTA MÅSTE KOLLAS OM DET ÄR RÄTT!!!!
        
        String projektnamn = txtProjektNamn.getText();
        String startdatum = txtStartDatum.getText();
        String slutdatum = txtSlutDatum.getText();
        String beskrivning = txtBeskrivning.getText();
        String kostnad = txtKostnad.getText();
        String prioritet = (String) comboStatus.getSelectedItem();
        String status = (String) comboStatus.getSelectedItem();
        String landId = (String) comboLand.getSelectedItem();
        String projektchef = txtProjektchef.getText();
        
//        String prioritet = "";
//        String status = "";
//        String landId = "1";
        
//        try {
//            String sqlFraga = "select projektchef from projekt where namn = '" + projektchef + "'";
//            projektchef = idb.fetchSingle(sqlFraga);
//        } catch (InfException ex) {}
        
        try {
            String sqlFraga = "select lid from land where namn = '" + landId + "'";
            landId = idb.fetchSingle(sqlFraga);
        } catch (InfException ex) {}
        
       
        boolean ok = ph.laggTillProjekt(projektnamn, beskrivning, startdatum, slutdatum, kostnad, prioritet, status, projektchef, landId);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(this, "Projekt sparat!");
            this.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte spara projekt, ange rätt Anställnings ID.");
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
        lblFornamn = new javax.swing.JLabel();
        lblEfternamn = new javax.swing.JLabel();
        lblAvdelning = new javax.swing.JLabel();
        txtProjektNamn = new javax.swing.JTextField();
        txtStartDatum = new javax.swing.JTextField();
        txtSlutDatum = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnSparaAnstalld = new javax.swing.JButton();
        lblFörklarDatum = new javax.swing.JLabel();
        txtBeskrivning = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        txtKostnad = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        btnTillbakaTillProjekt = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboLand = new javax.swing.JComboBox<>();
        comboStatus = new javax.swing.JComboBox<>();
        comboPrioritet = new javax.swing.JComboBox<>();
        lblFörklarDatum1 = new javax.swing.JLabel();
        txtProjektchef = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblLosenord.setText("Prioritet");

        lblAnsDatum.setText("Slutdatum");

        lblFornamn.setText("Projektnamn");

        lblEfternamn.setText("Startdatum");

        lblAvdelning.setText("Land");

        txtProjektNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProjektNamnActionPerformed(evt);
            }
        });

        txtStartDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStartDatumActionPerformed(evt);
            }
        });

        txtSlutDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSlutDatumActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Lägg till Projekt");

        btnSparaAnstalld.setBackground(new java.awt.Color(7, 96, 216));
        btnSparaAnstalld.setForeground(new java.awt.Color(255, 255, 255));
        btnSparaAnstalld.setText("Spara");
        btnSparaAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAnstalldActionPerformed(evt);
            }
        });

        lblFörklarDatum.setText("ÅÅÅÅ-MM-DD");

        txtBeskrivning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBeskrivningActionPerformed(evt);
            }
        });

        lblEpost.setText("Beskrivning");

        txtKostnad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKostnadActionPerformed(evt);
            }
        });

        lblTelefon.setText("Kostnad");

        btnTillbakaTillProjekt.setBackground(new java.awt.Color(7, 96, 216));
        btnTillbakaTillProjekt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turn-left-small.png"))); // NOI18N
        btnTillbakaTillProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaTillProjektActionPerformed(evt);
            }
        });

        jLabel1.setText("Status");

        jLabel2.setText("Projektchef");

        comboLand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLandActionPerformed(evt);
            }
        });

        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        comboPrioritet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPrioritetActionPerformed(evt);
            }
        });

        lblFörklarDatum1.setText("ÅÅÅÅ-MM-DD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEpost)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 605, Short.MAX_VALUE)
                                .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(448, 448, 448)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboLand, 0, 261, Short.MAX_VALUE)
                                    .addComponent(comboStatus, 0, 261, Short.MAX_VALUE)
                                    .addComponent(lblLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtProjektchef))
                                .addGap(20, 20, 20))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFornamn)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtKostnad, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblAnsDatum)
                                            .addGap(245, 245, 245)))
                                    .addComponent(txtStartDatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lblFörklarDatum1))
                            .addComponent(lblEfternamn)
                            .addComponent(lblTelefon)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFörklarDatum))
                            .addComponent(txtBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTillbakaTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(229, 229, 229)
                                .addComponent(lblTitle)))
                        .addContainerGap(343, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(538, 538, 538)
                    .addComponent(comboPrioritet, 0, 261, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTillbakaTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFornamn)
                    .addComponent(lblLosenord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEfternamn)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFörklarDatum1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnsDatum)
                    .addComponent(lblAvdelning))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFörklarDatum)
                    .addComponent(comboLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefon)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProjektchef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(lblEpost)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBeskrivning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSparaAnstalld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(comboPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(404, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProjektNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProjektNamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProjektNamnActionPerformed

    private void txtStartDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStartDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStartDatumActionPerformed

    private void txtSlutDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSlutDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlutDatumActionPerformed

    private void btnSparaAnstalldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaAnstalldActionPerformed
          sparaProjekt();     
    }//GEN-LAST:event_btnSparaAnstalldActionPerformed

    private void txtBeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBeskrivningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBeskrivningActionPerformed

    private void txtKostnadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKostnadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKostnadActionPerformed

    private void btnTillbakaTillProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaTillProjektActionPerformed
        this.setVisible(false);
        projektruta anst = new projektruta();
        anst.setVisible(true);
    }//GEN-LAST:event_btnTillbakaTillProjektActionPerformed

    private void comboLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLandActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboStatusActionPerformed

    private void comboPrioritetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPrioritetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPrioritetActionPerformed

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
            java.util.logging.Logger.getLogger(LäggTillProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LäggTillProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LäggTillProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LäggTillProjekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton btnSparaAnstalld;
    private javax.swing.JToggleButton btnTillbakaTillProjekt;
    private javax.swing.JComboBox<String> comboLand;
    private javax.swing.JComboBox<String> comboPrioritet;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAnsDatum;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblFörklarDatum;
    private javax.swing.JLabel lblFörklarDatum1;
    private javax.swing.JLabel lblLosenord;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtBeskrivning;
    private javax.swing.JTextField txtKostnad;
    private javax.swing.JTextField txtProjektNamn;
    private javax.swing.JTextField txtProjektchef;
    private javax.swing.JTextField txtSlutDatum;
    private javax.swing.JTextField txtStartDatum;
    // End of variables declaration//GEN-END:variables
}
