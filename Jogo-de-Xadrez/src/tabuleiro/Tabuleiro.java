package tabuleiro;

public class Tabuleiro {
	
	private Integer linhas;
	private Integer colunas;
	private Unidade[][] unidades;
	
	public Tabuleiro(Integer linhas, Integer colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		unidades = new Unidade[linhas][colunas];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}
	
	public Unidade unidade(Integer linha, Integer coluna) {
		return unidades[linha][coluna];
	}
	
	public Unidade unidade(Posicao posicao) {
		return unidades[posicao.getLinha()][posicao.getColuna()];
	}
}
