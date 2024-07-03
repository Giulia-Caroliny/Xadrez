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
import tabuleiro.Pecas;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.XadrezException;

/**
 *
 * @author giuli
 */
public abstract class PartidaXadrez {

    private int turno = 1;
    private Cores jogadorVez = Cores.BRANCAS;
    private Tabuleiro tab = new Tabuleiro(8, 8);
    private boolean check;
    private boolean checkmate;
    private PecasXadrez enPassant;
    private PecasXadrez promocao;

    private List<Pecas> pecasTabuleiro = new ArrayList<Pecas>();
    private List<Pecas> pecasCapturadas = new ArrayList<Pecas>();

    public int getTurno() {
        return turno;
    }

    public Cores getJogadorVez() {
        return jogadorVez;
    }

    public Tabuleiro getTab() {
        return tab;
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

    public PecasXadrez getPromocao() {
        return promocao;
    }

    public List<Pecas> getPecasTabuleiro() {
        return pecasTabuleiro;
    }

    public List<Pecas> getPecasCapturadas() {
        return pecasCapturadas;
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

    public PecasXadrez movimentarPeca(Posicao origem, Posicao destino) {
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Pecas pecaCapturada = fazerMovimento(origem, destino);

        if (testeCheck(jogadorVez)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Rei não pode ser colocado em check.");
        }

        PecasXadrez pecaAux = (PecasXadrez) tab.peca(destino);

        //Promoção
        promocao = null;
        if (pecaAux instanceof Peao) {
            if (destino.getLinha() == 0 && pecaAux.getCor() == Cores.BRANCAS || destino.getLinha() == 7 && pecaAux.getCor() == Cores.PRETAS) {
                promocao = (PecasXadrez) tab.peca(destino);
            }
        }
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

    public PecasXadrez trocarPecaPromovida(String tipo) {
        if (promocao == null) {
            throw new IllegalStateException("Não há peça para ser promovida.");
        }
        if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("D") && !tipo.equals("T")) {
            return trocarPecaPromovida("D");
        }

        Posicao pProm = promocao.getPosicao();
        pecasTabuleiro.remove(tab.removerPecas(pProm));
        PecasXadrez p;

        switch (tipo) {
            case "B":
                p = new Bispo(promocao.getCor(), tab);
                tab.lugarPeca(p, pProm);
                pecasTabuleiro.add(p);
                break;
            case "C":
                p = new Cavalo(promocao.getCor(), tab);
                tab.lugarPeca(p, pProm);
                pecasTabuleiro.add(p);
                break;
            case "D":
                p = new Dama(promocao.getCor(), tab);
                tab.lugarPeca(p, pProm);
                pecasTabuleiro.add(p);
                break;
            default:
                p = new Torre(promocao.getCor(), tab);
                tab.lugarPeca(p, pProm);
                pecasTabuleiro.add(p);
        }

        return p;
    }

    protected Pecas fazerMovimento(Posicao origem, Posicao destino) {
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

    protected void desfazerMovimento(Posicao origem, Posicao destino, Pecas pecaCapturada) {
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

    protected void validarPosicaoOrigem(Posicao origem) {
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

    protected void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tab.peca(origem).podeMovimenar(destino)) {
            throw new XadrezException("A peça não pode se mover para o destino inserido.");
        }
    }

    private void proximoTurno() {
        turno++;
        jogadorVez = (jogadorVez == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
    }

    protected Cores oponente(Cores cor) {
        return (cor == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
    }

    public PecasXadrez getReis(Cores cor) {
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

            if (bol[rei.getPosicao().getLinha()][rei.getPosicao().getColuna()]) {
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
                        Posicao origem = ((PecasXadrez) p).getPosicao();
                        Posicao destino = new Posicao(i, j);
                        Pecas pecaCapturada = fazerMovimento(origem, destino);
                        boolean deuRuim = testeCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!deuRuim) {
                            return false;
                        }
                        pecasEnvolvidas(cor);
                    }
                }
            }
        }
        return true;
    }

    protected void pecasEnvolvidas(Cores cor) {
    }
}
