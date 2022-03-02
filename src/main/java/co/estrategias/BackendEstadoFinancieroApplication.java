package co.estrategias;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.discovery.shared.Application;

@RequestMapping("${app.path_rest_service}")
@EnableDiscoveryClient
@SpringBootApplication
public class BackendEstadoFinancieroApplication {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	PathBean pathBean() {
        return new PathBean();
    }
	
	public static void main(String[] args) {
      
		SpringApplication bootApp = new SpringApplication(BackendEstadoFinancieroApplication.class);		
        ConfigurableApplicationContext context = bootApp.run(args);
        PathBean pathBean = context.getBean(PathBean.class);
        pathBean.write();
	}
	
	private String getNameClass() {
		return this.getClass().getSimpleName();
	}
	
	private static class PathBean {
		@Value("${app.pathPID}")
		private String path;

		public void write() {
			BackendEstadoFinancieroApplication l = new BackendEstadoFinancieroApplication();
			SpringApplicationBuilder app = new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE);
	        app.build().addListeners(new ApplicationPidFileWriter(path + l.getNameClass() + ".pid"));
	        app.run();
		}
	}
}
