package com.infy.shopping.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class SAppUser implements UserDetails {
	
	private String userId;
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private String accountType;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		final String rolePrefix = "ROLE_";
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (StringUtils.isBlank(this.accountType))
			return authorities;
		authorities.add(new SimpleGrantedAuthority(rolePrefix + this.accountType));
		return authorities;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
