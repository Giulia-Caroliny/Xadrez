/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class PartidaXadrez {
    private Tabuleiro tab;

    public PartidaXadrez(){
        tab = new Tabuleiro(8, 8);
    }
    
    public PecasXadrez[][] getPecas(){
        PecasXadrez[][] pecaAux = new PecasXadrez[tab.getLinhas()][tab.getColunas()];
        
        for (int i = 0; i < tab.getLinhas(); i++) {
            for (int j = 0; j < tab.getColunas(); j++) {
                pecaAux[i][j] = (PecasXadrez) tab.peca(i, j);
            }
        }
        return pecaAux;
    }
    
    
    
}
