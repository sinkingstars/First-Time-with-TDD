package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class leilaoTest {
	@Test
	public void devoReceberUmLance()
	{
		Leilao leilao = new Leilao("Mac Book Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Joao"), 2000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances()
	{
		Leilao leilao = new Leilao("MacBook Pro 15");
		leilao.propoe(new Lance(new Usuario("Joao"), 2000.0));
		leilao.propoe(new Lance(new Usuario("Jose"), 3000.0));
		leilao.propoe(new Lance(new Usuario("Maria"), 4000.0));
	
		assertEquals(3, leilao.getLances().size());
		assertEquals(2000.0,leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0,leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(4000.0,leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario()
	{
		Leilao leilao = new Leilao("Mac Book Pro 15");
		Usuario joao = new Usuario("Joao");
		
		leilao.propoe(new Lance(joao, 2000.0));
		leilao.propoe(new Lance(joao, 3000.0));
		
		assertEquals(1,leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario()
	{
		//cenario
		Leilao leilao = new Leilao("Mac Book Pro 15");
		Usuario joao = new Usuario ("Joao");
		Usuario maria = new Usuario("Maria");
		
		//acao
		leilao.propoe(new Lance(joao, 2000.0));
		leilao.propoe(new Lance(maria, 3000.0));

		leilao.propoe(new Lance(joao, 4000.0));
		leilao.propoe(new Lance(maria, 5000.0));
		
		leilao.propoe(new Lance(joao, 6000.0));
		leilao.propoe(new Lance(maria, 7000.0));
		
		leilao.propoe(new Lance(joao, 8000.0));
		leilao.propoe(new Lance(maria, 9000.0));
		
		leilao.propoe(new Lance(joao, 10000.0));
		leilao.propoe(new Lance(maria, 11000.0));
		
		//valida
		leilao.propoe(new Lance(joao, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
}
