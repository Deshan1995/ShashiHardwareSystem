package Inventory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damith
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import DBconnection.DBconnect;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;

public class Stock extends javax.swing.JInternalFrame {
  Connection conn=null;
  ResultSet rs = null;
  PreparedStatement pst = null;
  PreparedStatement pst1 = null;
  Statement pst2 = null;
 
  String expDate = null,itemId=null,itemName=null,manudate=null,qty=null,brandID=null,brandName=null,supplierID=null,supplierName=null;
  String PexpDate = null,PitemID = null,Pmanudate = null,Pqty=null;
    public Stock() {
        initComponents();
        lowQty();
        conn = DBconnect.connectDb();
        
        CSizeManu.disable();
        CSizeSelect.disable();
      
        CLengthManu.disable();
        CLengthSelect.disable();
        
        RSizeManu.disable();
        RSizeSelect.disable();
        
        RColourManu.disable();
        RColourSelect.disable();
        
        CItemID.setEditable(false);
        PItemID.setEditable(false);
        RItemID.setEditable(false);
        WItemID.setEditable(false);
        CFItemID.setEditable(false);
        OItemID.setEditable(false);
        
        calendar();
        Notification();
        Notification1();
        Notify();
        Notify1();
    
        
        tableloadConstuction1();
        tableloadPaint1();
        tableloadRoofing1();
        tableloadWater1();
        tableloadChemical1();
        tableloadOther1();
    }
    
    public void tableloadConstuction1()
    {
        String Construction = "select * from Construction";
        try{
        pst = conn.prepareStatement(Construction);
        rs = pst.executeQuery();
        
        TableConstruction.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableloadConstuction2()
{
String Construction = "select * from Paint_Thinner";
        try{
        pst = conn.prepareStatement(Construction);
        rs = pst.executeQuery();
        
        TablePaint.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
}
    
    public void tableload01(String key)
    {
        
        String construction = "select * from Construction where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(construction);
        rs = pst.executeQuery();
        
        TableConstruction.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
    
public void tableloadPaint1()
    {
        String paint = "select * from Paint_Thinner";
        try{
        pst = conn.prepareStatement(paint);
        rs = pst.executeQuery();
        
        TablePaint.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableload02(String key)
    {
        
        String paint = "select * from Paint_Thinner where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(paint);
        rs = pst.executeQuery();
        
        TablePaint.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableloadRoofing1()
    {
        String roofing = "select * from Roofing_Fitting";
        try{
        pst = conn.prepareStatement(roofing);
        rs = pst.executeQuery();
        
        TableRoofing.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableload03(String key)
    {
        
        String roofing = "select * from Roofing_Fitting where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(roofing);
        rs = pst.executeQuery();
        
        TableRoofing.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableloadWater1()
    {
        String water = "select * from WaterPipe_Fitting";
        try{
        pst = conn.prepareStatement(water);
        rs = pst.executeQuery();
        
        TableWater.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableload04(String key)
    {
        
        String water = "select * from WaterPipe_Fitting where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(water);
        rs = pst.executeQuery();
        
        TableWater.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }

public void tableloadChemical1()
    {
        String Chemical = "select * from Chemical_Farming";
        try{
        pst = conn.prepareStatement(Chemical);
        rs = pst.executeQuery();
        
        TableChemical.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableload05(String key)
    {
        
        String chemical = "select * from Chemical_Farming where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(chemical);
        rs = pst.executeQuery();
        
        TableChemical.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableloadOther1()
    {
        String other = "select * from OtherItem";
        try{
        pst = conn.prepareStatement(other);
        rs = pst.executeQuery();
        
        TableOther.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
        finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void tableload06(String key)
    {
        
        String other = "select * from OtherItem where ItemID='"+key+"'";
        try{
        pst = conn.prepareStatement(other);
        rs = pst.executeQuery();
        
        TableOther.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
         {
                
         }
   finally
        {
            try{
            pst.close();
            rs.close();
            }
            catch(Exception e){
            
            }
                  
        }
       
    }
public void createConID()
{
char c[]={'C','N','T',' ',' ',' ',' ',' ',' ',' ',' ',' '};
       
       sizelength();
 
       String iname = CItemName.getText();
       c[3] = iname.charAt(0);
       c[4] = iname.charAt(1);
       String bname = CBrandName.getText();
       c[5] = bname.charAt(0);
       c[6] = bname.charAt(1);
       c[7] = size.charAt(0);
       c[8] = size.charAt(1);
       c[9] = length.charAt(0);
       c[10] = length.charAt(1);
       String b =new String(c);
        b = b.replace(" " , "");
        CItemID.setText(b);

}
public void createPntID()
{
 char c[]={'P','N','T',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        
       String cat = PCategory.getSelectedItem().toString();
       c[3] = cat.charAt(0);
       c[4] = cat.charAt(1);
       String bname = PBrandName.getSelectedItem().toString();
       c[5] = bname.charAt(0);
       c[6] = bname.charAt(1);
       String code = PColourCode.getText();
       c[10] = code.charAt(0);
       c[11] = code.charAt(1);
       String col = PCapacity.getSelectedItem().toString();
       c[7] = col.charAt(0);
       c[8] = col.charAt(1);
       c[9] = col.charAt(2);
        
       String b =new String(c);
        b = b.replace(" " , "");
        PItemID.setText(b);
}
public void createRofID()
{
  char c[]={'R','O','F',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        
       sizecolour();
       String cat = RCategory.getSelectedItem().toString();
       c[3] = cat.charAt(0);
       c[4] = cat.charAt(1);
       String type = RType.getSelectedItem().toString();
       c[5] = type.charAt(0);
       c[6] = type.charAt(1);
       String Bname = RBrandName.getText();
       c[7] = Bname.charAt(0);
       c[8] = Bname.charAt(1);
       c[9] = size1.charAt(0);
       c[10] = size1.charAt(1);
       c[11] = colour.charAt(0);
       c[12] = colour.charAt(1);
        
       String b =new String(c);
        b = b.replace(" " , "");
        RItemID.setText(b);
}
public void createWatID()
{
 char c[]={'W','T','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',};
 
       String iname = WItemName.getSelectedItem().toString();
       c[3] = iname.charAt(0);
       c[4] = iname.charAt(1); 
       String Bname = WBrandName.getSelectedItem().toString();
       c[5] = Bname.charAt(0);
       c[6] = Bname.charAt(1);
       String gauge = WGauge.getSelectedItem().toString();
       c[7] = gauge.charAt(0);
       c[8] = gauge.charAt(1); 
       String size2 = WSize.getSelectedItem().toString();
       c[9] = size2.charAt(0);
       c[10] = size2.charAt(1);
       c[11] = size2.charAt(2);
//       c[12] = size2.charAt(3);
//       c[13] = size2.charAt(4);
        
       String b =new String(c);
        b = b.replace(" " , "");
     //b = b.replace(, "");
        WItemID.setText(b);
}
public void createChemID()
{
char c[]={'C','H','E',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
 
       String iname = CFItemName.getText();
       c[3] = iname.charAt(0);
       c[4] = iname.charAt(1); 
       String Bname = CFBrandName.getText();
       c[5] = Bname.charAt(0);
       c[6] = Bname.charAt(1);
       
        
       String b =new String(c);
        b = b.replace(" " , "");
        CFItemID.setText(b);

}
public  void createOtrID()
{
char c[]={'O','T','R',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
    String iname = OItemName.getText();
    c[3] = iname.charAt(0);
    c[4] = iname.charAt(1);
    String Bname =OBrandName.getText();
    c[5] = Bname.charAt(0);
    c[6] = Bname.charAt(1);
    String colour = OColour.getText();
    c[7] = colour.charAt(0);
    c[8] = colour.charAt(1);
    String size = OSize.getText();
    c[9] = colour.charAt(0);
    c[10] = colour.charAt(1);
    
    String b = new String(c);
    b = b.replace(" " , "");
    OItemID.setText(b);
}

public void QtyUpdateConstruction(){
        String quan =null;
        String sql = null;
        String itemcode = CItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE Construction SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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
public void QtyUpdatePaint(){
        String quan =null;
        String sql = null;
        String itemcode = PItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE Paint_Thinner SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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
public void QtyUpdateRoofing(){
        String quan =null;
        String sql = null;
        String itemcode = RItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE Roofing_Fitting SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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
public void QtyUpdateWater(){
        String quan =null;
        String sql = null;
        String itemcode = WItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE WaterPipe_Fitting SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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
public void QtyUpdateChemical(){
        String quan =null;
        String sql = null;
        String itemcode = CFItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE Chemical_Farming SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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
public void QtyUpdateOther(){
        String quan =null;
        String sql = null;
        String itemcode = OItemID.getText(); 
          sql = "SELECT ItemID,SUM(Qty) FROM StockQty WHERE ItemID = '"+ itemcode +"' GROUP BY ItemID HAVING SUM(Qty)";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
                quan = rs.getString("SUM(Qty)");
                System.out.println(quan);          
            }         
            String q = "UPDATE OtherItem SET Qty='"+ quan +"' WHERE ItemID='"+ itemcode +"' ";
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


public void clearconstruction(){

            CItemID.setText("");
            CItemName.setText("");
            CBrandID.setText("");
            CBrandName.setText("");
            CSupplierID.setText("");
            CSupplierName.setText("");
            CPurchasingPrice.setText("");
            CSellingPrice.setText("");
            QConstruction.setText("");
            AQConstruction.setText("");
            CSizeSelect.setSelectedItem("Select");
            CSizeManu.setText("");
            buttonGroup1.clearSelection();
            buttonGroup4.clearSelection();
            CLengthSelect.setSelectedItem("Select");
            CLengthManu.setText("");
}

public void clearpaint(){

          PItemID.setText("");
          PCategory.setSelectedItem("Select");
          PBrandID.setText("");
          PBrandName.setSelectedItem("Select");
          PSupplierID.setText("");
          PSupplierName.setText("");
          PColourCode.setText("");
          PManufactureDate.setDate(null);
          PExpireDate.setDate(null);
          PLablePrice.setText("");
          PDiscount.setText("");
          PPurchingPrice.setText("");
          PSellingPrice.setText("");
          AQPaint.setText("");
          QPaint.setText("");
          PCapacity.setSelectedItem("Select");

}
public void clearRoofing(){
            RItemID.setText("");
            RCategory.setSelectedItem("Select");
            RType.setSelectedItem("Sheet");
            RBrandID.setText("");
            RBrandName.setText("");
            RSupplierID.setText("");
            RSupplierName.setText("");
            RSizeSelect.setSelectedItem("Select");
            RSizeManu.setText("");
            buttonGroup2.clearSelection();
            buttonGroup3.clearSelection();
            RColourSelect.setSelectedItem("Select");
            RColourManu.setText("");
            RPurchasingPrice.setText("");
            RSellingPrice.setText("");
            AQRoofing.setText("");
            QRoofing.setText("");
}
public void clearwater (){
        WItemID.setText("");
        WItemName.setSelectedItem("Select");
        WBrandID.setText("");
        WBrandName.setSelectedItem("Select");
        WSupplierID.setText("");
        WSupplierName.setText("");
        WPrice.setText("");
        WGauge.setSelectedItem("Select");
        WSellingPrice.setText("");
        WSize.setSelectedItem("Select");
        AQWater.setText("");
        QWater.setText("");

}
public void clearother(){
        OItemID.setText("");
        OItemName.setText("");
        OBrandID.setText("");
        OBrandName.setText("");
        OSupplierID.setText("");
        OSupplierName.setText("");
        OColour.setText("");
        OSize.setText("");
        OPurchasingPrice.setText("");
        OSellingPrice.setText("");
        AQOther.setText("");
        QOther.setText("");
}
public void clearchemical(){
        CFItemID.setText("");
        CFItemName.setText("");
        CFBrandID.setText("");
        CFBrandName.setText("");
        CFSupplierID.setText("");
        CFSupplierName.setText("");
        CFManufactureDate.setDate(null);
        CFExpireDate.setDate(null);
        CFPrice.setText("");
        CFSellingPrice.setText("");
        AQChemical.setText("");
        QChemical.setText("");
}
public void date(){
    
        Date d1=null;
        Date d2=null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            
            d1=format.parse(format.format(CFManufactureDate.getDate()));
            d2=format.parse(format.format(CFExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
           
            
                    
            if(diff<0){
           
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                PManufactureDate.setDate(null);
                PExpireDate.setDate(null);
            }
            
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e);
        }
}

int day=0,month=0,year=0,day1=0,month1=0,year1=0;
     
    public void calendar()
    { 
        Calendar cal=new GregorianCalendar();
        //year= cal.get(Calendar.YEAR);
        //month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        year=2017;
        month=11;
     
    
    }
     String length = null;
      String size = null;
    public void sizelength()
    {
    //String size = null;
        if(sselect.isSelected())
        {
            size = CSizeSelect.getSelectedItem().toString();
        }
        else if(smanu.isSelected())
        {
            size = CSizeManu.getText();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Size required");
        }

        //String length = null;
        if(CLSelect.isSelected())
        {
            length = CLengthSelect.getSelectedItem().toString();
        }
        else if(CLManu.isSelected())
        {
            length = CLengthManu.getText();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Length required");
        }
    
    }
String size1 = null;
String colour = null;

public void sizecolour()
    {
        //String size = null;
        if(RSSelect.isSelected())
        {
            size1 = RSizeSelect.getSelectedItem().toString();
        }
        else if(RSManu.isSelected())
        {
            size1 = RSizeManu.getText();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Size required");
        }

//        String colour = null;
        if(RCSelect.isSelected())
        {
            colour = RColourSelect.getSelectedItem().toString();
        }
        else if(RCManu.isSelected())
        {
            colour = RColourManu.getText();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"colour required");
        }
    }
public void Notification(){
    
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;
            

	try{
	    String sql="Select ItemId,ExpireDate,ManufactureDate,Qty from Chemical_Farming ";
            
            pst=conn.prepareStatement(sql);
            
            rs=pst.executeQuery();
               

		while(rs.next()){
                    itemId = rs.getString("ItemId");
                    expDate = rs.getString("ExpireDate");
                    manudate = rs.getString("ManufactureDate");
                    qty = rs.getString("Qty");

                  d1 = format.parse(expDate);
              
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
                   
                     
                    
                    if(month==(month1-6)) {
                      //  JOptionPane.showMessageDialog(rootPane, "The product is going to be expired in another 6 months", "Notify", JOptionPane.WARNING_MESSAGE);
                        try{
                        String sql1="Insert into Nearto_Be_Expired(ItemId,ManufactureDate,ExpireDate,Quantity) values(?,?,?,?)";
           
                        pst=conn.prepareStatement(sql1);
            
                        pst.setString(1,itemId);  
                        pst.setString(2,manudate);
                        pst.setString(3,expDate);
                        pst.setString(4,qty);

                            pst.execute();
            
            
            
                            }catch(Exception e1){
                 
                                if(e1.toString().contains("java.sql.SQLException: column itemId is not unique") ){                   
                                    System.out.println(e1);
                                }else{
                                    System.out.println(e1);
                                    //JOptionPane.showMessageDialog(null,e1);
                                            
                                }                
                            }
                    } 
                    
	}}catch(Exception e){
            JOptionPane.showMessageDialog(null,6);
            
        }
        finally{
            try{
                pst.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
}
public void Notification1(){
    
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;
            

	try{
	    String sq2="Select ItemID,ExpireDate,ManufactureDate,Qty from Paint_Thinner ";
            
            pst=conn.prepareStatement(sq2);
            
            rs=pst.executeQuery();
               

		while(rs.next()){
                    PitemID = rs.getString("ItemID");
                    PexpDate = rs.getString("ExpireDate");
                    Pmanudate = rs.getString("ManufactureDate");
                    Pqty = rs.getString("Qty");
                    
                     
                    d1 = format.parse(PexpDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
      
                    if(month==(month1-6)) {
                       // JOptionPane.showMessageDialog(rootPane, "The product is going to be expired in another 6 months", "Notify", JOptionPane.WARNING_MESSAGE);
                        try{
                        String sql1="Insert into Nearto_Be_Expired(ItemId,ManufactureDate,ExpireDate,Quantity) values(?,?,?,?)";
           
                        pst=conn.prepareStatement(sql1);
            
                        pst.setString(1,PitemID);
                        pst.setString(2,PexpDate);
                        pst.setString(3,Pmanudate);
                        pst.setString(4,Pqty);

                            pst.execute();
            
            
            
                            }catch(Exception e1){
                 
                                if(e1.toString().contains("java.sql.SQLException: column itemId is not unique") ){                   
                                    System.out.println(e1);
                                }else{
                                    System.out.println(e1);
                                    //JOptionPane.showMessageDialog(null,e1);
                                            
                                }                
                            }
                    } 
                    
	}}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
        finally{
            try{
                pst.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
}

public void Notify(){
    
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;
            

	try{
	    String sql="Select ItemId,ItemName,BrandID,BrandName,SupplierID,SupplierName,ExpireDate from Chemical_Farming ";
           
            pst=conn.prepareStatement(sql);
            
            rs=pst.executeQuery();
               

		while(rs.next()){
                    itemId = rs.getString("ItemId");
                    itemName=rs.getString("ItemName");
                    brandID=rs.getString("BrandID");
                    brandName=rs.getString("BrandName");
                    supplierID=rs.getString("SupplierID");
                    supplierName=rs.getString("SupplierName");
                    expDate = rs.getString("ExpireDate");
//                    manudate = rs.getString("ManufactureDate");
//                    qty = rs.getString("Qty");
                    
                    
                    

                  d1 = format.parse(expDate);
              
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
                   
                     
                    
                    if(month==month1) {
                      //  JOptionPane.showMessageDialog(rootPane, "The product is going to be expired in another 6 months", "Notify", JOptionPane.WARNING_MESSAGE);
                        try{
                        String sql1="Insert into Expired_Item(ItemID,ItemName,BID,BName,SID,SName) values(?,?,?,?,?,?)";
           
                        pst=conn.prepareStatement(sql1);
            
                        pst.setString(1,itemId);  
                        pst.setString(2,itemName);  
                        pst.setString(3,brandID);  
                        pst.setString(4,brandName);  
                        pst.setString(5,supplierID);  
                        pst.setString(6,supplierName);  
                        

                            pst.execute();
            
            
            
                            }catch(Exception e1){
                 
                                if(e1.toString().contains("java.sql.SQLException: column itemId is not unique") ){                   
                                    System.out.println(e1);
                                }else{
                                    System.out.println(e1);
                                    //JOptionPane.showMessageDialog(null,e1);
                                            
                                }                
                            }
                    } 
                    
	}}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
        finally{
            try{
                pst.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
}

public void Notify1(){
    
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dy = new SimpleDateFormat("yyyy");
            SimpleDateFormat dm = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");

            java.util.Date d1 = null;
            

	try{
	    String sq2="Select ItemID,Catergory,BrandID,BrandName,SupplierID,SupplierName,ExpireDate from Paint_Thinner ";
            
            pst=conn.prepareStatement(sq2);
            
            rs=pst.executeQuery();
               

		while(rs.next()){
                    itemId = rs.getString("ItemId");
                    itemName=rs.getString("Catergory");
                    brandID=rs.getString("BrandID");
                    brandName=rs.getString("BrandName");
                    supplierID=rs.getString("SupplierID");
                    supplierName=rs.getString("SupplierName");
                    expDate = rs.getString("ExpireDate");
                    
                     
                    d1 = format.parse(PexpDate);
                    year1 = Integer.parseInt(dy.format(d1));
                    month1 = Integer.parseInt(dm.format(d1));
                    day1= Integer.parseInt(dd.format(d1));
      
                    if(month==month1) {
                       // JOptionPane.showMessageDialog(rootPane, "The product is going to be expired in another 6 months", "Notify", JOptionPane.WARNING_MESSAGE);
                        try{
                        String sql1="Insert into Expired_Item(ItemID,ItemName,BID,BName,SID,SName) values(?,?,?,?,?,?)";
           
                        pst=conn.prepareStatement(sql1);
            
                        pst.setString(1,itemId);  
                        pst.setString(2,itemName);  
                        pst.setString(3,brandID);  
                        pst.setString(4,brandName);  
                        pst.setString(5,supplierID);  
                        pst.setString(6,supplierName);  
                        

                            pst.execute();
            
            
            
                            }catch(Exception e1){
                 
                                if(e1.toString().contains("java.sql.SQLException: column itemId is not unique") ){                   
                                    System.out.println(e1);
                                }else{
                                    System.out.println(e1);
                                    //JOptionPane.showMessageDialog(null,e1);
                                            
                                }                
                            }
                    } 
                    
	}}catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
        finally{
            try{
                pst.close();
                rs.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
}
String itemid,quan;

public void lowQty(){
double qty=0;
	

 try {
	String sql = "SELECT ItemID,SUM(Qty) FROM StockQty GROUP BY ItemID HAVING SUM(Qty)";
       
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){ 
		itemid = rs.getString("ItemID");
    		quan = rs.getString("SUM(Qty)");
                qty=Double.parseDouble(quan); 
                
                System.out.println(itemid);
                System.out.println(quan);
           
	

	if(Double.compare(qty,10.0)<0){
		String sql1 = "Insert into lowQty(ItemId,Quantity) values('"+ itemid +"','"+ quan +"')";
		pst = conn.prepareStatement(sql1);
            	pst.execute();
	}
            }
}
catch(Exception e){
	   System.out.println(e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CBrandID = new javax.swing.JTextField();
        CSupplierID = new javax.swing.JTextField();
        CPurchasingPrice = new javax.swing.JTextField();
        CSellingPrice = new javax.swing.JTextField();
        CBrandName = new javax.swing.JTextField();
        CSupplierName = new javax.swing.JTextField();
        CItemID = new javax.swing.JTextField();
        CItemName = new javax.swing.JTextField();
        SaveConstruction = new javax.swing.JButton();
        UpdateConstruction = new javax.swing.JButton();
        DeleteConstruction = new javax.swing.JButton();
        ClearConstruction = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableConstruction = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        sselect = new javax.swing.JRadioButton();
        smanu = new javax.swing.JRadioButton();
        CSizeSelect = new javax.swing.JComboBox();
        CSizeManu = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        CLSelect = new javax.swing.JRadioButton();
        CLManu = new javax.swing.JRadioButton();
        CLengthSelect = new javax.swing.JComboBox();
        CLengthManu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        AQConstruction = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        QConstruction = new javax.swing.JTextField();
        UpdateQtyConstruction = new javax.swing.JButton();
        ConstructionID = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        SearchConID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        PSupplierName = new javax.swing.JTextField();
        PColourCode = new javax.swing.JTextField();
        PLablePrice = new javax.swing.JTextField();
        PSellingPrice = new javax.swing.JTextField();
        SavePaint = new javax.swing.JButton();
        UpdatePaint = new javax.swing.JButton();
        DelatePaint = new javax.swing.JButton();
        ClearPaint = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        PCapacity = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        PPurchingPrice = new javax.swing.JTextField();
        PDiscount = new javax.swing.JTextField();
        PCategory = new javax.swing.JComboBox();
        PBrandName = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        PBrandID = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        PSupplierID = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablePaint = new javax.swing.JTable();
        PManufactureDate = new com.toedter.calendar.JDateChooser();
        PExpireDate = new com.toedter.calendar.JDateChooser();
        PaintID = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        AQPaint = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        QPaint = new javax.swing.JTextField();
        UpdateQtyPaint = new javax.swing.JButton();
        PItemID = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        SearchPntID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        RBrandID = new javax.swing.JTextField();
        RSupplierID = new javax.swing.JTextField();
        RPurchasingPrice = new javax.swing.JTextField();
        RSellingPrice = new javax.swing.JTextField();
        RBrandName = new javax.swing.JTextField();
        RSupplierName = new javax.swing.JTextField();
        RItemID = new javax.swing.JTextField();
        RoofingSave = new javax.swing.JButton();
        UpdateRoofing = new javax.swing.JButton();
        RoofingDelete = new javax.swing.JButton();
        RoofingClear = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableRoofing = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        RSizeSelect = new javax.swing.JComboBox();
        RColourSelect = new javax.swing.JComboBox();
        RSSelect = new javax.swing.JRadioButton();
        RSizeManu = new javax.swing.JTextField();
        RSManu = new javax.swing.JRadioButton();
        RColourManu = new javax.swing.JTextField();
        RCSelect = new javax.swing.JRadioButton();
        RCManu = new javax.swing.JRadioButton();
        jLabel67 = new javax.swing.JLabel();
        RCategory = new javax.swing.JComboBox();
        RType = new javax.swing.JComboBox();
        RoofingID = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        AQRoofing = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        QRoofing = new javax.swing.JTextField();
        UpdateQtyRoofing = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        SearchRofID = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        WBrandID = new javax.swing.JTextField();
        WSupplierID = new javax.swing.JTextField();
        WPrice = new javax.swing.JTextField();
        WSellingPrice = new javax.swing.JTextField();
        WSupplierName = new javax.swing.JTextField();
        WItemID = new javax.swing.JTextField();
        SaveWater = new javax.swing.JButton();
        UpdateWater = new javax.swing.JButton();
        DeleteWater = new javax.swing.JButton();
        ClrearWater = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableWater = new javax.swing.JTable();
        WBrandName = new javax.swing.JComboBox();
        WItemName = new javax.swing.JComboBox();
        jLabel70 = new javax.swing.JLabel();
        WGauge = new javax.swing.JComboBox();
        jLabel71 = new javax.swing.JLabel();
        WSize = new javax.swing.JComboBox();
        WaterID = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        AQWater = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        QWater = new javax.swing.JTextField();
        UpdateQtyWater = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        SearchWatID = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        CFBrandID = new javax.swing.JTextField();
        CFSupplierID = new javax.swing.JTextField();
        CFPrice = new javax.swing.JTextField();
        CFSellingPrice = new javax.swing.JTextField();
        CFBrandName = new javax.swing.JTextField();
        CFSupplierName = new javax.swing.JTextField();
        CFItemID = new javax.swing.JTextField();
        CFItemName = new javax.swing.JTextField();
        ChemicalSave = new javax.swing.JButton();
        UpdateChemical = new javax.swing.JButton();
        DeleteChemical = new javax.swing.JButton();
        ChemicalClear = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableChemical = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        CFManufactureDate = new com.toedter.calendar.JDateChooser();
        CFExpireDate = new com.toedter.calendar.JDateChooser();
        ChemicalID = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        AQChemical = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        QChemical = new javax.swing.JTextField();
        UpdateQtyChemical = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        SearchCheID = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        OBrandID = new javax.swing.JTextField();
        OSupplierID = new javax.swing.JTextField();
        OSellingPrice = new javax.swing.JTextField();
        OItemID = new javax.swing.JTextField();
        OtherSave = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        DeleteOther = new javax.swing.JButton();
        OtherClear = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableOther = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        OItemName = new javax.swing.JTextField();
        OBrandName = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        OSupplierName = new javax.swing.JTextField();
        OPurchasingPrice = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        OSize = new javax.swing.JTextField();
        OColour = new javax.swing.JTextField();
        OtherID = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        AQOther = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        QOther = new javax.swing.JTextField();
        UpdateQtyOther = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        SearchOthID5 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1601, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1601, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Brand ID");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 80, 107, 27);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Item ID");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 40, 107, 27);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Brand Name");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(450, 80, 107, 27);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Item Name");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(450, 40, 107, 27);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Supplier ID");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(30, 120, 107, 27);
        jLabel5.getAccessibleContext().setAccessibleDescription("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Supplier Name");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(450, 120, 107, 27);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Purchasing Price");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 160, 140, 27);

        CBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBrandIDActionPerformed(evt);
            }
        });
        CBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBrandIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CBrandIDKeyReleased(evt);
            }
        });
        jPanel2.add(CBrandID);
        CBrandID.setBounds(169, 80, 200, 30);

        CSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSupplierIDActionPerformed(evt);
            }
        });
        CSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CSupplierIDKeyPressed(evt);
            }
        });
        jPanel2.add(CSupplierID);
        CSupplierID.setBounds(169, 120, 200, 30);
        CSupplierID.getAccessibleContext().setAccessibleDescription("");

        CPurchasingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPurchasingPriceActionPerformed(evt);
            }
        });
        CPurchasingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CPurchasingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CPurchasingPriceKeyTyped(evt);
            }
        });
        jPanel2.add(CPurchasingPrice);
        CPurchasingPrice.setBounds(169, 160, 200, 30);

        CSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSellingPriceActionPerformed(evt);
            }
        });
        CSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CSellingPriceKeyTyped(evt);
            }
        });
        jPanel2.add(CSellingPrice);
        CSellingPrice.setBounds(169, 200, 200, 30);

        CBrandName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBrandNameActionPerformed(evt);
            }
        });
        CBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBrandNameKeyPressed(evt);
            }
        });
        jPanel2.add(CBrandName);
        CBrandName.setBounds(600, 80, 200, 30);

        CSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSupplierNameActionPerformed(evt);
            }
        });
        CSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CSupplierNameKeyPressed(evt);
            }
        });
        jPanel2.add(CSupplierName);
        CSupplierName.setBounds(600, 120, 200, 30);

        CItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CItemIDActionPerformed(evt);
            }
        });
        CItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CItemIDKeyReleased(evt);
            }
        });
        jPanel2.add(CItemID);
        CItemID.setBounds(170, 40, 160, 30);

        CItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CItemNameActionPerformed(evt);
            }
        });
        CItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CItemNameKeyPressed(evt);
            }
        });
        jPanel2.add(CItemName);
        CItemName.setBounds(600, 40, 200, 30);

        SaveConstruction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SaveConstruction.setText("Save");
        SaveConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveConstructionActionPerformed(evt);
            }
        });
        jPanel2.add(SaveConstruction);
        SaveConstruction.setBounds(450, 320, 140, 30);

        UpdateConstruction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateConstruction.setText("Update");
        UpdateConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateConstructionActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateConstruction);
        UpdateConstruction.setBounds(900, 40, 140, 30);

        DeleteConstruction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteConstruction.setText("Delete");
        DeleteConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteConstructionActionPerformed(evt);
            }
        });
        jPanel2.add(DeleteConstruction);
        DeleteConstruction.setBounds(900, 80, 140, 30);

        ClearConstruction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ClearConstruction.setText("Clear");
        ClearConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearConstructionActionPerformed(evt);
            }
        });
        jPanel2.add(ClearConstruction);
        ClearConstruction.setBounds(660, 320, 140, 30);

        TableConstruction.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableConstruction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Item name", "Brand ID", "Brand Name", "Supplier ID", "Supplier Name", "Price", "Selling Price", "Quantity"
            }
        ));
        TableConstruction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableConstructionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableConstruction);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 480, 1630, 580);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("size");
        jPanel2.add(jLabel65);
        jLabel65.setBounds(450, 160, 72, 27);

        buttonGroup1.add(sselect);
        sselect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sselectMouseClicked(evt);
            }
        });
        sselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sselectActionPerformed(evt);
            }
        });
        jPanel2.add(sselect);
        sselect.setBounds(560, 160, 25, 25);

        buttonGroup1.add(smanu);
        smanu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                smanuMouseClicked(evt);
            }
        });
        smanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smanuActionPerformed(evt);
            }
        });
        jPanel2.add(smanu);
        smanu.setBounds(560, 200, 25, 25);

        CSizeSelect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CSizeSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "   None", "8MM", "10MM", "12MM", "16MM", "20MM" }));
        CSizeSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSizeSelectActionPerformed(evt);
            }
        });
        jPanel2.add(CSizeSelect);
        CSizeSelect.setBounds(600, 160, 200, 30);

        CSizeManu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSizeManuActionPerformed(evt);
            }
        });
        jPanel2.add(CSizeManu);
        CSizeManu.setBounds(600, 200, 200, 30);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Length");
        jPanel2.add(jLabel66);
        jLabel66.setBounds(450, 240, 72, 27);

        buttonGroup4.add(CLSelect);
        CLSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CLSelectMouseClicked(evt);
            }
        });
        CLSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLSelectActionPerformed(evt);
            }
        });
        jPanel2.add(CLSelect);
        CLSelect.setBounds(560, 240, 25, 25);

        buttonGroup4.add(CLManu);
        CLManu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CLManuMouseClicked(evt);
            }
        });
        CLManu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLManuActionPerformed(evt);
            }
        });
        jPanel2.add(CLManu);
        CLManu.setBounds(560, 280, 25, 25);

        CLengthSelect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CLengthSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "   None", "12f", "19f", "20f" }));
        jPanel2.add(CLengthSelect);
        CLengthSelect.setBounds(600, 240, 200, 30);

        CLengthManu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLengthManuActionPerformed(evt);
            }
        });
        jPanel2.add(CLengthManu);
        CLengthManu.setBounds(600, 280, 200, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Selling price");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(30, 200, 107, 27);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel14.setLayout(null);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Available Quantity");
        jPanel14.add(jLabel52);
        jLabel52.setBounds(20, 60, 163, 24);

        AQConstruction.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQConstructionActionPerformed(evt);
            }
        });
        AQConstruction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AQConstructionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AQConstructionKeyTyped(evt);
            }
        });
        jPanel14.add(AQConstruction);
        AQConstruction.setBounds(230, 60, 150, 30);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Quantity");
        jPanel14.add(jLabel72);
        jLabel72.setBounds(20, 110, 110, 16);

        QConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QConstructionActionPerformed(evt);
            }
        });
        QConstruction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QConstructionKeyTyped(evt);
            }
        });
        jPanel14.add(QConstruction);
        QConstruction.setBounds(230, 100, 150, 30);

        UpdateQtyConstruction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyConstruction.setText("Update Quantity");
        UpdateQtyConstruction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyConstructionMouseClicked(evt);
            }
        });
        UpdateQtyConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyConstructionActionPerformed(evt);
            }
        });
        UpdateQtyConstruction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyConstructionKeyReleased(evt);
            }
        });
        jPanel14.add(UpdateQtyConstruction);
        UpdateQtyConstruction.setBounds(230, 150, 150, 25);

        jPanel2.add(jPanel14);
        jPanel14.setBounds(1120, 40, 390, 190);

        ConstructionID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConstructionIDActionPerformed(evt);
            }
        });
        jPanel2.add(ConstructionID);
        ConstructionID.setBounds(330, 40, 40, 30);

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel15.setLayout(null);

        SearchConID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchConIDKeyReleased(evt);
            }
        });
        jPanel15.add(SearchConID);
        SearchConID.setBounds(1, 1, 138, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Search by Item ID");
        jPanel15.add(jLabel8);
        jLabel8.setBounds(7, 40, 130, 30);

        jPanel2.add(jPanel15);
        jPanel15.setBounds(900, 150, 140, 80);

        jTabbedPane1.addTab("Construction", jPanel2);
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Supplier Name");
        jPanel8.add(jLabel10);
        jLabel10.setBounds(450, 120, 107, 27);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Item ID");
        jPanel8.add(jLabel11);
        jLabel11.setBounds(30, 40, 107, 27);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Brand Name");
        jPanel8.add(jLabel12);
        jLabel12.setBounds(450, 80, 107, 27);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Category");
        jPanel8.add(jLabel13);
        jLabel13.setBounds(450, 40, 107, 27);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Colour Code");
        jPanel8.add(jLabel14);
        jLabel14.setBounds(30, 160, 107, 27);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Lable Price");
        jPanel8.add(jLabel16);
        jLabel16.setBounds(30, 240, 107, 27);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Selling price");
        jPanel8.add(jLabel17);
        jLabel17.setBounds(30, 320, 107, 27);

        PSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSupplierNameActionPerformed(evt);
            }
        });
        PSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSupplierNameKeyPressed(evt);
            }
        });
        jPanel8.add(PSupplierName);
        PSupplierName.setBounds(600, 120, 200, 30);

        PColourCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PColourCodeActionPerformed(evt);
            }
        });
        PColourCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PColourCodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PColourCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PColourCodeKeyTyped(evt);
            }
        });
        jPanel8.add(PColourCode);
        PColourCode.setBounds(170, 160, 200, 30);

        PLablePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PLablePriceActionPerformed(evt);
            }
        });
        PLablePrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PLablePriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PLablePriceKeyTyped(evt);
            }
        });
        jPanel8.add(PLablePrice);
        PLablePrice.setBounds(170, 240, 200, 30);

        PSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSellingPriceActionPerformed(evt);
            }
        });
        PSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSellingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PSellingPriceKeyTyped(evt);
            }
        });
        jPanel8.add(PSellingPrice);
        PSellingPrice.setBounds(170, 320, 200, 30);

        SavePaint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SavePaint.setText("Save");
        SavePaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavePaintActionPerformed(evt);
            }
        });
        jPanel8.add(SavePaint);
        SavePaint.setBounds(450, 360, 140, 30);

        UpdatePaint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdatePaint.setText("Update");
        UpdatePaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePaintActionPerformed(evt);
            }
        });
        jPanel8.add(UpdatePaint);
        UpdatePaint.setBounds(900, 40, 140, 30);

        DelatePaint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DelatePaint.setText("Delete");
        DelatePaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelatePaintActionPerformed(evt);
            }
        });
        jPanel8.add(DelatePaint);
        DelatePaint.setBounds(900, 80, 140, 30);

        ClearPaint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ClearPaint.setText("Clear");
        ClearPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearPaintActionPerformed(evt);
            }
        });
        jPanel8.add(ClearPaint);
        ClearPaint.setBounds(660, 360, 140, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Capacity");
        jPanel8.add(jLabel19);
        jLabel19.setBounds(30, 200, 107, 27);

        PCapacity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PCapacity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "QB =Quarter Bottle", "HB =Half Bottle", "BO =Bottle", "50 =50ML", "100=100ML", "200=200ML", "500=500ML", "1  =1L", "2  =2L", "4  =4L", "10 =10L", "20 =20L", "1 =1Bag", "1.7=1.7Kg", "4  =4Kg", "14 =14Kg", "28 =28Kg" }));
        PCapacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PCapacityKeyPressed(evt);
            }
        });
        jPanel8.add(PCapacity);
        PCapacity.setBounds(170, 200, 200, 30);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Discount");
        jPanel8.add(jLabel20);
        jLabel20.setBounds(30, 280, 107, 26);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Purchasing price");
        jPanel8.add(jLabel21);
        jLabel21.setBounds(450, 280, 130, 27);

        PPurchingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPurchingPriceActionPerformed(evt);
            }
        });
        PPurchingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PPurchingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PPurchingPriceKeyTyped(evt);
            }
        });
        jPanel8.add(PPurchingPrice);
        PPurchingPrice.setBounds(600, 280, 200, 30);

        PDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDiscountActionPerformed(evt);
            }
        });
        PDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PDiscountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PDiscountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PDiscountKeyTyped(evt);
            }
        });
        jPanel8.add(PDiscount);
        PDiscount.setBounds(170, 280, 200, 30);

        PCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Emulsion", "Enamal", "Weathercote", "Anticross", "Varnish", "Sealer", "Water Base", "Filler", "Wallcote(Putty)", "Wood Finish", "Thinner" }));
        PCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PCategoryKeyPressed(evt);
            }
        });
        jPanel8.add(PCategory);
        PCategory.setBounds(600, 40, 200, 30);

        PBrandName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PBrandName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Robbiac", "Courseway", "Nippolac", "Dulux", "Jat", "Sachee", "Superlac", "Duco" }));
        PBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PBrandNameKeyPressed(evt);
            }
        });
        jPanel8.add(PBrandName);
        PBrandName.setBounds(600, 80, 200, 30);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("Brand ID");
        jPanel8.add(jLabel68);
        jLabel68.setBounds(30, 80, 107, 27);

        PBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBrandIDActionPerformed(evt);
            }
        });
        PBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PBrandIDKeyPressed(evt);
            }
        });
        jPanel8.add(PBrandID);
        PBrandID.setBounds(170, 80, 200, 30);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Supplier ID");
        jPanel8.add(jLabel69);
        jLabel69.setBounds(30, 120, 107, 27);

        PSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSupplierIDActionPerformed(evt);
            }
        });
        PSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSupplierIDKeyPressed(evt);
            }
        });
        jPanel8.add(PSupplierID);
        PSupplierID.setBounds(170, 120, 200, 30);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Manufacture Date");
        jPanel8.add(jLabel62);
        jLabel62.setBounds(450, 160, 128, 27);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Expire Date");
        jPanel8.add(jLabel73);
        jLabel73.setBounds(450, 200, 128, 27);

        TablePaint.setModel(new javax.swing.table.DefaultTableModel(
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
        TablePaint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePaintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TablePaintMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(TablePaint);

        jPanel8.add(jScrollPane2);
        jScrollPane2.setBounds(0, 480, 1630, 550);

        PManufactureDate.setDateFormatString("yyyy-MM-dd");
        PManufactureDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PManufactureDateKeyPressed(evt);
            }
        });
        jPanel8.add(PManufactureDate);
        PManufactureDate.setBounds(600, 160, 200, 30);

        PExpireDate.setDateFormatString("yyyy-MM-dd");
        PExpireDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PExpireDateKeyPressed(evt);
            }
        });
        jPanel8.add(PExpireDate);
        PExpireDate.setBounds(600, 200, 200, 30);

        PaintID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaintIDActionPerformed(evt);
            }
        });
        jPanel8.add(PaintID);
        PaintID.setBounds(330, 40, 40, 30);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel16.setLayout(null);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("Available Quantity");
        jPanel16.add(jLabel74);
        jLabel74.setBounds(20, 60, 163, 24);

        AQPaint.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQPaintActionPerformed(evt);
            }
        });
        jPanel16.add(AQPaint);
        AQPaint.setBounds(230, 60, 150, 30);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setText("Quantity");
        jPanel16.add(jLabel77);
        jLabel77.setBounds(20, 110, 110, 16);

        QPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QPaintActionPerformed(evt);
            }
        });
        jPanel16.add(QPaint);
        QPaint.setBounds(230, 100, 150, 30);

        UpdateQtyPaint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyPaint.setText("Update Quantity");
        UpdateQtyPaint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyPaintMouseClicked(evt);
            }
        });
        UpdateQtyPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyPaintActionPerformed(evt);
            }
        });
        UpdateQtyPaint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyPaintKeyReleased(evt);
            }
        });
        jPanel16.add(UpdateQtyPaint);
        UpdateQtyPaint.setBounds(230, 150, 150, 25);

        jPanel8.add(jPanel16);
        jPanel16.setBounds(1120, 30, 390, 190);

        PItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PItemIDActionPerformed(evt);
            }
        });
        PItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PItemIDKeyReleased(evt);
            }
        });
        jPanel8.add(PItemID);
        PItemID.setBounds(170, 40, 160, 30);

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel21.setLayout(null);

        SearchPntID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchPntIDKeyReleased(evt);
            }
        });
        jPanel21.add(SearchPntID);
        SearchPntID.setBounds(1, 1, 138, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Search by Item ID");
        jPanel21.add(jLabel18);
        jLabel18.setBounds(7, 40, 130, 30);

        jPanel8.add(jPanel21);
        jPanel21.setBounds(900, 150, 140, 80);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1709, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 207, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Paint & Thinner", jPanel3);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Brand ID");
        jPanel9.add(jLabel22);
        jLabel22.setBounds(30, 120, 107, 17);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Item ID");
        jPanel9.add(jLabel23);
        jLabel23.setBounds(30, 40, 107, 27);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Brand Name");
        jPanel9.add(jLabel24);
        jLabel24.setBounds(450, 120, 107, 17);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Catergory");
        jPanel9.add(jLabel25);
        jLabel25.setBounds(450, 40, 90, 27);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Size");
        jPanel9.add(jLabel26);
        jLabel26.setBounds(30, 200, 107, 27);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Supplier Name");
        jPanel9.add(jLabel27);
        jLabel27.setBounds(450, 160, 107, 27);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Purchasing Price");
        jPanel9.add(jLabel28);
        jLabel28.setBounds(30, 360, 130, 27);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Selling price");
        jPanel9.add(jLabel29);
        jLabel29.setBounds(30, 400, 107, 27);

        RBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBrandIDActionPerformed(evt);
            }
        });
        RBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RBrandIDKeyPressed(evt);
            }
        });
        jPanel9.add(RBrandID);
        RBrandID.setBounds(170, 120, 200, 30);

        RSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSupplierIDActionPerformed(evt);
            }
        });
        RSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RSupplierIDKeyPressed(evt);
            }
        });
        jPanel9.add(RSupplierID);
        RSupplierID.setBounds(170, 160, 200, 30);

        RPurchasingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RPurchasingPriceActionPerformed(evt);
            }
        });
        RPurchasingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPurchasingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RPurchasingPriceKeyTyped(evt);
            }
        });
        jPanel9.add(RPurchasingPrice);
        RPurchasingPrice.setBounds(170, 360, 200, 30);

        RSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSellingPriceActionPerformed(evt);
            }
        });
        RSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RSellingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RSellingPriceKeyTyped(evt);
            }
        });
        jPanel9.add(RSellingPrice);
        RSellingPrice.setBounds(170, 400, 200, 30);

        RBrandName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBrandNameActionPerformed(evt);
            }
        });
        RBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RBrandNameKeyPressed(evt);
            }
        });
        jPanel9.add(RBrandName);
        RBrandName.setBounds(600, 120, 200, 30);

        RSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSupplierNameActionPerformed(evt);
            }
        });
        RSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RSupplierNameKeyPressed(evt);
            }
        });
        jPanel9.add(RSupplierName);
        RSupplierName.setBounds(600, 160, 200, 30);

        RItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RItemIDActionPerformed(evt);
            }
        });
        RItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RItemIDKeyReleased(evt);
            }
        });
        jPanel9.add(RItemID);
        RItemID.setBounds(170, 40, 160, 30);

        RoofingSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RoofingSave.setText("Save");
        RoofingSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoofingSaveActionPerformed(evt);
            }
        });
        jPanel9.add(RoofingSave);
        RoofingSave.setBounds(450, 440, 140, 30);

        UpdateRoofing.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateRoofing.setText("Update");
        UpdateRoofing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateRoofingActionPerformed(evt);
            }
        });
        jPanel9.add(UpdateRoofing);
        UpdateRoofing.setBounds(900, 40, 140, 30);

        RoofingDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RoofingDelete.setText("Delete");
        RoofingDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoofingDeleteActionPerformed(evt);
            }
        });
        jPanel9.add(RoofingDelete);
        RoofingDelete.setBounds(900, 80, 140, 30);

        RoofingClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RoofingClear.setText("Clear");
        RoofingClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoofingClearActionPerformed(evt);
            }
        });
        jPanel9.add(RoofingClear);
        RoofingClear.setBounds(660, 440, 140, 30);

        TableRoofing.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableRoofing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Item name", "Brand ID", "Brand Name", "Supplier ID", "Supplier Name", "Price", "Selling Price", "Quantity"
            }
        ));
        TableRoofing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRoofingMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableRoofing);

        jPanel9.add(jScrollPane3);
        jScrollPane3.setBounds(0, 480, 1630, 710);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Colour");
        jPanel9.add(jLabel31);
        jLabel31.setBounds(30, 280, 107, 27);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Supplier ID");
        jPanel9.add(jLabel32);
        jLabel32.setBounds(30, 160, 107, 27);

        RSizeSelect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RSizeSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "None", "1ft", "2ft", "4ft", "6ft", "8ft", "10ft", "12ft", "" }));
        jPanel9.add(RSizeSelect);
        RSizeSelect.setBounds(170, 200, 200, 30);

        RColourSelect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RColourSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "none", "Sky Blue", "Navy Blue", "Dark green", "Maroon", "Brown", " " }));
        jPanel9.add(RColourSelect);
        RColourSelect.setBounds(170, 280, 200, 30);

        buttonGroup2.add(RSSelect);
        RSSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RSSelectMouseClicked(evt);
            }
        });
        RSSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSSelectActionPerformed(evt);
            }
        });
        jPanel9.add(RSSelect);
        RSSelect.setBounds(140, 200, 25, 25);

        RSizeManu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSizeManuActionPerformed(evt);
            }
        });
        jPanel9.add(RSizeManu);
        RSizeManu.setBounds(170, 240, 200, 30);

        buttonGroup2.add(RSManu);
        RSManu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RSManuMouseClicked(evt);
            }
        });
        jPanel9.add(RSManu);
        RSManu.setBounds(140, 240, 25, 25);

        RColourManu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RColourManuActionPerformed(evt);
            }
        });
        jPanel9.add(RColourManu);
        RColourManu.setBounds(170, 320, 200, 30);

        buttonGroup3.add(RCSelect);
        RCSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RCSelectMouseClicked(evt);
            }
        });
        jPanel9.add(RCSelect);
        RCSelect.setBounds(140, 280, 25, 25);

        buttonGroup3.add(RCManu);
        RCManu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RCManuMouseClicked(evt);
            }
        });
        jPanel9.add(RCManu);
        RCManu.setBounds(140, 320, 25, 25);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Type");
        jPanel9.add(jLabel67);
        jLabel67.setBounds(450, 80, 82, 27);

        RCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Amano", "Asbastos", "PVC" }));
        RCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RCategoryKeyPressed(evt);
            }
        });
        jPanel9.add(RCategory);
        RCategory.setBounds(600, 40, 200, 30);

        RType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sheet", "Gutter", "Running Head", "Mitre Joiner", "Bracket (Inner)", "Bracket (Outer)", "End Cap", "Gutter Joiner", "Down Pipe", "Down Spot", "Down L-bow", "Down Pipe Joiner", "Down Pipe Bracket", "Gutter Box Connecter", " " }));
        RType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RTypeKeyPressed(evt);
            }
        });
        jPanel9.add(RType);
        RType.setBounds(600, 80, 200, 30);

        RoofingID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoofingIDActionPerformed(evt);
            }
        });
        jPanel9.add(RoofingID);
        RoofingID.setBounds(330, 40, 40, 30);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel17.setLayout(null);

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("Available Quantity");
        jPanel17.add(jLabel78);
        jLabel78.setBounds(20, 60, 163, 24);

        AQRoofing.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQRoofing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQRoofingActionPerformed(evt);
            }
        });
        AQRoofing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AQRoofingKeyTyped(evt);
            }
        });
        jPanel17.add(AQRoofing);
        AQRoofing.setBounds(230, 60, 150, 30);

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Quantity");
        jPanel17.add(jLabel79);
        jLabel79.setBounds(20, 110, 110, 16);

        QRoofing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QRoofingActionPerformed(evt);
            }
        });
        QRoofing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QRoofingKeyTyped(evt);
            }
        });
        jPanel17.add(QRoofing);
        QRoofing.setBounds(230, 100, 150, 30);

        UpdateQtyRoofing.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyRoofing.setText("Update Quantity");
        UpdateQtyRoofing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyRoofingMouseClicked(evt);
            }
        });
        UpdateQtyRoofing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyRoofingActionPerformed(evt);
            }
        });
        UpdateQtyRoofing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyRoofingKeyReleased(evt);
            }
        });
        jPanel17.add(UpdateQtyRoofing);
        UpdateQtyRoofing.setBounds(230, 150, 150, 25);

        jPanel9.add(jPanel17);
        jPanel17.setBounds(1120, 30, 390, 190);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel22.setLayout(null);

        SearchRofID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchRofIDKeyReleased(evt);
            }
        });
        jPanel22.add(SearchRofID);
        SearchRofID.setBounds(1, 1, 138, 30);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Search by Item ID");
        jPanel22.add(jLabel30);
        jLabel30.setBounds(7, 40, 130, 30);

        jPanel9.add(jPanel22);
        jPanel22.setBounds(900, 150, 140, 80);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1709, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Roofing & Fitting", jPanel5);

        jPanel4.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Brand ID");
        jPanel10.add(jLabel15);
        jLabel15.setBounds(30, 80, 107, 30);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Item ID");
        jPanel10.add(jLabel33);
        jLabel33.setBounds(30, 40, 107, 30);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Brand Name");
        jPanel10.add(jLabel34);
        jLabel34.setBounds(450, 80, 107, 27);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Item Name");
        jPanel10.add(jLabel35);
        jLabel35.setBounds(450, 40, 107, 27);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Supplier ID");
        jPanel10.add(jLabel36);
        jLabel36.setBounds(30, 120, 107, 30);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Supplier Name");
        jPanel10.add(jLabel37);
        jLabel37.setBounds(450, 120, 107, 27);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Price");
        jPanel10.add(jLabel38);
        jLabel38.setBounds(30, 160, 107, 30);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Selling price");
        jPanel10.add(jLabel39);
        jLabel39.setBounds(30, 200, 107, 30);

        WBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBrandIDActionPerformed(evt);
            }
        });
        WBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBrandIDKeyPressed(evt);
            }
        });
        jPanel10.add(WBrandID);
        WBrandID.setBounds(170, 80, 200, 30);

        WSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSupplierIDActionPerformed(evt);
            }
        });
        WSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WSupplierIDKeyPressed(evt);
            }
        });
        jPanel10.add(WSupplierID);
        WSupplierID.setBounds(170, 120, 200, 30);

        WPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WPriceActionPerformed(evt);
            }
        });
        WPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                WPriceKeyTyped(evt);
            }
        });
        jPanel10.add(WPrice);
        WPrice.setBounds(170, 160, 200, 30);

        WSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSellingPriceActionPerformed(evt);
            }
        });
        WSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WSellingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                WSellingPriceKeyTyped(evt);
            }
        });
        jPanel10.add(WSellingPrice);
        WSellingPrice.setBounds(170, 200, 200, 30);

        WSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSupplierNameActionPerformed(evt);
            }
        });
        WSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WSupplierNameKeyPressed(evt);
            }
        });
        jPanel10.add(WSupplierName);
        WSupplierName.setBounds(600, 120, 200, 30);

        WItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WItemIDActionPerformed(evt);
            }
        });
        WItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                WItemIDKeyReleased(evt);
            }
        });
        jPanel10.add(WItemID);
        WItemID.setBounds(170, 40, 160, 30);

        SaveWater.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SaveWater.setText("Save");
        SaveWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveWaterActionPerformed(evt);
            }
        });
        jPanel10.add(SaveWater);
        SaveWater.setBounds(450, 240, 140, 30);

        UpdateWater.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateWater.setText("Update");
        UpdateWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateWaterActionPerformed(evt);
            }
        });
        jPanel10.add(UpdateWater);
        UpdateWater.setBounds(900, 40, 140, 30);

        DeleteWater.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteWater.setText("Delete");
        DeleteWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteWaterActionPerformed(evt);
            }
        });
        jPanel10.add(DeleteWater);
        DeleteWater.setBounds(900, 80, 140, 30);

        ClrearWater.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ClrearWater.setText("Clear");
        ClrearWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClrearWaterActionPerformed(evt);
            }
        });
        jPanel10.add(ClrearWater);
        ClrearWater.setBounds(660, 240, 140, 30);

        TableWater.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableWater.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Item name", "Brand ID", "Brand Name", "Supplier ID", "Supplier Name", "Price", "Selling Price", "Quantity"
            }
        ));
        TableWater.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableWaterMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableWater);

        jPanel10.add(jScrollPane4);
        jScrollPane4.setBounds(0, 480, 1630, 660);

        WBrandName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        WBrandName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "S-lon", "Anton", "KTK", "Arpico", "Other" }));
        WBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WBrandNameKeyPressed(evt);
            }
        });
        jPanel10.add(WBrandName);
        WBrandName.setBounds(600, 80, 200, 30);

        WItemName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        WItemName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Pipe", "Elbow", "Socket", "T-Socket", "R-Socket", "Valve Socket", "Endcap", "Bend", "Water Tank", " " }));
        WItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WItemNameKeyPressed(evt);
            }
        });
        jPanel10.add(WItemName);
        WItemName.setBounds(600, 40, 200, 30);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("Gauge");
        jPanel10.add(jLabel70);
        jLabel70.setBounds(450, 160, 107, 27);

        WGauge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        WGauge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "None", "400G", "600G", "1000G", " " }));
        WGauge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WGaugeActionPerformed(evt);
            }
        });
        WGauge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WGaugeKeyPressed(evt);
            }
        });
        jPanel10.add(WGauge);
        WGauge.setBounds(600, 160, 200, 30);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Size");
        jPanel10.add(jLabel71);
        jLabel71.setBounds(450, 200, 107, 27);

        WSize.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        WSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "None", "1/2\"", "3/4\"", "1\"", "1 1/4\"", "1 1/2\"", "2\"", "2 1/2\"", "3\"", "4\"", "6\"", "500L", "1000L", "2000L", "5000L", " " }));
        WSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WSizeActionPerformed(evt);
            }
        });
        WSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WSizeKeyPressed(evt);
            }
        });
        jPanel10.add(WSize);
        WSize.setBounds(600, 200, 200, 30);

        WaterID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WaterIDActionPerformed(evt);
            }
        });
        jPanel10.add(WaterID);
        WaterID.setBounds(330, 40, 40, 30);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel18.setLayout(null);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Available Quantity");
        jPanel18.add(jLabel80);
        jLabel80.setBounds(20, 60, 163, 24);

        AQWater.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQWaterActionPerformed(evt);
            }
        });
        AQWater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AQWaterKeyTyped(evt);
            }
        });
        jPanel18.add(AQWater);
        AQWater.setBounds(230, 60, 150, 30);

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Quantity");
        jPanel18.add(jLabel81);
        jLabel81.setBounds(20, 110, 110, 16);

        QWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QWaterActionPerformed(evt);
            }
        });
        QWater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QWaterKeyTyped(evt);
            }
        });
        jPanel18.add(QWater);
        QWater.setBounds(230, 100, 150, 30);

        UpdateQtyWater.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyWater.setText("Update Quantity");
        UpdateQtyWater.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyWaterMouseClicked(evt);
            }
        });
        UpdateQtyWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyWaterActionPerformed(evt);
            }
        });
        UpdateQtyWater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyWaterKeyReleased(evt);
            }
        });
        jPanel18.add(UpdateQtyWater);
        UpdateQtyWater.setBounds(230, 150, 150, 25);

        jPanel10.add(jPanel18);
        jPanel18.setBounds(1120, 30, 390, 190);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel23.setLayout(null);

        SearchWatID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchWatIDKeyReleased(evt);
            }
        });
        jPanel23.add(SearchWatID);
        SearchWatID.setBounds(1, 1, 138, 30);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Search by Item ID");
        jPanel23.add(jLabel40);
        jLabel40.setBounds(7, 40, 130, 30);

        jPanel10.add(jPanel23);
        jPanel23.setBounds(900, 150, 140, 80);

        jPanel4.add(jPanel10);
        jPanel10.setBounds(0, 0, 1650, 1030);

        jTabbedPane1.addTab("Water pipe  & Fittings", jPanel4);

        jPanel6.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Brand ID");
        jPanel11.add(jLabel41);
        jLabel41.setBounds(30, 80, 107, 27);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Item ID          ");
        jPanel11.add(jLabel42);
        jLabel42.setBounds(30, 40, 107, 27);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Brand Name");
        jPanel11.add(jLabel43);
        jLabel43.setBounds(450, 80, 107, 27);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Item Name");
        jPanel11.add(jLabel44);
        jLabel44.setBounds(450, 40, 107, 27);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Supplier ID");
        jPanel11.add(jLabel45);
        jLabel45.setBounds(30, 120, 107, 27);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Supplier Name");
        jPanel11.add(jLabel46);
        jLabel46.setBounds(450, 120, 107, 27);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Price");
        jPanel11.add(jLabel47);
        jLabel47.setBounds(30, 200, 107, 27);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Selling price");
        jPanel11.add(jLabel48);
        jLabel48.setBounds(30, 240, 107, 27);

        CFBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFBrandIDActionPerformed(evt);
            }
        });
        CFBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFBrandIDKeyPressed(evt);
            }
        });
        jPanel11.add(CFBrandID);
        CFBrandID.setBounds(170, 80, 200, 30);

        CFSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFSupplierIDActionPerformed(evt);
            }
        });
        CFSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFSupplierIDKeyPressed(evt);
            }
        });
        jPanel11.add(CFSupplierID);
        CFSupplierID.setBounds(170, 120, 200, 30);

        CFPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFPriceActionPerformed(evt);
            }
        });
        CFPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CFPriceKeyTyped(evt);
            }
        });
        jPanel11.add(CFPrice);
        CFPrice.setBounds(170, 200, 200, 30);

        CFSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFSellingPriceActionPerformed(evt);
            }
        });
        CFSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFSellingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CFSellingPriceKeyTyped(evt);
            }
        });
        jPanel11.add(CFSellingPrice);
        CFSellingPrice.setBounds(170, 240, 200, 30);

        CFBrandName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFBrandNameActionPerformed(evt);
            }
        });
        CFBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFBrandNameKeyPressed(evt);
            }
        });
        jPanel11.add(CFBrandName);
        CFBrandName.setBounds(600, 80, 200, 30);

        CFSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFSupplierNameActionPerformed(evt);
            }
        });
        CFSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFSupplierNameKeyPressed(evt);
            }
        });
        jPanel11.add(CFSupplierName);
        CFSupplierName.setBounds(600, 120, 200, 30);

        CFItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFItemIDActionPerformed(evt);
            }
        });
        CFItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CFItemIDKeyReleased(evt);
            }
        });
        jPanel11.add(CFItemID);
        CFItemID.setBounds(170, 40, 160, 30);

        CFItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CFItemNameActionPerformed(evt);
            }
        });
        CFItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CFItemNameKeyPressed(evt);
            }
        });
        jPanel11.add(CFItemName);
        CFItemName.setBounds(600, 40, 200, 30);

        ChemicalSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ChemicalSave.setText("Save");
        ChemicalSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChemicalSaveActionPerformed(evt);
            }
        });
        jPanel11.add(ChemicalSave);
        ChemicalSave.setBounds(450, 280, 140, 30);

        UpdateChemical.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateChemical.setText("Update");
        UpdateChemical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateChemicalActionPerformed(evt);
            }
        });
        jPanel11.add(UpdateChemical);
        UpdateChemical.setBounds(900, 40, 140, 30);

        DeleteChemical.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteChemical.setText("Delete");
        DeleteChemical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteChemicalActionPerformed(evt);
            }
        });
        jPanel11.add(DeleteChemical);
        DeleteChemical.setBounds(900, 80, 140, 30);

        ChemicalClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ChemicalClear.setText("Clear");
        ChemicalClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChemicalClearActionPerformed(evt);
            }
        });
        jPanel11.add(ChemicalClear);
        ChemicalClear.setBounds(660, 280, 140, 30);

        TableChemical.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableChemical.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Item name", "Brand ID", "Brand Name", "Supplier ID", "Supplier Name", "Price", "Selling Price", "Quantity"
            }
        ));
        TableChemical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableChemicalMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableChemical);

        jPanel11.add(jScrollPane5);
        jScrollPane5.setBounds(0, 480, 1630, 630);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Manufacture Date");
        jPanel11.add(jLabel50);
        jLabel50.setBounds(30, 160, 129, 27);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Expire Date");
        jPanel11.add(jLabel51);
        jLabel51.setBounds(450, 160, 90, 27);

        CFManufactureDate.setDateFormatString("yyyy-MM-dd");
        jPanel11.add(CFManufactureDate);
        CFManufactureDate.setBounds(170, 160, 200, 30);

        CFExpireDate.setDateFormatString("yyyy-MM-dd");
        jPanel11.add(CFExpireDate);
        CFExpireDate.setBounds(600, 160, 200, 30);

        ChemicalID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChemicalIDActionPerformed(evt);
            }
        });
        jPanel11.add(ChemicalID);
        ChemicalID.setBounds(330, 40, 40, 30);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel19.setLayout(null);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setText("Available Quantity");
        jPanel19.add(jLabel82);
        jLabel82.setBounds(20, 60, 163, 24);

        AQChemical.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQChemical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQChemicalActionPerformed(evt);
            }
        });
        AQChemical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AQChemicalKeyTyped(evt);
            }
        });
        jPanel19.add(AQChemical);
        AQChemical.setBounds(230, 60, 150, 30);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setText("Quantity");
        jPanel19.add(jLabel83);
        jLabel83.setBounds(20, 110, 110, 16);

        QChemical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QChemicalActionPerformed(evt);
            }
        });
        QChemical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QChemicalKeyTyped(evt);
            }
        });
        jPanel19.add(QChemical);
        QChemical.setBounds(230, 100, 150, 30);

        UpdateQtyChemical.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyChemical.setText("Update Quantity");
        UpdateQtyChemical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyChemicalMouseClicked(evt);
            }
        });
        UpdateQtyChemical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyChemicalActionPerformed(evt);
            }
        });
        UpdateQtyChemical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyChemicalKeyReleased(evt);
            }
        });
        jPanel19.add(UpdateQtyChemical);
        UpdateQtyChemical.setBounds(230, 150, 150, 25);

        jPanel11.add(jPanel19);
        jPanel19.setBounds(1120, 30, 390, 190);

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel24.setLayout(null);

        SearchCheID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchCheIDKeyReleased(evt);
            }
        });
        jPanel24.add(SearchCheID);
        SearchCheID.setBounds(1, 1, 138, 30);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Search by Item ID");
        jPanel24.add(jLabel49);
        jLabel49.setBounds(7, 40, 130, 30);

        jPanel11.add(jPanel24);
        jPanel24.setBounds(900, 150, 140, 80);

        jPanel6.add(jPanel11);
        jPanel11.setBounds(0, 0, 1577, 1000);

        jTabbedPane1.addTab("Chemicl & Farming", jPanel6);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(null);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Brand ID");
        jPanel12.add(jLabel53);
        jLabel53.setBounds(30, 80, 107, 30);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Item ID");
        jPanel12.add(jLabel54);
        jLabel54.setBounds(30, 40, 107, 30);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Brand Name");
        jPanel12.add(jLabel55);
        jLabel55.setBounds(450, 80, 107, 27);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Item Name");
        jPanel12.add(jLabel56);
        jLabel56.setBounds(450, 40, 107, 27);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Supplier ID");
        jPanel12.add(jLabel57);
        jLabel57.setBounds(30, 120, 107, 30);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Selling price");
        jPanel12.add(jLabel59);
        jLabel59.setBounds(30, 280, 107, 30);

        OBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OBrandIDActionPerformed(evt);
            }
        });
        OBrandID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OBrandIDKeyPressed(evt);
            }
        });
        jPanel12.add(OBrandID);
        OBrandID.setBounds(170, 80, 200, 30);

        OSupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OSupplierIDActionPerformed(evt);
            }
        });
        OSupplierID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OSupplierIDKeyPressed(evt);
            }
        });
        jPanel12.add(OSupplierID);
        OSupplierID.setBounds(170, 120, 200, 30);

        OSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OSellingPriceActionPerformed(evt);
            }
        });
        OSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OSellingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OSellingPriceKeyTyped(evt);
            }
        });
        jPanel12.add(OSellingPrice);
        OSellingPrice.setBounds(170, 280, 200, 30);

        OItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OItemIDActionPerformed(evt);
            }
        });
        OItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OItemIDKeyReleased(evt);
            }
        });
        jPanel12.add(OItemID);
        OItemID.setBounds(170, 40, 160, 30);

        OtherSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OtherSave.setText("Save");
        OtherSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherSaveActionPerformed(evt);
            }
        });
        jPanel12.add(OtherSave);
        OtherSave.setBounds(450, 320, 140, 30);

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton27.setText("Update");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton27);
        jButton27.setBounds(900, 40, 140, 30);

        DeleteOther.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteOther.setText("Delete");
        DeleteOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteOtherActionPerformed(evt);
            }
        });
        jPanel12.add(DeleteOther);
        DeleteOther.setBounds(900, 80, 140, 30);

        OtherClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OtherClear.setText("Clear");
        OtherClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherClearActionPerformed(evt);
            }
        });
        jPanel12.add(OtherClear);
        OtherClear.setBounds(660, 320, 140, 30);

        TableOther.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TableOther.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Item name", "Brand ID", "Brand Name", "Supplier ID", "Supplier Name", "Price", "Selling Price", "Quantity"
            }
        ));
        TableOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableOtherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableOtherMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(TableOther);

        jPanel12.add(jScrollPane6);
        jScrollPane6.setBounds(0, 480, 1630, 590);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("Purchasing price");
        jPanel12.add(jLabel63);
        jLabel63.setBounds(30, 240, 130, 30);

        OItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OItemNameActionPerformed(evt);
            }
        });
        OItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OItemNameKeyPressed(evt);
            }
        });
        jPanel12.add(OItemName);
        OItemName.setBounds(600, 40, 200, 30);

        OBrandName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OBrandNameActionPerformed(evt);
            }
        });
        OBrandName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OBrandNameKeyPressed(evt);
            }
        });
        jPanel12.add(OBrandName);
        OBrandName.setBounds(600, 80, 200, 30);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Supplier Name");
        jPanel12.add(jLabel64);
        jLabel64.setBounds(450, 120, 107, 27);

        OSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OSupplierNameActionPerformed(evt);
            }
        });
        OSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OSupplierNameKeyPressed(evt);
            }
        });
        jPanel12.add(OSupplierName);
        OSupplierName.setBounds(600, 120, 200, 30);

        OPurchasingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OPurchasingPriceActionPerformed(evt);
            }
        });
        OPurchasingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OPurchasingPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OPurchasingPriceKeyTyped(evt);
            }
        });
        jPanel12.add(OPurchasingPrice);
        OPurchasingPrice.setBounds(170, 240, 200, 30);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Colour");
        jPanel12.add(jLabel58);
        jLabel58.setBounds(30, 160, 107, 30);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Size");
        jPanel12.add(jLabel61);
        jLabel61.setBounds(30, 200, 107, 30);

        OSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OSizeActionPerformed(evt);
            }
        });
        OSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OSizeKeyPressed(evt);
            }
        });
        jPanel12.add(OSize);
        OSize.setBounds(170, 200, 200, 30);

        OColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OColourActionPerformed(evt);
            }
        });
        OColour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OColourKeyPressed(evt);
            }
        });
        jPanel12.add(OColour);
        OColour.setBounds(170, 160, 200, 30);

        OtherID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherIDActionPerformed(evt);
            }
        });
        jPanel12.add(OtherID);
        OtherID.setBounds(330, 40, 40, 30);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Add OR Update Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel20.setLayout(null);

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Available Quantity");
        jPanel20.add(jLabel84);
        jLabel84.setBounds(20, 60, 163, 24);

        AQOther.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AQOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AQOtherActionPerformed(evt);
            }
        });
        AQOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AQOtherKeyTyped(evt);
            }
        });
        jPanel20.add(AQOther);
        AQOther.setBounds(230, 60, 150, 30);

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setText("Quantity");
        jPanel20.add(jLabel85);
        jLabel85.setBounds(20, 110, 110, 16);

        QOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QOtherActionPerformed(evt);
            }
        });
        QOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QOtherKeyTyped(evt);
            }
        });
        jPanel20.add(QOther);
        QOther.setBounds(230, 100, 150, 30);

        UpdateQtyOther.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateQtyOther.setText("Update Quantity");
        UpdateQtyOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateQtyOtherMouseClicked(evt);
            }
        });
        UpdateQtyOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateQtyOtherActionPerformed(evt);
            }
        });
        UpdateQtyOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UpdateQtyOtherKeyReleased(evt);
            }
        });
        jPanel20.add(UpdateQtyOther);
        UpdateQtyOther.setBounds(230, 150, 150, 25);

        jPanel12.add(jPanel20);
        jPanel20.setBounds(1120, 30, 390, 190);

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel25.setLayout(null);

        SearchOthID5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchOthID5KeyReleased(evt);
            }
        });
        jPanel25.add(SearchOthID5);
        SearchOthID5.setBounds(1, 1, 138, 30);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("Search by Item ID");
        jPanel25.add(jLabel60);
        jLabel60.setBounds(7, 40, 130, 30);

        jPanel12.add(jPanel25);
        jPanel25.setBounds(900, 150, 140, 80);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1709, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Other Items", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OColourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OColourActionPerformed

    private void OSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OSizeActionPerformed

    private void OPurchasingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OPurchasingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OPurchasingPriceActionPerformed

    private void OSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OSupplierNameActionPerformed

    private void OBrandNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OBrandNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OBrandNameActionPerformed

    private void OItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OItemNameActionPerformed

    private void OtherClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherClearActionPerformed
        clearother();
    }//GEN-LAST:event_OtherClearActionPerformed

    private void DeleteOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteOtherActionPerformed
         try {

            String I_ID = OItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM OtherItem WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM OtherItem WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,OItemID.getText());
                        ps.execute();
                        tableloadOther1();
                        clearother();
                        OItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM OtherItem WHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,OItemID.getText());
                                ps.execute();
                                tableloadOther1();
                                clearother();
                                OItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }       
    }//GEN-LAST:event_DeleteOtherActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed

        if(OItemID.getText().equals("") || OItemID.getText().equals("") || OBrandID.getText().equals("") || OBrandName.getText().equals("")|| OSupplierID.getText().equals("")|| OSupplierName.getText().equals("") || OColour.getText().equals("")|| OSize.getText().equals("")|| OPurchasingPrice.getText().equals("")|| OSellingPrice.getText().equals(""))
        {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
        
        try {
            
            String value1=OItemID.getText();
            String value2=OItemName.getText();
            String value3=OBrandID.getText();
            String value4=OBrandName.getText();
            String value5=OSupplierID.getText();
            String value6=OSupplierName.getText(); 
            String value7=OColour.getText(); 
            String value8=OSize.getText();
            String value9=OPurchasingPrice.getText(); 
            String value10=CFSellingPrice.getText();
            
           
            
            String Chemical="Update OtherItem set ItemID='"+value1+"',ItemName='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',Colour='"+value7+"',Size='"+value8+"',PurchasingPrice='"+value9+"',SellingPrice='"+value10+"' where ItemID='"+value1+"'";
            pst=conn.prepareStatement(Chemical);
            
            pst.execute();
            tableloadOther1();
            JOptionPane.showMessageDialog(null, "Updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void OtherSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherSaveActionPerformed
        createOtrID();
         if(OItemID.getText().equals("") || OItemID.getText().equals("") || OBrandID.getText().equals("") || OBrandName.getText().equals("")|| OSupplierID.getText().equals("")|| OSupplierName.getText().equals("") || OColour.getText().equals("")|| OSize.getText().equals("")|| OPurchasingPrice.getText().equals("")|| OSellingPrice.getText().equals(""))
        {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
        try{

            String water="Insert into OtherItem(ItemID,ItemName,BrandID,BrandName,SupplierID,SupplierName,Colour,Size,PurchasingPrice,SellingPrice,Qty) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(water);
            pst.setString(1,OItemID.getText());
            pst.setString(2,OItemName.getText());
            pst.setString(3,OBrandID.getText());
            pst.setString(4,OBrandName.getText());
            pst.setString(5,OSupplierID.getText());
            pst.setString(6,OSupplierName.getText());
            pst.setString(7,OColour.getText());
            pst.setString(8,OSize.getText());
            pst.setString(9,OPurchasingPrice.getText());
            pst.setString(10,OSellingPrice.getText());
            pst.setString(11,QOther.getText());

            pst.execute();
            tableloadOther1();
            clearother();
            JOptionPane.showMessageDialog(null, "saved!");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
         }
    }//GEN-LAST:event_OtherSaveActionPerformed

    private void OItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OItemIDActionPerformed

    private void OSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OSellingPriceActionPerformed

    private void OSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OSupplierIDActionPerformed

    private void OBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OBrandIDActionPerformed

    private void ChemicalClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChemicalClearActionPerformed

       clearchemical();

    }//GEN-LAST:event_ChemicalClearActionPerformed

    private void DeleteChemicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteChemicalActionPerformed
        try {

            String I_ID = CFItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM Chemical_Farming WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM Chemical_Farming WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,CFItemID.getText());
                        ps.execute();
                        tableloadChemical1();
                        clearchemical();
                        CFItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM Chemical_Farming WHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,CFItemID.getText());
                                ps.execute();
                                tableloadChemical1();
                                clearchemical();
                                CFItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_DeleteChemicalActionPerformed

    private void UpdateChemicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateChemicalActionPerformed
 /**if(CFItemID.getText().equals("") || CFItemName.getText().equals("") || CFBrandID.getText().equals("") || CFBrandName.getText().equals("") || CFSupplierID.getText().equals("") ||CFSupplierName.getText().equals("") ||CFPrice.getText().equals("")||CFSellingPrice.getText().equals(""))
        
       {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
          
        
        try {
            date();
            String value1=CFItemID.getText();
            String value2=CFItemName.getText();
            String value3=CFBrandID.getText();
            String value4=CFBrandName.getText();
            String value5=CFSupplierID.getText();
            String value6=CFSupplierName.getText(); 
            String value7=(((JTextField)CFManufactureDate.getDateEditor().getUiComponent()).getText());
            String value8=(((JTextField)CFExpireDate.getDateEditor().getUiComponent()).getText());
            String value9=CFPrice.getText();
            String value10=CFSellingPrice.getText();
            
           
            
            String Chemical="Update Chemical_Farming set ItemID='"+value1+"',ItemName='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',ManufactureDate='"+value7+"',ExpireDate='"+value8+"',Price='"+value9+"',SellingPrice='"+value10+"' where ItemID='"+value1+"'";
            pst=conn.prepareStatement(Chemical);
            
            pst.execute();
            tableloadChemical1();
            JOptionPane.showMessageDialog(null, "Updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
 */
 
  if(CFItemID.getText().equals("") || CFItemName.getText().equals("") || CFBrandID.getText().equals("") || CFBrandName.getText().equals("") || CFSupplierID.getText().equals("") ||CFSupplierName.getText().equals("") ||CFPrice.getText().equals("")||CFSellingPrice.getText().equals(""))
      {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(CFManufactureDate.getDate()));
            d2=format.parse(format.format(CFExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
                
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                CFManufactureDate.setDate(null);
                CFExpireDate.setDate(null);
          }
            else{
                
           try{

            date();
            String value1=CFItemID.getText();
            String value2=CFItemName.getText();
            String value3=CFBrandID.getText();
            String value4=CFBrandName.getText();
            String value5=CFSupplierID.getText();
            String value6=CFSupplierName.getText(); 
            String value7=(((JTextField)CFManufactureDate.getDateEditor().getUiComponent()).getText());
            String value8=(((JTextField)CFExpireDate.getDateEditor().getUiComponent()).getText());
            String value9=CFPrice.getText();
            String value10=CFSellingPrice.getText();
            
           
            
            String Chemical="Update Chemical_Farming set ItemID='"+value1+"',ItemName='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',ManufactureDate='"+value7+"',ExpireDate='"+value8+"',Price='"+value9+"',SellingPrice='"+value10+"' where ItemID='"+value1+"'";
            pst=conn.prepareStatement(Chemical);
            
            pst.execute();
            tableloadChemical1();
            JOptionPane.showMessageDialog(null, "Updated");
                
                JOptionPane.showMessageDialog(null, "saved!");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "All the fields are required ");

            }
            }
}  
      catch(Exception e)
      {
          e.printStackTrace();
      }
        }
  
 
 
    }//GEN-LAST:event_UpdateChemicalActionPerformed

    private void ChemicalSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChemicalSaveActionPerformed
       
        createChemID();
        if(CFItemID.getText().equals("") || CFItemName.getText().equals("") || CFBrandID.getText().equals("") || CFBrandName.getText().equals("") || CFSupplierID.getText().equals("") ||CFSupplierName.getText().equals("") ||CFPrice.getText().equals("")||CFSellingPrice.getText().equals(""))
        
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(CFManufactureDate.getDate()));
            d2=format.parse(format.format(CFExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
                
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                PManufactureDate.setDate(null);
                PExpireDate.setDate(null);
          }
            else{
                
           try{

                String paint="Insert into Chemical_Farming(ItemID,ItemName,BrandID,BrandName,SupplierID,SupplierName,ManufactureDate,ExpireDate,Price,SellingPrice,Qty) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(paint);
            pst.setString(1,CFItemID.getText());
            pst.setString(2,CFItemName.getText());
            pst.setString(3,CFBrandID.getText());
            pst.setString(4,CFBrandName.getText());
            pst.setString(5,CFSupplierID.getText());
            pst.setString(6,CFSupplierName.getText());
            pst.setString(7,((JTextField)CFManufactureDate.getDateEditor().getUiComponent()).getText());  
            pst.setString(8,((JTextField)CFExpireDate.getDateEditor().getUiComponent()).getText());
            pst.setString(9,CFPrice.getText());
            pst.setString(10,CFSellingPrice.getText());
            pst.setString(11,QChemical.getText());

            pst.execute();
            tableloadChemical1();
            clearchemical();
            JOptionPane.showMessageDialog(null, "saved!");
                
                JOptionPane.showMessageDialog(null, "saved!");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "All the fields are required ");

            }
            }
}  
      catch(Exception e)
      {
      
      }
        }
        
        
    }//GEN-LAST:event_ChemicalSaveActionPerformed

    
    private void CFItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFItemNameActionPerformed

    private void CFItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFItemIDActionPerformed

    private void CFSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFSupplierNameActionPerformed

    private void CFBrandNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFBrandNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFBrandNameActionPerformed

    private void CFSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFSellingPriceActionPerformed

    private void CFPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFPriceActionPerformed

    private void CFSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFSupplierIDActionPerformed

    private void CFBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CFBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CFBrandIDActionPerformed

    private void WSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WSizeActionPerformed

    private void WGaugeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WGaugeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WGaugeActionPerformed

    private void ClrearWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClrearWaterActionPerformed
        clearwater();
    }//GEN-LAST:event_ClrearWaterActionPerformed

    private void DeleteWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteWaterActionPerformed
         try {

            String I_ID = WItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM WaterPipe_Fitting WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM WaterPipe_Fitting WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,WItemID.getText());
                        ps.execute();
                        tableloadWater1();
                        clearwater();
                        WItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM WaterPipe_Fitting WHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,WItemID.getText());
                                ps.execute();
                                tableloadWater1();
                                clearwater();
                                WItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_DeleteWaterActionPerformed

    private void UpdateWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateWaterActionPerformed

        
        if(WItemID.getText().equals("") || WItemName.getSelectedItem().equals("") || WBrandID.getText().equals("") || WBrandName.getSelectedItem().equals("")|| WSupplierID.getText().equals("")|| WSupplierName.getText().equals("") || WPrice.getText().equals("")|| WGauge.getSelectedItem().equals("")|| WSellingPrice.getText().equals("")||WSize.getSelectedItem().equals(""))
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{

        try {
            
            String value1=WItemID.getText();
            String value2=WItemName.getSelectedItem().toString();
            String value3=WBrandID.getText();
            String value4=WBrandName.getSelectedItem().toString();
            String value5=WSupplierID.getText();
            String value6=WSupplierName.getText();
            String value7=WPrice.getText();
            String value8=WGauge.getSelectedItem().toString();
            String value9=WSellingPrice.getText();
            String value10=WSize.getSelectedItem().toString();
            
            
            
            String water="Update WaterPipe_Fitting set ItemID='"+value1+"',ItemName='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',Price='"+value7+"',Gauge='"+value8+"',SellingPrice='"+value9+"',Size='"+value10+"' where ItemID='"+value1+"'";
                pst=conn.prepareStatement(water);
            
            pst.execute();
            tableloadWater1();
            JOptionPane.showMessageDialog(null, "Updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_UpdateWaterActionPerformed

    private void SaveWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveWaterActionPerformed
     
            createWatID();
            
            if(WItemID.getText().equals("") || WItemName.getSelectedItem().equals("") || WBrandID.getText().equals("") || WBrandName.getSelectedItem().equals("")|| WSupplierID.getText().equals("")|| WSupplierName.getText().equals("") || WPrice.getText().equals("")|| WGauge.getSelectedItem().equals("")|| WSellingPrice.getText().equals("")||WSize.getSelectedItem().equals(""))
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
        try{

            String water="Insert into WaterPipe_Fitting(ItemID,ItemName,BrandID,BrandName,SupplierID,SupplierName,Price,Gauge,SellingPrice,Size,Qty) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(water);
            pst.setString(1,WItemID.getText());
            pst.setString(2,WItemName.getSelectedItem().toString());
            pst.setString(3,WBrandID.getText());
            pst.setString(4,WBrandName.getSelectedItem().toString());
            pst.setString(5,WSupplierID.getText());
            pst.setString(6,WSupplierName.getText());
            pst.setString(7,WPrice.getText());
            pst.setString(8,WGauge.getSelectedItem().toString());
            pst.setString(9,WSellingPrice.getText());
            pst.setString(10,WSize.getSelectedItem().toString());
            pst.setString(11,QWater.getText());

            pst.execute();
            tableloadWater1();
            clearwater();
            JOptionPane.showMessageDialog(null, "saved!");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        } 
            }
    
    }//GEN-LAST:event_SaveWaterActionPerformed

    private void WItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WItemIDActionPerformed

    private void WSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WSupplierNameActionPerformed

    private void WSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WSellingPriceActionPerformed

    private void WPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WPriceActionPerformed

    private void WSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WSupplierIDActionPerformed

    private void WBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WBrandIDActionPerformed

    private void RCManuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RCManuMouseClicked
        RColourSelect.disable();
        RColourManu.enable();
    }//GEN-LAST:event_RCManuMouseClicked

    private void RCSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RCSelectMouseClicked
        RColourManu.disable();
        RColourSelect.enable();
    }//GEN-LAST:event_RCSelectMouseClicked

    private void RColourManuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RColourManuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RColourManuActionPerformed

    private void RSManuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RSManuMouseClicked
        RSizeSelect.disable();
        RSizeManu.enable();
    }//GEN-LAST:event_RSManuMouseClicked

    private void RSizeManuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSizeManuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSizeManuActionPerformed

    private void RSSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RSSelectMouseClicked
        RSizeManu.disable();
        RSizeSelect.enable();
    }//GEN-LAST:event_RSSelectMouseClicked

    private void RoofingClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoofingClearActionPerformed

        clearRoofing();
        
    }//GEN-LAST:event_RoofingClearActionPerformed

    private void RoofingDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoofingDeleteActionPerformed
        try {

            String I_ID = RItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM Roofing_Fitting WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM Roofing_Fitting WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,RItemID.getText());
                        ps.execute();
                        tableloadRoofing1();
                        clearRoofing();
                        RItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM Roofing_Fitting WHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,RItemID.getText());
                                ps.execute();
                                tableloadRoofing1();
                                clearRoofing();
                                RItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }
        
    }//GEN-LAST:event_RoofingDeleteActionPerformed

    private void UpdateRoofingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateRoofingActionPerformed
         
         sizecolour();
      
        if(RItemID.getText().equals("") || RCategory.getSelectedItem().equals("") || RType.getSelectedItem().equals("") || RBrandID.getText().equals("") || RBrandName.getText().equals("")|| RSupplierID.getText().equals("")|| RSupplierName.getText().equals("") ||size1.equals("")|| colour.equals("")|| RPurchasingPrice.getText().equals("")|| RSellingPrice.getText().equals(""))
        {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
               
        try {
            String value1=RItemID.getText();
            String value2=RCategory.getSelectedItem().toString();
            String value3=RType.getSelectedItem().toString();
            String value4=RBrandID.getText();
            String value5=RBrandName.getText();
            String value6=RSupplierID.getText();
            String value7=RSupplierName.getText();
            String value10=RPurchasingPrice.getText();
            String value11=RSellingPrice.getText();
            
            String Roofing="Update Roofing_Fitting set ItemID='"+value1+"',Catergary='"+value2+"',Type='"+value3+"',BrandID='"+value4+"',BrandName='"+value5+"',SupplierID='"+value6+"',SupplierName='"+value7+"', Size = '"+size1+"', Colour = '"+colour+"',PurchasingPrice='"+value10+"',SellingPrice='"+value11+"' where ItemID='"+value1+"'";
            pst=conn.prepareStatement(Roofing);
            
            pst.execute();
            tableloadRoofing1();
            JOptionPane.showMessageDialog(null, "Updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
    }//GEN-LAST:event_UpdateRoofingActionPerformed


    
    private void RoofingSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoofingSaveActionPerformed
        
        createRofID();

        sizecolour();
        if(RItemID.getText().equals("") || RCategory.getSelectedItem().equals("") || RType.getSelectedItem().equals("") || RBrandID.getText().equals("") || RBrandName.getText().equals("")|| RSupplierID.getText().equals("")|| RSupplierName.getText().equals("") ||size1.equals("")|| colour.equals("")|| RPurchasingPrice.getText().equals("")|| RSellingPrice.getText().equals(""))
        {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
        

        try
        {
            String construction="Insert into Roofing_Fitting(ItemID,Catergary,Type,BrandID,BrandName,SupplierID,SupplierName,Size,Colour,PurchasingPrice,SellingPrice,Qty) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(construction);
            pst.setString(1,RItemID.getText());
            pst.setString(2,RCategory.getSelectedItem().toString());
            pst.setString(3,RType.getSelectedItem().toString());
            pst.setString(4,RBrandID.getText());
            pst.setString(5,RBrandName.getText());
            pst.setString(6,RSupplierID.getText());
            pst.setString(7,RSupplierName.getText());
            pst.setString(8,size1);
            pst.setString(9,colour);
            pst.setString(10,RPurchasingPrice.getText());
            pst.setString(11,RSellingPrice.getText());
            pst.setString(12,QRoofing.getText());

            pst.execute();
            tableloadRoofing1();
            clearRoofing();
            JOptionPane.showMessageDialog(null, "saved!");

        }catch(Exception e){

        }
        }
    }//GEN-LAST:event_RoofingSaveActionPerformed

    private void RItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RItemIDActionPerformed

    private void RSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSupplierNameActionPerformed

    private void RBrandNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBrandNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBrandNameActionPerformed

    private void RSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSellingPriceActionPerformed

    private void RPurchasingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RPurchasingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RPurchasingPriceActionPerformed

    private void RSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSupplierIDActionPerformed

    private void RBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBrandIDActionPerformed

    private void TablePaintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePaintMouseClicked
        clearpaint();
      //   AQPaint.setText("");
        DefaultTableModel model=(DefaultTableModel) TablePaint.getModel();
       int row = TablePaint.getSelectedRow();
       

        String Iid = TablePaint.getValueAt(row, 0).toString();
        String Category = TablePaint.getValueAt(row, 1).toString();
        String Bid = TablePaint.getValueAt(row, 2).toString();
        String Bname = TablePaint.getValueAt(row, 3).toString();
        String Sid = TablePaint.getValueAt(row, 4).toString();
        String Sname = TablePaint.getValueAt(row, 5).toString();
        String Ccode = TablePaint.getValueAt(row, 6).toString();
        ((JTextField)PManufactureDate.getDateEditor().getUiComponent()).setText( model.getValueAt(row,7).toString());
        ((JTextField)PExpireDate.getDateEditor().getUiComponent()).setText( model.getValueAt(row,8).toString());
        String Lprice = TablePaint.getValueAt(row, 9).toString();
        String Discount = TablePaint.getValueAt(row, 10).toString();
        String Pprice = TablePaint.getValueAt(row, 11).toString();
        String Sprice = TablePaint.getValueAt(row, 12).toString();
        String Quantity = TablePaint.getValueAt(row, 13).toString();
        String Capacity = TablePaint.getValueAt(row, 14).toString();

        PItemID.setText(Iid);
        PCategory.setSelectedItem(Category);
        PBrandID.setText(Bid);
        PBrandName.setSelectedItem(Bname);
        PSupplierID.setText(Sid);
        PSupplierName.setText(Sname);
        PColourCode.setText(Ccode);
        PLablePrice.setText(Lprice);
        PDiscount.setText(Discount);
        PPurchingPrice.setText(Pprice);
        PSellingPrice.setText(Sprice);
        AQPaint.setText(Quantity);
        PCapacity.setSelectedItem(Capacity);
    }//GEN-LAST:event_TablePaintMouseClicked

    private void PSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSupplierIDActionPerformed

    private void PBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PBrandIDActionPerformed

    private void PDiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PDiscountKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
        
    }//GEN-LAST:event_PDiscountKeyTyped

    private void PDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PDiscountActionPerformed

    private void PPurchingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PPurchingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_PPurchingPriceKeyTyped

    private void PPurchingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPurchingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PPurchingPriceActionPerformed

    private void ClearPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearPaintActionPerformed

        clearpaint();
        
    }//GEN-LAST:event_ClearPaintActionPerformed

    private void DelatePaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelatePaintActionPerformed
        try {

            String I_ID = PItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM Paint_Thinner WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM Paint_Thinner WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,PItemID.getText());
                        ps.execute();
                        tableloadPaint1();
                        clearpaint();
                        PItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM Paint_ThinnerWHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,PItemID.getText());
                                ps.execute();
                                tableloadPaint1();
                                clearpaint();
                                PItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_DelatePaintActionPerformed

    private void UpdatePaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePaintActionPerformed
    
    //createPntID();
        if(PItemID.getText().equals("") || PCategory.getSelectedItem().equals("") || PBrandID.getText().equals("") || PBrandName.getSelectedItem().equals("")|| PSupplierID.getText().equals("")|| PSupplierName.getText().equals("") || ((JTextField)PManufactureDate.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)PExpireDate.getDateEditor().getUiComponent()).getText().equals("")|| PLablePrice.getText().equals("")|| PDiscount.getText().equals("")|| PPurchingPrice.getText().equals("")|| PSellingPrice.getText().equals("")|| PCapacity.getSelectedItem().equals(""))
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(PManufactureDate.getDate()));
            d2=format.parse(format.format(PExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
                
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                PManufactureDate.setDate(null);
                PExpireDate.setDate(null);
          }
            else{
                
           try{

            String value1=PItemID.getText();
            String value2=PCategory.getSelectedItem().toString();
            String value3=PBrandID.getText();
            String value4=PBrandName.getSelectedItem().toString();
            String value5=PSupplierID.getText();
            String value6=PSupplierName.getText();
            String value7=PColourCode.getText();
            String value8=(((JTextField)PManufactureDate.getDateEditor().getUiComponent()).getText());
            String value9=(((JTextField)PExpireDate.getDateEditor().getUiComponent()).getText());
            String value10=PLablePrice.getText();
            String value11=PDiscount.getText();
            String value12=PPurchingPrice.getText();
            String value13=PSellingPrice.getText();
            String value14=QPaint.getText();
            String value15=PCapacity.getSelectedItem().toString();
            
            String paint="Update Paint_Thinner set ItemID='"+value1+"',Catergory='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',ColourCode='"+value7+"',ManufactureDate='"+value8+"',ExpireDate='"+value9+"',LablePrice='"+value10+"',Discount='"+value11+"',PurchasingPrice='"+value12+"',SellingPrice='"+value13+"',Qty='"+value14+"',Capacity='"+value15+"' where ItemID='"+value1+"'";
            pst=conn.prepareStatement(paint);
            
            pst.execute();
            tableloadPaint1();
            JOptionPane.showMessageDialog(null, "Updated");
                
                JOptionPane.showMessageDialog(null, "saved!");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "All the fields are required ");

            }
            }
}  
      catch(Exception e)
      {
          e.printStackTrace();
      }
        }
    }//GEN-LAST:event_UpdatePaintActionPerformed



    private void SavePaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePaintActionPerformed
        createPntID();
        if(PItemID.getText().equals("") || PCategory.getSelectedItem().equals("") || PBrandID.getText().equals("") || PBrandName.getSelectedItem().equals("")|| PSupplierID.getText().equals("")|| PSupplierName.getText().equals("") || ((JTextField)PManufactureDate.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)PExpireDate.getDateEditor().getUiComponent()).getText().equals("")|| PLablePrice.getText().equals("")|| PDiscount.getText().equals("")|| PPurchingPrice.getText().equals("")|| PSellingPrice.getText().equals("")|| PCapacity.getSelectedItem().equals(""))
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(PManufactureDate.getDate()));
            d2=format.parse(format.format(PExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
                
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"Expire date Should be Upcomming Manufacture Date");
                PManufactureDate.setDate(null);
                PExpireDate.setDate(null);
          }
            else{
                
           try{

                String paint="Insert into Paint_Thinner(ItemID,Catergory,BrandID,BrandName,SupplierID,SupplierName,ColourCode,ManufactureDate,ExpireDate,LablePrice,Discount,PurchasingPrice,SellingPrice,Qty,Capacity) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst=conn.prepareStatement(paint);
                pst.setString(1,PItemID.getText());
                pst.setString(2,PCategory.getSelectedItem().toString());
                pst.setString(3,PBrandID.getText());
                pst.setString(4,PBrandName.getSelectedItem().toString());
                pst.setString(5,PSupplierID.getText());
                pst.setString(6,PSupplierName.getText());
                pst.setString(7,PColourCode.getText());
                pst.setString(8,((JTextField)PManufactureDate.getDateEditor().getUiComponent()).getText()); 
                pst.setString(9,((JTextField)PExpireDate.getDateEditor().getUiComponent()).getText());
                pst.setString(10,PLablePrice.getText());
                pst.setString(11,PDiscount.getText());
                pst.setString(12,PPurchingPrice.getText());
                pst.setString(13,PSellingPrice.getText());
                pst.setString(14,QPaint.getText());
                pst.setString(15,PCapacity.getSelectedItem().toString());

                pst.execute(); 
                tableloadPaint1();
                clearpaint();
                
                JOptionPane.showMessageDialog(null, "saved!");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "All the fields are required ");

            }
            }
}  
      catch(Exception e)
      {
      
      }
        }
        
            
    }//GEN-LAST:event_SavePaintActionPerformed

    private void PItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PItemIDActionPerformed

    private void PSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_PSellingPriceKeyTyped

    private void PSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSellingPriceActionPerformed

    private void PLablePriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PLablePriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_PLablePriceKeyTyped

    private void PLablePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PLablePriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PLablePriceActionPerformed

    private void PColourCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PColourCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PColourCodeActionPerformed

    private void PSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSupplierNameActionPerformed

    private void CLengthManuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLengthManuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CLengthManuActionPerformed

    private void CLManuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLManuActionPerformed

    }//GEN-LAST:event_CLManuActionPerformed

    private void CLManuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CLManuMouseClicked
        CLengthSelect.disable();
        CLengthManu.enable();
    }//GEN-LAST:event_CLManuMouseClicked

    private void CLSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLSelectActionPerformed

    }//GEN-LAST:event_CLSelectActionPerformed

    private void CLSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CLSelectMouseClicked
        CLengthManu.disable();
        CLengthSelect.enable();
    }//GEN-LAST:event_CLSelectMouseClicked

    private void CSizeManuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSizeManuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CSizeManuActionPerformed

    private void CSizeSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSizeSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CSizeSelectActionPerformed

    private void smanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smanuActionPerformed
       
    }//GEN-LAST:event_smanuActionPerformed

    private void smanuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smanuMouseClicked
        CSizeSelect.disable();
        CSizeManu.enable();
    }//GEN-LAST:event_smanuMouseClicked

    private void sselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sselectActionPerformed
       
    }//GEN-LAST:event_sselectActionPerformed

    private void sselectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sselectMouseClicked
        CSizeManu.disable();
        CSizeSelect.enable();
    }//GEN-LAST:event_sselectMouseClicked

    private void TableConstructionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableConstructionMouseClicked
clearconstruction();  
AQConstruction.setText("");
        int row = TableConstruction.getSelectedRow();

        String Iid = TableConstruction.getValueAt(row, 0).toString();
        String Iname = TableConstruction.getValueAt(row, 1).toString();
        String Bid = TableConstruction.getValueAt(row, 2).toString();
        String Bname = TableConstruction.getValueAt(row, 3).toString();
        String Sid = TableConstruction.getValueAt(row, 4).toString();
        String Sname = TableConstruction.getValueAt(row, 5).toString();
        String Pprice = TableConstruction.getValueAt(row, 6).toString();
        String Sprice = TableConstruction.getValueAt(row, 7).toString();
        String Quantity = TableConstruction.getValueAt(row, 8).toString();
        String size = TableConstruction.getValueAt(row, 9).toString();
        String length = TableConstruction.getValueAt(row, 10).toString();
       
        CItemID.setText(Iid);
        CItemName.setText(Iname);
        CBrandID.setText(Bid);
        CBrandName.setText(Bname);
        CSupplierID.setText(Sid);
        CSupplierName.setText(Sname);
        CPurchasingPrice.setText(Pprice);
        CSellingPrice.setText(Sprice);
        AQConstruction.setText(Quantity);
        
      
        if(size.equals("Select")||size.equals("None")||size.equals("8MM")||size.equals("10MM")||size.equals("12MM")||size.equals("16MM")||size.equals("20MM"))
        {
            CSizeManu.disable();
            CSizeSelect.enable();
            sselect.setSelected(true);
            CSizeSelect.setSelectedItem(size);
            CSizeManu.setText("");
        }
        else
        {
            CSizeSelect.disable();
            CSizeManu.enable();
            smanu.setSelected(true);
            CSizeSelect.setSelectedItem("");
            CSizeManu.setText(size);
        }
        if(length.equals("Select")||length.equals("None")||length.equals("12f")||length.equals("19f")||length.equals("20f"))
        {
            CLengthManu.disable();
            CLengthSelect.enable();
            CLSelect.setSelected(true);
            CLengthSelect.setSelectedItem(length);
            CLengthManu.setText("");
        }
        else
        {
            
            CLengthSelect.disable();
            CLengthManu.enable();
            CLManu.setSelected(true);
            CLengthSelect.setSelectedItem("");
            CLengthManu.setText(length);
        }
    }//GEN-LAST:event_TableConstructionMouseClicked

    private void ClearConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearConstructionActionPerformed
     
        clearconstruction();
        
        
    }//GEN-LAST:event_ClearConstructionActionPerformed

    private void DeleteConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteConstructionActionPerformed
        try {

            String I_ID = CItemID.getText();

            if(I_ID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "You haven't selected a row ");
            }
            else
            {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null; 
        Double qty=0.0;
        String q=null;
                
                   
                int x=JOptionPane.showConfirmDialog(null, "Are you sure want to remove Iteam ID "+I_ID);

                if(x==0)
                {
                    try {
                        String sql = "SELECT Qty FROM Construction WHERE ItemID ='"+I_ID+"' ";
                        ps1 = conn.prepareStatement(sql);
                        rs1= ps1.executeQuery();
                        
                        while(rs1.next()){
                            q=rs1.getString("Qty");
                           
                        }
                     
                        if(q.isEmpty()){   
                        String rbque = "DELETE FROM Construction WHERE ItemID =? ";

                        ps = conn.prepareStatement(rbque);
                        ps.setString(1,CItemID.getText());
                        ps.execute();
                        tableloadConstuction1();
                        clearconstruction();
                        CItemID.setText("");
                        }
                        else{
                            qty  = Double.parseDouble(q);
                            if(qty==0){
                                String rbque = "DELETE FROM Construction WHERE ItemID =? ";

                                ps = conn.prepareStatement(rbque);
                                ps.setString(1,CItemID.getText());
                                ps.execute();
                                tableloadConstuction1();
                                clearconstruction();
                                CItemID.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Cannot be deleted since there is a quantity left", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                           System.out.println(e);
                    
                    }
                }
            }
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_DeleteConstructionActionPerformed

    private void UpdateConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateConstructionActionPerformed

     sizelength();
        if(CItemID.getText().equals("") || CItemName.getText().equals("") || CBrandID.getText().equals("") || CBrandName.getText().equals("")|| CSupplierID.getText().equals("")|| CSupplierName.getText().equals("") || CPurchasingPrice.getText().equals("")|| CSellingPrice.getText().equals("")|| size.equals("") || length.equals(""))
        {

            JOptionPane.showMessageDialog(null,"All fields are required(except meterstatus)");

        }
        else{
        try {
            
            String value1=CItemID.getText();
            String value2=CItemName.getText();
            String value3=CBrandID.getText();
            String value4=CBrandName.getText();
            String value5=CSupplierID.getText();
            String value6=CSupplierName.getText();
            String value7=CPurchasingPrice.getText();
            String value8=CSellingPrice.getText();
           
            
            
            String paint="Update Construction set ItemID='"+value1+"',ItemName='"+value2+"',BrandID='"+value3+"',BrandName='"+value4+"',SupplierID='"+value5+"',SupplierName='"+value6+"',PurchasingPrice='"+value7+"',SellingPrice='"+value8+"', Size = '"+size+"',Length='"+length+"'  where ItemID='"+value1+"'";
            pst=conn.prepareStatement(paint);
            
            pst.execute();
            tableloadConstuction1();
            JOptionPane.showMessageDialog(null, "Updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    }//GEN-LAST:event_UpdateConstructionActionPerformed

    private void SaveConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveConstructionActionPerformed
       
        createConID();
        if(CItemID.getText().equals("") || CItemName.getText().equals("") || CBrandID.getText().equals("") || CBrandName.getText().equals("")|| CSupplierID.getText().equals("")|| CSupplierName.getText().equals("") || CPurchasingPrice.getText().equals("")|| CSellingPrice.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"All fields are required");
        }
        else{
        sizelength();
        try
        {
            String construction="Insert into Construction(ItemID,ItemName,BrandID,BrandName,SupplierID,SupplierName,PurchasingPrice,SellingPrice,Qty,Size,Length) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(construction);
            pst.setString(1,CItemID.getText());
            pst.setString(2,CItemName.getText());
            pst.setString(3,CBrandID.getText());
            pst.setString(4,CBrandName.getText());
            pst.setString(5,CSupplierID.getText());
            pst.setString(6,CSupplierName.getText());
            pst.setString(7,CPurchasingPrice.getText());
            pst.setString(8,CSellingPrice.getText());
            pst.setString(9,QConstruction.getText());
            pst.setString(10,size);
            pst.setString(11,length);
            //pst.setString(12, TXTcqty.getText());

            pst.execute();
            tableloadConstuction1();
            clearconstruction();
            QtyUpdateConstruction();
            JOptionPane.showMessageDialog(null, "saved!");
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_SaveConstructionActionPerformed

    private void CItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CItemNameActionPerformed

    private void CItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CItemIDActionPerformed

    private void CSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CSupplierNameActionPerformed

    private void CBrandNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBrandNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBrandNameActionPerformed

    private void CSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_CSellingPriceKeyTyped

    private void CSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CSellingPriceActionPerformed

    private void CPurchasingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPurchasingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CPurchasingPriceActionPerformed

    private void CSupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CSupplierIDActionPerformed

    private void CBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBrandIDActionPerformed

    private void TableChemicalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableChemicalMouseClicked
         DefaultTableModel model=(DefaultTableModel) TableChemical.getModel();
        int row = TableChemical.getSelectedRow();

        String Iid = TableChemical.getValueAt(row, 0).toString();
        String Iname = TableChemical.getValueAt(row, 1).toString();
        String Bid = TableChemical.getValueAt(row, 2).toString();
        String Bname = TableChemical.getValueAt(row, 3).toString();
        String Sid = TableChemical.getValueAt(row, 4).toString();
        String Sname = TableChemical.getValueAt(row, 5).toString();
        ((JTextField)CFManufactureDate.getDateEditor().getUiComponent()).setText( model.getValueAt(row,6).toString());
        ((JTextField)CFExpireDate.getDateEditor().getUiComponent()).setText( model.getValueAt(row,7).toString());
        String price = TableChemical.getValueAt(row, 8).toString();
        String Sprice = TableChemical.getValueAt(row, 9).toString();
        String Quantity = TableChemical.getValueAt(row, 10).toString();

        CFItemID.setText(Iid);
        CFItemName.setText(Iname);
        CFBrandID.setText(Bid);
        CFBrandName.setText(Bname);
        CFSupplierID.setText(Sid);
        CFSupplierName.setText(Sname);
        CFPrice.setText(price);
        CFSellingPrice.setText(Sprice);
        AQChemical.setText(Quantity);
    }//GEN-LAST:event_TableChemicalMouseClicked

    private void TableWaterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableWaterMouseClicked
       // DefaultTableModel model=(DefaultTableModel) TableWater.getModel();
        int row = TableWater.getSelectedRow();

        String Iid = TableWater.getValueAt(row, 0).toString();
        String Iname = TableWater.getValueAt(row, 1).toString();
        String Bid = TableWater.getValueAt(row, 2).toString();
        String Bname = TableWater.getValueAt(row, 3).toString();
        String Sid = TableWater.getValueAt(row, 4).toString();
        String Sname = TableWater.getValueAt(row, 5).toString();
        String Price = TableWater.getValueAt(row, 6).toString();
        String Gauge = TableWater.getValueAt(row, 7).toString();
        String Sprice = TableWater.getValueAt(row, 8).toString();
        String Size = TableWater.getValueAt(row, 9).toString();
        String Quantity = TableWater.getValueAt(row, 10).toString();
        

        WItemID.setText(Iid);
        WItemName.setSelectedItem(Iname);
        WBrandID.setText(Bid);
        WBrandName.setSelectedItem(Bname);
        WSupplierID.setText(Sid);
        WSupplierName.setText(Sname);
        WPrice.setText(Price);
        WGauge.setSelectedItem(Gauge);
        WSellingPrice.setText(Sprice);
        WSize.setSelectedItem(Size);
        AQWater.setText(Quantity);
        
    }//GEN-LAST:event_TableWaterMouseClicked

    private void TableRoofingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRoofingMouseClicked
        clearRoofing();         

        
        
//        DefaultTableModel model=(DefaultTableModel) TableRoofing.getModel();
        int row = TableRoofing.getSelectedRow();

        String Iid = TableRoofing.getValueAt(row, 0).toString();
        String Category = TableRoofing.getValueAt(row, 1).toString();
        String Type =TableRoofing.getValueAt(row, 2).toString();
        String Bid = TableRoofing.getValueAt(row, 3).toString();
        String Bname = TableRoofing.getValueAt(row, 4).toString();
        String Sid = TableRoofing.getValueAt(row, 5).toString();
        String Sname = TableRoofing.getValueAt(row, 6).toString();
        String size = TableRoofing.getValueAt(row, 7).toString();
        String colour = TableRoofing.getValueAt(row, 8).toString();
        String Pprice = TableRoofing.getValueAt(row, 9).toString();
        String Sprice = TableRoofing.getValueAt(row, 10).toString();
        String Quantity = TableRoofing.getValueAt(row, 11).toString();
        
        
       

        RItemID.setText(Iid);
        RCategory.setSelectedItem(Category);
        RType.setSelectedItem(Type);
        RBrandID.setText(Bid);
        RBrandName.setText(Bname);
        RSupplierID.setText(Sid);
        RSupplierName.setText(Sname);
        RPurchasingPrice.setText(Pprice);
        RSellingPrice.setText(Sprice);
        AQRoofing.setText(Quantity);
        
        if(size.equals("1ft")||size.equals("2ft")||size.equals("4ft")||size.equals("6ft")||size.equals("8ft")||size.equals("10ft")||size.equals("12ft")||size.equals("Select")||size.equals("None")||size.equals(""))
        {
            RSizeSelect.enable();
            RSizeManu.disable();
            RSSelect.setSelected(true);
            RSizeSelect.setSelectedItem(size);
            RSizeManu.setText("");
        }
        else
        {
            RSizeManu.enable();
            RSizeSelect.disable();
            RSManu.setSelected(true);
            RSizeSelect.setSelectedItem("");
            RSizeManu.setText(size);
        }
        if(colour.equals("Select")||colour.equals("None")||colour.equals("Sky Blue")||colour.equals("Navy Blue")||colour.equals("Dark green")||colour.equals("Maroon")||colour.equals("Brown"))
        {
            RColourSelect.enable();
            RColourManu.disable();
            RCSelect.setSelected(true);
            RColourSelect.setSelectedItem(colour);
            RColourManu.setText("");
        }
        else
        {
            RColourManu.enable();
            RColourSelect.disable();
            RCManu.setSelected(true);
            RColourSelect.setSelectedItem("");
            RColourManu.setText(colour);
        }
      
    }//GEN-LAST:event_TableRoofingMouseClicked

    private void TableOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableOtherMouseClicked
        DefaultTableModel model=(DefaultTableModel) TableOther.getModel();
        int row = TableOther.getSelectedRow();

        String Iid = TableOther.getValueAt(row, 0).toString();
        String Iname = TableOther.getValueAt(row, 1).toString();
        String Bid = TableOther.getValueAt(row, 2).toString();
        String Bname = TableOther.getValueAt(row, 3).toString();
        String Sid = TableOther.getValueAt(row, 4).toString();
        String Sname = TableOther.getValueAt(row, 5).toString();
        
        String Colour =TableOther.getValueAt(row, 6).toString();
        String Size =TableOther.getValueAt(row, 7).toString();
        String Pprice = TableOther.getValueAt(row, 8).toString();
        String Sprice = TableOther.getValueAt(row, 9).toString();
        String Quantity = TableOther.getValueAt(row, 10).toString();
        

        OItemID.setText(Iid);
        OItemName.setText(Iname);
        OBrandID.setText(Bid);
        OBrandName.setText(Bname);
        OSupplierID.setText(Sid);
        OSupplierName.setText(Sname);
        OColour.setText(Colour);
        OSize.setText(Size);
        OPurchasingPrice.setText(Pprice);
        OSellingPrice.setText(Sprice);
        AQOther.setText(Quantity);
    }//GEN-LAST:event_TableOtherMouseClicked

    private void TableOtherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableOtherMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableOtherMouseEntered

    private void PDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PDiscountKeyReleased
       int L= Integer.parseInt(PLablePrice.getText());
      int D=Integer.parseInt(PDiscount.getText());
      int P=L-(L*D/100);
      PPurchingPrice.setText(P+"");
    }//GEN-LAST:event_PDiscountKeyReleased

    private void PExpireDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PExpireDateKeyReleased
        
            
            
            
    }//GEN-LAST:event_PExpireDateKeyReleased

    private void PExpireDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PExpireDateKeyTyped
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        Date d1=null;
        Date d2=null;
        
        try {
            
           d1=format.parse(format.format(PManufactureDate.getDate()));
            d2=format.parse(format.format(PExpireDate.getDate()));
            
            long diff=d2.getTime()-d1.getTime();
            
            //long hours=diff/(60*60*1000);
            //long days=hours/24;
                    
            if(diff<0){
            
                JOptionPane.showMessageDialog(null,"End date Should be Upcomming Date");
                PManufactureDate.setDate(null);
                PExpireDate.setDate(null);
            }
        }  
      catch(Exception e)
      {
      
      }
    }//GEN-LAST:event_PExpireDateKeyTyped

    private void CPurchasingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CPurchasingPriceKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_CPurchasingPriceKeyTyped

    private void RPurchasingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPurchasingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_RPurchasingPriceKeyTyped

    private void RSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_RSellingPriceKeyTyped

    private void WPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WPriceKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_WPriceKeyTyped

    private void WSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_WSellingPriceKeyTyped

    private void CFPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_CFPriceKeyTyped

    private void CFSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_CFSellingPriceKeyTyped

    private void OPurchasingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OPurchasingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_OPurchasingPriceKeyTyped

    private void OSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OSellingPriceKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_OSellingPriceKeyTyped

    private void CItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CItemIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = CItemID.getText();
       
        String q=null;
        
       
        try{

                q = "SELECT * FROM Construction WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableConstruction.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }  
    }//GEN-LAST:event_CItemIDKeyReleased

    private void PItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PItemIDKeyReleased
       PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = PItemID.getText();
        
        String q=null;
        
      
        try{

            q = "SELECT * FROM Paint_Thinner WHERE ItemID LIKE '%"+itemID+"%'"; 
   
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
            rs_itemList = ps_itemList.executeQuery();
            TablePaint.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_PItemIDKeyReleased

    private void RItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RItemIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = RItemID.getText();
        
        String q=null;
        
      
        try{

        q = "SELECT * FROM Roofing_Fitting WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableRoofing.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_RItemIDKeyReleased

    private void WItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WItemIDKeyReleased
         PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = WItemID.getText();
        
        String q=null;
        
        
        try{


                q = "SELECT * FROM WaterPipe_Fitting WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableWater.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_WItemIDKeyReleased

    private void CFItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFItemIDKeyReleased
         PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = CFItemID.getText();
        
        String q=null;
        

        try{

                q = "SELECT * FROM Chemical_Farming WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableChemical.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_CFItemIDKeyReleased

    private void OItemIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OItemIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = OItemID.getText();
        
        String q=null;
        
     
        try{


                q = "SELECT * FROM OtherItem WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableOther.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_OItemIDKeyReleased

    private void RSSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSSelectActionPerformed

    private void PaintIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaintIDActionPerformed
        createPntID();       
    }//GEN-LAST:event_PaintIDActionPerformed

    private void ConstructionIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConstructionIDActionPerformed
        createConID();
    }//GEN-LAST:event_ConstructionIDActionPerformed

    private void RoofingIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoofingIDActionPerformed
        createRofID();
    }//GEN-LAST:event_RoofingIDActionPerformed

    private void QConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QConstructionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QConstructionActionPerformed

    private void UpdateQtyConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyConstructionActionPerformed

        String qty = QConstruction.getText();
        String id = CItemID.getText();
        String availableQty = AQConstruction.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM Construction WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdateConstruction();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            
            
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
            
           
        }
        catch(Exception e){
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
         
        
      
      
        AQConstruction.setText(availableQty);
        tableloadConstuction1();
        QConstruction.setText("");
        
    }//GEN-LAST:event_UpdateQtyConstructionActionPerformed

    private void AQConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQConstructionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQConstructionActionPerformed

    private void WaterIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WaterIDActionPerformed
            createWatID();
    }//GEN-LAST:event_WaterIDActionPerformed

    private void PColourCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PColourCodeKeyReleased
        
    }//GEN-LAST:event_PColourCodeKeyReleased

    private void UpdateQtyConstructionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyConstructionKeyReleased
        
    }//GEN-LAST:event_UpdateQtyConstructionKeyReleased

    private void UpdateQtyConstructionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyConstructionMouseClicked

    }//GEN-LAST:event_UpdateQtyConstructionMouseClicked

    private void AQPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQPaintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQPaintActionPerformed

    private void QPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QPaintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QPaintActionPerformed

    private void UpdateQtyPaintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyPaintMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyPaintMouseClicked

    private void UpdateQtyPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyPaintActionPerformed
        String qty = QPaint.getText();
        String id = PItemID.getText();
        String availableQty = AQPaint.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM Paint_Thinner WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdatePaint();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
           
        }
        catch(Exception e){
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
        AQPaint.setText(availableQty);
        tableloadPaint1();
        QPaint.setText("");
    }//GEN-LAST:event_UpdateQtyPaintActionPerformed

    private void UpdateQtyPaintKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyPaintKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyPaintKeyReleased

    private void AQRoofingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQRoofingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQRoofingActionPerformed

    private void QRoofingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QRoofingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QRoofingActionPerformed

    private void UpdateQtyRoofingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyRoofingMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyRoofingMouseClicked

    private void UpdateQtyRoofingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyRoofingActionPerformed
       String qty = QRoofing.getText();
        String id = RItemID.getText();
        String availableQty = AQRoofing.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM Roofing_Fitting WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdateRoofing();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
           
        }
        catch(Exception e){
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
        AQRoofing.setText(availableQty);
        tableloadRoofing1();
        QRoofing.setText("");
    }//GEN-LAST:event_UpdateQtyRoofingActionPerformed

    private void UpdateQtyRoofingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyRoofingKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyRoofingKeyReleased

    private void AQWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQWaterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQWaterActionPerformed

    private void QWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QWaterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QWaterActionPerformed

    private void UpdateQtyWaterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyWaterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyWaterMouseClicked

    private void UpdateQtyWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyWaterActionPerformed
        String qty = QWater.getText();
        String id = WItemID.getText();
        String availableQty = AQWater.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM WaterPipe_Fitting WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdateWater();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
           
        }
        catch(Exception e){
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
        AQWater.setText(availableQty);
        tableloadWater1();
        QWater.setText("");
    }//GEN-LAST:event_UpdateQtyWaterActionPerformed

    private void UpdateQtyWaterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyWaterKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyWaterKeyReleased

    private void AQChemicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQChemicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQChemicalActionPerformed

    private void QChemicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QChemicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QChemicalActionPerformed

    private void UpdateQtyChemicalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyChemicalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyChemicalMouseClicked

    private void UpdateQtyChemicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyChemicalActionPerformed
         String qty = QChemical.getText();
        String id = CFItemID.getText();
        String availableQty = AQChemical.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM Chemical_Farming WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdateChemical();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
           
        }
        catch(Exception e){
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
        AQChemical.setText(availableQty);
        tableloadChemical1();
        QChemical.setText("");
    }//GEN-LAST:event_UpdateQtyChemicalActionPerformed

    private void UpdateQtyChemicalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyChemicalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyChemicalKeyReleased

    private void AQOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AQOtherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AQOtherActionPerformed

    private void QOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QOtherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QOtherActionPerformed

    private void UpdateQtyOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateQtyOtherMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyOtherMouseClicked

    private void UpdateQtyOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateQtyOtherActionPerformed
        String qty = QOther.getText();
        String id = OItemID.getText();
        String availableQty = AQOther.getText();
   
        String q = "INSERT INTO StockQty (ItemID, Qty) VALUES('"+ id +"' , '"+ qty +"')"; 
        String sql = "SELECT Qty FROM OtherItem WHERE ItemID = '"+ id +"' ";
  
        try{
            pst = conn.prepareStatement(q);
            pst.execute();
            
            QtyUpdateOther();
            
            pst2 = conn.createStatement();
            rs = pst2.executeQuery(sql);
        
            while(rs.next()){
                availableQty = rs.getString("Qty");
               
            } 
           
        }
        catch(Exception e){
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
        AQOther.setText(availableQty);
        tableloadOther1();
        QOther.setText("");
    }//GEN-LAST:event_UpdateQtyOtherActionPerformed

    private void UpdateQtyOtherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UpdateQtyOtherKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateQtyOtherKeyReleased

    private void OtherIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherIDActionPerformed
        createOtrID();
    }//GEN-LAST:event_OtherIDActionPerformed

    private void ChemicalIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChemicalIDActionPerformed
        createChemID();
    }//GEN-LAST:event_ChemicalIDActionPerformed

    private void TablePaintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePaintMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TablePaintMouseEntered

    private void AQConstructionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQConstructionKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_AQConstructionKeyTyped

    private void QConstructionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QConstructionKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_QConstructionKeyTyped

    private void PColourCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PColourCodeKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_PColourCodeKeyTyped

    private void AQRoofingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQRoofingKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_AQRoofingKeyTyped

    private void QRoofingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QRoofingKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_QRoofingKeyTyped

    private void AQWaterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQWaterKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_AQWaterKeyTyped

    private void QWaterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QWaterKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_QWaterKeyTyped

    private void AQChemicalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQChemicalKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_AQChemicalKeyTyped

    private void QChemicalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QChemicalKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_QChemicalKeyTyped

    private void AQOtherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQOtherKeyTyped
        char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_AQOtherKeyTyped

    private void QOtherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QOtherKeyTyped
       char c = evt.getKeyChar();

        if(Character.isLetter(c))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Enter only numbers");
        }
    }//GEN-LAST:event_QOtherKeyTyped

    private void SearchConIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchConIDKeyReleased
       PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchConID.getText();
       
        String q=null;
        
       
        try{

                q = "SELECT * FROM Construction WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableConstruction.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchConIDKeyReleased

    private void SearchPntIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPntIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchPntID.getText();
        
        String q=null;
        
      
        try{

            q = "SELECT * FROM Paint_Thinner WHERE ItemID LIKE '%"+itemID+"%'"; 
   
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
            rs_itemList = ps_itemList.executeQuery();
            TablePaint.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchPntIDKeyReleased

    private void SearchRofIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchRofIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchRofID.getText();
        
        String q=null;
        
      
        try{

        q = "SELECT * FROM Roofing_Fitting WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableRoofing.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchRofIDKeyReleased

    private void SearchWatIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchWatIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchWatID.getText();
        
        String q=null;
        
        
        try{


                q = "SELECT * FROM WaterPipe_Fitting WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableWater.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchWatIDKeyReleased

    private void SearchCheIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchCheIDKeyReleased
        PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchCheID.getText();
        
        String q=null;
        

        try{

                q = "SELECT * FROM Chemical_Farming WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableChemical.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchCheIDKeyReleased

    private void SearchOthID5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchOthID5KeyReleased
         PreparedStatement ps_category=null;
        ResultSet rs_category = null;        
        PreparedStatement ps_itemList= null;
        ResultSet rs_itemList = null;
        
        String itemID = SearchOthID5.getText();
        
        String q=null;
        
     
        try{


                q = "SELECT * FROM OtherItem WHERE ItemID LIKE '%"+itemID+"%'";

           
            ps_itemList = conn.prepareStatement(q);
            rs_itemList = ps_itemList.executeQuery();

            
           rs_itemList = ps_itemList.executeQuery();
          TableOther.setModel(DbUtils.resultSetToTableModel(rs_itemList));
                
                
        }catch(IndexOutOfBoundsException e){
            System.out.print("IndexOutOfBoundsException thrown\n");
        
        }catch (Exception e){
            
            System.out.print(e + " MAIN");
            e.printStackTrace();
        }  finally {
            
            try{            
                if (ps_category != null) {
                    ps_category.close();
                }
                if (rs_category != null) {
                    rs_category.close();
                }
                if (ps_itemList != null) {
                    ps_itemList.close();
                }
                if (rs_itemList != null) {
                    rs_itemList.close();
                }
                
                conn.setAutoCommit(true);
                
            }catch(Exception e){
                System.out.println(e);
            }
        } 
    }//GEN-LAST:event_SearchOthID5KeyReleased

    private void CItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CItemNameKeyPressed
                                           
       if(evt.getKeyCode()==10){
            if(CItemName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Item Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CBrandID.grabFocus();    
            }
        }
    
    }//GEN-LAST:event_CItemNameKeyPressed

    private void CBrandIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBrandIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CBrandIDKeyReleased

    private void CBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBrandIDKeyPressed
       if(evt.getKeyCode()==10){
            if(CBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_CBrandIDKeyPressed

    private void CBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBrandNameKeyPressed
        if(evt.getKeyCode()==10){
            if(CBrandName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_CBrandNameKeyPressed

    private void CSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CSupplierIDKeyPressed
       if(evt.getKeyCode()==10){
            if(CSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_CSupplierIDKeyPressed

    private void CSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CSupplierNameKeyPressed
        if(evt.getKeyCode()==10){
            if(CSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CPurchasingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_CSupplierNameKeyPressed

    private void CPurchasingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CPurchasingPriceKeyPressed
        if(evt.getKeyCode()==10){
            if(CPurchasingPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Purchasing Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_CPurchasingPriceKeyPressed

    private void AQConstructionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AQConstructionKeyPressed
       
    }//GEN-LAST:event_AQConstructionKeyPressed

    private void PCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PCategoryKeyPressed
        if(evt.getKeyCode()==10){
            if(PCategory.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Category", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PBrandID.grabFocus();    
            }
        }
    }//GEN-LAST:event_PCategoryKeyPressed

    private void PBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PBrandIDKeyPressed
       if(evt.getKeyCode()==10){
            if(PBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_PBrandIDKeyPressed

    private void PBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PBrandNameKeyPressed
       if(evt.getKeyCode()==10){
            if(PBrandName.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_PBrandNameKeyPressed

    private void PSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSupplierIDKeyPressed
       if(evt.getKeyCode()==10){
            if(PSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_PSupplierIDKeyPressed

    private void PSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSupplierNameKeyPressed
       if(evt.getKeyCode()==10){
            if(PSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PColourCode.grabFocus();    
            }
        }
    }//GEN-LAST:event_PSupplierNameKeyPressed

    private void PColourCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PColourCodeKeyPressed
        if(evt.getKeyCode()==10){
            if(PColourCode.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Colour Code", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PCapacity.grabFocus();    
            }
        }
    }//GEN-LAST:event_PColourCodeKeyPressed

    private void PManufactureDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PManufactureDateKeyPressed
        
    }//GEN-LAST:event_PManufactureDateKeyPressed

    private void PCapacityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PCapacityKeyPressed
         if(evt.getKeyCode()==10){
            if(PCapacity.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Capacity", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PLablePrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_PCapacityKeyPressed

    private void PExpireDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PExpireDateKeyPressed
        
    }//GEN-LAST:event_PExpireDateKeyPressed

    private void PLablePriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PLablePriceKeyPressed
        if(evt.getKeyCode()==10){
            if(PLablePrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Lable Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PDiscount.grabFocus();    
            }
        }
    }//GEN-LAST:event_PLablePriceKeyPressed

    private void PDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PDiscountKeyPressed
        if(evt.getKeyCode()==10){
            if(PDiscount.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Discount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                PSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_PDiscountKeyPressed

    private void PPurchingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PPurchingPriceKeyPressed
       
    }//GEN-LAST:event_PPurchingPriceKeyPressed

    private void PSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSellingPriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSellingPriceKeyPressed

    private void RCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RCategoryKeyPressed
        if(evt.getKeyCode()==10){
            if(RCategory.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Category", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RType.grabFocus();    
            }
        }
    }//GEN-LAST:event_RCategoryKeyPressed

    private void RTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RTypeKeyPressed
        if(evt.getKeyCode()==10){
            if(RType.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Type", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RBrandID.grabFocus();    
            }
        }
    }//GEN-LAST:event_RTypeKeyPressed

    private void RBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RBrandIDKeyPressed
        if(evt.getKeyCode()==10){
            if(RBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_RBrandIDKeyPressed

    private void RBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RBrandNameKeyPressed
       if(evt.getKeyCode()==10){
            if(RBrandName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_RBrandNameKeyPressed

    private void RSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RSupplierIDKeyPressed
       if(evt.getKeyCode()==10){
            if(RSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_RSupplierIDKeyPressed

    private void RSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RSupplierNameKeyPressed
        if(evt.getKeyCode()==10){
            if(RSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RPurchasingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_RSupplierNameKeyPressed

    private void RPurchasingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPurchasingPriceKeyPressed
        if(evt.getKeyCode()==10){
            if(RPurchasingPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Purchasing Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                RSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_RPurchasingPriceKeyPressed

    private void RSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RSellingPriceKeyPressed
      
    }//GEN-LAST:event_RSellingPriceKeyPressed

    private void WItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WItemNameKeyPressed
        if(evt.getKeyCode()==10){
            if(WItemName.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Item Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WBrandID.grabFocus();    
            }
        }
    }//GEN-LAST:event_WItemNameKeyPressed

    private void WBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBrandIDKeyPressed
        if(evt.getKeyCode()==10){
            if(WBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_WBrandIDKeyPressed

    private void WBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WBrandNameKeyPressed
        if(evt.getKeyCode()==10){
            if(WBrandName.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_WBrandNameKeyPressed

    private void WSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSupplierIDKeyPressed
        if(evt.getKeyCode()==10){
            if(WSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_WSupplierIDKeyPressed

    private void WSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSupplierNameKeyPressed
        if(evt.getKeyCode()==10){
            if(WSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_WSupplierNameKeyPressed

    private void WPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WPriceKeyPressed
        if(evt.getKeyCode()==10){
            if(WPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WGauge.grabFocus();    
            }
        }
    }//GEN-LAST:event_WPriceKeyPressed

    private void WGaugeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WGaugeKeyPressed
       if(evt.getKeyCode()==10){
            if(WGauge.getSelectedItem()=="Select")
            {
                JOptionPane.showMessageDialog(null, "Enter a Gauge", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_WGaugeKeyPressed

    private void WSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSellingPriceKeyPressed
        if(evt.getKeyCode()==10){
            if(WSellingPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Selling Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                WSize.grabFocus();    
            }
        }
    }//GEN-LAST:event_WSellingPriceKeyPressed

    private void WSizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WSizeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WSizeKeyPressed

    private void CFItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFItemNameKeyPressed
        if(evt.getKeyCode()==10){
            if(CFItemName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Item Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFBrandID.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFItemNameKeyPressed

    private void CFBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFBrandIDKeyPressed
       if(evt.getKeyCode()==10){
            if(CFBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFBrandIDKeyPressed

    private void CFBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFBrandNameKeyPressed
       if(evt.getKeyCode()==10){
            if(CFBrandName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFBrandNameKeyPressed

    private void CFSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFSupplierIDKeyPressed
        if(evt.getKeyCode()==10){
            if(CFSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFSupplierIDKeyPressed

    private void CFSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFSupplierNameKeyPressed
       if(evt.getKeyCode()==10){
            if(CFSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFSupplierNameKeyPressed

    private void CFPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFPriceKeyPressed
       if(evt.getKeyCode()==10){
            if(CFPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                CFSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_CFPriceKeyPressed

    private void CFSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CFSellingPriceKeyPressed
        
    }//GEN-LAST:event_CFSellingPriceKeyPressed

    private void OItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OItemNameKeyPressed
       if(evt.getKeyCode()==10){
            if(OItemName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Item Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OBrandID.grabFocus();    
            }
        }
    }//GEN-LAST:event_OItemNameKeyPressed

    private void OBrandIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OBrandIDKeyPressed
       if(evt.getKeyCode()==10){
            if(OBrandID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OBrandName.grabFocus();    
            }
        }
    }//GEN-LAST:event_OBrandIDKeyPressed

    private void OBrandNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OBrandNameKeyPressed
       if(evt.getKeyCode()==10){
            if(OBrandName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Brand Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OSupplierID.grabFocus();    
            }
        }
    }//GEN-LAST:event_OBrandNameKeyPressed

    private void OSupplierIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OSupplierIDKeyPressed
        if(evt.getKeyCode()==10){
            if(OSupplierID.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OSupplierName.grabFocus();    
            }
        }
    }//GEN-LAST:event_OSupplierIDKeyPressed

    private void OSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OSupplierNameKeyPressed
       if(evt.getKeyCode()==10){
            if(OSupplierName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Supplier Name", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OColour.grabFocus();    
            }
        }
    }//GEN-LAST:event_OSupplierNameKeyPressed

    private void OColourKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OColourKeyPressed
       if(evt.getKeyCode()==10){
            if(OColour.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Colour", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OSize.grabFocus();    
            }
        }
    }//GEN-LAST:event_OColourKeyPressed

    private void OSizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OSizeKeyPressed
        if(evt.getKeyCode()==10){
            if(OSize.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Size", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OPurchasingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_OSizeKeyPressed

    private void OPurchasingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OPurchasingPriceKeyPressed
        if(evt.getKeyCode()==10){
            if(OPurchasingPrice.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter a Purchasing Price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                OSellingPrice.grabFocus();    
            }
        }
    }//GEN-LAST:event_OPurchasingPriceKeyPressed

    private void OSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OSellingPriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OSellingPriceKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AQChemical;
    private javax.swing.JTextField AQConstruction;
    private javax.swing.JTextField AQOther;
    private javax.swing.JTextField AQPaint;
    private javax.swing.JTextField AQRoofing;
    private javax.swing.JTextField AQWater;
    private javax.swing.JTextField CBrandID;
    private javax.swing.JTextField CBrandName;
    private javax.swing.JTextField CFBrandID;
    private javax.swing.JTextField CFBrandName;
    private com.toedter.calendar.JDateChooser CFExpireDate;
    private javax.swing.JTextField CFItemID;
    private javax.swing.JTextField CFItemName;
    private com.toedter.calendar.JDateChooser CFManufactureDate;
    private javax.swing.JTextField CFPrice;
    private javax.swing.JTextField CFSellingPrice;
    private javax.swing.JTextField CFSupplierID;
    private javax.swing.JTextField CFSupplierName;
    private javax.swing.JTextField CItemID;
    private javax.swing.JTextField CItemName;
    private javax.swing.JRadioButton CLManu;
    private javax.swing.JRadioButton CLSelect;
    private javax.swing.JTextField CLengthManu;
    private javax.swing.JComboBox CLengthSelect;
    private javax.swing.JTextField CPurchasingPrice;
    private javax.swing.JTextField CSellingPrice;
    private javax.swing.JTextField CSizeManu;
    private javax.swing.JComboBox CSizeSelect;
    private javax.swing.JTextField CSupplierID;
    private javax.swing.JTextField CSupplierName;
    private javax.swing.JButton ChemicalClear;
    private javax.swing.JButton ChemicalID;
    private javax.swing.JButton ChemicalSave;
    private javax.swing.JButton ClearConstruction;
    private javax.swing.JButton ClearPaint;
    private javax.swing.JButton ClrearWater;
    private javax.swing.JButton ConstructionID;
    private javax.swing.JButton DelatePaint;
    private javax.swing.JButton DeleteChemical;
    private javax.swing.JButton DeleteConstruction;
    private javax.swing.JButton DeleteOther;
    private javax.swing.JButton DeleteWater;
    private javax.swing.JTextField OBrandID;
    private javax.swing.JTextField OBrandName;
    private javax.swing.JTextField OColour;
    private javax.swing.JTextField OItemID;
    private javax.swing.JTextField OItemName;
    private javax.swing.JTextField OPurchasingPrice;
    private javax.swing.JTextField OSellingPrice;
    private javax.swing.JTextField OSize;
    private javax.swing.JTextField OSupplierID;
    private javax.swing.JTextField OSupplierName;
    private javax.swing.JButton OtherClear;
    private javax.swing.JButton OtherID;
    private javax.swing.JButton OtherSave;
    private javax.swing.JTextField PBrandID;
    private javax.swing.JComboBox PBrandName;
    private javax.swing.JComboBox PCapacity;
    private javax.swing.JComboBox PCategory;
    private javax.swing.JTextField PColourCode;
    private javax.swing.JTextField PDiscount;
    private com.toedter.calendar.JDateChooser PExpireDate;
    private javax.swing.JTextField PItemID;
    private javax.swing.JTextField PLablePrice;
    private com.toedter.calendar.JDateChooser PManufactureDate;
    private javax.swing.JTextField PPurchingPrice;
    private javax.swing.JTextField PSellingPrice;
    private javax.swing.JTextField PSupplierID;
    private javax.swing.JTextField PSupplierName;
    private javax.swing.JButton PaintID;
    private javax.swing.JTextField QChemical;
    private javax.swing.JTextField QConstruction;
    private javax.swing.JTextField QOther;
    private javax.swing.JTextField QPaint;
    private javax.swing.JTextField QRoofing;
    private javax.swing.JTextField QWater;
    private javax.swing.JTextField RBrandID;
    private javax.swing.JTextField RBrandName;
    private javax.swing.JRadioButton RCManu;
    private javax.swing.JRadioButton RCSelect;
    private javax.swing.JComboBox RCategory;
    private javax.swing.JTextField RColourManu;
    private javax.swing.JComboBox RColourSelect;
    private javax.swing.JTextField RItemID;
    private javax.swing.JTextField RPurchasingPrice;
    private javax.swing.JRadioButton RSManu;
    private javax.swing.JRadioButton RSSelect;
    private javax.swing.JTextField RSellingPrice;
    private javax.swing.JTextField RSizeManu;
    private javax.swing.JComboBox RSizeSelect;
    private javax.swing.JTextField RSupplierID;
    private javax.swing.JTextField RSupplierName;
    private javax.swing.JComboBox RType;
    private javax.swing.JButton RoofingClear;
    private javax.swing.JButton RoofingDelete;
    private javax.swing.JButton RoofingID;
    private javax.swing.JButton RoofingSave;
    private javax.swing.JButton SaveConstruction;
    private javax.swing.JButton SavePaint;
    private javax.swing.JButton SaveWater;
    private javax.swing.JTextField SearchCheID;
    private javax.swing.JTextField SearchConID;
    private javax.swing.JTextField SearchOthID5;
    private javax.swing.JTextField SearchPntID;
    private javax.swing.JTextField SearchRofID;
    private javax.swing.JTextField SearchWatID;
    private javax.swing.JTable TableChemical;
    private javax.swing.JTable TableConstruction;
    private javax.swing.JTable TableOther;
    private javax.swing.JTable TablePaint;
    private javax.swing.JTable TableRoofing;
    private javax.swing.JTable TableWater;
    private javax.swing.JButton UpdateChemical;
    private javax.swing.JButton UpdateConstruction;
    private javax.swing.JButton UpdatePaint;
    private javax.swing.JButton UpdateQtyChemical;
    private javax.swing.JButton UpdateQtyConstruction;
    private javax.swing.JButton UpdateQtyOther;
    private javax.swing.JButton UpdateQtyPaint;
    private javax.swing.JButton UpdateQtyRoofing;
    private javax.swing.JButton UpdateQtyWater;
    private javax.swing.JButton UpdateRoofing;
    private javax.swing.JButton UpdateWater;
    private javax.swing.JTextField WBrandID;
    private javax.swing.JComboBox WBrandName;
    private javax.swing.JComboBox WGauge;
    private javax.swing.JTextField WItemID;
    private javax.swing.JComboBox WItemName;
    private javax.swing.JTextField WPrice;
    private javax.swing.JTextField WSellingPrice;
    private javax.swing.JComboBox WSize;
    private javax.swing.JTextField WSupplierID;
    private javax.swing.JTextField WSupplierName;
    private javax.swing.JButton WaterID;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton27;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
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
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton smanu;
    private javax.swing.JRadioButton sselect;
    // End of variables declaration//GEN-END:variables
//
//private String SIZE;
//private String LENGTH;

}
