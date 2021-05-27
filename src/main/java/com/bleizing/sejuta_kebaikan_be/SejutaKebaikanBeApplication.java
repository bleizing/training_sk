package com.bleizing.sejuta_kebaikan_be;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bleizing.sejuta_kebaikan_be.model.Bill;
import com.bleizing.sejuta_kebaikan_be.model.Campaign;
import com.bleizing.sejuta_kebaikan_be.model.CampaignCategory;
import com.bleizing.sejuta_kebaikan_be.model.User;
import com.bleizing.sejuta_kebaikan_be.repository.CampaignCategoryRepository;
import com.bleizing.sejuta_kebaikan_be.repository.CampaignRepository;
import com.bleizing.sejuta_kebaikan_be.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class SejutaKebaikanBeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SejutaKebaikanBeApplication.class, args);
	}
	
	@Bean
    ApplicationRunner init(CampaignCategoryRepository campaignCategoryRepository, UserRepository userRepository, CampaignRepository campaignRepository) {
        return (ApplicationArguments args) ->  initData(campaignCategoryRepository, userRepository, campaignRepository);
        
    } 
	
	private void initData(CampaignCategoryRepository campaignCategoryRepository, UserRepository userRepository, CampaignRepository campaignRepository) {
		if (!isDataLoaded(userRepository)) {
			CampaignCategory campaignCategory = initCampaignCategory(campaignCategoryRepository);
			User user = initUser(userRepository);
			initCampaign(campaignRepository, campaignCategory, user);
		}
	}
	
	private boolean isDataLoaded(UserRepository userRepository) {
		boolean isDataLoaded = false;
		
		if (userRepository.findById(1L).isPresent()) {
			isDataLoaded = true;
		}
		
		return isDataLoaded;
	}
	
	private CampaignCategory initCampaignCategory(CampaignCategoryRepository campaignCategoryRepository) {
		CampaignCategory campaignCategory = new CampaignCategory();
		campaignCategory.setCode("DNT");
		campaignCategory.setLogo("url logo");
		campaignCategory.setName("Donasi");
		campaignCategory.setActive(true);
		
		campaignCategoryRepository.save(campaignCategory);
		
		return campaignCategory;
	}
	
	private User initUser(UserRepository userRepository) {
		User user = new User();
		user.setActive(true);
		user.setName("Admin");
		user.setPhoneNumber("081234567890");
		user.setEmail("admin@test.com");
		user.setPassword("admin");
		user.setAddress("Jakarta");
		user.setChannel(1);
		
		userRepository.save(user);
		
		return user;
	}
	
	private void initCampaign(CampaignRepository campaignRepository, CampaignCategory campaignCategory, User user) {
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setTitle("Test Donasi");
		campaign.setDescription("Untuk keperluan testing");
		campaign.setTargetAmount(new BigDecimal(1000000));
		campaign.setStartTime(new Date());
		
		// convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        // manipulate date
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
        c.add(Calendar.MINUTE, 1);
        c.add(Calendar.SECOND, 1);

        // convert calendar to date
        Date endDate = c.getTime();
        
        campaign.setEndTime(endDate);
        
        campaign.setBannerImage("bannerImageUrl");
        campaign.setCampaignCategory(campaignCategory);
        campaign.setUser(user);
        
        campaignRepository.save(campaign);
	}
}
