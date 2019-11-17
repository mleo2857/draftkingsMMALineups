package JDBC;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import DAO.Event_FighterDAO;
import DAO.LineupDAO;
import models.Event_Fighter;
import models.Lineup;

public class JDBCLineupDAO implements LineupDAO {
	
	private JdbcTemplate jdbcTemplate;
	private Event_FighterDAO eventFighterDAO;
	
	public JDBCLineupDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.eventFighterDAO = new JDBCEventFighterDAO(dataSource);
	}


	public void addLineupToDatabase(int eventId, ArrayList<Integer> fighterIds) {
		String sqlCommand = "INSERT INTO lineup(event_id, fighter_1_id,fighter_2_id,fighter_3_id,fighter_4_id,"
				+ "fighter_5_id,fighter_6_id,average_points_for,total_points_for,average_salary,total_salary,"
				+ "total_score)VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		double total_points_for = 0;
		double total_salary = 0;
		double total_score = 0;
		
		for (Integer fighterId : fighterIds) {
			Event_Fighter eventFighter = eventFighterDAO.getEventFighterById(eventId, fighterId);
			total_points_for += eventFighter.getAverage_points();
			total_salary += eventFighter.getSalary();
			total_score += eventFighter.getPoints_scored();
		}
		
		double average_points_for = total_points_for/6.0;
		double average_salary = total_salary/6.0;
		
		jdbcTemplate.update(sqlCommand,eventId, fighterIds.get(0),fighterIds.get(1),fighterIds.get(2),
				fighterIds.get(3),fighterIds.get(4),fighterIds.get(5),average_points_for,total_points_for,
				average_salary,total_salary,total_score);
		
	}


	

}
