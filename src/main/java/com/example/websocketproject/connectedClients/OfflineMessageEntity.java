package com.example.websocketproject.connectedClients;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "offline_messages")
public class OfflineMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String role;
    private String eventName;

    @Column(columnDefinition = "JSON")
    private String message;

    private Timestamp createdAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

