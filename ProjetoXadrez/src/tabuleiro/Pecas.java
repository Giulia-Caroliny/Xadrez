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

    public Pecas(Tabuleiro tab) {
        this.tab = tab;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public abstract boolean[][] movimentosPossiveis();

    public boolean podeMovimenar(Posicao pos) {
        return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
    }

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
