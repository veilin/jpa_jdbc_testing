package com.epiuse.artifact;

import com.epiuse.datasource.RoutingDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ArtifactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtifactApplication.class, args);
    }

//    @Bean
//    @Primary
//    public DataSource defaultDataSource() {
//        RoutingDataSource routingDataSource = new RoutingDataSource();
//        Map<Object, Object> map = new HashMap<>();
//
//        //TODO: Get this from Config Database
//        map.put("vantage", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/vantage?useSSL=false").username("root").password("xpedia023").build());
//        map.put("enterprise", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/enterprise?useSSL=false").username("root").password("xpedia023").build());
//
//        routingDataSource.setTargetDataSources(map);
//        routingDataSource.setDefaultTargetDataSource(map.get("vantage"));
//        routingDataSource.afterPropertiesSet();
//
//        return routingDataSource;
//    }

}
