package shopgui;

import shop.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

public class GENERATEBILL 
{
    public static void main(String[] args) 
    {
        GENERATEBILLFRAME generatebillframe=new GENERATEBILLFRAME();
    }
}

class GENERATEBILLFRAME extends JFrame implements ActionListener,KeyListener
{
    JLabel billL1,itemnameL,itemnumberL,itemquantityL,itempriceL,itemtotalpriceL,billernameL,billingtimeL,customernameL,customermobileL,billstatusL;
    JTextField itemnameT,itemnumberT,itemquantityT,itempriceT,itemtotalpriceT,customernameT,customermobileT;
    JButton generatebillB,searchitemB,additemB,newbillB,submitbillstatusB,printbillB;
    JTable billitemTB;
    DefaultTableModel billmodelM;
    JScrollPane billscrollpaneSP;
    JRadioButton paidRB,duesRB;
    ButtonGroup billstatusBG;
    String billstatus;
    String[] columns={"Item Name","Item Number","Item Quantity","Item Price","Total Price"};
    String[] data=new String[5];
    int countitem=0;
    int countprice=0;
    int totalprice=0;
    int totalitem=0;
    Currentuser U;
    Date todaysdate;
    Database db;
    
    public GENERATEBILLFRAME()
    {
        new JFrame("Generate bill");
        setLayout(null);
        getContentPane().setBackground(Color.orange);
        setLocation(25,125);
        setSize(1150,525);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
                U=new Currentuser();
                db=new Database();
                todaysdate=new Date(System.currentTimeMillis());
                billL1=new JLabel("Enter the item name/number",JLabel.CENTER);
                billernameL=new JLabel("Biller Name :"+U.username);
                billingtimeL=new JLabel("Billing Time :"+U.hour+":"+U.minute+":"+U.second);
                itemnameL=new JLabel("Item Name");
                itemnameT=new JTextField();
                itemnumberL=new JLabel("Item Number");
                itemnumberT=new JTextField();
                searchitemB=new JButton("Search Item");
                itemquantityL=new JLabel("Item Quantity");
                itemquantityT=new JTextField();
                itempriceL=new JLabel("Item Price");
                itempriceT=new JTextField();
                itemtotalpriceL=new JLabel("Item Total Price");
                itemtotalpriceT=new JTextField();
                additemB=new JButton("Add Item");
                newbillB=new JButton("New Bill");
                generatebillB=new JButton("Generate Bill");
                billitemTB=new JTable();
                billmodelM=new DefaultTableModel();
                billmodelM.setColumnIdentifiers(columns);
                billitemTB.setModel(billmodelM);
                billscrollpaneSP=new JScrollPane(billitemTB);
                billstatusL=new JLabel("Bill Status",JLabel.CENTER);
                paidRB=new JRadioButton("Bill Paid");
                duesRB=new JRadioButton("Bill Dues");
                billstatusBG=new ButtonGroup();
                billstatusBG.add(paidRB);
                billstatusBG.add(duesRB);
                submitbillstatusB=new JButton("Submit Bill Status");
                customernameL=new JLabel("Customer Name");
                customernameT=new JTextField();
                customermobileL=new JLabel("Customer Mobile");
                customermobileT=new JTextField();
                printbillB=new JButton("Print Bill");
                          
         add(billL1);
         add(billingtimeL);
         add(itemnameL);
         add(itemnameT);
         add(itemnumberL);
         add(itemnumberT);
         add(searchitemB);
         add(itemquantityL);
         add(itemquantityT);
         add(itempriceL);
         add(itempriceT);
         add(itemtotalpriceL);
         add(itemtotalpriceT);
         add(additemB);
         add(generatebillB);
         add(newbillB);
         add(billstatusL);
         add(duesRB);
         add(paidRB);
         add(submitbillstatusB);
         add(billernameL);
         add(billscrollpaneSP);
         add(customernameL);
         add(customernameT);
         add(customermobileL);
         add(customermobileT);
         add(printbillB);
         
                               billL1.setBounds(500,10,300,25);
                                itemnameL.setBounds(50,50,75,25);
                                itemnameT.setBounds(145,50,275,25);
                                 itemnumberL.setBounds(455,50,75,25);
                                itemnumberT.setBounds(535,50,250,25);
                                searchitemB.setBounds(835,50,225,25);
                                itempriceL.setBounds(50,80,100,25);
                                itempriceT.setBounds(145,80,140,25);
                                itemquantityL.setBounds(50,110,100,25);
                                itemquantityT.setBounds(145,110,140,25);
                                itemtotalpriceL.setBounds(50,140,100,25);
                                itemtotalpriceT.setBounds(145,140,140,25);
                                additemB.setBounds(835,80,225,25);
                                generatebillB.setBounds(835,110,225,25);
                                billernameL.setBounds(50,175,250,25); 
                                billingtimeL.setBounds(675,175,250,25);
                                billscrollpaneSP.setBounds(50,200,735,225);
                                customernameL.setBounds(50,430,100,25);
                                customernameT.setBounds(145,430,200,25);
                                customermobileL.setBounds(450,430,100,25);
                                customermobileT.setBounds(575,430,210,25);
                                 billstatusL.setBounds(835,200,225,25);
                                paidRB.setBounds(835,230,225,25);
                                duesRB.setBounds(835,260,225,25);
                                submitbillstatusB.setBounds(835,290,225,25);
                                newbillB.setBounds(835,320,225,25);
                                printbillB.setBounds(835,430,225,25);
                                
        searchitemB.addActionListener(this);
        generatebillB.addActionListener(this);
        additemB.addActionListener(this);
        newbillB.addActionListener(this);
        paidRB.addActionListener(this);
        duesRB.addActionListener(this);
        submitbillstatusB.addActionListener(this);
        printbillB.addActionListener(this);
        itemnumberT.addKeyListener(this);
        itemquantityT.addKeyListener(this);        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==searchitemB)
        {
                    if(itemnameT.getText().length()!=0) 
                    {
                        String itemname=itemnameT.getText();
                        try
                        {
                            Statement st=db.databaseconnection();
                            ResultSet rs=st.executeQuery("Select * from ITEM where Name='"+itemname+"'");
                            Boolean record=rs.next();
                            if(record.equals(true))
                            {
                                itemnumberT.setText(rs.getString("Number"));
                                itempriceT.setText(rs.getString("Price"));
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No itemname found");
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
                    else if(itemnumberT.getText().length()!=0)
                    {
                        int itemnumber=Integer.parseInt(itemnumberT.getText());
                        try
                        {
                            Statement st=db.databaseconnection();
                            ResultSet rs=st.executeQuery("Select * from ITEM where Number="+itemnumber);
                            Boolean record=rs.next();
                            if(record.equals(true))
                            {
                                itemnameT.setText(rs.getString("Name"));
                                itempriceT.setText(rs.getString("Price"));
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No itemnumber found");
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
                }
        
                else if(ae.getSource()==generatebillB)
                {
                    totalprice=countprice;
                    totalitem=countitem;
                    JOptionPane.showMessageDialog(null,"Number of Items :\t"+totalitem+"\n Total Amount :\t"+totalprice);
                    data[0]="Total";
                    data[1]="";
                    data[2]=String.valueOf(totalitem);
                    data[3]="";
                    data[4]=String.valueOf(totalprice);
                    billmodelM.addRow(data);
                    itemnameT.setText("");
                    itemnumberT.setText("");
                    itemquantityT.setText("");
                    itempriceT.setText("");
                    itemtotalpriceT.setText("");
                }
                else if(ae.getSource()==additemB)
                {
                    countitem=countitem+Integer.parseInt(itemquantityT.getText());
                    countprice=countprice+Integer.parseInt(itemtotalpriceT.getText());
                    data[0]=itemnameT.getText();
                    data[1]=itemnumberT.getText();
                    data[2]=itemquantityT.getText();
                    data[3]=itempriceT.getText();
                    data[4]=itemtotalpriceT.getText();
                    billmodelM.addRow(data);
                    itemnameT.setText("");
                    itemnumberT.setText("");
                    itemquantityT.setText("");
                    itempriceT.setText("");
                    itemtotalpriceT.setText("");
                }
                else if(ae.getSource()==newbillB)
                {
                    int rowcount;
                    rowcount=billmodelM.getRowCount();
                    
                    itemnameT.setText("");
                    itemnumberT.setText("");
                    itemquantityT.setText("");
                    itempriceT.setText("");
                    itemtotalpriceT.setText("");
                    customernameT.setText("");
                    customermobileT.setText("");
                    billstatusBG.clearSelection();
                    while(rowcount>0)
                    {
                        billmodelM.removeRow(rowcount-1);
                        rowcount=rowcount-1;
                    }
                    
                    countitem=0;
                    countprice=0;
                    totalitem=0;
                    totalprice=0;
                    
                    
                    
                }
                else if(ae.getSource()==paidRB)
                {
                    billstatus=ae.getActionCommand();
                }
                else if(ae.getSource()==duesRB)
                {
                    billstatus=ae.getActionCommand();
                }
                else if(ae.getSource()==submitbillstatusB)
                {
                    System.out.println(todaysdate);
                    if(billstatus.equals("Bill Paid"))
                    {
                        if(customernameT.getText().equals("") && customermobileT.getText().equals(""))
                        {
                            try
                            {
                                Statement st=db.databaseconnection();
                                st.executeUpdate("Insert into customer values('-----------','----------','"+todaysdate+"',"+totalprice+",'"+billstatus+"')");
                                JOptionPane.showMessageDialog(null,"Bill created successfully.....Thankyou");
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null,e);
                            }
                        }
                        else
                        {
                            String customername=customernameT.getText();
                            Long customermobile=Long.parseLong(customermobileT.getText());
                            try
                            {
                                Statement st=db.databaseconnection();
                                st.executeUpdate("Insert into customer values('"+customername+"','"+customermobile+"','"+todaysdate+"',"+totalprice+",'"+billstatus+"')");
                                JOptionPane.showMessageDialog(null,"Bill created successfully.....Thankyou Mr. "+customername);
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null,e);
                            }
                        }
                    }
                    else if(billstatus.equals("Bill Dues"))
                    {
                        if(customernameT.getText().equals("") && customermobileT.getText().equals(""))
                        {
                                JOptionPane.showMessageDialog(null,"Customer name and mobile is mandatory in case of Bill Dues");
                        }
                        else
                        {
                            String customername=customernameT.getText();
                            Long customermobile=Long.parseLong(customermobileT.getText());
                            try
                            {
                                Statement st=db.databaseconnection();
                                st.executeUpdate("Insert into customer values('"+customername+"','"+customermobile+"','"+todaysdate+"',"+totalprice+",'"+billstatus+"')");
                                JOptionPane.showMessageDialog(null,"Bill created successfully.....Thankyou Mr. "+customername);
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null,e);
                            }
                        }
                    }
                }
                else if(ae.getSource()==printbillB)
                {
                    try
                    {
                        billitemTB.print();
                    } 
                    catch (PrinterException ex)
                    {
                        Logger.getLogger(GENERATEBILLFRAME.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public void keyPressed(KeyEvent ke)
    {
        
    }
    public void keyReleased(KeyEvent ke)
    {
         int q=Integer.parseInt(itemquantityT.getText());
        int p=Integer.parseInt(itempriceT.getText());
        int tp=q*p;
        itemtotalpriceT.setText(String.valueOf(tp));
    }
    public void keyTyped(KeyEvent ke)
    {
        
    }
}