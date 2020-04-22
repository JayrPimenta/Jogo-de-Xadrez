package tabuleiro;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import xadrez.Cor;
import xadrez.unidades.Torre;

public class TabuleiroTest {

	
	@Test
	@Order(2)
	public void deveriaLancarExceptionPosicaoNaoExiste() {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Unidade unidade = new Torre(tabuleiro, Cor.BRANCO);
		Posicao posicao = new Posicao(8, 1);
		
		Exception exception = assertThrows(TabuleiroExcecoes.class, () ->
		
			tabuleiro.colocarUnidade(unidade, posicao));
		
		assertEquals("Posição no tabuleiro não existe", exception.getMessage());
	}
	
	@Test
	@Order(1)
	public void deveriaColocarUnidade() {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Posicao posicao = new Posicao(7, 1);
		
		tabuleiro.colocarUnidade(new Torre(tabuleiro, Cor.BRANCO), posicao);
		
		Unidade unidade = tabuleiro.unidade(posicao);
		
		assertEquals("Tr", unidade.toString());
	}
	
	@Test
	@Order(3)
	public void deveriaVerificarSePosicaoExiste() {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Posicao posicao = new Posicao(7, 1);

		assertTrue(tabuleiro.verificarExistenciaDePosicao(posicao));
	}
	
	@Test
	@Order(3)
	public void deveriaVerificarQuePosicaoNaoExiste() {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Posicao posicao = new Posicao(8, 1);

		assertFalse(tabuleiro.verificarExistenciaDePosicao(posicao));
	}
}
