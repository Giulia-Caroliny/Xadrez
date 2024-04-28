/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import jogo.Cores;
import jogo.PecasXadrez;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class Torre extends PecasXadrez {

    public Torre(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    @Override
    public boolean movimentosPossiveis() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "T ";
    }

}
