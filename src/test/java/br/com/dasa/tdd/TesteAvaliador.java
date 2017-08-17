package br.com.dasa.tdd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.dasa.tdd.dominio.Lance;
import br.com.dasa.tdd.dominio.Leilao;
import br.com.dasa.tdd.dominio.Usuario;
import br.com.dasa.tdd.servico.Avaliador;

public class TesteAvaliador {

	Avaliador leiloeiro;

	@Before
	public void criaCenario() {
		leiloeiro = new Avaliador();
	}
	
	@Test
	public void testarLance() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("praystation");
		
		leilao.propoe(new Lance(joao,250.00));
		leilao.propoe(new Lance(jose,300.00));
		leilao.propoe(new Lance(maria,400.00));
		
		double minorEsperado = 250.00;
		double maiorEsperado = 400.00;
		
		
		leiloeiro.avalia(leilao);
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(minorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
    public void deveCalcularAMedia() {
        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(jose,500.0));

        // executando a acao
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
//        assertEquals(400, leiloeiro.getMedia(), 0.0001);
    }
	
	
	
	@Test
	public void unicoLance() {
		Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria, 200.0));
        leiloeiro.avalia(leilao);
        
        assertEquals(200.00, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(200.00, leiloeiro.getMenorLance(), 0.00001);

	}
	
	@Test
	public void leilaoAleatorio() {
		Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(maria, 450.0));
        leilao.propoe(new Lance(joao, 120.0));
        leilao.propoe(new Lance(maria, 700.0));
        leilao.propoe(new Lance(maria, 630.0));
        leilao.propoe(new Lance(maria, 230.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(120.00, leiloeiro.getMenorLance(), 0.00001);
        assertEquals(700.00, leiloeiro.getMaiorLance(), 0.00001);
	}
	
	@Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
	
	 @Test
	    public void deveEncontrarOsTresMaioresLances() {
	        Usuario joao = new Usuario("João");
	        Usuario maria = new Usuario("Maria");
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leilao.propoe(new Lance(joao, 100.0));
	        leilao.propoe(new Lance(maria, 200.0));
	        leilao.propoe(new Lance(joao, 300.0));
	        leilao.propoe(new Lance(maria, 400.0));

	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(3, maiores.size());
	        assertEquals(400, maiores.get(0).getValor(), 0.00001);
	        assertEquals(300, maiores.get(1).getValor(), 0.00001);
	        assertEquals(200, maiores.get(2).getValor(), 0.00001);
	    }

	    @Test
	    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
	        Usuario joao = new Usuario("João");
	        Usuario maria = new Usuario("Maria");
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leilao.propoe(new Lance(joao, 100.0));
	        leilao.propoe(new Lance(maria, 200.0));

	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(2, maiores.size());
	        assertEquals(200, maiores.get(0).getValor(), 0.00001);
	        assertEquals(100, maiores.get(1).getValor(), 0.00001);
	    }

	    @Test
	    public void deveDevolverListaVaziaCasoNaoHajaLances() {
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(0, maiores.size());
	    }
}