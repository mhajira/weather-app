package com.mhajira.weatherapp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TimeService {
	
	@Inject
	private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "unknown")
	public String getTime()
	{
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}
	
	public String unknown() {
		return " May be your server is down";
	}
}
