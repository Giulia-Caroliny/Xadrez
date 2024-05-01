/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jogo.pecasXadrez.Bispo;
import jogo.pecasXadrez.Cavalo;
import jogo.pecasXadrez.Dama;
import jogo.pecasXadrez.Peao;
import jogo.pecasXadrez.Rei;
import jogo.pecasXadrez.Torre;
import projetoxadrez.ViewProvisorio;
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
    private boolean check;
    private boolean checkmate;
    private PecasXadrez enPassant;

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

    public boolean isCheck() {
        return check;
    }

    public boolean isCheckmate() {
        return checkmate;
    }

    public PecasXadrez getEnPassant() {
        return enPassant;
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

        if (testeCheck(jogadorVez)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Rei não pode ser colocado em check.");
        }
        
        PecasXadrez pecaAux = (PecasXadrez) tab.peca(destino);

        check = (testeCheck(oponente(jogadorVez)));

        if (testeCheckmate(oponente(jogadorVez))) {
            checkmate = true;
        } else {
            proximoTurno();
        }

        //en passant
        if (pecaAux instanceof Peao && (destino.getLinha() == origem.getLinha() + 2 || destino.getLinha() == origem.getLinha() - 2)) {
            enPassant = pecaAux;
        } else {
            enPassant = null;
        }

        return (PecasXadrez) pecaCapturada;
    }

    private Pecas fazerMovimento(Posicao origem, Posicao destino) {
        PecasXadrez aux = (PecasXadrez) tab.removerPecas(origem);
        aux.incrementoContagemMovimentos();
        Pecas pecaCapturada = tab.removerPecas(destino);
        tab.lugarPeca(aux, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        //verificar roque
        if (aux instanceof Rei) {
            if (destino.getColuna() == origem.getColuna() + 2) {
                Posicao pOrigem = new Posicao(origem.getLinha(), origem.getColuna() + 3);
                Posicao pDestino = new Posicao(origem.getLinha(), origem.getColuna() + 1);
                fazerMovimento(pOrigem, pDestino);
            }
            if (destino.getColuna() == origem.getColuna() - 2) {
                Posicao pOrigem = new Posicao(origem.getLinha(), origem.getColuna() - 4);
                Posicao pDestino = new Posicao(origem.getLinha(), origem.getColuna() - 1);
                fazerMovimento(pOrigem, pDestino);
            }
        }

        //verificar en passant
        if (aux instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
                if (jogadorVez == Cores.BRANCAS) {
                    pecaCapturada = tab.removerPecas(new Posicao(destino.getLinha() + 1, destino.getColuna()));
                } else {
                    pecaCapturada = tab.removerPecas(new Posicao(destino.getLinha() - 1, destino.getColuna()));
                }
                pecasTabuleiro.remove(pecaCapturada);
                pecasCapturadas.add(pecaCapturada);
            }
        }

        return pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Pecas pecaCapturada) {
        PecasXadrez aux = (PecasXadrez) tab.removerPecas(destino);
        tab.lugarPeca(aux, origem);
        aux.decrementoContagemMovimentos();

        if (pecaCapturada != null) {
            tab.lugarPeca(pecaCapturada, destino);
            pecasTabuleiro.add(pecaCapturada);
            pecasCapturadas.remove(pecaCapturada);
        }

        //verificar roque
        if (aux instanceof Rei) {
            if (destino.getColuna() == origem.getColuna() + 2) {
                Posicao pOrigem = new Posicao(origem.getLinha(), origem.getColuna() + 3);
                Posicao pDestino = new Posicao(origem.getLinha(), origem.getColuna() + 1);
                desfazerMovimento(pOrigem, pDestino, pecaCapturada);
            }
            if (destino.getColuna() == origem.getColuna() - 2) {
                Posicao pOrigem = new Posicao(origem.getLinha(), origem.getColuna() - 4);
                Posicao pDestino = new Posicao(origem.getLinha(), origem.getColuna() - 1);
                desfazerMovimento(pOrigem, pDestino, pecaCapturada);
            }
        }

        //verificar en passant
        if (aux instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassant) {
                if (jogadorVez == Cores.BRANCAS) {
                    tab.removerPecas(destino);
                    tab.lugarPeca(pecaCapturada, new Posicao(destino.getLinha() + 1, destino.getColuna()));
                } else {
                    tab.removerPecas(destino);
                    tab.lugarPeca(pecaCapturada, new Posicao(destino.getLinha() - 1, destino.getColuna()));
                }
            }
        }
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

    private Cores oponente(Cores cor) {
        return (cor == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
    }

    private PecasXadrez getReis(Cores cor) {
        List<Pecas> lista = pecasTabuleiro.stream().filter(x -> ((PecasXadrez) x).getCor() == cor).collect(Collectors.toList());

        for (Pecas p : lista) {
            if (p instanceof Rei) {
                return (PecasXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe rei da cor "
                + cor
                + " no tabuleiro");
    }

    private boolean testeCheck(Cores cor) {
        List<Pecas> lista = pecasTabuleiro.stream().filter(x -> ((PecasXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        PecasXadrez rei = getReis(cor);

        for (Pecas p : lista) {
            boolean[][] bol = p.movimentosPossiveis();

            if (bol[rei.getPosicao().posicaoTabuleiro().getLinha()][rei.getPosicao().posicaoTabuleiro().getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testeCheckmate(Cores cor) {
        if (!testeCheck(cor)) {
            return false;
        }

        List<Pecas> lista = pecasTabuleiro.stream().filter(x -> ((PecasXadrez) x).getCor() == cor).collect(Collectors.toList());

        for (Pecas p : lista) {
            boolean[][] bol = p.movimentosPossiveis();

            for (int i = 0; i < tab.getLinhas(); i++) {
                for (int j = 0; j < tab.getColunas(); j++) {

                    if (bol[i][j]) {
                        Posicao origem = ((PecasXadrez) p).getPosicao().posicaoTabuleiro();
                        Posicao destino = new Posicao(i, j);
                        Pecas pecaCapturada = fazerMovimento(origem, destino);
                        boolean deuRuim = testeCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!deuRuim) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void lugarNovaPeca(char coluna, int linha, PecasXadrez pecas) {
        tab.lugarPeca(pecas, new PosicaoXadrez(linha, coluna).posicaoTabuleiro());
        pecasTabuleiro.add(pecas);
    }

    private void iniciarPartida() {
        //torres
        lugarNovaPeca('a', 1, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca('h', 1, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca('a', 8, new Torre(Cores.PRETAS, tab));
        lugarNovaPeca('h', 8, new Torre(Cores.PRETAS, tab));

        //reis
        lugarNovaPeca('e', 1, new Rei(Cores.BRANCAS, tab, this));
        lugarNovaPeca('e', 8, new Rei(Cores.PRETAS, tab, this));

        //peões
        lugarNovaPeca('a', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('b', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('c', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('d', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('e', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('f', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('g', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPeca('h', 2, new Peao(Cores.BRANCAS, tab, this));

        lugarNovaPeca('a', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('b', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('c', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('d', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('e', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('f', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('g', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPeca('h', 7, new Peao(Cores.PRETAS, tab, this));

        //bispos
        lugarNovaPeca('c', 1, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPeca('f', 1, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPeca('c', 8, new Bispo(Cores.PRETAS, tab));
        lugarNovaPeca('f', 8, new Bispo(Cores.PRETAS, tab));

        //cavalos
        lugarNovaPeca('b', 1, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPeca('g', 1, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPeca('b', 8, new Cavalo(Cores.PRETAS, tab));
        lugarNovaPeca('g', 8, new Cavalo(Cores.PRETAS, tab));

        //damas
        lugarNovaPeca('d', 1, new Dama(Cores.BRANCAS, tab));
        lugarNovaPeca('d', 8, new Dama(Cores.PRETAS, tab));
    }

}
