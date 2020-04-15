package xadrez.unidades;

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
}
