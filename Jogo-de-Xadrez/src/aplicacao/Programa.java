package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PosicaoDoXadrez;
import xadrez.UnidadeDeXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while(true) {
			InterfaceDoUsuario.montarTabuleiro(partida.getUnidades());
			System.out.println();
			System.out.print("Unidade: ");
			
			PosicaoDoXadrez unidadeDeOrigem = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			
			PosicaoDoXadrez destinoDaUnidade = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
			
			UnidadeDeXadrez unidadeCapturada = partida.moverUnidadeDeXaderz(unidadeDeOrigem, destinoDaUnidade);
			
			
		}
		
		
		
		
		

	}
}
