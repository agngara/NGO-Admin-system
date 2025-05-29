/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.projektfiler;

import SQLHanterare.AnstalldHanterare;
import SQLHanterare.HandlaggareHanterare;
import SQLHanterare.PartnerHanterare;
import SQLHanterare.ProjektHanterare;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import orgEntities.Projekt;
import gui.Meny;
import gui.projektruta;
import java.util.ArrayList;
import logicComponents.User.CurrentUser;
import logicComponents.User.UserType;
import logicComponents.Validering;
import orgEntities.Partner;
import orgEntities.Anstalld;


/**
 *
 * @author theow
 */
public class OneProjectView extends javax.swing.JFrame {
    
    private Projekt projekt;
    private String[] kolumnNamn;
    private Object[] rad;
    private OneProjectView oneProjectView;
    private Partner partner; 
    
    

    /**
     * Creates new form projektInfo
     * @param projekt is a project to be displayed on this page.
     */
    public OneProjectView(String pid) {
        
        oneProjectView = this;
        ProjektHanterare projektHanterare = new ProjektHanterare(pid);
        Projekt projekt = new Projekt(projektHanterare);
        
        initComponents();
        
        
//         if (CurrentUser.getUsr().getUserType() != UserType.projektchef || CurrentUser.getUsr().getUserType() != UserType.admin1 || CurrentUser.getUsr().getUserType() != UserType.admin2) {
//            
//             btnEditTeam.setVisible(false);
//            
//        }
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        String[] projInfo;
        this.projekt = projekt;
        String[] kolumnNamn = new String[] {
            "Pid", 
            "Projektnamn", 
            "Beskrivning", 
            "Startdatum", 
            "Slutdatum",
            "Kostnad",
            "Status",
            "Prioritet",
            "Projektchef",
            "land"
         };
        
        this.kolumnNamn = kolumnNamn;
        
        // These calls fills the tables of the page.
         this.fillTable();
         this.fillPartnerTable();
         this.fillTeamTable();
         
         // This fills the cost of the table in the economy jpanel
         this.setKostnad();
         
         //Hides edit buttons if user is not admin or projektchef.
         this.HideBigBossControlls();
    }
    


    
    public void fillTable() {
        
    this.updateTable();
    
    rad = new Object[]{
    projekt.getPid(),
    projekt.getProjektnamn(),
    projekt.getBeskrivning(),
    projekt.getStartdatum(),
    projekt.getSlutdatum(),
    projekt.getKostnad(),
    projekt.getStatus(),
    projekt.getPrioritet(),
    projekt.getProjektchef(),
    projekt.getLand(),
  
    };
        
    DefaultTableModel nyModel = new DefaultTableModel(kolumnNamn, 0);
    nyModel.addRow(rad);
    jTable1.setModel(nyModel);   
    
    
 
 }
    
    public void updateTable() {
    
    ProjektHanterare projektHanterare = new ProjektHanterare(projekt.getPid());
    this.projekt = new Projekt(projektHanterare);
    DefaultTableModel nyModel = (DefaultTableModel) jTable1.getModel();
    nyModel.setRowCount(0);
    
    }
    
    public void updatePartnerTable() {
    
        
    DefaultTableModel nyModel = (DefaultTableModel) tblPartners.getModel();
    nyModel.setRowCount(0);
        
    }

    
    public void updateTeamTable() {
     
    DefaultTableModel nyModel = (DefaultTableModel) tblTeam.getModel();
    nyModel.setRowCount(0);
        
    }
    
    
    
    public void fillPartnerTable() {
        this.updatePartnerTable();
        ArrayList<HashMap<String, String>> rader = new PartnerHanterare().getPartnerByProject(projekt.getPid());
        String [] columnNames = {"Partner-ID", "Namn", "Kontaktperson", "kontaktepost", "telefon", "adress", "branch", "stad"};
        
      
        DefaultTableModel model = new DefaultTableModel (columnNames, 0);

        
        for (HashMap<String, String> rad : rader) {
                        
            String pid = rad.get("pid");
            String namn = rad.get("namn");
            String kontaktperson = rad.get("kontaktperson");
            String kontaktepost = rad.get("kontaktepost");
            String telefon = rad.get("telefon");
            String adress = rad.get("adress");
            String branch = rad.get("branch");
            String stad = rad.get("stad");
           
            model.addRow(new Object[] {pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, stad});
            
        }
        
        tblPartners.setModel(model);
        
    }
    
    public void fillTeamTable() {
        
        this.updateTeamTable();
        HashMap<String, String> projchef = new AnstalldHanterare().getProjChefByProject(projekt.getPid());
        String fornamn = projchef.get("fornamn");
        String efternamn = projchef.get("efternamn");
        
        lblProjChef.setText("Projektchef: " + fornamn + " " + efternamn);
        
        ArrayList<HashMap<String, String>> rader = new HandlaggareHanterare().getHandlaggareByProject(projekt.getPid());
        String [] columnNames = {"Anställnings-ID", "Ansvarighetsområde", "Mentor"};
        
      
        DefaultTableModel model = new DefaultTableModel (columnNames, 0);

        
        for (HashMap<String, String> rad : rader) {
                        
            String aid = rad.get("aid");
            String ansvar = rad.get("ansvarighetsomrade");
            String mentor = rad.get("mentor");
            
           
            model.addRow(new Object[] {aid, ansvar, mentor});
            
        }
        
        tblTeam.setModel(model);
        
        
    }
    
    public boolean addPartnerGUI() {
        
        String id = JOptionPane.showInputDialog("Lägg till projektpartner");
        PartnerHanterare partnerHanterare = new PartnerHanterare();
        
        if (Validering.giltigDouble(id) && Validering.tomFalt("Partner-ID", id)) {
            
        partnerHanterare.addPartnerToProject(projekt.getPid(), id);
        fillPartnerTable();
        JOptionPane.showMessageDialog(rootPane,"Partner tillagd.","Lägg till projektpartner", 2);
        return true;
        }
        else {
        JOptionPane.showMessageDialog(rootPane,"Ange ett giltigt partner-ID.","Lägg till projektpartner", 1);
        return false;

        }
    }
    
    
    public void removePartner(String partnerID) {
        
   
        if (new PartnerHanterare().removePartnerFromProject(projekt.getPid(), partnerID)) {
            JOptionPane.showMessageDialog(rootPane, "Partner borttagen.");
            fillPartnerTable();
            
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "Kunde inte ta bort partner.");

        }
    }
    // TODO: fixa validering, någon call ger oönskad return (boolean)
    public boolean addTeamMemberGUI() {
    
        String id = JOptionPane.showInputDialog("Lägg till handläggare");
        HandlaggareHanterare handlaggareHanterare = new HandlaggareHanterare();
        
        //if (Validering.giltigDouble(id) && !Validering.tomFalt("Handläggar-ID", id) && !Validering.finnsHandlaggare(id) && !Validering.finnsHandlaggareIprojekt(id, projekt.getPid())) {
          
        handlaggareHanterare.addHandlaggareToProject(projekt.getPid(), id);
        fillTeamTable();
        JOptionPane.showMessageDialog(rootPane,"Partner tillagd.","Lägg till projektpartner", 2);
        return true;
        //}
        //else {
        //JOptionPane.showMessageDialog(rootPane,"Ange ett giltigt partner-ID.","Lägg till projektpartner", 1);
        //return false;

        //}

        
    } 
    
        public void removeTeamMember(String aid) {
        
   
        if (new HandlaggareHanterare().removeHandlaggareFromProject(projekt.getPid(),aid)) {
            JOptionPane.showMessageDialog(rootPane, "Handläggare borttagen.");
            fillTeamTable();
            
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "Kunde inte ta bort handläggare");

        }
    }
   
        public void setKostnad() {
        
        lblDisplayKostnad2.setText(projekt.getKostnad() + "kr");
    }
        
        public void HideBigBossControlls() {
            
            AnstalldHanterare anstalldHanterare = new AnstalldHanterare();
            HashMap<String, String> anstalldrow = anstalldHanterare.getProjChefByProject(projekt.getPid());
            Anstalld anstalld = new Anstalld(anstalldrow);
            
            if (CurrentUser.getUsr().getUserType() != UserType.projektchef ||CurrentUser.getUsr().getUserType()!= UserType.admin1 || CurrentUser.getUsr().getUserType() != UserType.admin2) {
                
                lblEditProject1.setVisible(false);
                lblAddPartner1.setVisible(false);
                lblRemovePartner.setVisible(false);
                lblRemoveHandlaggare.setVisible(false);
                lblAddHandlaggare.setVisible(false);
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

        projektInfo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblInsikter = new javax.swing.JLabel();
        lbllProjName1 = new javax.swing.JLabel();
        btnTillbakaTillProjektRuta = new javax.swing.JToggleButton();
        lblPartners = new javax.swing.JLabel();
        lblEkonomi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDisplayKostnadSoFar = new javax.swing.JLabel();
        lblDisplayKostnad2 = new javax.swing.JLabel();
        lblKostnadCompare = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPartners = new javax.swing.JTable();
        lblRemovePartner = new javax.swing.JLabel();
        lblAddPartner1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTeam = new javax.swing.JTable();
        lblProjChef = new javax.swing.JLabel();
        lblAddHandlaggare = new javax.swing.JLabel();
        lblRemoveHandlaggare = new javax.swing.JLabel();
        lblTeam = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblEditProject1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(854, 480));

        projektInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable1.setBackground(new java.awt.Color(29, 29, 29));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jTable1.setDoubleBuffered(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout projektInfoLayout = new javax.swing.GroupLayout(projektInfo);
        projektInfo.setLayout(projektInfoLayout);
        projektInfoLayout.setHorizontalGroup(
            projektInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projektInfoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        projektInfoLayout.setVerticalGroup(
            projektInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projektInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblInsikter.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblInsikter.setText("Insikter");

        lbllProjName1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lbllProjName1.setText("Projektnamn");

        btnTillbakaTillProjektRuta.setBackground(new java.awt.Color(7, 96, 216));
        btnTillbakaTillProjektRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/turn-left-small.png"))); // NOI18N
        btnTillbakaTillProjektRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaTillProjektRutaActionPerformed(evt);
            }
        });

        lblPartners.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPartners.setText("Partners");

        lblEkonomi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEkonomi.setText("Ekonomi");

        jPanel2.setBackground(new java.awt.Color(40, 40, 40));

        jPanel3.setBackground(new java.awt.Color(40, 40, 40));

        lblDisplayKostnadSoFar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDisplayKostnadSoFar.setText("Kostnad hittils");

        lblDisplayKostnad2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblDisplayKostnad2.setForeground(new java.awt.Color(7, 96, 216));
        lblDisplayKostnad2.setText("30000");

        lblKostnadCompare.setText("Kostnaden är 30% högre än medelvärdet ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(lblDisplayKostnadSoFar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisplayKostnad2)
                            .addComponent(lblKostnadCompare))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(lblDisplayKostnadSoFar)
                .addGap(51, 51, 51)
                .addComponent(lblDisplayKostnad2)
                .addGap(60, 60, 60)
                .addComponent(lblKostnadCompare)
                .addContainerGap(376, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(40, 40, 40));

        tblPartners.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPartners);

        lblRemovePartner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-symbol.png"))); // NOI18N
        lblRemovePartner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemovePartnerMouseClicked(evt);
            }
        });

        lblAddPartner1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addition.png"))); // NOI18N
        lblAddPartner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddPartner1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblRemovePartner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAddPartner1)))
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRemovePartner)
                    .addComponent(lblAddPartner1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(40, 40, 40));

        tblTeam.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblTeam);

        lblProjChef.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProjChef.setForeground(new java.awt.Color(7, 96, 216));
        lblProjChef.setText("Projektchef: ");

        lblAddHandlaggare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addition.png"))); // NOI18N
        lblAddHandlaggare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddHandlaggareMouseClicked(evt);
            }
        });

        lblRemoveHandlaggare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-symbol.png"))); // NOI18N
        lblRemoveHandlaggare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoveHandlaggareMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblProjChef)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRemoveHandlaggare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAddHandlaggare)))
                .addGap(40, 40, 40))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProjChef)
                    .addComponent(lblRemoveHandlaggare)
                    .addComponent(lblAddHandlaggare))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTeam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTeam.setText("Team");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTeam)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTeam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 448, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(40, 40, 40));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );

        lblEditProject1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pen32.png"))); // NOI18N
        lblEditProject1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditProject1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(projektInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnTillbakaTillProjektRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lbllProjName1)
                        .addGap(1059, 1059, 1059)
                        .addComponent(lblEditProject1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(367, 367, 367)
                                .addComponent(lblPartners))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1684, 1684, 1684)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEkonomi)
                            .addComponent(lblInsikter))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbllProjName1)
                            .addComponent(btnTillbakaTillProjektRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEditProject1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(projektInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInsikter)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEkonomi)
                            .addComponent(lblPartners))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaTillProjektRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaTillProjektRutaActionPerformed
        this.setVisible(false);
        projektruta proj = new projektruta();
        proj.setVisible(true);
    }//GEN-LAST:event_btnTillbakaTillProjektRutaActionPerformed

    private void lblEditProject1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProject1MouseClicked
        
        new EditProjectFields(projekt, this.oneProjectView).setVisible(true); {
        
            
        }
        
    }//GEN-LAST:event_lblEditProject1MouseClicked

    private void lblAddPartner1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddPartner1MouseClicked
        
        addPartnerGUI();
            
            
        
    }//GEN-LAST:event_lblAddPartner1MouseClicked

    private void lblRemovePartnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemovePartnerMouseClicked
        
        int selectedRow = tblPartners.getSelectedRow();
        int column = 1; 
        String partnerID = "";
        partnerID = (String) tblPartners.getValueAt(selectedRow, 0);

        if (selectedRow != -1) { 
            
            if (JOptionPane.showConfirmDialog(rootPane, "Är du säker på att du vill ta bort denna partner?") == JOptionPane.YES_OPTION ) {
            
                removePartner(partnerID);
            }
            
            
        } else {
            
            System.out.println("Ingen rad är vald.");
        }
    }//GEN-LAST:event_lblRemovePartnerMouseClicked

    private void lblAddHandlaggareMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddHandlaggareMouseClicked
        
        this.addTeamMemberGUI();
        
        
    }//GEN-LAST:event_lblAddHandlaggareMouseClicked

    private void lblRemoveHandlaggareMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveHandlaggareMouseClicked
        int selectedRow = tblTeam.getSelectedRow();
        int column = 1; 
        String partnerID = "";
        String aid = (String) tblTeam.getValueAt(selectedRow, 0);

        if (selectedRow != -1) { 
            
            if (JOptionPane.showConfirmDialog(rootPane, "Är du säker på att du vill ta bort denna handläggare?") == JOptionPane.YES_OPTION ) {
         
                this.removeTeamMember(aid);
            }
            
            
        } else {
            
            System.out.println("Ingen rad är vald.");
        }
                                             
    }//GEN-LAST:event_lblRemoveHandlaggareMouseClicked


    
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
            java.util.logging.Logger.getLogger(OneProjectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OneProjectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OneProjectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OneProjectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new OneProjectView().setVisible(true);
            }
        });
        
        
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnTillbakaTillProjektRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAddHandlaggare;
    private javax.swing.JLabel lblAddPartner1;
    private javax.swing.JLabel lblDisplayKostnad2;
    private javax.swing.JLabel lblDisplayKostnadSoFar;
    private javax.swing.JLabel lblEditProject1;
    private javax.swing.JLabel lblEkonomi;
    private javax.swing.JLabel lblInsikter;
    private javax.swing.JLabel lblKostnadCompare;
    private javax.swing.JLabel lblPartners;
    private javax.swing.JLabel lblProjChef;
    private javax.swing.JLabel lblRemoveHandlaggare;
    private javax.swing.JLabel lblRemovePartner;
    private javax.swing.JLabel lblTeam;
    private javax.swing.JLabel lbllProjName1;
    private javax.swing.JPanel projektInfo;
    private javax.swing.JTable tblPartners;
    private javax.swing.JTable tblTeam;
    // End of variables declaration//GEN-END:variables
}

