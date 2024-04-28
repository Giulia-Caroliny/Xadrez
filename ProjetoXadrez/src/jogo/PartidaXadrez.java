/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import jogo.pecasXadrez.Rei;
import jogo.pecasXadrez.Torre;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class PartidaXadrez {

    private Tabuleiro tab;

    public PartidaXadrez() {
        tab = new Tabuleiro(8, 8);
        iniciarPartida();
    }

    public PecasXadrez[][] getPecas() {
        PecasXadrez[][] pecaAux = new PecasXadrez[tab.getLinhas()][tab.getColunas()];

        for (int i = 0; i < tab.getLinhas(); i++) {
            for (int j = 0; j < tab.getColunas(); j++) {
                pecaAux[i][j] = (PecasXadrez) tab.peca(i, j);
            }
        }
        return pecaAux;
    }

    private void lugarNovaPeca(char coluna, int linha, PecasXadrez pecas) {
        tab.lugarPeca(pecas, new PosicaoXadrez(linha, coluna).posicaoTabuleiro());
    }

    private void iniciarPartida() {
        lugarNovaPeca('a', 8, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca('h', 8, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca('a', 1, new Torre(Cores.PRETAS, tab));
        lugarNovaPeca('h', 1, new Torre(Cores.PRETAS, tab));
        lugarNovaPeca('e', 8, new Rei(Cores.BRANCAS, tab));
        lugarNovaPeca('e', 1, new Rei(Cores.PRETAS, tab));
    }

}
