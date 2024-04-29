/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabuleiro;

/**
 *
 * @author giuli
 */
public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Pecas[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("O tabuleiro deve ter ao menos 1 linha e 1 coluna.");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Pecas[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Pecas peca(int linha, int coluna) {
        if (!posicaoExiste(linha, coluna)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return pecas[linha][coluna];
    }

    public Pecas peca(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return pecas[pos.getLinha()][pos.getColuna()];
    }

    public void lugarPeca(Pecas p, Posicao pos) {
        if (temPeca(pos)) {
            throw new TabuleiroException("Já existe uma peça na posição " + pos);
        }
        pecas[pos.getLinha()][pos.getColuna()] = p;
        p.pos = pos;
    }

    public Pecas removerPecas(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        if (!temPeca(pos)) {
            return null;
        }
        Pecas aux = peca(pos);
        aux.pos = null;
        pecas[pos.getLinha()][pos.getColuna()] = null;
        return aux;
    }

    public boolean posicaoExiste(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao pos) {
        return posicaoExiste(pos.getLinha(), pos.getColuna());
    }

    public boolean temPeca(Posicao pos) {
        if (!posicaoExiste(pos)) {
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return peca(pos) != null;
    }

}
