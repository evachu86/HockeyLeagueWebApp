/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 2 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: PlayersController.java
 * Other Files in this Project: 
 * 	TeamController.java
 *  EditPlayerController.java
 *  del.jsp
 *  detail.jsp
 *  index.jsp
 *  list.jsp
 * 
 * Date: Nov 18, 2021
 * 
 * Description: 
 * Controller to handle request relative to list of player, 
 * and the I/O to player table.
 *  
 */
package com.hockeyleague.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hockeyleague.dao.PlayerDao;
import com.hockeyleague.dao.TeamDao;
import com.hockeyleague.model.Player;
import com.hockeyleague.model.Team;

/**
 * The Class PlayersController.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
@WebServlet("/Players")
public class PlayersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PlayerDao playerDao = new PlayerDao();
	private static TeamDao teamDao = new TeamDao();
       
    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
    	/*
		 * Check how user enter the url.
		 * Null imply that user enter from the index.
		 */
		String actBtn = request.getParameter("actBtn");
		actBtn = (actBtn == null)? "index" : actBtn; 
				
		// According to pushed button, decide how to deal data.
		String showPage = null;
		switch (actBtn) {
			case "Save": save(request); 
				query(request);
				showPage = "list.jsp";
				break;
			case "Delete": delete(request); 
				query(request);
				showPage = "list.jsp";
				break;
			case "Cancel":
			case "View Team": query(request); 
				showPage = "list.jsp";
				break;
			default:
				showPage = actBtn;
		}
		
		request.getRequestDispatcher(showPage)
			.forward(request, response);
	}

    private void query(HttpServletRequest request) {
		
    	String team = request.getParameter("team");
    	
    	if(team != null) {
    		Integer teamId = Integer.parseInt(team);
    		
    		Player playerVO = new Player();
    		playerVO.setTeamId(teamId);
    		
    		// read from Team Table
    		Team teamVO = new Team();
    		teamVO.setId(teamId);
    		ArrayList<Team> teams = teamDao.query(teamVO);
    		if(teams.size() > 0) {
    			request.setAttribute("team", teams.get(0));
    		}
    		
    		ArrayList<Player> players = playerDao.query(playerVO);
    		request.setAttribute("players", players);
    	}
	}
    
    private void save(HttpServletRequest request) {
		
    	// retrieve data from request
    	String playerId = request.getParameter("id"),
    			name = request.getParameter("name"),
				address = request.getParameter("address"),
    			teamId = request.getParameter("teamId"),
    			role = request.getParameter("role"),
    			active = request.getParameter("active");
    	
    	// set to VO / model
    	Player playerVO = new Player();
		playerVO.setName(name==null?"":name);
		playerVO.setAddress(address==null?"":address);
		playerVO.setTeamId(Integer.parseInt(teamId));
		playerVO.setRole(role);
		playerVO.setActive(Boolean.parseBoolean(active));
    	
		// if player id exist, update the player data.
    	if(playerId != null && !playerId.isEmpty()) {
    		playerVO.setId(Integer.parseInt(playerId));
    		
    		// update to Table Player
    		playerDao.updatePlayer(playerVO);
    	} else { // if player not exist, add the player data.
    		
    		// add to Table Player
    		playerDao.addPlayer(playerVO);
    	}
	}
    
    private void delete(HttpServletRequest request) {
		
    	String playerId = request.getParameter("id");
    	
    	if(playerId != null && !playerId.isEmpty()) {
    		Player playerVO = new Player();
    		playerVO.setId(Integer.parseInt(playerId));
    		
    		// update to Table Player
    		playerDao.deletePlayer(playerVO);
    	}
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
