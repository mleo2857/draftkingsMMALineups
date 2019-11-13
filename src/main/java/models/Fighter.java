package models;

public class Fighter {
	
	private int fighter_id;
	private String firstName;
	private String lastName;
	private int salary;
	private double avgPoints;
	private Fighter opponent;


	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	


	public int getFighter_id() {
		return fighter_id;
	}



	public void setFighter_id(int fighter_id) {
		this.fighter_id = fighter_id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}



	public double getAvgPoints() {
		return avgPoints;
	}



	public void setAvgPoints(double avgPoints) {
		this.avgPoints = avgPoints;
	}



	public Fighter getOpponent() {
		return opponent;
	}



	public void setOpponent(Fighter opponent) {
		this.opponent = opponent;
	}

}
