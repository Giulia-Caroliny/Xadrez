/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabuleiro;

/**
 *
 * @author giuli
 */
public abstract class Pecas {

    protected Posicao pos;
    private Tabuleiro tab;

    /**
     * Construtor - Parâmetros: Tabuleiro.
     *
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     * @see Tabuleiro
     */
    public Pecas(Tabuleiro tab) {
        this.tab = tab;
    }

    /**
     * Método get do atributo tab
     *
     * @return Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     */
    public Tabuleiro getTab() {
        return tab;
    }

    /**
     * Método abstrato para descobrir os movimentos possíveis de uma peça.
     * Avalia todos os movimentos da peça e as posições resultantes do
     * movimento.
     *
     * @return boolean[][] - matriz de booleanos, true na posição resultante de
     * algum de seus movimentos, false, se não há movimento que chegue à posição
     */
    public abstract boolean[][] movimentosPossiveis();

    /**
     * Verifica se a peça pode se movimentar para determinada posição. Verifica
     * dentro da matriz de boleanos, retorno do método movimentosPossiveis(), se
     * a posição declarada é um movimento da peça.
     *
     * @param pos Posicao - posição na matriz
     * @return boolean - true or false, se puder se movimentar ou não,
     * respectivamente
     */
    public boolean podeMovimenar(Posicao pos) {
        return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
    }

    /**
     * Verifica se existe ao menos um movimento para peça. Percorre a matriz de
     * booleanos dos movimentos possíveis para peça.
     *
     * @return boolean - true or false, se existe movimento ou não,
     * respectivamente
     */
    public boolean existeMovimentoPossivel() {
        boolean[][] aux = movimentosPossiveis();

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                if (aux[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
