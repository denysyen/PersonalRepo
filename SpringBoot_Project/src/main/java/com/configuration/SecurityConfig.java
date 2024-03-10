// package com.configuration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
//     @Autowired
//     private PasswordEncoder passwordEncoder;
    
//     // @Override
//     // protected void configure(HttpSecurity httpSecurity) throws Exception {
//     //     // httpSecurity.csrf().disable().formLogin().loginProcessingUrl("/login")
//     //     // .and().httpBasic().and().authorizeHttpRequests().antMachers("/login").permitAll().anyRequest()
//     //     // .authenticated();
//     //     httpSecurity
// 	// 		.antMatcher("/**")
// 	// 		.authorizeRequests(authorize -> authorize
// 	// 		.anyRequest().authenticated()
// 	// 		);

//     // }

    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//         // httpSecurity.authorizeHttpRequests()
//         //     .requestMatchers("/api/users/**").hasRole("ADMIN")
//         //     .and()
//         //     .httpBasic().and()
//         //     .csrf().disable();
//         httpSecurity
//             .authorizeHttpRequests()
//                 .requestMatchers("/login").permitAll()
//                 .requestMatchers("/api/users/**").hasRole("ADMIN")
//             .and()
//             .csrf().disable()
//             .formLogin().loginProcessingUrl("/login")
//             .and()
//             .httpBasic();
//         return httpSecurity.build();
//     }

//     @Bean
//     public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//     //    UserDetails user = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
//         return new InMemoryUserDetailsManager();
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception {
//         auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
//     }
// }

package com.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired  //injection of the password created in PasswordConfig.java
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
        .formLogin()
        .loginProcessingUrl("/login")
        .and()
        .httpBasic()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/login")
        .permitAll()
        .requestMatchers("/api/users/**").hasRole("ADMIN")
        .anyRequest()
        .authenticated();
        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("admin").password(passwordEncoder
        .encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }
}