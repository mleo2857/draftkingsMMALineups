package DAO;

import java.util.ArrayList;

import models.Event_Fighter;

public interface Event_FighterDAO {
	
	public void addEventFighter(int eventId, int fighterId, int salary, double averagePoints,  double points_scored);

	public Event_Fighter getEventFighterById(int eventId,int fighterId);
	
	public void addEventFighterPointsScored(int eventId, int fighterId, double points_scored);
	
	public Event_Fighter[] getFightersForEvent(int eventId);
}
