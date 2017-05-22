package com.kadylo.janus;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.util.*;

public class PollerAnswerer extends HttpServlet{
	private static volatile String instruction = null;
	private static volatile String Answer = null;
	
	/* public String getAnswer(){
		
		return Answer;
	}
	
	public setInstruction(String instruction){
		this.instruction = instruction;
	} */
	
	
	public static void main (String[] args){
		System.out.println("PollerAnswerer started");
	}
	
	// gets are sent from PC
	// returns stored instruction
	// ..........................
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		System.out.print("Doing PollerAnswerer GET");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (instruction == null){
			instruction = "echo Hello";
		}
		out.println(instruction);
		System.out.println(", sent instruction " + instruction);
	}
	
	// gets answers from HTTP POSTs
	// . .       .      .      .
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		System.out.print("Doing PollerAnswerer POST");
		Answer = request.getParameter("payload");
		System.out.println(", got Answer " + Answer);
	}
}
