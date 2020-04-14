package aplicacao;

import xadrez.UnidadeDeXadrez;

public class InterfaceDoUsuario {

	public static void montarTabuleiro(UnidadeDeXadrez[][] unidades) {
		for (int linha = 0; linha < unidades.length; linha++) {
			System.out.print((8 - linha)+ " ");
			for (int coluna = 0; coluna < unidades[linha].length; coluna++) {
				colocarUnidade(unidades[linha][coluna]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void colocarUnidade(UnidadeDeXadrez unidade) {
		if (unidade == null) {
			System.out.print("-");
		} else {
			System.out.print(unidade);
		}
		System.out.print(" ");
	}
	
}
