/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class PartidaXadrez {

    private int turno;
    private Cores jogadorVez;
    private Tabuleiro tab;
    private boolean check;
    private boolean checkmate;
    private PecasXadrez enPassant;
    private PecasXadrez promocao;

    private List<Pecas> pecasTabuleiro = new ArrayList<Pecas>();
    private List<Pecas> pecasCapturadas = new ArrayList<Pecas>();
    private Set<PecasXadrez> pecasCheckmate = new HashSet<PecasXadrez>();

    public PartidaXadrez() {
        tab = new Tabuleiro(8, 8);
        turno = 1;
        jogadorVez = Cores.BRANCAS;
        iniciarPartidaTerminal();
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

    public PecasXadrez getPromocao() {
        return promocao;
    }

    public Set<PecasXadrez> getPecasCheckmate() {
        return pecasCheckmate;
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

    public boolean validarAtribuicaoOrigem(Posicao posOrigem) {
        validarPosicaoOrigem(posOrigem);
        return true;
    }

    public boolean validarAtribuicaoDestino(Posicao posOrigem, Posicao posDestino) {
        validarPosicaoDestino(posOrigem, posDestino);
        return true;
    }

    public boolean[][] movimentosPossiveisImprimir(Posicao posOrigem) {
        validarPosicaoOrigem(posOrigem);
        return tab.peca(posOrigem).movimentosPossiveis();
    }

    public boolean[][] movimentosPossiveisImprimirTerminal(PosicaoXadrez posOrigem) {
        validarPosicaoOrigem(posOrigem.posicaoTabuleiro());
        return tab.peca(posOrigem.posicaoTabuleiro()).movimentosPossiveis();
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

    public PecasXadrez movimentarPecaTerminal(PosicaoXadrez posOrigem, PosicaoXadrez posDestino) {
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

    public PecasXadrez trocarPecaPromovidaTerminal(String tipo) {
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

    private Pecas fazerMovimentoTerminal(Posicao origem, Posicao destino) {
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

    private void desfazerMovimentoTerminal(Posicao origem, Posicao destino, Pecas pecaCapturada) {
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

    public Posicao reiCheck(Cores cor) {
        return getReis(oponente(cor)).getPosicao();
    }

    private Cores oponente(Cores cor) {
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

    private boolean testeCheckTerminal(Cores cor) {
        List<Pecas> lista = pecasTabuleiro.stream().filter(x -> ((PecasXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        PecasXadrez rei = getReis(cor);

        for (Pecas p : lista) {
            boolean[][] bol = p.movimentosPossiveis();

            /*
            if (bol[rei.getPosicao().posicaoTabuleiro().getLinha()][rei.getPosicao().posicaoTabuleiro().getColuna()]) {
                return true;
            }*/
        }
        return false;
    }

    private boolean testeCheckmate(Cores cor) {
        if (!testeCheck(cor)) {
            return false;
        }
        pecasCheckmate.clear();
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

    private boolean testeCheckmateTerminal(Cores cor) {
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
                    }
                }
            }
        }
        return true;
    }

    private void pecasEnvolvidas(Cores cor) {
        List<PecasXadrez> oponente = pecasTabuleiro.stream()
                .filter(x -> x instanceof PecasXadrez && ((PecasXadrez) x).getCor() == oponente(cor))
                .map(x -> (PecasXadrez) x)
                .collect(Collectors.toList());

        List<PecasXadrez> lista = pecasTabuleiro.stream()
                .filter(x -> x instanceof PecasXadrez && ((PecasXadrez) x).getCor() == cor)
                .map(x -> (PecasXadrez) x)
                .collect(Collectors.toList());

        boolean[][] reiMov = getReis(cor).movimentosPossiveis();
        Posicao reiPos = getReis(cor).getPosicao();

        verificarMovimentosRei(oponente, reiMov, reiPos);
        verificarMovimentosPecas(lista, reiMov, reiPos, oponente, cor);
    }

    private void verificarMovimentosRei(List<PecasXadrez> oponente, boolean[][] reiMov, Posicao reiPos) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (reiMov[i][j]) {
                    Pecas aux = fazerMovimento(reiPos, new Posicao(i, j));
                    verificarAmeacas(oponente, new Posicao(i, j));
                    desfazerMovimento(reiPos, new Posicao(i, j), aux);
                }
            }
        }
    }

    private void verificarMovimentosPecas(List<PecasXadrez> lista, boolean[][] reiMov, Posicao reiPos, List<PecasXadrez> oponente, Cores cor) {
        for (PecasXadrez pe : lista) {
            if (!(pe instanceof Rei)) {
                Posicao pos = pe.getPosicao();
                Pecas pec = tab.removerPecas(pe.getPosicao());
                verificarAmeacas(oponente, reiMov, reiPos);
                tab.lugarPeca(pec, pos);
            }
        }
    }

    private void verificarAmeacas(List<PecasXadrez> oponente, Posicao pos) {
        for (PecasXadrez p : oponente) {
            if (p.getPosicao() != null) {
                boolean[][] bol = p.movimentosPossiveis();
                if (bol[pos.getLinha()][pos.getColuna()]) {
                    pecasCheckmate.add(p);
                }
            }
        }
    }

    private void verificarAmeacas(List<PecasXadrez> oponente, boolean[][] reiMov, Posicao reiPos) {
        for (PecasXadrez p : oponente) {
            boolean[][] bol = p.movimentosPossiveis();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (bol[i][j] && (reiMov[i][j] || (i == reiPos.getLinha() && j == reiPos.getColuna()))) {
                        pecasCheckmate.add(p);
                    }
                }
            }
        }
    }

    private void lugarNovaPeca(int linha, int coluna, PecasXadrez pecas) {
        tab.lugarPeca(pecas, new Posicao(linha, coluna));
        pecasTabuleiro.add(pecas);
    }

    private void lugarNovaPecaTerminal(char coluna, int linha, PecasXadrez pecas) {
        tab.lugarPeca(pecas, new PosicaoXadrez(linha, coluna).posicaoTabuleiro());
        pecasTabuleiro.add(pecas);
    }

    private void iniciarPartida() {
        //torres
        lugarNovaPeca(7, 0, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca(7, 7, new Torre(Cores.BRANCAS, tab));
        lugarNovaPeca(0, 0, new Torre(Cores.PRETAS, tab));
        lugarNovaPeca(0, 7, new Torre(Cores.PRETAS, tab));

        //reis
        lugarNovaPeca(7, 4, new Rei(Cores.BRANCAS, tab, this));
        lugarNovaPeca(0, 4, new Rei(Cores.PRETAS, tab, this));

        //peões
        for (int i = 0; i < 8; i++) {
            lugarNovaPeca(6, i, new Peao(Cores.BRANCAS, tab, this));
            lugarNovaPeca(1, i, new Peao(Cores.PRETAS, tab, this));
        }

        //bispos
        lugarNovaPeca(7, 2, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPeca(7, 5, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPeca(0, 2, new Bispo(Cores.PRETAS, tab));
        lugarNovaPeca(0, 5, new Bispo(Cores.PRETAS, tab));

        //cavalos
        lugarNovaPeca(7, 1, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPeca(7, 6, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPeca(0, 1, new Cavalo(Cores.PRETAS, tab));
        lugarNovaPeca(0, 6, new Cavalo(Cores.PRETAS, tab));

        //damas
        lugarNovaPeca(0, 3, new Dama(Cores.PRETAS, tab));
        lugarNovaPeca(7, 3, new Dama(Cores.BRANCAS, tab));

    }

    private void iniciarPartidaTerminal() {
        //torres
        lugarNovaPecaTerminal('a', 1, new Torre(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('h', 1, new Torre(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('a', 8, new Torre(Cores.PRETAS, tab));
        lugarNovaPecaTerminal('h', 8, new Torre(Cores.PRETAS, tab));

        //reis
        lugarNovaPecaTerminal('e', 1, new Rei(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('e', 8, new Rei(Cores.PRETAS, tab, this));

        //peões
        lugarNovaPecaTerminal('a', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('b', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('c', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('d', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('e', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('f', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('g', 2, new Peao(Cores.BRANCAS, tab, this));
        lugarNovaPecaTerminal('h', 2, new Peao(Cores.BRANCAS, tab, this));

        lugarNovaPecaTerminal('a', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('b', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('c', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('d', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('e', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('f', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('g', 7, new Peao(Cores.PRETAS, tab, this));
        lugarNovaPecaTerminal('h', 7, new Peao(Cores.PRETAS, tab, this));

        //bispos
        lugarNovaPecaTerminal('c', 1, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('f', 1, new Bispo(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('c', 8, new Bispo(Cores.PRETAS, tab));
        lugarNovaPecaTerminal('f', 8, new Bispo(Cores.PRETAS, tab));

        //cavalos
        lugarNovaPecaTerminal('b', 1, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('g', 1, new Cavalo(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('b', 8, new Cavalo(Cores.PRETAS, tab));
        lugarNovaPecaTerminal('g', 8, new Cavalo(Cores.PRETAS, tab));

        //damas
        lugarNovaPecaTerminal('d', 1, new Dama(Cores.BRANCAS, tab));
        lugarNovaPecaTerminal('d', 8, new Dama(Cores.PRETAS, tab));
    }
}
