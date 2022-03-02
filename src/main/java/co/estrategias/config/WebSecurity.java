package co.estrategias.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import co.estrategias.security.JWTAuthenticationFilter;
import co.estrategias.security.JWTAuthorizationFilter;

import static co.estrategias.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private static final String VIATICOS_NIT_URL = "/api/viaticos/PendientesById/{id}";//
    private static final String VIATICOS_ID_URL = "/api/viaticos/viaticoById";
    
    private static final String SOLICITUD = "/api/viaticos/solicitud-viaticos";
    private static final String VIATICOS = "/api/viaticos/*";
    
	private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, VIATICOS_NIT_URL).permitAll()
                .antMatchers(HttpMethod.PUT, VIATICOS_ID_URL).permitAll()
                .antMatchers(HttpMethod.GET, SOLICITUD).permitAll()
                .antMatchers(HttpMethod.PUT, SOLICITUD).permitAll()
                .antMatchers(HttpMethod.POST, SOLICITUD).permitAll()  
                .antMatchers(HttpMethod.GET, VIATICOS).permitAll()
                .antMatchers(HttpMethod.PUT, VIATICOS).permitAll()
                .antMatchers(HttpMethod.POST, VIATICOS).permitAll()   
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}