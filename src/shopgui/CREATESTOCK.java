package shopgui;

import shop.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import shop.Currentuser;

public class CREATESTOCK 
{
    public static void main(String[] args)
    {
        CREATESTOCKFRAME createstockframe=new CREATESTOCKFRAME();
    }
}

class CREATESTOCKFRAME extends JFrame implements ActionListener,KeyListener
{
    JLabel stockernameL,stockingtimeL,stockL, itemnameL, itemnumberL, itemquantityL, itempriceL, itemtotalpriceL,stocktotalL,stocktotalquantityL,stocktotalamountL;
     JTextField  itemnameT, itemnumberT, itemquantityT, itempriceT, itemtotalpriceT,stocktotalquantityT,stocktotalamountT;
    JButton  resetB,searchitemB, newstockB, updatestockB,generatebillB,printbillB;
    JTable bill_itemTB, itemTB;
    DefaultTableModel stockmodelM;
    JScrollPane scrollpaneSP;
    String[] columns={"Item Name","Item Number","Item Quantity","Item Price","Total Price"};
    String[] data=new String[5];
    int countitem=0;
    int countprice=0;
    int totalitem=0;
    int totalprice=0;
    Currentuser U;
    Database db;
    
    public CREATESTOCKFRAME()
    {
        new JFrame("Create Stock");
         setLayout(null);
         getContentPane().setBackground(Color.orange);
         setLocation(25,125);
         setSize(1150,525);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setVisible(true);
         
                             U=new Currentuser();
                             db=new Database();
                             stockernameL=new JLabel("Stocker Name :"+U.username);
                             stockingtimeL=new JLabel("Stocking Time :"+U.hour+":"+U.minute+":"+U.second);
                             stockL=new JLabel("Enter the item description",JLabel.CENTER);
                              itemnameL=new JLabel("Item Name");
                              itemnameT=new JTextField();
                              itemnumberL=new JLabel("Item Number");
                              itemnumberT=new JTextField();
                              searchitemB=new JButton("Search Item");
                              itemquantityL=new JLabel("Item Quantity");
                              itemquantityT=new JTextField();
                              itempriceL=new JLabel("Item Price");
                              itempriceT=new JTextField();
                              itemtotalpriceL=new JLabel("Total Price");
                              itemtotalpriceT=new JTextField();
                              resetB=new JButton("Reset");
                              newstockB=new JButton("Create New Stock");
                              newstockB.setEnabled(false);
                              updatestockB=new JButton("Update Existing Stock");
                               updatestockB.setEnabled(false);
                              generatebillB=new JButton("Generate Stock Bill");
                              itemTB=new JTable();
                             stockmodelM=new DefaultTableModel();
                             stockmodelM.setColumnIdentifiers(columns);
                              itemTB.setModel(stockmodelM);
                             scrollpaneSP=new JScrollPane(itemTB);
                             printbillB=new JButton("Print Bill");
                             
         
         add(stockL);
         add( itemnameL);
         add( itemnameT);
         add( itemnumberL);
         add( itemnumberT);
         add(searchitemB);
         add( itemquantityL);
         add( itemquantityT);
         add( itempriceL);
         add( itempriceT);
         add( itemtotalpriceL);
         add( itemtotalpriceT);
         add( resetB);
         add( newstockB);
         add( updatestockB);
         add( generatebillB);
         add(stockingtimeL);
         add(stockernameL);
         add(scrollpaneSP);
          add(printbillB);
         
                                stockL.setBounds(500,10,300,25);
                                 itemnameL.setBounds(50,50,75,25);
                                 itemnameT.setBounds(145,50,275,25);
                                 itemnumberL.setBounds(455,50,75,25);
                                 itemnumberT.setBounds(535,50,250,25);
                                 searchitemB.setBounds(835,50,225,25);
                                 itemquantityL.setBounds(50,110,100,25);
                                 itemquantityT.setBounds(145,110,140,25);
                                 itempriceL.setBounds(50,80,100,25);
                                 itempriceT.setBounds(145,80,140,25);
                                 itemtotalpriceL.setBounds(50,140,100,25);
                                 itemtotalpriceT.setBounds(145,140,140,25);
                                 resetB.setBounds(835,175,225,25);
                                 newstockB.setBounds(50,175,375,25);
                                 updatestockB.setBounds(455,175,330,25);
                                 stockernameL.setBounds(50,210,250,25); 
                                stockingtimeL.setBounds(675,210,250,25);
                                 scrollpaneSP.setBounds(50,235,735,225);
                                generatebillB.setBounds(835,235,225,25);
                                printbillB.setBounds(835,430,225,25);
                                
         searchitemB.addActionListener(this);
          resetB.addActionListener(this);
         newstockB.addActionListener(this);
         updatestockB.addActionListener(this);
         generatebillB.addActionListener(this);
         printbillB.addActionListener(this);
         itemquantityT.addKeyListener(this);  
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        Object o=ae.getSource();
        if(o.equals(searchitemB))
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
                                JOptionPane.showMessageDialog(null,"Item found.Please update this item.");
                                itemnumberT.setText(rs.getString("Number"));
                                //itempriceT.setText(rs.getString("Price"));
                                newstockB.setEnabled(false);
                                updatestockB.setEnabled(true);
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No item found.Please create this item.");
                                newstockB.setEnabled(true);
                                updatestockB.setEnabled(false); 
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
                                JOptionPane.showMessageDialog(null,"Item found.Please update this item.");
                                itemnameT.setText(rs.getString("Name"));
                                newstockB.setEnabled(false);
                                updatestockB.setEnabled(true);
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No item found.Please create this item");
                                newstockB.setEnabled(true);
                                updatestockB.setEnabled(false);
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
        }
        else if(o.equals(resetB))
        {
                    int rowcount;
                    rowcount=stockmodelM.getRowCount();
                   
                    while(rowcount>0)
                    {
                        stockmodelM.removeRow(rowcount-1);
                        rowcount=rowcount-1;
                    }
                    
                    countitem=0;
                    countprice=0;
                    totalitem=0;
                    totalprice=0;
                    
            
            itemnameT.setText("");
            itemnumberT.setText("");
            itemquantityT.setText("");
            itempriceT.setText("");
            itemtotalpriceT.setText("");
            newstockB.setEnabled(false);
            updatestockB.setEnabled(false);
        }
        else if(o.equals(newstockB))
        {
               String itemname=itemnameT.getText();
                int itemnumber=Integer.parseInt(itemnumberT.getText());
                int itemprice=Integer.parseInt(itempriceT.getText());
                int itemquantity=Integer.parseInt(itemquantityT.getText());
                 countitem=countitem+Integer.parseInt(itemquantityT.getText());
                 countprice=countprice+Integer.parseInt(itemtotalpriceT.getText());
                 data[0]=itemnameT.getText();
                 data[1]=itemnumberT.getText();
                 data[2]=itemquantityT.getText();
                 data[3]=itempriceT.getText();
                 data[4]=itemtotalpriceT.getText();
                 stockmodelM.addRow(data);
                 itemnameT.setText("");
                 itemnumberT.setText("");
                 itemquantityT.setText("");
                 itempriceT.setText("");
                 itemtotalpriceT.setText("");
                try
                {
                    Statement st=db.databaseconnection();
                    st.executeUpdate("Insert into ITEM values('"+itemname+"',"+itemnumber+","+itemprice+","+itemquantity+")");
                    JOptionPane.showMessageDialog(null,"Stock created successfully");
                    
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
        }
        else if(o.equals(updatestockB))
        {
                String itemname=itemnameT.getText();
                int itemnumber=Integer.parseInt(itemnumberT.getText());
                int itemprice=Integer.parseInt(itempriceT.getText());
                int itemquantity=Integer.parseInt(itemquantityT.getText());
                 countitem=countitem+Integer.parseInt(itemquantityT.getText());
                 countprice=countprice+Integer.parseInt(itemtotalpriceT.getText());
                 data[0]=itemnameT.getText();
                 data[1]=itemnumberT.getText();
                 data[2]=itemquantityT.getText();
                 data[3]=itempriceT.getText();
                 data[4]=itemtotalpriceT.getText();
                 stockmodelM.addRow(data);
                 itemnameT.setText("");
                 itemnumberT.setText("");
                 itemquantityT.setText("");
                 itempriceT.setText("");
                 itemtotalpriceT.setText("");
                try
                {
                    Statement st=db.databaseconnection();
                    st.executeUpdate("Update ITEM set Quantity="+itemquantity+", Price="+itemprice+" where Name='"+itemname+"' AND Number="+itemnumber+";");
                    JOptionPane.showMessageDialog(null,"Stock updated successfully");
                    itemnameT.setText("");
                    itemnumberT.setText("");
                    itempriceT.setText("");
                    itemquantityT.setText("");
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
        }
        else if(o.equals(generatebillB))
        {
            int q=countitem;
            int p=countprice;
            totalitem=countitem;
            totalprice=countprice;
            JOptionPane.showMessageDialog(null,"Number of Items :\t"+q+"\n Total Amount :\t"+p);
            data[0]="Total";
            data[1]="";
            data[2]=String.valueOf(totalitem);
            data[3]="";
            data[4]=String.valueOf(totalprice);
            stockmodelM.addRow(data);
            itemnameT.setText("");
            itemnumberT.setText("");
            itemquantityT.setText("");
            itempriceT.setText("");
            itemtotalpriceT.setText("");
        }
        else if(o.equals(printbillB))
        {
            try
            {
                itemTB.print();
                
            } 
            catch (PrinterException ex)
            {
                Logger.getLogger(CREATESTOCKFRAME.class.getName()).log(Level.SEVERE, null, ex);
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
