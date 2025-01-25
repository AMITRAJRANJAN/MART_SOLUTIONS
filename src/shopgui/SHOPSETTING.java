package shopgui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import shop.Database;
import shop.Shop;
import shop.Currentuser;

public class SHOPSETTING
{
    public static void main(String[] args)
    {
        SHOPSETTINGFRAME shopsettingframe=new SHOPSETTINGFRAME();
    
    }
    
}

class SHOPSETTINGFRAME  extends JFrame implements ActionListener
{
    JLabel shopsettingL, shopnameL,addressline1L,addressline2L,addressline3L,pincodeL,contactL;
    JTextField shopnameT,addressline1T,addressline2T,addressline3T,pincodeT,contactT;
    JButton updateshopB;
    Database DB;
    Currentuser U;
    Shop S;
    String shopname,newshopname;
    String address1,newaddress1;
    String address2,newaddress2;
    String address3,newaddress3;
    int pincode,newpincode;
    int contact,newcontact;
    public SHOPSETTINGFRAME()
    {
         new JFrame("Shop Setting");
         setLayout(null);
         setLocation(25,125);
         setSize(1150,525);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         getContentPane().setBackground(Color.orange);
         setVisible(true);
         
                                    DB=new Database();
                                    U=new Currentuser();
                                    S=new Shop();
                                    shopname=S.shopname;
                                    address1=S.address1;
                                    address2=S.address2;
                                    address3=S.address3;
                                    pincode=S.pincode;
                                    contact=S.contact;
                                    System.out.println(pincode);
                                    System.out.println(contact);
                                    shopsettingL=new JLabel("Create or Update your shop details");
                                    shopnameL=new JLabel("Shop Name");
                                    shopnameT=new JTextField(shopname);
                                    addressline1L=new JLabel(" Address Line 1");
                                    addressline1T=new JTextField(address1);
                                    addressline2L=new JLabel("Address Line 2");
                                    addressline2T=new JTextField(address2);
                                    addressline3L=new JLabel("Address Line 3");
                                    addressline3T=new JTextField(address3);
                                    pincodeL=new JLabel("Pincode");
                                    pincodeT=new JTextField(String.valueOf(pincode));
                                    contactL=new JLabel("Contact");
                                    contactT=new JTextField(String.valueOf(contact));
                                    updateshopB=new JButton("Update shop details");
                                    
         add(shopsettingL);                           
         add(shopnameL);
         add(shopnameT);
         add(addressline1L);
         add(addressline1T);
         add(addressline2L);
         add(addressline2T);
         add(addressline3L);
         add(addressline3T);
         add(pincodeL);
         add(pincodeT);
         add(contactL);
         add(contactT);
         add(updateshopB);
         
                                        shopsettingL.setBounds(500,10,300,25);
                                        shopnameL.setBounds(400,100,100,25);
                                        shopnameT.setBounds(550,100,250,25);
                                        addressline1L.setBounds(400,130,100,25);
                                        addressline1T.setBounds(550,130,250,25);
                                        addressline2L.setBounds(400,160,100,25);
                                        addressline2T.setBounds(550,160,250,25);
                                        addressline3L.setBounds(400,190,100,25);
                                        addressline3T.setBounds(550,190,250,25);
                                        pincodeL.setBounds(400,220,100,25);
                                        pincodeT.setBounds(550,220,250,25);
                                        contactL.setBounds(400,250,100,25);
                                        contactT.setBounds(550,250,250,25);
                                        updateshopB.setBounds(500,300,150,25);
                                        
         updateshopB.addActionListener(this);
                                                                
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==updateshopB)
        {
            newshopname=shopnameT.getText();
            newaddress1=addressline1T.getText();
            newaddress2=addressline2T.getText();
            newaddress3=addressline3T.getText();
            newpincode=Integer.parseInt(pincodeT.getText());
            newcontact=Integer.parseInt(contactT.getText());
            
            try
            {
                Statement st=DB.databaseconnection();
                st.executeUpdate("Update shop set Name='"+newshopname+"',Address1='"+newaddress1+"',Address2='"+newaddress2+"',Address3='"+newaddress3+"',Pincode="+newpincode+",Contact="+newcontact+" where Name='"+shopname+"'");
                 JOptionPane.showMessageDialog(null,"New shop details have been updated successfully.");
                 dispose();
            }
            catch(Exception e)
            {
                 JOptionPane.showMessageDialog(null,e);
                
            }
            
        }
    }
} 
