package JDBC;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import DAO.FighterDAO;
import models.Fighter;

public class JDBCFighterDAO implements FighterDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCFighterDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public void addFighterToDatabase(Fighter fighter) {
		String sqlCommand = "INSERT INTO fighter(first_name, last_name, salary, average_points)VALUES(?,?,?,?);";
		jdbcTemplate.update(sqlCommand,fighter.getFirstName(),fighter.getLastName(),fighter.getSalary(),fighter.getAvgPoints());
		
	}
	

}
