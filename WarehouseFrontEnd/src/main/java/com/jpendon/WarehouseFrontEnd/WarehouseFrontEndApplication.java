package com.jpendon.WarehouseFrontEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WarehouseFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseFrontEndApplication.class, args);
	}

}
