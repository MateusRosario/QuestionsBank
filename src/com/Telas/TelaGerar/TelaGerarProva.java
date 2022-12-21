package com.Telas.TelaGerar;

import com.Admin;
import com.ListaQuestoes.Questao.Questao;
import com.Telas.TelaGerar.TelaGerarSelecionar.TelaBuscaGERAR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class TelaGerarProva extends JFrame {
//Matheus Almeida

    Admin admin;
    CardLayout Card = new CardLayout();
    Container cont = getContentPane();
    List<Questao> questoes;

    JComboBox assuntosGerar=new JComboBox();
    Font f = new Font("SansSerif", Font.BOLD, 20);
    Font f2 = new Font("SansSerif", Font.ITALIC, 20);

    //PainelPrincipal

    JPanel PrincipalBorder;
    JPanel PrincipalGrid;
    JPanel CancelarWest ;
    JPanel ProximoEast ;


    JLabel vazioeast = new JLabel("                        ");
    JLabel vaziowest = new JLabel("                        ");
    JLabel vaziosouth = new JLabel(" " );
    JLabel vazionorth = new JLabel("                 " );
    JLabel BotaoFullAleatoria;
    JLabel BotaoAleatoriaAssunto;
    JLabel BotaoEscolherQuestao;
    JLabel BotaoProximo;
    JLabel BotaoCancelar;
    JComboBox Nquestoes;

    //Painel Full Aleatorias

    JPanel MainPainelFullAleatorio;
    GridBagConstraints Grid = new GridBagConstraints();
    Random radom = new Random();

    JLabel professor;
    JLabel instituicao;
    JLabel enviar;
    JLabel voltar;

    JTextField nomeProfessor;
    JTextField nomeInstituicao;

    // Painel por Assunto

    JPanel MainAssunto;

    JLabel professorAssunto;
    JLabel instituicaoAssunto;
    JLabel enviarAssunto;

    JTextField nomeProfessorAssunto;
    JTextField nomeInstituicaoAssunto;

    //Painel escolher a questao

    JPanel MainPainelEscolher;
    JLabel enviarEscolher;
    JTextField nomeEmail;

    TelaBuscaGERAR busca;
    int Ecolhido;

    //Tela de Gerar a Prova

    public TelaGerarProva(Admin admin){
        this.admin=admin;

        setLayout(Card);
        setSize(600,600);
        setVisible(true);

        setBackground(Color.WHITE);
        add(PainelPricipal(),"Mainpainel");

        add(PainelFullaleatorio(), "Fullpainel");

        add(PainelAssunto(), "Assuntopainel");

        add(PainelEscolherDados(),"Escolherpainel");

        setLocationRelativeTo(null);





    }

    public JPanel PainelPricipal(){

        PrincipalBorder = new JPanel(new BorderLayout());
        CancelarWest = new JPanel(new BorderLayout());
        ProximoEast = new JPanel(new BorderLayout());
        PrincipalGrid = new JPanel(new GridLayout(5,1));
        Font f = new Font("SansSerif", Font.BOLD, 20);
        JPanel ComboB = new JPanel(new BorderLayout());

        BotaoAleatoriaAssunto = new JLabel("           Questões aleatórias por assunto");
        BotaoAleatoriaAssunto.setOpaque(true);
        BotaoAleatoriaAssunto.setFont(f);
        BotaoAleatoriaAssunto.setBackground(Color.WHITE);

        BotaoFullAleatoria = new JLabel("          Questões totalmente aleatórias");
        BotaoFullAleatoria.setOpaque(true);
        BotaoFullAleatoria.setFont(f);
        BotaoFullAleatoria.setBackground(Color.WHITE);

        BotaoEscolherQuestao = new JLabel("          Escolher questoes da prova");
        BotaoEscolherQuestao.setFont(f);
        BotaoEscolherQuestao.setOpaque(true);
        BotaoEscolherQuestao.setBackground(Color.WHITE);

        BotaoProximo = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-next-page-filled-50.png")));

        BotaoCancelar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair-50.png")));
        CancelarWest.add(BotaoCancelar,BorderLayout.SOUTH);

        ProximoEast.add(BotaoProximo,BorderLayout.SOUTH);


        Nquestoes = new JComboBox();

        for (int i=0; i<admin.getQuestoes().size();i++){

            Nquestoes.addItem(1+i);

        }

        Nquestoes.setEnabled(false);
        Nquestoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BotaoProximo.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-divisa-circulada-à-direita-50.png")));

                BotaoProximo.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        switch (Ecolhido){
                            case 1:
                                Card.show(cont,"Fullpainel");
                                break;
                            case 2:
                                Card.show(cont,"Assuntopainel");
                                break;
                            case 3:
                                PainelEscolher();
                                break;
                        }

                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        BotaoProximo.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-divisa-circulada-a-direita-filled-50.png")));


                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        BotaoProximo.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-divisa-circulada-à-direita-50.png")));


                    }
                });
            };
        });
        MouseListenersPrincipal();

        PrincipalGrid.add(BotaoFullAleatoria);
        PrincipalGrid.add(BotaoAleatoriaAssunto);
        PrincipalGrid.add(BotaoEscolherQuestao);

       //Gambiarra///
        JLabel inf = new JLabel("Numero de Questoes ");
        JPanel infBorder = new JPanel(new BorderLayout());
        infBorder.add(inf,BorderLayout.EAST);
        ComboB.add(Nquestoes,BorderLayout.EAST);
        ComboB.add(infBorder,BorderLayout.CENTER);
        JPanel ComboB2= new JPanel(new BorderLayout());
        ComboB2.add(ComboB,BorderLayout.NORTH);
        PrincipalGrid.add(ComboB2);


        PrincipalBorder.add(PrincipalGrid,BorderLayout.CENTER);
        PrincipalBorder.add(vazionorth,BorderLayout.NORTH);

        PrincipalBorder.add(ProximoEast,BorderLayout.EAST);

        PrincipalBorder.add(CancelarWest,BorderLayout.WEST);
        PrincipalBorder.add(vaziosouth,BorderLayout.SOUTH);

        return PrincipalBorder;

    }

    public void MouseListenersPrincipal(){

        BotaoFullAleatoria.addMouseListener(new GerarProvaMouse(this,1,BotaoFullAleatoria,1));
        BotaoAleatoriaAssunto.addMouseListener(new GerarProvaMouse(this,1,BotaoAleatoriaAssunto,2));
        BotaoEscolherQuestao.addMouseListener(new GerarProvaMouse(this,1,BotaoEscolherQuestao,3));
        BotaoCancelar.addMouseListener(new GerarProvaMouse(this,2,BotaoCancelar,0));



    }

    public JPanel PainelFullaleatorio(){

        MainPainelFullAleatorio = new JPanel(new BorderLayout());
        Grid = new GridBagConstraints();


        JPanel CenterFullAleatorio = new JPanel(new GridBagLayout());
        JPanel EastFullAleatorio = new JPanel(new BorderLayout());
        JPanel WestFullAleatorio = new JPanel(new BorderLayout());


        //Norte
        JLabel titulo = new JLabel("Forneça as informações, para concluir a criação ");
        MainPainelFullAleatorio.add(titulo,BorderLayout.NORTH);

        //Centro
         professor = new JLabel("Nome do Professor :");
         professor.setFont(f);
         instituicao = new JLabel("Nome da Instituicao :");
         instituicao.setFont(f);
         nomeProfessor= new JTextField(10);
         nomeProfessor.setFont(f2);
         nomeProfessor.setForeground(new Color(101, 102, 246));
         nomeInstituicao= new JTextField(10);
         nomeInstituicao.setFont(f2);
         nomeInstituicao.setForeground(new Color(101, 102, 246));

         Grid.gridx=0;
         Grid.gridy=1;
        CenterFullAleatorio.add(professor,Grid);

        Grid.gridx=1;
        Grid.gridy=1;
        CenterFullAleatorio.add(nomeProfessor,Grid);
         Grid.gridx=0;
         Grid.gridy=3;
        CenterFullAleatorio.add(instituicao,Grid);

         Grid.gridx=1;
         Grid.gridy=3;
        CenterFullAleatorio.add(nomeInstituicao,Grid);

        MainPainelFullAleatorio.add(CenterFullAleatorio,BorderLayout.CENTER);

        //South


        WestFullAleatorio.add(VoltarMain(),BorderLayout.SOUTH);
        MainPainelFullAleatorio.add(WestFullAleatorio,BorderLayout.WEST);

        enviar=new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));




        enviar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                //Verifica o que tem nos Text, dai libera para enviar email

               if(nomeProfessor.getText().compareTo("0")>0 && nomeInstituicao.getText().compareTo("0")>0){


                   List<Integer> numeros = new ArrayList<Integer>();
                   questoes = new ArrayList<>();

                   for (int i=0; i < admin.getNumQuestoes(); i++) { //Sequencia da mega sena
                       numeros.add(i);
                   }
                   Collections.shuffle(numeros);

                   for (int j=0; j<Nquestoes.getSelectedIndex()+1;j++) {
                       questoes.add(admin.getQuestoes().get(numeros.get(j)));
                   }

                   TelaEnviar(nomeProfessor.getText(),nomeInstituicao.getText(),questoes);

                } else{
                  JOptionPane.showMessageDialog(null,"É necessario o nome do professor e a instituicao para gerar a prova !");

               }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                enviar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-filled-50.png")));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                enviar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));

            }
        });

        EastFullAleatorio.add(enviar,BorderLayout.SOUTH);
        MainPainelFullAleatorio.add(EastFullAleatorio,BorderLayout.EAST);




        return MainPainelFullAleatorio;
    }

    public JLabel VoltarMain(){
        voltar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-go-back-50.png")));

        //Volta para tela principal

        voltar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Ecolhido=0;
                nomeProfessor.setText("");
                nomeInstituicao.setText("");
                nomeInstituicaoAssunto.setText("");
                nomeProfessorAssunto.setText("");
                Card.show(cont,"Mainpainel");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                voltar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-go-back-filled-50.png")));

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                voltar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-go-back-50.png")));

            }
        });

        return voltar;
    }

    public JPanel PainelAssunto(){

        MainAssunto = new JPanel(new BorderLayout());
        Grid = new GridBagConstraints();

        JLabel titulo = new JLabel("Escolha o assunto e será gerada uma prova com as questões do mesmo");
        MainAssunto.add(titulo,BorderLayout.NORTH);

        JPanel CentroGrid = new JPanel(new GridBagLayout());
        JPanel WestBorder = new JPanel(new BorderLayout());
        JPanel EastBorder = new JPanel(new BorderLayout());

        professorAssunto = new JLabel("Nome do professor: ");
        professorAssunto.setFont(f);
        instituicaoAssunto = new JLabel("Nome da instituição: ");
        instituicaoAssunto.setFont(f);
        nomeProfessorAssunto = new JTextField(8);
        nomeProfessorAssunto.setFont(f2);
        nomeProfessorAssunto.setForeground(new Color(101, 102, 246));
        nomeInstituicaoAssunto = new JTextField(8);
        nomeInstituicaoAssunto.setFont(f2);
        nomeInstituicaoAssunto.setForeground(new Color(101, 102, 246));

        Grid.gridx=0;
        Grid.gridy=0;
        CentroGrid.add(professorAssunto,Grid);

        Grid.gridx=1;
        Grid.gridy=0;
        CentroGrid.add(nomeProfessorAssunto,Grid);

        Grid.gridx=0;
        Grid.gridy=1;
        CentroGrid.add(instituicaoAssunto,Grid);

        Grid.gridx=1;
        Grid.gridy=1;
        CentroGrid.add(nomeInstituicaoAssunto,Grid);

        for (int i=0; i<admin.getNomeAssuntosList().size();i++){

            assuntosGerar.addItem(admin.getNomeAssuntosList().get(i));
        }

        Grid.gridx=2;
        Grid.gridy=2;

        CentroGrid.add(assuntosGerar,Grid);

        MainAssunto.add(CentroGrid,BorderLayout.CENTER);

        WestBorder.add(VoltarMain(),BorderLayout.SOUTH);
        MainAssunto.add(WestBorder,BorderLayout.WEST);

        enviarAssunto=new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));




        enviarAssunto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                //Verifica o que tem nos Text, dai libera para enviar email

                if(nomeProfessorAssunto.getText().compareTo("0")>0 && nomeInstituicaoAssunto.getText().compareTo("0")>0){


                    List<Integer> numeros = new ArrayList<Integer>();
                    questoes = new ArrayList<>();
                    int novotam;
                    String assunto = (String)assuntosGerar.getSelectedItem();

                    for (int i=0; i < admin.getQuestoesPorAssunto(assunto).size(); i++) { //Sequencia da mega sena
                        numeros.add(i);
                    }
                    Collections.shuffle(numeros);

                    if (Nquestoes.getSelectedIndex()+1>admin.getQuestoesPorAssunto(assunto).size()){
                        novotam=admin.getQuestoesPorAssunto(assunto).size();

                    }else {
                        novotam=Nquestoes.getSelectedIndex()+1;
                    }

                    if(novotam==0){
                        JOptionPane.showMessageDialog(null,"Esse assunto não contem nenhuma questao");
                    }else{

                    for (int j=0; j<novotam;j++) {
                        questoes.add((Questao) admin.getQuestoesPorAssunto(assunto).get(numeros.get(j)));

                    }

                    TelaEnviar(nomeProfessorAssunto.getText(),nomeInstituicaoAssunto.getText(),questoes);}

                } else{
                    JOptionPane.showMessageDialog(null,"É necessario o nome do professor e a instituicao para gerar a prova !");

                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                enviarAssunto.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-filled-50.png")));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                enviarAssunto.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));

            }
        });

        EastBorder.add(enviarAssunto,BorderLayout.SOUTH);
        MainAssunto.add(EastBorder,BorderLayout.EAST);





        return MainAssunto;
    }

    public void PainelEscolher(){

       busca = new TelaBuscaGERAR(admin,Nquestoes.getSelectedIndex()+1,this);


    }

    public void MudarParaEscolher(){
        List IDs = new ArrayList();
        IDs=busca.ListaCompleta();
        questoes = new ArrayList<>();
        for (int i = 0; i < IDs.size()-1; i++) {
            questoes.add(admin.getQuestao((Long) IDs.get(i)));
        }
        Card.show(cont,"Escolherpainel");
    }


    public void GerarProva(String prof ,String inst, List<Questao>quest,int op) throws Exception {

        if(op==1){
            //Gera o arquivo com a prova e o Gabarito em pdf
            //new GerarPdf(prof,inst,quest);
            //new GabaritoPDF(prof,quest);

            new EnviarEmail("bancodequestoesuft@gmail.com", nomeEmail.getText().split(";"),"Prova Pdf Gerada","","Prova.pdf","Gabarito.pdf");

        }else{

            new GerarDocx(inst,prof,quest);
            new EnviarEmail("bancodequestoesuft@gmail.com", nomeEmail.getText().split(";"),"Prova Docx Gerada","","Prova.docx", "Gabarito.docx");

        }

    }


    public void TelaEnviar(String prof ,String inst, List<Questao>quest){

        JFrame telaEnviar = new JFrame();
        telaEnviar.setLayout(new BorderLayout());

        Grid = new GridBagConstraints();

        //Border do centro;
        JPanel CentroEnviar = new JPanel(new GridBagLayout());

        JPanel Aviso = new JPanel(new FlowLayout());
        JLabel aviso = new JLabel("Para enviar mais de um email, basta colocar ';' ponto e virgula entre eles");
        aviso.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,15));
        aviso.setForeground(new Color(120, 154, 241));
        Aviso.add(aviso);
        telaEnviar.add(Aviso,BorderLayout.NORTH);

        JLabel email = new JLabel("Email(s) para envio: ");
        nomeEmail = new JTextField(20);

        JRadioButton pdf = new JRadioButton("Gerar em PDF");
        JRadioButton docx = new JRadioButton("Gerar em Docx");
        ButtonGroup selecionado = new ButtonGroup();

        selecionado.add(pdf);
        selecionado.add(docx);


        Grid.gridx=0;
        Grid.gridy=0;
        CentroEnviar.add(email,Grid);
        Grid.gridx=1;
        Grid.gridy=0;
        CentroEnviar.add(nomeEmail,Grid);
        Grid.gridx=1;
        Grid.gridy=1;
        CentroEnviar.add(pdf,Grid);
        Grid.gridx=0;
        Grid.gridy=1;
        CentroEnviar.add(docx,Grid);

        telaEnviar.add(CentroEnviar,BorderLayout.CENTER);

        //Botao de cancelar
    JPanel CancelarWest = new JPanel(new BorderLayout());
    JLabel cancelar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-cancel-50.png")));

    cancelar.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            switch (Ecolhido){
                case 1:

                    nomeInstituicao.setText("");
                    nomeProfessor.setText("");

                    telaEnviar.dispose();
                    break;
                case 2:
                    nomeProfessorAssunto.setText("");
                    nomeInstituicaoAssunto.setText("");
                    telaEnviar.dispose();
                    break;
                case 3:
                    break;
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            cancelar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-cancel-filled-50.png")));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            cancelar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-cancel-50.png")));
        }
    });

    CancelarWest.add(cancelar,BorderLayout.SOUTH);
    telaEnviar.add(CancelarWest,BorderLayout.WEST);

    //Botao enviar email

        JPanel EnviarEast = new JPanel(new BorderLayout());
        JLabel Email = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-email-50.png")));

        Email.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if(nomeEmail.getText().compareTo("0")>0) {
                    if(pdf.isSelected()){
                        try {
                            GerarProva(prof,inst,quest,1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null,"Arquivo Enviado com sucesso !");
                        telaEnviar.dispose();
                        FecharFrameMain();

                         }else{
                        if (docx.isSelected()){

                            try {
                                GerarProva(prof,inst,quest,2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null,"Prova com sucesso !");
                            telaEnviar.dispose();
                            FecharFrameMain();

                        }else{
                            JOptionPane.showMessageDialog(null,"Escolha entre Docx ou PDF !");
                        }
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"É necessario pelo menos um email para enviar a prova");
                }


            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Email.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-email-filled-50.png")));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                Email.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-email-50.png")));
            }
        });

        EnviarEast.add(Email,BorderLayout.SOUTH);
        telaEnviar.add(EnviarEast,BorderLayout.EAST);

        telaEnviar.setSize(600,200);
        telaEnviar.setLocationRelativeTo(null);
        telaEnviar.setVisible(true);




    }

    public void FecharFrameMain(){

        this.dispose();

    }

    public JPanel PainelEscolherDados(){

        MainPainelEscolher = new JPanel(new BorderLayout());
        Grid = new GridBagConstraints();


        JPanel CenterFullAleatorio = new JPanel(new GridBagLayout());
        JPanel EastFullAleatorio = new JPanel(new BorderLayout());
        JPanel WestFullAleatorio = new JPanel(new BorderLayout());


        //Norte
        JLabel titulo = new JLabel("Forneça as informações, para concluir a criação ");
        MainPainelEscolher.add(titulo,BorderLayout.NORTH);

        //Centro
       JLabel professor = new JLabel("Nome do Professor :");
       professor.setFont(f);
        JLabel instituicao = new JLabel("Nome da Instituicao :");
        instituicao.setFont(f);
       JTextField nomeProfessor= new JTextField(10);
       nomeProfessor.setFont(f2);
       nomeProfessor.setForeground(new Color(101, 102, 246));
        JTextField nomeInstituicao= new JTextField(10);
        nomeInstituicao.setFont(f2);
        nomeInstituicao.setForeground(new Color(101, 102, 246));
        Grid.gridx=0;
        Grid.gridy=1;
        CenterFullAleatorio.add(professor,Grid);

        Grid.gridx=1;
        Grid.gridy=1;
        CenterFullAleatorio.add(nomeProfessor,Grid);
        Grid.gridx=0;
        Grid.gridy=3;
        CenterFullAleatorio.add(instituicao,Grid);

        Grid.gridx=1;
        Grid.gridy=3;
        CenterFullAleatorio.add(nomeInstituicao,Grid);

        MainPainelEscolher.add(CenterFullAleatorio,BorderLayout.CENTER);

        //South


        WestFullAleatorio.add(VoltarMain(),BorderLayout.SOUTH);
        MainPainelEscolher.add(WestFullAleatorio,BorderLayout.WEST);

        enviarEscolher=new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));




        enviarEscolher.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                //Verifica o que tem nos Text, dai libera para enviar email

                if(nomeProfessor.getText().compareTo("0")>0 && nomeInstituicao.getText().compareTo("0")>0){

                    TelaEnviar(nomeProfessor.getText(),nomeInstituicao.getText(),questoes);

                } else{
                    JOptionPane.showMessageDialog(null,"É necessario o nome do professor e a instituicao para gerar a prova !");

                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                enviarEscolher.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-filled-50.png")));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                enviarEscolher.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-secured-letter-50.png")));

            }
        });

        EastFullAleatorio.add(enviarEscolher,BorderLayout.SOUTH);
        MainPainelEscolher.add(EastFullAleatorio,BorderLayout.EAST);




        return MainPainelEscolher;
    }

}
