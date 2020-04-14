package tabuleiro;

public class Unidade {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Unidade(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
	
	
}
