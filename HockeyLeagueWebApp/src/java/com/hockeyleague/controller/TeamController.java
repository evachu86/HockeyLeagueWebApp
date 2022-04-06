/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 2 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: TeamController.java
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
 * Controller to handle request show index,
 * involving query the list of teams. 
 * 
 */
package com.hockeyleague.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class TeamController.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
@WebServlet(urlPatterns={"/index"})
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		
		request.getRequestDispatcher("index.jsp")
			.forward(request, response);
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
