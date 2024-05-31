/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projetoxadrez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import jogo.Cores;
import jogo.PartidaXadrez;
import jogo.PecasXadrez;
import tabuleiro.Posicao;

/**
 *
 * @author giuli
 */
public class FrmXadrez extends javax.swing.JFrame {

    private static PartidaXadrez partida = new PartidaXadrez();
    private static JButton[][] pos;
    private static List<PecasXadrez> pecasCap = new ArrayList<PecasXadrez>();
    private static Posicao origem = null;
    private static Posicao destino = null;
    private static FrmPromocao promo = new FrmPromocao();

    /**
     * Creates new form FrmXadrez
     */
    public FrmXadrez() {
        setTitle("Xadrez");
        //setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        iniciarTab();

        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JPanel();
        pretas = new javax.swing.JPanel();
        brancas = new javax.swing.JPanel();
        jogadorVez = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pecasP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pecasB = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 660));
        setSize(new java.awt.Dimension(900, 660));
        getContentPane().setLayout(null);

        javax.swing.GroupLayout tabLayout = new javax.swing.GroupLayout(tab);
        tab.setLayout(tabLayout);
        tabLayout.setHorizontalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        tabLayout.setVerticalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(tab);
        tab.setBounds(0, 0, 600, 600);

        javax.swing.GroupLayout pretasLayout = new javax.swing.GroupLayout(pretas);
        pretas.setLayout(pretasLayout);
        pretasLayout.setHorizontalGroup(
            pretasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        pretasLayout.setVerticalGroup(
            pretasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        getContentPane().add(pretas);
        pretas.setBounds(600, 50, 290, 225);

        javax.swing.GroupLayout brancasLayout = new javax.swing.GroupLayout(brancas);
        brancas.setLayout(brancasLayout);
        brancasLayout.setHorizontalGroup(
            brancasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        brancasLayout.setVerticalGroup(
            brancasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(brancas);
        brancas.setBounds(600, 375, 290, 230);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jogadorVezLayout = new javax.swing.GroupLayout(jogadorVez);
        jogadorVez.setLayout(jogadorVezLayout);
        jogadorVezLayout.setHorizontalGroup(
            jogadorVezLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        jogadorVezLayout.setVerticalGroup(
            jogadorVezLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jogadorVez);
        jogadorVez.setBounds(600, 275, 290, 50);

        pecasP.setBackground(java.awt.Color.lightGray);
        pecasP.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Peças capturadas:");

        javax.swing.GroupLayout pecasPLayout = new javax.swing.GroupLayout(pecasP);
        pecasP.setLayout(pecasPLayout);
        pecasPLayout.setHorizontalGroup(
            pecasPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        pecasPLayout.setVerticalGroup(
            pecasPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pecasP);
        pecasP.setBounds(600, 0, 290, 0);

        pecasB.setBackground(java.awt.Color.white);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Peças capturadas:");

        javax.swing.GroupLayout pecasBLayout = new javax.swing.GroupLayout(pecasB);
        pecasB.setLayout(pecasBLayout);
        pecasBLayout.setHorizontalGroup(
            pecasBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pecasBLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        pecasBLayout.setVerticalGroup(
            pecasBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pecasBLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pecasB);
        pecasB.setBounds(600, 325, 290, 0);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmXadrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmXadrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmXadrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmXadrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmXadrez().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel brancas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jogadorVez;
    private javax.swing.JPanel pecasB;
    private javax.swing.JPanel pecasP;
    private javax.swing.JPanel pretas;
    private javax.swing.JPanel tab;
    // End of variables declaration//GEN-END:variables

    private void iniciarTab() {
        tab.setSize(600, 600);
        tab.setLayout(new GridLayout(8, 8));
        pos = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                if ((i + j) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }

                if (partida.getPecas()[i][j] != null) {
                    button.setIcon(partida.getPecas()[i][j].toImageIcon());
                }
                button.setName("pos-" + i + "-" + j);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        String pos = clickedButton.getName();

                        String[] posi = pos.split("-");

                        int l = Integer.parseInt(posi[1]);
                        int c = Integer.parseInt(posi[2]);

                        jogo(l, c);
                    }
                });
                pos[i][j] = button;
                tab.add(button);
            }
        }
        add(tab);
    }

    private static void jogo(int linha, int coluna) {
        if (origem == null) {
            if (partida.validarAtribuicaoOrigem(new Posicao(linha, coluna))) {
                origem = new Posicao(linha, coluna);
                imprimirMovimentosPossiveis(origem.getLinha(), origem.getColuna());
            }
        } else if (destino == null) {
            if (partida.validarAtribuicaoDestino(origem, new Posicao(linha, coluna))) {
                destino = new Posicao(linha, coluna);
                PecasXadrez aux = partida.movimentarPeca(origem, destino);

                if (aux != null) {
                    pecasCap.add(aux);
                    imprimirPecasCapturadas();
                }

                origem = null;
                destino = null;

                imprimirTabuleiro();
            }

            if (partida.getPromocao() != null) {
                promo.setVisible(true);
            }
        }
    }

    protected static void promover(String pecaPromo) {
        while (!pecaPromo.equals("B") && !pecaPromo.equals("C") && !pecaPromo.equals("D") && !pecaPromo.equals("T")) {
            promo.setVisible(true);
        }

        partida.trocarPecaPromovida(pecaPromo);
        imprimirTabuleiro();
    }

    private static void imprimirTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pos[i][j].setBackground(Color.WHITE);
                } else {
                    pos[i][j].setBackground(Color.GRAY);
                }

                if (partida.getPecas()[i][j] != null) {
                    if (partida.getJogadorVez() != partida.getPecas()[i][j].getCor()) {
                        pos[i][j].setEnabled(false);
                    } else {
                        pos[i][j].setEnabled(true);
                    }
                    pos[i][j].setIcon(partida.getPecas()[i][j].toImageIcon());
                } else {
                    pos[i][j].setIcon(null);
                    pos[i][j].setEnabled(false);
                }
            }
        }
    }

    private static void imprimirMovimentosPossiveis(int linha, int coluna) {
        boolean[][] b = partida.movimentosPossiveisImprimir(new Posicao(linha, coluna));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j]) {
                    pos[i][j].setBackground(Color.red);
                    pos[i][j].setEnabled(true);
                }
                if (partida.getPecas()[i][j] != null) {
                    pos[i][j].setIcon(partida.getPecas()[i][j].toImageIcon());
                }
            }
        }
    }

    private static void imprimirPecasCapturadas() {
        if (!pecasCap.isEmpty()) {
            List<PecasXadrez> pecasBrancas = pecasCap.stream().filter(x -> x.getCor() == Cores.BRANCAS).collect(Collectors.toList());
            List<PecasXadrez> pecasPretas = pecasCap.stream().filter(x -> x.getCor() == Cores.PRETAS).collect(Collectors.toList());

            if (!pecasBrancas.isEmpty()) {
                //lblPecasCapB.setText("Brancas: " + pecasBrancas);
            }

            if (!pecasPretas.isEmpty()) {
                for (PecasXadrez p : pecasPretas) {
                    //pretas.add(new JLabel().setIcon(p.toImageIcon()));
                }
                //lblPecasCapP.setText("Pretas: " + pecasPretas);
            }
        }
    }
}
