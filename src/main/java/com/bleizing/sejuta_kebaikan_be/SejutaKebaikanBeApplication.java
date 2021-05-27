package com.bleizing.sejuta_kebaikan_be;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bleizing.sejuta_kebaikan_be.model.Bill;
import com.bleizing.sejuta_kebaikan_be.model.Campaign;
import com.bleizing.sejuta_kebaikan_be.model.User;
import com.bleizing.sejuta_kebaikan_be.repository.CampaignRepository;
import com.bleizing.sejuta_kebaikan_be.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class SejutaKebaikanBeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SejutaKebaikanBeApplication.class, args);
	}
	
	@Bean
    ApplicationRunner init(UserRepository repository) {
        return (ApplicationArguments args) ->  setup(repository);
    } 
	
	private void setup(UserRepository userRepository) {
		User user = null;
		try {
			user = userRepository.findById(1L).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (user == null) {
				user = new User();
				user.setActive(true);
				user.setName("Admin");
				user.setPhoneNumber("081234567890");
				user.setEmail("admin@test.com");
				user.setPassword("admin");
				user.setAddress("Jakarta");
				
				userRepository.save(user);
			}
		}
	}
}
