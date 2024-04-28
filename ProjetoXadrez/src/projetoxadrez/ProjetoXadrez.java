/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetoxadrez;

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

        try {
            while (true) {
                ViewProvisorio.imprimirTabuleiro(partida.getPecas());
                System.out.println();

                System.out.println("Coordenadas de origem:");
                PosicaoXadrez origem = ViewProvisorio.lerPosicaoJogador(sc);

                System.out.println("Coordenadas de destino:");
                PosicaoXadrez destino = ViewProvisorio.lerPosicaoJogador(sc);

                PecasXadrez pecaCapturada = partida.movimentarPeca(origem, destino);

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
