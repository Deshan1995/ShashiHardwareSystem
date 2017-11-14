/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;

/**
 *
 * @author S V
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Property extends javax.swing.JInternalFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement ps = null;
Statement s = null;
    /**
     * Creates new form Property
     */
    public Property() {
        initComponents();
        conn=javaconnect.ConnecrDb();
        tableUpdate();
        calendar();
        showtime();
        auto();
    }
    public void tableUpdate(){
           try{
            String sql="Select * from Property ";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            property.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           
            finally{
             try{
                ps.close();
                rs.close();
             }
             catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
             }
            }
    }
    
    public void tableUpdate1(){
           try{
               
            String sql="Select * from Property_History";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            property_history.setModel(DbUtils.resultSetToTableModel(rs));
            
            txt_propertyId.setText("");
            txt_location.setText("");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           
           finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public void clear(){
           try{
                txt_propertyId.setText("");
                txt_perches.setText("");
                txt_value.setText("");
                ((JTextField)date.getDateEditor().getUiComponent()).setText("");
                txt_location.setText("");
                
                txt_propertyId.setEditable(true);
                txt_location.setEditable(true);
                
                auto();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           tableUpdate();
    }
    
    int month,day,year,hour,minute,second;
     
     public void calendar()
    { 
        Calendar cal=new GregorianCalendar();
        year= cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
       // lbl_date.setText(year+"/"+month+"/"+day);
    
    }
     
     public void showtime(){
        new Timer(0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar cal = new GregorianCalendar();
                hour = cal.get(Calendar.HOUR);
                minute = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);
             //   lbl_time.setText(hour+":"+minute+":"+second); 
            }
        }).start();
                
    }
     
     public void saveHistory(){
           try{
            String sql="Select count(Property_Id) AS count from Property_History where Property_Id Like '%"+txt_propertyId.getText()+"%' ";
       
            ps=conn.prepareStatement(sql);
            
            rs=ps.executeQuery();
            
            if(rs.next())
            {
		String sql1="Insert into Property_History(Valuation_No,Property_Id,Location,Perches,Valuation_Date,Property_Value) values(?,?,?,?,?,?)";
                ps=conn.prepareStatement(sql1);
                ps.setString(1,Integer.toString(Integer.parseInt(rs.getString("count"))+1));
                ps.setString(2,txt_propertyId.getText());
                ps.setString(3,txt_location.getText());
                ps.setString(4,txt_perches.getText());
                ps.setString(5,((JTextField)date.getDateEditor().getUiComponent()).getText());
                ps.setString(6,txt_value.getText());

                ps.execute();

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
     
     public void auto(){
        try{

            s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(Property_Id) AS propertyid FROM Property");
            String propertyid;
            while(rs.next()){
                propertyid=rs.getString("propertyid");
                String ino[]=propertyid.split("P");
                
                int no=Integer.parseInt(ino[1]);
                no=no+1;
              txt_propertyId.setText("P"+no);
            }
        }
            catch (Exception e) {
            System.out.println(e);
        }
    }
     
     public void saveHistory1(){
        try{
            String sql="Insert into Property_History(Valuation_No,Property_Id,Location,Perches,Valuation_Date,Property_Value) values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"1");
            ps.setString(2,txt_propertyId.getText());
            ps.setString(3,txt_location.getText());
            ps.setString(4,txt_perches.getText());
            ps.setString(5,((JTextField)date.getDateEditor().getUiComponent()).getText());
            ps.setString(6,txt_value.getText());

                ps.execute();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
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

        History = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        property_history = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txt_hisPropertyId = new javax.swing.JTextField();
        txt_hisLocation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_historySearch = new javax.swing.JButton();
        btn_hisRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        property = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_perches = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_location = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_propertyId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_value = new javax.swing.JTextField();
        date = new com.toedter.calendar.JDateChooser();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_history = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        property_history.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Valuation No", "Property Id", "Perches", "Valuation Date", "Property Value", "Location"
            }
        ));
        jScrollPane2.setViewportView(property_history);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        txt_hisPropertyId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_hisPropertyId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txt_hisLocation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_hisLocation.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Property Id");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Location");

        btn_historySearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_historySearch.setText("Search");
        btn_historySearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historySearchActionPerformed(evt);
            }
        });

        btn_hisRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_hisRefresh.setText("Refresh");
        btn_hisRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hisRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_hisPropertyId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_hisLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btn_historySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btn_hisRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_hisPropertyId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_hisLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_historySearch)
                    .addComponent(btn_hisRefresh))
                .addContainerGap())
        );

        javax.swing.GroupLayout HistoryLayout = new javax.swing.GroupLayout(History.getContentPane());
        History.getContentPane().setLayout(HistoryLayout);
        HistoryLayout.setHorizontalGroup(
            HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryLayout.createSequentialGroup()
                .addGroup(HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HistoryLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HistoryLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        HistoryLayout.setVerticalGroup(
            HistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1000, 705));

        jPanel1.setPreferredSize(new java.awt.Dimension(962, 633));

        property.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        property.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Property Id", "Perches", "Purchased Value", "Current Value", "Location"
            }
        ));
        property.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propertyMouseClicked(evt);
            }
        });
        property.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                propertyKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(property);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Property Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        txt_perches.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_perches.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_perches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_perchesKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_perchesKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Property Id");

        txt_location.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_location.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_location.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_locationKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Perches");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Location");

        txt_propertyId.setEditable(false);
        txt_propertyId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_propertyId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Valuation Date");

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Property Value");

        txt_value.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_value.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_value.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_valueKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_valueKeyTyped(evt);
            }
        });

        date.setDateFormatString("yyyy-MM-dd");
        date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_perches, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_propertyId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_location, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_value, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_propertyId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_perches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search)
                    .addComponent(btn_refresh))
                .addGap(20, 20, 20))
        );

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_history.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_history.setText("History");
        btn_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historyActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_history, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete)
                    .addComponent(btn_clear)
                    .addComponent(btn_history))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(271, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("   Property");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        
        
        try{
            String sql="Insert into Property(Property_Id,Perches,Valuation_Date,Property_Value,Location) values(?,?,?,?,?)";
           
            ps=conn.prepareStatement(sql);
            
            ps.setString(1,txt_propertyId.getText());
            ps.setString(2,txt_perches.getText());
            ps.setString(3,((JTextField)date.getDateEditor().getUiComponent()).getText());
            ps.setString(4,txt_value.getText());
            ps.setString(5,txt_location.getText());
          
            if(validation()){
                ps.execute();
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        saveHistory();
          tableUpdate();
          clear();
          
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
               clear();
                
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        
    int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Update","Update",JOptionPane.YES_NO_OPTION);
    if(d==0)
    {
        
        try{
            String val1 = txt_propertyId.getText();
            String val2 = txt_perches.getText();
            String val3 = ((JTextField)date.getDateEditor().getUiComponent()).getText();
            String val4 = txt_value.getText();
            String val5 = txt_location.getText();
          
            String sql = "Update Property set Property_Id='"+val1+"',Perches='"+val2+"',Valuation_Date='"+val3+"',Property_Value='"+val4+"',Location='"+val5+"' where Property_Id=?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_propertyId.getText());
            ps.execute();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        saveHistory();
          tableUpdate();
          clear();
          
    }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
    int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete","Delete",JOptionPane.YES_NO_OPTION);
    if(d==0)
    {
        String sql = "delete from Property where Property_Id=?";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_propertyId.getText());
            ps.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
          tableUpdate();
          clear();
    }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        
          String vDate = ((JTextField)date.getDateEditor().getUiComponent()).getText();
        
        try{
       
       String sql="Select * from Property where Valuation_Date Like '%"+vDate+"%' AND Location Like '%"+txt_location.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
          
            rs=ps.executeQuery();
            property.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void propertyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propertyMouseClicked
        DefaultTableModel model =  (DefaultTableModel) property.getModel();
        int selectedRowIndex = property.getSelectedRow();
        
        txt_propertyId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txt_perches.setText(model.getValueAt(selectedRowIndex, 1).toString());
        ((JTextField)date.getDateEditor().getUiComponent()).setText(model.getValueAt(selectedRowIndex, 2).toString());
        txt_value.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_location.setText(model.getValueAt(selectedRowIndex, 4).toString());
        
        txt_propertyId.setEditable(false);
        txt_location.setEditable(false);
    }//GEN-LAST:event_propertyMouseClicked

    private void propertyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_propertyKeyPressed
        DefaultTableModel model =  (DefaultTableModel) property.getModel();
        int selectedRowIndex = property.getSelectedRow();
        
        txt_propertyId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txt_perches.setText(model.getValueAt(selectedRowIndex, 1).toString());
        ((JTextField)date.getDateEditor().getUiComponent()).setText(model.getValueAt(selectedRowIndex, 2).toString());
        txt_value.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_location.setText(model.getValueAt(selectedRowIndex, 4).toString());
        
        txt_propertyId.setEditable(false);
        txt_location.setEditable(false);
    }//GEN-LAST:event_propertyKeyPressed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        tableUpdate();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historyActionPerformed
        History.setVisible(true);
        
        try{
            String sql="Select * from Property_History";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            property_history.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_historyActionPerformed

    private void btn_historySearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historySearchActionPerformed
        try{
       
       String sql="Select * from Property_History where Property_Id Like '%"+txt_hisPropertyId.getText()+"%' AND Location Like '%"+txt_hisLocation.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
          
            rs=ps.executeQuery();
            property_history.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_historySearchActionPerformed

    private void btn_hisRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hisRefreshActionPerformed
        tableUpdate1();
        
        txt_propertyId.setText("");
        txt_location.setText("");
    }//GEN-LAST:event_btn_hisRefreshActionPerformed

    private void txt_perchesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_perchesKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_perchesKeyTyped

    private void txt_valueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valueKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_valueKeyTyped

    private void txt_locationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_locationKeyTyped
        char c=evt.getKeyChar();
        if(Character.isDigit(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only characters are allowed");
        }
    }//GEN-LAST:event_txt_locationKeyTyped

    private void txt_perchesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_perchesKeyPressed
        if(evt.getKeyCode()==10){
            if(txt_perches.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter the no of perches", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                date.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_perchesKeyPressed

    private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
       if(evt.getKeyCode()==10){
            if(((JTextField)date.getDateEditor().getUiComponent()).getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter the date", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                txt_value.grabFocus();    
            }
        }
    }//GEN-LAST:event_dateKeyPressed

    private void txt_valueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valueKeyPressed
        if(evt.getKeyCode()==10){
            if(txt_value.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter the property value", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                txt_location.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_valueKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog History;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_hisRefresh;
    private javax.swing.JButton btn_history;
    private javax.swing.JButton btn_historySearch;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable property;
    private javax.swing.JTable property_history;
    private javax.swing.JTextField txt_hisLocation;
    private javax.swing.JTextField txt_hisPropertyId;
    private javax.swing.JTextField txt_location;
    private javax.swing.JTextField txt_perches;
    private javax.swing.JTextField txt_propertyId;
    private javax.swing.JTextField txt_value;
    // End of variables declaration//GEN-END:variables

private boolean validation() {
    boolean bool=true ;   
    try {
            
            if(txt_propertyId.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a property id", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_perches.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter the no of perches", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(((JTextField)date.getDateEditor().getUiComponent()).getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter the valuation date", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_value.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter the property value ", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_location.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a location", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
             }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return bool;
}
}
