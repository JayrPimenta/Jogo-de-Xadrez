package aplicacao;

import xadrez.PartidaDeXadrez;

public class Programa {

	public static void main(String[] args) {

		PartidaDeXadrez partida = new PartidaDeXadrez();
		InterfaceDoUsuario.montarTabuleiro(partida.getUnidades());

	}

}
