import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame implements ActionListener {
    // gobal variables
    protected String choice;
    JFrame register = new JFrame();
    JTextField text_fname, text_lname, /* text_id, */ text_email, text_nickname, text_country;
    JLabel fname, lname, /* id, */ pass, repass, email, nickname, bdate, gender, country, profession;
    JButton submit, backButton;
    JPasswordField pass1, text_repass;
    JFormattedTextField b_date;
    JComboBox cb_gender, cb_prof;

    Register(String choice) {
        // local variables
        this.choice = choice;
        String sex[] = { "Male", "Female", "Others" };
        String prof[] = { "Student", "Working", "Unemployed", "Retired" };

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

        JLabel headingLabel = new JLabel("Welcome " + choiceDisplay + "!");
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

        // components
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        b_date = new JFormattedTextField(formatter);
        text_fname = new JTextField();
        text_lname = new JTextField();
        text_email = new JTextField();
        text_nickname = new JTextField();
        text_country = new JTextField("Philippines");
        text_repass = new JPasswordField();
        pass1 = new JPasswordField();
        fname = new JLabel("First Name: ");
        lname = new JLabel("Last Name: ");
        pass = new JLabel("Password: ");
        repass = new JLabel("Re-Enter Password: ");
        email = new JLabel("Email:");
        country = new JLabel("Country:");
        nickname = new JLabel("Nickname:");
        bdate = new JLabel("Birthdate:");
        submit = new JButton("Register");
        cb_gender = new JComboBox(sex);
        cb_prof = new JComboBox(prof);
        gender = new JLabel("Gender");
        profession = new JLabel("Profession:");
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

        // label designs
        // first name
        fname.setFont(new Font("Montserrat", Font.BOLD, 20));
        fname.setForeground(new Color(214, 204, 153));

        // last name
        lname.setFont(new Font("Montserrat", Font.BOLD, 20));
        lname.setForeground(new Color(214, 204, 153));

        // email
        email.setFont(new Font("Montserrat", Font.BOLD, 20));
        email.setForeground(new Color(214, 204, 153));

        // password
        pass.setFont(new Font("Montserrat", Font.BOLD, 20));
        pass.setForeground(new Color(214, 204, 153));

        // re enter password
        repass.setFont(new Font("Montserrat", Font.BOLD, 20));
        repass.setForeground(new Color(214, 204, 153));

        // nickname
        nickname.setFont(new Font("Montserrat", Font.BOLD, 20));
        nickname.setForeground(new Color(214, 204, 153));

        // birtdate
        bdate.setFont(new Font("Montserrat", Font.BOLD, 20));
        bdate.setForeground(new Color(214, 204, 153));

        // country
        country.setFont(new Font("Montserrat", Font.BOLD, 20));
        country.setForeground(new Color(214, 204, 153));

        // gender
        gender.setFont(new Font("Montserrat", Font.BOLD, 20));
        gender.setForeground(new Color(214, 204, 153));

        // profession
        profession.setFont(new Font("Montserrat", Font.BOLD, 20));
        profession.setForeground(new Color(214, 204, 153));

        // texfield design
        // firstname
        text_fname.setPreferredSize(new Dimension(300, 30));
        text_fname.setForeground(new Color(68, 93, 72));
        text_fname.setBackground(new Color(214, 204, 153));
        text_fname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_fname.setFont(new Font("Montserrat", Font.BOLD, 13));

        // lastname
        text_lname.setPreferredSize(new Dimension(300, 30));
        text_lname.setForeground(new Color(68, 93, 72));
        text_lname.setBackground(new Color(214, 204, 153));
        text_lname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_lname.setFont(new Font("Montserrat", Font.BOLD, 13));

        // email
        text_email.setPreferredSize(new Dimension(300, 30));
        text_email.setForeground(new Color(68, 93, 72));
        text_email.setBackground(new Color(214, 204, 153));
        text_email.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_email.setFont(new Font("Montserrat", Font.BOLD, 13));

        // password
        pass1.setPreferredSize(new Dimension(300, 30));
        pass1.setForeground(new Color(68, 93, 72));
        pass1.setBackground(new Color(214, 204, 153));
        pass1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pass1.setFont(new Font("Montserrat", Font.BOLD, 13));

        // re enter pasword
        text_repass.setPreferredSize(new Dimension(300, 30));
        text_repass.setForeground(new Color(68, 93, 72));
        text_repass.setBackground(new Color(214, 204, 153));
        text_repass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_repass.setFont(new Font("Montserrat", Font.BOLD, 13));

        // nickname
        text_nickname.setPreferredSize(new Dimension(300, 30));
        text_nickname.setForeground(new Color(68, 93, 72));
        text_nickname.setBackground(new Color(214, 204, 153));
        text_nickname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_nickname.setFont(new Font("Montserrat", Font.BOLD, 13));

        // birthdate
        b_date.setPreferredSize(new Dimension(300, 30));
        b_date.setForeground(new Color(68, 93, 72));
        b_date.setBackground(new Color(214, 204, 153));
        b_date.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        b_date.setFont(new Font("Montserrat", Font.BOLD, 13));

        // country
        text_country.setPreferredSize(new Dimension(300, 30));
        text_country.setForeground(new Color(68, 93, 72));
        text_country.setBackground(new Color(214, 204, 153));
        text_country.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_country.setFont(new Font("Montserrat", Font.BOLD, 13));

        // gender
        cb_gender.setEditable(true);
        cb_gender.setPreferredSize(new Dimension(300, 30));
        cb_gender.setForeground(new Color(68, 93, 72));
        cb_gender.setBackground(new Color(214, 204, 153));
        cb_gender.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cb_gender.getEditor().getEditorComponent().setBackground(new Color(214, 204, 153));
        cb_gender.getEditor().getEditorComponent().setForeground(new Color(68, 93, 72));
        ((JComponent) cb_gender.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder());

        // profession
        cb_prof.setEditable(true);
        cb_prof.setPreferredSize(new Dimension(300, 30));
        cb_prof.setForeground(new Color(68, 93, 72));
        cb_prof.setBackground(new Color(214, 204, 153));
        cb_prof.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cb_prof.getEditor().getEditorComponent().setFocusable(false); // Remove focusable behavior on the text
        cb_prof.getEditor().getEditorComponent().setBackground(new Color(214, 204, 153));
        cb_prof.getEditor().getEditorComponent().setForeground(new Color(68, 93, 72));
        ((JComponent) cb_prof.getEditor().getEditorComponent()).setBorder(BorderFactory.createEmptyBorder());

        // submit design
        submit.setFocusable(false);
        submit.setFont(new Font("Montserrat", Font.BOLD, 18));
        submit.setForeground(new Color(214, 204, 153));
        submit.setBackground(new Color(0, 21, 36));
        submit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        submit.setPreferredSize(new Dimension(150, 35));
        // submit action button
        submit.addActionListener(this);

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(450, 15);
        this.setTitle("Register");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        // adding components on the frame/panels
        this.add(backBackPannel);
        this.add(headingLabel);
        this.add(inputPanel);

        // adjusting layout and positioning
        // first name
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(fname, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_fname, c);
        // last name
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(lname, c);
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_lname, c);
        // email
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(email, c);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_email, c);
        // password
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(pass, c);
        c.gridy = 3;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(pass1, c);
        // reenter password
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(repass, c);
        c.gridy = 4;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_repass, c);
        // nickname
        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(nickname, c);
        c.gridy = 5;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_nickname, c);
        // birthdate
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(bdate, c);
        c.gridy = 6;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(b_date, c);
        // country
        c.gridy = 7;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(country, c);
        c.gridy = 7;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_country, c);
        // gender
        c.gridy = 8;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(gender, c);
        c.gridy = 8;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(cb_gender, c);
        // profession
        c.gridy = 9;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(profession, c);
        c.gridy = 9;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(cb_prof, c);
        // submit button
        c.gridy = 10;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(submit, c);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] pass1Array = pass1.getPassword();
        char[] repassArray = text_repass.getPassword();

        if (e.getSource() == submit) {
            // checking if correct ang password nga gi reenter
            if (Arrays.equals(pass1Array, repassArray)) {

                String s_gender = cb_gender.getSelectedItem().toString();
                String s_prof = cb_prof.getSelectedItem().toString();
                String email = text_email.getText();
                String country = text_country.getText();
                User user = new User(text_fname.getText(), text_lname.getText(), text_nickname.getText(),
                        b_date.getText(),
                        s_prof, s_gender, country, this.choice);
                user.setPassword(new String(pass1.getPassword()));
                user.setEmail(email);
                System.out.println(
                        "Hello " + user.first_name + " " + user.last_name + "\n" + /* user.getID()/ + */ "\n"
                                + user.getPassword()
                                + "\n" + user.b_date + "\n" + user.nickname + "\n" + s_gender + "\n" + country);

                boolean validation = user.databaseInsert();

                if (validation == true) {
                    JOptionPane.showMessageDialog(null, "Successfully created account. Please log in",
                            "Account Created", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new Login(choice, user);
                } else {
                    this.dispose();
                    new Register(choice);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password does not match",
                        "Password error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Password does not match");
                this.dispose();
                new Register(choice);
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            new Menu(choice);
        }
    }
}
