package com.spring.ai.demo.first_project.service;

import com.spring.ai.demo.first_project.entity.Tutorial;
import com.spring.ai.demo.first_project.tool.DateTimeTool;
import com.spring.ai.demo.first_project.tool.WeatherTool;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    private WeatherTool weatherTool;
    private DateTimeTool dateTimeTool;

    public List<Tutorial> chat(String userPrompt) {
        return chatClient.prompt(userPrompt).call()
                .entity(new ParameterizedTypeReference<List<Tutorial>>(){});
    }

    public String chatDefaultOptions(String userQuery) {

        //Default chatclient options via ChatClientConfig
        Prompt userPrompt = new Prompt(userQuery);
        return chatClient.prompt(userPrompt).call().content();
    }

    public String chatTemplate(){
        String userQuery = "Tell me about {techName} and an example like {exampleName}";
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(userQuery)
                .build();

        String rendererMessage = promptTemplate.render(Map.of(
                "techName", "spring boot",
                "exampleName", "coding ai assistant"
        ));
        return this.chatClient.prompt(new Prompt(rendererMessage)).call().content();
    }

    @Override
    public Flux<String> streamChat(String query, String userUid) {
        return chatClient
                .prompt()
                .tools(dateTimeTool, weatherTool)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userUid)) //This will be used for user session management based on conversation id!
                .user(query)
                .stream()
                .content();
    }
}
