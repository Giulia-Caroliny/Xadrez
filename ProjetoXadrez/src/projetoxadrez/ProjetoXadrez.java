/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetoxadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jogo.PartidaXadrez;
import jogo.PecasXadrez;
import jogo.PosicaoXadrez;
import tabuleiro.TabuleiroException;
import tabuleiro.XadrezException;

/**
 *
 * @author giuli
 */
public class ProjetoXadrez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez partida = new PartidaXadrez();
        List<PecasXadrez> pecas = new ArrayList<PecasXadrez>();

        while (!partida.isCheckmate()) {
            try {

                ViewProvisorio.imprimirPartida(partida, pecas);
                System.out.println();

                System.out.println("Coordenadas de origem:");
                PosicaoXadrez origem = ViewProvisorio.lerPosicaoJogador(sc);

                boolean[][] b = partida.movimentosPossiveisImprimir(origem);

                ViewProvisorio.imprimirTabuleiro(partida.getPecas(), b);

                System.out.println("Coordenadas de destino:");
                PosicaoXadrez destino = ViewProvisorio.lerPosicaoJogador(sc);

                PecasXadrez pecaCapturada = partida.movimentarPeca(origem, destino);
                if (pecaCapturada != null) {
                    pecas.add(pecaCapturada);
                }

                if (partida.getPromocao() != null) {
                    System.out.println("Peça para a promoçao (B, C, D, T):");
                    String tipo = sc.nextLine().toUpperCase();

                    while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("D") && !tipo.equals("T")) {
                        System.out.println("Peça inválida.");
                        System.out.println("Peça para a promoçao (B, C, D, T):");
                        tipo = sc.nextLine().toUpperCase();
                    }
                    partida.trocarPecaPromovida(tipo);
                }
            } catch (XadrezException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (TabuleiroException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
