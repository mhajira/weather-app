package com.mhajira.weatherapp;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
public class WeatherAppApplication {

	@Inject
	private WeatherService weatherService;
	
	@Inject
	private TimeService timeService;
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	@GetMapping("/current/weather")
	public String getWeather() {
		return "Current weather is =>"+weatherService.getWeather() +" @ "+ timeService.getTime();
	}
	

}
