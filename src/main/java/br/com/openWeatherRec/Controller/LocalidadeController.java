package br.com.openWeatherRec.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.openWeatherRec.ModelAPI.Air_Polution;
import br.com.openWeatherRec.ModelAPI.OpenWeather;
import br.com.openWeatherRec.Service.OpenWeatherService;
import br.com.openWeatherRec.dao.LocalidadeRepository;
import br.com.openWeatherRec.model.Localidade;

@Controller
@RequestMapping("/localidades")
public class LocalidadeController {
	@Autowired
	LocalidadeRepository dao; // instanciou repository
	
	@Autowired
	OpenWeatherService apiService; //chamou - instanciou o servico
	
	@GetMapping("/new")
	public String newForm(Model model) {
		Localidade l = new Localidade();
		model.addAttribute("localidade", l);	
		return "localidade/manterLocalidade.html";
	}
	
	@GetMapping("/getapi/{id}")
	public String getapi(@PathVariable Long id, Model model) {
		Localidade l = dao.findById(id).get();
		model.addAttribute("localidade", l);
		
		OpenWeather weather = apiService.getOpenWeatherByName(l.getNome()); 
		// verifica se a lat e lon inseridos na entidade localidade estão de acordo com a API
		if(weather.getCoord().getLat().equals(l.getLatitude()) && weather.getCoord().getLon().equals(l.getLongitude()) )
		{
			Air_Polution polution = apiService.getAirPolutionByLatLong(l.getLatitude(), l.getLongitude()); // Pega os dados da poluição
			l.setPolution(polution);
			
			String aqi = l.getPolution().getList().get(0).getMain().getAqi();	// Pega dentro de localidade na estrutura poluição temp o código da
																				// qualidade do ar.
			//Verifica qual a qualidade do ar. Air Quality. 1= Good, 2=Fair, 3=Moderate, 4=Poor, 5=Very Poor.
			switch (aqi) {
			  case "1":
				  l.getPolution().getList().get(0).getMain().setAqi("Good");
			    break;
			  case "2":
				  l.getPolution().getList().get(0).getMain().setAqi("Fair");
			    break;
			  case "3":
				  l.getPolution().getList().get(0).getMain().setAqi("Moderate");
			    break;
			  case "4":
				  l.getPolution().getList().get(0).getMain().setAqi("Poor");
			    break;
			  case "5":
				  l.getPolution().getList().get(0).getMain().setAqi("Very Poor");
				  break;
			}
		}
		else {

		}
				
		l.setWeather(weather);
		
		return "localidade/manterLocalidade.html";
		
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Localidade l = dao.findById(id).get();
		model.addAttribute("localidade", l);
		
		return "localidade/manterLocalidade.html";
	}
	
	@PostMapping("/save")
	public String save(@Valid Localidade localidade, BindingResult result, Model model) {
		OpenWeather weather = apiService.getOpenWeatherByName(localidade.getNome());
		if(weather == null) {
			result.addError(new FieldError("localidade", "nome", "Cidade não existe na API OpenWeather"));
			return "localidade/manterLocalidade.html";
		}
		else
			localidade.setCodigo(weather.getId());//Verificação de lat e lon
			if(weather.getCoord().getLat().equals(localidade.getLatitude()) && weather.getCoord().getLon().equals(localidade.getLongitude()))
			{
				dao.save(localidade);
				return "redirect:/localidades/list";
				
			}
			else if(!weather.getCoord().getLat().equals(localidade.getLatitude())){
				result.addError(new FieldError("localidade", "latitude", "A latitude não é igual a API"));
				return "localidade/manterLocalidade.html";
			}
			else if(!weather.getCoord().getLon().equals(localidade.getLongitude()))
			{
				result.addError(new FieldError("localidade", "longitude", "A longitude não é igual a API"));
				return "localidade/manterLocalidade.html";
			}
			else
				
			if(result.hasErrors())
				return "localidade/manterLocalidade.html";
			else
				dao.save(localidade);
			return "redirect:/localidades/list";
			
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Localidade> localidades = dao.findAll();
		model.addAttribute("localidadeList", localidades);
		return "localidade/list.html";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerLocalidade(id);
		return "redirect:/localidades/list";
	}
}
