package com.kadylo.janus;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class UserHandler extends HttpServlet{
	
	//constructor
	public UserHandler(){
		
		// setting gate
		Shuttle.access().setUserGate(Thread.currentThread());
	}
	
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
		String instruction = request.getParameter("i");
		
		Shuttle.access().setRequest(instruction);
		
		String resp = "";
		try{
			Shuttle.access().getPollerGate().join();
		} catch (InterruptedException e){
			resp = Shuttle.access().getResponce();
		}
		resp = Shuttle.access().getResponce();
		
		out.println(resp);
		System.out.println(", sent instruction " + resp);
	}
}
