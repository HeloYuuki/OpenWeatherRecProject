package br.com.openWeatherRec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.openWeatherRec.dao.UsuarioRepository;
import br.com.openWeatherRec.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {
	//  implementa a interface UserDetailsService

	@Autowired
	UsuarioRepository dao;
	// carrega do banco os dados do usuario de acordo com o email fornecido
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// ir na UsuarioRepository e consultar se o usuario existe....
		
		Usuario user = dao.findByEmail(email);
		if (user==null) {
			throw new UsernameNotFoundException("Login invalido");
		}
		UsuarioDetails usd = new UsuarioDetails(user);
		return usd;

	}
}

