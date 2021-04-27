package com.jarvis.adminservice.config;

import com.jarvis.adminservice.exception.TokenAccessDeniedHandler;
import com.jarvis.adminservice.exception.TokenAuthenticationEntryPoint;
import com.jarvis.adminservice.filter.TokenAuthenticationFilter;
import com.jarvis.adminservice.service.UserService;
import com.jarvis.adminservice.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // 禁用 CSRF
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/admin/**").hasAuthority("admin")
//                .antMatchers("/root/**").hasAuthority("root")
//                .antMatchers("/management/**").hasAnyAuthority("admin", "root")
                .anyRequest().authenticated()
                .and()
                //添加自定义Filter
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), userService, redisUtils))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new TokenAuthenticationEntryPoint())
                .accessDeniedHandler(new TokenAccessDeniedHandler());
        // 防止H2 web 页面的Frame 被拦截
        http.headers().frameOptions().disable();
    }
}
