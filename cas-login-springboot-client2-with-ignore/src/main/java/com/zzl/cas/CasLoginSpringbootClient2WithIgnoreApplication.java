package com.zzl.cas;

import com.zzl.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class CasLoginSpringbootClient2WithIgnoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasLoginSpringbootClient2WithIgnoreApplication.class, args);
	}

}
