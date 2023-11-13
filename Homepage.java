import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{

    public Homepage(String choice){
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton createWorkshop = new JButton("Create Workshop");
            JButton manageWorkshop = new JButton("Manage Existing Workshop");
            JButton manageProfile = new JButton("Manage Profile");
            JButton upcomingEvents = new JButton("Upcoming Events");
            JButton registeredEvents = new JButton("My Registered Events");
            JButton exit = new JButton("Log out");


        if(choice == "EventManager"){
           /*  JButton createWorkshop = new JButton("Create Workshop");
            JButton manageWorkshop = new JButton("Manage Existing Workshop");
            JButton manageProfile = new JButton("Manage Profile");
            JButton exit = new JButton("Exit");*/

            createWorkshop.setBounds(211,100,200,30); 
            manageWorkshop.setBounds(211,150,200,30); 
            manageProfile.setBounds(211,200,200,30); 
            exit.setBounds(0, 0, 100,30);

            //action listener
            createWorkshop.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new CreateWorkshop();

                }

                });

            manageWorkshop.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new manageWorkshop();
                }  
                });

            manageProfile.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new ManageProfile();
                }  
                });

            exit.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new Choice();
                }  
                });


            this.add(createWorkshop);
            this.add(manageWorkshop);
            this.add(manageProfile);
            this.add(exit);


        }
        else if(choice =="Attendee"){
           /*  JButton upcomingEvents = new JButton("Upcoming Events");
            JButton registeredEvents = new JButton("My Registered Events");
            JButton manageProfile = new JButton("Manage Profile");
            JButton exit = new JButton("Exit");*/

            upcomingEvents.setBounds(211,100,200,30); 
            registeredEvents.setBounds(211,150,200,30); 
            manageProfile.setBounds(211,200,200,30); 
            exit.setBounds(0, 0, 100,30);

            //action listener
            upcomingEvents.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new UpcomingEvents();
                }  
                });

            registeredEvents.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new RegisteredEvents();
                }  
                });

            manageProfile.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new ManageProfile();
                }  
                });

            exit.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        dispose();
                        new Choice();
                }  
                });

            this.add(upcomingEvents);
            this.add(registeredEvents);
            this.add(manageProfile);
            this.add(exit);

        }
    }


 
    
}
