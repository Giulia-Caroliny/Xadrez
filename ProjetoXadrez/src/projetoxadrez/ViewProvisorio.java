/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoxadrez;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import jogo.Cores;
import jogo.PartidaXadrez;
import jogo.PecasXadrez;
import jogo.PosicaoXadrez;

/**
 *
 * @author giuli
 */
public class ViewProvisorio {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static PosicaoXadrez lerPosicaoJogador(Scanner sc) {
        try {
            String aux = sc.nextLine();
            char coluna = aux.charAt(0);
            int linha = Integer.parseInt(aux.substring(1));
            return new PosicaoXadrez(linha, coluna);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao instanciar posição. Posições válidas estão entre a1 e h8");
        }
    }

    public static void imprimirPartida(PartidaXadrez partida, List<PecasXadrez> pecas) {
        imprimirTabuleiro(partida.getPecas());
        System.out.println("");
        System.out.println("Turno: " + partida.getTurno());
        imprimirPecasCapturadas(pecas);
        System.out.println("Aguardando jogador: " + partida.getJogadorVez());
        if (partida.isCheck()) {
            System.out.println("CHECK!");
        }
    }

    public static void imprimirTabuleiro(PecasXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void imprimirTabuleiro(PecasXadrez[][] pecas, boolean[][] movimentosPossiveis) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimirPeca(PecasXadrez peca, boolean corFundo) {
        if (corFundo) {
            System.out.print(ANSI_CYAN_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("- " + ANSI_RESET);
        } else {
            if (peca.getCor() == Cores.BRANCAS) {
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_PURPLE + peca + ANSI_RESET);
            }
        }
        System.out.print("");
    }

    private static void imprimirPecasCapturadas(List<PecasXadrez> pecas) {
        List<PecasXadrez> pecasBrancas = pecas.stream().filter(x -> x.getCor() == Cores.BRANCAS).collect(Collectors.toList());
        List<PecasXadrez> pecasPretas = pecas.stream().filter(x -> x.getCor() == Cores.PRETAS).collect(Collectors.toList());

        System.out.println("Peças capturadas: ");
        System.out.println("Brancas: " + pecasBrancas);
        System.out.println("Pretas: " + pecasPretas);
    }
}
