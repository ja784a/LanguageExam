package language.exam.domain.exams.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Accounts;
import language.exam.domain.exams.service.AccountsService;
import language.exam.security.CustomUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountsService accountsService;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException{
		Accounts account = accountsService.getAccount(mail);
		
		if (account == null) {
			throw new UsernameNotFoundException("Not user found");
		}
		
		String username = account.getMail();
		String password = account.getPass();
		Integer id = account.getId();
		String name = account.getName();
		String role = "";
		if (account.getRole() == 0) {
			role += "ROLE_USER";
		} else {
			role += "ROLE_ADMIN";
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		return new CustomUserDetails(username, password, id, name, authorities);
	}
}