package portafilio.securemessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication( exclude={ DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class SecuremessageApplication {

	private static final int STRENGHT = 12;

	public static void main(String[] args) {
		SpringApplication.run(SecuremessageApplication.class, args);
	}

	@Bean
	public  BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(STRENGHT);
	}


}
