package com.gleyson.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.gleyson.cursomc.dominio.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
