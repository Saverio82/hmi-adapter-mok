package com.hitachirail.maas.acingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

@SpringBootApplication
@EnableSwagger2
public class AcIngestionApplication {

	public static void main(String[] args) {
		System.setProperty("application.instance.id", UUID.randomUUID().toString());
		SpringApplication.run(AcIngestionApplication.class, args);
	}

}
