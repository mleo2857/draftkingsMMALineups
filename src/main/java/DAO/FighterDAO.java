package DAO;

import models.Fighter;

public interface FighterDAO {
	
	public boolean fighterAlreadyInDatabase(String firstName, String lastName);
	
	public void saveFighter(String firstName, String lastName);
	
	public int getFighterId(String firstName, String lastName);

}
