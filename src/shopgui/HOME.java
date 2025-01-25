package shopgui;

import shop.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class HOME 
{
    public static void main(String[] args)
    {
        HOMEFRAME homeframe=new HOMEFRAME();
    }
}

class HOMEFRAME extends JFrame implements ActionListener
{
    JLabel userL,logintimeL,headingL,taskL;
   JButton stockB,billB,logoutB;
   JMenuBar menubarMB;
   JMenu newM,logoutM,searchM,settingM,userM;
   JMenuItem newbillMI,newstockMI,logoutMI,searchstockMI,searchcustomerMI,shopMI,softwareMI,createnewuserMI,updateuserMI;
   Currentuser CU;
   Customer C;
   Database db;
   Software SOFT;
   Lowstock LS;
   Shop S;
   User U;
   Date D;
   String shopname;
    public HOMEFRAME()
    {
        new JFrame("Home");
        setLayout(null);
        getContentPane().setBackground(Color.orange);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
                         CU=new Currentuser();
                         C=new Customer();
                         LS=new Lowstock();
                         db=new Database();
                         S=new Shop();
                         SOFT=new Software();
                         U=new User();
                         try
                        {
                            Statement st=db.databaseconnection();
                            ResultSet rs=st.executeQuery("Select * from shop");
                            Boolean record=rs.next();
                            if(record==true)
                            {
                                    shopname=rs.getString("Name");
                            }
                        }
                        catch(Exception e)
                        {
                                JOptionPane.showMessageDialog(null,e);
                        }
                         userL=new JLabel("Hello Mr."+CU.username);
                         logintimeL=new JLabel("Login at :"+CU.hour+":"+CU.minute+":"+CU.second);
                         headingL=new JLabel("Welcome to "+shopname,JLabel.CENTER);
                         taskL=new JLabel("Select your Task");
                         stockB=new JButton("Stock Entry");
                         billB=new JButton("Billing");
                         logoutB=new JButton("Logout");
                         
                         menubarMB=new JMenuBar();
                         
                         newM=new JMenu("New");
                         logoutM=new JMenu("Logout");
                         searchM=new JMenu("Search");
                         settingM=new JMenu("Setting");
                         userM=new JMenu("User Setting");
                         
                         newbillMI=new JMenuItem("New Bill");
                         newstockMI=new JMenuItem("New Stock");
                         logoutMI=new JMenuItem("Logout");
                         searchstockMI=new JMenuItem("Search Stock");
                         searchcustomerMI=new JMenuItem("Search Customer");
                         shopMI=new JMenuItem("Shop Setting");
                         softwareMI=new JMenuItem("Software Setting");
                         createnewuserMI=new JMenuItem("Create New User");
                         updateuserMI=new JMenuItem("Update Current User");
                         
                         userM.add(createnewuserMI);
                         userM.add(updateuserMI);
                         
                         newM.add(newbillMI);
                         newM.add(newstockMI);
                         logoutM.add(logoutMI);
                         searchM.add(searchstockMI);
                         searchM.add(searchcustomerMI);
                         settingM.add(shopMI);
                         settingM.add(softwareMI);
                         settingM.add(userM);
                         
                         
                         menubarMB.add(newM);
                         menubarMB.add(logoutM);
                         menubarMB.add(searchM);
                         menubarMB.add(settingM);
                        
       
        add(userL);
        add(logintimeL);
        add(headingL);
        add(taskL);
        add(stockB);
        add(billB);
        add(logoutB);
        setJMenuBar(menubarMB);
                                
                                menubarMB.setBounds(0,0,1250,25);
                                userL.setBounds(25,50,175,25);
                                logintimeL.setBounds(250,50,200,25);
                                headingL.setBounds(500,50,300,25);
                                taskL.setBounds(1200,50,300,25);
                                billB.setBounds(1175,100,150,25);
                                stockB.setBounds(1175,135,150,25);
                                logoutB.setBounds(1175,600,150,25);
                                
       stockB.addActionListener(this);
       billB.addActionListener(this);
       logoutB.addActionListener(this);
       newbillMI.addActionListener(this);
       newstockMI.addActionListener(this);
       logoutMI.addActionListener(this);
       searchstockMI.addActionListener(this);
       searchcustomerMI.addActionListener(this);
       shopMI.addActionListener(this);
       softwareMI.addActionListener(this);
       //userM.addActionListener(this);
       updateuserMI.addActionListener(this);
       createnewuserMI.addActionListener(this);
       setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==stockB)
        {
            CREATESTOCKFRAME createstockframe=new CREATESTOCKFRAME();
        }
        else if(ae.getSource()==billB)
        {
            GENERATEBILLFRAME generatebillframe=new GENERATEBILLFRAME();
        }
        else if(ae.getSource()==logoutB)
        {
            dispose();
        }
        else if(ae.getSource()==newbillMI)
        {
            GENERATEBILLFRAME generatebillframe=new GENERATEBILLFRAME();
        }
        else if(ae.getSource()==newstockMI)
        {
            CREATESTOCKFRAME createstockframe=new CREATESTOCKFRAME();
        }
        else if(ae.getSource()==logoutMI)
        {
            dispose();
        }
        else if(ae.getSource()==searchcustomerMI)
        {
            int first,last,totaldueses=0;
            try
            {
                    Statement st=db.databaseconnection();
                   ResultSet rs=st.executeQuery("Select * from customer where Status='Bill Dues'");
                   while(rs.isLast()==false)
                   {
                     rs.next();
                     totaldueses=totaldueses+1;
                   }
                   C.duesescustomer=totaldueses;
                   SEARCHCUSTOMERFRAME searchcustomerfarme=new SEARCHCUSTOMERFRAME();
                   
            }
            catch(Exception e)
            {
                 JOptionPane.showMessageDialog(null,e);
             }
        }
        else if(ae.getSource()==searchstockMI)
        {
            int lowstock=0;
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from item where Quantity<10");
                Boolean record=rs.next();
                if(record==true)
                {
                    while(record==true)
                    {
                        lowstock=lowstock+1;
                        record=rs.next();
                    }
                    LS.lowstockitems=lowstock+" items in stock are having their quantity less than 10";
                }
                else
                {
                     LS.lowstockitems="All items in stock are having their quantity good";   
                }
            }
            catch(Exception e)
            {
               
            }
            
            SEARCHSTOCKFRAME searchstockframe=new SEARCHSTOCKFRAME();
        }
        else if(ae.getSource()==shopMI)
        {
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from Shop");
                Boolean record=rs.next();
                if(record==true)
                {
                    S.shopname=rs.getString("Name");
                    S.address1=rs.getString("Address1");
                    S.address2=rs.getString("Address1");
                    S.address3=rs.getString("Address1");
                    S.pincode=rs.getInt("Pincode");
                    S.contact=rs.getInt("Contact");
                    SHOPSETTINGFRAME shopsettingframe=new SHOPSETTINGFRAME();
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No shop found");
                }
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
            
        }
        else if(ae.getSource()==updateuserMI)
        {
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from users");
                Boolean record=rs.next();
                if(record==true)
                {
                    U.name=rs.getString("Name");
                    U.userid=rs.getString("Userid");
                    U.password=rs.getString("Password");
                     UPDATEUSERFRAME updateuserframe=new UPDATEUSERFRAME();
                }
                else
                {
                    
                }
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        else if(ae.getSource()==createnewuserMI)
        {
                 CREATEUSERFRAME createuserframe=new CREATEUSERFRAME();
          
        }
        else if(ae.getSource()==softwareMI)
        {
            try
            {
                   Statement st=db.databaseconnection();
                   ResultSet rs=st.executeQuery("Select * from software");
                   Boolean record=rs.next();
                   
                   if(record.equals(true))
                  {
                      
                        SOFT.name=rs.getString("Name");
                        SOFT.id=rs.getInt("Id");
                        SOFT.iday=rs.getInt("Installationday");
                        SOFT.imonth=rs.getInt("Installationmonth");
                        SOFT.iyear=rs.getInt("Installationyear");
                        SOFT.eday=rs.getInt("Expiryday");
                        SOFT.emonth=rs.getInt("Expirymonth");
                        SOFT.eyear=rs.getInt("Expiryyear");
                        SOFTWAREFRAME softwareframe=new SOFTWAREFRAME();
                        
                  }
            }
            catch(Exception e)
            {
                   System.out.println(e);
            }
        }
    }
}
