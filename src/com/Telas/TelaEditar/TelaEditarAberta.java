package com.Telas.TelaEditar;

import com.Admin;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Questao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaEditarAberta extends JFrame {

    private Questao questao;
    //Objetos do painel superior{
    private JPanel superior;
    private JLabel assunto;
    private JTextField assuntoT;
    //}----------------
    //Objetos do painel Central{
    private JPanel centro;

    private JPanel IDP;
    private JLabel ID;
    private JLabel IDT;

    private JPanel tipoP;
    private JLabel tipo;

    private JPanel perguntaP;
    private JLabel pergunta;
    private JTextField perguntaT;

    private JPanel respostaP;
    private JLabel resposta;
    private JTextField respostaT;
    //}----------------------
    //Objetos do painel inferior{
    private JPanel inferior;
    private JLabel sair;
    private JLabel ok;
    //}----------------------------

    Admin admin;

    private String assuntoA;
    private String perguntaA;
    private String respostaA;

    public TelaEditarAberta(Questao questao,Admin admin){
        this.admin = admin;
        this.questao = questao;
        setLayout(new BorderLayout());
        setTitle("Editar");
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

        assuntoA = questao.getAssunto();

        assunto = new JLabel("Assunto:");
        assuntoT = new JTextField(14);
        assuntoT.setText(questao.getAssunto());
        format(assunto);
        format(assuntoT);
        assunto.setForeground(Color.WHITE);
        superior.add(assunto);
        superior.add(assuntoT);
    }

    public void elementosDoPainelCentro(){
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createLineBorder(new Color(0,53,105)));

        IDP = new JPanel(new FlowLayout());
        elementosdoPainelIDP();

        tipoP = new JPanel(new FlowLayout());
        elementosdoPainelTipo();

        perguntaP = new JPanel(new FlowLayout());
        elementosdoPainelPergunta();

        respostaP = new JPanel(new FlowLayout());
        elementosdoPainelResposta();

        centro.add(IDP);
        centro.add(tipoP);
        centro.add(perguntaP);
        centro.add(respostaP);
    }

    public void elementosdoPainelIDP(){
        ID = new JLabel("ID: " + questao.getID());
        format(ID);
        IDP.add(ID);
    }

    public void elementosdoPainelTipo(){
        tipo = new JLabel("Tipo: " + questao.getTipo());
        format(tipo);
        tipoP.add(tipo);
    }

    public void elementosdoPainelPergunta(){
        perguntaA = questao.getPergunta();
        pergunta = new JLabel("Pergunta: ");
        format(pergunta);
        perguntaT = new JTextField(14);
        perguntaT.setText(questao.getPergunta());
        format(perguntaT);
        perguntaP.add(pergunta);
        perguntaP.add(perguntaT);
    }

    public void elementosdoPainelResposta(){
        respostaA = ((Aberta) questao).getResposta();
        resposta = new JLabel("Resposta: ");
        format(resposta);
        respostaT = new JTextField(14);
        respostaT.setText(((Aberta) questao).getResposta());
        format(respostaT);

        respostaP.add(resposta);
        respostaP.add(respostaT);
    }

    public void elementosDoPainelInferior(){
        sair = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
        sair.addMouseListener(new TelaEditarListeners(1,"Imagens/icons8-sair",sair,this));

        ok = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-ok_Black.png")));
        ok.addMouseListener(new TelaEditarListeners(2,"Imagens/icons8-ok",ok,this));

        inferior.add(sair);
        inferior.add(ok);
    }

    public void format(JLabel label){
        Font fonte = new Font("SansSerif", Font.BOLD, 18);
        label.setFont(fonte);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    public void format(JTextField label){
        Font fonte = new Font("SansSerif", Font.BOLD, 18);
        label.setFont(fonte);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    public void editar(){
        String MensagemMA="";
        String MensagemMP="";
        String MensagemMR="";
        boolean modificou = false;

        if(!assuntoA.equals(assuntoT.getText())){
            String assuntoK = assuntoT.getText().substring(0,1).toUpperCase().concat(assuntoT.getText().substring(1).toLowerCase());
            if(admin.addQuestaoAssunto(assuntoK, questao)){
                admin.removerQuestao(questao.getAssunto(),(int) questao.getID());
                questao.setAssunto(assuntoK);
                MensagemMA = "Assunto Mudado Com Sucesso";
                modificou = true;
            }else{
                MensagemMA = "Ocorreu um problema! Assunto da Questão não modificado";
                modificou = true;
            }
        }

        if(!perguntaA.equals(perguntaT.getText()) || !respostaA.equals(respostaT.getText())){
            admin.editarQuestao((int) questao.getID(), perguntaT.getText(), respostaT.getText());
            modificou = true;

            if(!perguntaA.equals(perguntaT.getText())){
                MensagemMP = "Pergunta Mudada";
            }

            if(!respostaA.equals(respostaT.getText())){
                MensagemMR = "Resposta Mudada";
            }
        }
        if(modificou) {
            JOptionPane.showMessageDialog(null, MensagemMA + "\n" + MensagemMP + "\n" + MensagemMR);
            assuntoA = questao.getAssunto();
            perguntaA = questao.getPergunta();
            respostaA = ((Aberta) questao).getResposta();
        }
    }
}