package models;

public class Event_Payout_Info {
	
	private int event_id;
	private int number_of_payouts;
	private double money_cutoff_points;
	private double top_score;
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getNumber_of_payouts() {
		return number_of_payouts;
	}
	public void setNumber_of_payouts(int number_of_payouts) {
		this.number_of_payouts = number_of_payouts;
	}
	public double getMoney_cutoff_points() {
		return money_cutoff_points;
	}
	public void setMoney_cutoff_points(double money_cutoff_points) {
		this.money_cutoff_points = money_cutoff_points;
	}
	public double getTop_score() {
		return top_score;
	}
	public void setTop_score(double top_score) {
		this.top_score = top_score;
	}
	
	

}
