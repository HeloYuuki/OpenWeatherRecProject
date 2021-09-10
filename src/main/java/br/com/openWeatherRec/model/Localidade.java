package br.com.openWeatherRec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import br.com.openWeatherRec.ModelAPI.Air_Polution;
import br.com.openWeatherRec.ModelAPI.OpenWeather;


@Entity
public class Localidade {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	private OpenWeather weather;
	
	
	@Transient
	private Air_Polution polution;
	
	@NotBlank(message="Campo nome obrigatorio")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message="Campo codigo obrigatorio")
	@Column(nullable = false, unique=true)
	private String codigo;
	
	@NotBlank(message="Campo latitude obrigatorio")
	@Column(nullable = false)
	private String latitude;
	
	@NotBlank(message="Campo longitude obrigatorio")
	@Column(nullable = false)
	private String longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public OpenWeather getWeather() {
		return weather;
	}

	public void setWeather(OpenWeather weather) {
		this.weather = weather;
	}

	public Air_Polution getPolution() {
		return polution;
	}

	public void setPolution(Air_Polution polution) {
		this.polution = polution;
	}
	
}
