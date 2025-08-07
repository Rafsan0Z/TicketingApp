package ticketingApp;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // List of all screens we'll have (so far only for the User)
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    EventBrowsePanel browsePanel;
    EventUpcomingPanel upcomingPanel;
    PastPurchasePanel previousPanel; // we can also call it EventPastPanel

    TicketPurchasePanel purchasePanel;
    AccountInfoPanel userinfoPanel;

    public MainFrame() throws IOException {

        // We might wanna change this and outsource it to another class / method 
        // which initializes everything!
            Shipping.slurpAll();

        // main screen goes here
        setTitle("Ticketing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new CardLayout()); // use CardLayout to switch between panels

        LoginPanel loginPanel = new LoginPanel();
        getContentPane().add(loginPanel, "LoginPanel");
    }
}
