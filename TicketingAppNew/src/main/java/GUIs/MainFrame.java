package GUIs;
import java.awt.CardLayout;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JFrame;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
	// List of all screens we'll have (so far only for the User)
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    EventViewerPanel eventviewerPanel;
    TransferTicketPanel transferPanel; // we can also call it EventPastPanel

    TicketPurchasePanel purchasePanel;
    AccountInfoPanel userinfoPanel;
    
    AddEventPanel addeventPanel;
    
    private static CardLayout Maincard;
    private static Container content;

    public MainFrame() throws IOException {

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
        
        eventviewerPanel = new EventViewerPanel();
        content.add(eventviewerPanel, "eventview");
        
        purchasePanel = new TicketPurchasePanel();
        content.add(purchasePanel, "purchase");
        
        transferPanel = new TransferTicketPanel();
        content.add(transferPanel, "transfer");
        
        addeventPanel = new AddEventPanel();
        content.add(addeventPanel, "addevent");
        
        
    }
    
    public static void swap(String name) {
    	Maincard.show(content, name);
    }
    
    
    	
   
}
