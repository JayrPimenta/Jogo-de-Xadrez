package aplicacao;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PosicaoDoXadrez;
import xadrez.UnidadeDeXadrez;
import xadrez.XadrezExcecoes;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<UnidadeDeXadrez> listaUnidadesCapturadas = new ArrayList<>();
		
		while(!partida.getXequeMate()) {
			try {
				InterfaceDoUsuario.atualizarATela();
				InterfaceDoUsuario.criarPartida(partida, listaUnidadesCapturadas);
				System.out.println();
				System.out.print("Unidade: ");
				
				PosicaoDoXadrez unidadeDeOrigem = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(unidadeDeOrigem);
				InterfaceDoUsuario.atualizarATela();
				InterfaceDoUsuario.montarTabuleiro(partida.getUnidades(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				
				PosicaoDoXadrez destinoDaUnidade = InterfaceDoUsuario.lerPosicaoDaUnidade(sc);
				
				UnidadeDeXadrez unidadeCapturada = partida.moverUnidadeDeXaderz(unidadeDeOrigem, destinoDaUnidade);
				
				if (unidadeCapturada != null) {
					listaUnidadesCapturadas.add(unidadeCapturada);
				}
				
				if (partida.getUnidadePromovida() != null) {
					System.out.print("Informe a promoção para a unidade Torre(Tr), Cavalo(Cv), Bispo(Bp) ou Rainha(Ra): ");
					String unidade = sc.nextLine();
					while (!unidade.equals("Tr") && !unidade.equals("Cv") && !unidade.equals("Bp") && !unidade.equals("Ra")) {
						System.out.print("Unidade invalida: Favor informar uma promoção valida para a unidade Torre(Tr), Cavalo(Cv), Bispo(Bp) ou Rainha(Ra): ");
						unidade = sc.nextLine();
					}
					partida.trocaDeUnidadePromovida(unidade);
					
				}
				
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
		InterfaceDoUsuario.atualizarATela();
		InterfaceDoUsuario.criarPartida(partida, listaUnidadesCapturadas);
		
		
		
		

	}
}
