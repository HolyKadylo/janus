package com.kadylo.janus;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class UserHandler extends HttpServlet{
	
	//constructor
	public UserHandler(){
		
		// setting gate
		System.out.println("-->Constructing UserHandler");
		Shuttle.access().setUserGate(Thread.currentThread());
	}
	
	// gets are sent from user with instruction
	// returns processed output
	// ..........................
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.println("-->Doing UserHandler GET");
		response.setContentType("text/html; charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();
		
		// getting instruction from user
		String instruction = request.getParameter("i");
		
		Shuttle.access().setUserGate(Thread.currentThread());
		Shuttle.access().setRequest(instruction);
		
		String resp = "";
		try{
			Shuttle.access().getPollerGate().join();
		} catch (InterruptedException e){
			resp = Shuttle.access().getResponce();
		}
		
		String writing = "";
		String[] parts = resp.split("\r\n");
		out.println("<!DOCTYPE html><html><head><title>" + instruction + "</title></head><body>");
		for (String s : parts){
			out.println(s + "<br>");
		}
		out.println("</body></html>");
		out.close();
		System.out.println("-->Answer sent");
	}
}
