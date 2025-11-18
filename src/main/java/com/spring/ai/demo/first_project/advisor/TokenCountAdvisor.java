package com.spring.ai.demo.first_project.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

@Slf4j
public class TokenCountAdvisor implements CallAdvisor, StreamAdvisor {
    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return streamAdvisorChain.nextStream(chatClientRequest);
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("Calling custom TokenCount Advisor");
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        log.info("Prompt token consumed: {}" , chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        log.info("Completion tokens consumed: {}" , chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        log.info("Total tokens consumed: {}",  chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());
        log.info("Received Response");
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public void setOrder(){

    }
}
