package com.mhajira.weatherapp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@Service
public class WeatherService {
	
	@Inject
	private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "unknown")
	public String getWeather()
	{
		return restTemplate.getForEntity("http://weather-service/weather", String.class).getBody();
	}
	
	public String unknown() {
		return " May be your server is down";
	}
	
}
