package com.kadylo.janus;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class PollerAnswerer extends HttpServlet{
	
	//constructor
	public PollerAnswerer(){
		
		// setting gate
		System.out.println("-->Constructing PollerAnswerer");
		Shuttle.access().setPollerGate(Thread.currentThread());
	}
	
	// gets are sent from PC
	// returns stored instruction
	// ..........................
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.print("-->Doing PollerAnswerer GET");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String instruction = Shuttle.access().getRequest();
		
		out.println(instruction);
		System.out.println(", sent instruction " + instruction);
	}
	
	// gets answers from HTTP POSTs
	// . .       .      .      .
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		System.out.print("-->Doing PollerAnswerer POST");
		
		String answer = request.getParameter("payload");
		System.out.println(", got Answer " + answer);
		
		Shuttle.access().setResponce(answer);
		Shuttle.access().getUserGate().interrupt();
	}
}
