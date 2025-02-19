package com.example.websocketproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.websocketproject.model.Candidate;


// implemented jpa repo for test saving data

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

}
