package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador 
{
	double maiorDeTodos = Double.NEGATIVE_INFINITY;
	double menorDeTodos = Double.MAX_VALUE;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao)
	{
		int i;
		
		List<Lance> lances = leilao.getLances();
		
		for(i = 0; i < lances.size(); i++)
		{
			if(maiorDeTodos < lances.get(i).getValor())
			{
				maiorDeTodos = lances.get(i).getValor();
			}
		
			if(menorDeTodos > lances.get(i).getValor())
			{
				menorDeTodos = lances.get(i).getValor();
			}
		}
		
		maiores = new ArrayList<Lance> (leilao.getLances());
		 Collections.sort(maiores, new Comparator<Lance>() {

	            public int compare(Lance o1, Lance o2) {
	                if(o1.getValor() < o2.getValor()) 
	                	return 1;
	                if(o1.getValor() > o2.getValor())
	                	return -1;
	                return 0;
	            }
	        });
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
		
	public Double getMaiorLance()
	{
		return maiorDeTodos;	
	}
	
	public Double getMenorLance()
	{
		return menorDeTodos;
	}
	
	public List<Lance> getTresMaiores()
	{
		return this.maiores;
	}
	
}

