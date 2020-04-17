package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Unidade;
import xadrez.unidades.Bispo;
import xadrez.unidades.Cavalo;
import xadrez.unidades.Peao;
import xadrez.unidades.Rainha;
import xadrez.unidades.Rei;
import xadrez.unidades.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	private Integer turno;
	private Cor jogadorDaVez;
	private boolean xeque;
	private boolean xequeMate;
	private UnidadeDeXadrez vulneravelEnPassant;
	private UnidadeDeXadrez unidadePromovida;
	
	private List<Unidade> unidadesNoTabuleiro = new ArrayList<>();
	private List<Unidade> unidadesCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorDaVez = Cor.BRANCO;
		inicioDaPartidaColocarUnidades();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorDaVez() {
		return jogadorDaVez;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public UnidadeDeXadrez getVulneravelEnPassant() {
		return vulneravelEnPassant;
	}
	
	public UnidadeDeXadrez getUnidadePromovida() {
		return unidadePromovida;
	}
	
	public UnidadeDeXadrez[][] getUnidades(){
		UnidadeDeXadrez[][] matriz = new UnidadeDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int linhaDaMatriz = 0; linhaDaMatriz < tabuleiro.getLinhas(); linhaDaMatriz++) {
			for (int colunaDaMatriz = 0; colunaDaMatriz < tabuleiro.getColunas(); colunaDaMatriz++) {
				matriz[linhaDaMatriz][colunaDaMatriz] = (UnidadeDeXadrez) tabuleiro.unidade(linhaDaMatriz, colunaDaMatriz);
			}
		}
		
		return matriz;
	}
	

	public boolean[][] movimentosPossiveis(PosicaoDoXadrez posicaoDeOrigem) {
		Posicao posicao = posicaoDeOrigem.paraPosicao();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.unidade(posicao).movimentosPossiveisDasUnidades();
	}
	
	
	public UnidadeDeXadrez moverUnidadeDeXaderz(PosicaoDoXadrez posicaoDeOrigem,
												PosicaoDoXadrez posicaoDeDestino) {
		Posicao origem = posicaoDeOrigem.paraPosicao();
		Posicao destino = posicaoDeDestino.paraPosicao();
		validarPosicaoDeOrigem(origem);
		validarPosicaoAlvo(origem, destino);
		Unidade unidadeCapturada = fazerMovimento(origem, destino);
		
		if (verificarXeque(getJogadorDaVez())) {
			desfazerMovimento(origem, destino, unidadeCapturada);
			throw new XadrezExcecoes("Você não pode se colocar em xeque");
		}
		
		UnidadeDeXadrez unidadeMovida = (UnidadeDeXadrez)tabuleiro.unidade(destino);
		
		// Movimento Especial Promoção
		unidadePromovida = null;
		if (unidadeMovida instanceof Peao) {
			if ( unidadeMovida.getCor() == Cor.BRANCO && destino.getLinha() == 0 || unidadeMovida.getCor() == Cor.PRETO && destino.getLinha() == 7) {
				unidadePromovida = (UnidadeDeXadrez)tabuleiro.unidade(destino);
				unidadePromovida = trocaDeUnidadePromovida("Ra");
			}
		}
		
		xeque = (verificarXeque(oponente(jogadorDaVez))) ? true : false;
		
		if (verificarXequeMate(oponente(jogadorDaVez))) {
			xequeMate = true;
		} else {
		auternarTurnos();
		}
		
		// Movimento especial en Passant
		if (unidadeMovida instanceof Peao && (destino.getLinha() == origem.getLinha() -2 || destino.getLinha() == origem.getLinha() +2)) {
			vulneravelEnPassant = unidadeMovida;
		} else {
			vulneravelEnPassant = null;
		}
		
		return (UnidadeDeXadrez) unidadeCapturada;
		
	}
	
	public UnidadeDeXadrez trocaDeUnidadePromovida(String unidade) {
		if (unidadePromovida == null) {
			throw new IllegalStateException("Não tem unidade para ser promovida");
		}
		
		if(!unidade.equals("Tr") && !unidade.equals("Cv") && !unidade.equals("Bp") && !unidade.equals("Ra")) {
			throw new InvalidParameterException("Unidade invalida");
		}
		
		Posicao posicaoUnidadePromovida = unidadePromovida.getPosicaoDoXadrez().paraPosicao();
		Unidade peao = tabuleiro.removerUnidade(posicaoUnidadePromovida);
		unidadesNoTabuleiro.remove(peao);
		
		UnidadeDeXadrez unidadePromovidaComNovaPatente = novaPatente(unidade, unidadePromovida.getCor());
		tabuleiro.colocarUnidade(unidadePromovidaComNovaPatente, posicaoUnidadePromovida);	
		unidadesNoTabuleiro.add(unidadePromovidaComNovaPatente);
		return unidadePromovidaComNovaPatente;
	}
	
	private UnidadeDeXadrez novaPatente(String patente, Cor cor) {
		if (patente.equals("Tr")) return new Torre(tabuleiro, cor);
		if (patente.equals("Cv")) return new Cavalo(tabuleiro, cor);
		if (patente.equals("Bp")) return new Bispo(tabuleiro, cor);
		return new Rainha(tabuleiro, cor);
		
	}
	
	
	private Unidade fazerMovimento(Posicao origem, Posicao destino) {
		UnidadeDeXadrez unidadeEmMovimento = (UnidadeDeXadrez) tabuleiro.removerUnidade(origem);
		unidadeEmMovimento.adicionarMovimentoAoContador();
		Unidade unidadeCapturada = tabuleiro.removerUnidade(destino);
		tabuleiro.colocarUnidade(unidadeEmMovimento, destino);
		
		if (unidadeCapturada != null) {
			unidadesNoTabuleiro.remove(unidadeCapturada);
			unidadesCapturadas.add(unidadeCapturada);
		}
		
		// Movimento Especial Roque Pequeno
		if (unidadeEmMovimento instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemDaTorre = new Posicao(origem.getLinha(), origem.getColuna() +3);
			Posicao destinoDaTorre = new Posicao(origem.getLinha(), origem.getColuna() +1);
			UnidadeDeXadrez moverTorre = (UnidadeDeXadrez)tabuleiro.removerUnidade(origemDaTorre); 
			tabuleiro.colocarUnidade(moverTorre, destinoDaTorre);
			moverTorre.adicionarMovimentoAoContador();
		}
		
		// Movimento Especial Roque Grande
		if (unidadeEmMovimento instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemDaTorre = new Posicao(origem.getLinha(), origem.getColuna() -4);
			Posicao destinoDaTorre = new Posicao(origem.getLinha(), origem.getColuna() -1);
			UnidadeDeXadrez moverTorre = (UnidadeDeXadrez)tabuleiro.removerUnidade(origemDaTorre); 
			tabuleiro.colocarUnidade(moverTorre, destinoDaTorre);
			moverTorre.adicionarMovimentoAoContador();
		}
		
		
		// Movimento Especial en Passant
		if (unidadeEmMovimento instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && unidadeCapturada == null) {
				Posicao posicaoDoPeao;
				if (unidadeEmMovimento.getCor() == Cor.BRANCO) {
					posicaoDoPeao = new Posicao(destino.getLinha() +1, destino.getColuna());
				} else {
					posicaoDoPeao = new Posicao(destino.getLinha() -1, destino.getColuna());
				}
				unidadeCapturada = tabuleiro.removerUnidade(posicaoDoPeao);
				unidadesCapturadas.add(unidadeCapturada);
				unidadesNoTabuleiro.remove(unidadeCapturada);
			}
		}
		
		return unidadeCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Unidade unidadeCapturada) {
		UnidadeDeXadrez unidadeEmMovimento = (UnidadeDeXadrez) tabuleiro.removerUnidade(destino);
		unidadeEmMovimento.removerMovimentoDoContador();
		tabuleiro.colocarUnidade(unidadeEmMovimento, origem);
		
		if (unidadeCapturada != null) {
			tabuleiro.colocarUnidade(unidadeCapturada, destino);
			unidadesCapturadas.remove(unidadeCapturada);
			unidadesNoTabuleiro.add(unidadeCapturada);
		}
		
		// Movimento Especial Roque Pequeno
		if (unidadeEmMovimento instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemDaTorre = new Posicao(origem.getLinha(), origem.getColuna() +3);
			Posicao destinoDaTorre = new Posicao(origem.getLinha(), origem.getColuna() +1);
			UnidadeDeXadrez moverTorre = (UnidadeDeXadrez)tabuleiro.removerUnidade(destinoDaTorre); 
			tabuleiro.colocarUnidade(moverTorre, origemDaTorre);
			moverTorre.removerMovimentoDoContador();
		}
		
		// Movimento Especial Roque Grande
		if (unidadeEmMovimento instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemDaTorre = new Posicao(origem.getLinha(), origem.getColuna() -4);
			Posicao destinoDaTorre = new Posicao(origem.getLinha(), origem.getColuna() -1);
			UnidadeDeXadrez moverTorre = (UnidadeDeXadrez)tabuleiro.removerUnidade(destinoDaTorre); 
			tabuleiro.colocarUnidade(moverTorre, origemDaTorre);
			moverTorre.removerMovimentoDoContador();
		}
		
		// Movimento Especial en Passant
		if (unidadeEmMovimento instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && unidadeCapturada == vulneravelEnPassant) {
				
				UnidadeDeXadrez peao = (UnidadeDeXadrez)tabuleiro.removerUnidade(destino);
				
				Posicao posicaoDoPeao;
				if (unidadeEmMovimento.getCor() == Cor.BRANCO) {
					posicaoDoPeao = new Posicao(3, destino.getColuna());
				} else {
					posicaoDoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.colocarUnidade(peao, posicaoDoPeao);
			}
		}
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.verificarPosicaoOcupadaPorOutraUnidade(posicao)) {
			throw new XadrezExcecoes("Não há uma unidade nesta posição.");
		}
		if (jogadorDaVez != ((UnidadeDeXadrez)tabuleiro.unidade(posicao)).getCor()) {
			throw new XadrezExcecoes("Voce esta tentando mover uma unidade do adiversario, escolha uma de suas unidades");
		}
		if (!tabuleiro.unidade(posicao).verificarMovimentoPossivel()) {
			throw new XadrezExcecoes("Não existe movimento possivel para a unidade escolhida");
		}
	}
	
	private void validarPosicaoAlvo(Posicao origem, Posicao destino) {
		if (!tabuleiro.unidade(origem).movimentoPocivelDaUnidade(destino)) {
			throw new XadrezExcecoes("A unidade excolhida não pode fazer o movimento solicitado");
		}
	}
	
	private void auternarTurnos() {
		turno++;
		jogadorDaVez = (jogadorDaVez == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private UnidadeDeXadrez identificarORei(Cor cor) {
		List<Unidade> listaDeUnidades = unidadesNoTabuleiro.stream().filter(unidade -> ((UnidadeDeXadrez)unidade).getCor() == cor).collect(Collectors.toList());
		
		for (Unidade rei : listaDeUnidades) {
			if (rei instanceof Rei) {
				return (UnidadeDeXadrez) rei;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor "+ cor +" no tabuleiro");
	}
	
	private boolean verificarXeque(Cor cor) {
		Posicao posicaoDoRei = identificarORei(cor).getPosicaoDoXadrez().paraPosicao();
		List<Unidade> listaDeUnidadesDoOponente = unidadesNoTabuleiro.stream().filter(unidade -> ((UnidadeDeXadrez)unidade).getCor() == oponente(cor)).collect(Collectors.toList());
		
		for (Unidade unidadeDoOponente : listaDeUnidadesDoOponente) {
			boolean[][] matrizDeMovimentos = unidadeDoOponente.movimentosPossiveisDasUnidades();
			if (matrizDeMovimentos[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
		
	}
	
	private boolean verificarXequeMate(Cor cor) {
		if (!verificarXeque(cor)) {
			return false;
		}
		
		List<Unidade> listaDeUnidades = unidadesNoTabuleiro.stream().filter(unidade -> ((UnidadeDeXadrez)unidade).getCor() == cor).collect(Collectors.toList());
		
		for (Unidade unidade : listaDeUnidades) {
			boolean[][] matrizDeMovimentos = unidade.movimentosPossiveisDasUnidades();
			for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
				for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
					if (matrizDeMovimentos[linha][coluna]) {
						Posicao origem = ((UnidadeDeXadrez)unidade).getPosicaoDoXadrez().paraPosicao();
						Posicao destino = new Posicao(linha, coluna);
						Unidade unidadeCapturada = fazerMovimento(origem, destino);
						boolean verificarXeque = verificarXeque(cor);
						desfazerMovimento(origem, destino, unidadeCapturada);
						if (!verificarXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void colocarUnidade(char coluna, int linha, UnidadeDeXadrez unidade) {
		tabuleiro.colocarUnidade(unidade, new PosicaoDoXadrez(coluna, linha).paraPosicao());
		unidadesNoTabuleiro.add(unidade);
	}
	
	private void inicioDaPartidaColocarUnidades() {
		
		// Unidades Brancas
		colocarUnidade('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarUnidade('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarUnidade('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarUnidade('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarUnidade('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarUnidade('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarUnidade('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		
		colocarUnidade('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarUnidade('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		
		// Unidades Pretas
		colocarUnidade('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarUnidade('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarUnidade('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarUnidade('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarUnidade('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarUnidade('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarUnidade('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarUnidade('h', 8, new Torre(tabuleiro, Cor.PRETO));
		
	
		colocarUnidade('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarUnidade('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
		
		
	}
	
}
