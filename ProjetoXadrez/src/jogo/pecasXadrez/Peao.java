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
public class Peao extends PecasXadrez {

    public Peao(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    public boolean podeMover(Posicao pos) {
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        Posicao p = new Posicao(pos.getLinha(), pos.getColuna());

        //primeiro movimento
        if (getContagemMovimentos() == 0) {
            if (getCor() == Cores.BRANCAS && podeMover(new Posicao(pos.getLinha() - 2, pos.getColuna())) && podeMover(new Posicao(pos.getLinha() - 1, pos.getColuna()))) {
                b[pos.getLinha() - 2][pos.getColuna()] = true;
            } else if (podeMover(new Posicao(pos.getLinha() + 2, pos.getColuna()))) {
                b[pos.getLinha() + 2][pos.getColuna()] = true;
            }
        }

        //mover em linha
        if (getCor() == Cores.BRANCAS) {
            p.setLinha(pos.getLinha() - 1);
            if (getTab().posicaoExiste(p) && podeMover(p)) {
                b[pos.getLinha() - 1][pos.getColuna()] = true;
            }
        } else {
            p.setLinha(pos.getLinha() + 1);
            if (getTab().posicaoExiste(p) && podeMover(p)) {
                b[pos.getLinha() + 1][pos.getColuna()] = true;
            }
        }

        //mover em diagonal
        if (getCor() == Cores.BRANCAS) {
            p.setLinha(pos.getLinha() - 1);

            p.setColuna(pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && !podeMover(p) && pecaOponente(p)) {
                b[pos.getLinha() - 1][pos.getColuna() - 1] = true;
            }

            p.setColuna(pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && !podeMover(p) && pecaOponente(p)) {
                b[pos.getLinha() - 1][pos.getColuna() + 1] = true;
            }
        } else {
            p.setLinha(pos.getLinha() + 1);

            p.setColuna(pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && !podeMover(p) && pecaOponente(p)) {
                b[pos.getLinha() - 1][pos.getColuna() - 1] = true;
            }

            p.setColuna(pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && !podeMover(p) && pecaOponente(p)) {
                b[pos.getLinha() - 1][pos.getColuna() + 1] = true;
            }
        }
        return b;
    }

    @Override
    public String toString() {
        return "P ";
    }

}
