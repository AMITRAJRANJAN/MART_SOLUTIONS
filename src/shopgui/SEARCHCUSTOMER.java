package shopgui;

import shop.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import shop.Customer;

public class SEARCHCUSTOMER
{
    public static void main(String[] args)
    {
        SEARCHCUSTOMERFRAME searchcustomerfarme=new SEARCHCUSTOMERFRAME();
    }
}

class SEARCHCUSTOMERFRAME extends JFrame implements ActionListener, MouseListener
{
    JLabel totalduesesL,searchcustomerL,customernameL,customermobileL,duesamountL,duesdateL,amountpaidL,billernameL,billingtimeL;
    JTextField customernameT,customermobileT,duesamountT,duesdateT,amountpaidT;
    JButton searchcustomerB,clicktoseeB,clearduesB,resetB,showallB;
    JTable customerduesesTB;
    DefaultTableModel customerduesesmodelM;
    JScrollPane customerduesesscrollpaneSP;
    String[] columns={"Customer Name","Customer Mobile","Dues Date","Dues Amount"};
    String[] data=new String[4];
    Customer C;
    Currentuser U;
    Database db;
    public SEARCHCUSTOMERFRAME()
    {
        new JFrame("Search Customer");
         setLayout(null);
         setLocation(25,135);
         setSize(1150,525);
         getContentPane().setBackground(Color.orange);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setVisible(true);
         
                                    C=new Customer();  
                                    U=new Currentuser();
                                    db=new Database();
                                    searchcustomerL=new JLabel("Search customer",JLabel.CENTER);
                                     customernameL=new JLabel("Customer Name");
                                     customernameT=new JTextField();
                                     customermobileL=new JLabel("Customer Mobile");
                                     customermobileT=new JTextField();
                                     searchcustomerB=new JButton("Search Customer");
                                     duesamountL=new JLabel("Dues Amount");
                                     duesamountT=new JTextField();
                                     duesdateL=new JLabel("Dues Date");
                                     duesdateT=new JTextField();
                                     amountpaidL=new JLabel("Amount to be paid");
                                     amountpaidT=new JTextField();
                                     clearduesB=new JButton("Clear Dues");
                                     resetB=new JButton("Reset");
                                     billernameL=new JLabel("Biller Name :"+U.username);
                                     billingtimeL=new JLabel("Billing Time :"+U.hour+":"+U.minute+":"+U.second);
                                     customerduesesTB=new JTable();
                                     customerduesesmodelM=new DefaultTableModel();
                                     customerduesesmodelM.setColumnIdentifiers(columns);
                                     customerduesesTB.setModel(customerduesesmodelM);
                                     customerduesesscrollpaneSP=new JScrollPane(customerduesesTB);
                                     totalduesesL=new JLabel("Total customer having dueses :"+C.duesescustomer,JLabel.CENTER);
                                     clicktoseeB=new JButton("Click to see");
                                     showallB=new JButton("Show all customers");
                                     
            add(searchcustomerL);                       
            add(customernameL);
            add(customernameT);
            add(customermobileL);
            add(customermobileT);
            add(searchcustomerB);
            //add(duesamountL);
            //add(duesamountT);
            //add(duesdateL);
            //add(duesdateT);
            //add(amountpaidL);
            //add(amountpaidT);
            add(clearduesB);
            add(resetB);
            add(billernameL);
            add(billingtimeL);
            add(customerduesesscrollpaneSP);
            add(totalduesesL);
            add(clicktoseeB);
            add(showallB);
         
                                       searchcustomerL.setBounds(500,10,250,25);
                                       customernameL.setBounds(50,50,100,25);
                                       customernameT.setBounds(175,50,275,25);
                                       customermobileL.setBounds(475,50,100,25);
                                       customermobileT.setBounds(600,50,250,25);
                                       searchcustomerB.setBounds(900,50,175,25);
                                       duesamountL.setBounds(50,80,100,25);
                                        duesamountT.setBounds(175,80,140,25);
                                        duesdateL.setBounds(50,110,100,25);
                                        duesdateT.setBounds(175,110,140,25);
                                        amountpaidL.setBounds(50,140,140,25);
                                        amountpaidT.setBounds(175,140,140,25);
                                        resetB.setBounds(900,80,175,25);
                                        clearduesB.setBounds(900,200,175,25);
                                        billernameL.setBounds(50,175,250,25); 
                                        billingtimeL.setBounds(750,175,250,25);
                                        customerduesesscrollpaneSP.setBounds(50,200,800,225);
                                       totalduesesL.setBounds(25,430,250,25);
                                       clicktoseeB.setBounds(675,430,175,25);
                                       showallB.setBounds(900,430,175,25);
                                       
            searchcustomerB.addActionListener(this);
            clearduesB.addActionListener(this);
            resetB.addActionListener(this);
            clicktoseeB.addActionListener(this);
            customerduesesTB.addMouseListener(this);
            showallB.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==searchcustomerB)
        {
            if(customernameT.getText().length()!=0) 
                    {
                        String customername=customernameT.getText();
                        try
                        {
                            Statement st=db.databaseconnection();
                            ResultSet rs=st.executeQuery("Select * from customer where Name='"+customername+"'");
                            Boolean record=rs.next();
                            
                            if(record.equals(true))
                            {
                                customermobileT.setText(rs.getString("Mobile"));
                                
                                while(record==true)
                                {
                                    data[0]=rs.getString("Name");
                                    data[1]=rs.getString("Mobile");
                                    data[2]=rs.getString("Date");
                                    data[3]=String.valueOf(rs.getInt("Amount"));
                                    customerduesesmodelM.addRow(data);
                                    record=rs.next();
                                }
                                
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No customer found");
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
                    else if(customermobileT.getText().length()!=0)
                    {
                        int customermobile=Integer.parseInt(customermobileT.getText());
                        try
                        {
                            Statement st=db.databaseconnection();
                            ResultSet rs=st.executeQuery("Select * from customer where Mobile="+customermobile);
                            Boolean record=rs.next();
                            
                            if(record.equals(true))
                            {
                                customernameT.setText(rs.getString("Name"));
                                while(record==true)
                                {
                                    data[0]=rs.getString("Name");
                                    data[1]=rs.getString("Mobile");
                                    data[2]=rs.getString("Date");
                                    data[3]=String.valueOf(rs.getInt("Amount"));
                                    customerduesesmodelM.addRow(data);
                                    record=rs.next();
                                }
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No customer found");
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
        }
        else if(ae.getSource()==clearduesB)
        {
            int confirmation;
            String customername;
            String customermobile;
            String date;
            int duesamount;
            int row;
 
            row=customerduesesTB.getSelectedRow(); 
            customername=String.valueOf(customerduesesmodelM.getValueAt(row,0));
            customermobile=String.valueOf(customerduesesmodelM.getValueAt(row,1));
            date=String.valueOf(customerduesesmodelM.getValueAt(row,2));
            duesamount=Integer.parseInt(String.valueOf(customerduesesmodelM.getValueAt(row,3)));
            confirmation=JOptionPane.showConfirmDialog(null,"Do you want to clear this dues of Mr."+customername,"Confirm dues clear",JOptionPane.YES_NO_CANCEL_OPTION);
            if(confirmation==0)
             {
                 try
                {
                    Statement st=db.databaseconnection();
                    st.executeUpdate("Delete from customer where Name='"+customername+"' AND Mobile="+customermobile+" AND Date='"+date+"' AND Amount="+duesamount);
                    customernameT.setText("");
                    customermobileT.setText("");
                    duesamountT.setText("");
                    duesdateT.setText("");
                    customerduesesmodelM.removeRow(row);
             
                    JOptionPane.showMessageDialog(null,"Thankyou Mr."+customername+", your dueses are now cleared");
                }
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,e);
                }  
             }
             
        }   
        else if(ae.getSource()==resetB)
        {
            int rowcount;
            rowcount=customerduesesmodelM.getRowCount();
                    
            customernameT.setText("");
            customermobileT.setText("");
            duesamountT.setText("");
             duesdateT.setText("");
             
              
            while(rowcount>=0)
            {
                   customerduesesmodelM.removeRow(rowcount-1);
                   rowcount=rowcount-1;
            }
        }
        else if(ae.getSource()==clicktoseeB)
        {
            int n=0,i=1;
            boolean norecord;
            try
            {
                  Statement st=db.databaseconnection();
                  ResultSet rs=st.executeQuery("Select * from customer where Status='Bill Dues'");
                  
                  while(rs.isLast()==false)
                  {
                      n=n+1;
                      rs.next();
                  }
                  rs.first();
                  while(i<=n)
                  {
                        data[0]=rs.getString("Name");
                        data[1]=rs.getString("Mobile");
                        data[2]=rs.getString("Date");
                        data[3]=rs.getString("Amount");
                        customerduesesmodelM.addRow(data);
                        rs.next();
                        i=i+1;
                   }
            }
            catch(Exception e)
            {
                   JOptionPane.showMessageDialog(null,e);
            }
        }
        
        else if(ae.getSource()==showallB)
        {
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from customer");
                Boolean record=rs.next();
                if(record==true)
                {
                    while(record==true)
                    {
                        data[0]=rs.getString("Name");
                        data[1]=rs.getString("Mobile");
                        data[2]=rs.getString("Date");
                        data[3]=String.valueOf(rs.getInt("Amount"));
                        customerduesesmodelM.addRow(data);
                        record=rs.next();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No customer are there.");
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
            
        }
    }
    public void mouseClicked(MouseEvent me)
    {
        int confirmation;
        String customername;
        String customermobile;
        String date;
        int duesamount;
        int row;
 
        row=customerduesesTB.getSelectedRow(); 
        customernameT.setText(String.valueOf(customerduesesmodelM.getValueAt(row,0)));
        customermobileT.setText(String.valueOf(customerduesesmodelM.getValueAt(row,1)));
        duesdateT.setText(String.valueOf(customerduesesmodelM.getValueAt(row,2)));
        duesamountT.setText(String.valueOf(customerduesesmodelM.getValueAt(row,3)));
            
    }
    public void mouseExited(MouseEvent me)
    {
        
    }
    public void mouseEntered(MouseEvent me)
    {
        
    }
    public void mousePressed(MouseEvent me)
    {
        
    }
    public void mouseReleased(MouseEvent me)
    {
        
    }
}
