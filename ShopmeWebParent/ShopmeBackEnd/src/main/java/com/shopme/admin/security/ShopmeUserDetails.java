package com.shopme.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

public class ShopmeUserDetails implements UserDetails {

	private User user;
	
	public ShopmeUserDetails(User user) {
		this.user = user;
	}

	//METHOD TO BE USED BY SPRING SECURITY TO GET LIST OF ASSIGNED ROLES TO USER
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles(); 
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}
	
	//METHOD TO GET PASSWORD
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	//METHOD TO GET USERNAME(IN THIS CASE USERNAME IS EMAIL ADDRESS)
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	//METHOD TO ENSURE THAT ACCOUNT IS TREATED AS NON EXPIRED
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//METHOD TO ENSURE THAT ACCOUNT IS TREATED AS NON LOCKED
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//METHOD TO ENSURE THAT ACCOUNT CREDENTIALS ARE TREATED AS NON EXPIRED
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//METHOD TO ENSURE THAT USER ACCOUNT IS ENABLED
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	//METHOD BUILT TO GET ACCESS TO FULL NAME OF USER FOR LOGOUT LINK
	public String getFullname() {
		return this.user.getFirstName() + " " + this.user.getLastName();
	}
	
	public void setFirstName(String firstName) {
		this.user.setFirstName(firstName);
	}
	
	public void setLastName(String lastName) {
		this.user.setLastName(lastName);
	}

}
