/**
 * Projeto das trilhas de treinamento de Java b�sico ou avan�ado 
 * com foco nas certifica��es java e em treinamentos corporativos. 
 * Fontes dispon�veis em https://github.com/rodrigofujioka
 * 
 * Professor: Rodrigo da Cruz Fujioka
 * Ano: 2016
 * http://www.rodrigofujioka.com
 * http://www.fujideia.com.br
 * http://lattes.cnpq.br/0843668802633139
 * 
 * Contato: rcf4@cin.ufpe.br 
 */

/*
 * Nome dos integrantes do grupo:
 * 
 * Mateus da Costa Cavalcanti - 1620016111
 * Matheus Tavares de Lima Coelho - 12620016108
 * Thales Henrique Gomes Bezerra- 1620016632
 * Thiago Henrique Coelho Tavares da Silva - 1620017572
 * Anderson Marcos Eleotério da Silva - 1620017115
 * */


package br.unipe.tad.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"br.unipe.tad.controller","br.unipe.tad.boot"})
@EntityScan({"br.unipe.tad.model"})
@EnableJpaRepositories(basePackages = {"br.unipe.tad.repository"})
public class Aplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Aplication.class, args);
	}

}
