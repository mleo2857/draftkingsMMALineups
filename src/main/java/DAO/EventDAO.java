package DAO;

import java.time.LocalDate;

public interface EventDAO {
	
	public boolean eventAlreadyInDatabase(String eventName);
	
	public void saveEvent(String eventName, String eventDate, String eventLocation);
	
	public int getEventId(String eventName);

}
