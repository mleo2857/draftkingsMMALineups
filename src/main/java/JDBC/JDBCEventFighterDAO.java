package JDBC;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAO.Event_FighterDAO;
import DAO.Event_FighterDAO;
import models.Event_Fighter;

public class JDBCEventFighterDAO implements Event_FighterDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCEventFighterDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addEventFighter(int eventId, int fighterId, int salary, double averagePoints, double pointsScored) {
		String sqlCommand = "INSERT INTO event_fighter(event_id, fighter_id, salary, average_points, points_scored)VALUES(?,?,?,?,?);";
		jdbcTemplate.update(sqlCommand,eventId,fighterId,salary,averagePoints,pointsScored);
	}

	public Event_Fighter getEventFighterById(int eventId ,int fighterId) {
		String sqlCommand = "SELECT * FROM event_fighter WHERE event_id = " + eventId + " AND fighter_id = " + fighterId + ";";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCommand);
		Event_Fighter eventFighter = new Event_Fighter();
		if (result.next()) {
			eventFighter = mapRowToEventFighter(result);
		}
		return eventFighter;
	}
	
	private Event_Fighter mapRowToEventFighter(SqlRowSet result) {
		Event_Fighter eventFighter = new Event_Fighter();
		eventFighter.setAverage_points(result.getDouble("average_points"));
		eventFighter.setEvent_id(result.getInt("event_id"));
		eventFighter.setFighter_id(result.getInt("fighter_id"));
		eventFighter.setPoints_scored(result.getDouble("points_scored"));
		eventFighter.setSalary(result.getInt("salary"));
		return eventFighter;
	}

	public void addEventFighterPointsScored(int eventId, int fighterId, double points_scored) {
		String sqlCommand = "UPDATE event_fighter "
				  			+ "SET points_scored = " + points_scored
				  			+ " WHERE event_id = " + eventId
				  			+ " AND fighter_id = " + fighterId + ";";
		jdbcTemplate.update(sqlCommand);
	}

	public Event_Fighter[] getFightersForEvent(int eventId) {
		String sqlCommand1 = "SELECT * FROM event_fighter WHERE event_id = " + eventId + ";";
		SqlRowSet result1 = jdbcTemplate.queryForRowSet(sqlCommand1);
		String sqlCommand2 = "SELECT count(*) FROM event_fighter WHERE event_id = " + eventId + ";";
		SqlRowSet result2 = jdbcTemplate.queryForRowSet(sqlCommand2);
		result2.next();
		Event_Fighter[] eventFighterArray = new Event_Fighter[result2.getInt("count")];
		int i = 0;
		while(result1.next()) {
			Event_Fighter eventFighter = mapRowToEventFighter(result1);
			eventFighterArray[i] = eventFighter;
			i++;
		}
		return eventFighterArray;
	}

}
