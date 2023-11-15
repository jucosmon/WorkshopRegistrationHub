import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.Font;

public class Register extends JFrame implements ActionListener {

    protected String choice;

    JFrame register = new JFrame();
    JTextField text_fname, text_lname, /*text_id,*/ text_email, text_nickname, text_country;
    JLabel fname, lname, /*id,*/ pass, repass, email, nickname, bdate, gender, country, profession;
    JButton submit, back;
    JPasswordField pass1, text_repass;
    JFormattedTextField b_date;
    JComboBox cb_gender, cb_prof;

    Register(String choice) {
        this.choice = choice;
        String sex[] = { "Male", "Female", "Others" };
        String prof[] = { "Student", "Working", "Unemployed", "Retired" };

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        b_date = new JFormattedTextField(formatter);
        text_fname = new JTextField();
        text_lname = new JTextField();
       // text_id = new JTextField();
        text_email = new JTextField();
        text_nickname = new JTextField();
        text_country = new JTextField();
        text_repass = new JPasswordField();
        pass1 = new JPasswordField();
        fname = new JLabel("First Name: ");
        lname = new JLabel("Last Name: ");
       // id = new JLabel("ID NO: ");
        pass = new JLabel("Password: ");
        repass = new JLabel("Re-Enter Password: ");
        email = new JLabel("Email:");
        country = new JLabel("Country:");
        nickname = new JLabel("Nickname:");
        bdate = new JLabel("Birthdate:");
        submit = new JButton("Register");
        back = new JButton("Back");
        cb_gender = new JComboBox(sex);
        cb_prof = new JComboBox(prof);
        gender = new JLabel("Gender");
        profession = new JLabel("Profession:");

        // setbounds
        text_fname.setBounds(211, 25, 200, 30);
        text_lname.setBounds(211, 75, 200, 30);
       // text_id.setBounds(211, 125, 200, 30);
        pass1.setBounds(211, 175, 200, 30);
        text_repass.setBounds(211, 225, 200, 30);
        text_email.setBounds(211, 275, 200, 30);
        text_nickname.setBounds(211, 325, 200, 30);
        b_date.setBounds(211, 375, 200, 30);
        cb_gender.setBounds(150, 425, 100, 30);
        text_country.setBounds(211, 475, 200, 30);
        cb_prof.setBounds(375, 425, 100, 30);

        // end of setbounds

        // label
        fname.setBounds(110, 25, 150, 30);
        fname.setFont(new Font("Serif", Font.PLAIN, 20));
        lname.setBounds(110, 75, 150, 30);
        lname.setFont(new Font("Serif", Font.PLAIN, 20));

       // id.setBounds(110, 125, 150, 30);
       // id.setFont(new Font("Serif", Font.PLAIN, 20));

        pass.setBounds(110, 175, 150, 30);
        pass.setFont(new Font("Serif", Font.PLAIN, 20));

        repass.setBounds(40, 225, 200, 30);
        repass.setFont(new Font("Serif", Font.PLAIN, 20));

        email.setBounds(100, 275, 200, 30);
        email.setFont(new Font("Serif", Font.PLAIN, 20));

        nickname.setBounds(100, 325, 200, 30);
        nickname.setFont(new Font("Serif", Font.PLAIN, 20));

        bdate.setBounds(100, 375, 200, 30);
        bdate.setFont(new Font("Serif", Font.PLAIN, 20));

        gender.setBounds(50, 425, 200, 30);
        gender.setFont(new Font("Serif", Font.PLAIN, 20));

        profession.setBounds(275, 425, 200, 30);
        profession.setFont(new Font("Serif", Font.PLAIN, 20));

        country.setBounds(100, 475, 200, 30);
        country.setFont(new Font("Serif", Font.PLAIN, 20));

        submit.setBounds(260, 525, 100, 30);
        back.setBounds(0, 0, 100, 30);
        // end of label

        // submit action button
        submit.addActionListener(this);
        back.addActionListener(this);
        // ---end of submit button---

        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(text_fname);
        this.add(text_lname);
       // this.add(text_id);
        this.add(pass1);
        this.add(text_repass);
        this.add(submit);
        this.add(fname);
        this.add(lname);
       // this.add(id);
        this.add(pass);
        this.add(repass);
        this.add(back);
        this.add(b_date);
        this.add(text_nickname);
        this.add(text_email);
        this.add(email);
        this.add(nickname);
        this.add(bdate);
        this.add(cb_gender);
        this.add(gender);
        this.add(country);
        this.add(text_country);
        this.add(cb_prof);
        this.add(profession);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            String s_gender = cb_gender.getSelectedItem().toString();
            String s_prof = cb_prof.getSelectedItem().toString();
            String email = text_email.getText();
            String country = text_country.getText();
            User user = new User(text_fname.getText(), text_lname.getText(), text_nickname.getText(), b_date.getText(),
                    s_prof, s_gender, country, this.choice);
           // int id = Integer.parseInt(text_id.getText());
            user.setPassword(new String(pass1.getPassword()));
           // user.setID(id);
            user.setEmail(email);
            System.out.println(
                    "Hello " + user.first_name + " " + user.last_name + "\n" + /*user.getID()/ +*/ "\n" + user.getPassword()
                            + "\n" + user.b_date + "\n" + user.nickname + "\n" + s_gender + "\n" + country);
            user.databaseInsert();

        } else if (e.getSource() == back) {
            this.dispose();
            new Menu(choice);
        }
    }
}
