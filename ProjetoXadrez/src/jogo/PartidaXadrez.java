/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import java.util.ArrayList;
import java.util.List;
import jogo.pecasXadrez.Rei;
import jogo.pecasXadrez.Torre;
import tabuleiro.Pecas;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.XadrezException;

/**
 *
 * @author giuli
 */
public class PartidaXadrez {

    private int turno;
    private Cores jogadorVez;
    private Tabuleiro tab;

    private List<Pecas> pecasTabuleiro = new ArrayList<Pecas>();
    private List<Pecas> pecasCapturadas = new ArrayList<Pecas>();

    public PartidaXadrez() {
        tab = new Tabuleiro(8, 8);
        turno = 1;
        jogadorVez = Cores.BRANCAS;
        iniciarPartida();
    }

    public int getTurno() {
        return turno;
    }

    public Cores getJogadorVez() {
        return jogadorVez;
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

    public boolean[][] movimentosPossiveisImprimir(PosicaoXadrez posOrigem) {
        validarPosicaoOrigem(posOrigem.posicaoTabuleiro());
        return tab.peca(posOrigem.posicaoTabuleiro()).movimentosPossiveis();
    }

    public PecasXadrez movimentarPeca(PosicaoXadrez posOrigem, PosicaoXadrez posDestino) {
        Posicao origem = posOrigem.posicaoTabuleiro();
        Posicao destino = posDestino.posicaoTabuleiro();

        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Pecas pecaCapturada = fazerMovimento(origem, destino);

        proximoTurno();
        return (PecasXadrez) pecaCapturada;
    }

    private Pecas fazerMovimento(Posicao origem, Posicao destino) {
        Pecas aux = tab.removerPecas(origem);
        Pecas pecaCapturada = tab.removerPecas(destino);
        tab.lugarPeca(aux, destino);
        
        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao origem) {
        if (!tab.temPeca(origem)) {
            throw new XadrezException("Não tem peça na posição de origem.");
        }
        if (jogadorVez != ((PecasXadrez) tab.peca(origem)).getCor()) {
            throw new XadrezException("Não é possível mover peça de outro jogador.");
        }
        if (!tab.peca(origem).existeMovimentoPossivel()) {
            throw new XadrezException("A peça não possui movimentos disponíveis.");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tab.peca(origem).podeMovimenar(destino)) {
            throw new XadrezException("A peça não pode se mover para o destino inserido.");
        }
    }

    private void proximoTurno() {
        turno++;
        jogadorVez = (jogadorVez == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
    }

    private void lugarNovaPeca(char coluna, int linha, PecasXadrez pecas) {
        tab.lugarPeca(pecas, new PosicaoXadrez(linha, coluna).posicaoTabuleiro());
        pecasTabuleiro.add(pecas);
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
