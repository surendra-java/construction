package org.construction.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class FlywayConfig {
    @Autowired
    private DataSource dataSource;

    @Value("${spring.flyway.locations}")
    private String locations;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        log.debug("locations{}", locations);
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .schemas("site_construction")
                .baselineOnMigrate(true) // Optional, sets baselineOnMigrate to true
                .load();
        flyway.baseline();
        flyway.migrate();
        return flyway;
    }
}
