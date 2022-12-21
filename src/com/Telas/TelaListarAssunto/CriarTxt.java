package com.Telas.TelaListarAssunto;

import com.Admin;
import com.ListaQuestoes.Assunto;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;
import com.ListaQuestoes.Questao.VouF;
import com.Telas.TelaPrincipal.TileListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CriarTxt extends JFrame {

    private Admin admin;
    private JRadioButton comRespostas;
    private JRadioButton semRespostas;
    private ButtonGroup group;
    private JButton botaoCriar;

    public CriarTxt(Admin admin, Assunto assunto, String nomeAssunto){

        this.comRespostas = new JRadioButton("Gerar txt com respostas");
        this.semRespostas = new JRadioButton("Gerar txt sem respostas");
        this.group = new ButtonGroup();
        this.botaoCriar = new JButton("Criar txt");
        this.admin = admin;

        botaoCriar.setBackground(TileListener.estadoPadrao);
        comRespostas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN+Font.BOLD, 15));
        semRespostas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN+Font.BOLD, 15));


        setLayout(new BorderLayout());
        group.add(comRespostas);
        group.add(semRespostas);

        add(comRespostas, BorderLayout.WEST);
        add(semRespostas, BorderLayout.EAST);

        add(botaoCriar, BorderLayout.SOUTH);

        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    escreveTxt(assunto.getListaDeQuestoes(), comRespostas.isSelected(), nomeAssunto);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        setVisible(true);
        setSize(400,150);


    }

    public void escreveTxt(List<Questao> lista, boolean escreverResposta, String assunto) throws IOException {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("txt_" + assunto+".txt"));
        String linha = "";
        String newLine = System.getProperty("line.separator");

        for (Questao q : lista){
            linha = q.getPergunta();
            buffWrite.append(linha + newLine);

            if(q.getTipo().equals("Objetiva")){
                for(String a : ((Objetiva)q).getAlternativas()){

                    buffWrite.append(a + newLine);
                }
            }
            buffWrite.append(newLine);
            if(escreverResposta){


                if(q.getTipo().equals("Aberta")){
                    linha = ((Aberta)q).getResposta();
                }
                else if(q.getTipo().equals("VouF")){
                    linha = ((VouF)q).getResposta() ? "Verdadeiro" : "Falso";
                }
                else if(q.getTipo().equals("Objetiva")){
                    linha = "{"+((Objetiva)q).getCorretas()+"}";
                }
                buffWrite.append(linha+newLine+newLine);
            }
        }




        buffWrite.close();

    }
}
