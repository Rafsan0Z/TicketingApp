package ticketingApp;

import java.awt.EventQueue;

import ticketingApp.shipping.Shipping;

public class TicketingApp {

	public static void main(String[] args) throws Exception {
    	// We might wanna change this and outsource it to another class / method 
        // which initializes everything!
        Shipping.slurpAll();

    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
