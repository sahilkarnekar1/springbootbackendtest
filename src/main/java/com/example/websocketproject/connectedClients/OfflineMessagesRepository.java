package com.example.websocketproject.connectedClients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfflineMessagesRepository extends JpaRepository<OfflineMessageEntity, Long> {
    List<OfflineMessageEntity> findByUserIdAndRole(String userId, String role);
}

