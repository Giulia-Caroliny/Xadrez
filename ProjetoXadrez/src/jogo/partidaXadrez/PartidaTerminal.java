/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.partidaXadrez;

import jogo.PartidaXadrez;
import jogo.Cores;
import jogo.PecasXadrez;
import jogo.PosicaoXadrez;
import jogo.pecasXadrez.Bispo;
import jogo.pecasXadrez.Cavalo;
import jogo.pecasXadrez.Dama;
import jogo.pecasXadrez.Peao;
import jogo.pecasXadrez.Rei;
import jogo.pecasXadrez.Torre;
import tabuleiro.Posicao;

/**
 *
 * @author giuli
 */
public final class PartidaTerminal extends PartidaXadrez {

    public PartidaTerminal() {
        iniciarPartida();
    }

    public PecasXadrez movimentarPeca(PosicaoXadrez posOrigem, PosicaoXadrez posDestino) {
        Posicao origem = posOrigem.posicaoTabuleiro();
        Posicao destino = posDestino.posicaoTabuleiro();

        return super.movimentarPeca(origem, destino);
    }

    public boolean[][] movimentosPossiveisImprimir(PosicaoXadrez posOrigem) {
        validarPosicaoOrigem(posOrigem.posicaoTabuleiro());
        return getTab().peca(posOrigem.posicaoTabuleiro()).movimentosPossiveis();
    }

    private void lugarNovaPeca(char coluna, int linha, PecasXadrez pecas) {
        getTab().lugarPeca(pecas, new PosicaoXadrez(linha, coluna).posicaoTabuleiro());
        getPecasTabuleiro().add(pecas);
    }

    private void iniciarPartida() {
        //torres
        lugarNovaPeca('a', 1, new Torre(Cores.BRANCAS, getTab()));
        lugarNovaPeca('h', 1, new Torre(Cores.BRANCAS, getTab()));
        lugarNovaPeca('a', 8, new Torre(Cores.PRETAS, getTab()));
        lugarNovaPeca('h', 8, new Torre(Cores.PRETAS, getTab()));

        //reis
        lugarNovaPeca('e', 1, new Rei(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('e', 8, new Rei(Cores.PRETAS, getTab(), this));

        //peões
        lugarNovaPeca('a', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('b', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('c', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('d', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('e', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('f', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('g', 2, new Peao(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca('h', 2, new Peao(Cores.BRANCAS, getTab(), this));

        lugarNovaPeca('a', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('b', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('c', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('d', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('e', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('f', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('g', 7, new Peao(Cores.PRETAS, getTab(), this));
        lugarNovaPeca('h', 7, new Peao(Cores.PRETAS, getTab(), this));

        //bispos
        lugarNovaPeca('c', 1, new Bispo(Cores.BRANCAS, getTab()));
        lugarNovaPeca('f', 1, new Bispo(Cores.BRANCAS, getTab()));
        lugarNovaPeca('c', 8, new Bispo(Cores.PRETAS, getTab()));
        lugarNovaPeca('f', 8, new Bispo(Cores.PRETAS, getTab()));

        //cavalos
        lugarNovaPeca('b', 1, new Cavalo(Cores.BRANCAS, getTab()));
        lugarNovaPeca('g', 1, new Cavalo(Cores.BRANCAS, getTab()));
        lugarNovaPeca('b', 8, new Cavalo(Cores.PRETAS, getTab()));
        lugarNovaPeca('g', 8, new Cavalo(Cores.PRETAS, getTab()));

        //damas
        lugarNovaPeca('d', 1, new Dama(Cores.BRANCAS, getTab()));
        lugarNovaPeca('d', 8, new Dama(Cores.PRETAS, getTab()));
    }
}
