package com.Telas.TelaInformacoes;

import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Questao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaInformaçõesAberta extends JFrame {
    private Questao questao;
    //Objetos do painel superior{
    private JPanel superior;
        private JLabel assunto;
    //}----------------
    //Objetos do painel Central{
    private JPanel centro;
        private JLabel ID;
        private JLabel tipo;
        private JLabel pergunta;
        private JLabel resposta;
    //}----------------------
    //Objetos do painel inferior{
    private JPanel inferior;
        private JLabel sair;
    //}----------------------------

    public TelaInformaçõesAberta(Questao questao){
        this.questao = questao;
        setLayout(new BorderLayout());
        setTitle("Informações");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        elementosDoFrame();
    }

    public void elementosDoFrame(){
        superior = new JPanel(new FlowLayout());
        elementosDoPainelSuperior();
        add(superior,BorderLayout.NORTH);

        centro = new JPanel(new GridLayout(4,1));
        elementosDoPainelCentro();
        add(centro,BorderLayout.CENTER);

        inferior = new JPanel(new GridLayout(1,1));
        elementosDoPainelInferior();
        add(inferior,BorderLayout.SOUTH);
    }

    public void elementosDoPainelSuperior(){
        superior.setBackground(new Color(0,53,105));
        assunto = new JLabel("Assunto: " + questao.getAssunto());
        format(assunto);
        assunto.setForeground(Color.WHITE);
        superior.add(assunto);
    }

    public void elementosDoPainelCentro(){
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createLineBorder(new Color(0,53,105)));

        ID = new JLabel("ID: " + questao.getID());
        format(ID);
        tipo = new JLabel("Tipo: " + questao.getTipo());
        format(tipo);
        pergunta = new JLabel("Pergunta: " + questao.getPergunta());
        format(pergunta);
        resposta = new JLabel("Resposta: " + ((Aberta) questao).getResposta());
        format(resposta);

        centro.add(ID);
        centro.add(tipo);
        centro.add(pergunta);
        centro.add(resposta);
    }

    public void elementosDoPainelInferior(){
        sair = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
        sair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                sair.setBackground(new Color(155, 0, 0));
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                sair.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-sair_White.png")));
                sair.setOpaque(true);
                sair.setBackground(new Color(177, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                sair.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
                sair.setOpaque(false);
            }
        });
        inferior.add(sair);
    }

    public void format(JLabel label){
        Font fonte = new Font("SansSerif", Font.BOLD, 18);
        label.setFont(fonte);
        label.setHorizontalAlignment(JLabel.CENTER);
    }
}
