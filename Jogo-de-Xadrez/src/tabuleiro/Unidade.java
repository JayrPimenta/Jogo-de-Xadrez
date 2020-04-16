package tabuleiro;

public abstract class Unidade {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Unidade(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPociveisDasUnidades();
	
	public boolean movimentoPocivelDaUnidade(Posicao posicao) {
		return movimentosPociveisDasUnidades()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean verificarMovimentoPossivel() {
		boolean[][] matrizDePociveisMovimentos = movimentosPociveisDasUnidades();
		for (int linha = 0; linha < matrizDePociveisMovimentos.length; linha++) {
			for(int coluna = 0; coluna < matrizDePociveisMovimentos[linha].length; coluna++) {
				if (matrizDePociveisMovimentos[linha][coluna]) {
					return true;
				}
			}
		}
		return false;
	}
}
