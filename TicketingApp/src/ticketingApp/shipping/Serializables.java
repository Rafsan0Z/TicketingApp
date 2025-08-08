package ticketingApp.shipping;

/*
 * This is a special class that defines the constants that
 * will be serialized and used as file names
 */

public enum Serializables {
	
	// ITEMS THAT CAN BE SERIALIZED
	EVENTS("events"),
	TICKETS("tickets"),
	USERS("user"),
	VENUES("venues");
	
	// TODO: fix when packages are finalized
	public String fileName;
	
	/**
	 * This creates a predefined constant for the given string
	 * @param fn
	 */
	Serializables(String fn) {
		this.fileName = fn;
	}
}
