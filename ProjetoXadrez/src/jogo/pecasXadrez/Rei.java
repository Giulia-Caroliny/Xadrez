/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import javax.swing.ImageIcon;
import jogo.Cores;
import jogo.PartidaXadrez;
import jogo.PecasXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class Rei extends PecasXadrez {

    private PartidaXadrez partida;

    public Rei(Cores cor, Tabuleiro tab, PartidaXadrez partida) {
        super(cor, tab);
        this.partida = partida;
    }

    public boolean podeMover(Posicao pos) {
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null || pe.getCor() != getCor();
    }

    public boolean testeTorreRoque(Posicao pos) {
        PecasXadrez p = (PecasXadrez) getTab().peca(pos);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0;
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

        //jogada especial Roque
        if (getContagemMovimentos() == 0 && !partida.isCheck()) {
            Posicao torreD = new Posicao(pos.getLinha(), pos.getColuna() + 3);
            if (testeTorreRoque(torreD)) {
                Posicao pos1 = new Posicao(pos.getLinha(), pos.getColuna() + 1);
                Posicao pos2 = new Posicao(pos.getLinha(), pos.getColuna() + 2);
                if (!getTab().temPeca(pos1) && !getTab().temPeca(pos2)) {
                    b[pos.getLinha()][pos.getColuna() + 2] = true;
                }
            }

            Posicao torreE = new Posicao(pos.getLinha(), pos.getColuna() - 4);
            if (testeTorreRoque(torreE)) {
                Posicao pos1 = new Posicao(pos.getLinha(), pos.getColuna() - 1);
                Posicao pos2 = new Posicao(pos.getLinha(), pos.getColuna() - 2);
                Posicao pos3 = new Posicao(pos.getLinha(), pos.getColuna() - 3);
                if (!getTab().temPeca(pos1) && !getTab().temPeca(pos2) && !getTab().temPeca(pos3)) {
                    b[pos.getLinha()][pos.getColuna() - 2] = true;
                }
            }
        }

        return b;
    }

    /**
     * Icon = Rei B -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez">Xadrez ícones
     * criados por smalllikeart - Flaticon</a>
     * Rei P -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez">Xadrez ícones
     * criados por Freepik - Flaticon</a>
     *
     * @return ImageIcon - icone da peça
     */
    @Override
    public ImageIcon toImageIcon() {
        if (super.getCor() == Cores.BRANCAS) {
            return new ImageIcon(this.getClass().getResource(".\\imagens\\reiBranco.png"));
        } else {
            return new ImageIcon(this.getClass().getResource(".\\imagens\\reiPreto.png"));
        }
    }

    @Override
    public String toString() {
        return "R ";
    }

}
