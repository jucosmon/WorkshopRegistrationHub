import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.*;


public class manageWorkshop extends JFrame{
        JTable table;
        int code;
        String title;
        JComboBox selectWs, selectColumn;
        JLabel l_selectWs, l_selectColumn;
        JTextField textField;
        JButton edit, delete, okay, okay1, okay2;
    public manageWorkshop(String email){

        String[] columnNames = {"Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host", "Organization"};
        String[] columnfields = {"title", "description", "event_date", "venue", "speakers", "host", "org"};
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        edit = new JButton("Edit");
        delete = new JButton("Delete");
        okay = new JButton("Okay");
        okay1 = new JButton("Okay");
        okay2 = new JButton("Confirm");
        selectColumn = new JComboBox(columnfields);
        textField = new JTextField();
        
        edit.setBounds(0, 0, 100, 30);
        delete.setBounds(0, 50, 100, 30);
        
        

            try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ws_code, title FROM workshop");
           

            Vector v = new Vector<>();
                while(rs.next()){
                    code = rs.getInt(1);
                    title = rs.getString(2);
                    v.add(code);
                   
                } 
                selectWs = new JComboBox(v);
                con.close();
             } catch (Exception e) {
           
        }
        
       

        edit.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent ef){  
                    
                selectWs.setBounds(150, 110, 150, 20);
                okay.setBounds(300, 110, 100, 30);

                okay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ef){ 

                        okay.setBounds(0, 0, 0, 0);
                        selectWs.setBounds(0,0,0,0);
                        okay1.setBounds(300, 110, 100, 30);
                        selectColumn.setBounds(90, 110, 100, 30);
                        int from = (int) selectWs.getSelectedItem();                 
                        
                        

                            okay1.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent ef){

                                String column = (String)selectColumn.getSelectedItem();
                                selectColumn.setBounds(0,0,0,0);
                                okay1.setBounds(0, 0, 0, 0);
                                okay2.setBounds(150, 160, 300, 30); 
                                textField.setBounds(150, 110, 300, 30);
                                System.out.println(from + " " + column); 
                                
                                        okay2.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent ef){
                                                    String updatedText = textField.getText();
                                                    System.out.println(from + " " + column + " " + updatedText);
                                                        try { 
                                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

                                                            switch(column){
                                                                case "title":
                                                                    String sql = "UPDATE workshop SET title = ? WHERE ws_code = ?";
                                                                    PreparedStatement pstmt = con.prepareStatement(sql);
                                                                   // pstmt.setString(2, column);
                                                                    pstmt.setString(1, updatedText);
                                                                    pstmt.setInt(2, code);
                                                                    pstmt.executeUpdate();
                                                                    System.out.println("Update Success");
                                                                    break;
                                                            
                                                            }
                                                            
                                                            con.close();
                                                           
                                                            } catch (Exception e) {
                                                        
                                                        }
                                            }
                                            });

                               
                            }
                            });
                        
                        }
                        });
            
        

            }
            });
        
   
       this.add(selectWs);
       this.add(delete);
       this.add(edit);
       this.add(okay);
       this.add(selectColumn);
       this.add(textField);
       this.add(okay1);
       this.add(okay2);
      
       



    }

   


    public void showDataTable(){
        String[] columnNames = {"Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host", "Organization"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        


    }

}
