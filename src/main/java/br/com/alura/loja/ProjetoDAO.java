package br.com.alura.loja;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoDAO {
	
	private static Set<Projeto> banco = new HashSet<Projeto>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Projeto worker = new Projeto(1L, "Paulo André Moreira Cruz", 2016);
		banco.add(worker);
	}
	
	public void adiciona(Projeto projeto) {
		banco.add(projeto);
	}
	
	public Projeto busca(Long id) {
		for (Projeto projeto : banco) {
			if (projeto.getId() == id) {
				return projeto;
			}
		}
		return null;
	}
	
	public boolean remove(long id) {
		for (Projeto projeto : banco) {
			if (projeto.getId() == id) {
				return banco.remove(projeto);
			}
		}
		return false;
	}

}
