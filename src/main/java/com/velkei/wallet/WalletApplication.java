package com.velkei.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class WalletApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

}
