package com.example.websocketproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.websocketproject.model.Candidate;
import com.example.websocketproject.repo.CandidateRepo;

// simple candidate save data service created here

@Service
public class CandidateService {

	@Autowired
	CandidateRepo canRepo;
	
	 public Candidate saveCandidate(Candidate candidate) {
	        return canRepo.save(candidate);
	    }
	
}
