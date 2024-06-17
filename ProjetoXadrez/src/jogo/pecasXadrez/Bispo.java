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
public class Bispo extends PecasXadrez {

    /**
     * Construtor - Parâmetros: Cores e Tabuleiro
     *
     * @param cor Cores - cor da peça
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     */
    public Bispo(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    /**
     * Método sobrescrito para descobrir os movimentos possíveis de uma peça.
     * Avalia todos os movimentos da peça e as posições resultantes do
     * movimento.
     *
     * @return boolean[][] - matriz de booleanos, true na posição resultante de
     * algum de seus movimentos, false, se não há movimento que chegue à posição
     */
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] b = new boolean[getTab().getLinhas()][getTab().getColunas()];
        Posicao p = new Posicao(pos.getLinha() - 1, pos.getColuna() - 1);

        //diagonal superior esquerda
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal inferior direita
        p.setLinha(pos.getLinha() + 1);
        p.setColuna(pos.getColuna() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal inferior esquerda
        p.setLinha(pos.getLinha() + 1);
        p.setColuna(pos.getColuna() - 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() - 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //diagonal superior direita
        p.setLinha(pos.getLinha() - 1);
        p.setColuna(pos.getColuna() + 1);
        while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
            b[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() + 1);
        }
        if (getTab().posicaoExiste(p) && pecaOponente(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        return b;
    }

    /**
     * Método sobrescrito - localizar e retornar o Icone referente a peça Icon -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez">Xadrez ícones
     * criados por smalllikeart - Flaticon</a>
     *
     * @return ImageIcon - icone da peça
     */
    @Override
    public ImageIcon toImageIcon() {
        try {
            if (super.getCor() == Cores.BRANCAS) {
                return new ImageIcon(this.getClass().getResource(".\\imagens\\bispoBranco.png"));
            } else {
                return new ImageIcon(this.getClass().getResource(".\\imagens\\bispoPreto.png"));
            }
        } catch (Exception a) {
            try {
                if (super.getCor() == Cores.BRANCAS) {
                    return new ImageIcon("..\\src\\jogo\\pecasXadrez\\imagens\\bispoBranco.png");
                } else {
                    return new ImageIcon("..\\src\\jogo\\pecasXadrez\\imagens\\bispoPreto.png");
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "B ";
    }
}
