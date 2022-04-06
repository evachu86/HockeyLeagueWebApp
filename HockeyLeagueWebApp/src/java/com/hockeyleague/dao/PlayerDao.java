package com.hockeyleague.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hockeyleague.model.Player;

public class PlayerDao {
	
	private static DBManager dbManager = new DBManager();
	
	public ArrayList<Player> query(Player player) {

		ArrayList<Player> players = null;
		ArrayList<Object> params = new ArrayList<>();

		try {
			// build SQL
			StringBuilder sql = new StringBuilder("SELECT * FROM DBHOCKEYLEAGUE.Player "
					+ "WHERE 1=1 ");
			
			if (player.getTeamId() != null) {
				sql.append("AND teamID = ? ");
				params.add(player.getTeamId());
			}
			
			if (player.getId() != null) {
				sql.append("AND playerID = ? ");
				params.add(player.getId());
			}
			sql.append(";");
			
			ResultSet rs = dbManager.query(sql.toString(), params);
			players = playerMapper(rs);
		} catch (SQLException e) {
			System.out.println("PlayerDao.query failed!!");
			e.printStackTrace();
		}
		
		return players;
	}
	
	public int addPlayer(Player player) {

		
		String sql = "INSERT INTO DBHOCKEYLEAGUE.player "
				+ "SELECT MAX(playerID)+1, ?, ?, ?, ?, ? FROM DBHOCKEYLEAGUE.player;";
		int result = dbManager.update(sql, extractParams(player));
		
		return result;
	}
	
	public int updatePlayer(Player player) {
		
		String sql = "UPDATE DBHOCKEYLEAGUE.player SET "
				+ "playerName = ?, "
				+ "playerAddress = ?, "
				+ "teamID = ?, "
				+ "playerRole = ?, "
				+ "playerActiveStatus = ?"
				+ "WHERE playerID = ?;";

		// Extract insert value from VO.
		ArrayList<Object> params = extractParams(player);
		params.add(player.getId());
		
		int result = dbManager.update(sql, params);
		
		
		return result;
	}
	
	public int deletePlayer(Player player) {
		
		String sql = "DELETE FROM DBHOCKEYLEAGUE.player WHERE playerID = ?;";

		// Extract insert value from VO.
		ArrayList<Object> params = new ArrayList<>();
		params.add(player.getId());
		
		int result = dbManager.update(sql, params);
		
		return result;
	}
	
	
	private ArrayList<Object> extractParams(Player player) {
		
		ArrayList<Object> params = new ArrayList<>();
		params.add(player.getName());
		params.add(player.getAddress());
		params.add(player.getTeamId());
		params.add(player.getRole());
		params.add( (player.isActive())?"T":"F" );	
		
		return params;
	}	
	
	private ArrayList<Player> playerMapper(ResultSet rs) 
									throws SQLException {
		
		ArrayList<Player> Players = new ArrayList<Player>();
		while(rs.next()) {
			Player player = new Player();
			player.setId(rs.getInt("playerID"));
			player.setName(rs.getString("playerName"));
			player.setAddress(rs.getString("playerAddress"));
			player.setTeamId(rs.getInt("teamID"));
			player.setRole(rs.getString("playerRole"));
			player.setActive(
					rs.getString("playerActiveStatus").equals("T"));

			Players.add(player);
		}
		
		return Players;
	}
}
