package com.bank_system.adarsh_bank.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendGlobalUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/updates", message);
    }
}
