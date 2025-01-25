package shopgui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import shop.Database;
import shop.User;

public class UPDATEUSER
{
    public static void main(String[] args)
    {
        UPDATEUSERFRAME updateuserframe=new UPDATEUSERFRAME();
    
    }
    
}

class UPDATEUSERFRAME extends JFrame implements ActionListener
{
    JLabel usersettingL,nameL,useridL,passwordL,confirmpasswordL;
    JTextField nameT,useridT,passwordT,confirmpasswordT,securityanswerT;
    JButton updateuserB;
    JComboBox securityquestionsCB;
    String securityquestions[]={"Select a security question","What is your nick name ?","What is your favourite pet name ?","What is your favourite friend name ?"};
    User U;
    Database DB;
    public UPDATEUSERFRAME()
    {
        new JFrame("User Setting");
         setLayout(null);
         setLocation(25,125);
         setSize(1150,525);
         getContentPane().setBackground(Color.orange);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setVisible(true);
         
                                    U=new User();
                                    DB=new Database();
                                    usersettingL=new JLabel("Crete or Update user details");
                                    nameL=new JLabel("Name");
                                    nameT=new JTextField(U.name);
                                    useridL=new JLabel("User id");
                                    useridT=new JTextField(U.userid);
                                    passwordL=new JLabel("Password");
                                    passwordT=new JTextField(U.password);
                                    confirmpasswordL=new JLabel("Confirm Password");
                                    confirmpasswordT=new JTextField(U.password);
                                    securityquestionsCB=new JComboBox(securityquestions);
                                    securityanswerT=new JTextField();
                                    updateuserB=new JButton("Update User");
              
        add(usersettingL);
        add(nameL);
        add(nameT);
        add(useridL);
        add(useridT);
        add(passwordL);
        add(passwordT);
        add(confirmpasswordL);
        add(confirmpasswordT);
        add(securityquestionsCB);
        add(securityanswerT);
        add(updateuserB);
        
                                        usersettingL.setBounds(500,10,300,25);
                                        nameL.setBounds(400,100,125,25);
                                        nameT.setBounds(550,100,250,25);
                                        useridL.setBounds(400,130,125,25);
                                        useridT.setBounds(550,130,250,25);
                                        passwordL.setBounds(400,160,125,25);
                                        passwordT.setBounds(550,160,250,25);
                                        confirmpasswordL.setBounds(400,190,125,25);
                                        confirmpasswordT.setBounds(550,190,250,25);
                                        securityquestionsCB.setBounds(400,220,400,25);
                                        securityanswerT.setBounds(400,250,400,25);
                                        updateuserB.setBounds(500,300,150,25);
                                        
         updateuserB.addActionListener(this);
                                        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==updateuserB)
        {
            String newname=nameT.getText();
            String newuserid=useridT.getText();
            String newpassword=passwordT.getText();
            String newconfirmpassword=confirmpasswordT.getText();
            String securityquestion=String.valueOf(securityquestionsCB.getSelectedItem());
            String securityanswer=securityanswerT.getText();
            
            if(newpassword.equals(newconfirmpassword))
            {
                try
                {
                    Statement st=DB.databaseconnection();
                    st.executeUpdate("Update users set Name='"+newname+"',Userid='"+newuserid+"',Password='"+newpassword+"',Securityquestion='"+securityquestion+"',Securityanswer='"+securityanswer+"' where Name='"+U.name+"'");
                    JOptionPane.showMessageDialog(null,"New user details have been updated successfully.");
                    dispose();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please make sure that Password Field and Confirma Password Field must be same.");
            }
            
        }
    }
        
}