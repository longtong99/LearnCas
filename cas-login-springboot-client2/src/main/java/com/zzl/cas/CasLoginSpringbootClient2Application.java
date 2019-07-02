package com.zzl.cas;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient//开启cas
public class CasLoginSpringbootClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(CasLoginSpringbootClient2Application.class, args);
	}

}
