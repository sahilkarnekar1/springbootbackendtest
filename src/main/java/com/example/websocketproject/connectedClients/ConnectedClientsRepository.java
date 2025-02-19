package com.example.websocketproject.connectedClients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectedClientsRepository extends JpaRepository<ConnectedClientEntity, Long> {
    ConnectedClientEntity findByUserIdAndRole(String userId, String role);
    void deleteByUserIdAndRole(String userId, String role);
}
