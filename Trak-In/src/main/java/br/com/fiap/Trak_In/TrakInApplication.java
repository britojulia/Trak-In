package br.com.fiap.Trak_In;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "TrackIn API", version = "1",
description = "API do Challenge do para o projeto de java TrackIn ", 
contact = @Contact(name = "Júlia Brito, Leandro Correa, Vitor", 
email = "juliabri1234@gmail.com")))
public class TrakInApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrakInApplication.class, args);
	}

}
