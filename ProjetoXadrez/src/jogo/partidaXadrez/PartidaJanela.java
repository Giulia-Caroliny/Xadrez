/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.partidaXadrez;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jogo.PartidaXadrez;
import jogo.Cores;
import jogo.PecasXadrez;
import jogo.pecasXadrez.Bispo;
import jogo.pecasXadrez.Cavalo;
import jogo.pecasXadrez.Dama;
import jogo.pecasXadrez.Peao;
import jogo.pecasXadrez.Rei;
import jogo.pecasXadrez.Torre;
import tabuleiro.Pecas;
import tabuleiro.Posicao;

/**
 *
 * @author giuli
 */
public final class PartidaJanela extends PartidaXadrez {

    private Set<PecasXadrez> pecasCheckmate = new HashSet<PecasXadrez>();

    public PartidaJanela() {
        iniciarPartida();
    }

    public Set<PecasXadrez> getPecasCheckmate() {
        return pecasCheckmate;
    }

    public boolean[][] movimentosPossiveisImprimir(Posicao posOrigem) {
        validarPosicaoOrigem(posOrigem);
        return getTab().peca(posOrigem).movimentosPossiveis();
    }

    public boolean validarAtribuicaoOrigem(Posicao posOrigem) {
        validarPosicaoOrigem(posOrigem);
        return true;
    }

    public boolean validarAtribuicaoDestino(Posicao posOrigem, Posicao posDestino) {
        validarPosicaoDestino(posOrigem, posDestino);
        return true;
    }

    public void limparSetCheckmate() {
        pecasCheckmate.clear();
    }

    public Posicao reiCheck(Cores cor) {
        return getReis(oponente(cor)).getPosicao();
    }

    @Override
    protected void pecasEnvolvidas(Cores cor) {
        List<PecasXadrez> oponente = getPecasTabuleiro().stream()
                .filter(x -> x instanceof PecasXadrez && ((PecasXadrez) x).getCor() == oponente(cor))
                .map(x -> (PecasXadrez) x)
                .collect(Collectors.toList());

        List<PecasXadrez> lista = getPecasTabuleiro().stream()
                .filter(x -> x instanceof PecasXadrez && ((PecasXadrez) x).getCor() == cor)
                .map(x -> (PecasXadrez) x)
                .collect(Collectors.toList());

        boolean[][] reiMov = getReis(cor).movimentosPossiveis();
        Posicao reiPos = getReis(cor).getPosicao();

        verificarMovimentosRei(oponente, reiMov, reiPos);
        verificarMovimentosPecas(lista, reiMov, reiPos, oponente);
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

    private void verificarMovimentosPecas(List<PecasXadrez> lista, boolean[][] reiMov, Posicao reiPos, List<PecasXadrez> oponente) {
        for (PecasXadrez pe : lista) {
            if (!(pe instanceof Rei)) {
                Posicao pos = pe.getPosicao();
                Pecas pec = getTab().removerPecas(pe.getPosicao());
                verificarAmeacas(oponente, reiMov, reiPos);
                getTab().lugarPeca(pec, pos);
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
        getTab().lugarPeca(pecas, new Posicao(linha, coluna));
        getPecasTabuleiro().add(pecas);
    }

    private void iniciarPartida() {
        //torres
        lugarNovaPeca(7, 0, new Torre(Cores.BRANCAS, getTab()));
        lugarNovaPeca(7, 7, new Torre(Cores.BRANCAS, getTab()));
        lugarNovaPeca(0, 0, new Torre(Cores.PRETAS, getTab()));
        lugarNovaPeca(0, 7, new Torre(Cores.PRETAS, getTab()));

        //reis
        lugarNovaPeca(7, 4, new Rei(Cores.BRANCAS, getTab(), this));
        lugarNovaPeca(0, 4, new Rei(Cores.PRETAS, getTab(), this));

        //peões
        for (int i = 0; i < 8; i++) {
            lugarNovaPeca(6, i, new Peao(Cores.BRANCAS, getTab(), this));
            lugarNovaPeca(1, i, new Peao(Cores.PRETAS, getTab(), this));
        }

        //bispos
        lugarNovaPeca(7, 2, new Bispo(Cores.BRANCAS, getTab()));
        lugarNovaPeca(7, 5, new Bispo(Cores.BRANCAS, getTab()));
        lugarNovaPeca(0, 2, new Bispo(Cores.PRETAS, getTab()));
        lugarNovaPeca(0, 5, new Bispo(Cores.PRETAS, getTab()));

        //cavalos
        lugarNovaPeca(7, 1, new Cavalo(Cores.BRANCAS, getTab()));
        lugarNovaPeca(7, 6, new Cavalo(Cores.BRANCAS, getTab()));
        lugarNovaPeca(0, 1, new Cavalo(Cores.PRETAS, getTab()));
        lugarNovaPeca(0, 6, new Cavalo(Cores.PRETAS, getTab()));

        //damas
        lugarNovaPeca(0, 3, new Dama(Cores.PRETAS, getTab()));
        lugarNovaPeca(7, 3, new Dama(Cores.BRANCAS, getTab()));
    }
}
