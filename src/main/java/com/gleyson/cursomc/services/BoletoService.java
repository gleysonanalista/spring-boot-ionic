package com.gleyson.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.PagamentoBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoBoleto(PagamentoBoleto pgto, Date instanteDoPedido ) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}

}
