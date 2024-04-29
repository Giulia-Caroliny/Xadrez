/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import jogo.Cores;
import jogo.PecasXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class Rei extends PecasXadrez {

    public Rei(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (getTab().temPeca(new Posicao(i, j))) {
                    b[i][j] = false;
                } else if(i <= pos.getLinha() + 1|| i >= pos.getLinha() - 1 || j <= pos.getColuna() + 1 || j >= pos.getColuna() + 1) {
                    b[i][j] = true;
                } else {
                    b[i][j] = false;
                }
            }
        }
        return b;
        
    }

    @Override
    public String toString() {
        return "R ";
    }

}
