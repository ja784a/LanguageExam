package language.exam.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private final String username;
	private final String password;
	private final Integer id;
	private final String name;
	private final List<GrantedAuthority> authorities;
	
	public CustomUserDetails(String username, String password, Integer id, String name, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.name = name;
		this.authorities = authorities;
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}