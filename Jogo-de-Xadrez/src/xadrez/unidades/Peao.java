package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.UnidadeDeXadrez;

public class Peao extends UnidadeDeXadrez {
	
	private PartidaDeXadrez partidaDeXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
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
			
			// Movimento Especial En Passant unidades brancas
			
			if (posicao.getLinha() == 3) {
				Posicao aEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().verificarExistenciaDePosicao(aEsquerda) && verificarUnidadeDoOponente(aEsquerda) && getTabuleiro().unidade(aEsquerda) == partidaDeXadrez.getVulneravelEnPassant()) {
					matrizDePociveisMovimentos[aEsquerda.getLinha() -1][aEsquerda.getColuna()] = true;
				}
				Posicao aDireita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().verificarExistenciaDePosicao(aDireita) && verificarUnidadeDoOponente(aDireita) && getTabuleiro().unidade(aDireita) == partidaDeXadrez.getVulneravelEnPassant()) {
					matrizDePociveisMovimentos[aDireita.getLinha() -1][aDireita.getColuna()] = true;
				}
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
			
			// Movimento Especial En Passant unidades pretas
			
			if (posicao.getLinha() == 4) {
				Posicao aEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().verificarExistenciaDePosicao(aEsquerda) && verificarUnidadeDoOponente(aEsquerda) && getTabuleiro().unidade(aEsquerda) == partidaDeXadrez.getVulneravelEnPassant()) {
					matrizDePociveisMovimentos[aEsquerda.getLinha() +1][aEsquerda.getColuna()] = true;
				}
				Posicao aDireita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().verificarExistenciaDePosicao(aDireita) && verificarUnidadeDoOponente(aDireita) && getTabuleiro().unidade(aDireita) == partidaDeXadrez.getVulneravelEnPassant()) {
					matrizDePociveisMovimentos[aDireita.getLinha() +1][aDireita.getColuna()] = true;
				}
			}			
		}
			
		
		return matrizDePociveisMovimentos;
	}
	
	
}
