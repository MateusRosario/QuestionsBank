package com.Telas.TelaEditar;

import com.Admin;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;

import javax.swing.*;
import java.awt.*;

public class TelaEditarObjetiva extends JFrame{
    private Questao questao;
    //Objetos do painel superior{
    private JPanel superior;
    private JLabel assunto;
    private JTextField assuntoT;
    //}----------------
    //Objetos do painel Central{
    private JPanel centro;
    //Objetos do painel centroS{
    private JPanel centroS;
    private JPanel IDP;
    private JLabel ID;
    private JPanel tipoP;
    private JLabel tipo;
    private JPanel perguntaP;
    private JLabel pergunta;
    private JTextField perguntaT;
    private JPanel corretasP;
    private JLabel corretas;
    //}-------------------------
    //Objetos do painel centroI{
    JPanel centroI;
    JTextField[] textFields;
    JRadioButton[] radio;
    //}------------------------
    //}----------------------
    //Objetos do painel inferior{
    private JPanel inferior;
    private JLabel ok;
    private JLabel sair;
    //}----------------------------

    Admin admin;

    private String assuntoA;
    private String perguntaA;
    private String corretasA;

    public TelaEditarObjetiva(Questao questao,Admin admin){
        this.admin = admin;
        this.questao = questao;
        setLayout(new BorderLayout());
        setTitle("Editar");
        setSize(900,500);
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
        centroS = new JPanel(new GridLayout(4,1));
        elementosDoPainelCentroS();
        centro.add(centroS,BorderLayout.NORTH);

        centroI = new JPanel(new GridLayout(3,2));
        elementosDoPainelCentroI();
        centro.add(centroI,BorderLayout.CENTER);
    }

    public void elementosDoPainelCentroS(){
        centroS.setBackground(Color.WHITE);
        centroS.setBorder(BorderFactory.createLineBorder(new Color(0,53,105)));

        IDP = new JPanel(new FlowLayout());
        elementosdoPainelIDP();

        tipoP = new JPanel(new FlowLayout());
        elementosdoPainelTipo();

        perguntaP = new JPanel(new FlowLayout());
        elementosdoPainelPergunta();

        corretasP = new JPanel(new FlowLayout());
        elementosdoPainelResposta();

        centroS.add(IDP);
        centroS.add(tipoP);
        centroS.add(perguntaP);
        centroS.add(corretasP);
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
        perguntaT = new JTextField(25);
        perguntaT.setText(questao.getPergunta());
        format(perguntaT);
        perguntaP.add(pergunta);
        perguntaP.add(perguntaT);
    }

    public void elementosdoPainelResposta(){
        corretasA = ((Objetiva) questao).getCorretas();
        corretas = new JLabel("Corretas: " + ((Objetiva) questao).getCorretas());
        format(corretas);

        corretasP.add(corretas);
    }

    public void elementosDoPainelCentroI(){
        textFields = new JTextField[6];
        radio = new JRadioButton[6];
        String[] alternativas = ((Objetiva) questao).getAlternativas();
        String[] corretas = corretasA.split(",");
        String[] Letras = {"A","B","C","D","E","F"};
        for (int i = 0; i < 6; i++) {
            textFields[i]= new JTextField(15);
            textFields[i].setText(i<alternativas.length? alternativas[i]:"");
            format(textFields[i]);

            radio[i]= new JRadioButton();
            if(verificaArray(corretas,Letras[i])){
                radio[i].setSelected(true);
            }

            JPanel painel = new JPanel(new FlowLayout());
            painel.add(new JLabel("(" + Letras[i] + ")."));
            painel.add(radio[i]);
            painel.add(textFields[i]);
            centroI.add(painel);
        }
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

    public boolean verificaArray(String[] a, String b){
        for (int i = 0; i < a.length ; i++) {
            if(a[i].equals(b)){
                return true;
            }
        }
        return false;
    }

    public boolean verificaMudAlt(String[] alt){
        String[] alt2 = ((Objetiva) questao).getAlternativas();
        if(alt.length!=alt2.length){
            return true;
        }

        for (int i = 0; i < alt.length ; i++) {
            if(!alt[i].equals(alt2[i])){
                return true;
            }
        }
        return false;
    }

    public int verificaNumAlt(){
        for (int i = 0; i < 6; i++) {
            if(textFields[i].getText().equals("")){
                return i;
            }
        }
        return 6;
    }

    public void editar(){
        String MensagemMA="";
        String MensagemMP="";
        String MensagemMR="";
        String corretasT="";
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

        String[] novaAlternativas = new String[verificaNumAlt()];
        for (int i = 0; i < novaAlternativas.length; i++) {
            novaAlternativas[i] = textFields[i].getText();
        }

        if (radio[0].isSelected()) {
            corretasT="A,";
        }
        if (radio[1].isSelected()) {
            corretasT=corretasT+"B,";
        }
        if (radio[2].isSelected()) {
            corretasT=corretasT+"C,";
        }
        if (radio[3].isSelected()) {
            corretasT=corretasT+"D,";
        }
        if (radio[4].isSelected()) {
            corretasT=corretasT+"E,";
        }
        if (radio[5].isSelected()) {
            corretasT=corretasT+"F,";
        }

        corretasT=corretasT.substring(0,corretasT.length()-1);

        boolean verificaMud = verificaMudAlt(novaAlternativas);

        if(!perguntaA.equals(perguntaT.getText()) || !corretasT.equals(corretasA) || verificaMud){
            admin.editarQuestao((int) questao.getID(), perguntaT.getText(), novaAlternativas, corretasT);
            corretas.setText("Corretas: " + corretasT);

            modificou = true;

            if(!perguntaA.equals(perguntaT.getText())){
                MensagemMP = "Pergunta Mudada";
            }

            if(!corretasT.equals(corretasA)){
                MensagemMR = "Alternativas corretas Mudadas";
            }

            if(verificaMud){
                MensagemMR = "Alternativas Mudadas";
            }
        }

        if(modificou) {
            JOptionPane.showMessageDialog(null, MensagemMA + "\n" + MensagemMP + "\n" + MensagemMR);
            assuntoA = questao.getAssunto();
            perguntaA = questao.getPergunta();
            corretasA = ((Objetiva) questao).getCorretas();
        }
    }
}
