import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class manageWorkshop extends JFrame implements ActionListener {

    JTable table;
    int code;
    String title;
    JComboBox selectWs, selectColumn;
    JLabel l_selectWs, l_selectColumn, textFieldLabel;
    JTextField textField;
    JButton edit, delete, okay, okay1, okay2, delete_okay, backButton, viewButton;
    String email;
    User user;

    public manageWorkshop(User user) {
        this.email = user.getEmail();
        this.user = user;
        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;

        String[] columnfields = { "title", "description", "event_date", "venue", "speakers", "host", "org" };
        String[] columnNames = { "Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host",
                "Organization" };
        // declaring components
        textFieldLabel = new JLabel("Enter new value: ");
        l_selectColumn = new JLabel("Choose options: ");
        l_selectWs = new JLabel("Select Event Code: ");
        edit = new JButton("Edit");
        delete = new JButton("Delete");
        viewButton = new JButton("View");
        okay = new JButton("Okay");
        okay1 = new JButton("Okay");
        okay2 = new JButton("Confirm");
        delete_okay = new JButton("Delete");
        selectColumn = new JComboBox(columnfields);
        textField = new JTextField();
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonsPanel.setBorder(new EmptyBorder(10, 30, 30, 30)); // add padding or margin
        buttonsPanel.setBackground(new Color(68, 93, 72));
        buttonsPanel.add(viewButton);
        buttonsPanel.add(edit);
        buttonsPanel.add(delete);
        // back button
        backButton = new JButton("Go Back");
        backButton.addActionListener(this);

        // back button design
        backButton.setFocusable(false);
        backButton.setFont(new Font("Montserrat", Font.BOLD, 15));
        backButton.setForeground(new Color(68, 93, 72));
        backButton.setBackground(new Color(214, 204, 153));
        backButton.setPreferredSize(new Dimension(130, 30));

        // panel for back button para d macenter
        JPanel backBackPannel = new JPanel();
        backBackPannel.setLayout(new FlowLayout(FlowLayout.LEADING));
        backBackPannel.add(backButton);
        backBackPannel.setBackground(new Color(68, 93, 72));
        // edit button design
        edit.setFocusable(false);
        edit.setFont(new Font("Montserrat", Font.BOLD, 15));
        edit.setForeground(new Color(68, 93, 72));
        edit.setBackground(new Color(214, 204, 153));
        edit.setPreferredSize(new Dimension(130, 30));
        // delete button design
        delete.setFocusable(false);
        delete.setFont(new Font("Montserrat", Font.BOLD, 15));
        delete.setForeground(new Color(68, 93, 72));
        delete.setBackground(new Color(214, 204, 153));
        delete.setPreferredSize(new Dimension(130, 30));
        // view button design
        viewButton.setFocusable(false);
        viewButton.setFont(new Font("Montserrat", Font.BOLD, 15));
        viewButton.setForeground(new Color(68, 93, 72));
        viewButton.setBackground(new Color(214, 204, 153));
        viewButton.setPreferredSize(new Dimension(130, 30));
        // okay 1 button design
        okay.setFont(new Font("Montserrat", Font.BOLD, 15));
        okay.setForeground(new Color(214, 204, 153));
        okay.setBackground(new Color(0, 21, 36));
        okay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        okay.setPreferredSize(new Dimension(100, 30));
        // okay 2 button design
        okay1.setFont(new Font("Montserrat", Font.BOLD, 15));
        okay1.setForeground(new Color(214, 204, 153));
        okay1.setBackground(new Color(0, 21, 36));
        okay1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        okay1.setPreferredSize(new Dimension(100, 30));
        // confirmg button design
        okay2.setFont(new Font("Montserrat", Font.BOLD, 15));
        okay2.setForeground(new Color(214, 204, 153));
        okay2.setBackground(new Color(0, 21, 36));
        okay2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        okay2.setPreferredSize(new Dimension(100, 30));
        // confirmg button design
        delete_okay.setFont(new Font("Montserrat", Font.BOLD, 15));
        delete_okay.setForeground(new Color(214, 204, 153));
        delete_okay.setBackground(new Color(0, 21, 36));
        delete_okay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        delete_okay.setPreferredSize(new Dimension(100, 30));

        // all about the table
        JPanel tablePanel = new JPanel();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        // Disable column reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Disable row reordering
        table.getTableHeader().setResizingAllowed(false);

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

        tablePanel.add(scroll);
        tablePanel.setVisible(true);
        tablePanel.setSize(700, 300);
        tablePanel.setBorder(new EmptyBorder(10, 30, 30, 30)); // add padding or margin
        tablePanel.setBackground(new Color(68, 93, 72));
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        JPanel selectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side

        edit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ef) {
                // disabling previous buttons kay nakaclick naman
                edit.setEnabled(false);
                delete.setEnabled(false);
                viewButton.setEnabled(false);
                // label select wscode positioning
                c.gridy = 0;
                c.gridx = 0;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(l_selectWs, c);
                // combobox select ws positioning
                c.gridx = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                selectPanel.add(selectWs, c);
                // okay button
                c.gridx = 2;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(okay, c);
                selectPanel.revalidate();
                selectPanel.repaint();

                // if okay button is click (first okay)
                okay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ef) {
                        // disabling previous okay button since na click naman
                        okay.setEnabled(false);
                        // store the selected value of the event code
                        int selected = (int) selectWs.getSelectedItem();

                        // label select column positioning
                        c.gridy = 1;
                        c.gridx = 0;
                        c.gridwidth = 1;
                        c.fill = GridBagConstraints.NONE;
                        selectPanel.add(l_selectColumn, c);
                        // select column combobox positioning
                        c.gridx = 1;
                        c.fill = GridBagConstraints.HORIZONTAL;
                        selectPanel.add(selectColumn, c);
                        // okay 1 button positioning
                        c.gridx = 2;
                        c.gridwidth = 1;
                        c.fill = GridBagConstraints.NONE;
                        selectPanel.add(okay1, c);
                        selectPanel.revalidate();
                        selectPanel.repaint();

                        // if okay is clicked
                        okay1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ef) {
                                // disabling previous button since naclick naman
                                okay1.setEnabled(false);
                                // store the selected column string
                                String column = (String) selectColumn.getSelectedItem();
                                //
                                c.gridy = 2;
                                c.gridx = 0;
                                c.gridwidth = 1;
                                c.fill = GridBagConstraints.NONE;
                                selectPanel.add(textFieldLabel, c);
                                // textfield positioning
                                c.gridy = 2;
                                c.gridx = 1;
                                c.fill = GridBagConstraints.HORIZONTAL;
                                selectPanel.add(textField, c);
                                // okay 2 positioning
                                c.gridx = 2;
                                c.fill = GridBagConstraints.HORIZONTAL;
                                selectPanel.add(okay2, c);
                                selectPanel.revalidate();
                                selectPanel.repaint();

                                // check
                                System.out.println(selected + " " + column);

                                // if okay 2 is clicked after confiriming the new inputted text
                                okay2.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ef) {
                                        // get the updatedText
                                        String updatedText = textField.getText();
                                        // check
                                        System.out.println(selected + " " + column + " " + updatedText);

                                        // then updating it onto the database
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
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;
                                                case "description":
                                                    sql = "UPDATE workshop SET description = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "event_date":
                                                    sql = "UPDATE workshop SET event_date = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "venue":
                                                    sql = "UPDATE workshop SET venue = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "speakers":
                                                    sql = "UPDATE workshop SET speakers = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "host":
                                                    sql = "UPDATE workshop SET host = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                                case "org":
                                                    sql = "UPDATE workshop SET org = ? WHERE ws_code = ?";
                                                    pstmt = con.prepareStatement(sql);
                                                    // pstmt.setString(2, column);
                                                    pstmt.setString(1, updatedText);
                                                    pstmt.setInt(2, selected);
                                                    pstmt.executeUpdate();
                                                    System.out.println("Update Success");
                                                    break;

                                            }
                                            dispose();
                                            new manageWorkshop(user);

                                            con.close();

                                        } catch (Exception e) {
                                            dispose();
                                            new manageWorkshop(user);
                                        }
                                    }
                                });

                            }
                        });

                    }
                });

            }

        });

        // if delete button is selected
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                // disabling EDIT AND delete button para d mag override kay mana man ug pili
                edit.setEnabled(false);
                delete.setEnabled(false);
                viewButton.setEnabled(false);
                // label select wscode positioning
                c.gridy = 0;
                c.gridx = 0;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(l_selectWs, c);
                // combobox select ws positioning
                c.gridx = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                selectPanel.add(selectWs, c);
                // okay button
                c.gridx = 2;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(delete_okay, c);
                selectPanel.revalidate();
                selectPanel.repaint();

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
                            JOptionPane.showMessageDialog(null, "Deleted successfully");

                            dispose();
                            new manageWorkshop(user);
                        } catch (Exception e) {
                            dispose();
                            new manageWorkshop(user);
                        }
                    }
                });

            }
        });
        // if delete button is selected
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                // disabling EDIT AND delete button para d mag override kay mana man ug pili
                edit.setEnabled(false);
                delete.setEnabled(false);
                viewButton.setEnabled(false);
                // label select wscode positioning
                c.gridy = 0;
                c.gridx = 0;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(l_selectWs, c);
                // combobox select ws positioning
                c.gridx = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                selectPanel.add(selectWs, c);
                // okay button
                c.gridx = 2;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.NONE;
                selectPanel.add(okay, c);
                selectPanel.revalidate();
                selectPanel.repaint();

                okay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ef) {
                        int id = (int) selectWs.getSelectedItem();
                        dispose();
                        new workshopDetails(user, id);
                    }
                });

            }
        });
        // frame design ug properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setTitle("Manage Workshops");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        // adding components
        this.add(backBackPannel);
        this.add(tablePanel);
        this.add(buttonsPanel);
        this.add(selectPanel);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ef) {
        if (ef.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new Homepage(user);
        }

    }

}
