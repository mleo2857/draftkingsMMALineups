package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.sql.DataSource;

import DAO.FighterDAO;
import DAO.LineupDAO;
import JDBC.JDBCFighterDAO;
import JDBC.JDBCLineupDAO;
import models.Fighter;

public class Event {
	
	private int event_id;
	private String event_name;
	private LocalDate event_date;
	private ArrayList<Fighter> fighterList;
	private ArrayList<ArrayList<Fighter>> possibleLineups;
	

	public Event(ArrayList<Fighter> eventFighters, String event_name, LocalDate event_date) {
		this.fighterList = eventFighters;
		assignOpponents();
		this.possibleLineups = this.determinePossibleLineups();
	}
	
	public ArrayList<Fighter> getFighterList() {
		return this.fighterList;
	}
	
	public void displayFighters() {
		String format = "%1$-30s%2$-20s%3$-20s%4$-20s\n";
		System.out.format(format, "Fighter Name", "Salary", "Average Points","Opponent");
		
		for (Fighter fighter : fighterList) {
			System.out.format(format, fighter.getName(),fighter.getSalary(),fighter.getAvgPoints(),fighter.getOpponent());
		}
		System.out.println();
	}
	
	public void assignOpponents() {
		for (int i = 0; i < fighterList.size(); i++) {
			fighterList.get(i).setOpponent(fighterList.get(fighterList.size()-1-i));
		}
	}
	
	public ArrayList<ArrayList<Fighter>> determinePossibleLineups() {
		ArrayList<ArrayList<Fighter>> possibleLineups = new ArrayList<ArrayList<Fighter>>();
		
		for (int fighter1 = 0; fighter1 < fighterList.size()-5; fighter1++) {
			for (int fighter2 = fighter1 + 1; fighter2 < fighterList.size()-4; fighter2++) {
				for (int fighter3 = fighter2 + 1; fighter3 < fighterList.size()-3; fighter3++) {
					for (int fighter4 = fighter3 + 1; fighter4 < fighterList.size()-2; fighter4++) {
						for (int fighter5 = fighter4 + 1; fighter5 < fighterList.size()-1; fighter5++) {
							for (int fighter6 = fighter5 + 1; fighter6 < fighterList.size(); fighter6++) {
								ArrayList<Fighter> lineup = new ArrayList<Fighter>();
								int salary = 0;
								lineup.add(fighterList.get(fighter1));
								salary += fighterList.get(fighter1).getSalary();
								lineup.add(fighterList.get(fighter2));
								salary += fighterList.get(fighter2).getSalary();
								lineup.add(fighterList.get(fighter3));
								salary += fighterList.get(fighter3).getSalary();
								lineup.add(fighterList.get(fighter4));
								salary += fighterList.get(fighter4).getSalary();
								lineup.add(fighterList.get(fighter5));
								salary += fighterList.get(fighter5).getSalary();
								lineup.add(fighterList.get(fighter6));
								salary += fighterList.get(fighter6).getSalary();
								boolean areOpponents = false;
								for (Fighter aFighter : lineup) {
									for (int i = 0; i < lineup.size();i++) {
										if (aFighter.getOpponent().equals(lineup.get(i).getName())) {
											areOpponents = true;
										}
									}
								}
								if (salary <= 50000 && !areOpponents) {
									possibleLineups.add(lineup);
									Lineup aLineup = new Lineup(lineup);
								}
							}
						}
					}
				}
			}
		}
		
		
		return possibleLineups;
		
	}
	

	
	public ArrayList<ArrayList<Fighter>> getPossibleLineups() {
		return possibleLineups;
	}
	
	public ArrayList<ArrayList<Fighter>> filter(ArrayList<String> selectedFighters) {
		ArrayList<ArrayList<Fighter>> returnList = new ArrayList<ArrayList<Fighter>>();
		if (selectedFighters.size() == 0) {
			return this.possibleLineups;
		}
		for (ArrayList<Fighter> fighterList : this.possibleLineups) {
			List<String> fighterNames = new ArrayList<String>();
			for (Fighter fighter : fighterList) {
				fighterNames.add(fighter.getName());
			}
			boolean addLineup = true;
			for (int i = 0; i < selectedFighters.size(); i++) {
				if (!fighterNames.contains(selectedFighters.get(i))) {
					addLineup = false;
				}
			}
			
			if (addLineup) {
				returnList.add(fighterList);
			}
		}
		
		return returnList;
		
	}
	
}
