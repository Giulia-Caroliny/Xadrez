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

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Pecas[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Pecas peca(int linha, int coluna) {
        return pecas[linha][coluna];
    }

    public Pecas peca(Posicao pos) {
        return pecas[pos.getLinha()][pos.getColuna()];
    }

    public void lugarPeca(Pecas p, Posicao pos) {

    }

    public Pecas removerPecas(Posicao pos) {
        return null;
    }

    public boolean posicaoExiste(Posicao pos) {
        return true;
    }

    public boolean temPeca(Posicao pos) {
        return true;
    }

}
