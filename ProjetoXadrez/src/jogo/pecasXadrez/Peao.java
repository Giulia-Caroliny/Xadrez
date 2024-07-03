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
public class Peao extends PecasXadrez {

    private PartidaXadrez partida;

    /**
     * Construtor - Parâmetros: Cores, Tabuleiro e PartidaXadrez
     *
     * @param cor Cores - cor da peça
     * @param tab Tabuleiro - linhas, colunas e matriz de peças posicionadas no
     * tabuleiro
     * @param partida PartidaXadrez - partida de xadrez atual
     */
    public Peao(Cores cor, Tabuleiro tab, PartidaXadrez partida) {
        super(cor, tab);
        this.partida = partida;
    }

    /**
     * Método para verificar se a peça pode mover para a posição informada.
     * Verifica se não há peça na posição informada.
     *
     * @param pos Posicao - posição a ser verificada, posição de destino da peça
     * @return boolean - true or false, se a peça pode se mover ou não para a
     * posição informada, respectivamente
     */
    public boolean podeMover(Posicao pos) {
        PecasXadrez pe = (PecasXadrez) getTab().peca(pos);
        return pe == null;
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
        //Posicao p = new Posicao(pos.getLinha(), pos.getColuna());

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cores.BRANCAS) {
            p.setValores(pos.getLinha() - 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 2, pos.getColuna());
            Posicao p2 = new Posicao(pos.getLinha() - 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2) && getContagemMovimentos() == 0) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }

            //jogada especial en passant
            if (pos.getLinha() == 3) {
                Posicao left = new Posicao(pos.getLinha(), pos.getColuna() - 1);
                if (getTab().posicaoExiste(left) && pecaOponente(left) && getTab().peca(left) == partida.getEnPassant()) {
                    b[left.getLinha() - 1][left.getColuna()] = true;
                }
                Posicao right = new Posicao(pos.getLinha(), pos.getColuna() + 1);
                if (getTab().posicaoExiste(right) && pecaOponente(right) && getTab().peca(right) == partida.getEnPassant()) {
                    b[right.getLinha() - 1][right.getColuna()] = true;
                }
            }
        } else {
            p.setValores(pos.getLinha() + 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 2, pos.getColuna());
            Posicao p2 = new Posicao(pos.getLinha() + 1, pos.getColuna());
            if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2) && getContagemMovimentos() == 0) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
            if (getTab().posicaoExiste(p) && pecaOponente(p)) {
                b[p.getLinha()][p.getColuna()] = true;
            }

            //jogada especial en passant
            if (pos.getLinha() == 4) {
                Posicao left = new Posicao(pos.getLinha(), pos.getColuna() - 1);
                if (getTab().posicaoExiste(left) && pecaOponente(left) && getTab().peca(left) == partida.getEnPassant()) {
                    b[left.getLinha() + 1][left.getColuna()] = true;
                }
                Posicao right = new Posicao(pos.getLinha(), pos.getColuna() + 1);
                if (getTab().posicaoExiste(right) && pecaOponente(right) && getTab().peca(right) == partida.getEnPassant()) {
                    b[right.getLinha() + 1][right.getColuna()] = true;
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
     * Peao B -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez" >Xadrez ícones
     * criados por Freepik - Flaticon</a>
     * Peao P -
     * <a href="https://www.flaticon.com/br/icones-gratis/xadrez" >Xadrez ícones
     * criados por smashingstocks - Flaticon</a>
     *
     * @return ImageIcon - icone da peça
     */
    @Override
    public ImageIcon toImageIcon() {
        try {
            if (super.getCor() == Cores.BRANCAS) {
                return carregarIcone("imagens\\peaoBranco.png");
            } else {
                return carregarIcone("imagens\\peaoPreto.png");
            }
        } catch (NoSuchFileException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "P ";
    }

}
