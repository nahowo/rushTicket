package com.nahowo.rushTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(basePackages = {"com.nahowo.rushTicket.domain"})
@PropertySource("classpath:env.properties")
@SpringBootApplication
public class RushTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(RushTicketApplication.class, args);
    }

}
