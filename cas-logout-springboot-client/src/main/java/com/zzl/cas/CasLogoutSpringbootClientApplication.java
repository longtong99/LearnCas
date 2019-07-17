package com.zzl.cas;

import com.zzl.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient//开启
public class CasLogoutSpringbootClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasLogoutSpringbootClientApplication.class, args);
	}

}
