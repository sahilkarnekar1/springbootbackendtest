package com.example.websocketproject.SocketEmitEventsPkg;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocketproject.SocketIoConnectionServer;
import com.example.websocketproject.model.Candidate;

@Service
public class SocketEmitEvents {

	@Autowired
	SocketIoConnectionServer handlerServer;
	
	@Autowired
	SendMessageClass sendMessageHandler;
	
	
	
	
	
	
	public void addCandidateEvent(String roleParam,String recruiterIdParam,String teamLeaderIdParam,String managerIdParam, String superUserIdParam, Candidate data) {
    	String eventName = "add_candidate";
    	 Candidate candidateData = data;
    //	call data saving operation in database here
    	Map<String, Object> candidateSavedData = new HashMap<>();
    	candidateSavedData.put("eventName", eventName);  // Add eventName to the map
    	candidateSavedData.put("candidate", candidateData);
        
        System.out.println(candidateSavedData);
handleRoleBasedMessaging(roleParam,recruiterIdParam, teamLeaderIdParam, managerIdParam, superUserIdParam, candidateSavedData, handlerServer);
	}
	
	public void updateCandidateEvent(String roleParam,String recruiterIdParam,String teamLeaderIdParam,String managerIdParam, String superUserIdParam, Candidate data) {
    	String eventName = "update_candidate";
    	 Candidate candidateData = data;
    //	call data saving operation in database here
    	Map<String, Object> candidateSavedData = new HashMap<>();
    	candidateSavedData.put("eventName", eventName);  // Add eventName to the map
    	candidateSavedData.put("candidate", candidateData);
    	
        System.out.println(candidateSavedData);
        
        handleRoleBasedMessaging(roleParam,recruiterIdParam, teamLeaderIdParam, managerIdParam, superUserIdParam, candidateSavedData, handlerServer);
	}
	
	
	public void handleRoleBasedMessaging(String role,String recruiterIdParam, String teamLeaderIdParam, String managerIdParam, String superUserIdParam, Map<String, Object> candidateSavedData, SocketIoConnectionServer handlerServer) {
	    switch (role) {
	        case "Recruiters":
	        	sendMessageHandler.sendMessageToClient("TeamLeader_", teamLeaderIdParam, candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessageToClient("Manager_", managerIdParam, candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessageToClient("SuperUser_", superUserIdParam, candidateSavedData, handlerServer);
	            break;

	        case "TeamLeader":
	        	sendMessageHandler.sendMessagesToMultipleClients("Recruiters_", recruiterIdParam.split(","), candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessageToClient("Manager_", managerIdParam, candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessageToClient("SuperUser_", superUserIdParam, candidateSavedData, handlerServer);
	            break;

	        case "Manager":
	        	sendMessageHandler.sendMessagesToMultipleClients("Recruiters_", recruiterIdParam.split(","), candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessagesToMultipleClients("TeamLeader_", teamLeaderIdParam.split(","), candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessageToClient("SuperUser_", superUserIdParam, candidateSavedData, handlerServer);
	            break;

	        case "SuperUser":
	        	sendMessageHandler.sendMessagesToMultipleClients("Recruiters_", recruiterIdParam.split(","), candidateSavedData, handlerServer);
	        	sendMessageHandler. sendMessagesToMultipleClients("TeamLeader_", teamLeaderIdParam.split(","), candidateSavedData, handlerServer);
	        	sendMessageHandler.sendMessagesToMultipleClients("Manager_", managerIdParam.split(","), candidateSavedData, handlerServer);
	            break;

	        default:
	            System.out.println("Invalid role: " + role);
	    }
	}

	
	
}
