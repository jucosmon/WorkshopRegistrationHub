import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Choice extends JFrame implements ActionListener {
    JButton eventmanager, attendee;
    String choice;

    Choice() {

        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;
        ImageIcon managerIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/manager.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ;
        ImageIcon attendeeIcon = new ImageIcon( // setting up image size
                new ImageIcon("images/man.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ;

        // panel for container for the 2 buttons para matapad
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.setBorder(new EmptyBorder(10, 30, 30, 30)); // add padding or margin
        panel.setBackground(new Color(68, 93, 72));

        JLabel label = new JLabel("Please select your role:");
        label.setFont(new Font("Montserrat", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(214, 204, 153));
        label.setBorder(new EmptyBorder(20, 0, 10, 0)); // add padding or margin

        eventmanager = new JButton("Event Manager");
        attendee = new JButton("Attendee");

        eventmanager.addActionListener(this);
        attendee.addActionListener(this);

        // para mawala tung murag box sa text sa button
        eventmanager.setFocusable(false);
        attendee.setFocusable(false);

        // event design buttons
        eventmanager.setIcon(managerIcon);
        eventmanager.setFont(new Font("Montserrat", Font.BOLD, 15));
        eventmanager.setIconTextGap(5);
        eventmanager.setForeground(new Color(68, 93, 72));
        eventmanager.setBackground(new Color(214, 204, 153));
        eventmanager.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        eventmanager.setPreferredSize(new Dimension(150, 30));

        // attendee button design
        attendee.setIcon(attendeeIcon);
        attendee.setFont(new Font("Montserrat", Font.BOLD, 15));
        attendee.setIconTextGap(5);
        attendee.setForeground(new Color(68, 93, 72));
        attendee.setBackground(new Color(214, 204, 153));
        attendee.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        attendee.setPreferredSize(new Dimension(150, 30));

        // frame design ug properties
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        // this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        this.setTitle("Role");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        // adding components
        this.add(label);
        this.add(panel);
        panel.add(eventmanager);
        panel.add(attendee);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attendee) {
            System.out.println("attendee");
            choice = "Attendee";
            new Menu(choice);
            this.dispose();
        } else if (e.getSource() == eventmanager) {
            System.out.println("eventmanager");
            choice = "EventManager";
            new Menu(choice);
            this.dispose();
        }

    }
}
