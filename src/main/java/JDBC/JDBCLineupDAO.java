package JDBC;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import DAO.LineupDAO;
import models.Lineup;

public class JDBCLineupDAO implements LineupDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCLineupDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public void addLineupToDatabase(Lineup lineup) {
		String sqlCommand = "INSERT INTO lineup(fighter_1_name, fighter_2_name, fighter_3_name, fighter_4_name, fighter_5_name, fighter_6_name, total_points_for, total_salary)VALUES(?,?,?,?,?,?,?,?);";
		jdbcTemplate.update(sqlCommand, lineup.getFighters().get(0).getName(), lineup.getFighters().get(1).getName(), lineup.getFighters().get(2).getName(),
				lineup.getFighters().get(3).getName(), lineup.getFighters().get(4).getName(), lineup.getFighters().get(5).getName(), lineup.getAverage(),
				lineup.getTotalSalary());
	}
	

}
