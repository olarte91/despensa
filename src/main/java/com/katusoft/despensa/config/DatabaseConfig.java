package com.katusoft.despensa.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConditionalOnExpression("'${DATABASE_URL:}${RE_DATABASE_URL:}' != ''")
    public DataSource dataSource() throws URISyntaxException {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isBlank()) {
            databaseUrl = System.getenv("RE_DATABASE_URL");
        }

        URI uri = new URI(databaseUrl);
        String userInfo = uri.getUserInfo();
        String username = userInfo != null ? userInfo.split(":", 2)[0] : null;
        String password = userInfo != null && userInfo.contains(":") ? userInfo.split(":", 2)[1] : null;
        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + (uri.getPort() > 0 ? uri.getPort() : 5432) + uri.getPath();

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }
}