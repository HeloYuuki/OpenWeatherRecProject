package br.com.openWeatherRec.ModelAPI;

import java.util.List;

public class OpenWeather {
	
	private String id;
	
	private String name;
	
	/* sys {}*/
	private Sys sys;
	
	/* coord {}*/
	private Coord coord;
	
	/* main {} */
	private Main main;
	
	/* Weather {}*/
	private List<Weather> weather;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
    	
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}




}
