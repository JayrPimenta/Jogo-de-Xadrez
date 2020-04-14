package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.Unidade;

public class UnidadeDeXadrez extends Unidade {

	private Cor cor;

	public UnidadeDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	
}
