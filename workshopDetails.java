
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

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

    public workshopDetails(User user, int code) {
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
            // Close the resources
            resultSet.close();
            statement.close();
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
        JLabel headingLabel = new JLabel("Worshop Details");
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

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(450, 50);
        this.setTitle("Profile");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

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

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new manageWorkshop(this.user);
        }

    }
}
