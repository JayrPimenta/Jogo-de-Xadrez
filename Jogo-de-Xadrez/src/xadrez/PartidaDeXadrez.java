package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.unidades.Rei;
import xadrez.unidades.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicioDaPartidaColocarUnidades();
	}
	
	public UnidadeDeXadrez[][] getUnidades(){
		UnidadeDeXadrez[][] matriz = new UnidadeDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int linhaDaMatriz = 0; linhaDaMatriz < tabuleiro.getLinhas(); linhaDaMatriz++) {
			for (int colunaDaMatriz = 0; colunaDaMatriz < tabuleiro.getColunas(); colunaDaMatriz++) {
				matriz[linhaDaMatriz][colunaDaMatriz] = (UnidadeDeXadrez) tabuleiro.unidade(linhaDaMatriz, colunaDaMatriz);
			}
		}
		
		return matriz;
	}
	
	private void inicioDaPartidaColocarUnidades() {
		tabuleiro.colocarUnidade(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 0));
		tabuleiro.colocarUnidade(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 7));
		tabuleiro.colocarUnidade(new Rei(tabuleiro, Cor.BRANCO),new Posicao(0,4));
		
		tabuleiro.colocarUnidade(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 0));
		tabuleiro.colocarUnidade(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 7));
		tabuleiro.colocarUnidade(new Rei(tabuleiro, Cor.BRANCO),new Posicao(7,4));
	}
	
}
