import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.awt.Font;

public class CreateWorkshop extends JFrame implements ActionListener{
            String email;
            JTextField  text_title, text_description, text_venue, text_speakers, text_host, text_org;
            JLabel ws_code, title, description, venue, speakers, host, org, date;
            JButton submit, back;
            JFormattedTextField event_date;

    public CreateWorkshop(String email){
        this.email = email;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
       
        text_title = new JTextField();
        text_description = new JTextField();
        text_venue = new JTextField();
        text_speakers = new JTextField();
        text_host = new JTextField();
        text_org = new JTextField();
        ws_code = new JLabel("Workshop Code");
        title = new JLabel("Workshop Title:");
        description = new JLabel("Workshop Description:");
        venue = new JLabel("Workshop Venue:");
        speakers = new JLabel("Workshop Speakers");
        host = new JLabel("Workshop Host:");
        org = new JLabel("Workshop Org:");
        submit = new JButton("Register");
        back = new JButton("Back");
        date = new JLabel("Event Date:");
        event_date = new JFormattedTextField(formatter);

       
        text_title.setBounds(211, 75, 200, 30);
        text_description.setBounds(211, 125, 200, 30);
        text_venue.setBounds(211, 175, 200, 30);
        text_speakers.setBounds(211, 225, 200, 30);
        text_host.setBounds(211, 275, 200, 30);
        text_org.setBounds(211, 325, 200, 30);
        event_date.setBounds(211, 375, 200, 30);


       
        title.setBounds(80, 75, 150, 30);
        title.setFont(new Font("Serif", Font.PLAIN, 20));

        description.setBounds(80, 125, 150, 30);
        description.setFont(new Font("Serif", Font.PLAIN, 20));

        venue.setBounds(80, 175, 150, 30);
        venue.setFont(new Font("Serif", Font.PLAIN, 20));

        speakers.setBounds(80, 225, 200, 30);
        speakers.setFont(new Font("Serif", Font.PLAIN, 20));

        host.setBounds(80, 275, 200, 30);
        host.setFont(new Font("Serif", Font.PLAIN, 20));

        org.setBounds(80, 325, 200, 30);
        org.setFont(new Font("Serif", Font.PLAIN, 20));

        date.setBounds(80, 375, 200, 30);
        date.setFont(new Font("Serif", Font.PLAIN, 20));
        
        submit.setBounds(260, 425, 200, 30);
        back.setBounds(0, 0, 200, 30);

        submit.addActionListener(this);


        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(text_venue);
        this.add(text_title);
        this.add(text_speakers);
        this.add(text_host);
        this.add(text_description);
        this.add(text_org);
        this.add(submit);
        this.add(event_date);
        this.add(submit);
        this.add(back);
        this.add(date);
        this.add(description);
        this.add(title);
        this.add(venue);
        this.add(speakers);
       
        this.add(org);
        this.add(host);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==submit){
       WorkshopDb workshop = new WorkshopDb(email, text_title.getText(), text_description.getText(), event_date.getText(), text_venue.getText(), text_speakers.getText(), text_host.getText(), text_org.getText());
       workshop.databaseInsert();
    }
    }

}
