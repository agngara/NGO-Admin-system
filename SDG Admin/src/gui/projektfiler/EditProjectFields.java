/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.projektfiler;
import java.util.ArrayList;
import orgEntities.Projekt;
import java.util.HashMap;
import SQLHanterare.*;
import gui.projektfiler.OneProjectView;
import logicComponents.User.CurrentUser;
import logicComponents.User.UserType;

/**
 *
 * @author theow
 */
public class EditProjectFields extends javax.swing.JFrame {
   
    Projekt projekt;
    ProjektHanterare ph;
    String pid;
    OneProjectView oneProjectView;
    
     
    
    /**
     * Creates new form EditProjectFields
     */
    public EditProjectFields(Projekt projekt, OneProjectView oneProjectView) {
        this.projekt = projekt;
        initComponents();
        hideBigBossComponents();
        this.setTextBoxes();
        pid = projekt.getPid();
        ph = new ProjektHanterare(pid);
        this.oneProjectView = oneProjectView;
        
    }
    /**
     * Returns true if 
     * @return 
     */
    public boolean hideBigBossComponents() {
    
    if (CurrentUser.getUsr().getUserType() != UserType.admin1 && CurrentUser.getUsr().getUserType() != UserType.admin2) {
        
        lblProjektChef.setVisible(false);
        txtProjChef.setVisible(false);
        return true;
    }
    
    return false;
    
}
    
    public void setTextBoxes() {
        
        txtProjID1.setText(projekt.getPid());
        txtBeskrivning.setText(projekt.getBeskrivning());
        txtKostnad.setText(projekt.getKostnad());
        //txtPrioritet.setText(projekt.getPrioritet());
        //txtProjektChef.setText(projekt.getProjektchef());
        txtProjChef.setText(projekt.getProjektnamn());
        txtSlutDatum.setText(projekt.getSlutdatum());
        txtStartDatum.setText(projekt.getStartdatum());
        //txtStatus.setText(projekt.getStatus());
        this.fillComboBoxes();

    }
        

    public void fillComboBoxes() {

        //FIll land
        ComboLand.removeAllItems();
        LandHanterare landHanterare = new LandHanterare();
        ArrayList<HashMap<String,String>> lander = landHanterare.fetchAllLand();
        String namn = "";

        for (HashMap<String,String> hashmap : lander) {


            namn = hashmap.get("namn");
            ComboLand.addItem(namn);

        }
        String land = projekt.getLand();
        ComboLand.setSelectedItem(land);

        // Fill prioritet
        ComboPrio.removeAllItems();
        ComboPrio.addItem("Låg");
        ComboPrio.addItem("Medel");
        ComboPrio.addItem("Hög");
        
        String prio = projekt.getPrioritet();
        ComboPrio.setSelectedItem(prio);


        // Fill status
        comboStatus.removeAllItems();
        comboStatus.addItem("Planerat");
        comboStatus.addItem("Pågående");
        comboStatus.addItem("Avslutat");
        
        String status = projekt.getStatus();
        comboStatus.setSelectedItem(status);
}
     

    public boolean setProjectInfo() {
        String pid = txtProjID1.getText();
        if (!ph.andraPid(pid, pid)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera projekt-ID.");
            return false;
        }

        String beskrivning = txtBeskrivning.getText();
        if (!ph.andraBeskrivning(pid, beskrivning)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera beskrivning.");
            return false;
        }

        String kostnad = txtKostnad.getText();
        if (!ph.andraKostnad(pid, kostnad)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera kostnad.");
            return false;
        }

        String prio = (String) ComboPrio.getSelectedItem();
        if (!ph.andraPrioritet(pid, prio)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera prioritet.");
            return false;
        }

        String projektnamn = txtProjChef.getText();
        if (!ph.andraProjektnamn(pid, projektnamn)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera projektnamn.");
            return false;
        }

        String slutDatum = txtSlutDatum.getText();
        if (!ph.andraSlutdatum(pid, slutDatum)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera slutdatum.");
            return false;
        }

        String startDatum = txtStartDatum.getText();
        if (!ph.andraStartdatum(pid, startDatum)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera startdatum.");
            return false;
        }

        String status = (String) comboStatus.getSelectedItem();
        if (!ph.andraStatus(pid, status)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera status.");
            return false;
        }

        String land = (String) ComboLand.getSelectedItem();
        System.out.println(land);
        int lid = new LandHanterare().getLIDbyNamn(land);
        System.out.println("Hämtad lid" + lid);
        if (!ph.andraLand(pid, lid)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera land.");
            return false;
        }
        
        String projektchef = txtProjChef.getText();
        if (!ph.andraProjektchef(pid, projektchef)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera projektchef.");
            return false;
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Uppgifterna har sparats");
        
        return true;

    }
        
    
        
        
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProjektID = new javax.swing.JLabel();
        lblProjektNamn = new javax.swing.JLabel();
        lblStartDatum = new javax.swing.JLabel();
        lblBeskrivning = new javax.swing.JLabel();
        lblSlutDatum = new javax.swing.JLabel();
        lblKostnad = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblPrioritet = new javax.swing.JLabel();
        lblNamn = new javax.swing.JLabel();
        txtProjChef = new javax.swing.JTextField();
        txtProjID1 = new javax.swing.JTextField();
        txtSlutDatum = new javax.swing.JTextField();
        txtKostnad = new javax.swing.JTextField();
        txtBeskrivning = new javax.swing.JTextField();
        txtStartDatum = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnRedigera = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox<>();
        ComboLand = new javax.swing.JComboBox<>();
        ComboPrio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblProjektChef = new javax.swing.JLabel();
        txtProjektNamn1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblProjektID.setText("Project-ID*");

        lblProjektNamn.setText("Projektnamn*");

        lblStartDatum.setText("Startdatum*");

        lblBeskrivning.setText("Beskrivning*");

        lblSlutDatum.setText("Slutdatum*");

        lblKostnad.setText("Kostnad*");

        lblStatus.setText("Status*");

        lblPrioritet.setText("Prioritet*");

        lblNamn.setText("Land*");

        txtProjChef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProjChefActionPerformed(evt);
            }
        });

        txtProjID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProjID1ActionPerformed(evt);
            }
        });

        txtSlutDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSlutDatumActionPerformed(evt);
            }
        });

        txtKostnad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKostnadActionPerformed(evt);
            }
        });

        txtBeskrivning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBeskrivningActionPerformed(evt);
            }
        });

        txtStartDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStartDatumActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Redigera projekt");

        btnRedigera.setBackground(new java.awt.Color(7, 96, 216));
        btnRedigera.setForeground(new java.awt.Color(255, 255, 255));
        btnRedigera.setText("Spara");
        btnRedigera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraActionPerformed(evt);
            }
        });

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ComboLand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ComboPrio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("ÅÅÅÅ-MM-DD");

        jLabel2.setText("ÅÅÅÅ-MM-DD");

        lblProjektChef.setText("[ADMIN] Ändra projektchef  (AID)");

        txtProjektNamn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProjektNamn1ActionPerformed(evt);
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
                        .addComponent(lblStartDatum)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtStartDatum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKostnad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(lblProjektID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSlutDatum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKostnad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBeskrivning, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSlutDatum)
                            .addComponent(txtProjID1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBeskrivning, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRedigera, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboPrio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboLand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtProjChef, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblProjektChef)
                                            .addComponent(lblProjektNamn)
                                            .addComponent(lblPrioritet)
                                            .addComponent(lblNamn)
                                            .addComponent(lblStatus))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(31, 31, 31))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(534, 534, 534)
                    .addComponent(txtProjektNamn1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitle)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektID)
                    .addComponent(lblProjektNamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProjID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStartDatum)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtStartDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSlutDatum)
                    .addComponent(lblPrioritet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboPrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKostnad)
                    .addComponent(lblNamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeskrivning)
                    .addComponent(lblProjektChef))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProjChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRedigera, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(txtProjektNamn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(444, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProjChefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProjChefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProjChefActionPerformed

    private void txtProjID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProjID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProjID1ActionPerformed

    private void txtSlutDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSlutDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlutDatumActionPerformed

    private void txtKostnadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKostnadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKostnadActionPerformed

    private void txtBeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBeskrivningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBeskrivningActionPerformed

    private void txtStartDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStartDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStartDatumActionPerformed

    private void btnRedigeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraActionPerformed
        
        if (setProjectInfo()) {
            
        oneProjectView.fillTable();
        this.setVisible(false);
            
        }
        
        
   
    }//GEN-LAST:event_btnRedigeraActionPerformed

    private void txtProjektNamn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProjektNamn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProjektNamn1ActionPerformed

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
            java.util.logging.Logger.getLogger(EditProjectFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProjectFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProjectFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProjectFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new EditProjectFields().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboLand;
    private javax.swing.JComboBox<String> ComboPrio;
    private javax.swing.JButton btnRedigera;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBeskrivning;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblNamn;
    private javax.swing.JLabel lblPrioritet;
    private javax.swing.JLabel lblProjektChef;
    private javax.swing.JLabel lblProjektID;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblSlutDatum;
    private javax.swing.JLabel lblStartDatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtBeskrivning;
    private javax.swing.JTextField txtKostnad;
    private javax.swing.JTextField txtProjChef;
    private javax.swing.JTextField txtProjID1;
    private javax.swing.JTextField txtProjektNamn1;
    private javax.swing.JTextField txtSlutDatum;
    private javax.swing.JTextField txtStartDatum;
    // End of variables declaration//GEN-END:variables
}
