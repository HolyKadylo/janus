package com.kadylo.janus;
//TODO import

public class Shuttle{
	
	private String request;
	private String responce;
    
    private Thread pollerGate;
    private Thread userGate;
	
	// Singleton
	private Shuttle(){
		setRequest("echo HelloWorld");
		setResponce("dummyResp");
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
	public Thread getPollerGate(){
		return pollerGate;
	}
	public Thread getUserGate(){
		return userGate;
	}
	
	// setters
	public void setRequest(String request){
		this.request = request;
	}
	public void setResponce(String responce){
		this.responce = responce;
	}
	public void setPollerGate(Thread pollerGate){
		this.pollerGate = pollerGate;
	}
	public void setUserGate(Thread userGate){
		this.userGate = userGate;
	}
	
}