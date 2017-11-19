/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL-PC
 */
public class me {
    
    
    public static boolean isAlph(char c)
    {
        boolean p=false;
        
            if(c <= 'z' && c>='a' || c<='Z' && c>='A' || c<='9' && c >= '0')
            {
                p = true;
            }
            else
            {
                p=false;
            }
    
        return p;
    }
    
//    public static String RearrangeString(String str,char ch,int pos)
//    {
//        String done="";
//    
//            int size = str.length();
//            char temp1,temp2=ch;
//            char[] broke = str.toCharArray();
//            
//            for(int i=pos;i<size;i++)
//            {
//                temp1=broke[i];
//                broke[i]=temp2;
//                temp2=broke[i+1];
//                broke[i+1]=temp1;
//                
//            }
//            
//    
//        return done;
//    }
    
    public static String addLetter(char letter, int position, String word){
    String toSupport = "";

    if(position == -1){
       toSupport += letter+word;
    } 
    else if(position == 0)
    {
        toSupport += word+letter;
    }
    else{
        String temp = word.substring(0, position+1);
        toSupport += temp + Character.toString(letter) + word.substring(position+1, word.length());
    }
    return toSupport;
    
    }
    
    
    public static boolean nicCheck(String nic)
    {
        boolean valid=false;
        int size=nic.length();
        
        if(size==10)
        {
            for(int i=0;i<size-1;i++)
            {
                if(Character.isDigit(nic.charAt(i)))
                {
                    valid=true;
                }
                else
                {
                    valid=false;
                    break;
                }
                
                
                if(nic.charAt(9)=='V')
                {
                    valid = true;
                }
                else
                {
                    valid = false;
                    break;
                }
            }
        }
        else if( size==12 )
        {
           for(int i=0;i<size;i++)
           {
               if(Character.isDigit(nic.charAt(i)))
                {
                    valid=true;
                }
                else
                {
                    valid=false;
                    break;
                }
           }
        }
        else
        {
            valid=false;
        }
        return valid;
    }
    
    public static boolean phnCheck(String no)
    {
        boolean valid=false;
        
        if(no.length()==10)
        {
        valid = true;
        }
        
        return valid;
    }
    public static boolean dateCheck(String dateformat,Date cd, Date doi) {

        

        long diff = cd.getTime() - doi.getTime();
        int diffDays = (int) (diff / (24 * 1000 * 60 * 60));

        if (diffDays > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public static Date getStringDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        Date longDate =null;
        try {
            longDate = (sdf.parse(date));
            //System.out.println(sdf.format(jDateChooser1.getDate()) );        // TODO add your handling code here:
        } catch (ParseException ex) {
            //Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
        }
        
        return  longDate;

    }
    
    public static Date getStringDate1(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy");
        Date longDate =null;
        try {
            longDate = (sdf.parse(date));
            //System.out.println(sdf.format(jDateChooser1.getDate()) );        // TODO add your handling code here:
        } catch (ParseException ex) {
            //Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println();
        }
        
        return  longDate;

    }


    
}

    
  

