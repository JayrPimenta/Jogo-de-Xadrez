package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Unidade;

public abstract class UnidadeDeXadrez extends Unidade {

	private Cor cor;

	public UnidadeDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean verificarUnidadeDoOponente(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) getTabuleiro().unidade(posicao);
		return unidade != null && unidade.getCor() != cor;
		
	}
}
