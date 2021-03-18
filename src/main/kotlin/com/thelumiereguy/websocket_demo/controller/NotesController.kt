package com.thelumiereguy.websocket_demo.controller

import com.thelumiereguy.websocket_demo.model.Note
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller


@Controller
class NotesController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun sendMessage(@Payload note: Note): Note {
        println("Welcome ${note.text}")
        return Note("Welcome ${note.text}")
    }

}

@Component
@EnableScheduling
class WebSocketPingScheduler {

    @Autowired
    lateinit var brokerMessagingTemplate: SimpMessagingTemplate


    @Scheduled(fixedDelay = 3000L)
    fun webSocketPing() {
        print("hello")
        brokerMessagingTemplate.convertAndSend(
            "/topic/greetings",
            Note("Hello from scheduled")
        )
    }
}