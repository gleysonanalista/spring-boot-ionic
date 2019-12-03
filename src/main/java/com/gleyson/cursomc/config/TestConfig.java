package com.gleyson.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gleyson.cursomc.services.DBService;
import com.gleyson.cursomc.services.EmailService;
import com.gleyson.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciaBanco() throws ParseException {
		dbService.instanciaDadosBanco();
		return true;
	}
	
	@Bean
	public EmailService emailServic() {
		return new MockEmailService();
	}
}
