package com.hockeyleague.dao;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

	private static Connection conn;
	
	protected static Connection getConnection() {
		
		if(conn == null) {
			try {
				Class.forName("org.h2.Driver");
				conn = DriverManager.getConnection(
						"jdbc:h2:tcp://localhost/~/dbhockeyleague", 
						"sa", 
						"");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("DB connection failed!!");
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public ResultSet query(String sql, ArrayList<?> params) {

		ResultSet rs = null;
		
		try {
			Connection conn = getConnection();

			if(conn != null) {
				PreparedStatement st = conn.prepareStatement(sql);
				setPreparedStatement(st, params);
				rs = st.executeQuery();
			}
			
		} catch (SQLException e) {
			System.out.println("DBManager.query failed:"+sql);
			e.printStackTrace();
		}
		
		return rs;		
	}
	
	public int update(String sql, ArrayList<?> params) {
		
		int updatedRow = 0;
		
		try {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement st = conn.prepareStatement(sql);
				setPreparedStatement(st, params);
				updatedRow = st.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("DBManager.update failed:"+sql);
			e.printStackTrace();
		}
		
		return updatedRow;
	}
	
	private PreparedStatement setPreparedStatement(
				PreparedStatement st, ArrayList<?> params
			) throws SQLException {
		
		if(params != null) {
			for(int i = 1; i <= params.size(); i++) {
				Object param = params.get(i-1);
				
				if(param instanceof String)
					st.setString(i, (String)param);
				if(param instanceof Integer)
					st.setInt(i, (int)param );
			}
		}
		
		return st;
	}
}
