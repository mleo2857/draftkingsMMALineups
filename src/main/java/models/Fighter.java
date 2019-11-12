package models;

public class Fighter {
	
	private int fighter_id;
	private String firstName;
	private String lastName;
	private int salary;
	private double avgPoints;
	private Fighter opponent;
	
	public Fighter(String firstName, String lastName, int salary, double avgPoints) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.avgPoints = avgPoints;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getFighter_id() {
		return fighter_id;
	}

	public int getSalary() {
		return salary;
	}

	public double getAvgPoints() {
		return avgPoints;
	}

	public String getOpponent() {
		return opponent.getName();
	}

	public void setOpponent(Fighter opponent) {
		this.opponent = opponent;
	}

}
