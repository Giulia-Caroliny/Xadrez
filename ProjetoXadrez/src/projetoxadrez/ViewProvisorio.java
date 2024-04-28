/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoxadrez;

import java.util.InputMismatchException;
import java.util.Scanner;
import jogo.Cores;
import jogo.PecasXadrez;
import jogo.PosicaoXadrez;

/**
 *
 * @author giuli
 */
public class ViewProvisorio {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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

    public static void imprimirTabuleiro(PecasXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimirPeca(PecasXadrez peca) {
        if (peca == null) {
            System.out.print("- ");
        } else {
            if (peca.getCor() == Cores.BRANCAS) {
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_PURPLE + peca + ANSI_RESET);
            }
        }
        System.out.print("");
    }
}
