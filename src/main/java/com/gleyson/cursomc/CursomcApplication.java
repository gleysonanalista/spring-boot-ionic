package com.gleyson.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Casa da moeda");
		Categoria cat2 = new Categoria(null, "Viva a Vida");
		
		Produto p1 = new Produto(null, "PS4", 2.500);
		Produto p2 = new Produto(null, "XBOX ONE", 2.000);
		Produto p3 = new Produto(null, "NINTENDO SWITCH", 2.000);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		cat.save(Arrays.asList(cat1, cat2));
		prod.save(Arrays.asList(p1, p2, p3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "Bahia");
		
		Cidade cidade1 = new Cidade(null, "Contagem", estado1);
		Cidade cidade2 = new Cidade(null, "Salvador", estado2);
		Cidade cidade3 = new Cidade(null, "Marau", estado2);
		
		estado1.getCidade().addAll(Arrays.asList(cidade1));
		estado2.getCidade().addAll(Arrays.asList(cidade2, cidade3));
		
		uf.save(Arrays.asList(estado1, estado2));
		city.save(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cli1 = new Cliente(null,"Gleyson Costa" , "gleysonanalista@gmail.com","04784225633", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("98020-8873","4545-3333"));
		
		Endereco end1 = new Endereco(null, "Av das Americas", "25", "perto da Fabrica Joselito", "Keneddy", "32145-000", cli1, cidade1);
		Endereco end2 = new Endereco(null, "Rua Agua de Colonia", "177", "perto do Posto de Saude", "Toboão da Serra", "31995-230", cli1, cidade2);
		
		cli1.getEnderecoCliente().addAll(Arrays.asList(end1, end2));
		
		cliente.save(Arrays.asList(cli1));
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


