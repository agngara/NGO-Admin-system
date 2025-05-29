package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import orgEntities.Anstalld;
import db.DatabaseInterface;
import gui.Meny;
import gui.projektfiler.OneProjectView;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import logicComponents.User.CurrentUser;
import logicComponents.User.UserType;

/**
 *
 * @author amandahelinlarsson
 */
public class projektruta extends javax.swing.JFrame {
    private InfDB idb;
    private projektruta projektr;
    
private Anstalld currentAnstalld;    
    
 public projektruta(){
    initComponents(); 
    setExtendedState(MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    projektr = this;
try {
    idb = DatabaseInterface.databaseConnection();
    fyllTabell();
} catch (Exception e){
 }
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
searchDateByActionPerformed(evt);
}
});


   
if (CurrentUser.getUsr() !=null) {
    UserType userType = CurrentUser.getUsr().getUserType();
    String userTypeString = userType.toString();
} else {

}
    try {
    this.tableMouseEvent(projektr);
    } 
    catch (Exception e) {
        
        System.out.println(e);
    }

 }
    
private void fyllTabell(){
        
    try {
        String query = "";
        currentAnstalld = CurrentUser.getUsr().getAnstalld();
        String aid = currentAnstalld.getAid();
        UserType userType = currentAnstalld.getRole(aid);

        if (userType == UserType.handlaggare){
             query = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, prioritet, status" +
                        "FROM projekt WHERE handlaggare_id = '" + aid + "'";
        } else if (userType == UserType.admin1 || userType == UserType.admin2){
        query = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, prioritet, status FROM projekt";
} else if (userType == UserType.projektchef){
       query = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum , p.kostnad, p.prioritet, p.status " +
               "FROM projekt p " +
               "WHERE p.projektchef_id = '" + aid + "'" ;

   }
    System.out.println("SQL-fråga: " + query);

  


        ArrayList<HashMap< String, String >> projektlista = idb.fetchRows(query);
        System.out.println("Antal projekt hämtade:" + projektlista.size());
        String [] columnNames = {"pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "kostnad", "prioritet", "status" };

    DefaultTableModel model = new DefaultTableModel (columnNames, 0);

    for (HashMap<String, String> projekt : projektlista){

        String pid = projekt.get("pid");
        String namn = projekt.get("projektnamn");
        String beskrivning = projekt.get ("beskrivning");
        String start = projekt.get ("startdatum");
        String slut = projekt.get ("slutdatum");
        String prioritet = projekt.get("prioritet");
        String kostnad = projekt.get ("kostnad"); 
        String status = projekt.get ("status");
        model.addRow(new Object[] {pid, namn, beskrivning, start, slut, kostnad, prioritet, status, "Visa"});
    }

    tblProjekt.setModel(model);
    } catch (InfException e) {
    JOptionPane.showMessageDialog(this, "Fel vid hämtning av projektdata: " + e.getMessage());
    }
}

private void searchDateByActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String searchDate = jTextField1.getText();
        if (searchDate.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ange sökdatum.");
            return;
        }
   
    
        String query = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, p.prioritet, p.kostnad, p.status " +
                " FROM projekt p " + 
       "WHERE p.startdatum = '" + searchDate + "' OR p.slutdatum = '" + searchDate +"'";
System.out.println("SQL-fråga: " + query);
ArrayList<HashMap<String, String>> projektlista = idb.fetchRows(query);
if (projektlista == null || projektlista.isEmpty()) {
JOptionPane.showMessageDialog(this, "Inga projekt hittades.");
return;
}
String [] columnNames = {"pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "prioritet", "kostnad", "status"};
DefaultTableModel model = new DefaultTableModel(columnNames, 0);

for (HashMap<String, String> projekt : projektlista) {
    String pid = projekt.get("pid");
    String namn = projekt.get("projektnamn");
    String beskrivning = projekt.get ("beskrivning");
    String start = projekt.get("startdatum");
    String slut = projekt.get("slutdatum");
    String prioritet = projekt.get("prioritet");
    String kostnad = projekt.get("kostnad");
    String status = projekt.get("status");
    model.addRow(new Object[]{pid, namn, beskrivning, start, slut, prioritet, kostnad, status});
        
}
tblProjekt.setModel(model);
    } catch (InfException e){
        e.printStackTrace();
JOptionPane.showMessageDialog(this, "Fel vid hämtning: " + e.getMessage());
return;
    }
    }





public void tableMouseEvent(projektruta projektr) {
        
        tblProjekt.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblProjekt.rowAtPoint(evt.getPoint());
        int col = tblProjekt.columnAtPoint(evt.getPoint());
        if (row >= 0 && col == 7) {
                
            Object varde = tblProjekt.getValueAt(row, 0);
            String pid = varde.toString();
            OneProjectView oneProjectView = new OneProjectView(pid);
            oneProjectView.setVisible(true);
            projektr.setVisible(false);
            
            

            }
        }
    }
    );
        
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
        proTillbakaTillMeny = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProjekt = new javax.swing.JTable();
        bnTaBortProjekt = new javax.swing.JButton();
        bnLaggTillProjekt = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Projekt");

        proTillbakaTillMeny.setBackground(new java.awt.Color(51, 102, 255));
        proTillbakaTillMeny.setForeground(new java.awt.Color(255, 255, 255));
        proTillbakaTillMeny.setText("Tillbaka till meny");
        proTillbakaTillMeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proTillbakaTillMenyActionPerformed(evt);
            }
        });

        tblProjekt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "pid", "projektnamn", "beskrivning", "Startdatum", "slutdatum", "kostnad", "prioritet", "status"
            }
        ));
        jScrollPane1.setViewportView(tblProjekt);

        bnTaBortProjekt.setBackground(new java.awt.Color(204, 0, 0));
        bnTaBortProjekt.setForeground(new java.awt.Color(255, 255, 255));
        bnTaBortProjekt.setText("Ta bort projekt");
        bnTaBortProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTaBortProjektActionPerformed(evt);
            }
        });

        bnLaggTillProjekt.setBackground(new java.awt.Color(0, 204, 0));
        bnLaggTillProjekt.setForeground(new java.awt.Color(255, 255, 255));
        bnLaggTillProjekt.setText("Lägg till projekt");
        bnLaggTillProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnLaggTillProjektActionPerformed(evt);
            }
        });

        jButton1.setText("Sök");

        jLabel2.setText("Sök projekt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(proTillbakaTillMeny)
                                .addGap(453, 453, 453)
                                .addComponent(bnTaBortProjekt)
                                .addGap(30, 30, 30)
                                .addComponent(bnLaggTillProjekt))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(54, 54, 54))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnTaBortProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proTillbakaTillMenyActionPerformed
        this.setVisible(false);
        new Meny().setVisible(true);
    }//GEN-LAST:event_proTillbakaTillMenyActionPerformed

    private void bnTaBortProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortProjektActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bnTaBortProjektActionPerformed

    private void bnLaggTillProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLaggTillProjektActionPerformed
         new LäggTillProjekt().setVisible(true);
//                this.setVisible(false);
    }//GEN-LAST:event_bnLaggTillProjektActionPerformed

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
            java.util.logging.Logger.getLogger(projektruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(projektruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(projektruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(projektruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new projektruta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnLaggTillProjekt;
    private javax.swing.JButton bnTaBortProjekt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton proTillbakaTillMeny;
    private javax.swing.JTable tblProjekt;
    // End of variables declaration//GEN-END:variables
}

