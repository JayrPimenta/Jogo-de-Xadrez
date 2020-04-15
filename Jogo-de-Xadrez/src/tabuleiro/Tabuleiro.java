package tabuleiro;

public class Tabuleiro {
	
	private Integer linhas;
	private Integer colunas;
	private Unidade[][] unidades;
	
	public Tabuleiro(Integer linhas, Integer colunas) {
		if (linhas < 1 || colunas < 1) {
		throw new TabuleiroExcecoes("Erro ao criar tabuleiro: é necessario que hja pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		unidades = new Unidade[linhas][colunas];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public Integer getColunas() {
		return colunas;
	}
	
	public Unidade unidade(Integer linha, Integer coluna) {
		if (!verificarExistenciaDePosicao(linha, coluna)) {
			throw new TabuleiroExcecoes("Posição no tabuleiro não existe");
		}
		return unidades[linha][coluna];
	}
	
	public Unidade unidade(Posicao posicao) {
		if (!verificarExistenciaDePosicao(posicao)) {
			throw new TabuleiroExcecoes("Posição no tabuleiro não existe");
		}
		return unidades[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarUnidade(Unidade unidade, Posicao posicao) {
		if (verificarPosicaoOcupadaPorOutraUnidade(posicao)) {
			throw new TabuleiroExcecoes("Posição já ocupada por outra unidade na posição " + posicao);
		}
		unidades[posicao.getLinha()][posicao.getColuna()] = unidade;
		unidade.posicao = posicao;
	}
	
	public Unidade removerUnidade(Posicao posicao) {
		if (!verificarExistenciaDePosicao(posicao)) {
			throw new TabuleiroExcecoes("Posição no tabuleiro não existe");
		}
		
		if (unidade(posicao) == null) {
			return null;
		}
		
		Unidade retirarUnidadeDoTabuleiro = unidade(posicao);
		retirarUnidadeDoTabuleiro.posicao = null;
		unidades[posicao.getLinha()][posicao.getColuna()] = null;
		
		return retirarUnidadeDoTabuleiro;
	}
	
	public boolean verificarExistenciaDePosicao(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean verificarExistenciaDePosicao(Posicao posicao) {
		return verificarExistenciaDePosicao(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean verificarPosicaoOcupadaPorOutraUnidade(Posicao posicao) {
		if (!verificarExistenciaDePosicao(posicao)) {
			throw new TabuleiroExcecoes("Posição no tabuleiro não existe");
		}
		return unidade(posicao) != null;
	}
}
