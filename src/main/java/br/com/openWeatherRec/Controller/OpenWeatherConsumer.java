package br.com.openWeatherRec.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.openWeatherRec.ModelAPI.Air_Polution;
import br.com.openWeatherRec.ModelAPI.OpenWeather;
import br.com.openWeatherRec.Service.OpenWeatherService;


@RestController 
@RequestMapping("/OpenWeather")
public class OpenWeatherConsumer {
	
	 @Autowired 
	 OpenWeatherService apiService;
	 @GetMapping("/weather/{name}") 
    public OpenWeather getOpenWeatherByName(@PathVariable String name) {
    	
        return apiService.getOpenWeatherByName(name);
    }
    
    
@GetMapping("/polution")    
public Air_Polution getPolution(@RequestParam String lat, @RequestParam String lon) {
    	
        return apiService.getAirPolutionByLatLong(lat,lon);
    }


}
