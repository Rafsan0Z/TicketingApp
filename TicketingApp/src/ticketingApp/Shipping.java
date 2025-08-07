package ticketingApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Shipping implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String FILENAME_EXTENSION = ".ser";
	
	// SERIALIZATION METHODS
	
	/**
	 * 
	 * @param <T>
	 * @param path
	 * @param newT
	 * @throws FileNotFoundException
	 * This method slurps in a file of type T and creates an ArrayList<T>
	 */
	public static <T> void slurpFile(String path, Function<String[], T> newT) throws FileNotFoundException {
		ArrayList<T> spreadsheet = new ArrayList<T>();
		
		Scanner kb = new Scanner(new FileReader(path));
		kb.useDelimiter(",");
		kb.nextLine();
		while (kb.hasNext()) {
			String line = kb.nextLine();
			String[] objectInfo = line.split(",");
			spreadsheet.add(newT.apply(objectInfo));
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 * This method slurps in all necessary files for this project
	 */
	public static void slurpAll() throws IOException {
		slurpFile("src/Users.csv", RegisteredUser::new);
		slurpFile("src/Venues.csv", Venue::new);
		slurpFile("src/Events.csv", Event::new);
		slurpFile("src/Tickets.csv", Ticket::new);
	}
	
	
	
	/**
	 * This method deserializes an ArrayList<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> void deserialization(Serializables s) {
		String fileName = s.fileName + FILENAME_EXTENSION;
		
		ArrayList<T> list = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			list.addAll((ArrayList<T>) ois.readObject());
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("worked i think");
	}
	
	/**
	 * 
	 * @param <T>
	 * @param variable
	 * @param s
	 * This method serializes an ArrayList<T>
	 */
	public static <T> void serialization(ArrayList<T> variable, Serializables s) {
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
