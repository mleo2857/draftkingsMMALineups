package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Event {
	
	private ArrayList<Fighter> fighterList = new ArrayList<Fighter>();
	private ArrayList<ArrayList<Fighter>> possibleLineups = new ArrayList<ArrayList<Fighter>>();
	private Map<ArrayList<Fighter>, Double> lineupsWithAverages = new HashMap<ArrayList<Fighter>, Double>();
	private Map<ArrayList<Fighter>, Integer> lineupsWithTotalSalary = new HashMap<ArrayList<Fighter>, Integer>();
	
	

	public Event(File file) throws FileNotFoundException {
		importFighterInformation(file);
		assignOpponents();
		this.possibleLineups = this.determinePossibleLineups();
		this.createAveragesMap();
	}
	
	public void importFighterInformation(File file) throws FileNotFoundException {
		Scanner fighterScanner = new Scanner(file);
		fighterScanner.nextLine();
		
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
			Fighter aFighter = new Fighter(firstName,lastName,salary,avgPoints);
			this.fighterList.add(aFighter);
		}
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
									this.lineupsWithTotalSalary.put(lineup, salary);
								}
							}
						}
					}
				}
			}
		}
		
		
		return possibleLineups;
		
	}
	
	public void createAveragesMap() {
		for (int i = 0; i < this.possibleLineups.size(); i++) {
			double thisAverage = 0;
			for (Fighter aFighter : this.possibleLineups.get(i)) {
				thisAverage += aFighter.getAvgPoints();
			}
			lineupsWithAverages.put(this.possibleLineups.get(i), thisAverage);
		}
	}
	
//	public void getTopAverageScoreLineups(int numberOfLineups) {
//		TreeMap<Double, ArrayList> temp = this.lineupsWithAverages;
//		for (int i = 0; i < numberOfLineups; i++) {
//			String format = "%1$-30s%2$-20s%3$-20s\n";
//			System.out.format(format, "Fighter Name", "Salary","Opponent");
//			System.out.println();
//			double average = temp.lastKey();
//			ArrayList<Fighter> aFighterList = temp.remove(temp.lastKey());
//			for (Fighter aFighter : aFighterList) {
//				System.out.format(format, aFighter.getName(), aFighter.getSalary(),aFighter.getOpponent());
//			}
//			System.out.println();
//			System.out.print("Total Average: ");
//			System.out.format("%.2f", average);
//			System.out.println();
//		}
//		System.out.println();
//	}
	
//	public void getTopSalaryLineups(int numberOfLineups) {
//		TreeMap<Integer, ArrayList<Fighter>> temp = this.lineupsWithTotalSalary;
//		for (int i = 0; i < numberOfLineups; i++) {
//			String format = "%1$-30s%2$-20s%3$-20s\n";
//			System.out.format(format, "Fighter Name", "Salary","Opponent");
//			System.out.println();
//			int salary = temp.lastKey();
//			ArrayList<Fighter> aFighterList = temp.remove(temp.lastKey());
//			for (Fighter aFighter : aFighterList) {
//				System.out.format(format, aFighter.getName(), aFighter.getSalary(),aFighter.getOpponent());
//			}
//			System.out.println();
//			System.out.print("Total Salary: " + salary);
//			System.out.println();
//		}
//		System.out.println();
//	}

	
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
	
	
	

	public Map<ArrayList<Fighter>, Double> getLineupsWithAverages() {
		return lineupsWithAverages;
	}

	public Map<ArrayList<Fighter>, Integer> getLineupsWithTotalSalary() {
		return lineupsWithTotalSalary;
	}
}
