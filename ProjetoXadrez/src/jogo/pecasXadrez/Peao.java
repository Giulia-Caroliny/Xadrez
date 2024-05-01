/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import jogo.Cores;
import jogo.PartidaXadrez;
import jogo.PecasXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class Peao extends PecasXadrez {

    private PartidaXadrez partida;

    public Peao(Cores cor, Tabuleiro tab, PartidaXadrez partida) {
        super(cor, tab);
        this.partida = partida;
    }

    public boolean podeMover(Posicao pos) {
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        //Posicao p = new Posicao(pos.getLinha(), pos.getColuna());

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cores.BRANCAS) {
            p.setValores(pos.getLinha() - 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 2, pos.getColuna());
            Posicao p2 = new Posicao(pos.getLinha() - 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2) && getContagemMovimentos() == 0) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }

            //jogada especial en passant
            if (pos.getLinha() == 3) {
                Posicao left = new Posicao(pos.getLinha(), pos.getColuna() - 1);
                if (getTab().posicaoExiste(left) && pecaOponente(left) && getTab().peca(left) == partida.getEnPassant()) {
                    b[left.getLinha() - 1][left.getColuna()] = true;
                }
                Posicao right = new Posicao(pos.getLinha(), pos.getColuna() + 1);
                if (getTab().posicaoExiste(right) && pecaOponente(right) && getTab().peca(right) == partida.getEnPassant()) {
                    b[right.getLinha() - 1][right.getColuna()] = true;
                }
            }
        } else {
            p.setValores(pos.getLinha() + 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 2, pos.getColuna());
            Posicao p2 = new Posicao(pos.getLinha() + 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2) && getContagemMovimentos() == 0) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }

            //jogada especial en passant
            if (pos.getLinha() == 4) {
                Posicao left = new Posicao(pos.getLinha(), pos.getColuna() - 1);
                if (getTab().posicaoExiste(left) && pecaOponente(left) && getTab().peca(left) == partida.getEnPassant()) {
                    b[left.getLinha() + 1][left.getColuna()] = true;
                }
                Posicao right = new Posicao(pos.getLinha(), pos.getColuna() + 1);
                if (getTab().posicaoExiste(right) && pecaOponente(right) && getTab().peca(right) == partida.getEnPassant()) {
                    b[right.getLinha() + 1][right.getColuna()] = true;
                }
            }
        }
        return b;
    }

    @Override
    public String toString() {
        return "P ";
    }

}
