package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Unidade;
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
	
	public UnidadeDeXadrez moverUnidadeDeXaderz(PosicaoDoXadrez posicaoDeOrigem,
												PosicaoDoXadrez posicaoDeDestino) {
		Posicao origem = posicaoDeOrigem.paraPosicao();
		Posicao destino = posicaoDeDestino.paraPosicao();
		validarPosicaoDeOrigem(origem);
		Unidade unidadeCapturada = fazerMovimento(origem, destino);
		return (UnidadeDeXadrez) unidadeCapturada;
		
	}
	
	private Unidade fazerMovimento(Posicao origem, Posicao destino) {
		Unidade unidadeEmMovimento = tabuleiro.removerUnidade(origem);
		Unidade unidadeCapturada = tabuleiro.removerUnidade(destino);
		tabuleiro.colocarUnidade(unidadeEmMovimento, destino);
		return unidadeCapturada;
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.verificarPosicaoOcupadaPorOutraUnidade(posicao)) {
			throw new XadrezExcecoes("Não há uma unidade nesta posição.");
		}
		if (!tabuleiro.unidade(posicao).verificarMovimentoPossivel()) {
			throw new XadrezExcecoes("Não existe movimento possivel para a unidade escolhida");
		}
	}
	
	private void colocarUnidade(char coluna, int linha, UnidadeDeXadrez unidade) {
		tabuleiro.colocarUnidade(unidade, new PosicaoDoXadrez(coluna, linha).paraPosicao());
	}
	
	private void inicioDaPartidaColocarUnidades() {
		colocarUnidade('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarUnidade('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarUnidade('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarUnidade('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarUnidade('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarUnidade('e', 8, new Rei(tabuleiro, Cor.PRETO));
		
	}
	
}
