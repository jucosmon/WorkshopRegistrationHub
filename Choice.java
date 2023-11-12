import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Choice extends JFrame implements ActionListener{
     JButton eventmanager, attendee;
     String choice;

    Choice(){

        
        eventmanager = new JButton("Event Manager");
        attendee = new JButton("Attendee");

        //eventmanager.setBounds(0, 0, 100, 50);
        eventmanager.setBounds(0,10,100,30);  
        attendee.setBounds(0, 60, 100, 30);
        eventmanager.addActionListener(this);
        attendee.addActionListener(this);

        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.add(eventmanager);
        this.add(attendee);

}



    @Override
     public void actionPerformed(ActionEvent e) {
        if(e.getSource()==attendee){
            System.out.println("attendee");
            choice = "Attendee";
            new Menu(choice);
            this.dispose();
        }
        else if(e.getSource()==eventmanager){
            System.out.println("eventmanager");
            choice = "EventManager";
            new Menu(choice);
            this.dispose();
        }

    }
}
