package br.com.openWeatherRec.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.openWeatherRec.model.Usuario;

@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {

	private String email; 
	private String password;
	
    private List<GrantedAuthority> authorities; 

    public UsuarioDetails(Usuario user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO - get from database
		return true;
	}
}
