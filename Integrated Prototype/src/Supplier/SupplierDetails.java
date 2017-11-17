/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Supplier;

import DBconnection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Timansi_Lakshika
 */
public class SupplierDetails extends javax.swing.JInternalFrame {
    
    Connection conn1 = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    PreparedStatement pst5 = null;
    PreparedStatement pst6 = null;
    PreparedStatement pst7 = null;
    PreparedStatement pst8 = null;
    PreparedStatement pst9 = null;
    PreparedStatement pst10 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;

    /**
     * Creates new form SupplierDetails
     */
    public SupplierDetails() {
        initComponents();
        TextID.setEditable(false);
        conn1 = DBconnect.connectDb();
        LoadingSupplierDetailsTable();
        AddButton.setEnabled(false);
        ButtonItemDetails.setEnabled(false);
    }
    
    public void LoadingSupplierDetailsTable(){
        try{
            String c = "SELECT SupplierID, SupplierName, Address, ItemCategories, PhoneNo FROM SupplierDetails";
            
        
            pst1 = conn1.prepareStatement(c);
            rs1 = pst1.executeQuery();
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
            
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error in loading the Supplier Order Table!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }finally{
            try {
                pst1.close();
                rs1.close();
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void LoadingSupplierItemDetailsTable(){
        try {
            int SID = Integer.parseInt(TextID.getText());
            String s1 = "SELECT ItemCode,ItemName,BrandID,BrandName,UnitPrice,Size FROM SupplierItemDetails WHERE SupplierID = '"+SID+"'";
            pst2 = conn1.prepareStatement(s1);
            rs2 = pst2.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs2));
            
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        } finally{
            try {
                pst2.close();
                rs2.close();
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void ClearSupplierItemDetails(){
        //AddButton.setEnabled(false);
        
        ItemCode.setText("");
        ItemName.setText("");
        BrandID.setText("");
        BrandName.setText("");
        UnitPrice.setText("");
        Size.setText("");
    }
    
    public boolean isValidPhone(String phone){
       
        //Validating Phone Text Field
        if(phone.length()==10){
            for(int i=0;i<10;i++){
                if(Character.isLetter(phone.charAt(i))){
                    JOptionPane.showMessageDialog(null,"Please Enter a Valid Telephone Number. (Enter digits only)!","Error!", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } 
        }
        else if(phone.length()!=10){
            JOptionPane.showMessageDialog(null,"Please Enter a Valid Telephone Number with exact 10 digits!","Error!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
       //Phone Validation Ends
    }
    
    public String GetSelectedCategories(){
        String catgry = "";
        
        if(PaintCheckBox.isSelected()){
            catgry = catgry + "Paint ,\n";
        }
        if(BathroomCheckBox.isSelected()){
            catgry = catgry + "Bathroom_Fittings ,\n";
        }
        if(CementCheckBox.isSelected()){
            catgry = catgry + "Cement ,\n";
        }
        if(RoofingCheckBox.isSelected()){
            catgry = catgry + "Roofing_Items ,\n";
        }
        if(ConstructionCheckBox.isSelected()){
            catgry = catgry + "Construction_Items ,\n";
        }
        if(WaterCheckBox.isSelected()){
            catgry = catgry + "Water_Pipe_Fittings ,\n";
        }
        if(ChemicalsCheckBox.isSelected()){
            catgry = catgry + "Chemicals ,\n";
        }
        if(FertilizerCheckBox.isSelected()){
            catgry = catgry + "Fertilizers ,\n";
        }
        if(TankCheckBox.isSelected()){
            catgry = catgry + "Water_Tanks ,\n";
        }
        if(OtherCheckBox.isSelected()){
            catgry = catgry + "Other ,\n";
        }
        
        return catgry;
    }
    
    public void SetSelectedCategories(){
         int row = jTable1.getSelectedRow();
         
         String category = jTable1.getValueAt(row,3).toString();
         String Catgry[] = category.split(" ");  
         
         for(int i=0; i<Catgry.length; i++){
             if(Catgry[i].equals("Paint_Thinner ")){
                 PaintCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Bathroom_Fittings ")){
                 BathroomCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Cement ")){
                 CementCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Roofing_Items ")){
                 RoofingCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Construction_Items ")){
                 ConstructionCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Water_Pipe_Fittings ")){
                 WaterCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Chamicals ")){
                 ChemicalsCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Fertilizers ")){
                 FertilizerCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Water_Tanks ")){
                 TankCheckBox.setSelected(true);
             }
             else if(Catgry[i].equals("Other ")){
                 OtherCheckBox.setSelected(true);
             }
         }
             
    }
    
    public void ClearCheckBoxes(){
        PaintCheckBox.setSelected(false);
        BathroomCheckBox.setSelected(false);
        CementCheckBox.setSelected(false);
        RoofingCheckBox.setSelected(false);
        ConstructionCheckBox.setSelected(false);
        WaterCheckBox.setSelected(false);
        ChemicalsCheckBox.setSelected(false);
        FertilizerCheckBox.setSelected(false);
        TankCheckBox.setSelected(false);
        OtherCheckBox.setSelected(false);
       
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        BrandID = new javax.swing.JTextField();
        LabelSID = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Size = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        BrandName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        ItemName = new javax.swing.JTextField();
        ItemCode = new javax.swing.JTextField();
        UnitPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        AddButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        TextName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        TextID = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextAddress = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        TextPhone = new javax.swing.JTextField();
        ButtonItemDetails = new javax.swing.JButton();
        PaintCheckBox = new javax.swing.JCheckBox();
        BathroomCheckBox = new javax.swing.JCheckBox();
        CementCheckBox = new javax.swing.JCheckBox();
        RoofingCheckBox = new javax.swing.JCheckBox();
        ConstructionCheckBox = new javax.swing.JCheckBox();
        WaterCheckBox = new javax.swing.JCheckBox();
        ChemicalsCheckBox = new javax.swing.JCheckBox();
        FertilizerCheckBox = new javax.swing.JCheckBox();
        TankCheckBox = new javax.swing.JCheckBox();
        OtherCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        jDialog1.setSize(new java.awt.Dimension(960, 520));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Brand ID", "Brand Name", "Unit Price", "Size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204), 3));

        LabelSID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Item Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("SupplierID");

        jButton10.setText("Clear");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Unit Price");

        jButton8.setText("Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Brand ID");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Item Code");

        jButton9.setText("Remove");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Brand Name");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Size");

        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelSID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ItemCode)
                            .addComponent(ItemName)
                            .addComponent(BrandID)
                            .addComponent(BrandName)
                            .addComponent(UnitPrice)
                            .addComponent(Size, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LabelSID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(BrandID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(BrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        setBackground(new java.awt.Color(204, 204, 255));
        setTitle("Supplier Details");
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1660, 1000));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Supplier Name", "Location", "Item categories", "Contact Number"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        AddButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TextName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TextName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNameKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Address");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Supplier Name");

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("New Entry");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        TextID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TextID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Supplier ID");

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Item Categories");

        TextAddress.setColumns(20);
        TextAddress.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TextAddress.setRows(5);
        jScrollPane4.setViewportView(TextAddress);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Contact Number");

        TextPhone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TextPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextPhoneKeyTyped(evt);
            }
        });

        ButtonItemDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ButtonItemDetails.setText("Item Details");
        ButtonItemDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonItemDetailsActionPerformed(evt);
            }
        });

        PaintCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PaintCheckBox.setText("Paint/Thinner");

        BathroomCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BathroomCheckBox.setText("Bathroom Fittings");

        CementCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CementCheckBox.setText("Cement");

        RoofingCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RoofingCheckBox.setText("Roofing Items");

        ConstructionCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ConstructionCheckBox.setText("Construction Items");

        WaterCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        WaterCheckBox.setText("Water Pipe Fittings");

        ChemicalsCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ChemicalsCheckBox.setText("Chemicals");

        FertilizerCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FertilizerCheckBox.setText("Fertilizers");

        TankCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TankCheckBox.setText("Water Tanks");

        OtherCheckBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        OtherCheckBox.setText("Other");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ButtonItemDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextName)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(TextPhone)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BathroomCheckBox)
                                    .addComponent(PaintCheckBox)
                                    .addComponent(CementCheckBox)
                                    .addComponent(RoofingCheckBox)
                                    .addComponent(ConstructionCheckBox)
                                    .addComponent(WaterCheckBox)
                                    .addComponent(ChemicalsCheckBox)
                                    .addComponent(FertilizerCheckBox)
                                    .addComponent(TankCheckBox)
                                    .addComponent(OtherCheckBox))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TextPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(PaintCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BathroomCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CementCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RoofingCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConstructionCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WaterCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChemicalsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FertilizerCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TankCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OtherCheckBox)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(ButtonItemDetails))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("   Supplier Details");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
       
        int id = Integer.parseInt(TextID.getText());
        String id1 = TextID.getText();
        
        String name = TextName.getText();
        String address = TextAddress.getText();
        String category = GetSelectedCategories();
        
        String phone  = TextPhone.getText();
        
        if(id1.equals("") || name.equals("") || address.equals("") || category.equals("") || phone.equals("")){
            JOptionPane.showMessageDialog(null,"All the text fields should be filled!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
        if(isValidPhone(phone)==true){
        try{
            
        String a = "INSERT INTO supplierdetails (SupplierID,SupplierName,Address,ItemCategories,PhoneNo ) values ('"+id+"','"+name+"','"+address+"','"+category+"','"+phone+"')";
        
        pst3 = conn1.prepareStatement(a);
        pst3.execute();
        
        LoadingSupplierDetailsTable();
        
        TextName.setText("");
        TextAddress.setText("");
        TextID.setText("");
        ClearCheckBoxes();
        TextPhone.setText("");
        
        
        System.out.println("Supplier Details Successfully added!");
        
        }
        catch(Exception e){
        
            JOptionPane.showMessageDialog(null,"Error in Entering the Supplier Record!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }finally{
            try {
                pst3.close();
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        }
        
    }//GEN-LAST:event_AddButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        int x = JOptionPane.showConfirmDialog(null,"Want to Update?");
        
        if(x==0){
        
        int id = Integer.parseInt(TextID.getText());
        String name  = TextName.getText();
        String address  = TextAddress.getText();
        String category = GetSelectedCategories();
        String phone = TextPhone.getText();
        
        try{  
            String sql1 = "UPDATE SupplierDetails SET SupplierName = '"+name+"',Address = '"+address+"',ItemCategories = '"+category+"',PhoneNo = '"+phone+"' WHERE SupplierID = '"+id+"' ";
            pst4 = conn1.prepareStatement(sql1);
            pst4.execute();
            
            LoadingSupplierDetailsTable();
            
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error in updating the Record!","Error!", JOptionPane.INFORMATION_MESSAGE);
        }finally{
            try {
                pst4.close();
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       TextID.setText("");
       TextName.setText("");
       TextAddress.setText("");
       ClearCheckBoxes();
       TextPhone.setText("");
        
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
         int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete this record?");

        if(x==0)

        {

            String ID = TextID.getText();

            try {
                String sql1 = "DELETE from supplierdetails where SupplierID = "+ID;
                
                pst5 = conn1.prepareStatement(sql1);
                pst5.execute();
               

                //load the table after deleting the record
                LoadingSupplierDetailsTable();
                
                }
            catch (Exception e) {
                //JOptionPane.showMessageDialog(null,e);
                JOptionPane.showMessageDialog(null,"Error in deleting the record!","Error!", JOptionPane.INFORMATION_MESSAGE);
            }finally{
                try {
                    pst5.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
       TextID.setText("");
       TextName.setText("");
       TextAddress.setText("");
       ClearCheckBoxes();
       TextPhone.setText("");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
       AddButton.setEnabled(false); 
       int row=jTable1.getSelectedRow();
       
       String ID=jTable1.getValueAt(row,0).toString();
       String Name=jTable1.getValueAt(row,1).toString();
       String Location=jTable1.getValueAt(row,2).toString();
       String Category=jTable1.getValueAt(row,3).toString();
       String Phone = jTable1.getValueAt(row,4).toString();
      
       TextID.setText(ID);
       TextID.setEditable(false);
       TextName.setText(Name);
       TextAddress.setText(Location);
       SetSelectedCategories();
       TextPhone.setText(Phone);
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       AddButton.setEnabled(false); 
       TextID.setText("");
       TextName.setText("");
       TextAddress.setText("");
       ClearCheckBoxes();
       TextPhone.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            //AddButton.setEnabled(true);
            ButtonItemDetails.setEnabled(true);
            
            String sql2 = "SELECT * from SupplierDetails GROUP BY SupplierID";
            
            pst6 = conn1.prepareStatement(sql2);
            rs3 = pst6.executeQuery();
            
            int row = -1;
            
            while(rs3.next()){
                row++;
            }
            
            if(row==-1){
                TextID.setText(String.valueOf("1"));
            }
            else{
                String oid=jTable1.getValueAt(row,0).toString();
                int oid1 = Integer.parseInt(oid);
                int OID = oid1 + 1;
                TextID.setText(String.valueOf(OID));
            }
            
        } catch (SQLException e) {
            //Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try {
                pst6.close();
                rs3.close();
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TextIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDActionPerformed

    private void ButtonItemDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonItemDetailsActionPerformed
        // TODO add your handling code here:
        jDialog1.setVisible (true);
        LabelSID.setText(TextID.getText());
        LoadingSupplierItemDetailsTable();
    }//GEN-LAST:event_ButtonItemDetailsActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            AddButton.setEnabled(true);    
        
            int SID = Integer.parseInt(LabelSID.getText());
            String Icode = ItemCode.getText();
            String IName = ItemName.getText();
            String BID = BrandID.getText();
            String BName = BrandName.getText();
            String UPrice = UnitPrice.getText();
            String size = Size.getText();
            
        try {    
            String s2 = "INSERT INTO SupplierItemDetails (SupplierID,ItemCode,ItemName,BrandID,BrandName,UnitPrice,Size) VALUES ('"+SID+"','"+Icode+"','"+IName+"','"+BID+"','"+BName+"','"+UPrice+"','"+size+"')";
            pst7 = conn1.prepareStatement(s2);
            pst7.execute();
            LoadingSupplierItemDetailsTable();
            
            ClearSupplierItemDetails();
            
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                try {
                    pst7.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            ItemCode.setEditable(true);
            
            //Update the table
            int SID = Integer.parseInt(LabelSID.getText());
            String Icode = ItemCode.getText();
            String IName = ItemName.getText();
            String BID = BrandID.getText();
            String BName = BrandName.getText();
            String UPrice = UnitPrice.getText();
            String size = Size.getText();
        try {    
            String s3 = "UPDATE SupplierItemDetails SET ItemCode = '"+Icode+"',ItemName = '"+IName+"',BrandID = '"+BID+"',BrandName = '"+BName+"',UnitPrice = '"+UPrice+"',Size = '"+size+"' WHERE (SupplierID = "+SID+" AND ItemCode = '"+Icode+"')";
            pst8 = conn1.prepareStatement(s3);
            pst8.execute();
            LoadingSupplierItemDetailsTable();
            
            ClearSupplierItemDetails();
            
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                try {
                    pst8.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ItemCode.setEditable(true);
        
        //deleting the record
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete this record?");

        if(x==0)

        {

            int SID = Integer.parseInt(LabelSID.getText());
            String Icode = ItemCode.getText();

            try {
                String s4 = "DELETE FROM SupplierItemDetails WHERE (SupplierID = "+SID+" AND ItemCode = '"+Icode+"')";
                
                pst9 = conn1.prepareStatement(s4);
                pst9.execute();
               

                //load the table after deleting the record
                LoadingSupplierItemDetailsTable();
                
                ClearSupplierItemDetails();
                
                }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
                JOptionPane.showMessageDialog(null,"Error in deleting the record!","Error!", JOptionPane.INFORMATION_MESSAGE);
            }finally{
                try {
                    pst9.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        ItemCode.setEditable(false);
        
        int row = jTable2.getSelectedRow();
        
        String Icode=jTable2.getValueAt(row,0).toString();
        String IName=jTable2.getValueAt(row,1).toString();
        String BID=jTable2.getValueAt(row,2).toString();
        String BName=jTable2.getValueAt(row,3).toString();
        String UPrice = jTable2.getValueAt(row,4).toString();
        String size = jTable2.getValueAt(row,5).toString();
        
        ItemCode.setText(Icode);
        ItemName.setText(IName);
        BrandID.setText(BID);
        BrandName.setText(BName);
        UnitPrice.setText(UPrice);
        Size.setText(size);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ClearSupplierItemDetails();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void TextNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNameKeyTyped
        
    }//GEN-LAST:event_TextNameKeyTyped

    private void TextPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPhoneKeyTyped
        // TODO add your handling code here:
        
        char c=evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null,"Enter only Numbers");
        }
        
        String check = TextPhone.getText()+c;
        
        if(check.length()>10){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Only ten numbers are allowed!");
        }
        
        /*String str = TextPhone.getText();
        if(str.length()==10){
            for(int i=0;i<10;i++){
                if(Character.isLetter(str.charAt(i)))
                    JOptionPane.showMessageDialog(null,"Please Enter a Valid Telephone Number. (Enter digits only)!","Error!", JOptionPane.INFORMATION_MESSAGE);
            } 
        }
        else
            JOptionPane.showMessageDialog(null,"Please Enter a Valid Telephone Number with exact 10 digits!","Error!", JOptionPane.INFORMATION_MESSAGE);*/
    }//GEN-LAST:event_TextPhoneKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JCheckBox BathroomCheckBox;
    private javax.swing.JTextField BrandID;
    private javax.swing.JTextField BrandName;
    private javax.swing.JButton ButtonItemDetails;
    private javax.swing.JCheckBox CementCheckBox;
    private javax.swing.JCheckBox ChemicalsCheckBox;
    private javax.swing.JCheckBox ConstructionCheckBox;
    private javax.swing.JCheckBox FertilizerCheckBox;
    private javax.swing.JTextField ItemCode;
    private javax.swing.JTextField ItemName;
    private javax.swing.JLabel LabelSID;
    private javax.swing.JCheckBox OtherCheckBox;
    private javax.swing.JCheckBox PaintCheckBox;
    private javax.swing.JCheckBox RoofingCheckBox;
    private javax.swing.JTextField Size;
    private javax.swing.JCheckBox TankCheckBox;
    private javax.swing.JTextArea TextAddress;
    private javax.swing.JTextField TextID;
    private javax.swing.JTextField TextName;
    private javax.swing.JTextField TextPhone;
    private javax.swing.JTextField UnitPrice;
    private javax.swing.JCheckBox WaterCheckBox;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
