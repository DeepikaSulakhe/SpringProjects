package com.springaiExample.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ProductRecommendService {

	private final ChatClient chatClient;
	
	public ProductRecommendService(ChatClient.Builder builder) {
		this.chatClient = builder.defaultAdvisors(MessageChatMemoryAdvisor
				.builder(MessageWindowChatMemory
				.builder().maxMessages(10).build())
				.build())
				.build();
	}
	
	public String productRecommendation(String brand, String name,int ram) {
		String prompt = """
				 Recommend a Product based on:
				 Brand: %s
				 Name: %s
				 Ram: %s
				 
				""".formatted(brand,name,ram);
		return chatClient.prompt()
				.system("""
						You are an expert Product Recommendation assistant:
						
						Responsibilities :
						- Recommend Products based on user preferences
						- Consider previous conversation history
						- Avoid duplicate recommendations
						- Recommend price for the product
						- Recommend within the specified ram or better
						
						Response Format:
					    - Brand Name:
					    - Product Name:
					    - Price Range:
					    - Why Recommended:
					    - Required Ram:
						
						"""
						).user(prompt).call().content();
	}
}
