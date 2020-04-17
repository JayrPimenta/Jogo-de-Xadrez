package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class Peao extends UnidadeDeXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Pe";
	}

	@Override
	public boolean[][] movimentosPossiveisDasUnidades() {
		boolean[][] matrizDePociveisMovimentos = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao verificarPosicao = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCO) {
			verificarPosicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() - 2, posicao.getColuna());
			Posicao verificarCaminhoDaUnidade = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().verificarExistenciaDePosicao(verificarCaminhoDaUnidade) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarCaminhoDaUnidade) && getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao) && getContadorDeMovimentos() == 0) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna() -1);
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna() +1);
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
		} else {
			verificarPosicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao)) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() + 2, posicao.getColuna());
			Posicao verificarCaminhoDaUnidade = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().verificarExistenciaDePosicao(verificarCaminhoDaUnidade) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarCaminhoDaUnidade) && getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && !getTabuleiro().verificarPosicaoOcupadaPorOutraUnidade(verificarPosicao) && getContadorDeMovimentos() == 0) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna() -1);
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
				matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
			verificarPosicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna() +1);
			if(getTabuleiro().verificarExistenciaDePosicao(verificarPosicao) && verificarUnidadeDoOponente(verificarPosicao)) {
			matrizDePociveisMovimentos[verificarPosicao.getLinha()][verificarPosicao.getColuna()] = true;
			}
			
		}
			
		
		return matrizDePociveisMovimentos;
	}
	
	
}
