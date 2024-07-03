/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.pecasXadrez;

import java.io.File;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import javax.swing.ImageIcon;
import jogo.Cores;
import jogo.PecasXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author giuli
 */
public class Cavalo extends PecasXadrez {

    /**
     * Construtor - Parâmetros: Cores e Tabuleiro
     *
     * @param cor Cores - cor da peça
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     */
    public Cavalo(Cores cor, Tabuleiro tab) {
        super(cor, tab);
    }

    /**
     * Método para verificar se a peça pode mover para a posição informada.
     * Verifica se não há peça na posição informada ou, caso tenha peça, se a
     * peça é do oponente.
     *
     * @param pos Posicao - posição a ser verificada, posição de destino da peça
     * @return boolean - true or false, se a peça pode se mover ou não para a
     * posição informada, respectivamente
     */
    public boolean podeMover(Posicao pos) {
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null || pe.getCor() != getCor();
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
        Posicao p = new Posicao(pos.getLinha() - 2, pos.getColuna() - 1);

        //superior esquerda 1
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //superior direita 1
        p.setColuna(pos.getColuna() + 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //superior direita 2
        p.setLinha(pos.getLinha() - 1);
        p.setColuna(pos.getColuna() + 2);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //superior esquerda 2
        p.setColuna(pos.getColuna() - 2);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //inferior esquerda 1
        p.setLinha(pos.getLinha() + 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //inferior direita 1
        p.setColuna(pos.getColuna() + 2);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //inferior direita 2
        p.setLinha(pos.getLinha() + 2);
        p.setColuna(pos.getColuna() + 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }

        //inferior esquerda 2
        p.setColuna(pos.getColuna() - 1);
        if (getTab().posicaoExiste(p) && podeMover(p)) {
            b[p.getLinha()][p.getColuna()] = true;
        }
        return b;
    }
    
    /**
     * Método para carregar o Icone. 
     * @param caminhoRelativo String - caminho relativo do png da peça
     * @return ImageIcon - icone da peça
     * @throws NoSuchFileException - Arquivo não encontrado
     */
    private ImageIcon carregarIcone(String caminhoRelativo) throws NoSuchFileException {
        URL url = this.getClass().getResource(caminhoRelativo);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            String filePath = "..\\src\\jogo\\pecasXadrez\\" + caminhoRelativo;
            File file = new File(filePath);
            if (file.exists()) {
                return new ImageIcon(filePath);
            } else {
                throw new NoSuchFileException("Icone não encontrado.");
            }
        }
    }

    /**
     * Método sobrescrito - localizar e retornar o Icone referente a peça Icon =
     * Cavalo B -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez">Xadrez ícones
     * criados por Icongeek26 - Flaticon</a>
     * Cavalo P -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez">Xadrez ícones
     * criados por Freepik - Flaticon</a>
     *
     * @return ImageIcon - icone da peça
     */
    @Override
    public ImageIcon toImageIcon() {
        try {
            if (super.getCor() == Cores.BRANCAS) {
                return carregarIcone("imagens\\cavaloBranco.png");
            } else {
                return carregarIcone("imagens\\cavaloPreto.png");
            }
        } catch (NoSuchFileException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "C ";
    }
}
