package models;

public class Fight {
	
	private int fight_id;
	private int event_id;
	private int winner_id;
	private int loser_id;
	private String method_of_victory;
	private int round;
	public int getFight_id() {
		return fight_id;
	}
	public void setFight_id(int fight_id) {
		this.fight_id = fight_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getWinner_id() {
		return winner_id;
	}
	public void setWinner_id(int winner_id) {
		this.winner_id = winner_id;
	}
	public int getLoser_id() {
		return loser_id;
	}
	public void setLoser_id(int loser_id) {
		this.loser_id = loser_id;
	}
	public String getMethod_of_victory() {
		return method_of_victory;
	}
	public void setMethod_of_victory(String method_of_victory) {
		this.method_of_victory = method_of_victory;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
	

}
