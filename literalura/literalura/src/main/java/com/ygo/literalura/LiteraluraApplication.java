package com.ygo.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public LiteraluraApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Conectando a la base de datos...");
		System.out.println("URL: " + dataSource.getConnection().getMetaData().getURL());
		System.out.println("Usuario: " + dataSource.getConnection().getMetaData().getUserName());
	}
}
