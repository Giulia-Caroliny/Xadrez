/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetoxadrez;

import jogo.PartidaXadrez;
import static projetoxadrez.ViewProvisorio.imprimirTabuleiro;

/**
 *
 * @author giuli
 */
public class ProjetoXadrez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PartidaXadrez partida = new PartidaXadrez();
        imprimirTabuleiro(partida.getPecas());
    }
    
}
