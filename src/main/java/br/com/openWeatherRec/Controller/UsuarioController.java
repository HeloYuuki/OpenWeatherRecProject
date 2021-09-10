package br.com.openWeatherRec.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.openWeatherRec.dao.UsuarioRepository;
import br.com.openWeatherRec.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		@Autowired
		UsuarioRepository dao;
		
		@GetMapping("/new")
		public String newForm(Model model) {
			Usuario u = new Usuario();
			model.addAttribute("usuario", u);	
			return "usuario/manterUsuario.html";
		}
		
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable Long id, Model model) {
			Usuario u = dao.findById(id).get();
			model.addAttribute("usuario", u);
			return "usuario/manterUsuario.html";
		}
		@PostMapping("/save")
		public String save(@Valid Usuario usuario, BindingResult result, Model model) {
			if(result.hasErrors())
				return "usuario/manterUsuario.html";
			
			usuario.setPassword(passEncoder.encode(usuario.getPassword()));
			if(passEncoder.matches(usuario.getConfirmPassword(), usuario.getPassword()))
				{
				dao.save(usuario);
				return "redirect:/usuarios/list";
				}
			else
				result.addError(new FieldError("usuario", "confirmPassword", "As senhas tem que ser iguais"));
			
			return "usuario/manterUsuario.html";	
		}
		
		@GetMapping("/list")
		public String list(Model model) {
			List<Usuario> usuarios = dao.findAll();
			model.addAttribute("userList", usuarios);
			return "usuario/list.html";
		}
		
		@GetMapping(value = "/delete/{id}")
		public String delete(@PathVariable Long id) {
			dao.removerUsuario(id);
			return "redirect:/usuarios/list";
		}
}

