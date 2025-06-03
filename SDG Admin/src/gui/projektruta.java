package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import SQLHanterare.*;
import orgEntities.*;
import db.DatabaseInterface;
import gui.*;
import gui.projektfiler.OneProjectView;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import logicComponents.User.CurrentUser;
import logicComponents.User.UserType;
import java.awt.event.ActionEvent;
import orgEntities.Projekt;
import sdg.admin.projekt;
import SQLHanterare.PartnerHanterare;
import java.util.Date;
import java.awt.event.ItemEvent;

/**
 *
 * @author amandahelinlarsson
 */
public class projektruta extends javax.swing.JFrame {
    private Projekt projekt;
    private InfDB idb;
    private projektruta projektr;
   
    
                
    
    
private Anstalld currentAnstalld;    
    
 public projektruta(){
    initComponents(); 
    setExtendedState(MAXIMIZED_BOTH); // design
    setLocationRelativeTo(null);
    projektr = this;
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chkAvdelningActionPerformed(evt);
        }
    });
    
try {
    idb = DatabaseInterface.databaseConnection();
    fyllTabell(); // fyller tabellen utifrån databasen
} catch (Exception e){
 }
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
searchDateByActionPerformed(evt); 
}
});
jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
jComboBox1ActionPerformed(evt);
}
    });


   
if (CurrentUser.getUsr() !=null) {
    UserType userType = CurrentUser.getUsr().getUserType(); // Visar vilken typ av användare som är inloggad
    String userTypeString = userType.toString();
} else {
    System.out.println ("Ingen användare är inloggad.");
    return;

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
        String query = ""; // Fylls i beroende på vilken användare som är inloggad
        currentAnstalld = CurrentUser.getUsr().getAnstalld(); // Hämtar den nuvarande inloggade användaren
        String aid = currentAnstalld.getAid(); // Hämtar den inloggade användarens användarID
        UserType userType = currentAnstalld.getRole(aid);

           if (userType == UserType.handlaggare){
         query = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, p.kostnad, p.prioritet, p.status " +
                    "FROM projekt p " +
                 "JOIN ans_proj ap ON p.pid = ap.pid " +  // Koppla projekt tabellen till anställda tabellen, handläggaren får endast se de projekt hen ör kopplad till via tabellen ans_proj
                 "WHERE ap.aid =  " +"'" + aid + "'";
        } 
   else if (userType == UserType.admin1 || userType == UserType.admin2){
    query = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, prioritet, status FROM projekt"; // Admin 1 och 2 får se alla projekt
        } 
   else if (userType == UserType.projektchef){
       query = "SELECT * FROM projekt WHERE projektchef IN (SELECT aid FROM anstalld WHERE aid = " + aid + ");"; // Visar projekt där projektchefens aid matchar i tabellen
       System.out.println(query); 


   }

        ArrayList<HashMap< String, String >> projektlista = idb.fetchRows(query);
        System.out.println("Antal projekt hämtade:" + projektlista.size()); // Skriver ut antal projekt som hittades när när SQL-frågan körs
        String [] columnNames = {"pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "kostnad", "prioritet", "status","Visa"}; // Skapar en tabell med kolumnnamn matchande de i databasen

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
        model.addRow(new Object[] {pid, namn, beskrivning, start, slut, kostnad, prioritet, status, "Visa"}); // Hämtar alla kolumner som behövs i tabellen
    }

    tblProjekt.setModel(model);
    } catch (InfException e) {
    JOptionPane.showMessageDialog(this, "Fel vid hämtning av projektdata: " + e.getMessage()); // visar om något blev fel
    }
}

private void searchDateByActionPerformed(java.awt.event.ActionEvent evt) { // metoden körs när kanppen trycks på
    try {
        String startDate = jTextFieldStart.getText();
        String endDate = jTextFieldend.getText();
        
        if (startDate.isEmpty() || endDate.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ange start och slutdatum."); // Om inget datum skrivs in kommer ett meddelande upp 
            return; }

     String query = "SELECT *  FROM projekt WHERE startdatum >= '" + startDate +"' AND slutdatum <= '" + endDate + "'";
       // söker på projekt utifrån det datum som anges i sökrutan
System.out.println("SQL-fråga: " + query);
ArrayList<HashMap<String, String>> projektlista = idb.fetchRows(query); // kör frågan mot databasen för att se vilka datum som matchar, returnerar en lista med projekt som matchar i datum
if (projektlista == null || projektlista.isEmpty()) {
JOptionPane.showMessageDialog(this, "Inga projekt hittades."); // om inga projekt hittades med matchande datum kommer ett meddelande upp med den informationen
return;
}
String [] columnNames = {"pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "prioritet", "kostnad", "status", "Visa"}; // kolumnnamn som ska användas i tabellen
DefaultTableModel model = new DefaultTableModel(columnNames, 0);

for (HashMap<String, String> projekt : projektlista) { // loopar igenom alla projekt
    String pid = projekt.get("pid");
    String namn = projekt.get("projektnamn");
    String beskrivning = projekt.get ("beskrivning");
    String start = projekt.get("startdatum");
    String slut = projekt.get("slutdatum");
    String prioritet = projekt.get("prioritet");
    String kostnad = projekt.get("kostnad");
    String status = projekt.get("status"); // hämtar ut fälten från projekt
    model.addRow(new Object[]{pid, namn, beskrivning, start, slut, prioritet, kostnad, status, "Visa"});  // lägger till en rad för varje del
        
}
tblProjekt.setModel(model);
    } catch (InfException e){
        e.printStackTrace();
JOptionPane.showMessageDialog(this, "Fel vid hämtning: " + e.getMessage()); // meddelande som visas om det vart fel
return;
    }
    }

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    try {

        String selectedStatus = (String) jComboBox1.getSelectedItem(); // hämtar det valda värdet som användaren klickat i
        String query;
        if (selectedStatus.equals("Alla")){ // om alla projekt är valda visas alla projekt
            query = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, p.prioritet, p.kostnad, p.status " +
                    "FROM projekt p"; // här hämtas då alla projekt oberoende av status
} else { 
            query = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, p.prioritet, p.kostnad, p.status " +
                    "FROM projekt p " +
            "WHERE p.Status = '" + selectedStatus +"'"; // hämtar projekt utifrån vad användaren klikcat i
}
        System.out.println("SQL-fråga: " + query);
        ArrayList<HashMap<String, String>> projektlista = idb.fetchRows(query); // hämtar projekten från databasen utifrån vald status
        String [] columnNames = { "pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "prioritet", "kostnad", "status", "" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); //skapar en tabell
        for (HashMap<String, String> projekt : projektlista ){
            String pid = projekt.get("pid");
            String namn = projekt.get("projektnamn");
            String beskrivning = projekt.get("beskrivning");
            String start = projekt.get("startdatum");
            String slut = projekt.get("slutdatum");
            String prioritet = projekt.get("prioritet");
            String kostnad = projekt.get("kostnad");
            String status = projekt.get("status"); // går igenom alla projekt och lägger till i tabellen
            
            model.addRow(new Object[] {pid, namn, beskrivning, start, slut, prioritet, kostnad, status, "Visa"});
}
tblProjekt.setModel(model);
} catch (InfException e) {
e.printStackTrace();
JOptionPane.showMessageDialog(this, "fel vid hämtning av projektdata: " + e.getMessage()); // visar felmeddelande om något blev fel
}}

private void chkAvdelningActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        boolean endastMinAvdelning = jCheckBox1.isSelected(); // kolla om checkboxen är ibockad

        if (endastMinAvdelning) {
            if (CurrentUser.getUsr() == null) {
                System.out.println("Ingen användare är inloggad.");
                return;
            }

            String avdid = CurrentUser.getUsr().getAnstalld().getAvdelning();
            System.out.println("Avdelnings_ID: " + avdid);

            String query = "SELECT DISTINCT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, " +
                           "p.prioritet, p.kostnad, p.status " +
                           "FROM projekt p " +
                           "JOIN ans_proj ap ON p.pid = ap.pid " +
                           "JOIN anstalld a ON ap.aid = a.aid " +
                           "WHERE a.avdelning = '" + avdid + "'";

            System.out.println("SQL-fråga: " + query);
            ArrayList<HashMap<String, String>> projektlista = idb.fetchRows(query);
            if (projektlista == null || projektlista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inga projekt hittades.");
                return;
            }
            String[] columnNames = {"pid", "projektnamn", "beskrivning", "startdatum", "slutdatum", "prioritet", "kostnad", "status", ""};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            for (HashMap<String, String> projekt : projektlista) {
                String pid = projekt.get("pid");
                String namn = projekt.get("projektnamn");
                String beskrivning = projekt.get("beskrivning");
                String start = projekt.get("startdatum");
                String slut = projekt.get("slutdatum");
                String prioritet = projekt.get("prioritet");
                String kostnad = projekt.get("kostnad");
                String status = projekt.get("status");
                model.addRow(new Object[]{pid, namn, beskrivning, start, slut, prioritet, kostnad, status, "visa"});
            }
            tblProjekt.setModel(model);

        } else {
            // rutan är avkryssad visa då metoden som va innan rutan kryssades i
            fyllTabell(); 
        }

    } catch (InfException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fel vid hämtning av projekt: " + e.getMessage());
    }
}





public void tableMouseEvent(projektruta projektr) {
        
        tblProjekt.addMouseListener(new java.awt.event.MouseAdapter() { // lägger till en muslyssnare på 
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) { // tar reda på vilken kolumn och rad som är i klickad
        int row = tblProjekt.rowAtPoint(evt.getPoint());
        int col = tblProjekt.columnAtPoint(evt.getPoint());
        if (row >= 0 && col == 8) {
                
            Object varde = tblProjekt.getValueAt(row, 0); // hämtar värdet i den i klickade kolumnen
            String pid = varde.toString();
            OneProjectView oneProjectView = new OneProjectView(pid);
            oneProjectView.setVisible(true); // visar ett nytt visningsfönster
            projektr.setVisible(false);
            
            

            }
        }
    }
    );
        
    }

    public void removeProjekt(String pid) {
        // anropar metoden tabortprojekt
        if (new ProjektHanterare().taBortProjekt(pid)) { // försöka ta bort projekt med ett angivet projektid (pid)
            JOptionPane.showMessageDialog(rootPane, "Projekt borttaget."); // om projektet togs bort visas detta meddelande
            fyllTabell();
            
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "Kunde inte ta bort projekt."); // visas om borttagningen misslyckades

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
        proTillbakaTillMeny = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProjekt = new javax.swing.JTable();
        bnTaBortProjekt = new javax.swing.JButton();
        bnLaggTillProjekt = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldStart = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextFieldend = new javax.swing.JTextField();

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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Sök projekt start - slutdatum");

        jCheckBox1.setText("Visa bara projekt på min avdelning");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alla", "Planerat", "Pågående", "Avslutat" }));

        jTextFieldend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldend)))
                        .addGap(24, 24, 24)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addGap(63, 63, 63)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(proTillbakaTillMeny)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bnTaBortProjekt)
                                .addGap(18, 18, 18)
                                .addComponent(bnLaggTillProjekt))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(101, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)
                                    .addComponent(jTextFieldend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(proTillbakaTillMeny, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnTaBortProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnLaggTillProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proTillbakaTillMenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proTillbakaTillMenyActionPerformed
        this.setVisible(false);
        new Meny().setVisible(true);
    }//GEN-LAST:event_proTillbakaTillMenyActionPerformed

    private void bnTaBortProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTaBortProjektActionPerformed
        
       int selectedRow = tblProjekt.getSelectedRow();
        int column = 1;  // hämtar den valda radens index i tabellen
        String projektID = ""; // används för att lagra projektets id
        projektID = (String) tblProjekt.getValueAt(selectedRow, 0);

        if (selectedRow != -1) { 
            
            if (JOptionPane.showConfirmDialog(rootPane, "Är du säker på att du vill ta bort detta projekt?") == JOptionPane.YES_OPTION ) {
            // meddelande om man är säker på att man vill ta bort projekt med svarsalternativ
                removeProjekt(projektID);
            }
            
            
        } else {
            
            System.out.println("Ingen rad är vald."); // om ingen rad är vald visas detta meddelande
        }
    }//GEN-LAST:event_bnTaBortProjektActionPerformed

    private void bnLaggTillProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLaggTillProjektActionPerformed
         new LäggTillProjekt().setVisible(true);
//                this.setVisible(false);
    }//GEN-LAST:event_bnLaggTillProjektActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldendActionPerformed

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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldStart;
    private javax.swing.JTextField jTextFieldend;
    private javax.swing.JButton proTillbakaTillMeny;
    private javax.swing.JTable tblProjekt;
    // End of variables declaration//GEN-END:variables
}

