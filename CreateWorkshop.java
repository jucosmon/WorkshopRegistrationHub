import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class CreateWorkshop extends JFrame implements ActionListener {
    String email, choice;
    JTextField text_title, text_description, text_venue, text_speakers, text_host, text_org;
    JLabel ws_code, title, description, venue, speakers, host, org, date;
    JButton submit, backButton;
    JFormattedTextField event_date;
    User user;

    public CreateWorkshop(User user) {
        this.user = user;
        this.email = user.getEmail();
        this.choice = user.getChoice();
        // images used
        ImageIcon logo = new ImageIcon( // setting up image size
                new ImageIcon("images/logo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        ;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        text_title = new JTextField();
        text_description = new JTextField();
        text_venue = new JTextField();
        text_speakers = new JTextField();
        text_host = new JTextField();
        text_org = new JTextField();
        ws_code = new JLabel("Workshop Code");
        title = new JLabel("Workshop Title:");
        description = new JLabel("Description:");
        venue = new JLabel("Venue:");
        speakers = new JLabel("Speaker");
        host = new JLabel("Host:");
        org = new JLabel("Organization:");
        submit = new JButton("Create");
        date = new JLabel("Event Date:");
        event_date = new JFormattedTextField(formatter);

        JLabel headingLabel = new JLabel("Create your new Event!");
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
        // title
        title.setFont(new Font("Montserrat", Font.BOLD, 17));
        title.setForeground(new Color(214, 204, 153));

        // description
        description.setFont(new Font("Montserrat", Font.BOLD, 17));
        description.setForeground(new Color(214, 204, 153));

        // venue
        venue.setFont(new Font("Montserrat", Font.BOLD, 17));
        venue.setForeground(new Color(214, 204, 153));

        // speakers
        speakers.setFont(new Font("Montserrat", Font.BOLD, 17));
        speakers.setForeground(new Color(214, 204, 153));

        // host
        host.setFont(new Font("Montserrat", Font.BOLD, 17));
        host.setForeground(new Color(214, 204, 153));

        // org
        org.setFont(new Font("Montserrat", Font.BOLD, 17));
        org.setForeground(new Color(214, 204, 153));
        // date
        date.setFont(new Font("Montserrat", Font.BOLD, 17));
        date.setForeground(new Color(214, 204, 153));

        // texfield design
        // title
        text_title.setPreferredSize(new Dimension(300, 30));
        text_title.setForeground(new Color(68, 93, 72));
        text_title.setBackground(new Color(214, 204, 153));
        text_title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_title.setFont(new Font("Montserrat", Font.BOLD, 13));

        // description
        text_description.setPreferredSize(new Dimension(300, 30));
        text_description.setForeground(new Color(68, 93, 72));
        text_description.setBackground(new Color(214, 204, 153));
        text_description.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_description.setFont(new Font("Montserrat", Font.BOLD, 13));

        // venue
        text_venue.setPreferredSize(new Dimension(300, 30));
        text_venue.setForeground(new Color(68, 93, 72));
        text_venue.setBackground(new Color(214, 204, 153));
        text_venue.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_venue.setFont(new Font("Montserrat", Font.BOLD, 13));

        // speakers
        text_speakers.setPreferredSize(new Dimension(300, 30));
        text_speakers.setForeground(new Color(68, 93, 72));
        text_speakers.setBackground(new Color(214, 204, 153));
        text_speakers.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_speakers.setFont(new Font("Montserrat", Font.BOLD, 13));

        // host
        text_host.setPreferredSize(new Dimension(300, 30));
        text_host.setForeground(new Color(68, 93, 72));
        text_host.setBackground(new Color(214, 204, 153));
        text_host.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_host.setFont(new Font("Montserrat", Font.BOLD, 13));

        // org
        text_org.setPreferredSize(new Dimension(300, 30));
        text_org.setForeground(new Color(68, 93, 72));
        text_org.setBackground(new Color(214, 204, 153));
        text_org.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        text_org.setFont(new Font("Montserrat", Font.BOLD, 13));

        // date
        event_date.setPreferredSize(new Dimension(300, 30));
        event_date.setForeground(new Color(68, 93, 72));
        event_date.setBackground(new Color(214, 204, 153));
        event_date.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        event_date.setFont(new Font("Montserrat", Font.BOLD, 13));

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
        this.setLocation(500, 100);
        this.setTitle("Create Worshop");
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(68, 93, 72));

        this.add(backBackPannel);
        this.add(headingLabel);
        this.add(inputPanel);

        // adjusting layout and positioning
        // title
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(title, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_title, c);
        // description
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(description, c);
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_description, c);
        // venue
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(venue, c);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_venue, c);
        // speakers
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(speakers, c);
        c.gridy = 3;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_speakers, c);
        // host
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(host, c);
        c.gridy = 4;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_host, c);
        // org
        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(org, c);
        c.gridy = 5;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(text_org, c);
        // date
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(date, c);
        c.gridy = 6;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(event_date, c);

        // submit button
        c.gridy = 7;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        inputPanel.add(submit, c);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            WorkshopDb workshop = new WorkshopDb(this.email, text_title.getText(), text_description.getText(),
                    event_date.getText(), text_venue.getText(), text_speakers.getText(), text_host.getText(),
                    text_org.getText());
            Boolean validation = workshop.databaseInsert();
            if (validation) {
                JOptionPane.showMessageDialog(null, "New workshop created");
                System.out.println("Valid workshop info");
                this.dispose();
                new manageWorkshop(this.user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid workshop info",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Invalid workshop info");
                this.dispose();
                new CreateWorkshop(this.user);
            }
        } else if (e.getSource() == backButton) {
            System.out.println("Go Back");
            this.dispose();
            new Homepage(this.user);
        }
    }

}
