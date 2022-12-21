package com.Telas.TelaInformacoes;

import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaInformaçõesObjetiva extends JFrame{
    private Questao questao;
    //Objetos do painel superior{
    private JPanel superior;
    private JLabel assunto;
    //}----------------
    //Objetos do painel Central{
    private JPanel centro;
    //Objetos do painel centroS{
    private JPanel centroS;
    private JLabel ID;
    private JLabel tipo;
    private JLabel pergunta;
    private JLabel corretas;
    //}-------------------------
    //Objetos do painel centroI{
    JPanel centroI;
    private JLabel resposta;
    //}------------------------
    //}----------------------
    //Objetos do painel inferior{
    private JPanel inferior;
    private JLabel sair;
    //}----------------------------

    public TelaInformaçõesObjetiva(Questao questao){
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

        centro = new JPanel(new BorderLayout());
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
        centroS = new JPanel(new GridLayout(4,1));
        elementosDoPainelCentroS();
        centro.add(centroS,BorderLayout.NORTH);

        centroI = new JPanel(new GridLayout(3,3));
        elementosDoPainelCentroI();
        centro.add(centroI,BorderLayout.CENTER);
    }

    public void elementosDoPainelCentroS(){
        centroS.setBackground(Color.WHITE);
        centroS.setBorder(BorderFactory.createLineBorder(new Color(0,53,105)));

        ID = new JLabel("ID: " + questao.getID());
        format(ID);
        tipo = new JLabel("Tipo: " + questao.getTipo());
        format(tipo);
        pergunta = new JLabel("Pergunta: " + questao.getPergunta());
        format(pergunta);
        corretas = new JLabel("Alt. Corretas: " + ((Objetiva)questao).getCorretas());
        format(corretas);

        centroS.add(ID);
        centroS.add(tipo);
        centroS.add(pergunta);
        centroS.add(corretas);
    }

    public void elementosDoPainelCentroI(){
        centroI.setBackground(Color.WHITE);
        centroI.setBorder(BorderFactory.createLineBorder(new Color(0,53,105)));

        String[] alternativas=((Objetiva)questao).getAlternativas();
        String[] letras = {"A","B","C","D","E","F"};
        for(int i=0; i<alternativas.length;i++){
            JLabel alt = new JLabel("     (" + letras[i] + ") " + alternativas[i]);
            format1(alt);
            centroI.add(alt);
        }
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

    public void format1(JLabel label){
        Font fonte = new Font("SansSerif", Font.BOLD, 18);
        label.setFont(fonte);
    }
}