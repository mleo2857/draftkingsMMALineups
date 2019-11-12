package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;


import models.Fighter;
import models.Event;

public class DraftKingsMMA {
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/draftkingsMMA");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		File dkSalaryInfo = new File(System.getProperty("user.dir"), "DKSalaries.csv");
		Event dkMMA = new Event(dkSalaryInfo, dataSource);
		
	
		dkMMA.displayFighters();
		System.out.println();
		System.out.println("Number of possible lineups: " + dkMMA.getPossibleLineups().size());
		
		Scanner userInput = new Scanner(System.in);
		
//		System.out.println("How many lineups do you need?");
//		String numString = userInput.nextLine();
//		int numberOfLineups = Integer.parseInt(numString);
		
//		System.out.println("Optimize for Highest (A)verage or Highest (S)alary?");
//		String averageOrSalary = userInput.nextLine().toLowerCase();
		
		dkMMA.displayFighters();
		
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
//		
//		
//		System.out.println(selectedFighters);
//		ArrayList<ArrayList<Fighter>> filteredLineups = dkMMA.filter(selectedFighters);
//		
//		
//		
//		
//		if (filteredLineups.size() == 0) {
//			System.out.println("No lineups match your search");
//		} else {
//
//			System.out.println("Number of possible lineups: " + dkMMA.getPossibleLineups().size());
//			System.out.println("Number of filtered lineups: " + filteredLineups.size());
//		}
		
		
		
		
		
		
	
		
		
		
		

	
	}

}
