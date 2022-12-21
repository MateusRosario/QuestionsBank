package com.Telas.TelaPrincipal;


import com.*;
import com.Salvar.Salvar;
import com.Gerar.Gerar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaPrincipal extends JFrame{

    //'tile' sao os quadrados que agem como botoes, mas sao JPanel

    private String opcoes[] = {"Questões", "Assuntos", "Gerar"};
    public static  String jlabels[] = {"Cadastrar", "Listar", "Buscar", "Cadastrar ", "Listar ", "Gerar Formulario", "Gerar Prova"};

    //'telas' sao as partes visíveis
    private JPanel menuEsquerda;    //menu que da acesso as quatro categorias de operacoes diferentes
    private JPanel principalDireita;    //parte maior onde ficam os tiles
    private JPanel telaQuestoes;    //tela referente ao primeiro botao do menu
    private JPanel telaAssuntos;    //tela referente ao segundo botao do menu
    private JPanel telaGerar;   //tela referente ao terceiro botao do menu
    private JList listMenu;     //a lista com as opcoes do menu

    //cores mais utilizadas no programa
    private Color azulEscuro;
    private Color rotulosTiles;

    //card layout usado para as trocas de tela no inicio
    private CardLayout cartoes;
    private GridBagConstraints posicoes;

    private EmptyBorder bordaTiles;
    private Font fonteTiles;

    //Objetos recebidos como parametro da classe Primaria
    Admin admin;
    Gerar gerar;
    Salvar salvar;


    public TelaPrincipal(Admin admin, Gerar gerar, Salvar salvar){
        this.admin = admin;
        this.gerar = gerar;
        this.salvar = salvar;
        setLayout(new BorderLayout());

        //inicializacao de todos os componentes possiveis
        azulEscuro = new Color(0, 53, 101);
        rotulosTiles = new Color(0, 0, 0);

        //JPanel que contem a JList menu
        menuEsquerda = new JPanel();

        fonteTiles = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

        //JPanel maior que tem cardlayout e guarda as telas
        principalDireita = new JPanel(new CardLayout());
        telaQuestoes = new JPanel(new GridBagLayout());
        telaAssuntos = new JPanel(new GridBagLayout());
        telaGerar = new JPanel(new GridBagLayout());
        posicoes = new GridBagConstraints();
        bordaTiles = new EmptyBorder(20,20,20,20);

        posicoes.anchor = GridBagConstraints.CENTER;
        posicoes.fill = GridBagConstraints.HORIZONTAL;
        posicoes.anchor = GridBagConstraints.CENTER;
        posicoes.insets = new Insets(10,10,10,10);

        //a JList e inicializada com o array de String opcoes
        listMenu = new JList(opcoes);

        //a borda da lista e configurada
        listMenu.setBorder(new EmptyBorder(25,0,0,0));
        //o metodo setCellRenderer recebe um objeto da classe que foi criada especificamente para isso, ela personaliza as celulas do JList como se fossem um JLabel
        listMenu.setCellRenderer(new ColoredCellRenderer());
        //o JPanel da esquerda recebe a lista
        menuEsquerda.add(listMenu);
        menuEsquerda.setBackground(azulEscuro);
        listMenu.setBackground(azulEscuro);
        listMenu.setFixedCellWidth(150);
        listMenu.setFixedCellHeight(35);

        cartoes = (CardLayout) principalDireita.getLayout();

        //o configuraTile, recebe qualquer numero de Strings e, para cada uma cria um JLabel (texto) com o icone que estiver com o mesmo nome,
        configuraTile(telaQuestoes, jlabels[0], jlabels[1], jlabels[2]);
        configuraTile(telaAssuntos, jlabels[3], jlabels[4]);
        configuraTile(telaGerar, jlabels[5], jlabels[6]);


        //chama os listeners do menu (JList)
        listeners();

        //as telas sao adicionadas ao cardlayout com um identificador que permite que sejam chamadas depois
        principalDireita.add("Questoes",telaQuestoes);
        principalDireita.add("Assuntos", telaAssuntos);
        principalDireita.add("Gerar", telaGerar);

        //o fundo das quatro telas é definido
        telaQuestoes.setBackground(new Color(214, 214, 214));
        telaAssuntos.setBackground(new Color(214, 214, 214));
        telaGerar.setBackground(new Color(214, 214, 214));
        add(menuEsquerda,BorderLayout.WEST);
        add(principalDireita, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                salvar.Salvar();
            }
        });

        this.setIconImage(new ImageIcon(getClass().getResource("passed-exam.png")).getImage());
        setTitle("Gerenciador de Simulados");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void listeners(){
        listMenu.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                switch (listMenu.getSelectedIndex()){
                    case 0: cartoes.show(principalDireita,"Questoes");break;
                    case 1: cartoes.show(principalDireita,"Assuntos");break;
                    case 2: cartoes.show(principalDireita,"Gerar");break;
                }
            }
        });
    }

    public void configuraTile(JPanel tela, String ... labels){

        int controle = 0;
        posicoes.gridx = 0;
        posicoes.gridy = 0;
        JPanel tile = null;

        
        for(String olabel : labels){

            tile = new JPanel(new BorderLayout());
            controle++;
            posicoes.gridx++;
            
            if(olabel.equals(jlabels[2])){
                posicoes.gridwidth = 2;
            }
            if((controle+1)%2 == 0){
                posicoes.gridx = 0;
                posicoes.gridy++;
            }


            JLabel label = new JLabel(new ImageIcon(getClass().getResource(olabel.toLowerCase().trim().replace(" ", "_")+"_icon_black.png")));

            label.setText(olabel);
            label.setFont(fonteTiles);
            label.setForeground(rotulosTiles);
            label.setBorder(new EmptyBorder(10,25,0,0));

            tile.addMouseListener(new TileListener(olabel, tile, label , admin));
            tile.add(label, BorderLayout.NORTH);
            tile.setBorder(bordaTiles);
            tile.setBackground(TileListener.estadoPadrao);


            tela.add(tile, posicoes);
            posicoes.gridwidth = 1;
        }

    }
}