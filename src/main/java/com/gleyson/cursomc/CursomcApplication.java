package com.gleyson.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gleyson.cursomc.dominio.Categoria;
import com.gleyson.cursomc.repository.CategoriaRepositorio;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepositorio cat ;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria dado1 = new Categoria(1, "Casa da moeda");
		Categoria dado2 = new Categoria(null, "Viva a Vida");
		
		cat.save(Arrays.asList(dado1, dado2));
		
	}

}


