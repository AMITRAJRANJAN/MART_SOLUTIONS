package shopgui;

import shop.*;
import java.util.Date;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LOGIN 
{
    public static void main(String args[])
    {
        
        LOGINFRAME loginframe=new LOGINFRAME();
    }    
}

class LOGINFRAME  extends JFrame implements ActionListener
{
    JLabel loginL,useridL,passwordL;
    JTextField useridT,passwordT;
    JPasswordField passwordPF;
    JButton loginB;
    JRadioButton show_passwordRB,hide_passwordRB;
    ButtonGroup bgBG;
    JMenuBar menubarMB;
    JMenu forgotM;
    JMenuItem forgotMI;
    Currentuser CU;
    Date D;
    Database db;
    Security SE;
    User U;
    Software S;
    String shopname;
    
    public LOGINFRAME()
    {    
        
         new JFrame("SHOP Login");
         
         getContentPane().setBackground(Color.orange);
         setLayout(null);
         setLocation(400,200);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setSize(600,300);
         
                        
                        CU=new Currentuser();
                        D=new Date();
                        db=new Database();
                        U=new User();
                        S=new Software();
                        try
                        {
                            Statement userst=db.databaseconnection();
                            ResultSet userrs=userst.executeQuery("Select * from shop");
                            Boolean record=userrs.next();
                            if(record==true)
                            {
                                    shopname=userrs.getString("Name");
                                    
                            }
                        }
                        catch(Exception e)
                        {
                                JOptionPane.showMessageDialog(null,e);
                        }
                        
                        menubarMB=new JMenuBar();
                        
                        forgotM=new JMenu("Forgot Userid Password");
                        
                        forgotMI=new JMenuItem("Forgot Userid Password");
                        
                        forgotM.add(forgotMI);
                        
                        menubarMB.add(forgotM);
                        
                        loginL=new JLabel("Login to "+shopname,JLabel.CENTER);
                        useridL=new JLabel("Enter User id");
                        passwordL=new JLabel("Enter Password");
                        useridT=new JTextField(150);
                        passwordPF=new JPasswordField(150);
                        show_passwordRB=new JRadioButton("Show");
                        show_passwordRB.setBackground(Color.ORANGE);
                        hide_passwordRB=new JRadioButton("Hide");
                        hide_passwordRB.setBackground(Color.ORANGE);
                        bgBG=new ButtonGroup();
                        bgBG.add(show_passwordRB);
                        bgBG.add(hide_passwordRB);
                        loginB=new JButton("Login");
                       
         setJMenuBar(menubarMB);
         add(loginL);
         add(useridL);
         add(useridT);
         add(passwordL);
         add(passwordPF);
         add(show_passwordRB);
         add(hide_passwordRB);
         add(loginB);
         
                        menubarMB.setBounds(0,0,550,25);
                        loginL.setBounds(75,25,300,25);
                        useridL.setBounds(50,75,100,25);
                        useridT.setBounds(175,75,150,25);
                        passwordL.setBounds(50,110,100,25);
                        passwordPF.setBounds(175,110,150,25);
                        show_passwordRB.setBounds(350,110,75,25);
                        hide_passwordRB.setBounds(450,110,50,25);
                        loginB.setBounds(175,150,150,25);
                        
        loginB.addActionListener(this);
        show_passwordRB.addActionListener(this);
        hide_passwordRB.addActionListener(this);
        forgotMI.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==loginB)
        {
            if(useridT.getText().equals("")||passwordPF.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter a valid userid and password");
            }
            else
            {
                String userid=useridT.getText();
                String password=passwordPF.getText();
                try
                {
                    Statement userst=db.databaseconnection();
                    ResultSet userrs=userst.executeQuery("select * from USERS where Userid='"+userid+"' AND Password='"+password+"'");
                    Boolean userrecord=userrs.next();
                    
                    if(userrecord.equals(true))
                    {
                     
                        CU.username=userrs.getString("Name");
                        CU.second=D.getSeconds();
                        CU.minute=D.getMinutes();
                        CU.hour=D.getHours();
                        /*try
                        {
                            Statement softwarest=db.databaseconnection();
                            ResultSet softwarers=softwarest.executeQuery("Select * from software");
                            Boolean softwarerecord=softwarers.next();
                            if(softwarerecord.equals(true))
                            {
                                S.name=softwarers.getString("Name");
                                S.id=softwarers.getInt("Id");
                                S.iday=softwarers.getInt("Installationday");
                                S.imonth=softwarers.getInt("Installationmonth");
                                S.iyear=softwarers.getInt("Installationyear");
                                S.eday=softwarers.getInt("Expiryday");
                                S.emonth=softwarers.getInt("Expirymonth");
                                S.eyear=softwarers.getInt("Expiryyear");
                                int cdate=D.getDate();
                                int cmonth=D.getMonth()+1;
                                int cyear=D.getYear()+1900;
                                if((S.eyear+S.emonth+S.eday)<=(cyear+cmonth+cdate))
                                {
                                    System.out.println(S.eyear+S.emonth+S.eday);
                                    System.out.println(cyear+cmonth+cdate);
                                    JOptionPane.showMessageDialog(null, "This application has expired.Please update..");
                                    SOFTWAREFRAME softwareframe=new SOFTWAREFRAME();
                                }
                                else if((S.eyear+S.emonth+S.eday)>(cyear+cmonth+cdate))
                                {*/
                                    System.out.println(S.eyear+S.emonth+S.eday);
                                        useridT.setText("");
                                        passwordPF.setText("");
                                        HOMEFRAME homeframe=new HOMEFRAME();
                                        dispose();
                               /* }
                            }
                            else if(softwarerecord.equals(false))
                            {
                                JOptionPane.showMessageDialog(null, "Please register this application before using.");
                                SOFTWAREFRAME softwareframe=new SOFTWAREFRAME();
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }*/
                    }
                    else if(userrecord.equals(false))
                    {
                        JOptionPane.showMessageDialog(null, "Unauthorized user");
                        useridT.setText("");
                        passwordPF.setText("");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }
        else if(ae.getSource()==show_passwordRB)
        {
            passwordPF.setEchoChar((char) 0);
        }
        else if(ae.getSource()==hide_passwordRB)
        {
            passwordPF.setEchoChar('*');
        }
        else if(ae.getSource()==forgotMI)
        {
            try
            {
                Statement userst=db.databaseconnection();
                ResultSet userrs=userst.executeQuery("Select * from users");
                Boolean record=userrs.next();
                if(record==true)
                {
                    U.name=userrs.getString("Name");
                    U.userid=userrs.getString("Userid");
                    U.password=userrs.getString("Password");
                    SE.securityquestion=userrs.getString("Securityquestion");
                    SE.securityanswer=userrs.getString("Securityanswer");
                    SECURITYFRAME securityframe=new SECURITYFRAME();
                    dispose();
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    } 
}

