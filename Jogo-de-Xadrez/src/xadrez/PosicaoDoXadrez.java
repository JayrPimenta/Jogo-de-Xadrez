package xadrez;

import tabuleiro.Posicao;

public class PosicaoDoXadrez {
	
	private Integer linha;
	private char coluna;
	
	public PosicaoDoXadrez(char coluna, Integer linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezExcecoes("Erro na conversão da posição. Informe valores validos de A1 a H8.");
		}
		this.linha = linha;
		this.coluna = coluna;
	}

	public Integer getLinha() {
		return linha;
	}

	public char getColuna() {
		return coluna;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoDoXadrez dePosicao(Posicao posicao) {
		return new PosicaoDoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" +coluna + linha;
	}
}
