package co.estrategias.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.estrategias.daos.ApplicationUserRepository;
import co.estrategias.entities.BsUsuario;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;
    
    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	BsUsuario applicationUser = applicationUserRepository.findByUsualogi(username);
    	
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        
        return new User(applicationUser.getUsualogi(), applicationUser.getPassword(), emptyList());
    }
}