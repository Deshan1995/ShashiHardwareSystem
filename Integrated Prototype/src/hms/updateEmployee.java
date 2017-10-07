/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import DBconnection.DBconnect;
import DBclass.me;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DELL-PC
 */
public class updateEmployee extends javax.swing.JInternalFrame {

       String s,s1;
       Connection  conn = null;
       PreparedStatement pst = null,pst1=null,pst3=null;
       ResultSet rs = null,rs1 = null,rs3=null;
       byte[] person_image = null;
       String selectedb=null;
       
       //to store current values
       String fname,uname,lname,phn,addr,nic,language,age,desig,status;
       byte[] ba;
       
       boolean hada=false;
       
    /**
     * Creates new form updateEmployee
     */
    public updateEmployee() {
        initComponents();
        conn=DBconnect.connectDb();
        tableload();
        ButtonGroup group = new ButtonGroup();
        group.add(usid);
        group.add(usname);
        
        ButtonGroup group1 = new ButtonGroup();
        group1.add(uftime);
        group1.add(uptime);
        
        usid.setSelected(true);
        uftime.setSelected(true);
        showtime();
    }
    
    void showtime(){
        new Timer(0,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d=new Date();
                SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss a");
                time.setText(s.format(d));
            }
        }).start();
    }
    public void tableload(String s,String b)
    {
        if(b=="name")
        {
            
        String sql = "select * from Employee where username like '%"+s+"%'";
        try{
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
         {
                
         }finally
        {
                try {
                    pst.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
        }
        else if(b == "id")
        {
            
            
        String sql = "select * from Employee where employee_id like'%"+s+"%'";
        try{
            
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        }
       
    }
  
    
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
    
    public void tableload()
    {
        
        String sql = "select * from Employee";
        try{
            
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        
//        if(rs.next())
//        {
//        
//            ImageIcon im = new ImageIcon(rs.getBytes("photograph"));
//            jLabel2.setIcon(im);
//            
//        }        
//if(rs.next())
//{
//    Blob temp;
//    Image tempImg=null;
//    temp = rs.getBlob("photograph");
//    int blobLength = (int) temp.length();
//    byte[] blobbytes = temp.getBytes(1, blobLength);
//    tempImg = new ImageIcon(blobbytes).getImage();
//    
//    ImageIcon i = new ImageIcon(tempImg);
//    jLabel2.setIcon(i);
//    
//    
//}  
//    if(rs.next()) 
//    {
//            byte[] base64String=Base64.encode(rs.getBytes("photograph"));
//		
// 
//		
// 
//		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(base64String));
//                
//                ImageIcon imm = new ImageIcon(imag);
//		jLabel2.setIcon(imm);
//
//        }
        
       
        }
        catch(Exception e)
         {
             System.out.print(e);
         }
        finally
        {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        uuname = new javax.swing.JTextField();
        ubc = new javax.swing.JTextField();
        ulname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ulang = new javax.swing.JTextField();
        uage = new javax.swing.JTextField();
        unic = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        uaddr = new javax.swing.JTextArea();
        uphn = new javax.swing.JTextField();
        udesig = new javax.swing.JComboBox<>();
        uftime = new javax.swing.JRadioButton();
        uptime = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ufname = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usid = new javax.swing.JRadioButton();
        usname = new javax.swing.JRadioButton();
        uid = new javax.swing.JTextField();
        uws = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1230, 685));
        getContentPane().setLayout(null);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(38, 44));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1230, 685));

        jTable1.setBackground(new java.awt.Color(204, 255, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "EID", "First name", "Last name", "Username", "Language", "NIC", "Address", "Title 8"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 108, 1130, 160);

        uuname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        uuname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uunameMouseClicked(evt);
            }
        });
        getContentPane().add(uuname);
        uuname.setBounds(200, 498, 260, 30);

        ubc.setEditable(false);
        ubc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ubc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ubcMouseClicked(evt);
            }
        });
        getContentPane().add(ubc);
        ubc.setBounds(200, 398, 100, 30);

        ulname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ulname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ulnameMouseClicked(evt);
            }
        });
        ulname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ulnameKeyTyped(evt);
            }
        });
        getContentPane().add(ulname);
        ulname.setBounds(340, 458, 120, 30);

        jLabel3.setBackground(new java.awt.Color(204, 204, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Change");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(384, 412, 70, 20);

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 288, 160, 150);

        ulang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ulang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ulangMouseClicked(evt);
            }
        });
        ulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ulangKeyTyped(evt);
            }
        });
        getContentPane().add(ulang);
        ulang.setBounds(200, 538, 260, 30);

        uage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        uage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uageKeyTyped(evt);
            }
        });
        getContentPane().add(uage);
        uage.setBounds(840, 498, 300, 30);

        unic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        unic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unicMouseClicked(evt);
            }
        });
        getContentPane().add(unic);
        unic.setBounds(840, 538, 300, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Status              :-");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(670, 458, 130, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Address            :-");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(670, 308, 120, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("NIC No             :-");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(670, 538, 130, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Age                   :-");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(670, 498, 140, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Phone no         :-");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(670, 368, 120, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Designation     :-");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(670, 408, 140, 40);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        uaddr.setColumns(20);
        uaddr.setRows(5);
        uaddr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane2.setViewportView(uaddr);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(840, 288, 300, 80);

        uphn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        uphn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uphnMouseClicked(evt);
            }
        });
        uphn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uphnKeyTyped(evt);
            }
        });
        getContentPane().add(uphn);
        uphn.setBounds(840, 378, 300, 30);

        udesig.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Cashier", "Driver", "Worker", "Stock Keeper", "Agency Manager", " " }));
        getContentPane().add(udesig);
        udesig.setBounds(840, 418, 300, 30);

        uftime.setText("Full Time");
        uftime.setOpaque(false);
        uftime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uftimeActionPerformed(evt);
            }
        });
        getContentPane().add(uftime);
        uftime.setBounds(870, 458, 110, 30);

        uptime.setText("Part Time");
        uptime.setOpaque(false);
        getContentPane().add(uptime);
        uptime.setBounds(1000, 458, 90, 30);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(410, 50));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search/Update/Delete Employee Details");

        time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 1214, 50);

        jButton2.setText("Update All");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(960, 598, 110, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Language         :-");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 528, 140, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Username         :-");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 498, 140, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Full name          :-   ");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 458, 130, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Basic  salary     :-");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 398, 130, 30);

        ufname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ufname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ufnameMouseClicked(evt);
            }
        });
        ufname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ufnameKeyTyped(evt);
            }
        });
        getContentPane().add(ufname);
        ufname.setBounds(200, 458, 120, 30);

        jButton3.setText("Back");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(100, 598, 110, 50);

        jButton4.setText("Clear");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(510, 598, 110, 50);

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        getContentPane().add(search);
        search.setBounds(80, 73, 130, 30);

        jLabel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        getContentPane().add(jLabel19);
        jLabel19.setBounds(20, 70, 390, 35);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Search");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 78, 50, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("by");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(220, 78, 20, 20);

        usid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usid.setText("Employee ID");
        usid.setOpaque(false);
        getContentPane().add(usid);
        usid.setBounds(240, 78, 110, 23);

        usname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usname.setText("Name");
        usname.setOpaque(false);
        getContentPane().add(usname);
        usname.setBounds(350, 78, 80, 23);

        uid.setEditable(false);
        uid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        uid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uidMouseClicked(evt);
            }
        });
        getContentPane().add(uid);
        uid.setBounds(200, 318, 100, 30);

        uws.setEditable(false);
        uws.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        uws.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uwsMouseClicked(evt);
            }
        });
        getContentPane().add(uws);
        uws.setBounds(200, 358, 100, 30);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Employee ID    :-");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(30, 318, 130, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Working since  :-");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(30, 358, 130, 30);

        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(20, 278, 1130, 310);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ubcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubcMouseClicked
        
    }//GEN-LAST:event_ubcMouseClicked

    private void ulnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ulnameMouseClicked
        ulname.setText("");
    }//GEN-LAST:event_ulnameMouseClicked

    private void ulangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ulangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ulangMouseClicked

    private void unicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unicMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_unicMouseClicked

    private void uphnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uphnMouseClicked
        
    }//GEN-LAST:event_uphnMouseClicked

    private void uftimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uftimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uftimeActionPerformed

    private void ufnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ufnameMouseClicked
        // TODO add your handling code here:
        ufname.setText("");
    }//GEN-LAST:event_ufnameMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try
        {
        JFileChooser filechooser = new JFileChooser();
         filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
         filechooser.addChoosableFileFilter(filter);
         int result = filechooser.showSaveDialog(null);
         
         
         if(result == JFileChooser.APPROVE_OPTION)
         {
             File selectedfile = filechooser.getSelectedFile();
             String path = selectedfile.getAbsolutePath();
             jLabel2.setIcon(ResizeImage(path));
             s = path;
             hada = true;
             
         }
         else if(result == JFileChooser.CANCEL_OPTION)
         {
             System.out.println("no data");
             hada = false;
         }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
         
         
        
        
    }//GEN-LAST:event_jLabel3MouseClicked
   
    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        char ch = evt.getKeyChar();
        String key = search.getText();
        String by=null;
//        System.out.println(key+ch);
        
        int p = search.getCaretPosition();
        if(usid.isSelected())
        {
            by = "id";
        }
        else if(usname.isSelected())
        {
            by = "name";
        }
        
        if(me.isAlph(ch))
        {
            
            
            tableload(me.addLetter(ch, p-1,key),by);
        }
        else
        {
            tableload(key,by);
        }
        
    }//GEN-LAST:event_searchKeyTyped

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        
    }//GEN-LAST:event_searchKeyPressed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
    }//GEN-LAST:event_searchActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        int row = jTable1.getSelectedRow();
            
        //set image to the label
        try {
            
            String id = jTable1.getValueAt(row,0).toString();
            //loading basic salary
//            String desig = jTable1.getValueAt(row,9).toString();
//            String sq = "SELECT basic_salary FROM usertype where user_type = '"+desig+"'";
            
            
            ///////////////////////////////////////////////////
            String query = "select photograph from employee where employee_id='"+id+"'";
            pst3=conn.prepareStatement(query);
            rs3=pst3.executeQuery();
            
            ba=rs3.getBytes("photograph");
            person_image=ba;
            
            
            
            
            
            
            ///////////////////////////////////////////////////
            
            
            
            
            
            
            
            
            
            String selectQuery = "SELECT * FROM Employee where employee_id = '"+id+"'";
            
            PreparedStatement st = conn.prepareStatement(selectQuery);
            rs1 = st.executeQuery();
            
            ImageIcon i = new ImageIcon((rs1.getBytes("photograph")));
            Image ii = i.getImage();
            Image newImage = ii.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImage);
            
            jLabel2.setIcon((image));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        finally
        {
            try {
                pst3.close();
                rs3.close();
                rs1.close();
            } catch (SQLException ex) {
                Logger.getLogger(updateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //set table values to fields
        uid.setText(jTable1.getValueAt(row,0).toString());
        ufname.setText(jTable1.getValueAt(row,1).toString());
        ulname.setText(jTable1.getValueAt(row,2).toString());
        uuname.setText(jTable1.getValueAt(row,3).toString());
        ulang.setText(jTable1.getValueAt(row,4).toString());
        uage.setText(jTable1.getValueAt(row,5).toString());
        unic.setText(jTable1.getValueAt(row,6).toString());
        uaddr.setText(jTable1.getValueAt(row,7).toString());
        uphn.setText("0"+(jTable1.getValueAt(row,8).toString()));
        udesig.setSelectedItem(jTable1.getValueAt(row,9));
        uws.setText(jTable1.getValueAt(row,11).toString());
        
        fname = jTable1.getValueAt(row,1).toString();
        lname = (jTable1.getValueAt(row,2).toString());
        uname = (jTable1.getValueAt(row,3).toString());
        language = (jTable1.getValueAt(row,4).toString());
        age = (jTable1.getValueAt(row,5).toString());
        nic = (jTable1.getValueAt(row,6).toString());
        addr = (jTable1.getValueAt(row,7).toString()); 
        phn = (jTable1.getValueAt(row,8).toString());
        desig = (jTable1.getValueAt(row,9).toString());
        
        
        
        
        if((jTable1.getValueAt(row,10).toString()).equals("Full Time"))
        {
            
            uftime.setSelected(true);
            status = "Full Time";
        }
        else if((jTable1.getValueAt(row,10).toString()).equals("Part Time"))
        {
            uptime.setSelected(true);
            status = "Part Time";
        }
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String cfname=ufname.getText(),r1=""; 
        String clname=ulname.getText(),r2="";
        String cuname=uuname.getText(),r3="";
        String clang = ulang.getText(),r4="";
        String cage= uage.getText(),r5="";
        String cnic=unic.getText(),r6="";
        String caddr=uaddr.getText(),r7="";
        String cphn=uphn.getText(),r8="";
        String cdesig = udesig.getSelectedItem().toString(),r9="",r10="";
        String cstatus="";
        if(uftime.isSelected())
        {
            cstatus = "Full Time";
        }
        else if(uptime.isSelected())
        {
            cstatus ="Part Time";
        }

        if(!fname.equals(cfname))
        {
            r1="First name :- "+fname+" to "+cfname;
        }
        if(!lname.equals(clname))
        {
            r2="Last name :- "+lname+" to "+clname;
        }
        if(!uname.equals(cuname))
        {
            r3 ="Username :- "+ uname+" to "+cuname;
        }
        if(!language.equals(clang))
        {
            r4="Language :- "+ulang+" to "+clang;
        }
        if(!age.equals(cage))
        {
            r5="Age :- "+age+" to "+cage;
        }
        if(!nic.equals(cnic))
        {
            r6="NIC no :- "+nic+" to "+cnic;
        }
        if(!addr.equals(caddr))
        {
            r7="Address :- "+addr+" to "+caddr;
        }
        if(!phn.equals(cphn))
        {
            r8="Phone no :- "+phn+" to "+cphn;
        }
        if(!desig.equals(cdesig))
        {
            r9="Designation :- "+desig+" to "+cdesig;
        }
        if(!status.equals(cstatus))
        {
            r10="Status :- "+status+" to "+cstatus;
        }
            if(uid.getText().equals("")||ufname.getText().equals("")||ulname.getText().equals("")||uuname.getText().equals("")||ulang.getText().equals("")||uage.getText().equals("")|| unic.getText().equals("")|| uaddr.getText().equals("")||uphn.getText().equals("")||udesig.getSelectedItem().toString().equals(""))
            {
                
                JOptionPane.showMessageDialog(null,"All the fields are required (except the optional fields)");
            }
            else
            {
        if(me.nicCheck(unic.getText()))
        {
                if(me.phnCheck(uphn.getText()))
                {
        int x = JOptionPane.showConfirmDialog(null,"Are you sure want to update following changes? \n"+r1+"\n"+r2+"\n"+r3+"\n"+r4+"\n"+r5+"\n"+r6+"\n"+r7+"\n"+r8+"\n"+r9+"\n"+r10);

        

        try
        {   
            System.out.println(hada);
            
            
            String eid = uid.getText();
            String sql1 ="UPDATE Employee SET photograph = ? WHERE employee_id = '"+eid+"'";
            
            if(hada==true)
                {
            
            
            InputStream is = new FileInputStream(new File(s));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];

            for(int readNum;(readNum=is.read(buf))!= -1;)
            {

                bos.write(buf,0,readNum);

            }
            person_image = bos.toByteArray();
                }

            //Updating Values
            
            
            if(x==0)
            {

                String sql = "UPDATE Employee SET first_name = '"+cfname+"',last_name = '"+clname+"',username = '"+cuname+"',language = '"+clang+"',age = '"+cage+"',nic_no = '"+cnic+"',address = '"+caddr+"',phone_no = '"+cphn+"',designation = '"+cdesig+"',status = '"+cstatus+"' WHERE employee_id = '"+eid+"'";

                pst = conn.prepareStatement(sql);
                pst.executeUpdate();

                System.out.print(person_image);

                               if(hada=true)
                               {
                                    pst1 = conn.prepareStatement(sql1);
                                    pst1.setBytes(1, person_image);
                                    pst1.executeUpdate();
                                    JOptionPane.showMessageDialog(null,"Successfully Updated!");
                                    tableload();
                               }   
            }

        }catch (Exception e)
        {
            System.out.println(e);

        }finally
        {
            try {
                
                pst.close();
                pst1.close();
            } catch (Exception e) {
            }
        }
                }else
                {
                    JOptionPane.showMessageDialog(null,"Invalid phone number");
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Invalid NIC");
        }
            }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void uidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_uidMouseClicked

    private void uwsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uwsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_uwsMouseClicked

    private void uunameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uunameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_uunameMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
           
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ufnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ufnameKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Numbers not allowed");
        }   
    }//GEN-LAST:event_ufnameKeyTyped

    private void ulnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ulnameKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Numbers not allowed");
        }   
    }//GEN-LAST:event_ulnameKeyTyped

    private void ulangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ulangKeyTyped
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Numbers not allowed");
        }   
    }//GEN-LAST:event_ulangKeyTyped

    private void uphnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uphnKeyTyped
                char c = evt.getKeyChar();
        
        if(Character.isLetter(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Only numbers are allowed");
        }
        String check = uphn.getText()+c;
        
        if(check.length()>10)
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Phone number must contain only 10 digits");
        }
    }//GEN-LAST:event_uphnKeyTyped

    private void uageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uageKeyTyped
                char c = evt.getKeyChar();
        
        if(Character.isLetter(c))
        {
            evt.consume();
         JOptionPane.showMessageDialog(null, "Only numbers are allowed");
        }
    }//GEN-LAST:event_uageKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        ufname.setText(""); 
//        ulname.setText("");
//        uuname.setText("");
//        ulang.setText("");
//        uage.setText("");
//        unic.setText("");
//        uaddr.setText("");
//        uphn.setText("");
//        uftime.setSelected(true);

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField search;
    private javax.swing.JLabel time;
    private javax.swing.JTextArea uaddr;
    private javax.swing.JTextField uage;
    private javax.swing.JTextField ubc;
    private javax.swing.JComboBox<String> udesig;
    private javax.swing.JTextField ufname;
    private javax.swing.JRadioButton uftime;
    private javax.swing.JTextField uid;
    private javax.swing.JTextField ulang;
    private javax.swing.JTextField ulname;
    private javax.swing.JTextField unic;
    private javax.swing.JTextField uphn;
    private javax.swing.JRadioButton uptime;
    private javax.swing.JRadioButton usid;
    private javax.swing.JRadioButton usname;
    private javax.swing.JTextField uuname;
    private javax.swing.JTextField uws;
    // End of variables declaration//GEN-END:variables
}
