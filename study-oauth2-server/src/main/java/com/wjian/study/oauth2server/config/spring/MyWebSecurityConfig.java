package com.wjian.study.oauth2server.config.spring;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.domain.enums.StatusCode;
import com.wjian.study.oauth2server.server.impl.OAuth2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjian
 * @date 2020/6/22 0022 14:58
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // 开启方法级别上的保护
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String APPLICATION_JSON = "application/json;charset=utf-8";

    @Autowired
    private OAuth2UserDetailsService oAuth2UserDetailsService;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private MyUrlAccessDecisionManager myUrlAccessDecisionManager;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**/*.html", "/resources/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] strs = new String[ignoredUrlsProperties.getUrls().size()];
        ignoredUrlsProperties.getUrls().toArray(strs);
        http.authorizeRequests()
                .antMatchers(strs)
                .permitAll()
                //而其他的请求都需要认证
                .anyRequest()
                .authenticated()
                //设置后置处理程序对象
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        System.out.println("开始验证++++++++");
//                        o.setSecurityMetadataSource(metadataSource);
//                        o.setAccessDecisionManager(urlAccessDecisionManager);
//                        return o;
//                    }
//                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                        AuthenticationException e) throws IOException {
                        httpServletResponse.setContentType(APPLICATION_JSON);
                        BasicResponse basicResponse = new BasicResponse();
                        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.USER_LOGIN_ERROR);
                        } else if (e instanceof LockedException) {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.USER_ACCOUNT_LOCKED);
                        } else if (e instanceof CredentialsExpiredException) {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.PASSWRD_HAS_OUTTIME);
                        } else if (e instanceof AccountExpiredException) {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.USER_HAS_OUTTIME);
                        } else if (e instanceof DisabledException) {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.USER_ACCOUNT_FORBIDDEN);
                        } else {
                            basicResponse = StatusCode.getBasicResponse(StatusCode.USER_LOGIN_FAILED);
                        }
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(om.writeValueAsString(basicResponse));
                        out.flush();
                        out.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                        Authentication authentication) throws IOException {
                        initSuccessResponseJson(httpServletResponse);

                    }
                })
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                Authentication authentication) throws IOException, ServletException {
                        initSuccessResponseJson(httpServletResponse);
                    }
                })
//                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60)
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                //未登录的情况下访问所有接口都会拦截到此
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType(APPLICATION_JSON);
                        BasicResponse basicResponse = new BasicResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(om.writeValueAsString(basicResponse));
                        out.flush();
                        out.close();
                    }
                })
                //鉴权失败
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletResponse.setContentType(APPLICATION_JSON);
                        BasicResponse basicResponse = new BasicResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(om.writeValueAsString(basicResponse));
                        out.flush();
                        out.close();
                    }
                })
                //前后端分离采用JWT 不需要session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //解决不允许显示在iframe的问题
                .and().headers().frameOptions().disable();
                //过滤器链中插入自己的过滤器
                http.addFilterAt(new MyUsernamePasswordAuthenticationFilter(super.authenticationManager()), UsernamePasswordAuthenticationFilter.class);
                http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    private void initSuccessResponseJson(HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        BasicResponse basicResponse = StatusCode.getBasicResponse(StatusCode.SUCCESS);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(basicResponse));
        out.flush();
        out.close();
    }

    /**
     * 可持久化的cookie token服务
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /**
     * AuthenticationManager 的建造器，配置 AuthenticationManagerBuilder 会让Security 自动构建一个 AuthenticationManager（该类的功能参考流程图）；
     * 如果想要使用该功能你需要配置一个 UserDetailService 和 PasswordEncoder。UserDetailsService 用于在认证器中根据用户传过来的用户名查找一个用户，
     * PasswordEncoder 用于密码的加密与比对，我们存储用户密码的时候用PasswordEncoder.encode() 加密存储，在认证器里会调用 PasswordEncoder.matches()
     * 方法进行密码比对。如果重写了该方法，Security 会启用 DaoAuthenticationProvider 这个认证器，该认证就是先调用 UserDetailsService.loadUserByUsername
     * 然后使用 PasswordEncoder.matches() 进行密码比对，如果认证成功成功则返回一个 Authentication 对象
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(myAuthenticationProvider);
        auth.userDetailsService(oAuth2UserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
