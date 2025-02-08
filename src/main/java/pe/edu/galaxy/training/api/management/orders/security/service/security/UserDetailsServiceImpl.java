package pe.edu.galaxy.training.api.management.orders.security.service.security;


import pe.edu.galaxy.training.api.management.orders.security.entity.UserEntity;
import pe.edu.galaxy.training.api.management.orders.security.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUserName(username);
		
		if (isNull(userEntity)) {
			throw new UsernameNotFoundException("El usuario " + username + " no existe");
		}
		
		List<SimpleGrantedAuthority> authorities= userEntity.getAuthorities().stream().map(a-> new SimpleGrantedAuthority(a.getName())).toList();
		
		UserDetails userDetails= User
				.builder()
				.username(userEntity.getUserName())
				.password(userEntity.getPassword())
				.authorities(authorities)
				.build();
		
		return userDetails;
	}

}
