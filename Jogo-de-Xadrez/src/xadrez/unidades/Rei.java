package xadrez.unidades;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.UnidadeDeXadrez;

public class Rei extends UnidadeDeXadrez {
	
	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}
	
	public String toString() {
		return "Re";
	}
	
	private boolean podeMover(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) getTabuleiro().unidade(posicao);
		return unidade == null || unidade.getCor() != getCor();
	}
	
	private boolean verificarTorresParaRoque(Posicao posicao) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez)getTabuleiro().unidade(posicao);
		return unidade != null && unidade instanceof Torre && unidade.getCor() == getCor() && unidade.getContadorDeMovimentos() == 0;
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
		
		// Movimento Especial Roque
		if (getContadorDeMovimentos() == 0 && !partidaDeXadrez.getXeque()) {
			
			// Movimento Especial pequeno Roque
			Posicao posicaoTorreDoRei = new Posicao(posicao.getLinha(), posicao.getColuna() +3);
			if(verificarTorresParaRoque(posicaoTorreDoRei)) {
				Posicao casaUmEntreOReiEATorre = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				Posicao casaDoisEntreOReiEATorre = new Posicao(posicao.getLinha(), posicao.getColuna() +2);
				if (getTabuleiro().unidade(casaUmEntreOReiEATorre) == null && getTabuleiro().unidade(casaDoisEntreOReiEATorre) == null) {
					matrizDePociveisMovimentos[posicao.getLinha()][posicao.getColuna() +2] = true;
				}
			}
			
			// Movimento Especial grande Roque
			Posicao posicaoTorreDaRainha = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if(verificarTorresParaRoque(posicaoTorreDaRainha)) {
				Posicao casaUmEntreOReiEATorre = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				Posicao casaDoisEntreOReiEATorre = new Posicao(posicao.getLinha(), posicao.getColuna() -2);
				Posicao casaTresEntreOReiEATorre = new Posicao(posicao.getLinha(), posicao.getColuna() -3);
				if (getTabuleiro().unidade(casaUmEntreOReiEATorre) == null && getTabuleiro().unidade(casaDoisEntreOReiEATorre) == null && getTabuleiro().unidade(casaTresEntreOReiEATorre) == null) {
					matrizDePociveisMovimentos[posicao.getLinha()][posicao.getColuna() -2] = true;
				}
			}
		}
		
		return matrizDePociveisMovimentos;
	}
}
