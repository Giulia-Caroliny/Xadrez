/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo;

import javax.swing.ImageIcon;
import tabuleiro.Pecas;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public abstract class PecasXadrez extends Pecas {

    private Cores cor;
    private int contagemMovimentos;

    /**
     * Construtor - Parâmetros: Cores e Tabuleiro
     *
     * @param cor Cores - cor da peça
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     */
    public PecasXadrez(Cores cor, Tabuleiro tab) {
        super(tab);
        this.cor = cor;
    }

    /**
     * Método get do atributo cor
     *
     * @return Cores - cor da peça
     */
    public Cores getCor() {
        return cor;
    }

    /**
     * Método get do atributo contagemMovimentos
     *
     * @return int - número de movimentos dados pela peça
     */
    public int getContagemMovimentos() {
        return contagemMovimentos;
    }

    /**
     * Método get do atributo posicao
     *
     * @return Posicao - posição da peça
     */
    public Posicao getPosicao() {
        return pos;
    }

    /**
     * Método para pegar a posição Posicao no padrão PosicaoXadrez
     *
     * @return PosicaoXadrez - posição convertida
     * @deprecated Utilize {@link #getPosicao()}
     */
    @Deprecated
    public PosicaoXadrez getPosicaoXadrez() {
        return PosicaoXadrez.posicaoXadrez(pos);
    }

    /**
     * Método para descobrir se a peça alocada na posição informada é do
     * oponente
     *
     * @param pos Posicao - posição da peça a ser avaliada
     * @return boolean - true or false, se a peça for oponente ou aliada,
     * respectivamente
     */
    protected boolean pecaOponente(Posicao pos) {
        PecasXadrez p = (PecasXadrez) getTab().peca(pos);
        return p != null && p.getCor() != cor;
    }

    /**
     * Método para incrementar à contagem de movimentos da peça
     */
    protected void incrementoContagemMovimentos() {
        contagemMovimentos++;
    }

    /**
     * Método para decrementar à contagem de movimentos da peça
     */
    protected void decrementoContagemMovimentos() {
        contagemMovimentos--;
    }

    /**
     * Método abstrato para localizar e retornar o Icone referente a peça
     *
     * @return ImageIcon - icone da peça
     */
    public abstract ImageIcon toImageIcon();
}
