package com.spring.ai.demo.first_project.service;

import com.spring.ai.demo.first_project.entity.Tutorial;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {

    List<Tutorial> chat(String query);
    String chatDefaultOptions(String query);
    String chatTemplate();

    Flux<String> streamChat(String query, String userUid);
}
