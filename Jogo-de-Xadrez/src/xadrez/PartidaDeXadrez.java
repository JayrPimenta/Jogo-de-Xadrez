package xadrez;

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
	
	private void colocarUnidade(char coluna, int linha, UnidadeDeXadrez unidade) {
		tabuleiro.colocarUnidade(unidade, new ConversaoDePosicaoParaPadraoDoXadrez(coluna, linha).paraPosicao());
	}
	
	private void inicioDaPartidaColocarUnidades() {
		colocarUnidade('a', 8, new Torre(tabuleiro, Cor.BRANCO));
		colocarUnidade('h', 8, new Torre(tabuleiro, Cor.BRANCO));
		colocarUnidade('e', 8, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarUnidade('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		
	}
	
}
