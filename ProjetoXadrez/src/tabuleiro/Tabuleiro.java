/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabuleiro;

/**
 *
 * @author giuli
 */
public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Pecas[][] pecas;

    /**
     * Construtor - Parâmetros: int, int
     *
     * @param linhas int - quantidade de linhas do tabuleiro
     * @param colunas int - quantidade de colunas do tabuleiro
     */
    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("O tabuleiro deve ter ao menos 1 linha e 1 coluna.");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Pecas[linhas][colunas];
    }

    /**
     * Método get do atributo linhas
     *
     * @return int - quantidade de linhas do tabuleiro
     */
    public int getLinhas() {
        return linhas;
    }

    /**
     * Método get do atributo linhas
     *
     * @return int - quantidade de colunas do tabuleiro
     */
    public int getColunas() {
        return colunas;
    }

    /**
     * Método sobrecarregado para adquirir o objeto Peca dentro da matriz de
     * peças em determinada linha e coluna.
     *
     * @param linha int - linha na matriz a ser buscada
     * @param coluna int - coluna na matriz a ser buscada
     * @return Pecas - peça alocada na linha e coluna informada
     */
    public Pecas peca(int linha, int coluna) {
        if (!posicaoExiste(linha, coluna)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return pecas[linha][coluna];
    }

    /**
     * Método sobrecarregado para adquirir o objeto Peca dentro da matriz de
     * peças em determinada posição.
     *
     * @param pos Posicao - posição na matriz a ser buscada
     * @return Pecas - peça alocada na posição informada
     */
    public Pecas peca(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return pecas[pos.getLinha()][pos.getColuna()];
    }

    /**
     * Posiciona/atribui uma peça em uma determinada posição no tabuleiro.
     *
     * @param p Pecas - peça a ser posicionada
     * @param pos Posicao - posição onde a peça será colocada
     */
    public void lugarPeca(Pecas p, Posicao pos) {
        if (temPeca(pos)) {
            throw new TabuleiroException("Já existe uma peça na posição " + pos);
        }
        pecas[pos.getLinha()][pos.getColuna()] = p;
        p.pos = pos;
    }

    /**
     * Remove a peça presente na posição informada.
     *
     * @param pos Posicao - posição da peça a ser removida
     * @return Peca - peça que foi removida
     */
    public Pecas removerPecas(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        if (!temPeca(pos)) {
            return null;
        }
        Pecas aux = peca(pos);
        aux.pos = null;
        pecas[pos.getLinha()][pos.getColuna()] = null;
        return aux;
    }

    /**
     * Verifica se a linha e coluna existem dentro do tabuleiro.
     *
     * @param linha int - linha a ser verificada
     * @param coluna int - coluna a ser verificada
     * @return boolean - true or false, se a posição existe ou não,
     * respectivamente
     */
    public boolean posicaoExiste(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    /**
     * Verifica se a posição existem dentro do tabuleiro.
     *
     * @param pos Posicao - posição a ser verificada
     * @return boolean - true or false, se a posição existe ou não,
     * respectivamente
     */
    public boolean posicaoExiste(Posicao pos) {
        return posicaoExiste(pos.getLinha(), pos.getColuna());
    }

    /**
     * Verifica se tem peça na posição informada.
     *
     * @param pos Posicao - posição do tabuleiro a ser verificada
     * @return boolean - true or false, se tem peça na posição indicada ou não,
     * respectivamente
     */
    public boolean temPeca(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return peca(pos) != null;
    }

}
