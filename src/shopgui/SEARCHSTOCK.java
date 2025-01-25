package shopgui;

import shop.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import shop.Lowstock;
import shop.Currentuser;

public class SEARCHSTOCK 
{
    public static void main(String[] args)
    {
        SEARCHSTOCKFRAME searchstockframe=new SEARCHSTOCKFRAME();
    
    }
    
}

class SEARCHSTOCKFRAME extends JFrame implements ActionListener,MouseListener
{
     
    JLabel lowstockitemsL,stockernameL,stockingtimeL,stockL, itemnameL, itemnumberL, itemquantityL, itempriceL, itemtotalpriceL,stocktotalL,stocktotalquantityL,stocktotalamountL;
    JTextField  itemnameT, itemnumberT, itemquantityT, itempriceT, itemtotalpriceT,stocktotalquantityT,stocktotalamountT;
    JButton  resetB,searchitemB,clicktoseeB,showallB;
    JTable bill_itemTB, itemTB;
    DefaultTableModel stockmodelM;
    JScrollPane scrollpaneSP;
    String[] columns={"Item Name","Item Number","Item Quantity","Item Price"};
    String[] data=new String[4];
    Lowstock LS;
    Currentuser U;
    Database db;
    
    public SEARCHSTOCKFRAME()
    {
        new JFrame("Search Stock");
         setLayout(null);
         setLocation(25,135);
         setSize(1150,525);
         getContentPane().setBackground(Color.orange);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         
                                LS=new Lowstock();
                                U=new Currentuser();
                                db=new Database();
                                lowstockitemsL=new JLabel(LS.lowstockitems);
                                stockernameL=new JLabel("Stocker Name :"+U.username);
                                stockingtimeL=new JLabel("Stocking Time :"+U.hour+":"+U.minute+":"+U.second);
                                stockL=new JLabel("Search your stock",JLabel.CENTER);
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
                                itemTB=new JTable();
                                stockmodelM=new DefaultTableModel();
                                stockmodelM.setColumnIdentifiers(columns);
                                itemTB.setModel(stockmodelM);
                                scrollpaneSP=new JScrollPane(itemTB);
                                clicktoseeB=new JButton("Click to see");
                                showallB=new JButton("Show all items");
                              
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
         add(stockingtimeL);
         add(stockernameL);
         add(scrollpaneSP);
          add(clicktoseeB);      
          add(lowstockitemsL);
          add(showallB);
         
                                 stockL.setBounds(500,10,300,25);
                                 itemnameL.setBounds(50,50,75,25);
                                 itemnameT.setBounds(145,50,275,25);
                                 itemnumberL.setBounds(475,50,75,25);
                                 itemnumberT.setBounds(575,50,275,25);
                                 searchitemB.setBounds(900,50,175,25);
                                 itemquantityL.setBounds(50,110,100,25);
                                 itemquantityT.setBounds(145,110,140,25);
                                 itempriceL.setBounds(50,80,100,25);
                                 itempriceT.setBounds(145,80,140,25);
                                 itemtotalpriceL.setBounds(50,140,100,25);
                                 itemtotalpriceT.setBounds(145,140,140,25);
                                 resetB.setBounds(900,80,175,25);
                                 stockernameL.setBounds(50,175,250,25); 
                                stockingtimeL.setBounds(750,175,250,25);
                                scrollpaneSP.setBounds(50,200,800,225);
                                clicktoseeB.setBounds(675,430,175,25);
                                lowstockitemsL.setBounds(50,430,400,25);
                                showallB.setBounds(900,430,175,25);
                                
            searchitemB.addActionListener(this);
            resetB.addActionListener(this);
            clicktoseeB.addActionListener(this);
            showallB.addActionListener(this);
            itemTB.addMouseListener(this);
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
                                itempriceT.setText(String.valueOf(rs.getInt("Price")));
                                itemquantityT.setText(String.valueOf(rs.getInt("Quantity")));
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No item found.");
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
                                itempriceT.setText(String.valueOf(rs.getInt("Price")));
                                itemquantityT.setText(String.valueOf(rs.getInt("Quantity")));
                            }
                            else if(record.equals(false))
                            {
                                JOptionPane.showMessageDialog(null,"No item found.");
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }
        }
        if(ae.getSource()==resetB)
        {
            int rowcount;
            itemnameT.setText("");
            itemnumberT.setText("");
            itemquantityT.setText("");
            itempriceT.setText("");
            rowcount=stockmodelM.getRowCount();
            while(rowcount>0)
            {
                stockmodelM.removeRow(rowcount-1);
                rowcount=rowcount-1;
            }
        }
        if(ae.getSource()==clicktoseeB)
        {
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from item where Quantity<10");
                Boolean record=rs.next();
                if(record==true)
                {
                    while(record==true)
                    {
                        data[0]=rs.getString("Name");
                        data[1]=String.valueOf(rs.getInt("Number"));
                        data[2]=String.valueOf(rs.getInt("Quantity"));
                        data[3]=String.valueOf(rs.getInt("Price"));
                        stockmodelM.addRow(data);
                        record=rs.next();
                    }
                }
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
            
        }
        if(ae.getSource()==showallB)
        {
            try
            {
                Statement st=db.databaseconnection();
                ResultSet rs=st.executeQuery("Select * from item");
                Boolean record=rs.next();
                if(record==true)
                {
                    while(record==true)
                    {
                        data[0]=rs.getString("Name");
                        data[1]=String.valueOf(rs.getInt("Number"));
                        data[2]=String.valueOf(rs.getInt("Quantity"));
                        data[3]=String.valueOf(rs.getInt("Price"));
                        stockmodelM.addRow(data);
                        record=rs.next();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No items are there.");
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
        String itemname;
        int itemnumber;
        int itemprice;
        int itemquantity;
        int row;
 
        row=itemTB.getSelectedRow(); 
        itemnameT.setText(String.valueOf(stockmodelM.getValueAt(row,0)));
        itemnumberT.setText(String.valueOf(stockmodelM.getValueAt(row,1)));
        itemquantityT.setText(String.valueOf(stockmodelM.getValueAt(row,2)));
        itempriceT.setText(String.valueOf(stockmodelM.getValueAt(row,3)));
            
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