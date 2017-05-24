package com.kadylo.janus;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class UserHandler extends HttpServlet{
	private static volatile String instruction = null;
	private static volatile String Answer = null;
	
	/* public String getAnswer(){
		
		return Answer;
	}
	
	public setInstruction(String instruction){
		this.instruction = instruction;
	} */
	
	
	public static void main (String[] args){
		System.out.println("UserHandler started");
	}
	
	// gets are sent from user with instruction
	// returns processed output
	// ..........................
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.print("Doing UserHandler GET");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// getting instruction from user
		instruction = request.getParameter("i");
		
		out.println(instruction);
		System.out.println(", sent instruction " + instruction);
	}
}
