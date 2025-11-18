package com.spring.ai.demo.first_project;

import com.spring.ai.demo.first_project.service.ChatService;
import com.spring.ai.demo.first_project.tool.WeatherTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstProjectApplicationTests {

	@Autowired
	private ChatService chatService;

	@Autowired
	private WeatherTool weatherTool;


	@Test
	void testChatTemplate(){
		System.out.println("testing chat template");
		System.out.println("Response: " + chatService.chatTemplate());
	}

	@Test
	void getCurrentWeatherTest(){
		var response = weatherTool.getWeatherByCity("Bengaluru");
		System.out.println("Weather Reponse: " + response);
	}
}
