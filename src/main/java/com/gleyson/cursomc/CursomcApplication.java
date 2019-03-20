package com.gleyson.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.dominio.Produto;
import com.gleyson.cursomc.repository.CategoriaRepositorio;
import com.gleyson.cursomc.repository.ProdutoRepositorio;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepositorio cat ;
	
	@Autowired
	private ProdutoRepositorio prod;
	
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
		
	}

}


