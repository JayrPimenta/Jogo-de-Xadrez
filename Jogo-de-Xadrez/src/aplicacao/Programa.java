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
