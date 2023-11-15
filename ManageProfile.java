import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class ManageProfile extends JFrame implements ActionListener {
    JButton backButton, editButton, deleteButton;
    User user;

    ManageProfile(User user) {
        JLabel imageProfile;
        this.user = user;
        // image used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;
        ImageIcon profileIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/profile.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        ;

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
        JLabel headingLabel = new JLabel("Profile Information");
        headingLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingLabel.setForeground(new Color(214, 204, 153));
        headingLabel.setBorder(new EmptyBorder(20, 0, 20, 0)); // add padding or margin

        imageProfile = new JLabel(profileIcon);

        imageProfile.setAlignmentX(Component.CENTER_ALIGNMENT);

        // name
        JLabel nameLabel = new JLabel(this.user.getFirstName() + " " + this.user.getLastName());
        nameLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setForeground(new Color(214, 204, 153));
        nameLabel.setBorder(new EmptyBorder(10, 0, 0, 0)); // add padding or margin
        // profession
        JLabel professionLabel = new JLabel(this.user.getProfession());
        professionLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        professionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        professionLabel.setForeground(new Color(214, 204, 153));
        professionLabel.setBorder(new EmptyBorder(5, 0, 0, 0)); // add padding or margin

        // panel for the email to gender
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 50, 50, 100)); // add padding or margin
        panel.setBackground(new Color(68, 93, 72));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(21, 0, 0, 0); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side
        // email
        JLabel emailLabel = new JLabel("Email: " + this.user.getEmail());
        emailLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        emailLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        emailLabel.setForeground(new Color(214, 204, 153));
        // country
        JLabel locationLabel = new JLabel("Country: " + this.user.getCountry());
        locationLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        locationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        locationLabel.setForeground(new Color(214, 204, 153));
        // birthdate
        JLabel birthdayLabel = new JLabel("Birthday: " + this.user.getBirthDate());
        birthdayLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        birthdayLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        birthdayLabel.setForeground(new Color(214, 204, 153));
        // gender
        JLabel genderLabel = new JLabel("Gender: " + this.user.getGender());
        genderLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        genderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        genderLabel.setForeground(new Color(214, 204, 153));
        // nickname
        JLabel nickNameLabel = new JLabel("Nickname: " + this.user.getNickname());
        nickNameLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        nickNameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nickNameLabel.setForeground(new Color(214, 204, 153));

        // panel for container for the 2 buttons para matapad
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 30, 0)); // add padding or margin
        buttonsPanel.setBackground(new Color(68, 93, 72));

        editButton = new JButton("Edit Account");
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(this);

        // para mawala tung murag box sa text sa button
        editButton.setFocusable(false);
        deleteButton.setFocusable(false);

        // edit design buttons
        editButton.setFont(new Font("Montserrat", Font.BOLD, 17));
        editButton.setForeground(new Color(214, 204, 153));
        editButton.setBackground(new Color(0, 21, 36));
        editButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        editButton.setPreferredSize(new Dimension(150, 35));

        // deleteButton button design
        deleteButton.setFont(new Font("Montserrat", Font.BOLD, 17));
        deleteButton.setForeground(new Color(214, 204, 153));
        deleteButton.setBackground(new Color(0, 21, 36));
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        deleteButton.setPreferredSize(new Dimension(150, 35));

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
        this.add(imageProfile);
        this.add(nameLabel);
        this.add(professionLabel);
        c.gridy = 0;
        c.gridx = 0;
        panel.add(emailLabel, c);
        c.gridy = 1;
        c.gridx = 0;
        panel.add(locationLabel, c);
        c.gridy = 2;
        c.gridx = 0;
        panel.add(birthdayLabel, c);
        c.gridy = 3;
        c.gridx = 0;
        panel.add(genderLabel, c);
        c.gridy = 4;
        c.gridx = 0;
        panel.add(nickNameLabel, c);
        this.add(panel);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        this.add(buttonsPanel);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new Homepage(this.user);
        }

    }
}
