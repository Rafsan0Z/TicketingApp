package ticketingApp;
import java.awt.CardLayout;
import java.awt.Container;
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
    
    private static CardLayout Maincard;
    private static Container content;

    public MainFrame(){

        // main screen goes here
        setTitle("Ticketing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        Maincard = new CardLayout();
        content = getContentPane();
        content.setLayout(Maincard); // use CardLayout to switch between panels
        
                
        loginPanel = new LoginPanel();
        content.add(loginPanel, "login");
        
        registerPanel = new RegisterPanel();
        content.add(registerPanel, "register");
        
        userinfoPanel = new AccountInfoPanel();
        content.add(userinfoPanel, "userinfo");
        
    }
    
    public static void swap(String name) {
    	Maincard.show(content, name);
    }
    
    
    	
   
}
