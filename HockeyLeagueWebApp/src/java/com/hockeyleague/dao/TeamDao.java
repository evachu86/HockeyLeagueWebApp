package com.hockeyleague.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hockeyleague.model.Team;

public class TeamDao {
	
	private static DBManager dbManager = new DBManager();
	
	public ArrayList<Team> query(Team team) {

		ArrayList<Team> teams = null;
		ArrayList<Object> params = new ArrayList<>();

		try {
			// build SQL
			StringBuilder sql = new StringBuilder("SELECT * FROM DBHOCKEYLEAGUE.team "
					+ "WHERE 1=1 ");
			if (team != null) {
				sql.append("AND teamID = ? ");
				params.add(team.getId());
			}
			
			ResultSet rs = dbManager.query(sql.toString(), params);
			teams = teamMapper(rs);
		} catch (SQLException e) {
			System.out.println("TeamDao.query failed!!");
			e.printStackTrace();
		}
		
		return teams;
	}
	
	private ArrayList<Team> teamMapper(ResultSet rs) 
									throws SQLException {
		
		ArrayList<Team> teams = new ArrayList<Team>();
		while(rs.next()) {
			Team team = new Team();
			team.setId(rs.getInt("teamID"));
			team.setName(rs.getString("teamName"));
			teams.add(team);
		}
		
		return teams;
	}
}
