
// this complete file created by sahil karnekar date 10-12-2024


package com.example.websocketproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.corundumstudio.socketio.SocketIOServer;
import com.example.websocketproject.SocketEmitEventsPkg.SocketEmitEvents;
import com.example.websocketproject.model.Candidate;
import com.example.websocketproject.service.CandidateService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CandidateController {
	
	// this is candidate service created for save data in database
	@Autowired
	CandidateService canService;
	
	// socket io server implemented by sahil karnekar date 10-12-2024
	
	@Autowired
	SocketIOServer server;
	
	
	@Autowired
	SocketEmitEvents emitEvents;
	
	private String roleParam, recruiterIdParam, teamLeaderIdParam, managerIdParam, superUserIdParam;
	
	
	@PostMapping("/addCandidate")
	public String addCan(@RequestBody Candidate candidate, 
			@RequestParam String role,
			 @RequestParam(required = false) String recruiterId,
            @RequestParam(required = false) String teamLeaderId,
            @RequestParam(required = false) String managerId,
            @RequestParam(required = false) String superUserId
			
			) {
		// instead of taking params get ids from database directly 
		roleParam = role;
		recruiterIdParam = recruiterId;
		teamLeaderIdParam = teamLeaderId;
		managerIdParam = managerId;
		superUserIdParam = superUserId;
		
          canService.saveCandidate(candidate);

      //    emitEvents.addCandidateEvent(roleParam,recruiterIdParam,teamLeaderIdParam,managerIdParam, superUserIdParam, candidate);
          return "Candidate saved successfully";
	}
	
	@PostConstruct
	public void startServer() {
	    server.addEventListener("add_candidate", Candidate.class, (client, data, ackSender) -> {
	  
	    	 emitEvents.addCandidateEvent(roleParam,recruiterIdParam,teamLeaderIdParam,managerIdParam, superUserIdParam, data);
	    	
	    });
	  
    server.addEventListener("update_candidate", Candidate.class, (client, data, ackSender) -> {
	    	
	    	emitEvents.updateCandidateEvent(roleParam, recruiterIdParam, teamLeaderIdParam, managerIdParam, superUserIdParam, data);
	    	
	    });
	   
		
		
		
	}
	
	
	

}
