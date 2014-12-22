package be.digan.learning.christmas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ChristmasPresentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChristmasPresentsApplication.class, args);
    }
}
