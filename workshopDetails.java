import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import java.util.Vector;
import javax.swing.table.*;

public class workshopDetails extends JFrame implements ActionListener {
    JButton backButton, editButton, deleteButton;
    User user;
    String ws_title;
    String ws_desc;
    String ws_date;
    String ws_venue;
    String ws_speaker;
    String ws_host;
    String ws_org;
    JTable table;
    String f_name, l_name;
    JFrame attendeesTable;

    public workshopDetails(User user, int code) {

        String[] columnNames = { "Email", "First Name", "Last Name", "Profession", "Gender", "Nickname" };
        this.user = user;

        // image used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;

        // retrieving workshop details
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

            if (user.getChoice().equals("EventManager")) {
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM workshop WHERE email = ? AND ws_code = ?");
                statement.setString(1, user.getEmail());
                statement.setInt(2, code);
                ResultSet resultSet = statement.executeQuery();
                // Process the retrieved data
                if (resultSet.next()) {
                    // Retrieve the values from the result set
                    ws_title = resultSet.getString("title");
                    ws_desc = resultSet.getString("description");
                    ws_date = resultSet.getString("event_date");
                    ws_venue = resultSet.getString("venue");
                    ws_speaker = resultSet.getString("speakers");
                    ws_host = resultSet.getString("host");
                    ws_org = resultSet.getString("org");

                }
                resultSet.close();
                statement.close();
            } else {
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM workshop WHERE ws_code = ?");
                statement.setInt(1, code);
                ResultSet resultSet = statement.executeQuery();
                // Process the retrieved data
                if (resultSet.next()) {
                    // Retrieve the values from the result set
                    ws_title = resultSet.getString("title");
                    ws_desc = resultSet.getString("description");
                    ws_date = resultSet.getString("event_date");
                    ws_venue = resultSet.getString("venue");
                    ws_speaker = resultSet.getString("speakers");
                    ws_host = resultSet.getString("host");
                    ws_org = resultSet.getString("org");

                }
                resultSet.close();
                statement.close();
            }
            // Close the resources

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        // heading
        JLabel headingLabel = new JLabel("Workshop Event Details");
        headingLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingLabel.setForeground(new Color(214, 204, 153));
        headingLabel.setBorder(new EmptyBorder(20, 0, 20, 0)); // add padding or margin

        // name
        JLabel titleLable = new JLabel(this.ws_title);
        titleLable.setFont(new Font("Montserrat", Font.BOLD, 25));
        titleLable.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLable.setForeground(new Color(214, 204, 153));
        titleLable.setBorder(new EmptyBorder(10, 0, 0, 0)); // add padding or margin
        // profession
        JLabel descLabel = new JLabel(this.ws_desc);
        descLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setForeground(new Color(214, 204, 153));
        descLabel.setBorder(new EmptyBorder(5, 0, 0, 0)); // add padding or margin
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.add(descLabel);
        descPanel.setBackground(new Color(68, 93, 72));

        // panel for the email to gender
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 200, 100, 200)); // add padding or margin
        panel.setBackground(new Color(68, 93, 72));
        panel.setAlignmentX(CENTER_ALIGNMENT);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(21, 0, 0, 0); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side
        // email
        JLabel eventDateLabel = new JLabel("Event Date: " + this.ws_date);
        eventDateLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        eventDateLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventDateLabel.setForeground(new Color(214, 204, 153));
        // country
        JLabel locationLabel = new JLabel("Venue: " + this.ws_venue);
        locationLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        locationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        locationLabel.setForeground(new Color(214, 204, 153));
        // birthdate
        JLabel speakersLabel = new JLabel("Speaker: " + this.ws_speaker);
        speakersLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        speakersLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        speakersLabel.setForeground(new Color(214, 204, 153));
        // gender
        JLabel hostLabel = new JLabel("Host: " + this.ws_host);
        hostLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        hostLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        hostLabel.setForeground(new Color(214, 204, 153));
        // nickname
        JLabel orgLabel = new JLabel("Organization: " + this.ws_org);
        orgLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        orgLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        orgLabel.setForeground(new Color(214, 204, 153));

        // table for displaying how many registrants in a specific event
        if (user.getChoice().equals("EventManager")) {
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
            table.setPreferredSize(new Dimension(500, 200));
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
                PreparedStatement pst = con.prepareStatement(
                        "SELECT registration.reg_code, registration.email, registration.ws_code, user.f_name, user.l_name, user.profession, user.gender, user.nickname FROM registration INNER JOIN workshop ON workshop.ws_code = registration.ws_code INNER JOIN user ON user.email = registration.email WHERE workshop.email = ? AND workshop.ws_code = ? ");
                pst.setString(1, user.getEmail());
                pst.setInt(2, code);
                ResultSet rs = pst.executeQuery();
                int i = 0;

                int reg_code;
                int ws_code;
                String email = "";
                String f_name = "";
                String l_name = "";
                String profession = "";
                String gender = "";
                String nickname = "";
                while (rs.next()) {
                    reg_code = rs.getInt("reg_code");
                    ws_code = rs.getInt("ws_code");
                    email = rs.getString("email");
                    f_name = rs.getString("f_name");
                    l_name = rs.getString("l_name");
                    profession = rs.getString("profession");
                    gender = rs.getString("gender");
                    nickname = rs.getString("nickname");

                    model.addRow(new Object[] { email, f_name, l_name, profession, gender, nickname });
                    i++;
                }
                if (i < 1) {
                    JOptionPane.showMessageDialog(null, "No attendees yet", "Error",
                            JOptionPane.ERROR_MESSAGE);

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
            tablePanel.setSize(700, 200);
            tablePanel.setBorder(new EmptyBorder(10, 30, 30, 30)); // add padding or margin
            tablePanel.setBackground(new Color(68, 93, 72));
            tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

                PreparedStatement pStatement = con.prepareStatement(
                        "SELECT registration.email, user.f_name, user.l_name FROM registration INNER JOIN workshop ON workshop.ws_code = registration.ws_code INNER JOIN user ON user.email = registration.email WHERE workshop.email = ? AND workshop.ws_code = ?;");
                pStatement.setString(1, user.getEmail());
                pStatement.setInt(2, code);
                ResultSet rs = pStatement.executeQuery();

                Vector v = new Vector<>();
                while (rs.next()) {
                    code = rs.getInt(1);
                    f_name = rs.getString(2);
                    l_name = rs.getString(3);
                    v.add(code);

                }
                con.close();

            } catch (Exception e) {

            }
            attendeesTable = new JFrame();
            attendeesTable.setLocation(900, 150);
            attendeesTable.add(tablePanel);
            attendeesTable.setSize(new Dimension(500, 500));
            attendeesTable.setTitle("List of Attendees");
            attendeesTable.setIconImage(logo.getImage());
            attendeesTable.setVisible(true);

        } // end of table

        // adding components
        this.add(backBackPannel);
        this.add(headingLabel);
        this.add(titleLable);
        this.add(descPanel);
        c.gridy = 0;
        c.gridx = 0;
        panel.add(eventDateLabel, c);
        c.gridy = 1;
        c.gridx = 0;
        panel.add(locationLabel, c);
        c.gridy = 2;
        c.gridx = 0;
        panel.add(speakersLabel, c);
        c.gridy = 3;
        c.gridx = 0;
        panel.add(hostLabel, c);
        c.gridy = 4;
        c.gridx = 0;
        panel.add(orgLabel, c);
        this.add(panel);

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(250, 150);
        this.setTitle("Workshop Details");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            System.out.println("Go Back");

            this.dispose();

            if (user.getChoice().equals("EventManager")) {
                attendeesTable.dispose();
                new manageWorkshop(user);
                System.out.println(user.getChoice());
            } else {
                new Homepage(user);
                System.out.println(user.getChoice());
            }

        }

    }
}
