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
import gui.projektfiler.OneProjectView;
import logicComponents.User.UserType;
        

/**
 *
 * @author theow
 */
public class EditAnställda1 extends javax.swing.JFrame {
   
    Anstalld anstalld;
    AnstalldHanterare ah;
    String aid;
    OneProjectView oneProjectView;
    UserType userType;
    
     
    
    /**
     * Konstruktor
     */
    public EditAnställda1(Anstalld anstalld) {
        this.anstalld = anstalld;
        initComponents();
        setSize(820, 700);             
        setResizable(false);           
        setLocationRelativeTo(null); 
        ah = new AnstalldHanterare(aid, "filler");
        aid = anstalld.getAid();
        userType = ah.fetchRole(aid);        
        lblBehorighet.setVisible(false);
        comboBehorighet.setVisible(false);
        comboMentor.setVisible(false);
        lblMentor.setVisible(false);
        txtAnsvar.setVisible(false);
        lblAnsvar.setVisible(false);
        

        this.setTextBoxes();
        this.oneProjectView = oneProjectView;
//        ah = new AnstalldHanterare(aid);

        if (userType == UserType.admin1 || userType == UserType.admin2) {
            
            lblBehorighet.setVisible(true);
            comboBehorighet.setVisible(true);
        }
        
        if (userType == UserType.handlaggare || userType == UserType.projektchef) {
            
            comboMentor.setVisible(true);
            lblMentor.setVisible(true);
            txtAnsvar.setVisible(true);
            lblAnsvar.setVisible(true);
            
            
        }
        
        
        
    }

    
  // infogar den anställdas information från databasen till textrutor
    public void setTextBoxes() {
        
        txtTelefon.setText(anstalld.getTelefon());
        txtAnstalllning.setText(anstalld.getAnstallningsdatum());
        txtFornamn.setText(anstalld.getFornamn());
        txtLosenord.setText(anstalld.getLosenord());
        txtEpost.setText(anstalld.getEpost());
        txtAdress.setText(anstalld.getAdress());
        txtEfternamn.setText(anstalld.getEfternamn());
        this.fillComboBoxes();
        
        HandlaggareHanterare handlaggareHanterare = new HandlaggareHanterare(aid);
        Handlaggare handlaggare = new Handlaggare(handlaggareHanterare);
        String ansvar = handlaggare.getAnsvarighetsområden();
        txtAnsvar.setText(ansvar);
        
        
       


    }
        
// ger comboboxen värden
    public void fillComboBoxes() {
        
        //Fyll Avdelning
        comboAvdelning.removeAllItems();
        AvdelningHanterare avdelningHanterare = new AvdelningHanterare();
        ArrayList<HashMap<String,String>> avdelning = avdelningHanterare.getAllAvdelning();
        String namn = "";

        for (HashMap<String,String> hashmap : avdelning) {


            namn = hashmap.get("namn");
            comboAvdelning.addItem(namn);

        }
        String avdelningNamn = anstalld.getAvdelning();
        comboAvdelning.setSelectedItem(avdelningNamn);
        
        // Fyller behörighet (Admin)
        comboBehorighet.removeAllItems();
        comboBehorighet.addItem("1");
        comboBehorighet.addItem("2");
        
        if (userType == UserType.admin1) {
            
            comboBehorighet.setSelectedItem("1");
            
        }
        else if (userType == UserType.admin2) {
            
            comboBehorighet.setSelectedItem("2");
            
        }
        else {
            
        }
        
        //Fyller mentor (Handläggare)
        
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
        
//          HandlaggareHanterare handlaggareHanterare = new HandlaggareHanterare(anstalld.getAid());
//          Handlaggare handlaggare = new Handlaggare(handlaggareHanterare);
//          String mentorAid = handlaggare.getMentor();
//          AnstalldHanterare anstalldHanterare = new AnstalldHanterare(mentorAid, "filler");
//          Anstalld anstalld = new Anstalld(anstalldHanterare);
//          String mentorFornamn = anstalld.getFornamn();
//          String mentorEfternamn = anstalld.getEfternamn();
//          String mentorDisplay = mentorFornamn + " " + mentorEfternamn + " (" + mentorAid + ")";
//          comboMentor.setSelectedItem(mentorDisplay);
//          System.out.println(mentorDisplay);
//          
          
              
    
        

        
}
     
  // uppdaterar den nya informtionen i databasen
    public boolean setAnstalldInfo() {

        String Adress = txtTelefon.getText();
        if (!ah.andraAdress(aid, Adress)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera adress.");
            return false;
        }

        String Efternamn = txtEfternamn.getText();
        if (!ah.andraEfternamn(aid, Efternamn)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera efternamn.");
            return false;
        }

       

        String Fornamn = txtFornamn.getText();
        if (!ah.andraFornamn(aid, Fornamn)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera förnamn.");
            return false;
        }
        
        String Losenord = txtLosenord.getText();
        if (!ah.andraLosenord(aid, Losenord)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera lösenord.");
            return false;
        }
       
        
        String Anstallningsdatum = txtAnstalllning.getText();
        if (!ah.andraAnstallningsdatum(aid, Anstallningsdatum)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera anställningsdatum.");
            return false;
        }
      
        
        //Set admin
        
         if (userType == UserType.admin1 || userType == UserType.admin2) {
            String behorighetsniva = (String) comboBehorighet.getSelectedItem();
            AdminHanterare adminHanterare = new AdminHanterare(aid);
            if (!adminHanterare.andraBehorighetsniva(aid, behorighetsniva)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera behörighetsnivå.");
                return false;
            }
         }
         
         //Set handläggare
         
         if (userType == UserType.handlaggare || userType == UserType.projektchef) {
             
             String ansvarsomrade = txtAnsvar.getText();
             HandlaggareHanterare handlaggareHanterare = new HandlaggareHanterare(aid);
             if (!handlaggareHanterare.andraAnsvarighetsomrade(aid, ansvarsomrade)) {
                 javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera ansvarighetsomårde.");
                 return false;
             }

         
         
            String mentor = (String) comboMentor.getSelectedItem();
            int start = mentor.indexOf('(');
            int end = mentor.indexOf(')');
            String aidMentor = mentor.substring(start + 1, end);

            if (!handlaggareHanterare.andraMentor(aid, aidMentor)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Kunde inte uppdatera mentor.");
                return false;
            }

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

        lblLosenord = new javax.swing.JLabel();
        lblAnsDatum = new javax.swing.JLabel();
        lblAdress = new javax.swing.JLabel();
        lblFornamn = new javax.swing.JLabel();
        lblEfternamn = new javax.swing.JLabel();
        lblAvdelning = new javax.swing.JLabel();
        txtLosenord = new javax.swing.JTextField();
        txtFornamn = new javax.swing.JTextField();
        txtAnstalllning = new javax.swing.JTextField();
        txtTelefon = new javax.swing.JTextField();
        txtEfternamn = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        btnRedigera = new javax.swing.JButton();
        lblFörklarDatum = new javax.swing.JLabel();
        txtAdress = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();
        txtEpost = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        bAndraLösen = new javax.swing.JButton();
        btnTillbakaTillAnställda = new javax.swing.JToggleButton();
        comboAvdelning = new javax.swing.JComboBox<>();
        comboBehorighet = new javax.swing.JComboBox<>();
        txtAnsvar = new javax.swing.JTextField();
        comboMentor = new javax.swing.JComboBox<>();
        lblBehorighet = new javax.swing.JLabel();
        lblAnsvar = new javax.swing.JLabel();
        lblMentor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lblLosenord.setText("Lösenord");
        getContentPane().add(lblLosenord);
        lblLosenord.setBounds(524, 82, 49, 16);

        lblAnsDatum.setText("Anställningsdatum");
        getContentPane().add(lblAnsDatum);
        lblAnsDatum.setBounds(31, 240, 100, 22);

        lblAdress.setText("Adress");
        getContentPane().add(lblAdress);
        lblAdress.setBounds(31, 465, 35, 16);

        lblFornamn.setText("Förnamn");
        getContentPane().add(lblFornamn);
        lblFornamn.setBounds(31, 82, 48, 16);

        lblEfternamn.setText("Efternamn");
        getContentPane().add(lblEfternamn);
        lblEfternamn.setBounds(31, 148, 55, 16);

        lblAvdelning.setText("Avdelning");
        getContentPane().add(lblAvdelning);
        lblAvdelning.setBounds(544, 228, 54, 16);

        txtLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLosenordActionPerformed(evt);
            }
        });
        getContentPane().add(txtLosenord);
        txtLosenord.setBounds(524, 104, 265, 26);

        txtFornamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFornamnActionPerformed(evt);
            }
        });
        getContentPane().add(txtFornamn);
        txtFornamn.setBounds(31, 104, 300, 26);

        txtAnstalllning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnstalllningActionPerformed(evt);
            }
        });
        getContentPane().add(txtAnstalllning);
        txtAnstalllning.setBounds(31, 268, 300, 26);

        txtTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefon);
        txtTelefon.setBounds(31, 361, 300, 26);

        txtEfternamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfternamnActionPerformed(evt);
            }
        });
        getContentPane().add(txtEfternamn);
        txtEfternamn.setBounds(31, 170, 300, 26);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Redigera Anställd");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(319, 21, 186, 32);

        btnRedigera.setBackground(new java.awt.Color(7, 96, 216));
        btnRedigera.setForeground(new java.awt.Color(255, 255, 255));
        btnRedigera.setText("Spara");
        btnRedigera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraActionPerformed(evt);
            }
        });
        getContentPane().add(btnRedigera);
        btnRedigera.setBounds(713, 616, 93, 38);

        lblFörklarDatum.setText("ÅÅÅÅ-MM-DD");
        getContentPane().add(lblFörklarDatum);
        lblFörklarDatum.setBounds(349, 273, 80, 16);

        txtAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdressActionPerformed(evt);
            }
        });
        getContentPane().add(txtAdress);
        txtAdress.setBounds(31, 493, 300, 26);

        lblEpost.setText("E-post");
        getContentPane().add(lblEpost);
        lblEpost.setBounds(31, 399, 34, 16);

        txtEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEpostActionPerformed(evt);
            }
        });
        getContentPane().add(txtEpost);
        txtEpost.setBounds(31, 421, 300, 26);

        lblTelefon.setText("Telefon");
        getContentPane().add(lblTelefon);
        lblTelefon.setBounds(31, 327, 40, 16);

        bAndraLösen.setText("Ändra lösenord");
        getContentPane().add(bAndraLösen);
        bAndraLösen.setBounds(674, 160, 115, 27);

        btnTillbakaTillAnställda.setBackground(new java.awt.Color(7, 96, 216));
        btnTillbakaTillAnställda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turn-left-small.png"))); // NOI18N
        btnTillbakaTillAnställda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaTillAnställdaActionPerformed(evt);
            }
        });
        getContentPane().add(btnTillbakaTillAnställda);
        btnTillbakaTillAnställda.setBounds(31, 21, 52, 35);

        comboAvdelning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comboAvdelning);
        comboAvdelning.setBounds(544, 262, 265, 26);

        comboBehorighet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comboBehorighet);
        comboBehorighet.setBounds(544, 337, 264, 26);

        txtAnsvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnsvarActionPerformed(evt);
            }
        });
        getContentPane().add(txtAnsvar);
        txtAnsvar.setBounds(540, 340, 262, 26);

        comboMentor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comboMentor);
        comboMentor.setBounds(544, 408, 265, 26);

        lblBehorighet.setText("Behörighet");
        getContentPane().add(lblBehorighet);
        lblBehorighet.setBounds(544, 315, 58, 16);

        lblAnsvar.setText("Ansvarighetsområde");
        getContentPane().add(lblAnsvar);
        lblAnsvar.setBounds(540, 320, 109, 16);

        lblMentor.setText("Mentor");
        getContentPane().add(lblMentor);
        lblMentor.setBounds(550, 386, 39, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLosenordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLosenordActionPerformed

    private void txtFornamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFornamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornamnActionPerformed

    private void txtAnstalllningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnstalllningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnstalllningActionPerformed

    private void txtTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonActionPerformed

    private void txtEfternamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfternamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfternamnActionPerformed

    private void btnRedigeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraActionPerformed
        
        if (setAnstalldInfo()) {
            
        }
        
        
   
    }//GEN-LAST:event_btnRedigeraActionPerformed

    private void txtAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdressActionPerformed

    private void txtEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEpostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpostActionPerformed

    private void btnTillbakaTillAnställdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaTillAnställdaActionPerformed
        this.setVisible(false);
        Anställda anst = new Anställda();
        anst.setVisible(true);
    }//GEN-LAST:event_btnTillbakaTillAnställdaActionPerformed

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
            java.util.logging.Logger.getLogger(EditAnställda1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAnställda1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAnställda1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAnställda1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton bAndraLösen;
    private javax.swing.JButton btnRedigera;
    private javax.swing.JToggleButton btnTillbakaTillAnställda;
    private javax.swing.JComboBox<String> comboAvdelning;
    private javax.swing.JComboBox<String> comboBehorighet;
    private javax.swing.JComboBox<String> comboMentor;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAnsDatum;
    private javax.swing.JLabel lblAnsvar;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblBehorighet;
    private javax.swing.JLabel lblEfternamn;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFornamn;
    private javax.swing.JLabel lblFörklarDatum;
    private javax.swing.JLabel lblLosenord;
    private javax.swing.JLabel lblMentor;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtAnstalllning;
    private javax.swing.JTextField txtAnsvar;
    private javax.swing.JTextField txtEfternamn;
    private javax.swing.JTextField txtEpost;
    private javax.swing.JTextField txtFornamn;
    private javax.swing.JTextField txtLosenord;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
