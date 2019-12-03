package com.gleyson.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.ItemPedido;
import com.gleyson.cursomc.dominio.PagamentoBoleto;
import com.gleyson.cursomc.dominio.Pedido;
import com.gleyson.cursomc.dominio.enums.EstadoPagamento;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.repository.ItemPedidoRepositorio;
import com.gleyson.cursomc.repository.PagamentoRepositorio;
import com.gleyson.cursomc.repository.PedidoRepositorio;
import com.gleyson.cursomc.repository.ProdutoRepositorio;
import com.gleyson.cursomc.services.excecap.MessagensExcecao;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repositorio;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepositorio pgRepositorio;
	
	@Autowired
	private ProdutoRepositorio prodRepositorio;
	
	
	@Autowired
	private ItemPedidoRepositorio itemRepositorio;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	
	public Pedido buscar(Integer id) {
		
		Pedido dados = repositorio.findOne(id);
		if(dados == null) {
			throw new MessagensExcecao("Objeto não encontrado, id:" + id + ",Tipo" + Pedido.class.getName());
		}
		
		return dados;
	}
	
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pgto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pgto, obj.getInstante());
		}
		
		obj = repositorio.save(obj);
		 pgRepositorio.save(obj.getPagamento());
		 
		 for(ItemPedido ip: obj.getItens()) {
			 ip.setDesconto(0.0);
			 ip.setProduto(prodRepositorio.findOne(ip.getProduto().getId()));
			 ip.setPreco(ip.getProduto().getPreco());
			 ip.setPedido(obj);
		 }
		 itemRepositorio.save(obj.getItens());
		 emailService.sendOrderConfirmationHtmlEmail(obj);
		 return obj;
	}
}
