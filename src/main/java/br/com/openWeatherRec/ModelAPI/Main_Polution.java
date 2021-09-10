package br.com.openWeatherRec.ModelAPI;

public class Main_Polution {
	/*Air_Polution*/
	private String aqi;

	
	public String getAqi() {
		return aqi;
	}

	/*Air Quality Index. Possible values: 1, 2, 3, 4, 5. 
	 * Where 1 = Good, 2 = Fair, 3 = Moderate, 4 = Poor, 5 = Very Poor.*/
	public void setAqi(String aqi) {
			this.aqi = aqi;
	}

}
