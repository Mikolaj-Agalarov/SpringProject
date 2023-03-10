package org.example.config;

import org.example.session.AuthContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfig {

    @Bean
    @SessionScope
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthContext authContext() {
        return new AuthContext();
    }
}
