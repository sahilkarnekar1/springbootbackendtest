package com.example.websocketproject.SocketEmitEventsPkg;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.websocketproject.SocketIoConnectionServer;

@Service
public class SendMessageClass {

	public void sendMessageToClient(String rolePrefix, String id, Map<String, Object> candidateSavedData, SocketIoConnectionServer handlerServer) {
	    String clientKey = rolePrefix + id;
	    SocketIOClient client = handlerServer.getConnectedClients().get(clientKey);
	    
	    if (candidateSavedData.get("eventName").equals("add_candidate")) {
		    if (client != null) {
		        client.sendEvent("receive_saved_candidate", candidateSavedData);
		        System.out.println("Message sent to " + clientKey);
		    } else {
		        handlerServer.getOfflineMessages().computeIfAbsent(clientKey, k -> new ArrayList<>()).add(candidateSavedData);
		        System.out.println("Message queued for offline client: " + clientKey);
		    }
		}
	    
	    if (candidateSavedData.get("eventName").equals("update_candidate")) {
		    if (client != null) {
		        client.sendEvent("receive_updated_candidate", candidateSavedData);
		        System.out.println("Message sent to " + clientKey);
		    } else {
		        handlerServer.getOfflineMessages().computeIfAbsent(clientKey, k -> new ArrayList<>()).add(candidateSavedData);
		        System.out.println("Message queued for offline client: " + clientKey);
		    }
		}
	    
	 
	}

	public void sendMessagesToMultipleClients(String rolePrefix, String[] ids, Map<String, Object> candidateSavedData, SocketIoConnectionServer handlerServer) {
	    for (String id : ids) {
	        sendMessageToClient(rolePrefix, id, candidateSavedData, handlerServer);
	    }
	}
	
	
}
