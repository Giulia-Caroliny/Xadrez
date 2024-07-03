/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import tabuleiro.Posicao;
import tabuleiro.XadrezException;

/**
 *
 * @author giuli
 */
public class PosicaoXadrez {

    private int linha;
    private char coluna;

    public PosicaoXadrez(int linha, char coluna) {
        if (linha > 8 || linha < 0 || coluna > 'h' || coluna < 'a') {
            throw new XadrezException("Erro ao instanciar posição. Posições válidas estão entre a1 e h8");
        }
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    public Posicao posicaoTabuleiro() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    public static PosicaoXadrez posicaoXadrez(Posicao pos) {
        return new PosicaoXadrez(8 - pos.getLinha(), (char) ('a' + pos.getColuna()));
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }

}
