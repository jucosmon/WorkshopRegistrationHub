import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Homepage extends JFrame {
    User user;
    String email;
    String fname;
    String lname;
    String choice;

    public Homepage(User user) {
        this.user = user;
        this.email = user.getEmail();
        this.choice = user.getChoice();

        // components
        JButton createWorkshop = new JButton("Create Workshop");
        JButton manageWorkshop = new JButton("Manage Existing Workshop");
        JButton manageProfile = new JButton("Manage Profile");
        JButton upcomingEvents = new JButton("Upcoming Events");
        JButton registeredEvents = new JButton("My Registered Events");
        JButton exit = new JButton("Log out");

        // image used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;
        // back button
        exit = new JButton("Log out");
        // back button design
        exit.setFocusable(false);
        exit.setFont(new Font("Montserrat", Font.BOLD, 15));
        exit.setForeground(new Color(68, 93, 72));
        exit.setBackground(new Color(214, 204, 153));
        exit.setPreferredSize(new Dimension(130, 30));

        // panel for back button para d macenter
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        exitPanel.add(exit);
        exitPanel.setBackground(new Color(68, 93, 72));

        // heading
        JLabel headingLabel = new JLabel("Hello " + this.user.getNickname() + "!");
        headingLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingLabel.setForeground(new Color(214, 204, 153));
        headingLabel.setBorder(new EmptyBorder(20, 0, 10, 0)); // add padding or margin

        JPanel buttonsPannel = new JPanel(new GridBagLayout());
        buttonsPannel.setBorder(new EmptyBorder(10, 150, 50, 150)); // add padding or margin
        buttonsPannel.setBackground(new Color(68, 93, 72));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 5, 10, 5); // add gaps
        constraints.anchor = GridBagConstraints.CENTER; // aligning it to the center

        // buttons design
        // creatworkshop design buttons
        createWorkshop.setFont(new Font("Montserrat", Font.BOLD, 15));
        createWorkshop.setForeground(new Color(68, 93, 72));
        createWorkshop.setBackground(new Color(214, 204, 153));
        createWorkshop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        createWorkshop.setPreferredSize(new Dimension(300, 30));
        createWorkshop.setFocusable(false);
        // manage Workshop design buttons
        manageWorkshop.setFont(new Font("Montserrat", Font.BOLD, 15));
        manageWorkshop.setForeground(new Color(68, 93, 72));
        manageWorkshop.setBackground(new Color(214, 204, 153));
        manageWorkshop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        manageWorkshop.setPreferredSize(new Dimension(300, 30));
        manageWorkshop.setFocusable(false);
        // manage profile design buttons
        manageProfile.setFont(new Font("Montserrat", Font.BOLD, 15));
        manageProfile.setForeground(new Color(68, 93, 72));
        manageProfile.setBackground(new Color(214, 204, 153));
        manageProfile.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        manageProfile.setPreferredSize(new Dimension(300, 30));
        manageProfile.setFocusable(false);
        // Registered events design buttons
        registeredEvents.setFont(new Font("Montserrat", Font.BOLD, 15));
        registeredEvents.setForeground(new Color(68, 93, 72));
        registeredEvents.setBackground(new Color(214, 204, 153));
        registeredEvents.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        registeredEvents.setPreferredSize(new Dimension(300, 30));
        registeredEvents.setFocusable(false);
        // manage Events design buttons
        upcomingEvents.setFont(new Font("Montserrat", Font.BOLD, 15));
        upcomingEvents.setForeground(new Color(68, 93, 72));
        upcomingEvents.setBackground(new Color(214, 204, 153));
        upcomingEvents.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        upcomingEvents.setPreferredSize(new Dimension(300, 30));
        upcomingEvents.setFocusable(false);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Choice();
            }
        });
        switch (this.choice) {
            case "EventManager":
                // Display choices for EventManager
                createWorkshop.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new CreateWorkshop(user);
                    }
                });

                manageWorkshop.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new manageWorkshop(user);
                    }
                });

                manageProfile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new ManageProfile(user);
                    }
                });

                constraints.gridy = 0;
                constraints.gridx = 0;
                buttonsPannel.add(createWorkshop, constraints);
                constraints.gridy = 1;
                constraints.gridx = 0;
                buttonsPannel.add(manageWorkshop, constraints);
                constraints.gridy = 2;
                constraints.gridx = 0;
                buttonsPannel.add(manageProfile, constraints);
                break;

            case "Attendee":
                // Display choices for Attendee
                upcomingEvents.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new UpcomingEvents(user);
                    }
                });

                registeredEvents.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new RegisteredEvents(user);
                    }
                });

                manageProfile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new ManageProfile(user);
                    }
                });

                constraints.gridy = 0;
                constraints.gridx = 0;
                buttonsPannel.add(upcomingEvents, constraints);
                constraints.gridy = 1;
                constraints.gridx = 0;
                buttonsPannel.add(registeredEvents, constraints);
                constraints.gridy = 2;
                constraints.gridx = 0;
                buttonsPannel.add(manageProfile, constraints);
                break;

            default:
                // Handle other values of this.choice
                System.out.println(this.choice);
        }

        // frame design and properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 200);
        this.setTitle("Home Page");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        this.add(exitPanel);
        this.add(headingLabel);
        this.add(buttonsPannel);

        this.pack();
        this.setVisible(true);
    }

}
