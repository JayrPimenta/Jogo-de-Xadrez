package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Rei extends UnidadeDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	public String toString() {
		return "Re";
	}
	
	private boolean podeMover(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) getTabuleiro().unidade(posicao);
		return unidade == null || unidade.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveisDasUnidades() {
		boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoUnidade = new Posicao(0, 0);
		
		// Cima
		posicaoUnidade.setPosicao(posicao.getLinha() -1, posicao.getColuna());
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Baixo
		posicaoUnidade.setPosicao(posicao.getLinha() +1, posicao.getColuna());
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Esquerda
		posicaoUnidade.setPosicao(posicao.getLinha(), posicao.getColuna() -1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Direita
		posicaoUnidade.setPosicao(posicao.getLinha(), posicao.getColuna() +1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Cima Direita
		posicaoUnidade.setPosicao(posicao.getLinha() -1, posicao.getColuna() +1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Cima Esquerda
		posicaoUnidade.setPosicao(posicao.getLinha() -1, posicao.getColuna() -1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Baixo Direita
		posicaoUnidade.setPosicao(posicao.getLinha() +1, posicao.getColuna() +1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Baixo Esquerda
		posicaoUnidade.setPosicao(posicao.getLinha() +1, posicao.getColuna() -1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		return matrizDePociveisMovimentos;
	}
}
