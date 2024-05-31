/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import javax.swing.ImageIcon;
import jogo.Cores;
import jogo.PecasXadrez;
import tabuleiro.Posicao;
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
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        Posicao p = new Posicao(pos.getLinha() - 1, pos.getColuna());

        //acima
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo
        p.setLinha(pos.getLinha() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setLinha(pos.getLinha());
        p.setColuna(pos.getColuna() - 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //direita
        p.setColuna(pos.getColuna() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        return b;
    }

    /**
     * Icon = Torre B -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez" >Xadrez ícones
     * criados por Freepik - Flaticon</a>
     * Torre P -
     * <a href="https://www.flaticon.com/br/icones-gratis/torre" >Torre ícones
     * criados por riajulislam - Flaticon</a>
     *
     * @return ImageIcon - icone da peça
     */
    @Override
    public ImageIcon toImageIcon() {
        if (super.getCor() == Cores.BRANCAS) {
            return new ImageIcon(this.getClass().getResource(".\\imagens\\torreBranca.png"));
        } else {
            return new ImageIcon(this.getClass().getResource(".\\imagens\\torrePreta.png"));
        }
    }

    @Override
    public String toString() {
        return "T ";
    }

}
