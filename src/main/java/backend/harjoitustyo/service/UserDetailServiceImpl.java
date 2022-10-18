package backend.harjoitustyo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.harjoitustyo.domain.AppUser;
import backend.harjoitustyo.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	private final AppUserRepository userRepository;
	
	@Autowired
	public UserDetailServiceImpl(AppUserRepository appUserRepository) {
		this.userRepository = appUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: " + username);
		AppUser currentUser = userRepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
}
