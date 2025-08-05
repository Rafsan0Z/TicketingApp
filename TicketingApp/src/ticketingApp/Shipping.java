package ticketingApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Shipping implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String FILENAME_EXTENSION = ".ser";
	
	// SERIALIZATION METHODS
	
	
	// SLURPING
	
	/**
	 * This method will imports Venue file
	 * @throws IOException
	 */
	public static void slurpVenuesFile() throws IOException{
		ArrayList<Venue> venueSpreadsheet = new ArrayList<>();
		
		Scanner kb = new Scanner(new FileReader("src/Venues.csv"));
		kb.useDelimiter(",");
		kb.nextLine();
		while (kb.hasNext()) {
			String line = kb.nextLine();
			String[] venueInfo = line.split(",");
			venueSpreadsheet.add(new Venue(venueInfo));
		}
	}
	/**
	 * This method will imports the Events file
	 * @throws IOException
	 */
	public static void slurpEventsFile() throws IOException{
		ArrayList<Event> eventsSpreadsheet = new ArrayList<>();
		
		Scanner kb = new Scanner(new FileReader("src/Events.csv"));
		kb.useDelimiter(",");
		kb.nextLine();
		while (kb.hasNext()) {
			String line = kb.nextLine();
			String[] eventInfo = line.split(",");
			eventsSpreadsheet.add(new Event(eventInfo));
		}
	}
	
	/**
	 * This method will imports the Tickets file
	 * @throws IOException
	 */
	public static void slurpTicketsFile() throws IOException{
		ArrayList<Ticket> ticketsSpreadsheet = new ArrayList<>();
		
		Scanner kb = new Scanner(new FileReader("src/Tickets.csv"));
		kb.useDelimiter(",");
		kb.nextLine();
		while (kb.hasNext()) {
			String line = kb.nextLine();
			String[] ticketInfo = line.split(",");
			ticketsSpreadsheet.add(new Ticket(ticketInfo));
		}
	}
	
	/**
	 * This method will imports the Users file
	 * @throws IOException
	 */
	public static void slurpUsersFile() throws IOException{
		ArrayList<User> usersSpreadsheet = new ArrayList<>();
		
		Scanner kb = new Scanner(new FileReader("src/Users.csv"));
		kb.useDelimiter(",");
		kb.nextLine();
		while (kb.hasNext()) {
			String line = kb.nextLine();
			String[] userInfo = line.split(",");
			usersSpreadsheet.add(new User(userInfo));
		}
	}
	
	public static void slurpAll() throws IOException {
		slurpVenuesFile();
		slurpEventsFile();
		slurpTicketsFile();
		slurpUsersFile();
	}
	
	
	// DESERIALIZING
	
	/**
	 * This method will deserialize a Venue ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static void venuesDeserialization() {
		String fileName = "venues.ser";
		
		ArrayList<Venue> venuesList = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			venuesList.addAll((ArrayList<Venue>) ois.readObject());
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will deserialize an Event ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static void eventsDeserialization() {
		String fileName = "events.ser";
		
		ArrayList<Event> eventsList = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			eventsList.addAll((ArrayList<Event>) ois.readObject());
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will deserialize a Ticket ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static void ticketsDeserialization() {
		String fileName = "tickets.ser";
		
		ArrayList<Ticket> ticketsList = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ticketsList.addAll((ArrayList<Ticket>) ois.readObject());
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will deserialize a User ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static void usersDeserialization() {
		String fileName = "user.ser";
		
		ArrayList<User> usersList = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			usersList.addAll((ArrayList<User>) ois.readObject());
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// SERIALIZING
	/**
	 * This method will serialize a student ArrayList
	 * @param variable
	 * @param s
	 */
	public static void venueSerialization(ArrayList<Venue> variable, Serializables s) {
		
		String fileName = s.fileName + FILENAME_EXTENSION;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(variable);
			
			oos.close();
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void eventSerialization(ArrayList<Event> variable, Serializables s) {
		
		String fileName = s.fileName + FILENAME_EXTENSION;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(variable);
			
			oos.close();
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ticketSerialization(ArrayList<Ticket> variable, Serializables s) {
		
		String fileName = s.fileName + FILENAME_EXTENSION;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(variable);
			
			oos.close();
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void studentSerialization(ArrayList<User> variable, Serializables s) {
		
		String fileName = s.fileName + FILENAME_EXTENSION;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(variable);
			
			oos.close();
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
