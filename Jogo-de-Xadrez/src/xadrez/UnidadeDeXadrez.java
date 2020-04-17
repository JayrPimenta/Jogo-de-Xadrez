package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Unidade;

public abstract class UnidadeDeXadrez extends Unidade {

	private Cor cor;
	private int contadorDeMovimentos;

	public UnidadeDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContadorDeMovimentos() {
		return contadorDeMovimentos;
	}
	
	public void adicionarMovimentoAoContador() {
		contadorDeMovimentos++;
	}
	
	public void removerMovimentoDoContador() {
		contadorDeMovimentos--;
	}
	
	public PosicaoDoXadrez getPosicaoDoXadrez() {
		return PosicaoDoXadrez.dePosicao(posicao);
	}
	
	protected boolean verificarUnidadeDoOponente(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) getTabuleiro().unidade(posicao);
		return unidade != null && unidade.getCor() != cor;
		
	}
	
	
	
	
}
