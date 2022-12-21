package com.Telas.TelaCadastrarQuestao;

import com.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class TelaCadastrarQuestao {
    Admin admin;

    GridBagConstraints gbc = new GridBagConstraints();

    JFrame cadastrarQuestao = new JFrame("Cadastrar questão");

    //caso tenha dúvidas, essa identação deixa bem claro quem é adicionada em quem
    //painel principal no Border Layout=Center da janela
    JPanel main = new JPanel(new BorderLayout());
    //painel com as ações subsequentes janelas seguintes
    JPanel PainelPrincipal = new JPanel(new CardLayout());
    //==================================================================================\\
    JPanel painelSelecTipo = new JPanel(new GridBagLayout());
    JPanel painelcomCombobox = new JPanel(new GridBagLayout());
    JComboBox comboxTipoDeQuestoes = new JComboBox();
    JComboBox comboxListaDeAssuntos = new JComboBox();
    //===================================================================================\\

    //==================================================================================\\
    JPanel painelPerguntaAberta = new JPanel(new GridBagLayout());

    JTextField areaDePergunta = new JTextField("Digite a pergunta aqui ");
    JTextField areaDeResposta = new JTextField("Digite a resposta aqui ");
    JLabel mensagemDeErro = new JLabel("Não pode conter nulos!");

    //===================================================================================\\
    JPanel painelPerguntaMultipla = new JPanel(new BorderLayout());
    JPanel paineldePerguntaGeral = new JPanel(new FlowLayout());
    JPanel painelRespostaMultipla = new JPanel(new GridBagLayout());

    JTextField areaDePergunta1 = new JTextField("Digite a pergunta aqui ");

    JRadioButton[] alternativaBotao = new JRadioButton[]{
            new JRadioButton("(A)"),
            new JRadioButton("(B)"),
            new JRadioButton("(C)"),
            new JRadioButton("(D)"),
            new JRadioButton("(E)"),
            new JRadioButton("(F)")};

    JTextField alternativaTexto[] = new JTextField[]{
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField()};

    //====================================================================================\\
    JPanel painelVouF = new JPanel(new GridBagLayout());
    JPanel paineldePerguntaGeral1 = new JPanel(new GridBagLayout());
    JTextField areaDePergunta2 = new JTextField("Digite a pergunta aqui ");
    JPanel paineldeRespostaVouF = new JPanel(new GridLayout(1, 2));
    JComboBox comboBoxVouF = new JComboBox();

    //=====================================================================================\\

    JPanel painelConfirmar = new JPanel();
    JPanel painelConfirmarComMensagem = new JPanel();
    JLabel labelComTipoDeQuestao = new JLabel();

    //=====================================================================================\\
    JPanel painelConcluido = new JPanel(new GridBagLayout());
    JPanel painelComMensagens = new JPanel(new GridBagLayout());
    JLabel mensagem = new JLabel("Questão cadastrada com sucesso!!");


    //===================================================================================================\
    JPanel PainelInferior = new JPanel(new GridLayout(1, 3));
    JLabel botaoavanca;
    JLabel sair;
    JLabel botaovolta;

    //=====================================================================================================\\

    //ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ//
    public static boolean openWindow = false; //variavel estatica para saber se a janela foi aberta.


    public TelaCadastrarQuestao(Admin admin){
        this.admin=admin;

        openWindow=true;

        botaoavanca = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-avancar_Black.png")));
        sair = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
        botaovolta = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-voltar_Black.png")));

        for (int i = 0; i <admin.getNomeAssuntosList().size() ; i++) {
            comboxListaDeAssuntos.addItem(admin.getNomeAssuntosList().get(i));
        }

        comboxTipoDeQuestoes.addItem("Aberta");
        comboxTipoDeQuestoes.addItem("Objetiva");
        comboxTipoDeQuestoes.addItem("V ou F");

        PainelSelecionaTipoDeQuestao();
        PainelPerguntaAberta();
        PainelPerguntaObjetiva();
        PainelPerguntaVouF();
        PainelCadastroComSucesso();

        PainelPrincipal.add(painelSelecTipo);
        PainelPrincipal.add(painelPerguntaAberta, "Aberta");
        PainelPrincipal.add(painelPerguntaMultipla, "Objetiva");
        PainelPrincipal.add(painelVouF, "V ou F");
        PainelPrincipal.add(painelConcluido, "sucesso");

        main.add(PainelPrincipal);

        painelInferior();

        cadastrarQuestao.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                openWindow=false;
            }
        });


        cadastrarQuestao.add(main, BorderLayout.CENTER);
        cadastrarQuestao.add(PainelInferior, BorderLayout.SOUTH);

        cadastrarQuestao.setMinimumSize(new Dimension(400, 300));
        cadastrarQuestao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastrarQuestao.setLocationRelativeTo(null);
        cadastrarQuestao.setSize(400, 300);
        cadastrarQuestao.setVisible(true);
        cadastrarQuestao.setResizable(true);
    }

    public void PainelSelecionaTipoDeQuestao(){
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelcomCombobox.add(new JLabel("Selecione um assunto: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelcomCombobox.add(comboxListaDeAssuntos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelcomCombobox.add(new JLabel("Selecione um tipo de questão: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelcomCombobox.add(comboxTipoDeQuestoes, gbc);

        painelSelecTipo.add(painelcomCombobox);
        painelcomCombobox.setBackground(Color.WHITE);
        painelSelecTipo.setBackground(Color.WHITE);
    }

    public void PainelPerguntaAberta(){
        gbc.insets = new Insets(5, 0, 5, 0);


        //-------------------------------------------------------------------------------//
        gbc.gridx = 0;
        gbc.gridy = 1;                                                    //
        painelPerguntaAberta.add(new JLabel("Digite a pergunta: "), gbc);         //
        gbc.gridx = 1;
        gbc.gridy = 1;                                                  //
        areaDePergunta.setPreferredSize(new Dimension(250, 30));        //--AREA DE DIGITAR PERGUNTA
        areaDePergunta.addMouseListener(new TelaCadastrarQuestaoListener(0,areaDePergunta));  //
        painelPerguntaAberta.add(areaDePergunta, gbc);                             //
        //------------------------------------------------------------------------//
        //-------------------------------------------------------------------------------//
        gbc.gridx = 0;
        gbc.gridy = 2;                                                    //
        painelPerguntaAberta.add(new JLabel("Digite a resposta: "), gbc);         //
        gbc.gridx = 1;
        gbc.gridy = 2;                                                  //--AREA DE DIGITAR PERGUNTA
        areaDeResposta.addMouseListener(new TelaCadastrarQuestaoListener(0,areaDeResposta));    //
        areaDeResposta.setPreferredSize(new Dimension(250, 30));         //
        //--------------------------------------------------------------------------//

        painelPerguntaAberta.setBackground(Color.WHITE);
        painelPerguntaAberta.add(areaDeResposta, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mensagemDeErro.setForeground(new Color(196,22,24));
        painelPerguntaAberta.add(mensagemDeErro, gbc);
    }

    public void PainelPerguntaObjetiva(){
        //---------------------------------------------------------------------------//
        JLabel DigitePergunta = new JLabel("Digite a pergunta");
        paineldePerguntaGeral.add(DigitePergunta);                                  //
        DigitePergunta.setForeground(Color.WHITE);
        paineldePerguntaGeral.add(areaDePergunta1);                             //
        areaDePergunta1.setPreferredSize(new Dimension(250, 30));        //------AREA DE DIGITAR PERGUNTA
        areaDePergunta1.addMouseListener(new TelaCadastrarQuestaoListener(0,areaDePergunta1)); //

        //-------------------------------------------------------------------------//

        //TRAVANDO O TAMANHO DE TODAS AS CAIXAS DE TEXTOS POIS O GRID BAG LAYOUT ALTERA

        for (int i = 0; i < alternativaTexto.length; i++) {
            alternativaTexto[i].setPreferredSize(new Dimension(140, 25));
        }

        //ALTERNATIVAS DAS QUESTOES--------------------------//
        //------------------------------------------------------------------------------//
        do {                                                                            //
            int x = 0, y = 1;                                                          //
            //
            for (int i = 0; i < alternativaBotao.length; i++) {                      //
                gbc.gridx = x;                                                      //
                gbc.gridy = y;                                                     //
                painelRespostaMultipla.add(alternativaBotao[i], gbc);             //
                gbc.gridx = x + 1;                                               //
                gbc.gridy = y;                                                  //
                painelRespostaMultipla.add(alternativaTexto[i], gbc);          //------ESSE LAÇO COLOCA CADA BOTAO
                y++;                                                          //------EM SEU RESPECTIVO LUGAR
                if (y == 4) {                                                //-------NO PAINEL DAS ALTERNATIVAS
                    y = 1;                                                  //
                    x = 2;                                                 //
                }                                                         //
            }                                                            //
        } while (false);                                                  //
        //-------------------------------------------------------------//

        painelPerguntaMultipla.add(paineldePerguntaGeral, BorderLayout.NORTH);
        paineldePerguntaGeral.setBackground(new Color(0, 53, 105));
        painelPerguntaMultipla.add(painelRespostaMultipla, BorderLayout.CENTER);
        painelRespostaMultipla.setBackground(Color.WHITE);
    }

    public void PainelPerguntaVouF(){
        gbc.gridx = 0;
        gbc.gridy = 0;                                                      //
        paineldePerguntaGeral1.add(new JLabel("Digite a pergunta:"), gbc);         //
        gbc.gridx = 1;
        gbc.gridy = 0;                                                     //
        paineldePerguntaGeral1.add(areaDePergunta2, gbc);                              // ------AREA DE DIGITAR PERGUNTA
        //
        areaDePergunta2.setPreferredSize(new Dimension(250, 30));         //
        areaDePergunta2.addMouseListener(new TelaCadastrarQuestaoListener(0,areaDePergunta2));  //
        //--------------------------------------------------------------------------//

        //------------------------------------//
        comboBoxVouF.addItem("Verdadeiro");  //
        comboBoxVouF.addItem("Falso");      //--SELECIONAR VERDADEIRO OU FALSO
        gbc.gridx = 0;
        gbc.gridy = 1;          //
        painelVouF.add(comboBoxVouF, gbc); //
        //-------------------------------//

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelVouF.add(new JLabel("PS: lembre-se de adicionar 'É verdadeiro ou falso?'"), gbc);

        painelVouF.add(paineldePerguntaGeral1);
    }

    public void PainelCadastroComSucesso(){
        mensagem.setBackground(new Color(0x000000));


        painelComMensagens.add(mensagem);
        painelComMensagens.setBackground(Color.WHITE);
        painelConcluido.add(painelComMensagens);
        painelConcluido.setBackground(Color.WHITE);
    }

    public void painelInferior(){
        PainelInferior.add(botaovolta);
        PainelInferior.add(sair);
        PainelInferior.add(botaoavanca);
        //PainelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//criando espaçamento entre as celulas
        //------------------------------------------------------------------------------------------------/////
        botaoavanca.addMouseListener(new TelaCadastrarQuestaoListener(PainelPrincipal, 1, "Imagens/icons8-avancar", botaoavanca, comboxTipoDeQuestoes, this));
        sair.addMouseListener(new TelaCadastrarQuestaoListener(PainelPrincipal, 2, "Imagens/icons8-sair", sair, comboxTipoDeQuestoes, this));
        botaovolta.addMouseListener(new TelaCadastrarQuestaoListener(PainelPrincipal, -1, "Imagens/icons8-voltar", botaovolta, comboxTipoDeQuestoes, this));
    }

    public void cadastra(){
        if( ((String)comboxTipoDeQuestoes.getSelectedItem()).equals("Aberta") ){
            admin.addQuestao(areaDePergunta.getText(),
                    ((String) comboxListaDeAssuntos.getSelectedItem())
                    ,areaDeResposta.getText()
            );
            return;
        }
        if( ((String)comboxTipoDeQuestoes.getSelectedItem()).equals("Objetiva") ){

            int tamanho=0;

            for (int i = 0; i <alternativaTexto.length ; i++) {
                if(!alternativaTexto[i].getText().equals("")) {
                    tamanho++;
                }
            }
            String[] vetorderespostas= new String[tamanho];

            int j=0;

            for (int i = 0; i <tamanho ; i++)
            {
                for (j = 0; j <6 ; j++)
                {
                    if(!alternativaTexto[j].getText().equals("")) {
                        vetorderespostas[i]=alternativaTexto[j].getText();
                        alternativaTexto[j].setText("");
                        break;
                    }
                }
            }

            String resposta="";


            if (alternativaBotao[0].isSelected()) {
                resposta="A,";
            }
            if (alternativaBotao[1].isSelected()) {
                resposta=resposta+"B,";
            }
            if (alternativaBotao[2].isSelected()) {
                resposta=resposta+"C,";
            }
            if (alternativaBotao[3].isSelected()) {
                resposta=resposta+"D,";
            }
            if (alternativaBotao[4].isSelected()) {
                resposta=resposta+"E,";
            }
            if (alternativaBotao[5].isSelected()) {
                resposta=resposta+"F,";
            }

            resposta=resposta.substring(0,resposta.length()-1);

            for (int i = 0; i < vetorderespostas.length; i++) {
                System.out.println(vetorderespostas[i]);
            }


            admin.addQuestao(areaDePergunta1.getText(),(String)comboxListaDeAssuntos.getSelectedItem(),vetorderespostas,resposta);


            return;
        }
        if( ((String)comboxTipoDeQuestoes.getSelectedItem()).equals("V ou F") ){

            boolean VORF=false;

            if(comboBoxVouF.getSelectedItem().equals("Verdadeiro")){
                VORF=true;
            }
            admin.addQuestao(areaDePergunta2.getText(),
                    ((String) comboxListaDeAssuntos.getSelectedItem())
                    ,VORF);
            return;
        }
    }

    public boolean eufizrecursiva(int n){

        if(n==0){
            return !alternativaTexto[0].getText().equals("");
        }

        if(!alternativaTexto[n].getText().equals("") && alternativaTexto[n-1].getText().equals("")){
            return false;
        }
        return eufizrecursiva(n-1);

    }

    public boolean atualizaValidade() {


        if (comboxTipoDeQuestoes.getSelectedItem() == "Aberta") {
            if (((JTextField) painelPerguntaAberta.getComponent(1)).getText().equals("") ||
                    ((JTextField) painelPerguntaAberta.getComponent(1)).getText().equals("Digite a pergunta aqui ")) {

                return false;
            } else {
                if (((JTextField) painelPerguntaAberta.getComponent(3)).getText().equals("") ||
                        ((JTextField) painelPerguntaAberta.getComponent(3)).getText().equals("Digite a resposta aqui ")) {
                    return false;
                }

                return true;
            }

        }
        if(comboxTipoDeQuestoes.getSelectedItem()=="Objetiva"){

            if(eufizrecursiva(5)==false){
                return false;
            }

            int quantasForamPreenchidas=0;

            for (int i = 0; i <alternativaTexto.length ; i++) {
                if(!alternativaTexto[i].getText().equals("")){
                    quantasForamPreenchidas++;
                }
                if(alternativaTexto[i].getText().equals("") && alternativaBotao[i].isSelected()){
                    return false;
                }

            }
            if(quantasForamPreenchidas<3 ||
                    areaDePergunta1.equals("Digite a pergunta aqui ")||
                    areaDePergunta1.equals("")){
                return false;
            }

        }
        if(comboxTipoDeQuestoes.getSelectedItem()=="V ou F"){
            if(areaDePergunta2.getText().equals("Digite a pergunta aqui ")
                    || areaDePergunta2.getText().equals("")){
                return false;
            }
        }
        return true;
    }

    public void dispose(){
        cadastrarQuestao.dispose();
    }
}
