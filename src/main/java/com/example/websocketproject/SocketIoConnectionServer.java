package com.example.websocketproject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import jakarta.annotation.PostConstruct;

@Component
public class SocketIoConnectionServer {
	
	
	
	@Autowired
	SocketIOServer server;
	
	
	
	private final ConcurrentHashMap<String, SocketIOClient> connectedClients = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, List<Map<String, Object>>> offlineMessages = new ConcurrentHashMap<>();
	
	
	@PostConstruct
	public void startServer() {
		// line this 43 to line 64 call at the time of login
				server.addConnectListener(client -> {
			        String userId = client.getHandshakeData().getSingleUrlParam("userId");
			        String role = client.getHandshakeData().getSingleUrlParam("role");
			        if (userId != null && role != null) {
			            String clientKey = role + "_" + userId;
			            
			            connectedClients.put(clientKey, client);
			            System.out.println("User connected: " + clientKey);
			            
			          //  this function newly created for brodcasting offline messages stored in hashmap
			            
			            if (offlineMessages.containsKey(clientKey)) {
			            	List<Map<String, Object>> messages = offlineMessages.get(clientKey);
			            	messages.forEach(message -> {
			            		
			            		
			            		if (message.get("eventName").equals("add_candidate")) {
			            			client.sendEvent("receive_saved_candidate", message); // Send each message to the client
			                        System.out.println("Offline eventName: " + message.get("eventName"));
			                        System.out.println("Offline Message: " + message); // Optional logging
								}else if (message.get("eventName").equals("update_candidate")) {
									client.sendEvent("receive_updated_candidate", message); // Send each message to the client
			                        System.out.println("Offline eventName: " + message.get("eventName"));
			                        System.out.println("Offline Message: " + message); // Optional logging
								}
			            	
		                    });
		                    System.out.println("Delivered offline messages to: " + clientKey);
		                    offlineMessages.remove(clientKey); // Clear delivered messages
			            }
			            
			        }
			    });
				
				server.addDisconnectListener(client -> {
		            String userId = client.getHandshakeData().getSingleUrlParam("userId");
		            String role = client.getHandshakeData().getSingleUrlParam("role");
		            if (userId != null) {
		                connectedClients.remove(role+ "_" +userId);
		                System.out.println("User disconnected: " + role+ "_" +userId);
		            }
		        });
		
				server.start();
		
	}
	
	 public ConcurrentHashMap<String, SocketIOClient> getConnectedClients() {
	        return connectedClients;
	    }

	    // Gets the offline messages map
	    public ConcurrentHashMap<String, List<Map<String, Object>>> getOfflineMessages() {
	        return offlineMessages;
	    }

}
