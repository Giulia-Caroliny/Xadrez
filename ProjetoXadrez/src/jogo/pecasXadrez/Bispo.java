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
public class Bispo extends PecasXadrez {

    public Bispo(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        Posicao p = new Posicao(pos.getLinha() - 1, pos.getColuna() - 1);

        //diagonal superior esquerda
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal inferior direita
        p.setLinha(pos.getLinha() + 1);
        p.setColuna(pos.getColuna() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal inferior esquerda
        p.setLinha(pos.getLinha() + 1);
        p.setColuna(pos.getColuna() - 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal superior direita
        p.setLinha(pos.getLinha() - 1);
        p.setColuna(pos.getColuna() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        return b;
    }

    @Override
    public String toString() {
        return "B ";
    }
}
