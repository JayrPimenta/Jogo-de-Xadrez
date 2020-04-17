package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Bispo extends UnidadeDeXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "Bp";
	}

	@Override
	public boolean[][] movimentosPossiveisDasUnidades() {
boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao verificarPosicao = new Posicao(0, 0);
		
		// Cima Esquerda
		verificarPosicao.setPosicao(posicao.getLinha() -1, posicao.getColuna() -1);
		
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setPosicao(verificarPosicao.getLinha() -1, verificarPosicao.getColuna() -1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Baixo Direita
		verificarPosicao.setPosicao(posicao.getLinha() +1, posicao.getColuna() +1);
		
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setPosicao(verificarPosicao.getLinha() +1, verificarPosicao.getColuna() +1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Baixo Esquerda 
		verificarPosicao.setPosicao(posicao.getLinha() +1, posicao.getColuna() -1);
			
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setPosicao(verificarPosicao.getLinha() +1, verificarPosicao.getColuna() -1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Cima Direita
		verificarPosicao.setPosicao(posicao.getLinha() -1, posicao.getColuna() +1);
			
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setPosicao(verificarPosicao.getLinha() -1, verificarPosicao.getColuna() +1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}		
	
		return matrizDePociveisMovimentos;
	}

}
