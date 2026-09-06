package com.katusoft.despensa.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public DataSource dataSource(DataSourceProperties properties) throws URISyntaxException {
        String databaseUrl = env("DATABASE_URL");
        if (databaseUrl == null) {
            databaseUrl = env("RE_DATABASE_URL");
        }

        if (databaseUrl == null) {
            log.info("DATABASE_URL no definida; usando properties locales");
            return properties.initializeDataSourceBuilder().build();
        }

        URI uri = new URI(databaseUrl);
        String userInfo = uri.getUserInfo();
        String username = userInfo != null ? userInfo.split(":", 2)[0] : null;
        String password = userInfo != null && userInfo.contains(":") ? userInfo.split(":", 2)[1] : null;
        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":"
                + (uri.getPort() > 0 ? uri.getPort() : 5432) + uri.getPath()
                + (uri.getQuery() != null ? "?" + uri.getQuery() : "");

        log.info("Conectando a la base de datos {}", uri.getHost());

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }

    private static String env(String name) {
        String value = System.getenv(name);
        return value != null && !value.isBlank() ? value : null;
    }
}