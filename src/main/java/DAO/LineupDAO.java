package DAO;

import models.Lineup;

public interface LineupDAO {
	
	public Lineup getLineupById(int lineup_id);
	
	public void addLineupToDatabase(Lineup lineup);

}
