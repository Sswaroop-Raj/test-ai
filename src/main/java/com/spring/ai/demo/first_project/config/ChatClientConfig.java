package com.spring.ai.demo.first_project.config;

import com.spring.ai.demo.first_project.advisor.TokenCountAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@Slf4j
public class ChatClientConfig {

    @Value("classpath:system-message.st")
    private Resource systemMessageResource;

    @Bean
    public ChatClient openAiChatClient(ChatClient.Builder builder, ChatMemory chatMemory){

        log.info("ChatMemory in use: {}",  chatMemory.getClass().getName());
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return builder
                .defaultAdvisors(messageChatMemoryAdvisor, new TokenCountAdvisor(), new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("games", "cricket", "daimler truck")))
                .defaultSystem(systemMessageResource)
                .defaultOptions(OpenAiChatOptions.builder()
                        .temperature(1.0)
                        .model("gpt-4o-mini")
                        .maxTokens(100)
                        .build())
                .build();
    }

    //If in case need to use different models in both together we can inject them in this way using qualifier names
//    @Bean("OpenAiChatClient")
//    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel){
//        return ChatClient.builder(openAiChatModel).build();
//    }
//
//    @Bean("OllamaChatClient")
//    public ChatClient ollamaChatClient(OllamaAiChatModel ollamaAiChatModel){
//        return ChatClient.builder(ollamaAiChatModel).build();
//    }

}
