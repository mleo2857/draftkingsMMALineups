package DAO;

import java.time.LocalDate;

public interface EventDAO {
	
	public boolean eventAlreadyInDatabase(String eventName);
	
	public void saveEvent(String eventName, LocalDate eventDate);

}
