package models;

public class Event_Fighter {
	
	private int event_id;
	private int fighter_id;
	private int salary;
	private double average_points;
	private double points_scored;
	
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getFighter_id() {
		return fighter_id;
	}
	public void setFighter_id(int fighter_id) {
		this.fighter_id = fighter_id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getAverage_points() {
		return average_points;
	}
	public void setAverage_points(double average_points) {
		this.average_points = average_points;
	}

	public double getPoints_scored() {
		return points_scored;
	}
	public void setPoints_scored(double points_scored) {
		this.points_scored = points_scored;
	}
	
	

}
