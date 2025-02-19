// this is config file to setuo socket io server by sahil karnekar date 10-12-2024

// complete file code is required to create a socket io server with the port number as well

package com.example.websocketproject;


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(8080);
        config.setOrigin("*");
        return new SocketIOServer(config);
    }
}

