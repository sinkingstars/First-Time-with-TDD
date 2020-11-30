package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		int totalDePropostas = qtdDeLancesDoUsuario(lance.getUsuario());
		
		if(totalDePropostas >= 5)
			return;
	
		if(lances.isEmpty())
			lances.add(lance);
		else if(podeDarLanceUsuario(lance.getUsuario()))
			lances.add(lance);
	}

	private boolean podeDarLanceUsuario(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario);
	}

	private int qtdDeLancesDoUsuario(Usuario usuario) {
		int i, totalDePropostas = 0;
		for(i = 0; i < lances.size(); i++)
		{
			Lance lanceAtual = lances.get(i);
			if(lanceAtual.getUsuario().equals(usuario))
				totalDePropostas++;
		}
		return totalDePropostas;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
	
}
