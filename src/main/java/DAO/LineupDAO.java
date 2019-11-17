package DAO;

import java.util.ArrayList;

import models.Lineup;

public interface LineupDAO {

	public void addLineupToDatabase(int eventId, ArrayList<Integer> fighterIds);
	
	

}
