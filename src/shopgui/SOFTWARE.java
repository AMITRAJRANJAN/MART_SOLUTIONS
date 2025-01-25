
package shopgui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import shop.Database;
import shop.Software;
import shop.User;

public class SOFTWARE
{
    public static void main(String[] args)
    {
        SOFTWAREFRAME softwareframe=new SOFTWAREFRAME();
    }
}

class SOFTWAREFRAME extends JFrame implements ActionListener
{
    JLabel softwaresettingL,softwarenameL,softwareidL,installationdateL,expirydateL,newsoftwareidL;
    JTextField softwarenameT,softwareidT,installationdateT,expirydateT,newsoftwareidT;
    JButton updatesoftwareB,closeB;
    Date D;
    User U;
    Database DB;
    Software S;
    String softwarename;
    int softwareid;
    String softwareinstallationdate;
    String softwareexpirydate;
    
    public SOFTWAREFRAME()
    {
        new JFrame("");
         setLayout(null);
         setLocation(25,125);
         setSize(1150,525);
         getContentPane().setBackground(Color.orange);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setVisible(true);
         
                                    U=new User();
                                    DB=new Database();
                                    S=new Software();
                                    D=new Date();
                                    {
                                        softwarename=S.name;
                                        softwareid=S.id;
                                        
                                        softwareinstallationdate=String.valueOf(S.iday)+String.valueOf(S.imonth)+String.valueOf(S.iyear);
                                        softwareexpirydate=String.valueOf(S.eday)+String.valueOf(S.emonth)+String.valueOf(S.eyear);
                                      
                                    }
                                    softwaresettingL=new JLabel("Software details");
                                    softwarenameL=new JLabel("Software Name");
                                    softwarenameT=new JTextField(softwarename);
                                    softwareidL=new JLabel("Software Id");
                                    softwareidT=new JTextField(String.valueOf(softwareid));
                                    softwareidT.setEnabled(false);
                                    installationdateL=new JLabel("Date of Installation");
                                    installationdateT=new JTextField(String.valueOf(softwareinstallationdate));
                                    installationdateT.setEnabled(false);
                                    expirydateL=new JLabel("Date of Expiry");
                                    expirydateT=new JTextField(String.valueOf(softwareexpirydate));
                                    expirydateT.setEnabled(false);
                                    newsoftwareidL=new JLabel("New Software Id");
                                    newsoftwareidT=new JTextField();
                                    updatesoftwareB=new JButton("Update Software");
                                    closeB=new JButton("Close");
                                    
        add(softwaresettingL);
        add(softwarenameL);
        add(softwarenameT);
        add(softwareidL);
        add(softwareidT);
        add(installationdateL);
        add(installationdateT);
        add(expirydateL);
        add(expirydateT);
        add(newsoftwareidL);
        add(newsoftwareidT);
        add(updatesoftwareB);
        add(closeB);
        
                                        softwaresettingL.setBounds(500,10,300,25);
                                        softwarenameL.setBounds(400,100,125,25);
                                        softwarenameT.setBounds(550,100,250,25);
                                        softwareidL.setBounds(400,130,125,25);
                                        softwareidT.setBounds(550,130,250,25);
                                        installationdateL.setBounds(400,160,125,25);
                                        installationdateT.setBounds(550,160,250,25);
                                        expirydateL.setBounds(400,190,125,25);
                                        expirydateT.setBounds(550,190,250,25);
                                        newsoftwareidL.setBounds(400,220,125,25);
                                        newsoftwareidT.setBounds(550,220,250,25);
                                        updatesoftwareB.setBounds(500,300,150,25);
                                        closeB.setBounds(500,350,150,25);
                                        
         updatesoftwareB.addActionListener(this);
         closeB.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==updatesoftwareB)
        {
            String date=String.valueOf(D.getDate())+String.valueOf(D.getMonth()+1)+String.valueOf(D.getYear()+1900);
            if( softwareidT.getText().equals("0"))
            {
                if(newsoftwareidT.getText().length()==11)
                    {
                        String name=softwarenameT.getText();
                        int id=Integer.parseInt(newsoftwareidT.getText());
                        Boolean idvalidation=softwareid(id);
                        if(idvalidation==true)
                        {
                            int iday=D.getDate();
                            int imonth=D.getMonth()+1;
                            int iyear=D.getYear()+1900;
                            try
                            {
                                Statement st=DB.databaseconnection();  
                                st.executeUpdate("update software set Installationday="+iday+", Installationmonth="+imonth+", Installationyear="+iyear+",Expiryday="+iday+", Expirymonth="+imonth+", Expiryyear="+(iyear+1)+" where Name='"+name+"'");
                                JOptionPane.showMessageDialog(null, "This application has now been updated for next 1 year. Thankyou for updating.");
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                 
                             }
                        }
                        else if(idvalidation==false)
                        {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Software id.");
                        }
                    }
                    else if(newsoftwareidT.getText().length()<11||newsoftwareidT.getText().length()>11)
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter a valid software id. Software id must be of 11 digit");
                    }
                
            }
            else if(Integer.parseInt(date)>=Integer.parseInt(softwareexpirydate))
            {
                    if(newsoftwareidT.getText().length()==11)
                    {
                        String name=softwarenameT.getText();
                        int id=Integer.parseInt(newsoftwareidT.getText());
                        Boolean idvalidation=softwareid(id);
                        if(idvalidation==true)
                        {
                            int iday=D.getDate();
                            int imonth=D.getMonth()+1;
                            int iyear=D.getYear()+1900;
                            try
                            {
                                Statement st=DB.databaseconnection();  
                                st.executeUpdate("update software set Installationday="+iday+", Installationmonth="+imonth+", Installationyear="+iyear+",Expiryday="+iday+", Expirymonth="+imonth+", Expiryyear="+(iyear+1)+" where Name='"+name+"'");
                                JOptionPane.showMessageDialog(null, "This application has now been updated for next 1 year. Thankyou for updating.");
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                 
                            }
                            
                        }
                        else if(idvalidation==false)
                        {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Software id.");
                        }
              
                    }
                    else if(newsoftwareidT.getText().length()<11||newsoftwareidT.getText().length()>11)
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter a valid software id. Software id must be of 11 digit");
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "This application is currently updated.Do not modify it.");
                }
                
            }
            
        else if(ae.getSource()==closeB)
        {
            dispose();
        }
    }
    
    Boolean softwareid(int id)
    {
        Boolean ok=false;
        String s=String.valueOf(id);
        String s1=s.substring(0,1);
        String s2=s.substring(2,4);
        String s3=s.substring(5,9);
        String s4=s.substring(10,10);
       System.out.println(s1);
       System.out.println(s2);
       System.out.println(s3);
       System.out.println(s4);
        return ok; 
    }
}