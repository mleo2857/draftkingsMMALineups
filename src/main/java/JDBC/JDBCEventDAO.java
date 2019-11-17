package JDBC;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAO.EventDAO;


public class JDBCEventDAO implements EventDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCEventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean eventAlreadyInDatabase(String eventName) {
		String sqlCommand = "SELECT * FROM event WHERE event_name = '" + eventName + "';";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCommand);
		
		if(result.next()) {
			return true;
		}
		
		return false;
	}
	
	public void saveEvent(String eventName, String eventDate, String eventLocation) {
		String sqlCommand = "INSERT INTO event(event_name, event_date, event_location)"
							+ " VALUES('" + eventName + "',DATE'" + eventDate + "','" + eventLocation + "');";
		jdbcTemplate.update(sqlCommand);
	}
	
	public int getEventId(String eventName) {
		String sqlCommand = "SELECT * FROM event WHERE event_name = '" + eventName + "'";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCommand);
		result.next();
		
		return result.getInt("event_id");
	}
	

}
