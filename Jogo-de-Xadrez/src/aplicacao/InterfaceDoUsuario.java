package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PosicaoDoXadrez;
import xadrez.Cor;
import xadrez.UnidadeDeXadrez;

public class InterfaceDoUsuario {
	
	
	// Sistema de cores do console
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	//Metodo para limpara a tela do console
	public static void atualizarATela() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoDoXadrez lerPosicaoDaUnidade(Scanner sc) {
		try {
			String lanceDoJogador = sc.nextLine();
			char coluna = lanceDoJogador.charAt(0);
			int linha = Integer.parseInt(lanceDoJogador.substring(1));
			return new PosicaoDoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro na conversão da posição. Informe valores validos de A1 a H8.");
		}
	}
	
	public static void montarTabuleiro(UnidadeDeXadrez[][] unidades) {
		for (int linha = 0; linha < unidades.length; linha++) {
			System.out.print("#"+(8 - linha)+ " ");
			for (int coluna = 0; coluna < unidades[linha].length; coluna++) {
				colocarUnidade(unidades[linha][coluna], false);
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("    #A   #B   #C   #D   #E   #F   #G   #H");
	}
	
	public static void montarTabuleiro(UnidadeDeXadrez[][] unidades, boolean[][] movimentosPossiveis) {
		for (int linha = 0; linha < unidades.length; linha++) {
			System.out.print("#"+(8 - linha)+ " ");
			for (int coluna = 0; coluna < unidades[linha].length; coluna++) {
				colocarUnidade(unidades[linha][coluna], movimentosPossiveis[linha][coluna]);
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("    #A   #B   #C   #D   #E   #F   #G   #H");
	}
	
	public static void colocarUnidade(UnidadeDeXadrez unidade, boolean movimentosPossiveis) {
		if (movimentosPossiveis) {
			System.out.print(ANSI_CYAN_BACKGROUND);
		}
		if (unidade == null) {
			System.out.print("[  ]" + ANSI_RESET);
		} else {
			
			if (unidade.getCor() == Cor.BRANCO) {
				System.out.print("["+ANSI_WHITE + unidade + ANSI_RESET+"]");
			} else {
				System.out.print("["+ANSI_BLUE + unidade + ANSI_RESET+"]");
			}	
		}
		System.out.print(" ");
	}
	
}
