/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import tabuleiro.Pecas;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public abstract class PecasXadrez extends Pecas {

    private Cores cor;
    private int contagemMovimentos;

    public PecasXadrez(Cores cor, Tabuleiro tab) {
        super(tab);
        this.cor = cor;
    }

    public Cores getCor() {
        return cor;
    }

    protected boolean pecaOponente(Posicao pos) {
        PecasXadrez p = (PecasXadrez) getTab().peca(pos);
        return p != null && p.getCor() != cor;
    }

    public int getContagemMovimentos() {
        return contagemMovimentos;
    }

    public void setContagemMovimentos(int contagemMovimentos) {
        this.contagemMovimentos = contagemMovimentos;
    }

}
