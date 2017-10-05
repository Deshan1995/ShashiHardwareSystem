
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;

import DBconnection.DBconnect;
import java.awt.Image;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.table.TableModel;

/**
 *
 * @author udara
 */
public class addloans extends javax.swing.JInternalFrame {
    
    Connection conn=null;
    ResultSet rs,rs1=null;
    PreparedStatement pst,ps,ps1=null;
    Statement s=null;
    String date=null;
    // private String dateInString;
    /**
     * Creates new form addloans
     */
    public addloans() {
        initComponents();
        
        conn = DBconnect.connectDb();
        autoincrement();
        tableload();
        
        calendar();
        
        Update_loandate();
        notification();
        
    
       }
    int day=0,month=0,year=0,day1=0,month1=0,year1=0,day2=0,month2=0,year2=0,diff=0;
     
    public void calendar()
    { 
        Calendar cal=new GregorianCalendar();
        year= cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
       // day=cal.get(Calendar.DAY_OF_MONTH);
       day=4;
    
    }
   
    public void Update_loandate(){
        String loanid=null, sDate=null,eDate=null;       
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dy = new SimpleDateFormat("yyyy");
        SimpleDateFormat dm = new SimpleDateFormat("MM");
        SimpleDateFormat dd = new SimpleDateFormat("dd");

        java.util.Date d1 = null;
        java.util.Date d2 = null;

	try{

                String sql1="Select Loan_ID,Start_Date,End_Date from Loan ";
            
                ps=conn.prepareStatement(sql1);
                rs=ps.executeQuery();

                while(rs.next()){
                    loanid = rs.getString("Loan_ID");
                    sDate = rs.getString("Start_Date");
                    eDate = rs.getString("End_Date");
            
                    d1 = format.parse(sDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));

		    d2 = format.parse(eDate);
                    year2 = Integer.parseInt(dy.format(d2));
                    month2 = Integer.parseInt(dm.format(d2));
                    day2= Integer.parseInt(dd.format(d2));

//      		while((year1==year2)&&(month1==month2)&&(day1==day2)){
//ps.close();
			String val1 = year+"-"+month+"-"+day1;
                    
                        String sql = "Update Loan set loandate='"+val1+"' where Loan_ID='"+loanid+"' ";
            
                           
                        ps=conn.prepareStatement(sql);
                        //Statement stm = conn.createStatement();
                        ps.execute();
                        //stm.execute(sql);

//		}
                }          
	}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
                try{
                ps.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
    }
    
    public void notification(){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;

	try{
	    String sql="Select loandate from Loan";
            String sql1="select Loan_ID from Loan";
            
            ps=conn.prepareStatement(sql);
            ps1=conn.prepareStatement(sql1);
            
            rs=ps.executeQuery();
            rs1=ps1.executeQuery();
               

		while(rs.next()){
  
                    date = rs.getString("loandate");
                    String id=rs1.getString("Loan_ID");

                    d1 = format.parse(date);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
      
                    //diff=day1-3;
                    
                    if((year==year1)&&(month==month1)&&(day<day1)) {
                        try{
                        String sql2="Insert into Notifyl(Loan_ID,Notification) values(?,?)";
           
                        ps=conn.prepareStatement(sql2);
            
                        ps.setString(1,"L1");
                        ps.setString(2,"You have to pay "+id+" Installment within "+(day1-day)+" days");

                            ps.execute();
            
            
            
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,e);
                            }
                        finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
                    } 
                    else {
                    }
	}
    }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
    }
    
    
        public void autoincrement(){

    ArrayList<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT Loan_Id as loanid FROM Loan";
            
            pst=conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
            while(rs.next())
            {
                list.add(rs.getString("loanid"));
            }
            
            
           String a = list.get(list.size()-1);
           String ino[]=a.split("ln");
                
                int no=Integer.parseInt(ino[1]);
                no=no+1;
              lid.setText("ln"+no);
           
            
            System.out.println(a);
        } catch (Exception e) {
        }
       
    
    }   
    
        public void tableload()
        {
            String sql="select * from Loan";
            try{
               pst=conn.prepareStatement(sql);
               rs=pst.executeQuery();
               
               jTable2.setModel(DbUtils.resultSetToTableModel(rs));
                
            }
            catch(Exception e){
                
            }    
            finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
        
    }
        public void clear(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField6.setText("");
        autoincrement();
        
        
        
        
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        lid = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Loan Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Loan Name");

        jButton2.setBackground(new java.awt.Color(179, 174, 174));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(179, 174, 174));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Duration");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Bank Name");

        jButton7.setBackground(new java.awt.Color(179, 174, 174));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Remove");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Monthly Payment");

        jButton6.setBackground(new java.awt.Color(179, 174, 174));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Start Date");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("End Date");

        jTextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextField2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextField3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTextField6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Loan ID");

        lid.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(87, 87, 87)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7))
                                        .addComponent(jLabel3)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel6))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(83, 83, 83)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                        .addComponent(lid)
                                        .addComponent(jTextField2)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton1))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton2))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Loan ID", "Loan Name", "Bank Name", "Monthly Payment", "Start Date", "End Date", "Duration"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setBackground(new java.awt.Color(179, 174, 174));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Full Report ");

        jButton4.setBackground(new java.awt.Color(179, 174, 174));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Single Report ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("   HANDLE LOANS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 197, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField6.setText("");
        autoincrement();
        //jComboBox1.setSelectedItem("Select");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
      if(lid.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("") || ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().equals("") || jTextField6.getText().equals(""))
      {
          
          JOptionPane.showMessageDialog(null,"All fields are required");
            
       }  
      else{
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(jDateChooser1.getDate()));
            d2=format.parse(format.format(jDateChooser2.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
          
                    
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"End date Should be Upcomming Date");
                //jDateChooser1.setDate(null);
                jDateChooser2.setDate(null);
          } 
            else{
        try
        {
            
           //SimpleDateFormat format=new SimpleDateFormat();   
          String sql="Insert into Loan values(?,?,?,?,?,?,?,?)";  
          pst=conn.prepareStatement(sql);
          pst.setString(1,lid.getText());
          pst.setString(2,jTextField1.getText());    
          pst.setString(3,jTextField2.getText());
          pst.setDouble(4,Double.parseDouble(jTextField3.getText()));
          pst.setString(5,((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());   
          pst.setString(6,((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText());
          
          
          //pst.setString(5,format.parse(format.format(jDateChooser2.getDate())).toString());         
          pst.setString(7,jTextField6.getText());
          //pst.setString(7,jComboBox1.getSelectedItem().toString());
          
          
          
          
          pst.execute();
          Update_loandate();
          tableload();
          
          clear();
          JOptionPane.showMessageDialog(null,"Saved");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
      }
        }
        catch(Exception e){
        
        }
        finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
      }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

     if(lid.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("") || ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().equals("") || jTextField6.getText().equals(""))
      {
          
          JOptionPane.showMessageDialog(null,"All fields are required");
            
       }  
     else{
        int row=jTable2.getSelectedRow();
        int col=0;
        String Loan_ID = jTable2.getValueAt(row,col).toString();
        
        int x=JOptionPane.showConfirmDialog(null, "Are you sure you want to remove Loan"+Loan_ID);
        
        if(x==0)
        {
        
       try {
            String que = "DELETE FROM Loan WHERE Loan_ID ='"+Loan_ID+"' ";
            pst = conn.prepareStatement(que);
        
            pst.execute();
            clear();
            tableload();
        } 
       
        catch (Exception e) {
        }
       finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
       
        }
     }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
       char c=evt.getKeyChar();
       
       if(Character.isLetter(c))
       {
           evt.consume();
           JOptionPane.showMessageDialog(null,"Enter only Numbers");
       }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
                                            
        DefaultTableModel model=(DefaultTableModel) jTable2.getModel();
        
        
        
        int row=jTable2.getSelectedRow();
        
        String Lid1=jTable2.getValueAt(row,0).toString();
        String Lname=jTable2.getValueAt(row,1).toString();
        String bname=jTable2.getValueAt(row,2).toString();
        String monthlyp=jTable2.getValueAt(row,3).toString();
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(model.getValueAt(row,4).toString());
        ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).setText(model.getValueAt(row,5).toString());
        String duration=jTable2.getValueAt(row,6).toString();
        //String reminder=jTable2.getValueAt(row,6).toString();
        lid.setText(Lid1);
        jTextField1.setText(Lname);
        jTextField2.setText(bname);
        jTextField3.setText(monthlyp);
        jTextField6.setText(duration);
        //jComboBox1.setSelectedItem(reminder);
        
        
        
       
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

         if(lid.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("") || ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().equals("") || jTextField6.getText().equals(""))
      {
          
          JOptionPane.showMessageDialog(null,"All fields are required");
            
       }  
         else{
        
        try{
            String value1=lid.getText();
            String value2=jTextField1.getText();
            String value3=jTextField2.getText();
            String value4=jTextField3.getText();
            String value5=(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            String value6=(((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText());
            String value7=jTextField6.getText();
            //String value7=jComboBox1.getSelectedItem().toString();
            
            String sql="Update Loan set Loan_ID='"+value1+"', Loan_Name='"+value2+"' , Bank_Name='"+value3+"' ,Monthly_Payment='"+value4+"' ,Start_Date='"+value5+"' ,End_Date='"+value6+"' ,Duration='"+value7+"' where Loan_ID='"+value1+"'";
             pst=conn.prepareStatement(sql);
             pst.execute();
             tableload();
             clear();
             
            JOptionPane.showMessageDialog(null,"Updated"); 
            
        }
        
        catch(Exception e){
        
        JOptionPane.showMessageDialog(null,e);
        
        }
        finally{
                try{
                pst.close();
                rs.close();
                }
                catch(Exception e)
                {
                    
                }
            }
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField lid;
    // End of variables declaration//GEN-END:variables

    private void setExtendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setIconImages(ArrayList<Image> imageList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
