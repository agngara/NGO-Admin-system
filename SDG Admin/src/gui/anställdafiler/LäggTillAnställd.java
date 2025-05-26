/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.anställdafiler;
import gui.projektfiler.*;
import java.util.ArrayList;
import orgEntities.Projekt;
import java.util.HashMap;
import SQLHanterare.*;
import gui.projektfiler.OneProjectView;

/**
 *
 * @author theow
 */
public class LäggTillAnställd extends javax.swing.JFrame {
   
    Projekt projekt;
    ProjektHanterare ph;
    String pid;
    OneProjectView oneProjectView;
    
     
    
    /**
     * Creates new form EditProjectFields
     */
    public LäggTillAnställd(Projekt projekt, OneProjectView oneProjectView) {
        this.projekt = projekt;
        initComponents();
        this.setTextBoxes();
        pid = projekt.getPid();
        ph = new ProjektHanterare(pid);
        this.oneProjectView = oneProjectView;
        
    }
    
    public void setTextBoxes() {
        
        txtProjID1.setText(projekt.getPid());
        txtBeskrivning.setText(projekt.getBeskrivning());
        txtKostnad.setText(projekt.getKostnad());
        //txtPrioritet.setText(projekt.getPrioritet());
        //txtProjektChef.setText(projekt.getProjektchef());
        txtProjektNamn.setText(projekt.getProjektnamn());
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
        if (!ph.andraPid(this.pid, pid)) {
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

        String projektnamn = txtProjektNamn.getText();
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
        if (!ph.andraLand(pid, land)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera land.");
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
        txtProjektNamn = new javax.swing.JTextField();
        txtProjID1 = new javax.swing.JTextField();
        txtSlutDatum = new javax.swing.JTextField();
        txtKostnad = new javax.swing.JTextField();
        txtBeskrivning = new javax.swing.JTextField();
        txtStartDatum = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnRedigera = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtSlutDatum1 = new javax.swing.JTextField();
        lblSlutDatum1 = new javax.swing.JLabel();
        txtSlutDatum2 = new javax.swing.JTextField();
        lblSlutDatum2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblProjektID.setText("Anställnings-ID*");

        lblProjektNamn.setText("Lösenord");

        lblStartDatum.setText("Anställningsdatum");

        lblBeskrivning.setText("Adress");

        lblSlutDatum.setText("Förnamn");

        lblKostnad.setText("Efternamn");

        lblStatus.setText("Avdelning");

        txtProjektNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProjektNamnActionPerformed(evt);
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
        lblTitle.setText("Lägg till ny anställd");

        btnRedigera.setBackground(new java.awt.Color(7, 96, 216));
        btnRedigera.setForeground(new java.awt.Color(255, 255, 255));
        btnRedigera.setText("Spara");
        btnRedigera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraActionPerformed(evt);
            }
        });

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("ÅÅÅÅ-MM-DD");

        txtSlutDatum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSlutDatum1ActionPerformed(evt);
            }
        });

        lblSlutDatum1.setText("E-post");

        txtSlutDatum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSlutDatum2ActionPerformed(evt);
            }
        });

        lblSlutDatum2.setText("Telefon");

        jButton1.setText("Ändra lösenord");

        jButton2.setText("Generera lösenord");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSlutDatum2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtStartDatum, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKostnad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(lblProjektID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSlutDatum, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblKostnad, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBeskrivning, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSlutDatum)
                                    .addComponent(txtProjID1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBeskrivning, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSlutDatum1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(lblStartDatum)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRedigera, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addComponent(txtProjektNamn, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblProjektNamn)
                                            .addComponent(lblStatus))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(comboStatus, 0, 265, Short.MAX_VALUE))))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSlutDatum1)
                            .addComponent(lblSlutDatum2))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProjID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblStartDatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStartDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSlutDatum)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSlutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(lblKostnad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblBeskrivning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblSlutDatum2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSlutDatum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(lblSlutDatum1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRedigera, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSlutDatum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProjektNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProjektNamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProjektNamnActionPerformed

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

    private void txtSlutDatum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSlutDatum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlutDatum1ActionPerformed

    private void txtSlutDatum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSlutDatum2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlutDatum2ActionPerformed

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
            java.util.logging.Logger.getLogger(LäggTillAnställd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LäggTillAnställd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnRedigera;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBeskrivning;
    private javax.swing.JLabel lblKostnad;
    private javax.swing.JLabel lblProjektID;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblSlutDatum;
    private javax.swing.JLabel lblSlutDatum1;
    private javax.swing.JLabel lblSlutDatum2;
    private javax.swing.JLabel lblStartDatum;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtBeskrivning;
    private javax.swing.JTextField txtKostnad;
    private javax.swing.JTextField txtProjID1;
    private javax.swing.JTextField txtProjektNamn;
    private javax.swing.JTextField txtSlutDatum;
    private javax.swing.JTextField txtSlutDatum1;
    private javax.swing.JTextField txtSlutDatum2;
    private javax.swing.JTextField txtStartDatum;
    // End of variables declaration//GEN-END:variables
}
