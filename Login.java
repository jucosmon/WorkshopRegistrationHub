import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {
    protected String choice;
    JLabel school_id, password;
    JTextField t_email;
    JPasswordField t_password;
    JButton b_enter, backButton;
    User user;

    public Login(String choice, User user) {
        this.user = user;
        this.choice = choice;
        // used for displaying label to not affect the choice variable name
        String choiceDisplay;
        if (choice == "EventManager") {
            choiceDisplay = "Event Manager";
        } else {
            choiceDisplay = "Attendee";
        }

        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;

        JLabel headingLabel = new JLabel("Welcome Back " + choiceDisplay + "!");
        headingLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingLabel.setForeground(new Color(214, 204, 153));
        headingLabel.setBorder(new EmptyBorder(20, 0, 10, 0)); // add padding or margin

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(20, 80, 80, 80)); // add padding or margin
        inputPanel.setBackground(new Color(68, 93, 72));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side

        // adding labels, texfields and button
        school_id = new JLabel("Email:");
        password = new JLabel("Password:");
        t_email = new JTextField();
        t_password = new JPasswordField();
        b_enter = new JButton("Log In");
        b_enter.addActionListener(this);
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

        // email label design
        school_id.setFont(new Font("Montserrat", Font.BOLD, 20));
        school_id.setForeground(new Color(214, 204, 153));
        // school_id.setBorder(new EmptyBorder(20, 0, 10, 0));

        // password label design
        password.setFont(new Font("Montserrat", Font.BOLD, 20));
        password.setForeground(new Color(214, 204, 153));
        // password.setBorder(new EmptyBorder(20, 0, 10, 0));

        // email textfield design
        t_email.setPreferredSize(new Dimension(300, 30));
        t_email.setForeground(new Color(68, 93, 72));
        t_email.setBackground(new Color(214, 204, 153));
        t_email.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        t_email.setFont(new Font("Montserrat", Font.BOLD, 13));

        // password textfield design
        t_password.setPreferredSize(new Dimension(300, 30));
        t_password.setForeground(new Color(68, 93, 72));
        t_password.setBackground(new Color(214, 204, 153));
        t_password.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        t_password.setFont(new Font("Montserrat", Font.BOLD, 13));

        // log in or enter design
        b_enter.setFocusable(false);
        b_enter.setFont(new Font("Montserrat", Font.BOLD, 18));
        b_enter.setForeground(new Color(214, 204, 153));
        b_enter.setBackground(new Color(0, 21, 36));
        b_enter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        b_enter.setPreferredSize(new Dimension(150, 35));

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 200);
        this.setTitle("Log In");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        // add components

        this.add(backBackPannel);
        this.add(headingLabel);
        this.add(inputPanel);

        // adjusting layout and positioning
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(school_id, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(t_email, c);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(password, c);
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(t_password, c);
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(b_enter, c);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ef) {
        if (ef.getSource() == b_enter) {

            String email = t_email.getText();
            String password = String.valueOf(t_password.getPassword());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");

                PreparedStatement pStatement = con
                        .prepareStatement(
                                "SELECT * FROM User WHERE email = ? AND password = ? AND choice = ?");
                pStatement.setString(1, email);
                pStatement.setString(2, password);
                pStatement.setString(3, choice);

                ResultSet rs = pStatement.executeQuery();

                if (rs.next() == true) {
                    String first_name = rs.getString("f_name");
                    String last_name = rs.getString("l_name");
                    String nickname = rs.getString("nickname");
                    String b_date = rs.getString("bdate");
                    String gender = rs.getString("gender");
                    String profession = rs.getString("profession");
                    String country = rs.getString("country");
                    String choice = rs.getString("choice");
                    this.user = new User(first_name, last_name, nickname, b_date, profession, gender,
                            country, choice);
                    this.user.setEmail(email);
                    this.user.setPassword(password);
                    this.dispose();
                    System.out.println("Valid");
                    new Homepage(user);

                } else {
                    JOptionPane.showMessageDialog(null, "Email doesn't exist or password does not match.",
                            "Login unsuccesful", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Invalid");
                    this.dispose();
                    new Login(this.choice, user);

                }
            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (ef.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new Menu(this.choice);
        }

    }

}
