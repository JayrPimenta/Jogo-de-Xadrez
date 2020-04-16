package xadrez.unidades;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Torre extends UnidadeDeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "Tr";
	}
	
	@Override
	public boolean[][] movimentosPociveisDasUnidades() {
		boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return matrizDePociveisMovimentos;
	}
}
