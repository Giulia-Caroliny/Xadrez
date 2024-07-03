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

    /**
     * Construtor - Parâmetros: Cores, Tabuleiro e PartidaXadrez
     *
     * @param cor Cores - cor da peça
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     * @param partida PartidaXadrez - partida de xadrez atual
     */
    public Rei(Cores cor, Tabuleiro tab, PartidaXadrez partida) {
        super(cor, tab);
        this.partida = partida;
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
     * Método para verificar se o rei e a torre estão aptos a realizar o
     * movimento de Roque. Verifica se as peças estão posicionadas no lugar, as
     * peças são instancias, são aliadas e a contagem de movimentos é 0.
     *
     * @param pos Posicao - posição de destino do movimento de Roque
     * @return boolean - true or false, se está apto ou não a realizar o
     * movimento de Roque, respectivamente
     */
    public boolean testeTorreRoque(Posicao pos) {
        PecasXadrez p = (PecasXadrez) getTab().peca(pos);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0;
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
     * Rei B -
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
        try {
            if (super.getCor() == Cores.BRANCAS) {
                return carregarIcone("imagens\\reiBranco.png");
            } else {
                return carregarIcone("imagens\\reiPreto.png");
            }
        } catch (NoSuchFileException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "R ";
    }

}
