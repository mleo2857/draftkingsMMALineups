package models;

import java.util.ArrayList;

public class Lineup {
	
	private int lineup_id;
	private ArrayList<Fighter> fighters;
	private double average;
	private int totalSalary;
	
	public Lineup(ArrayList<Fighter> fighters) {
		this.fighters = fighters;
		this.average = calculateAverage();
		this.totalSalary = calculateTotalSalary();
	}
	
	private double calculateAverage() {
		double average = 0;
		for (Fighter fighter : this.fighters) {
			average += fighter.getAvgPoints();
		}
		return average;
	}
	
	private int calculateTotalSalary() {
		int salary = 0;
		for (Fighter fighter : this.fighters) {
			salary += fighter.getSalary();
		}
		return salary;
	}
	
	
	
	public int getLineup_id() {
		return lineup_id;
	}

	public ArrayList<Fighter> getFighters() {
		return fighters;
	}
	public double getAverage() {
		return average;
	}
	public int getTotalSalary() {
		return totalSalary;
	}

}
