package com.example.demo.config;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final CustomUserDetailsService userService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("custom authentication.");
		System.out.println("authentication : " + authentication);
		
		String username=authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = null;
		Collection<GrantedAuthority> authorities = null;
		
		try {
			user = (User)userService.loadUserByUsername(username);
			
			if(!passwordEncoder.matches(password, user.getPassword()))
				throw new BadCredentialsException("비밀번호 불일치.");
			
			authorities = (Collection<GrantedAuthority>) user.getAuthorities();
			
	    } catch(UsernameNotFoundException e) {
	        e.printStackTrace();
	        throw new UsernameNotFoundException(e.getMessage());
	    } catch(BadCredentialsException e) {
	        e.printStackTrace();
	        throw new BadCredentialsException(e.getMessage());
	    } catch(Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException(e.getMessage());
	    }
		System.out.println("here!!!!!!!!!!!!!!!!!!!");
		return new CustomAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
