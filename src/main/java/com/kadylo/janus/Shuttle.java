package com.kadylo.janus;
//TODO import

public class Shuttle{
	
	private String request;
	private String responce;
	private boolean requested;
    
    private Thread pollerGate;
    private Thread userGate;
	
	// Singleton
	private Shuttle(){
		
	}
	
	private static Shuttle shuttle = new Shuttle();
	
	public static Shuttle access(){
		return shuttle;
	}
	
	// getters
	public String getRequest(){
		return request;
	}
	
	public String getResponce(){
		return responce;
	}
	
	public boolean getRequested(){
		return requested;
	}
	
	// setters
	public void setRequest(String request){
		this.request = request;
	}
	
	public void setResponce(String responce){
		this.responce = responce;
	}
	
	public void setRequested(boolean requested){
		this.requested = requested;
	}
	
}