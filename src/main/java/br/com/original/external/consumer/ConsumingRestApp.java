package br.com.original.external.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.original.external.consumer.model.Quote;



@SpringBootApplication
public class ConsumingRestApp {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info("Resultado da Chamada REST: " + quote.toString());
        };
    }
}
	

