package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import DAO.EventDAO;
import DAO.Event_FighterDAO;
import DAO.FighterDAO;
import DAO.LineupDAO;
import JDBC.JDBCEventDAO;
import JDBC.JDBCEventFighterDAO;
import JDBC.JDBCFighterDAO;
import JDBC.JDBCLineupDAO;
import models.Fighter;
import models.Lineup;
import models.Event;
import models.Event_Fighter;

public class DraftKingsMMA {

	public static void main(String[] args) throws FileNotFoundException {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/draftkingsMMA");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		FighterDAO fighterDAO = new JDBCFighterDAO(dataSource);
		LineupDAO lineupDAO = new JDBCLineupDAO(dataSource);
		EventDAO eventDAO = new JDBCEventDAO(dataSource);
		Event_FighterDAO eventFighterDAO = new JDBCEventFighterDAO(dataSource);
		
		File dkSalaryInfo = new File(System.getProperty("user.dir"), "DKSalaries.csv");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Please enter the title of this event");
		String eventName = userInput.nextLine();
		System.out.println("What is the event location?");
		String eventLocation = userInput.nextLine();
		
		if (!eventDAO.eventAlreadyInDatabase(eventName)) {
			Scanner fighterScanner = new Scanner(dkSalaryInfo);
			fighterScanner.nextLine();
			String eventDate = null;
			int eventId = 0;
			while(fighterScanner.hasNextLine()) {
				String fighter = fighterScanner.nextLine();
				String[] fighterInfo = fighter.split(",");
				String[] fighterName = fighterInfo[2].split(" ");
				String firstName = fighterName[0];
				String lastName = fighterName[fighterName.length-1];
				String fighter1 = fighterInfo[6].split(" ")[0].split("@")[0];
				String fighter2 = fighterInfo[6].split(" ")[0].split("@")[1];
				if (!lastName.equals(fighter1) && !lastName.equals(fighter2)) {
					continue;
				}
				int salary = Integer.parseInt(fighterInfo[5]);
				double averagePoints = Double.parseDouble(fighterInfo[8]);
				eventDate = fighterInfo[6].split(" ")[1];
				if (!fighterDAO.fighterAlreadyInDatabase(firstName, lastName)) {
					fighterDAO.saveFighter(firstName, lastName);
				}
				if (!eventDAO.eventAlreadyInDatabase(eventName)) {
					eventDAO.saveEvent(eventName, eventDate, eventLocation);
				}
				eventId = eventDAO.getEventId(eventName);
				int fighterId = fighterDAO.getFighterId(firstName, lastName);
				eventFighterDAO.addEventFighter(eventId, fighterId, salary, averagePoints, 0);
			}
			
			File dkFighterPointInfo = new File(System.getProperty("user.dir"), "fighter_points.csv");	
			Scanner fighterPointsScanner = new Scanner(dkFighterPointInfo);
			
			while(fighterPointsScanner.hasNextLine()) {
				String fighterPoints = fighterPointsScanner.nextLine();
				String[] fighterInfo = fighterPoints.split(",");
				String[] fighterName = fighterInfo[0].split(" ");
				String firstName = fighterName[0];
				String lastName = fighterName[fighterName.length-1];
				double points = Double.parseDouble(fighterInfo[3]);
				int fighterId = fighterDAO.getFighterId(firstName, lastName);
				eventFighterDAO.addEventFighterPointsScored(eventId, fighterId, points);
			}
			
			Event_Fighter[] eventFighterArray = eventFighterDAO.getFightersForEvent(eventId);
			

			ArrayList<ArrayList<Integer>> possibleLineups = getPossibleLineups(eventFighterArray, eventId);
			System.out.println(possibleLineups.size());
			
			for (ArrayList<Integer> fighterIds : possibleLineups) {
				lineupDAO.addLineupToDatabase(eventId, fighterIds);
			}
			
		}

	}
		


//		while (true) {
//			System.out.println("What fighters do you want in your lineup?");
//			String input = userInput.nextLine();
//			ArrayList<String> fighterNames = new ArrayList<String>();
//			for (Fighter fighter: dkMMA.getFighterList()) {
//				fighterNames.add(fighter.getName());
//			}
//			
//			if (input.equals("")) {
//				break;
//			} else if (!fighterNames.contains(input)) {
//				System.out.println("Please pick a fighter from the list");
//				continue;
//			}
//			
//			selectedFighters.add(input);
//			if (selectedFighters.size() == 6) {
//				break;
//			}
//			
//		}
	

	
	public static ArrayList<ArrayList<Integer>> getPossibleLineups(Event_Fighter[] eventFighterArray, int eventId) {
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		
		int N = eventFighterArray.length;
		
		int pointers[] = new int[6];
		
		int r = 0; // index for combination array
		int i = 0; // index for elements array
		
		while(r >= 0){
			
			// forward step if i < (N + (r-K))
			if(i <= (N + (r - 6))){
				pointers[r] = i;
					
				// if combination array is full print and increment i;
				if(r == 5){
					ArrayList<Integer> fighterIds = new ArrayList<Integer>();
					int salary = 0;
					for (Integer index : pointers) {
						fighterIds.add(eventFighterArray[index].getFighter_id());
						salary += eventFighterArray[index].getSalary();
					}
			    	
			    	if (salary <= 50000) {
			    		combinations.add(fighterIds);
			    	}
					
					i++;				
				}
				else{
					// if combination is not full yet, select next element
					i = pointers[r]+1;
					r++;										
				}				
			}
			
			// backward step
			else{
				r--;
				if(r >= 0)
					i = pointers[r]+1;
				
			}			
		}
    	
		return combinations;
	}
}


