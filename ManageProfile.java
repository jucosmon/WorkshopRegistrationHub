import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class ManageProfile extends JFrame implements ActionListener {
    JButton backButton, editButton, deleteButton, okay1, okay2;
    User user;
    JTable table;
    int code;
    String title;
    JComboBox selectColumn, selectGender, selectProfession;
    JLabel l_selectColumn, textFieldLabel;
    JTextField textField;

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
        String[] columnfields = { "first_name", "last_name", "password", "nickname", "birthday", "gender",
                "profession", "country" };
        String[] genderOptions = { "Male", "Female", "Others" };
        String[] professionOptions = { "Student", "Working", "Unemployed", "Retired" };
        // declaring components
        textFieldLabel = new JLabel("Enter new value: ");
        l_selectColumn = new JLabel("Choose options: ");
        selectGender = new JComboBox(genderOptions);
        selectProfession = new JComboBox(professionOptions);
        okay1 = new JButton("Okay");
        okay2 = new JButton("Save");
        selectColumn = new JComboBox(columnfields);
        textField = new JTextField();

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
        panel.setBorder(new EmptyBorder(5, 50, 5, 100)); // add padding or margin
        panel.setBackground(new Color(68, 93, 72));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(21, 0, 0, 0); // add gaps
        constraints.anchor = GridBagConstraints.WEST; // aligning it to the left side
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
        buttonsPanel.setBorder(new EmptyBorder(5, 0, 50, 0)); // add padding or margin
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

        // some other components at the bottom of the edit and delete button
        JPanel selectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 10, 5); // add gaps
        c.anchor = GridBagConstraints.WEST; // aligning it to the left side
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

        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ef) {
                // disabling previous buttons kay nakaclick naman
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);

                // label select column positioning
                c.gridy = 0;
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

                        // if else para if incase gender or profession ang pilion, combo box ang mugawas
                        if (column == "gender") {
                            c.gridy = 1;
                            c.gridx = 0;
                            c.gridwidth = 1;
                            c.fill = GridBagConstraints.NONE;
                            selectPanel.add(l_selectColumn, c);
                            // textfield positioning
                            c.gridx = 1;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(selectGender, c);
                            // okay 2 positioning
                            c.gridx = 2;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(okay2, c);
                            selectPanel.revalidate();
                            selectPanel.repaint();
                        } else if (column == "profession") {
                            c.gridy = 1;
                            c.gridx = 0;
                            c.gridwidth = 1;
                            c.fill = GridBagConstraints.NONE;
                            selectPanel.add(l_selectColumn, c);
                            // textfield positioning
                            c.gridx = 1;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(selectProfession, c);
                            // okay 2 positioning
                            c.gridx = 2;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(okay2, c);
                            selectPanel.revalidate();
                            selectPanel.repaint();
                        } else {
                            c.gridy = 1;
                            c.gridx = 0;
                            c.gridwidth = 1;
                            c.fill = GridBagConstraints.NONE;
                            selectPanel.add(textFieldLabel, c);
                            // textfield positioning
                            c.gridx = 1;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(textField, c);
                            // okay 2 positioning
                            c.gridx = 2;
                            c.fill = GridBagConstraints.HORIZONTAL;
                            selectPanel.add(okay2, c);
                            selectPanel.revalidate();
                            selectPanel.repaint();

                        }

                        // if okay 2 is clicked after confiriming the new inputted text
                        okay2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ef) {
                                // get the updatedText
                                String updatedText;
                                if (column == "gender") {
                                    updatedText = (String) selectGender.getSelectedItem();
                                } else if (column == "profession") {
                                    updatedText = (String) selectProfession.getSelectedItem();
                                } else {
                                    updatedText = textField.getText();
                                }

                                // check
                                System.out.println(column + " " + updatedText);

                                // then updating it onto the database
                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con = DriverManager
                                            .getConnection("jdbc:mysql://localhost/pfbaliwis", "root", "");
                                    String sql;
                                    PreparedStatement pstmt;
                                    switch (column) {
                                        case "first_name":
                                            sql = "UPDATE user SET f_name = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setFirstName(updatedText);
                                            System.out.println("Update Success");
                                            break;
                                        case "last_name":
                                            sql = "UPDATE user SET l_name = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setLastName(updatedText);
                                            System.out.println("Update Success");
                                            break;
                                        case "password":
                                            sql = "UPDATE user SET password = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setPassword(updatedText);
                                            System.out.println("Update Success");
                                            break;
                                        case "nickname":
                                            sql = "UPDATE user SET nickname = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setNickName(updatedText);
                                            System.out.println("Update Success");
                                            break;
                                        case "birthday":
                                            sql = "UPDATE user SET bdate = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setBirthdate(updatedText);
                                            System.out.println("Update Success");
                                            break;

                                        case "gender":
                                            sql = "UPDATE user SET gender = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setGender(updatedText);
                                            System.out.println("Update Success");
                                            break;

                                        case "profession":
                                            sql = "UPDATE user SET profession = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setProfession(updatedText);
                                            System.out.println("Update Success");
                                            break;

                                        case "country":
                                            sql = "UPDATE user SET country = ? WHERE email = ?";
                                            pstmt = con.prepareStatement(sql);
                                            // pstmt.setString(2, column);
                                            pstmt.setString(1, updatedText);
                                            pstmt.setString(2, user.getEmail());
                                            pstmt.executeUpdate();
                                            user.setCountry(updatedText);
                                            System.out.println("Update Success");
                                            break;

                                    }
                                    dispose();
                                    new ManageProfile(user);

                                    con.close();

                                } catch (Exception e) {
                                    dispose();
                                    new ManageProfile(user);
                                }
                            }
                        });

                    }
                });

            }

        });

        // if delete button is selected
        deleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ef) {
                String email = user.getEmail();
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pfbaliwis", "root",
                            "");

                    PreparedStatement st = con.prepareStatement("DELETE FROM user WHERE email = ?");
                    st.setString(1, email);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account deleted successfully");
                    System.out.println(email + "" + "is deleted from database");
                    dispose();
                    new Choice();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Account not deleted",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new ManageProfile(user);
                }
            }

        });

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(450, 50);
        this.setTitle("Profile");
        this.setIconImage(logo.getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));
        // adding components
        this.add(backBackPannel);
        this.add(headingLabel);
        this.add(imageProfile);
        this.add(nameLabel);
        this.add(professionLabel);
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel.add(emailLabel, constraints);
        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(locationLabel, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(birthdayLabel, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(genderLabel, constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(nickNameLabel, constraints);
        this.add(panel);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        this.add(buttonsPanel);
        this.add(selectPanel);
        this.revalidate();
        this.repaint();

        // this.pack();
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
