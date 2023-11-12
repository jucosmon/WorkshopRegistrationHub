import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener{
    protected String choice;
    JButton login, register;


    Menu(String choice){
        this.choice = choice;
        login = new JButton("Login");
        register = new JButton("Register");

        //login.setBounds(0, 0, 100, 50);
        login.setBounds(0,10,100,30);  
        register.setBounds(0, 60, 100, 30);
        login.addActionListener(this);
        register.addActionListener(this);

        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.add(login);
        this.add(register);

}


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register){
            System.out.println("Register");
            this.dispose();
            new Register(this.choice);
        }
        else if(e.getSource()==login){
            System.out.println("Login");
            this.dispose();
            new Login(this.choice);
        }

    }
    
}
