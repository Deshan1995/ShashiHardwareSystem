/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;



import DBconnection.DBconnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
//import java.sql.SQLException;
import org.sqlite.SQLiteException;
/**
 *
 * @author User
 */

public class Depreciation extends javax.swing.JInternalFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    Statement s = null;
    String temp1;
    String val = null;
    String id = null;
    String date = null;
    String depre_method = null;
    double cost=0,rate=0,annual_depre=0;
    String depreDate = null,depreId=null;
    String temp = null;
    /**
     * Creates new form Depreciation
     */
    public Depreciation() {
        initComponents();
        conn=DBconnect.connectDb();
        tableUpdate();
        calendar();
        auto();
        Notification();
       //depreciation();
    }
    public void tableUpdate(){
           
        try{
            String sql="Select * from Depreciation";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            Depreciation.setModel(DbUtils.resultSetToTableModel(rs));
            
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
            
            String sql="Select * from Depreciation where Asset_Id Like '%"+txt_assetId.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            Depreciation.setModel(DbUtils.resultSetToTableModel(rs));
             
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
                txt_assetId.setText("");
                cmb_category.setSelectedIndex(0);
                txt_Description.setText("");
                txt_cost.setText("");
                txt_salvageValue.setText("");
                cmb_depreMethod.setSelectedIndex(0);
                txt_Rate.setText("");
                txt_life.setText("");
                txt_depreValue.setText("");
                txt_depreExpence.setText("");
                txt_netBookValue.setText("");
                ((JTextField)purchase_date.getDateEditor().getUiComponent()).setText("");
                
                txt_depreId.setEditable(true);
                txt_assetId.setEditable(true);
                cmb_category.setEnabled(true);
                txt_Description.setEditable(true);
                purchase_date.setEnabled(true);
                txt_cost.setEditable(true);
                txt_Rate.setEditable(true);
                txt_life.setEditable(true);
                
                auto();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        tableUpdate();
        
    }
     int month,day,year,hour,minute,second;
     int day1=0,month1=0,year1=0;
    public void calendar()
    { 
        Calendar cal=new GregorianCalendar();
//        year= cal.get(Calendar.YEAR);
//        month=cal.get(Calendar.MONTH)+1;
//        day=cal.get(Calendar.DAY_OF_MONTH);
       // lbl_date.setText(year+"/"+month+"/"+day);
       year=2017;
    month=9;
       day=2; 
    
    }
    
    public void showtime(){
        new Timer(0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar cal = new GregorianCalendar();
                hour = cal.get(Calendar.HOUR);
                minute = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);
               // lbl_time.setText(hour+":"+minute+":"+second); 
            }
        }).start();        
    }
    
    public void mouseClicked(String mc){
        try{
       
            String sql="Select * from Asset where Asset_Id Like '%"+mc+"%' ";
       
            ps=conn.prepareStatement(sql);
            
            rs=ps.executeQuery();
            
            if(rs.next())
            {
               cmb_category.setSelectedItem(rs.getString("Asset_Category"));
               txt_cost.setText(rs.getString("Purchased_cost"));
               ((JTextField)purchase_date.getDateEditor().getUiComponent()).setText(rs.getString("Purchased_Date"));
               txt_Rate.setText(rs.getString("Depreciation_Rate"));
               txt_life.setText(rs.getString("Depreciable_Life"));
               txt_Description.setText(rs.getString("Asset_Description"));
               
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
            rs = s.executeQuery("SELECT MAX(Depre_Id) AS depreid FROM Depreciation");
            String depreid;
            while(rs.next()){
                depreid=rs.getString("depreid");
                String ino[]=depreid.split("D");
                
                int no=Integer.parseInt(ino[1]);
                no=no+1;
              txt_depreId.setText("D"+no);
            }
        }
            catch (Exception e) {
            System.out.println(e);
        }
        
        finally{
            try{
                s.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public void depreciation(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("MM");

            java.util.Date d1 = null;
            int diff = 0;

	try{
	    String sql="Select a.Asset_Id,a.Purchased_Date,a.Purchased_Cost,a.Depreciation_Rate,d.Annual_Depreciation,d.Depreciation_Method from Asset a, Depreciation d where a.Asset_Id=d.Asset_Id ";
                    
            ps=conn.prepareStatement(sql); 
            rs=ps.executeQuery();
            
		while(rs.next()){
 
		id = rs.getString("Asset_Id");  
                date = rs.getString("Purchased_Date");
		cost = Double.parseDouble(rs.getString("Purchased_Cost"));
		rate = Double.parseDouble(rs.getString("Depreciation_Rate"));
                
                temp = rs.getString("Annual_Depreciation");
                if(!temp.isEmpty()){
                    annual_depre = Double.parseDouble(rs.getString("Annual_Depreciation"));
                }
                
		depre_method = rs.getString("Depreciation_Method");
 
		d1 = format.parse(date);
                String month1 = df.format(d1);
                diff = month - Integer.parseInt(month1);
                         
                       
		if(depre_method.equals("Straight Line")){ 
		 
                    val = Double.toString(cost-((annual_depre/12)*diff));
                                 
                }
		else if(depre_method.equals("Declining Balance")){
                 		
                    val= Double.toString(cost-(((cost*rate)/100.0)*diff));
		}

	 	String sql1 = "Update Depreciation set Net_Book_Value='"+val+"' where Asset_Id='"+id+"'";
           
            	ps=conn.prepareStatement(sql1);
                       
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
    
    public void Notification(){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;
            

	try{
	    String sql="Select Depre_Id,depre_date from Depreciation ";
            
            ps1=conn.prepareStatement(sql);
            
            rs=ps1.executeQuery();
               

		while(rs.next()){
                    depreId = rs.getString("Depre_Id");
                    depreDate = rs.getString("depre_date");

                    d1 = format.parse(depreDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
      
                    if((year==year1)&&(month==month1)&&(day<=day1)) {
                       // JOptionPane.showMessageDialog(rootPane, "The Asset is being depreciated in another "+(day1-day)+" days", "Notify", JOptionPane.WARNING_MESSAGE);
                        try{
                        String sql1="Insert into Notify(Depre_Id,Notification) values(?,?)";
           
                        ps=conn.prepareStatement(sql1);
            
                        ps.setString(1,depreId);
                        ps.setString(2,"The Asset is being depreciated in another "+(day1-day)+" days");

                            ps.execute();
            
            
            
                            }catch(Exception e1){
                 
                                if(e1.toString().contains("java.sql.SQLException: column Depre_Id is not unique") ){                   
                                    System.out.println(e1);
                                }else{
                                    //System.out.println(e1);
                                    JOptionPane.showMessageDialog(null,e1);
                                            
                                }                
                            }
                            
                    } 
                    
	}}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        finally{
            try{
                ps.close();
                ps1.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
}
    
    public void Update_Depredate(){
        String pDate=null,dId=null;
             int life=0;  
       
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dd = new SimpleDateFormat("dd");

        java.util.Date d1 = null;

	try{

                String sql1="Select a.Purchased_Date,a.Depreciable_Life,d.Depre_Id from Asset a,Depreciation d where a.Asset_Id=d.Asset_Id ";
            
                ps=conn.prepareStatement(sql1);
                rs=ps.executeQuery();

                while(rs.next()){
                   pDate = rs.getString("Purchased_Date");
		   dId = rs.getString("Depre_Id");
                   life = Integer.parseInt(rs.getString("Depreciable_Life"));
            
                    d1 = format.parse(pDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));


                    String val1 = (year1+life)+"-"+month1+"-"+day1;
                    
                    String sql = "Update Depreciation set depre_date='"+val1+"' where Depre_Id='"+dId+"' ";
            
                           
                    ps=conn.prepareStatement(sql);
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
 
    int no,no1;
    public void auto1(){
    
        ArrayList<String> list = new ArrayList<String>();
        try {
            String a = txt_depreId.getText();
            String ino[]=a.split("D");
            no1=Integer.parseInt(ino[1]);
            
            String sql = "SELECT Depre_Id as depreid FROM Depreciation";
            
            ps=conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("depreid"));
            }
    
            for(int i=0;i<=list.size()-1;i++){
	              
                String b = list.get(i);
                String ino1[]=b.split("D");
            
                no=Integer.parseInt(ino1[1]);

		if(no1+1<=no){

                    int j=no1+(i-1);
                    String id="D"+j;
                  
                    String sql1="Update Depreciation set Depre_Id='"+id+"' where Depre_Id='"+b+"'";
                    ps=conn.prepareStatement(sql1);
                    ps.execute();
                }
            }
        } catch (Exception e) {
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

        Asset = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        asset = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Depreciation = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_life = new javax.swing.JTextField();
        txt_depreExpence = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_Rate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmb_depreMethod = new javax.swing.JComboBox();
        txt_depreValue = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_netBookValue = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        purchase_date = new com.toedter.calendar.JDateChooser();
        btn_search = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txt_cost = new javax.swing.JTextField();
        cmb_category = new javax.swing.JComboBox<>();
        txt_assetId = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_salvageValue = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Description = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_depreId = new javax.swing.JTextField();
        btn_clear = new javax.swing.JButton();
        btn_search_depre = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        Asset.setSize(new java.awt.Dimension(850, 300));

        asset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        asset.setMaximumSize(new java.awt.Dimension(2147483647, 112));
        asset.setMinimumSize(new java.awt.Dimension(45, 112));
        asset.setPreferredSize(new java.awt.Dimension(225, 112));
        asset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(asset);

        javax.swing.GroupLayout AssetLayout = new javax.swing.GroupLayout(Asset.getContentPane());
        Asset.getContentPane().setLayout(AssetLayout);
        AssetLayout.setHorizontalGroup(
            AssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssetLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        AssetLayout.setVerticalGroup(
            AssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssetLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 705));

        jPanel2.setPreferredSize(new java.awt.Dimension(1123, 633));

        Depreciation.setModel(new javax.swing.table.DefaultTableModel(
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
                "Depre Id", "Asset Id", "Depreciation Method", "Depreciable Value", "Annual Depreciation", "Net Book Value"
            }
        ));
        Depreciation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DepreciationMouseClicked(evt);
            }
        });
        Depreciation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DepreciationKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(Depreciation);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Depreciation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        txt_life.setEditable(false);
        txt_life.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_life.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_life.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_lifeKeyTyped(evt);
            }
        });

        txt_depreExpence.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_depreExpence.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_depreExpence.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_depreExpenceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_depreExpenceKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Annual Depreciation Expence");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Depreciable Life");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Depreciation Method");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Depreciable Value");

        txt_Rate.setEditable(false);
        txt_Rate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Rate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RateKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Depreciation Rate");

        cmb_depreMethod.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmb_depreMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Select>", "Straight Line", "Declining Balance" }));
        cmb_depreMethod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cmb_depreMethod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmb_depreMethodKeyPressed(evt);
            }
        });

        txt_depreValue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_depreValue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_depreValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_depreValueKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_depreValueKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Net Book Value");

        txt_netBookValue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_netBookValue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_netBookValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_netBookValueKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Purchased Date");

        purchase_date.setDateFormatString("yyyy-MM-dd");
        purchase_date.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_netBookValue, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(txt_depreExpence, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_depreValue, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_depreMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(purchase_date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchase_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_depreMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_life, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_depreValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_depreExpence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_netBookValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_search.setText("Search asset");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assets", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Asset Id");

        txt_cost.setEditable(false);
        txt_cost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cost.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_cost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_costKeyTyped(evt);
            }
        });

        cmb_category.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmb_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer ", "Furniture", "Machinary", "Vehicle" }));
        cmb_category.setBorder(null);

        txt_assetId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_assetId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_assetId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_assetIdKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Purchase Cost");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Asset Category");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Salvage Value");

        txt_salvageValue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_salvageValue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_salvageValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_salvageValueKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_salvageValueKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Asset Description");

        txt_Description.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Description.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Depreciation Id");

        txt_depreId.setEditable(false);
        txt_depreId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_depreId.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(txt_salvageValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_category, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_assetId, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_depreId))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_depreId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_assetId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_salvageValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        btn_clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_search_depre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_search_depre.setText("Search depreciation");
        btn_search_depre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_depreActionPerformed(evt);
            }
        });

        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_search_depre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_refresh)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(48, 48, 48))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_search)
                            .addComponent(btn_search_depre)
                            .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete)
                    .addComponent(btn_update)
                    .addComponent(btn_insert)
                    .addComponent(btn_clear))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(271, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Depreciation");
        jLabel3.setPreferredSize(new java.awt.Dimension(34, 44));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        
        String temp = (String)cmb_depreMethod.getSelectedItem(); 
        
        try{
            String sql="Insert into Depreciation(Depre_Id,Asset_Id,Depreciation_Method,Depreciable_Value,Annual_Depreciation,Net_Book_Value) values(?,?,?,?,?,?)";
            
            ps=conn.prepareStatement(sql);
            
            ps.setString(1,txt_depreId.getText());
            ps.setString(2,txt_assetId.getText());
            ps.setString(3,temp);
            ps.setString(4,txt_depreValue.getText());
            ps.setString(5,txt_depreExpence.getText());
            ps.setString(6,txt_netBookValue.getText());

            if(validation()){
                ps.execute();
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        Update_Depredate();
        tableUpdate();
        clear();
          
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        
        int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Update","Update",JOptionPane.YES_NO_OPTION);
        if(d==0)
        {
        try{
            
            String val1 = txt_depreId.getText();
            String val2 = txt_assetId.getText();
            String val3 = (String)cmb_depreMethod.getSelectedItem();
            String val4 = txt_depreValue.getText();
            String val5 = txt_depreExpence.getText();
            String val6 = txt_netBookValue.getText();
            
          
            String sql = "Update Depreciation set Depre_Id='"+val1+"',Asset_Id='"+val2+"',Depreciation_Method='"+val3+"',Depreciable_Value='"+val4+"',Annual_Depreciation='"+val5+"',Net_Book_Value='"+val6+"' where Asset_Id=?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1,txt_assetId.getText());
            ps.execute();
                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
                ps.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        tableUpdate();
        clear();
        } 
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
        int d=JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete","Delete",JOptionPane.YES_NO_OPTION);
        if(d==0)
        {
            String sql = "delete from Depreciation where Depre_Id=?";
            try{
                ps=conn.prepareStatement(sql);
                ps.setString(1,txt_depreId.getText());
                ps.execute();

           }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
           }
        
            finally{
            try{
                ps.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
            auto1();
            tableUpdate();
            clear();
        }
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
             
        Asset.setVisible(true);  
             
        try{
            String sql="Select * from Asset where Asset_Id Like '%"+txt_assetId.getText()+"%' AND Asset_Description Like '%"+txt_Description.getText()+"%'";
       
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            asset.setModel(DbUtils.resultSetToTableModel(rs));
            
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

    private void DepreciationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DepreciationMouseClicked
        
        DefaultTableModel model =  (DefaultTableModel) Depreciation.getModel();
        int selectedRowIndex = Depreciation.getSelectedRow();
        
        txt_depreId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txt_assetId.setText(model.getValueAt(selectedRowIndex, 1).toString());
        cmb_depreMethod.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
        txt_depreValue.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_depreExpence.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txt_netBookValue.setText(model.getValueAt(selectedRowIndex, 5).toString());
        
        String mc = model.getValueAt(selectedRowIndex, 1).toString();
        mouseClicked(mc);
        
        txt_assetId.setEditable(false);
        cmb_category.setEnabled(false);
        txt_Description.setEditable(false);
        purchase_date.setEnabled(false);
        txt_cost.setEditable(false);
        txt_Rate.setEditable(false);
        txt_life.setEditable(false);
        
    }//GEN-LAST:event_DepreciationMouseClicked

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_salvageValueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_salvageValueKeyPressed
        
        if(evt.getKeyCode()==10){
            if(txt_salvageValue.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter Salvage Value", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                cmb_depreMethod.grabFocus();    
            }
        }
    }//GEN-LAST:event_txt_salvageValueKeyPressed

    private void txt_depreValueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depreValueKeyPressed
        
        temp1 = (String)cmb_depreMethod.getSelectedItem(); 
        
        if(evt.getKeyCode()==10){
            
            txt_depreExpence.grabFocus();
                
            if(temp1=="<Select>"){
                    
                JOptionPane.showConfirmDialog(rootPane, "Select a depreciation method", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(temp1=="Straight Line"){
                
                Double num1 = Double.parseDouble(txt_depreValue.getText());
                Double num2 = Double.parseDouble(txt_life.getText());
                String val = Double.toString(num1/num2);
                
                txt_depreExpence.setText(val);
            }
    
        }
    }//GEN-LAST:event_txt_depreValueKeyPressed

    private void txt_depreExpenceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depreExpenceKeyPressed
        
        if(evt.getKeyCode()==10){
            
            txt_netBookValue.grabFocus();

            String date1 =((JTextField)purchase_date.getDateEditor().getUiComponent()).getText();
		
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df = new SimpleDateFormat("yyyy");

            java.util.Date d1 = null;
            int diff = 0;
             
            try {
                d1 = format.parse(date1);
                String year1 = df.format(d1);
                diff = year - Integer.parseInt(year1);
            } 
            catch (Exception e) {
		JOptionPane.showMessageDialog(null,e);
            } 
              
            if(temp1=="Straight Line"){ 
                    
                Double num1 = Double.parseDouble(txt_cost.getText());
                Double num2 = Double.parseDouble(txt_depreExpence.getText());
                String val = Double.toString(num1-((num2/12)*diff));
               
                txt_netBookValue.setText(val);
            }
            else{
                    
                Double num = Double.parseDouble(txt_cost.getText());
                Double num3 = Double.parseDouble(txt_Rate.getText());
                Double val = (num*num3)/100.0;
                String val1= Double.toString(num-(val*diff));
                    
                txt_netBookValue.setText(val1);
            }
        }
    }//GEN-LAST:event_txt_depreExpenceKeyPressed

    private void cmb_depreMethodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_depreMethodKeyPressed
        
        if(evt.getKeyCode()==10){

                txt_depreValue.grabFocus();
                
                Double num1 = Double.parseDouble(txt_salvageValue.getText());
                Double num2 = Double.parseDouble(txt_cost.getText());
                String val = Double.toString(num2-num1);
                txt_depreValue.setText(val);
        }
    }//GEN-LAST:event_cmb_depreMethodKeyPressed

    private void txt_assetIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_assetIdKeyPressed
        if(evt.getKeyCode()==10){
            
                txt_Description.grabFocus();

        }
    }//GEN-LAST:event_txt_assetIdKeyPressed

    private void assetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assetMouseClicked
        Asset.setVisible(false);
        
        DefaultTableModel model =  (DefaultTableModel) asset.getModel();
        int selectedRowIndex = asset.getSelectedRow();
        
        txt_assetId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        cmb_category.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        txt_Description.setText(model.getValueAt(selectedRowIndex, 2).toString());
        ((JTextField)purchase_date.getDateEditor().getUiComponent()).setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_cost.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txt_Rate.setText(model.getValueAt(selectedRowIndex, 5).toString());
        txt_life.setText(model.getValueAt(selectedRowIndex, 6).toString());
        
        txt_assetId.setEditable(false);
        cmb_category.setEnabled(false);
        txt_Description.setEditable(false);
        purchase_date.setEnabled(false);
        txt_cost.setEditable(false);
        txt_Rate.setEditable(false);
        txt_life.setEditable(false); 
    }//GEN-LAST:event_assetMouseClicked

    private void btn_search_depreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_depreActionPerformed
        tableUpdate1();
    }//GEN-LAST:event_btn_search_depreActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
       tableUpdate();
 
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_costKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_costKeyTyped

    private void txt_salvageValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_salvageValueKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_salvageValueKeyTyped

    private void txt_depreValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depreValueKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_depreValueKeyTyped

    private void txt_depreExpenceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depreExpenceKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_depreExpenceKeyTyped

    private void txt_netBookValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_netBookValueKeyTyped
       char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_netBookValueKeyTyped

    private void DepreciationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepreciationKeyPressed
       DefaultTableModel model =  (DefaultTableModel) Depreciation.getModel();
        int selectedRowIndex = Depreciation.getSelectedRow();
        
        txt_depreId.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txt_assetId.setText(model.getValueAt(selectedRowIndex, 1).toString());
        cmb_depreMethod.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
        txt_depreValue.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_depreExpence.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txt_netBookValue.setText(model.getValueAt(selectedRowIndex, 5).toString());
        
        String mc = model.getValueAt(selectedRowIndex, 1).toString();
        mouseClicked(mc);
        
        txt_assetId.setEditable(false);
        cmb_category.setEnabled(false);
        txt_Description.setEditable(false);
        purchase_date.setEnabled(false);
        txt_cost.setEditable(false);
        txt_Rate.setEditable(false);
        txt_life.setEditable(false);
    }//GEN-LAST:event_DepreciationKeyPressed

    private void txt_RateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RateKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_RateKeyTyped

    private void txt_lifeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lifeKeyTyped
        char c=evt.getKeyChar();
        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Only numbers are allowed");
        }
    }//GEN-LAST:event_txt_lifeKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        String report = ".\\asset2.jrxml";
                        JasperDesign jd = JRXmlLoader.load(report);
                        String sql1 = "select * from Asset a ,Depreciation d where a.Asset_Id=d.Asset_Id";
                        JRDesignQuery n = new JRDesignQuery();
                        n.setText(sql1);
                        jd.setQuery(n);
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                        JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Asset;
    private javax.swing.JTable Depreciation;
    private javax.swing.JTable asset;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_search_depre;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_category;
    private javax.swing.JComboBox cmb_depreMethod;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser purchase_date;
    private javax.swing.JTextField txt_Description;
    private javax.swing.JTextField txt_Rate;
    private javax.swing.JTextField txt_assetId;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_depreExpence;
    private javax.swing.JTextField txt_depreId;
    private javax.swing.JTextField txt_depreValue;
    private javax.swing.JTextField txt_life;
    private javax.swing.JTextField txt_netBookValue;
    private javax.swing.JTextField txt_salvageValue;
    // End of variables declaration//GEN-END:variables

private boolean validation() {
    boolean bool=true ;   
    try {
            
            if(txt_assetId.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter an asset id", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(cmb_depreMethod.getSelectedItem()=="<Select>")
            {
                JOptionPane.showConfirmDialog(rootPane, "Select a depreciation method", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            else if(txt_depreValue.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a depreciation value", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
            
            
            else if(txt_netBookValue.getText().isEmpty())
            {
                JOptionPane.showConfirmDialog(rootPane, "Enter a net book value", "Error", JOptionPane.ERROR_MESSAGE);
                bool =false;
            }
             }
        catch (Exception e) {
            System.out.println(e);
        }
        return bool;
    }
}
