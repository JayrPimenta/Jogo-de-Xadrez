package xadrez;

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
		
		
		
		xeque = (verificarXeque(oponente(jogadorDaVez))) ? true : false;
		
		if (verificarXequeMate(oponente(jogadorDaVez))) {
			xequeMate = true;
		} else {
		auternarTurnos();
		}
		
		return (UnidadeDeXadrez) unidadeCapturada;
		
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
		
		return unidadeCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Unidade unidadeCapturada) {
		UnidadeDeXadrez unidade = (UnidadeDeXadrez) tabuleiro.removerUnidade(destino);
		unidade.removerMovimentoDoContador();
		tabuleiro.colocarUnidade(unidade, origem);
		
		if (unidadeCapturada != null) {
			tabuleiro.colocarUnidade(unidadeCapturada, destino);
			unidadesCapturadas.remove(unidadeCapturada);
			unidadesNoTabuleiro.add(unidadeCapturada);
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
		colocarUnidade('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarUnidade('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarUnidade('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarUnidade('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		
		colocarUnidade('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarUnidade('h', 2, new Peao(tabuleiro, Cor.BRANCO));
		
		// Unidades Pretas
		colocarUnidade('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarUnidade('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarUnidade('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarUnidade('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarUnidade('e', 8, new Rei(tabuleiro, Cor.PRETO));
		colocarUnidade('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarUnidade('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarUnidade('h', 8, new Torre(tabuleiro, Cor.PRETO));
		
		colocarUnidade('a', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('b', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('c', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('d', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('e', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('f', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('g', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarUnidade('h', 7, new Peao(tabuleiro, Cor.PRETO));
	
		
	}
	
}
