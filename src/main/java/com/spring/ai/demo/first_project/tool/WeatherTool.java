package com.spring.ai.demo.first_project.tool;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherTool {

    private final RestClient restClient;

    @Value("${app.weather.api-key}")
    private String weatherApiKey;

    private static final String CURRENT_WEATHER_URI = "/current.json";

    @Tool(name = "WeatherTool", description = "Get current weather information of the given city in the parameter")
    public String getWeatherByCity(@ToolParam(description = "Name of the city for which weather needs to be fetched") String city){
        return restClient
                .get()
                .uri(builder -> builder.path(CURRENT_WEATHER_URI).queryParam("key", weatherApiKey).queryParam("q", city).build())
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .toString();

    }

}
