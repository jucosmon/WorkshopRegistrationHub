import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class UpcomingEvents extends JFrame implements ActionListener {

    JTable table;
    int code;
    String title;
    JComboBox selectWs;
    JLabel l_selectWs;
    JButton backButton, viewButton, register, okay;
    String email;
    User user;

    public UpcomingEvents(User user) {
        this.email = user.getEmail();
        this.user = user;
        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;
        String[] columnNames = { "Workshop Code", "Title", "Description", "Event Date", "Venue", "Speakers", "Host",
                "Organization" };
        // declaring components

        l_selectWs = new JLabel("Select Event Code: ");
        register = new JButton("Register");
        viewButton = new JButton("View");
        okay = new JButton("Okay");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonsPanel.setBorder(new EmptyBorder(10, 30, 30, 30)); // add padding or margin
        buttonsPanel.setBackground(new Color(68, 93, 72));
        buttonsPanel.add(viewButton);
        buttonsPanel.add(register);

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
        register.setFocusable(false);
        register.setFont(new Font("Montserrat", Font.BOLD, 15));
        register.setForeground(new Color(68, 93, 72));
        register.setBackground(new Color(214, 204, 153));
        register.setPreferredSize(new Dimension(130, 30));

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
            PreparedStatement pst = con.prepareStatement("select * from workshop");
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
                JOptionPane.showMessageDialog(null, "No Upcoming Events", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception e) {
            System.out.println(e);
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

            PreparedStatement pStatement = con.prepareStatement("SELECT ws_code, title FROM workshop");
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
            System.out.println(e);
        }

        JPanel selectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                register.setEnabled(false);
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
                        System.out.println(id);
                        dispose();
                        new workshopDetails(user, id);
                    }
                });

            }
        });
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ef) {

                register.setEnabled(false);
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
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection con = DriverManager.getConnection(
                                    "jdbc:mysql://localhost/pfbaliwis", "root", "");
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(
                                    "CREATE TABLE IF NOT EXISTS registration(reg_code int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL, ws_code int(11) NOT NULL, email varchar(30) NOT NULL, FOREIGN KEY(email) REFERENCES user(email) ON DELETE CASCADE , FOREIGN KEY(ws_code) REFERENCES workshop(ws_code) ON DELETE CASCADE);");
                            System.out.println("Created table in given database...");

                            // Check if ws_code and email combination already exists
                            String checkDuplicateQuery = "SELECT * FROM registration WHERE ws_code = ? AND email = ?";
                            PreparedStatement checkDuplicateStmt = con.prepareStatement(checkDuplicateQuery);
                            checkDuplicateStmt.setInt(1, id);
                            checkDuplicateStmt.setString(2, email);
                            ResultSet duplicateResult = checkDuplicateStmt.executeQuery();

                            if (!duplicateResult.next()) { // If no duplicate code
                                String sql = "INSERT INTO registration(ws_code, email)" + "VALUES(?,?)";
                                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                pstmt.setInt(1, id);
                                pstmt.setString(2, email);
                                pstmt.executeUpdate();
                                System.out.println("VALUES INSERTED");
                                JOptionPane.showMessageDialog(null, "Registered Successfully");
                                dispose();
                                new RegisteredEvents(user);
                            } else { // If duplicate record found
                                JOptionPane.showMessageDialog(null, "You have already registered for this workshop.",
                                        "Registration Invalid", JOptionPane.ERROR_MESSAGE);
                                dispose();
                                new UpcomingEvents(user);
                            }

                            con.close();
                        } catch (Exception e) {
                            System.out.println(e);
                            dispose();
                            new UpcomingEvents(user);
                        }
                    }
                });

            }
        });
        // frame design ug properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.setTitle("Upcoming Events");
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
