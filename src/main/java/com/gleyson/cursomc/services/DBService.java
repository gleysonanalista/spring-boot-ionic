package com.gleyson.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.dominio.Cidade;
import com.gleyson.cursomc.dominio.Cliente;
import com.gleyson.cursomc.dominio.Endereco;
import com.gleyson.cursomc.dominio.Estado;
import com.gleyson.cursomc.dominio.ItemPedido;
import com.gleyson.cursomc.dominio.Pagamento;
import com.gleyson.cursomc.dominio.PagamentoBoleto;
import com.gleyson.cursomc.dominio.PagamentoCartao;
import com.gleyson.cursomc.dominio.Pedido;
import com.gleyson.cursomc.dominio.Produto;
import com.gleyson.cursomc.dominio.enums.EstadoPagamento;
import com.gleyson.cursomc.dominio.enums.Perfil;
import com.gleyson.cursomc.dominio.enums.TipoCliente;
import com.gleyson.cursomc.repository.CategoriaRepositorio;
import com.gleyson.cursomc.repository.CidadeRepositorio;
import com.gleyson.cursomc.repository.ClienteRepositorio;
import com.gleyson.cursomc.repository.EnderecoRepositorio;
import com.gleyson.cursomc.repository.EstadoRepositorio;
import com.gleyson.cursomc.repository.ItemPedidoRepositorio;
import com.gleyson.cursomc.repository.PagamentoRepositorio;
import com.gleyson.cursomc.repository.PedidoRepositorio;
import com.gleyson.cursomc.repository.ProdutoRepositorio;

@Service
public class DBService  {
	
	@Autowired
	private CategoriaRepositorio cat ;
	
	@Autowired
	private ProdutoRepositorio prod;
	
	@Autowired
	private CidadeRepositorio city;
	
	@Autowired
	private EstadoRepositorio uf;
	
	@Autowired
	private ClienteRepositorio cliente;
	
	@Autowired
	private EnderecoRepositorio endereco;
	
	@Autowired
	private PedidoRepositorio pedido;
	
	@Autowired
	private PagamentoRepositorio pagamento;
	
	@Autowired
	private ItemPedidoRepositorio itemPedido;

	@Autowired
	private BCryptPasswordEncoder bp;
	
	public void instanciaDadosBanco() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Casa da moeda");
		Categoria cat2 = new Categoria(null, "Viva a Vida");
		Categoria cat3 = new Categoria(null, "Casa do Pão");
		Categoria cat4 = new Categoria(null, "Deixa Aconteçer");
		Categoria cat5 = new Categoria(null, "Dolar Alto");
		Categoria cat6 = new Categoria(null, "Euro Alto");
		Categoria cat7 = new Categoria(null, "Tudo no Brasil Aumenta");
		
		
		Produto p1 = new Produto(null, "PS4", 2.500);
		Produto p2 = new Produto(null, "XBOX ONE", 2.000);
		Produto p3 = new Produto(null, "NINTENDO SWITCH", 2.000);
		Produto p4 = new Produto(null, "SEGA SATURNO", 1.000);
		Produto p5 = new Produto(null, "SUPER NINTENDO", 800.00);
		Produto p6= new Produto(null, "NEO GEO", 500.00);
		Produto p7 = new Produto(null, "MEGA DRIVE", 400.00);
		Produto p8 = new Produto(null, "GAME BOY", 500.00);
		Produto p9 = new Produto(null, "NINTENDO 64", 300.00);
		Produto p10 = new Produto(null, "ATARI", 600.00);
		Produto p11 = new Produto(null, "XBOX 360", 500.00);
		Produto p12 = new Produto(null, "PS3", 500.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11, p12));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat7));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		p12.getCategorias().addAll(Arrays.asList(cat7));
		
		cat.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prod.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "Bahia");
		
		Cidade cidade1 = new Cidade(null, "Contagem", estado1);
		Cidade cidade2 = new Cidade(null, "Salvador", estado2);
		Cidade cidade3 = new Cidade(null, "Marau", estado2);
		
		estado1.getCidade().addAll(Arrays.asList(cidade1));
		estado2.getCidade().addAll(Arrays.asList(cidade2, cidade3));
		
		uf.save(Arrays.asList(estado1, estado2));
		city.save(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cli1 = new Cliente(null,"Gleyson Costa" , "gleysonanalista@gmail.com","04784225633", TipoCliente.PESSOAFISICA, bp.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("98020-8873","4545-3333"));
		
		Cliente cli2 = new Cliente(null,"Bruno Costa" , "brunocosta@gmail.com","41043401032", TipoCliente.PESSOAFISICA, bp.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("98020-8873","4545-3333"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco end1 = new Endereco(null, "Av das Americas", "25", "perto da Fabrica Joselito", "Keneddy", "32145-000", cli1, cidade1);
		Endereco end2 = new Endereco(null, "Rua Agua de Colonia", "177", "perto do Posto de Saude", "Toboão da Serra", "31995-230", cli1, cidade2);
		
		cli1.getEnderecoCliente().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecoCliente().addAll(Arrays.asList(end1));
		
		cliente.save(Arrays.asList(cli1, cli2));
		endereco.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyy hh:mm");
		
		Pedido pedido1 = new Pedido(null,data.parse("20/08/2019 10:32"), cli1, end1);
		Pedido pedido2 = new Pedido(null,data.parse("25/08/2019 11:32"), cli1, end2);
		Pedido pedido3 = new Pedido(null,data.parse("27/07/2019 11:32"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido1, 5);
		pedido1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PEDENTE, pedido2, data.parse("31/09/2019 11:32"), null);
		pedido2.setPagamento(pgto2);
		
		pedido.save(Arrays.asList(pedido1,pedido2));
		pagamento.save(Arrays.asList(pgto1,pgto2));
		
		ItemPedido itemP1 = new ItemPedido(pedido1, p1, 10.00, 4, 250.00);
		ItemPedido itemP2 = new ItemPedido(pedido1, p3, 15.00, 2, 400.00);
		ItemPedido itemP3 = new ItemPedido(pedido2, p2, 11.00, 3, 200.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemP1, itemP2));
		pedido2.getItens().addAll(Arrays.asList(itemP3));
		pedido3.getItens().addAll(Arrays.asList(itemP2));
		
		itemPedido.save(Arrays.asList(itemP1,itemP2,itemP3));
		
	}

}
