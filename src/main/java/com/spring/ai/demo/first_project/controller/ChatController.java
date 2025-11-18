package com.spring.ai.demo.first_project.controller;

import com.spring.ai.demo.first_project.entity.Tutorial;
import com.spring.ai.demo.first_project.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {


    private ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Tutorial>> chat(@RequestParam(value = "question") String question){
        return ResponseEntity.ok(chatService.chat(question));
    }
    @GetMapping("/default")
    public ResponseEntity<String> defaultChat(@RequestParam(value = "query") String query){
        return ResponseEntity.ok(chatService.chatDefaultOptions(query));
    }

    @GetMapping("/stream/response")
    public ResponseEntity<Flux<String>> streamCHat(@RequestParam("query") String query, @RequestHeader("userUid") String userUid){
        return ResponseEntity.ok(chatService.streamChat(query, userUid));
    }

    @GetMapping("/test-connection")
    public ResponseEntity<String> testConnection(){
        return ResponseEntity.ok("Success! App is now deployed in azure");
    }

}
