package com.tkc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WelcomeController {
	private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	@RequestMapping("/citySeacrh")
	public String citySeacrh(Map<String, Object> model,HttpServletResponse httpServletResponse) {
		return "welcome";
	}
	
	@RequestMapping("/findByCityName")
	@ResponseBody
	public ArrayList findByCityName(@RequestParam("cityName") String cityName,HttpServletResponse httpServletResponse) {
		logger.debug("$$$$$$$$$$---cityName--$$$$$$$$$$"+cityName);
        RestTemplate restTemplate = new RestTemplate();
        ArrayList  cityArrList = new ArrayList<>();
        HashMap response = new HashMap();
        try {
        HashMap quote = restTemplate.getForObject("http://api.wunderground.com/weather/api/0febb2c6dfdd1e46/conditions/q/"+cityName+".json", HashMap.class);
        if(quote !=null && quote.get("response")!=null) {
        	logger.info("---quote---"+quote.get("response"));
        response=(HashMap)quote.get("response");
        if(response !=null && response.get("results") !=null) {
        cityArrList=(ArrayList) response.get("results");
        logger.info("---aList---"+cityArrList.size());
        httpServletResponse.setStatus(200);
        }else {
        	logger.debug("No results present inside response");
        }
        }else {
        	logger.debug("No data recived from API");
        }
        } catch(Exception e) {
        	logger.error("problem retrieving the city search Apt");
        	e.printStackTrace();
        }
        return cityArrList;
		
	}
}