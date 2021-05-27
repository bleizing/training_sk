package com.bleizing.sejuta_kebaikan_be;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bleizing.sejuta_kebaikan_be.model.Bill;
import com.bleizing.sejuta_kebaikan_be.model.User;

@SpringBootApplication
@EnableJpaAuditing
public class SejutaKebaikanBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SejutaKebaikanBeApplication.class, args);
	}

}
