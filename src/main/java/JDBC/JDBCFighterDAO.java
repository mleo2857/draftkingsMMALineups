package JDBC;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAO.FighterDAO;
import models.Fighter;

public class JDBCFighterDAO implements FighterDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCFighterDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean fighterAlreadyInDatabase(String firstName, String lastName) {
		String sqlCommand = "SELECT * FROM fighter WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCommand);
		
		if(result.next()) {
			return true;
		}
		
		return false;
	}

	
	public void saveFighter(String firstName, String lastName) {
		String sqlCommand = "INSERT INTO fighter(first_name, last_name)VALUES(?,?);";
		jdbcTemplate.update(sqlCommand,firstName,lastName);
	}
	
	public int getFighterId(String firstName, String lastName) {
		String sqlCommand = "SELECT * FROM fighter WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCommand);
		result.next();
		return result.getInt("fighter_id");
	}
	
	private Fighter mapRowToFighter(SqlRowSet result) {
		Fighter fighter = new Fighter();
		fighter.setFighter_id(result.getInt("fighter_id"));
		fighter.setFirst_name(result.getString("first_name"));
		fighter.setLast_name(result.getString("last_name"));
		return fighter;
	}
	

}
