/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agency;

//import static itp.StoreManagement.currentStoreNumber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import DBconnection.DBconnect;
import static java.lang.String.format;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;


/**
 *
 * @author chath
 */
public class ItemManagement extends javax.swing.JInternalFrame {

    
    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;
    Statement s = null;
    
    
    public ItemManagement() {
        initComponents();
        TXTstockNo.setVisible(false);
        conn = DBconnect.connectDb();
        int table = CMBproducttype.getSelectedIndex();
        generateID();
                        
        tableLoad(table);
        tableLoad1(table);
    }

   public void clear(){
    
            //CLEAR ALL TEXT FIELDS
        
        CMBproducttype.setSelectedItem("Fertilizers");
        TXTitemcode.setText("");
        TXTdescription.setText("");
        TXTquantity.setText("");
        JDCmanudate.setDate(null);
        JDCexpdate.setDate(null);
        generateID();
        
     }
   
   
   public void clear1(){
   
       TXTcitemcode.setText("");
       TXTcdescription.setText("");
       CMBcuom.setSelectedIndex(0);
       TXTccapacity.setText("");
       TXTcbrandname.setText("");
       TXTcsupid.setText("");
       TXTcitemcode.setText("");
       TXTccost.setText("");
       TXTcdiscounts.setText("");
       TXTcdeliverycharge.setText("");
       TXTcsellingprice.setText("");
       
       generateID();
   
   }
   
   
    /**
     *
     */
    public void tableLoad(int table)
    {
        updateItemtable();
        String sql = null;
        
        try{
        if(table == 1)
        {
            
            sql = "SELECT w.stockno, w.itemcode, i.description, w.quantityadded FROM waterTanks w, items i WHERE i.itemcode = w.itemcode";
                    
        }
        
        else if(table == 2)
        {
        
            sql = "SELECT c.stockno, c.itemcode, i.description, c.manufactureddate, c.expirydate, c.quantityadded FROM cement c, items i WHERE i.itemcode = c.itemcode";
        
        }
        
        else if(table == 0)
        {
        
             sql = "SELECT f.stockno, f.itemcode, i.description, f.manufactureddate, f.expirydate, f.quantityadded FROM fertilizers f, items i WHERE i.itemcode = f.itemcode" ;
        
        }
        
        else{
            
            itemtable.setVisible(false);
        
        }
        
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
         
        itemtable.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        
        catch(Exception e){
        
            System.out.println(e);
        
        }
        
        finally{
        
            try{
               
                pst.close();
                rs.close();
                           
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
        
            
    }
    
    public void tableLoad1(int table)
    {
        
        String sql = null;
        
        try{
        if(table == 1)
        {
            
            sql = "SELECT * FROM items WHERE itemcode LIKE 'WTK%'";
                    
        }
        
        else if(table == 2)
        {
        
            sql = "SELECT * FROM items WHERE itemcode LIKE 'CMT%'";
        
        }
        
        else if (table == 0)
        {
        
             sql = "SELECT * FROM items WHERE itemcode LIKE 'FTZ%'";
        
        }
        
        
        
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
         
        citemtable.setModel(DbUtils.resultSetToTableModel(rs));
        
        generateID();
        
        }
        
        catch(Exception e){
        
            System.out.println(e);
        
        }
            
        finally{
        
            try{
            
                pst.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
    }
    
    public void updateItemtable(){
        String quan =null;
        String sql = null;
        int table = CMBproducttype.getSelectedIndex();
        String itemcode = TXTitemcode.getText();
        
        if(table == 0)
            sql = "SELECT itemcode,SUM(quantityadded) FROM fertilizers WHERE itemcode = '"+ itemcode +"' GROUP BY itemcode HAVING SUM(quantityadded)";
             
        if(table == 1)
            sql = "SELECT itemcode,SUM(quantityadded) FROM waterTanks WHERE itemcode = '"+ itemcode +"' GROUP BY itemcode HAVING SUM(quantityadded)";
        
        if(table == 2)
            sql = "SELECT itemcode,SUM(quantityadded) FROM cement WHERE itemcode = '"+ itemcode +"' GROUP BY itemcode HAVING SUM(quantityadded)";
        
        
        try {
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
            
            quan = rs.getString("SUM(quantityadded)");
            System.out.println(quan);          
           }
           
                        
            String q = "UPDATE items SET quantityleft='"+ quan +"' WHERE itemcode='"+ itemcode +"' ";
            
            pst1 = conn.prepareStatement(q);
            pst1.execute();
            
            
            
        } 
        
        catch (Exception e) {
            
                System.out.println(e);
        }
        
        finally{
        
            try{
            
                pst.close();
                pst1.close();
                rs.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
        
    
    }
    
    
    public void generateID(){
        
        int table = CMBcproducttype.getSelectedIndex();
        
        if(table == 1)
            delim = "WTK";
        else if(table == 2)
            delim = "CMT";
        else if(table == 0)
            delim = "FTZ";
                    
        
        try{
            s = conn.createStatement();
            rs = s.executeQuery("SELECT MAX(itemcode) AS lastitem FROM items WHERE itemcode LIKE '"+ delim +"%'");
            
            String itemcode;
            
           
            
            while(rs.next()){
                
                itemcode = rs.getString("lastitem");
                String sid[] = itemcode.split(delim);
                int no=Integer.parseInt(sid[1]);
                no = no + 1;
                TXTcitemcode.setText(delim+no);
                
            }
        }
        
        catch (Exception e) {
            
            System.out.println(e);
        
        }
        
        finally{
        
            try{
            
                rs.close();
                                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        }
        
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TXTname1 = new javax.swing.JTextField();
        TXTad3 = new javax.swing.JTextField();
        TXTad4 = new javax.swing.JTextField();
        TXTcity1 = new javax.swing.JTextField();
        TXTcon1 = new javax.swing.JTextField();
        TXTid1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTNinsert = new javax.swing.JButton();
        BTNdelete = new javax.swing.JButton();
        BTNupdate = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemtable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        TXTitemcode = new javax.swing.JTextField();
        TXTquantity = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        TXTdescription = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        CMBproducttype = new javax.swing.JComboBox();
        PNLfertilizers = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        JDCmanudate = new com.toedter.calendar.JDateChooser();
        JDCexpdate = new com.toedter.calendar.JDateChooser();
        BTNaddtostock = new javax.swing.JButton();
        BTNremovefromstock = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        TXTstockNo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        CMBcuom = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        TXTcitemcode = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        TXTccapacity = new javax.swing.JTextField();
        TXTcdescription = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        TXTcbrandname = new javax.swing.JTextField();
        TXTcsupid = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        TXTcsellingprice = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        TXTcdiscounts = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        TXTcdeliverycharge = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        TXTccost = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        citemtable = new javax.swing.JTable();
        BTNcadd = new javax.swing.JButton();
        BTNcupdate = new javax.swing.JButton();
        CMBcproducttype = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        LBLstockno = new javax.swing.JLabel();
        BTNcremove = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Store Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(153, 153, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(102, 51, 255));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Store Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Store ID             ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Store Name         ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Address line 1      ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Address line 2      ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("City                   ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Contact Number   ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTname1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(4, 4, 4)
                        .addComponent(TXTad3))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(4, 4, 4)
                        .addComponent(TXTad4))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(7, 7, 7)
                        .addComponent(TXTcity1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTid1)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTcon1)))
                .addGap(31, 31, 31))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(TXTname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TXTid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TXTad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(TXTad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(TXTcity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TXTcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        BTNinsert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BTNinsert.setText("Insert");
        BTNinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNinsertActionPerformed(evt);
            }
        });

        BTNdelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BTNdelete.setText("Delete");

        BTNupdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BTNupdate.setText("Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(BTNinsert, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(BTNdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(BTNupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(175, 175, 175)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNinsert, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(447, Short.MAX_VALUE))
        );

        jMenuItem1.setText("items");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jPanel2.setForeground(new java.awt.Color(102, 51, 255));

        itemtable.setModel(new javax.swing.table.DefaultTableModel(
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
        itemtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemtableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(itemtable);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TXTitemcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTitemcodeActionPerformed(evt);
            }
        });
        TXTitemcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTitemcodeKeyTyped(evt);
            }
        });

        TXTquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTquantityKeyTyped(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Item Code         :");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Quantity            :");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Description        :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXTdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTitemcode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTitemcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(TXTdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(TXTquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Product Type     :");

        CMBproducttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fertilizers", "Water Tanks", "Cement", " " }));
        CMBproducttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBproducttypeActionPerformed(evt);
            }
        });

        PNLfertilizers.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fertilizers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Manufacture Date       :");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Expiry Date                  :");

        javax.swing.GroupLayout PNLfertilizersLayout = new javax.swing.GroupLayout(PNLfertilizers);
        PNLfertilizers.setLayout(PNLfertilizersLayout);
        PNLfertilizersLayout.setHorizontalGroup(
            PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNLfertilizersLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNLfertilizersLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(346, 346, 346))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNLfertilizersLayout.createSequentialGroup()
                        .addGroup(PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JDCexpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(JDCmanudate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34))))
        );
        PNLfertilizersLayout.setVerticalGroup(
            PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNLfertilizersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNLfertilizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PNLfertilizersLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel60))
                    .addGroup(PNLfertilizersLayout.createSequentialGroup()
                        .addComponent(JDCmanudate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JDCexpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(161, 161, 161)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BTNaddtostock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNaddtostock.setText("Add to Stock");
        BTNaddtostock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNaddtostockActionPerformed(evt);
            }
        });

        BTNremovefromstock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BTNremovefromstock.setText("Remove From Stock");
        BTNremovefromstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNremovefromstockActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("   Update Stock");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        TXTstockNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTstockNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CMBproducttype, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(129, 129, 129)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(PNLfertilizers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(473, 473, 473)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BTNaddtostock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTNremovefromstock, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TXTstockNo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(315, 315, 315))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTstockNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PNLfertilizers, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CMBproducttype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(BTNaddtostock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTNremovefromstock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Update Stock", jPanel2);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Product Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Description              :");

        CMBcuom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kg", "Tons", "Litres", "Units" }));
        CMBcuom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBcuomActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Item Code               :");

        TXTcitemcode.setEditable(false);
        TXTcitemcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcitemcodeActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("Unit of Measure      :");

        TXTccapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTccapacityActionPerformed(evt);
            }
        });
        TXTccapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTccapacityKeyTyped(evt);
            }
        });

        TXTcdescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcdescriptionActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Capacity                   :");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(TXTccapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TXTcitemcode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXTcdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CMBcuom, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(TXTcitemcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTcdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(CMBcuom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(TXTccapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brand Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Brand Name            :");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Supplier ID              :");

        TXTcbrandname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcbrandnameActionPerformed(evt);
            }
        });
        TXTcbrandname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcbrandnameKeyTyped(evt);
            }
        });

        TXTcsupid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcsupidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXTcbrandname, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(TXTcsupid))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTcbrandname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTcsupid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pricing Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 22), new java.awt.Color(0, 0, 102))); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Selling Price                :");

        TXTcsellingprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcsellingpriceActionPerformed(evt);
            }
        });
        TXTcsellingprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcsellingpriceKeyTyped(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("Discounts                   :");

        TXTcdiscounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcdiscountsActionPerformed(evt);
            }
        });
        TXTcdiscounts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcdiscountsKeyTyped(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Delivery Charge         :");

        TXTcdeliverycharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTcdeliverychargeActionPerformed(evt);
            }
        });
        TXTcdeliverycharge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTcdeliverychargeKeyTyped(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("Cost                            :");

        TXTccost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTccostActionPerformed(evt);
            }
        });
        TXTccost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTccostKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(TXTccost))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(TXTcdeliverycharge, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTcsellingprice)
                            .addComponent(TXTcdiscounts))))
                .addGap(31, 31, 31))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(TXTccost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(TXTcdiscounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(TXTcdeliverycharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(TXTcsellingprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        citemtable.setModel(new javax.swing.table.DefaultTableModel(
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
        citemtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                citemtableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(citemtable);

        BTNcadd.setText("Add Product");
        BTNcadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcaddActionPerformed(evt);
            }
        });

        BTNcupdate.setText("Update Product");
        BTNcupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcupdateActionPerformed(evt);
            }
        });

        CMBcproducttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fertilizers", "Water Tanks", "Cement", " " }));
        CMBcproducttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBcproducttypeActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Product Type     :");

        BTNcremove.setText("Remove Product");
        BTNcremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNcremoveActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("   Handle Products");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(BTNcadd, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(BTNcremove, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(BTNcupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBLstockno, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBcproducttype, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel73)
                .addGap(18, 18, 18)
                .addComponent(CMBcproducttype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTNcupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BTNcadd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BTNcremove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(LBLstockno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        jTabbedPane1.addTab("Handle Products", jPanel7);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   Update Stock");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinsertActionPerformed

        

    }//GEN-LAST:event_BTNinsertActionPerformed

    private void CMBcproducttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBcproducttypeActionPerformed
        
        int table = CMBcproducttype.getSelectedIndex();
              
        generateID();
        clear1();
        tableLoad1(table);


    }//GEN-LAST:event_CMBcproducttypeActionPerformed

    private void BTNcaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcaddActionPerformed

        if(TXTcitemcode.getText().equals("") || TXTcdescription.getText().equals("") || TXTccapacity.getText().equals("") || TXTcbrandname.getText().equals("") || TXTcsupid.getText().equals("") || TXTccost.getText().equals("") || TXTcdiscounts.getText().equals("") || TXTcdeliverycharge.getText().equals("") || TXTcsellingprice.getText().equals("")  );
        
        int table = CMBcproducttype.getSelectedIndex();

        String itemcode = TXTcitemcode.getText();
        String description = TXTcdescription.getText();
        String uom = CMBcuom .getSelectedItem().toString();
        String capacity = TXTccapacity.getText();
        String brandname= TXTcbrandname.getText();
        String supid = TXTcsupid.getText();
        String cost = TXTccost.getText();
        String sellingprice = TXTcsellingprice .getText();
        String discount = TXTcdiscounts.getText();
        String deliverycharge= TXTcdeliverycharge.getText();

        String q = "INSERT INTO items (itemcode,description,uom,capacity,brandname,supplierid,priceeach,discounts,deliverycharge,cost) values('"+ itemcode +"' , '"+ description +"' , '"+ uom +"' , '"+ capacity +"' , '"+ brandname +"', '"+ supid +"' , '"+ sellingprice +"' , '"+ discount +"' , '"+ deliverycharge +"' , '"+ cost +"')";

        try
        {

            pst = conn.prepareStatement(q);
            pst.execute();

            //load Table
            tableLoad1(table);
            clear1();

        }

        catch(Exception e)
        {

            System.out.println(e);

        }
        
        finally{
        
            try{
            
                pst.close();
                                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
    }//GEN-LAST:event_BTNcaddActionPerformed

    private void citemtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_citemtableMouseClicked

        int table = CMBcproducttype.getSelectedIndex();
        DefaultTableModel model = (DefaultTableModel) citemtable.getModel();
        int row = citemtable.getSelectedRow();
        
        String itemcode = citemtable.getValueAt(row,0).toString();
        String description = citemtable.getValueAt(row,1).toString();
        String priceeach = citemtable.getValueAt(row,2).toString();
        String uom = citemtable.getValueAt(row,3).toString();
        String brandname = citemtable.getValueAt(row,4).toString();
        String capacity = citemtable.getValueAt(row,5).toString();
        String supid = citemtable.getValueAt(row,6).toString();
        //String dateadded = citemtable.getValueAt(row,7).toString();
        String discount = citemtable.getValueAt(row,8).toString();
        String deliverycharge = citemtable.getValueAt(row,9).toString();
        String cost = citemtable.getValueAt(row,11).toString();
        
        TXTcitemcode.setText(itemcode);
        TXTcdescription.setText(description);
        TXTcsellingprice .setText(priceeach);
        CMBcuom.setSelectedItem(uom);
        TXTcbrandname.setText(brandname);
        TXTccapacity.setText(capacity);
        TXTcsupid.setText(supid);
        TXTcdiscounts.setText(discount);
        TXTcdeliverycharge.setText(deliverycharge);
        TXTccost.setText(cost);
        
        
       
        
    }//GEN-LAST:event_citemtableMouseClicked

    private void TXTcdeliverychargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcdeliverychargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcdeliverychargeActionPerformed

    private void TXTcdiscountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcdiscountsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcdiscountsActionPerformed

    private void TXTcsellingpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcsellingpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcsellingpriceActionPerformed

    private void TXTccostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTccostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTccostActionPerformed

    private void TXTcsupidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcsupidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcsupidActionPerformed

    private void TXTcbrandnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcbrandnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcbrandnameActionPerformed

    private void TXTcdescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcdescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcdescriptionActionPerformed

    private void TXTccapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTccapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTccapacityActionPerformed

    private void TXTcitemcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTcitemcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTcitemcodeActionPerformed

    private void CMBcuomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBcuomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMBcuomActionPerformed

    private void BTNaddtostockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNaddtostockActionPerformed

        int table = CMBcproducttype.getSelectedIndex();
        
        if(TXTitemcode.getText().equals("") || TXTdescription.getText().equals("") || TXTquantity.getText().equals(""))
        {
            
            JOptionPane.showMessageDialog(null,"All fields are required to be filled");
        
        }
        
        
        
        if(JDCexpdate.getDate().before(JDCmanudate.getDate()))
        {
        
            JOptionPane.showMessageDialog(null, "Expiry date must be a date after the Manufactured date");
        
        }
        
        
        
        
        
        
        
        
        
        
        else{
            
        
            
        
        
        String itemcode = TXTitemcode.getText();
        String quantity = TXTquantity.getText();
        
        
        String q;
        
        
        if (table == 1){
            q = "INSERT INTO waterTanks (itemcode, quantityadded) values('"+ itemcode +"' , '"+ quantity +"')";
        }
        
        else{
        
            Date manudate = (JDCmanudate.getDate());
            Date expdate = (JDCexpdate.getDate());
            
            if (table == 0){
                q = "INSERT INTO fertilizers (itemcode, quantityadded, manufactureddate, expirydate) values('"+ itemcode +"' , '"+ quantity +"' , '"+ manudate +"' , '"+ expdate +"')";
            }
        
            else{
                q = "INSERT INTO cement (itemcode, quantityadded, manufactureddate, expirydate) values('"+ itemcode +"' , '"+ quantity +"' , '"+ manudate +"' , '"+ expdate +"')";
            }
        
        }
        
        try
        {

            pst = conn.prepareStatement(q);
            pst.execute();

            //load Table
           
           updateItemtable();
           clear();
           tableLoad(table);

        }

        catch(Exception e)
        {

            System.out.println("ll");

        }
        
        finally{
        
            try{
            
                pst.close();
               
            }
            
            catch(Exception e){
        
                System.out.println("oo");
        
        }
        
        }
        
        
    }//GEN-LAST:event_BTNaddtostockActionPerformed
    }
    private void CMBproducttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBproducttypeActionPerformed

        int temp = CMBproducttype.getSelectedIndex();

        if(temp == 1){

            PNLfertilizers.setVisible(false);

        }

        else{

            PNLfertilizers.setVisible(true);

        }

        tableLoad(temp);
    }//GEN-LAST:event_CMBproducttypeActionPerformed

    private void TXTitemcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTitemcodeActionPerformed

        try {

            String itemcode = TXTitemcode.getText();

            String sql = "SELECT description FROM items WHERE itemcode = '"+ itemcode +"' ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if(rs.next()) {

                String description = rs.getString("description");
                TXTdescription.setText(description);

            }
        }

        catch (Exception e) {

            System.out.println(e);

        }

        finally{

            try{

                pst.close();
                rs.close();

            }

            catch(Exception e){

                System.out.println(e);

            }

        }

        TXTquantity.requestFocus();
    }//GEN-LAST:event_TXTitemcodeActionPerformed

    private void itemtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemtableMouseClicked

        int table = CMBproducttype.getSelectedIndex();
        DefaultTableModel model = (DefaultTableModel) itemtable.getModel();
        int row = itemtable.getSelectedRow();
        
        String stockno = itemtable.getValueAt(row,0).toString();
        String itemcode = itemtable.getValueAt(row,1).toString();
        String description = itemtable.getValueAt(row,2).toString();
        String quantity;
        String expdate;
        String manudate;
        
        
        if(table == 1){
          
            quantity = itemtable.getValueAt(row,3).toString();
        
            TXTstockNo.setText(stockno);
            TXTitemcode.setText(itemcode);
            TXTdescription.setText(description);
            TXTquantity.setText(quantity);
            
        
        }
        
        else{
                
            manudate = itemtable.getValueAt(row,3).toString();
            expdate = itemtable.getValueAt(row,4).toString();
            quantity = itemtable.getValueAt(row,5).toString();  
            
            TXTstockNo.setText(stockno);
            TXTitemcode.setText(itemcode);
            TXTdescription.setText(description);
            JDCmanudate.setDateFormatString(manudate);
            JDCexpdate.setDateFormatString(expdate);
            TXTquantity.setText(quantity);
            
        }
        
        
        
    }//GEN-LAST:event_itemtableMouseClicked

    private void BTNremovefromstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNremovefromstockActionPerformed
        
        String sql = null;
        int table = CMBproducttype.getSelectedIndex();
        int y = JOptionPane.showConfirmDialog(null,"Do you really want to delete this record?");

        if(y==0)
        {

            String stockno = TXTstockNo.getText();

            if(table == 0)
                sql = "DELETE from fertilizers where stockno = '"+ stockno +"'";
            
            if(table == 1)
                sql = "DELETE from waterTanks where stockno = '"+ stockno +"'";
            
            if(table == 2)
                sql = "DELETE from cement where stockno = '"+ stockno +"'";
    
            
            try
            {

                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Record was successfully removed");
                
                
                //load Table
                updateItemtable();
                clear();
                tableLoad(table);

            }

            catch(Exception e)
            {
                    System.out.println(e);
            }
            
            finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }
            
        }
         
       
        
    }//GEN-LAST:event_BTNremovefromstockActionPerformed

    private void BTNcremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcremoveActionPerformed
        
        int table = CMBcproducttype.getSelectedIndex();
        String sql = null;
        int y = JOptionPane.showConfirmDialog(null,"Do you really want to delete this record?");

        if(y==0)
        {

            String id = TXTcitemcode.getText();

            sql = "DELETE from items where itemcode = '"+ id +"'";

            try
            {

                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Record was successfully removed");
                clear1();
                
                //load Table
                tableLoad1(table);

            }

            catch(Exception e)
            {
                    System.out.println(e);
            }
            
            finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
                
        }
        
        }
        
    }
        
        clear1();
        
    }//GEN-LAST:event_BTNcremoveActionPerformed

    private void BTNcupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNcupdateActionPerformed
        
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update this record?");

        if(x == 0)
        {

        int table = CMBcproducttype.getSelectedIndex();

        String itemcode = TXTcitemcode.getText();
        String description = TXTcdescription.getText();
        String uom = CMBcuom .getSelectedItem().toString();
        String capacity = TXTccapacity.getText();
        String brandname= TXTcbrandname.getText();
        String supid = TXTcsupid.getText();
        String cost = TXTccost.getText();
        String sellingprice = TXTcsellingprice .getText();
        String discount = TXTcdiscounts.getText();
        String deliverycharge= TXTcdeliverycharge.getText();

            String sql = "UPDATE items SET itemcode='"+ itemcode +"' , description='"+ description +"' ,  priceeach='"+ sellingprice +"' , uom='"+ uom +"' ,  brandname='"+ brandname +"' ,"
                    + " capacity='"+ capacity +"' , supplierid='"+ supid +"' ,  discounts='"+ discount +"' , deliverycharge='"+ deliverycharge +"' ,  cost='"+ cost +"' "
                    + "WHERE itemcode = '"+ itemcode +"'";

            try{

                
                
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                

                //load Table
                tableLoad1(table);

            }

            catch(Exception e){
                    System.out.println(e);
            }
            
            finally{
        
            try{
            
                pst.close();
                
            
            }
            
            catch(Exception e){
        
                System.out.println(e);
        
        }
        
        }

        }
        
        
        clear1();
        
    }//GEN-LAST:event_BTNcupdateActionPerformed

    private void TXTccapacityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTccapacityKeyTyped
         char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTccapacityKeyTyped

    private void TXTcsellingpriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcsellingpriceKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTcsellingpriceKeyTyped

    private void TXTcdiscountsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcdiscountsKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTcdiscountsKeyTyped

    private void TXTcdeliverychargeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcdeliverychargeKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
    }//GEN-LAST:event_TXTcdeliverychargeKeyTyped

    private void TXTstockNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTstockNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTstockNoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void TXTitemcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTitemcodeKeyTyped
        
        int table = CMBproducttype.getSelectedIndex();
        String delim;
        
        if(table == 0){
        
            delim = "FTZ";
        
        }
        
        else if(table == 1){
        
            delim = "WTK";
        
        }
        
        else{
        
            delim = "CMT";
        
        }
        
        
    }//GEN-LAST:event_TXTitemcodeKeyTyped

    private void TXTquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTquantityKeyTyped
        
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Digits");
        }
        
    }//GEN-LAST:event_TXTquantityKeyTyped

    private void TXTcbrandnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTcbrandnameKeyTyped
        char c=evt.getKeyChar();

        if(Character.isDigit(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Letters");
        }
    }//GEN-LAST:event_TXTcbrandnameKeyTyped

    private void TXTccostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTccostKeyTyped
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Digits");
        }
    }//GEN-LAST:event_TXTccostKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNaddtostock;
    private javax.swing.JButton BTNcadd;
    private javax.swing.JButton BTNcremove;
    private javax.swing.JButton BTNcupdate;
    private javax.swing.JButton BTNdelete;
    private javax.swing.JButton BTNinsert;
    private javax.swing.JButton BTNremovefromstock;
    private javax.swing.JButton BTNupdate;
    private javax.swing.JComboBox CMBcproducttype;
    private javax.swing.JComboBox CMBcuom;
    private javax.swing.JComboBox CMBproducttype;
    private com.toedter.calendar.JDateChooser JDCexpdate;
    private com.toedter.calendar.JDateChooser JDCmanudate;
    private javax.swing.JLabel LBLstockno;
    private javax.swing.JPanel PNLfertilizers;
    private javax.swing.JTextField TXTad3;
    private javax.swing.JTextField TXTad4;
    private javax.swing.JTextField TXTcbrandname;
    private javax.swing.JTextField TXTccapacity;
    private javax.swing.JTextField TXTccost;
    private javax.swing.JTextField TXTcdeliverycharge;
    private javax.swing.JTextField TXTcdescription;
    private javax.swing.JTextField TXTcdiscounts;
    private javax.swing.JTextField TXTcitemcode;
    private javax.swing.JTextField TXTcity1;
    private javax.swing.JTextField TXTcon1;
    private javax.swing.JTextField TXTcsellingprice;
    private javax.swing.JTextField TXTcsupid;
    private javax.swing.JTextField TXTdescription;
    private javax.swing.JTextField TXTid1;
    private javax.swing.JTextField TXTitemcode;
    private javax.swing.JTextField TXTname1;
    private javax.swing.JTextField TXTquantity;
    private javax.swing.JTextField TXTstockNo;
    private javax.swing.JTable citemtable;
    private javax.swing.JTable itemtable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

String delim;

}
