package com.spring.ai.demo.first_project.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DateTimeTool {

    @Tool(description = "Get current date and time of the user based on his/her timezone")
    public String getCurrentDateTime(){
        log.info("Calling DateTimeTool");
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();

    }
}
