package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	@Test
	public void deveEntenderNomeEmOrdemCrescente()
	{
		//cenario
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		
		//acao
		Leilao leilao = new Leilao("Playstation 3");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//valida
		Double maiorEsperado = 400.0, menorEsperado = 250.0;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance()
	{
		//cenario
		Usuario joao = new Usuario ("Joao");
		Leilao leilao = new Leilao("Playstation 3");
		
		//acao
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//valida
		Double maiorLance = 1000.0;
		assertEquals(maiorLance, leiloeiro.getMaiorLance());
		
	}
	
	@Test
	public void deveEntenderLeilaoComTresMaioresLance()
	{
		//cenario
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");
		Usuario fernanda = new Usuario("Fernanda");
		Leilao leilao = new Leilao("Playstation 3");
		
		//acao
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(fernanda, 400.0));
		
		//valida
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List <Lance> maiores = leiloeiro.getTresMaiores(); 
		
		assertEquals(3, maiores.size());
		
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	

}
