import javax.swing.*;

public class MainFrame extends JFrame {
    
    // List of all screens we'll have (so far only for the User)
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    EventBrowsePanel browsePanel;
    EventUpcomingPanel upcomingPanel;
    PastPurchasePanel previousPanel; // we can also call it EventPastPanel

    TicketPurchasePanel purchasePanel;
    AccountInfoPanel userinfoPanel;

    public MainFrame(){

        // main screen goes here
        setTitle("Ticketing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // center the window on the screen
        setLayout(new CardLayout()); // use CardLayout to switch between panels
    }


}
