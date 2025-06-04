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
        
        //Fönstrets egenskaper
        setSize(846, 612);             
        setResizable(false);           
        setLocationRelativeTo(null);   
        
        // Sätter komponenter med villkorsberonde till osynliga
        comboMentor.setVisible(false);
        lblMentor.setVisible(false);
        lblAnsvar.setVisible(false);
        txtAnsvar.setVisible(false);
        lblBehorighet.setVisible(false);
        comboBehorighet.setVisible(false);
        
        
        
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
//        String avdelningNamn = anstalld.getAvdelning();
//        comboMentor.setSelectedItem(avdelningNamn);
//        
        // Fill roll
        comboRoll.removeAllItems();
        comboRoll.addItem("-");
        comboRoll.addItem("Administratör");
        comboRoll.addItem("Handläggare");
      
}
    
    public void loadHandlaggareComps() {
        
        //Göm adminkomponenter
        lblBehorighet.setVisible(false);
        comboBehorighet.setVisible(false);
        
        //Fyller mentor
        comboMentor.removeAllItems();
        comboMentor.addItem("Ingen mentor");
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
        lblMentor.setVisible(true);
        
        //Visa ansvarsområde
        lblAnsvar.setVisible(true);
        txtAnsvar.setVisible(true);

    }
    
    public void loadAdminComps() {
        
        //Göm handläggarekomponenter
         comboMentor.setVisible(false);
        lblMentor.setVisible(false);
        lblAnsvar.setVisible(false);
        txtAnsvar.setVisible(false);
        
        
        lblBehorighet.setVisible(true);
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

        
        // Variabler för scope
        boolean okAdmin = true;
        // Lägger till hnadläggare
        if (comboRoll.getSelectedItem().equals("Handläggare")) {
        String mentor = (String) comboMentor.getSelectedItem();

        int start = mentor.indexOf('(');
        int end = mentor.indexOf(')');
        String aidMentor = mentor.substring(start + 1, end);
 
        
           
           String ansvarsområde = txtAnsvar.getText();
           boolean okHandlaggare = new HandlaggareHanterare().laggTillHandlaggare(ansvarsområde, aidMentor);
           
           if (!okHandlaggare) {
               JOptionPane.showMessageDialog(rootPane, "Kunde inte registrera handläggaren. Var god undersök att inget fält har lämnas tomt.");
               ok = false;
           }
        }
               
            else if (comboRoll.getSelectedItem().equals("Administratör")) {
                    String behorighetsniva = (String) comboBehorighet.getSelectedItem();
                    okAdmin = new AdminHanterare().laggTillAdmin(behorighetsniva);
                           
                    
                    if (!okAdmin) {
                    JOptionPane.showMessageDialog(rootPane, "Kunde inte registrera admin. Var god undersök att inte 'Behörighetsnivå' har lämnats tomt.");
                    ok = false;
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
        getContentPane().setLayout(null);

        lblLosenord.setText("Lösenord");
        getContentPane().add(lblLosenord);
        lblLosenord.setBounds(524, 82, 49, 16);

        lblAnsDatum.setText("Anställningsdatum");
        getContentPane().add(lblAnsDatum);
        lblAnsDatum.setBounds(31, 208, 100, 16);

        lblAdress.setText("Adress");
        getContentPane().add(lblAdress);
        lblAdress.setBounds(31, 398, 93, 16);

        lblFornamn.setText("Förnamn");
        getContentPane().add(lblFornamn);
        lblFornamn.setBounds(31, 82, 52, 16);

        lblEfternamn.setText("Efternamn");
        getContentPane().add(lblEfternamn);
        lblEfternamn.setBounds(31, 148, 55, 16);

        lblAvdelning.setText("Avdelning");
        getContentPane().add(lblAvdelning);
        lblAvdelning.setBounds(524, 205, 54, 16);

        txtLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLosenordActionPerformed(evt);
            }
        });
        getContentPane().add(txtLosenord);
        txtLosenord.setBounds(524, 104, 286, 26);

        txtFornamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFornamnActionPerformed(evt);
            }
        });
        getContentPane().add(txtFornamn);
        txtFornamn.setBounds(31, 104, 300, 26);

        txtEfternamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfternamnActionPerformed(evt);
            }
        });
        getContentPane().add(txtEfternamn);
        txtEfternamn.setBounds(31, 170, 300, 26);

        txtAnsDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnsDatumActionPerformed(evt);
            }
        });
        getContentPane().add(txtAnsDatum);
        txtAnsDatum.setBounds(31, 236, 300, 26);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Lägg till Anställd");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(312, 29, 176, 32);

        btnSparaAnstalld.setBackground(new java.awt.Color(7, 96, 216));
        btnSparaAnstalld.setForeground(new java.awt.Color(255, 255, 255));
        btnSparaAnstalld.setText("Spara");
        btnSparaAnstalld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaAnstalldActionPerformed(evt);
            }
        });
        getContentPane().add(btnSparaAnstalld);
        btnSparaAnstalld.setBounds(720, 510, 93, 37);

        getContentPane().add(comboMentor);
        comboMentor.setBounds(524, 360, 286, 26);

        lblFörklarDatum.setText("ÅÅÅÅ-MM-DD");
        getContentPane().add(lblFörklarDatum);
        lblFörklarDatum.setBounds(349, 241, 80, 16);

        txtEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEpostActionPerformed(evt);
            }
        });
        getContentPane().add(txtEpost);
        txtEpost.setBounds(31, 360, 300, 26);

        lblEpost.setText("E-post");
        getContentPane().add(lblEpost);
        lblEpost.setBounds(31, 338, 102, 16);

        txtTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefon);
        txtTelefon.setBounds(31, 302, 300, 26);

        lblTelefon.setText("Telefon");
        getContentPane().add(lblTelefon);
        lblTelefon.setBounds(31, 274, 40, 16);

        bnGenereraLosen.setText("Generera Lösenord");
        getContentPane().add(bnGenereraLosen);
        bnGenereraLosen.setBounds(524, 160, 133, 27);

        btnTillbakaTillAnställd1.setBackground(new java.awt.Color(7, 96, 216));
        btnTillbakaTillAnställd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turn-left-small.png"))); // NOI18N
        btnTillbakaTillAnställd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaTillAnställd1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnTillbakaTillAnställd1);
        btnTillbakaTillAnställd1.setBounds(31, 29, 52, 35);

        txtAdress1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdress1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtAdress1);
        txtAdress1.setBounds(31, 426, 300, 26);

        txtAnsvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnsvarActionPerformed(evt);
            }
        });
        getContentPane().add(txtAnsvar);
        txtAnsvar.setBounds(524, 420, 286, 26);

        lblAnsvar.setText("Ansvarighetsområde");
        getContentPane().add(lblAnsvar);
        lblAnsvar.setBounds(524, 398, 90, 16);

        getContentPane().add(comboAvdelning1);
        comboAvdelning1.setBounds(524, 227, 286, 26);

        lblAvdelning1.setText("Roll");
        getContentPane().add(lblAvdelning1);
        lblAvdelning1.setBounds(524, 271, 20, 16);

        comboRoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRollActionPerformed(evt);
            }
        });
        getContentPane().add(comboRoll);
        comboRoll.setBounds(524, 299, 286, 26);

        lblMentor.setText("Mentor");
        getContentPane().add(lblMentor);
        lblMentor.setBounds(524, 343, 90, 16);

        getContentPane().add(comboBehorighet);
        comboBehorighet.setBounds(520, 360, 286, 26);

        lblBehorighet.setText("Brehörighetsnivå");
        getContentPane().add(lblBehorighet);
        lblBehorighet.setBounds(520, 340, 90, 16);

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

    private void comboRollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRollActionPerformed
         String valtAlternativ = (String) comboRoll.getSelectedItem();

        if (valtAlternativ.equals("Handläggare")) {
            loadHandlaggareComps();
        } else if (valtAlternativ.equals("Administratör")) {
            loadAdminComps();
        } else {
            
        comboMentor.setVisible(false);
        lblMentor.setVisible(false);
        lblAnsvar.setVisible(false);
        txtAnsvar.setVisible(false);
        lblBehorighet.setVisible(false);
        comboBehorighet.setVisible(false);
        
            
        }
    }//GEN-LAST:event_comboRollActionPerformed

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
