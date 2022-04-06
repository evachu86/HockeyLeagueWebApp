/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 2 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: EditPlayerController.java
 * Other Files in this Project: 
 * 	TeamController.java
 *  PlayersEditPlayerController.java
 *  del.jsp
 *  detail.jsp
 *  index.jsp
 *  list.jsp
 * 
 * Date: Nov 18, 2021
 * 
 * Description: 
 * Controller to handle request relative to show detail of a player, 
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
import com.hockeyleague.model.Role;
import com.hockeyleague.model.Team;

/**
 * The Class EditPlayerController.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
@WebServlet("/EditPlayer")
public class EditPlayerController extends HttpServlet {
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
		 * */
		String editBtn = request.getParameter("editBtn");
		editBtn = (editBtn == null)? "index" : editBtn; 
				
		// According to pushed button, decide how to deal data.
		String showPage = null;
		switch (editBtn) {
			case "update": queryById(request); 
			case "Add Player": setDtl(request); 
				showPage = "detail.jsp";
				break;
			case "delete": queryById(request);
				showPage = "del.jsp";
				break;
			case "Cancel": 
				showPage = "index";
				break;
			default:
				showPage = editBtn;
		}
		
		request.getRequestDispatcher(showPage)
			.forward(request, response);
	}

    private void queryById(HttpServletRequest request) {
		
    	String playerId = request.getParameter("id");
    	
    	if(playerId != null && !playerId.isEmpty()) {
    		Integer id = Integer.parseInt(playerId);
    		
    		Player playerVO = new Player();
    		playerVO.setId(id);
    		
    		ArrayList<Player> players = playerDao.query(playerVO);
    		if(playerId != null && !playerId.isEmpty()) {
    			Player player = players.get(0);
    			request.setAttribute("player", player);
    		}
    	}
	}
    
    private void setDtl(HttpServletRequest request) {
		
    	String teamStr = request.getParameter("team");
    	Integer team = null;
    	
    	if(teamStr == null) {
    		Player player = (Player)request.getAttribute("player");
    		team = player.getTeamId();
    	} else {
    		team = Integer.parseInt(teamStr);
    	}
    	
    	// read from Team Table
    	ArrayList<Team> teams = teamDao.query(null);
		request.setAttribute("teams", teams);
		
		// Read from Enum Role
		request.setAttribute("roles", Role.values());
    	
    	request.setAttribute("team", team);
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
