package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Rainha extends UnidadeDeXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "Ra";
	}

	@Override
	public boolean[][] movimentosPossiveisDasUnidades() {
		boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao verificarPosicao = new Posicao(0, 0);
		
		// Cima
		verificarPosicao.setPosicao(posicao.getLinha() -1, posicao.getColuna());
		
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setLinha(verificarPosicao.getLinha() -1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Baixo
		verificarPosicao.setPosicao(posicao.getLinha() +1, posicao.getColuna());
		
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setLinha(verificarPosicao.getLinha() +1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Esquerda
		verificarPosicao.setPosicao(posicao.getLinha(), posicao.getColuna() -1);
			
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setColuna(verificarPosicao.getColuna() -1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
		// Direita
		verificarPosicao.setPosicao(posicao.getLinha(), posicao.getColuna() +1);
			
		while (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			verificarPosicao.setColuna(verificarPosicao.getColuna() +1);
		}
		
		if (getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
		}
		
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
