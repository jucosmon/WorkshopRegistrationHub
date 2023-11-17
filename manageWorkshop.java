import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.*;

public class manageWorkshop extends JFrame {
    JTable table;
    int code;
    String title;
    JComboBox selectWs, selectColumn;
    JLabel l_selectWs, l_selectColumn;
    JTextField textField;
    JButton edit, delete, okay, okay1, okay2, delete_okay;

    public manageWorkshop(String email) {

        String[] columnNames = { "Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host",
                "Organization" };
        String[] columnfields = { "title", "description", "event_date", "venue", "speakers", "host", "org" };
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showDataTable(email);

        edit = new JButton("Edit");
        delete = new JButton("Delete");
        okay = new JButton("Okay");
        okay1 = new JButton("Okay");
        okay2 = new JButton("Confirm");
        delete_okay = new JButton("Delete");
        selectColumn = new JComboBox(columnfields);
        textField = new JTextField();

        edit.setBounds(0, 0, 100, 30);
        delete.setBounds(0, 50, 100, 30);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

            Statement stmt = con.createStatement();

            PreparedStatement pStatement = con.prepareStatement("SELECT ws_code, title FROM workshop WHERE email = ?");
            pStatement.setString(1, email);
            ResultSet rs = pStatement.executeQuery();

            Vector v = new Vector<>();
            while (rs.next()) {
                code = rs.getInt(1);
                title = rs.getString(2);
                v.add(code);

            }
            selectWs = new JComboBox(v);
            con.close();
        } catch (Exception e) {

        }

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                selectWs.setBounds(150, 110, 150, 20);
                okay.setBounds(300, 110, 100, 30);

                okay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ef) {

                        okay.setBounds(0, 0, 0, 0);
                        selectWs.setBounds(0, 0, 0, 0);
                        okay1.setBounds(300, 110, 100, 30);
                        selectColumn.setBounds(90, 110, 100, 30);
                        int selected = (int) selectWs.getSelectedItem();

                        okay1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ef) {

                                String column = (String) selectColumn.getSelectedItem();
                                selectColumn.setBounds(0, 0, 0, 0);
                                okay1.setBounds(0, 0, 0, 0);
                                okay2.setBounds(150, 160, 300, 30);
                                textField.setBounds(150, 110, 300, 30);
                                System.out.println(selected + " " + column);

                                okay2.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ef) {
                                        String updatedText = textField.getText();
                                        System.out.println(selected + " " + column + " " + updatedText);
                                        try {
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            Connection con = DriverManager
                                                    .getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");
                                            String sql;
                                            PreparedStatement pstmt;
                                            switch (column) {
                                                case "title":
                                                    sql = "UPDATE workshop SET title = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;
                                                case "description":
                                                    sql = "UPDATE workshop SET description = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "event_date":
                                                    sql = "UPDATE workshop SET event_date = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "venue":
                                                    sql = "UPDATE workshop SET venue = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "speakers":
                                                    sql = "UPDATE workshop SET speakers = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "host":
                                                    sql = "UPDATE workshop SET host = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, code);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "org":
                                                    sql = "UPDATE workshop SET org = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
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

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                selectWs.setBounds(150, 110, 150, 20);
                delete_okay.setBounds(300, 110, 100, 30);

                delete_okay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ef) {
                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root",
                                    "");
                            int id = (int) selectWs.getSelectedItem();
                            PreparedStatement st = con.prepareStatement("DELETE FROM workshop WHERE ws_code = ?");
                            st.setInt(1, id);
                            st.executeUpdate();
                            System.out.println(id + "" + "is deleted from database");
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
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
        this.add(delete_okay);

    }

    public void showDataTable(String email) {
        JFrame frame1 = new JFrame();
        String[] columnNames = { "Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host",
                "Organization" };
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");
            PreparedStatement pst = con.prepareStatement("select * from workshop where email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            int ws_code;
            String title = "";
            String description = "";
            String event_date = "";
            String venue = "";
            String speakers = "";
            String host = "";
            String org = "";
            while (rs.next()) {
                ws_code = rs.getInt("ws_code");
                title = rs.getString("title");
                description = rs.getString("description");
                event_date = rs.getString("event_date");
                venue = rs.getString("venue");
                speakers = rs.getString("speakers");
                host = rs.getString("host");
                org = rs.getString("org");

                model.addRow(new Object[] { ws_code, title, description, event_date, venue, speakers, host, org });
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception e) {

        }

        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);

    }

}
