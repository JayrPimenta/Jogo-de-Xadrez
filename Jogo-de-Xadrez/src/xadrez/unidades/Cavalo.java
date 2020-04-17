package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Cavalo extends UnidadeDeXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Cv";
	}
	
	private boolean podeMover(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) getTabuleiro().unidade(posicao);
		return unidade == null || unidade.getCor() != getCor();
	}

	
	@Override
	public boolean[][] movimentosPossiveisDasUnidades() {
	boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoUnidade = new Posicao(0, 0);
		
		// Cima Direita
		posicaoUnidade.setPosicao(posicao.getLinha() -2, posicao.getColuna() +1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Cima Esqueda
		posicaoUnidade.setPosicao(posicao.getLinha() -2, posicao.getColuna() -1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Esquerda Cima
		posicaoUnidade.setPosicao(posicao.getLinha() -1, posicao.getColuna() -2);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Esquerda Baixo
		posicaoUnidade.setPosicao(posicao.getLinha() +1, posicao.getColuna() -2);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Direita Cima
		posicaoUnidade.setPosicao(posicao.getLinha() -1, posicao.getColuna() +2);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Direita Baixo
		posicaoUnidade.setPosicao(posicao.getLinha() +1, posicao.getColuna() +2);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Baixo Direita
		posicaoUnidade.setPosicao(posicao.getLinha() +2, posicao.getColuna() +1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		// Baixo Esquerda
		posicaoUnidade.setPosicao(posicao.getLinha() +2, posicao.getColuna() -1);
		if (getTabuleiro().verificarExistenciaDePosicao(posicaoUnidade) && podeMover(posicaoUnidade)) {
			matrizDePociveisMovimentos[posicaoUnidade.getLinha()][posicaoUnidade.getColuna()] = true;
		}
		
		return matrizDePociveisMovimentos;
	}

}
