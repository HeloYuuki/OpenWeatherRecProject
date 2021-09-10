package br.com.openWeatherRec.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.openWeatherRec.ModelAPI.Air_Polution;
import br.com.openWeatherRec.ModelAPI.OpenWeather;

@Service //indica que a classe abaixo vai ser uma service ;D
public class OpenWeatherService {
	
	@Configuration
	public class RestTemplateClient {
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	}

	@Value("${api.openWeather.key}") //pegando a key do app properties
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;

    
 public OpenWeather getOpenWeatherByName(String name) { //pegar a api pelo nome
    	
    	String url = 
        		"https://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=" +  apiKey; //caminho para copnsminir nhauuum a api
    	
    	try {
    	OpenWeather weather = apiRequest.getForObject(url, OpenWeather.class);
    		
    		return weather; 
    	}catch (Exception handlerException) {
    		
    		return null;
    	}
        
    	
    	
        
        
    }
 
 
 public Air_Polution getAirPolutionByLatLong(String lat, String lon) {
	 
	 /*https://api.openweathermap.org/data/2.5/air_pollution?lat=34.0522&lon=-118.2437&appid=93a92e275946434503f2c6e38f620b48*/
	 String url = 
     		"https://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat +"&lon="+ lon + "&appid=" +  apiKey;
 	
	 Air_Polution polution = apiRequest.getForObject(url, Air_Polution.class);
     
     return polution; 
     
	 
 }
 
}
