package br.com.dasa.tdd.builder;

import br.com.dasa.tdd.dominio.Lance;
import br.com.dasa.tdd.dominio.Leilao;
import br.com.dasa.tdd.dominio.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;

    public CriadorDeLeilao() { }

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
    	   	if(valor < 0) {
    	   		throw new IllegalArgumentException("Não deve haver valor negativo no lance");
    	   	}
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() { 
        return leilao;
    }
}
