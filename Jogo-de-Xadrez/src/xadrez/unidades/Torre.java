package xadrez.unidades;

import tabuleiro.Posicao;
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
	
		return matrizDePociveisMovimentos;
	}
}
