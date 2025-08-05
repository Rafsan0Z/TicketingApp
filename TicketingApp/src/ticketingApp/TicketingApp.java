package ticketingApp;

import java.awt.EventQueue;

public class TicketingApp {
    public static void main(String[] args) throws Exception {
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
//    	new MainFrame();
    }
}
