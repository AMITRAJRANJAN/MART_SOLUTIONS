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
import shop.Security;
import shop.User;

public class SECURITY 
{
    public static void main(String[] args)
    {
        SECURITYFRAME securityframe=new SECURITYFRAME();
    }
}

class SECURITYFRAME extends JFrame implements ActionListener
{
    JLabel securityL;
    JComboBox securityquestionsCB;
    JTextField securityanswerT;
    JButton submitB;
    String securityquestions[]={"Select a security question","What is your nick name ?","What is your favourite pet name ?","What is your favourite friend name ?"};
    Database DB;
    Security SE;
    User U;
    public SECURITYFRAME()
    {
        new JFrame("Security Question");
         setLayout(null);
         setLocation(400,200);
         setSize(600,300);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         getContentPane().setBackground(Color.orange);
         setVisible(true);
         
                                        DB=new Database();
                                        SE=new Security();
                                        U=new User();
                                        securityL=new JLabel("Select and answer your security question to reset your userid and password");
                                        securityquestionsCB=new JComboBox(securityquestions);
                                        securityanswerT=new JTextField();
                                        submitB=new JButton("Submit");
                                        
         add(securityL);
         add(securityquestionsCB);
         add(securityanswerT);
         add(submitB);
         
                                            securityL.setBounds(75,25,500,25);
                                            securityquestionsCB.setBounds(100,75,400,25);
                                            securityanswerT.setBounds(100,105,400,25);
                                            submitB.setBounds(250,150,100,25);
                                            
         submitB.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submitB)
        {
            String securityquestion=SE.securityquestion;
            String securityanswer=SE.securityanswer;
            String newsecurityquestion=String.valueOf(securityquestionsCB.getSelectedItem());
            String newsecurityanswer=securityanswerT.getText();
            
            if(securityquestion.equals(newsecurityquestion) && securityanswer.equals(newsecurityanswer))
            {
                   UPDATEUSERFRAME updateuserframe=new UPDATEUSERFRAME(); 
                   dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please select a valid security question and answer it correctly");
            }
        }
    }
}