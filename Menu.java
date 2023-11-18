import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame implements ActionListener {
    protected String choice;
    JButton login, register, backButton;
    String choiceDisplay;

    Menu(String choice) {
        this.choice = choice;

        // used for displaying label to not affect the choice variable name
        if (choice == "EventManager") {
            choiceDisplay = "Event Manager";
        } else {
            choiceDisplay = "Attendee";
        }

        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;
        ImageIcon loginIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/login.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ;
        ImageIcon regIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/register.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ;
        ImageIcon backButtonIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/back.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        ;

        // back button
        backButton = new JButton("Go Back");
        backButton.addActionListener(this);
        // back button design
        backButton.setIcon(backButtonIcon);
        backButton.setFocusable(false);
        backButton.setFont(new Font("Montserrat", Font.BOLD, 13));
        backButton.setIconTextGap(10);
        backButton.setForeground(new Color(214, 204, 153));
        backButton.setBackground(new Color(0, 21, 36));
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        backButton.setPreferredSize(new Dimension(100, 25));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // container for back button just for adding margin
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButton);
        backPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); // add padding or margin
        backPanel.setBackground(new Color(68, 93, 72));

        // panel for container for the 2 buttons para matapad
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.setBorder(new EmptyBorder(5, 30, 15, 30)); // add padding or margin
        panel.setBackground(new Color(68, 93, 72));

        JLabel label = new JLabel("Welcome " + choiceDisplay + "!");
        label.setFont(new Font("Montserrat", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(214, 204, 153));
        label.setBorder(new EmptyBorder(20, 0, 10, 0)); // add padding or margin

        login = new JButton("Log in");
        register = new JButton("Register");

        login.addActionListener(this);
        register.addActionListener(this);

        // para mawala tung murag box sa text sa button
        login.setFocusable(false);
        register.setFocusable(false);

        // event design buttons
        register.setIcon(regIcon);
        register.setFont(new Font("Montserrat", Font.BOLD, 15));
        register.setIconTextGap(5);
        register.setForeground(new Color(68, 93, 72));
        register.setBackground(new Color(214, 204, 153));
        register.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        register.setPreferredSize(new Dimension(100, 30));

        // attendee button design
        login.setIcon(loginIcon);
        login.setFont(new Font("Montserrat", Font.BOLD, 15));
        login.setIconTextGap(5);
        login.setForeground(new Color(68, 93, 72));
        login.setBackground(new Color(214, 204, 153));
        login.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        login.setPreferredSize(new Dimension(100, 30));

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        this.setTitle("Welcome");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        // adding components
        this.add(label);
        this.add(panel);
        panel.add(register);
        panel.add(login);
        this.add(backPanel);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            System.out.println("Register");
            this.dispose();
            new Register(this.choice);
        } else if (e.getSource() == login) {
            System.out.println("Login");
            this.dispose();
            new Login(this.choice, null);
        } else if (e.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new Choice();
        }

    }

}
