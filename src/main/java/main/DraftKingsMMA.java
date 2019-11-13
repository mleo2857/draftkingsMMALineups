package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import DAO.EventDAO;
import DAO.FighterDAO;
import DAO.LineupDAO;
import JDBC.JDBCEventDAO;
import JDBC.JDBCFighterDAO;
import JDBC.JDBCLineupDAO;
import models.Fighter;
import models.Event;

public class DraftKingsMMA {

	

	public static void main(String[] args) throws FileNotFoundException {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/draftkingsMMA");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		FighterDAO fighterDAO = new JDBCFighterDAO(dataSource);
		LineupDAO lineupDAO = new JDBCLineupDAO(dataSource);
		EventDAO eventDAO = new JDBCEventDAO(dataSource);
		
		File dkSalaryInfo = new File(System.getProperty("user.dir"), "DKSalaries.csv");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Please enter the title of this event");
		String eventName = userInput.nextLine();
		
		if (!eventDAO.eventAlreadyInDatabase(eventName)) {
			Scanner fighterScanner = new Scanner(dkSalaryInfo);
			fighterScanner.nextLine();
			LocalDate eventDate = null;
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
				double avgPoints = Double.parseDouble(fighterInfo[8]);
				eventDate = LocalDate.parse(fighterInfo[6].split(" ")[1]);
				fighterDAO.saveFighter(firstName, lastName, salary, avgPoints);
			}
			eventDAO.saveEvent(eventName, eventDate);
			
		} else {
		
		
		}
		

//		ArrayList<String> selectedFighters = new ArrayList<String>();
//		
//		System.out.println(dkMMA.getPossibleLineups().size());
//		System.out.println(dkMMA.getLineupsWithTotalSalary().size());
//		
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
	
	}

}
