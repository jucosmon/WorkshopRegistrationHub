import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class Register extends JFrame implements ActionListener{


    JFrame register = new JFrame();
    JTextField text_fname, text_lname, text_id;
    JLabel fname, lname, id, pass, repass; 
    JButton submit, back; 
    JPasswordField pass1, text_repass;

    Register(){

    
        text_fname = new JTextField();
        text_lname = new JTextField();
        text_id = new JTextField();
        text_repass = new JPasswordField();
        pass1= new JPasswordField();
        fname = new JLabel("First Name: ");
        lname = new JLabel("Last Name: ");
        id = new JLabel("ID NO: ");
        pass = new JLabel("Password: ");
        repass = new JLabel("Re-Enter Password: ");
        submit = new JButton("Register");
        back = new JButton("Back");

         //setbounds
         text_fname.setBounds(211, 100, 200, 30);
         text_lname.setBounds(211, 150, 200, 30);
         text_id.setBounds(211, 200, 200, 30);
         /*text_*/pass1.setBounds(211, 250, 200, 30);
         text_repass.setBounds(211, 300, 200, 30);
         //end of setbounds
 
         //label
         fname.setBounds(110, 100, 150, 30);
         fname.setFont(new Font("Serif", Font.PLAIN, 20));
         lname.setBounds(110, 150, 150, 30);
         lname.setFont(new Font("Serif", Font.PLAIN, 20));
 
         id.setBounds(110, 200, 150, 30);
         id.setFont(new Font("Serif", Font.PLAIN, 20));
 
         pass.setBounds(110, 250, 150, 30);
         pass.setFont(new Font("Serif", Font.PLAIN, 20));
 
         repass.setBounds(40, 300, 200, 30);
         repass.setFont(new Font("Serif", Font.PLAIN, 20));

         submit.setBounds(260,350,100,30); 
         back.setBounds(0,0,100,30); 
         //end of label
        
    //submit action button
    submit.addActionListener(this);
    back.addActionListener(this);
    //---end of submit button---
        
    this.setLayout(null);
    this.setSize(600, 600);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(text_fname);
        this.add(text_lname);
        this.add(text_id);
        this.add(pass1);
        this.add(text_repass);
        this.add(submit);
        this.add(fname);
        this.add(lname);
        this.add(id);
        this.add(pass);
        this.add(repass);
        this.add(back);

    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
                User user = new User(text_fname.getText(), text_lname.getText());
                int id = Integer.parseInt(text_id.getText());
                user.setPassword(new String(pass1.getPassword()));  
                user.setID(id);
                System.out.println("Hello " + user.first_name + " " +  user.last_name + "\n" + user.getID() + "\n" + user.getPassword());
                user.databaseInsert(user.first_name, user.last_name, user.getID(), user.getPassword());
                
                
        }
        else if(e.getSource()==back){
            this.dispose();
            new Menu();
        }
    }
}
