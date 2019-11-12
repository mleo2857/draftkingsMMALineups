package DAO;

import models.Fighter;

public interface FighterDAO {
	
	public Fighter getFigtherByID(int fighter_id);
	
	public void addFighterToDatabase(Fighter fighter);

}
