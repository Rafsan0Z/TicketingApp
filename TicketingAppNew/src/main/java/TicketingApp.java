import java.awt.EventQueue;

import GUIs.MainFrame;
import data.DataStore;

public class TicketingApp {

	public static void main(String[] args) throws Exception {
    	// We might wanna change this and outsource it to another class / method 
        // which initializes everything!
//        Shipping.slurpAll();

        var dataStore = new DataStore();

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
