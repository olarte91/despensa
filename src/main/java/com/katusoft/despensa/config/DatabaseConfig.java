package com.katusoft.despensa.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public DataSource dataSource(Environment env) throws URISyntaxException {
        String url;
        String username;
        String password;

        String databaseUrl = env.getProperty("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isBlank()) {
            databaseUrl = env.getProperty("RE_DATABASE_URL");
        }

        if (databaseUrl != null && !databaseUrl.isBlank()) {
            URI uri = new URI(databaseUrl);
            String userInfo = uri.getUserInfo();
            username = userInfo != null ? userInfo.split(":", 2)[0] : null;
            password = userInfo != null && userInfo.contains(":") ? userInfo.split(":", 2)[1] : null;
            url = "jdbc:postgresql://" + uri.getHost() + ":"
                    + (uri.getPort() > 0 ? uri.getPort() : 5432) + uri.getPath()
                    + (uri.getQuery() != null ? "?" + uri.getQuery() : "");
        } else {
            url = env.getProperty("spring.datasource.url", "jdbc:postgresql://localhost:5432/despensa");
            username = env.getProperty("spring.datasource.username", "postgres");
            password = env.getProperty("spring.datasource.password", "postgres");
        }

        log.info("Conectando a la base de datos {}", url);

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}