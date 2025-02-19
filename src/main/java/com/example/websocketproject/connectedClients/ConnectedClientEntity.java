package com.example.websocketproject.connectedClients;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "connected_clients")
public class ConnectedClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String role;
    private String clientId;
    private Timestamp connectedAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public Timestamp getConnectedAt() { return connectedAt; }
    public void setConnectedAt(Timestamp connectedAt) { this.connectedAt = connectedAt; }
}
