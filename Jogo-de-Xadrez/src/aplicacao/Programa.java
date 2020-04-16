package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PosicaoDoXadrez;
import xadrez.UnidadeDeXadrez;
import xadrez.XadrezExcecoes;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while(true) {
			try {
				InterfaceDoUsuario.atualizarATela();
				InterfaceDoUsuario.montarTabuleiro(partida.getUnidades());
				System.out.println();
				System.out.print("Unidade: ");
				
				PosicaoDoXadrez unidadeDeOrigem = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
				
<<<<<<< HEAD
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(unidadeDeOrigem);
				InterfaceDoUsuario.atualizarATela();
				InterfaceDoUsuario.montarTabuleiro(partida.getUnidades(), movimentosPossiveis);
=======
				boolean [][] movimentosPociveis = partida.movimentosPociveis(unidadeDeOrigem);
				InterfaceDoUsuario.atualizarATela();
				InterfaceDoUsuario.montarTabuleiro(partida.getUnidades(), movimentosPociveis);
>>>>>>> 450ec3c97d61a7a8b421fbcb6f2ca6f84d9ce4ef
				
				System.out.println();
				System.out.print("Destino: ");
				
				PosicaoDoXadrez destinoDaUnidade = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
				
				UnidadeDeXadrez unidadeCapturada = partida.moverUnidadeDeXaderz(unidadeDeOrigem, destinoDaUnidade);
			}
			catch (XadrezExcecoes e ) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
			
		}
		
		
		
		
		

	}
}
