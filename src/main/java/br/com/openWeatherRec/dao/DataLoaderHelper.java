package br.com.openWeatherRec.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.openWeatherRec.model.Localidade;
import br.com.openWeatherRec.model.Usuario;


@Service
public class DataLoaderHelper {
	
	public static void loadData(
	
		UsuarioRepository daoUsuario,
		LocalidadeRepository daoLocal) {
/* Exemplo para salvar o usuario com senha criptografada */
	BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

	Usuario admin = new Usuario();
	admin.setEmail("admin@mail.com");
	admin.setPassword(passEncoder.encode("123"));
	admin.setConfirmPassword(passEncoder.encode("123"));
	admin.setNome("admin");
	daoUsuario.save(admin);

	Localidade local1 = new Localidade();
	local1.setCodigo("5368361");
	local1.setNome("Los Angeles");
	local1.setLatitude("34.0522");
	local1.setLongitude("-118.2437");
	daoLocal.save(local1);
	
	}
	@Bean
	public CommandLineRunner loader(UsuarioRepository daoUsuario, LocalidadeRepository daoLocal) {
		return (args) -> {
			DataLoaderHelper.loadData( daoUsuario, daoLocal);
			};
		}
}
