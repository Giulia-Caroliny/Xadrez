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
    
    public boolean podeMover(Posicao pos){
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null || pe.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        Posicao p = new Posicao(pos.getLinha() - 1, pos.getColuna() - 1);
        
        //diagonal superior esquerda
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //acima
        p.setColuna(pos.getColuna());
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //diagonal superior direita
        p.setColuna(pos.getColuna() + 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //direita
        p.setLinha(pos.getLinha());
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //diagonal inferior direita
        p.setLinha(pos.getLinha() + 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //abaixo
        p.setColuna(pos.getColuna());
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //diagonal inferior esquerda
        p.setColuna(pos.getColuna() - 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        //esquerda
        p.setColuna(pos.getColuna() - 1);
        p.setLinha(pos.getLinha());
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        
        return b;
        
    }

    @Override
    public String toString() {
        return "R ";
    }

}
