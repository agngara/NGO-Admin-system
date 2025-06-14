/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import SQLHanterare.AnstalldHanterare;
import SQLHanterare.AvdelningHanterare;
import oru.inf.InfDB;
import oru.inf.InfException;
import db.DatabaseInterface;
import gui.Avdelningar;
import gui.Meny;
import gui.anställdafiler.EditAnställda1;
import gui.anställdafiler.EditAvdelning;
import gui.anställdafiler.LäggTillAvdelning;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicComponents.User.CurrentUser;
import logicComponents.User.User;
import logicComponents.User.UserType;
import orgEntities.Anstalld;
import orgEntities.Avdelning;

/**
 *
 * @author stina
 */
public class Avdelningar extends javax.swing.JFrame {
    private InfDB idb;
    Avdelningar avdelningar;
    
    
    /**
     * Creates new form Avdelning
     * Konstruktor
     */
    public Avdelningar() {
        avdelningar = this;
        initComponents();
        setExtendedState(MAXIMIZED_BOTH); // maximerar fönstret
        setLocationRelativeTo(null); // centrerar fönstret
     try {
        idb = DatabaseInterface.databaseConnection();
        fyllTabell();// fyller tabellen från databasen
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Kunde inte ansluta till databasen");
    }
        tableMouseEvent();
    }
    

    
   // fyller tabellen 
    private void fyllTabell(){
        

        try {
            String query = "SELECT avdid, namn, beskrivning, adress, epost, telefon, stad, chef FROM avdelning";
            ArrayList<HashMap< String, String >> avdelningLista = idb.fetchRows(query);

            String [] columnNames = {"avdid", "namn", "beskrivning", "adress", "epost", "telefon", "stad", "chef", "Redigera"};
            DefaultTableModel model = new DefaultTableModel (columnNames, 0);
            
            if (avdelningLista == null || avdelningLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inga avdelningar hittades i databasen!");
            } else {
                for (HashMap<String, String> avdelning : avdelningLista){
                String avdid = avdelning.get("avdid");
                String namn = avdelning.get("namn");
                String beskrivning = avdelning.get ("beskrivning");
                String adress = avdelning.get ("adress");
                String epost = avdelning.get("epost");
                String telefon = avdelning.get("telefon");
                String stad = avdelning.get("stad");
                String chef = avdelning.get("chef");
            model.addRow(new Object[] {avdid, namn, beskrivning, adress, epost, telefon, stad, chef, "Redigera"});
        }
    }
    tblAvdelning.setModel(model);
    } catch (InfException e) {
        JOptionPane.showMessageDialog(this, "Fel vid hämtning av projektdata: " + e.getMessage());
        } 
    
    }
    
    
 // klicklyssnare för att kunna redigera avdelning.   
    public void tableMouseEvent() {
        
        tblAvdelning.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblAvdelning.rowAtPoint(evt.getPoint());
        int col = tblAvdelning.columnAtPoint(evt.getPoint());
        if (row >= 0 && col == 8) {
                
            Object varde = tblAvdelning.getValueAt(row, 0);
            String avdid = varde.toString();
            AvdelningHanterare avdelningHanterare = new AvdelningHanterare(avdid);
            Avdelning avdelning = new Avdelning(avdelningHanterare);
            EditAvdelning editAvdelning = new EditAvdelning(avdelning);
            editAvdelning.setVisible(true);
//            avdelningar.setVisible(false);

            }
        }
    }
    );    
 }
    
  // ta bort en avdelning
    public void removeAvdelning(String avdid) {
        
        if (new AvdelningHanterare().taBortAvdelning(avdid)) {
            JOptionPane.showMessageDialog(rootPane, "Avdelning borttagen.");
            fyllTabell();
            
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "Kunde inte ta bort avdelning.");

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
        avdTillbakaTillMeny = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvdelning = new javax.swing.JTable();
        bnTaBortAvdelning = new javax.swing.JButton();
        bnLaggTillAvdelning = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Avdelning:");

        avdTillbakaTillMeny.setBackground(new java.awt.Color(0, 102, 255));
        avdTillbakaTillMeny.setForeground(new java.awt.Color(255, 255, 255));
        avdTillbakaTillMeny.setText("Tillbaka till meny");
        avdTillbakaTillMeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avdTillbakaTillMenyActionPerformed(evt);
            }
        });

        tblAvdelning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAvdelning);

        bnTaBortAvdelning.setBackground(new java.awt.Color(204, 0, 0));
        bnTaBortAvdelning.setForeground(new java.awt.Color(255, 255, 255));
        bnTaBortAvdelning.setText("Ta bort Avdelning");
        bnTaBortAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortAvdelningActionPerformed(evt);
            }
        });

        bnLaggTillAvdelning.setBackground(new java.awt.Color(0, 204, 0));
        bnLaggTillAvdelning.setForeground(new java.awt.Color(255, 255, 255));
        bnLaggTillAvdelning.setText("Lägg till avdelning");
        bnLaggTillAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnLaggTillAvdelningActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(avdTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnTaBortAvdelning)
                        .addGap(18, 18, 18)
                        .addComponent(bnLaggTillAvdelning))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bnLaggTillAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bnTaBortAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(avdTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void avdTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avdTillbakaTillMenyActionPerformed
        this.setVisible(false);
        new Meny().setVisible(true);
    }//GEN-LAST:event_avdTillbakaTillMenyActionPerformed

    private void bnTaBortAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortAvdelningActionPerformed
 // kontrollerar om en rad är vald, om ja så anropas avdelningen.
        int selectedRow = tblAvdelning.getSelectedRow();
          //int column = 1; 
          if (selectedRow == -1) {
              JOptionPane.showMessageDialog(null,"Ingen vald rad");
              return;
          }
          
          int columnIndex = 0;
          String avdid = (String) tblAvdelning.getValueAt(selectedRow, columnIndex);

       
            
          if (JOptionPane.showConfirmDialog(rootPane, "Är du säker på att du vill ta bort avdelningen?") == JOptionPane.YES_OPTION ) {
            
                removeAvdelning(avdid);
            }
    }//GEN-LAST:event_bnTaBortAvdelningActionPerformed

    private void bnLaggTillAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLaggTillAvdelningActionPerformed
        new LäggTillAvdelning().setVisible(true);
        //        this.setVisible(false);
    }//GEN-LAST:event_bnLaggTillAvdelningActionPerformed

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
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avdelningar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Avdelningar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton avdTillbakaTillMeny;
    private javax.swing.JButton bnLaggTillAvdelning;
    private javax.swing.JButton bnTaBortAvdelning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAvdelning;
    // End of variables declaration//GEN-END:variables
}
