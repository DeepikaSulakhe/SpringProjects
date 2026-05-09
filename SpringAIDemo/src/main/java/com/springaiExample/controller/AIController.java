package com.springaiExample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springaiExample.service.MovieAiService;
import com.springaiExample.service.ProductRecommendService;

@RestController
public class AIController {
	
	private final MovieAiService movieService;
	
	private final ProductRecommendService prodService;
	
	
	public AIController(MovieAiService movieService, ProductRecommendService prodService) {
		this.movieService = movieService;
		this.prodService  = prodService;
	}
	

	//1. This one is for normal prompt based approach 
	//@GetMapping("/ask")
	//public String askMyAI(@RequestParam String prompt) {
	//	
	//		return chatClient.prompt() //it creates the prompt
	//		.user(prompt) // it sends the message to AI
	//		.call() // it calls the open ai server
	//		.content(); // return the response in text
	//		
	//		}
	

	// 2. This one is for movie based prompt and language based approach 
	@GetMapping("/movies")
	public String recommendMovie(@RequestParam String genre,@RequestParam String language,@RequestParam String mood) {
		
		return movieService.movieRecommendation(genre, language, mood);
		
	}
	
	@GetMapping("/moviesList")
	public List<String> movieListRecommendationFromService(@RequestParam String genre,
			@RequestParam String language,@RequestParam String mood,@RequestParam(defaultValue = "3") int count){
		
		return movieService.movieListRecommendation(genre, language,mood,count);
		
	}
	
	@GetMapping("/products")
		public String recommendProduct(@RequestParam String brand, @RequestParam String name, @RequestParam int ram) {
			return prodService.productRecommendation(brand, name, ram);
		}

     
}