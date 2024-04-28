/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoxadrez;

import jogo.PecasXadrez;

/**
 *
 * @author giuli
 */
public class ViewProvisorio {

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

    public static void imprimirPeca(PecasXadrez peca) {
        if (peca == null) {
            System.out.print("- ");
        } else {
            System.out.print(peca);
        }
    }
}
