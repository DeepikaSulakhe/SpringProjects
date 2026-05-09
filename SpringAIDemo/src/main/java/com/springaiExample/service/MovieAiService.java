package com.springaiExample.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

@Service
public class MovieAiService {
	
	private final ChatClient chatClient;
	
	public MovieAiService(ChatClient.Builder builder) {
		
//		this.chatClient = builder.defaultAdvisors(MessageChatMemoryAdvisor
//				.builder(MessageWindowChatMemory.builder(
//						).maxMessages(10).build()
//						).build()).build();
		this.chatClient = builder
			    .defaultAdvisors(
			        MessageChatMemoryAdvisor.builder( // so for every builder() we have to do build() (It's like build yourself) at the end else it won't work
			            MessageWindowChatMemory.builder()
			                .maxMessages(10)
			                .build() // Builds the memory implementation for message window chat memory
			        ).build() // CRITICAL: Builds the advisor instance for MessageChatMemoryAdvisor 
			    )
			    .build(); // Builds the ChatClient as we opened a builder and built all the advisor part inside here.

	}
	
	public String movieRecommendation(String genre,String language,String mood) {
		String prompt = """
				 Recommend a Movie based on:
				 Genre: %s
				 Language : %s
				 Mood: %s
				""".formatted(genre,language,mood);
		return chatClient.prompt()
				//prompt engineering - instruction, rules, role, guideline, response format
				.system("""
						
						You are an expert movie recommendation assistant.
						
						Responsibilities :
						- Recommend Movies based on user preferences
						- Consider previous conversation history
						- Avoid duplicate recommendations
						- Recommend highly rated movies
						- Recommend only Adult certified movies
						
						Response Format:
						Movie Name:
						Genre:
						Language:
						Mood:
						Director:
						Why Recommended:
						Movie Summary:
						
						Keep the Responses concise and engaging. 
						
						""")
				.user(prompt)
				.call()
				.content();
				
	}
	
	//It works too but still need to look into it
	public List<String> movieListRecommendation(String genre,String language,String mood,int count) {
//		String prompt = """
//				 Recommend a Movie based on:
//				 Genre: %s
//				 Language : %s
//				 Mood: %s
//				""".formatted(genre,language,mood);
		return chatClient.prompt()
				//prompt engineering - instruction, rules, role, guideline, response format
				.system("""
						
						You are an expert movie recommendation assistant.
						
						Responsibilities :
						- Recommend Movies based on user preferences
						- Consider previous conversation history
						- Avoid duplicate recommendations
						- Recommend highly rated movies
						- Recommend only Adult certified movies
						
						Response Format:
						Movie Name:
						Genre:
						Language:
						Mood:
						Director:
						Why Recommended:
						Movie Summary:
						
						Keep the Responses concise and engaging. 
						
						""")
				.user(u -> u.text("""
				 Recommend a Movie based on:
				 Genre: %s
				 Language : %s
				 Mood: %s
				""".formatted(genre,language,mood,count)))
				.call()
				.entity(new ListOutputConverter(new DefaultConversionService()));
	}
	
	

}
