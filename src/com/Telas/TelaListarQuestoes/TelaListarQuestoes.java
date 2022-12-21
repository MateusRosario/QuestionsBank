package com.Telas.TelaListarQuestoes;

import com.Admin;
import com.ListaQuestoes.Questao.Questao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaListarQuestoes extends JFrame {
    //object admin, recebido como parametro{
    private Admin admin;
    //}-----------------------------------

    //Objeto Painel Main{
    private JPanel main;

    //Objetos do Painel areaPesquisa{
    private JPanel areaPesquisa;
    //Objetos do Painel esquerdo{
    JPanel esquerdo;
    private JLabel filtro;
    private JComboBox<String> selecFiltro;
    //}----------------------------
    //Objetos do Painel direito{
    JPanel direito;
    private JTextField barraPesquisa;
    private JLabel pesquisar;
    //}---------------------------
    //}-------------------------------

    //Objetos do Painel table{
    private JPanel table;
    //Objetos do Painel cabeçalho{
    JPanel cabecalho;
    JLabel ID;
    JLabel assunto;
    JLabel tipo;
    JLabel pergunta;
    //----------------------------
    //Objetos do Painel lista{
    JPanel lista;
    java.util.List<Questao> resultados;
    private JPanel listaQuestoes;
    private JScrollPane scroll;
    //}---------------------------------
    //}--------------------------------

    //Objetos do Painel botton{
    private JPanel botton;
    JLabel sair;
    JLabel resposta;
    JLabel excluir;
    JLabel editar;
    ListMouseListeners[] ListMouseListenerss = new ListMouseListeners[3];
    int tamBuscaMouse=0;
    //}------------------------------

    //}--------------------------------------
    JPanel selecionado;

    public static boolean openWindow = false;

    //int pra separar se e a tela de busca principal ou foi chamada pela ListarAssuntos
    int i = 1;
    //string recebida, que vai deixar o Jcombobox mostrando só esse assunto
    String assuntoPassadoComoFiltro;

    public TelaListarQuestoes(Admin admin) {
        this.admin = admin;
        main = new JPanel(new BorderLayout());
        this.openWindow=true;
        elementosPainelMain();
        add(main);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                openWindow=false;
            }
        });

        //----------------------
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public TelaListarQuestoes(Admin admin, String assunto) {
        this.admin = admin;
        this.i = 2;
        this.assuntoPassadoComoFiltro = assunto;
        main = new JPanel(new BorderLayout());
        elementosPainelMain();
        add(main);
        //----------------------
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void elementosPainelMain() {
        areaPesquisa = new JPanel(new GridLayout(1, 2));
        elementosPainelAreaPesquisa();
        main.add(areaPesquisa, BorderLayout.NORTH);

        table = new JPanel(new BorderLayout());
        elementosPainelTable();
        main.add(table, BorderLayout.CENTER);

        botton = new JPanel(new GridLayout(1, 4));
        elementosPainelBotton();
        main.add(botton, BorderLayout.SOUTH);
    }

    public void elementosPainelAreaPesquisa() {
        formatL1(areaPesquisa);
        //-----------------------------------------------
        esquerdo = new JPanel(new FlowLayout());
        elementosPainelesquerdo();
        //-----------------------------------
        direito = new JPanel(new FlowLayout());
        //---------------------------------------
        areaPesquisa.add(esquerdo);
    }

    public void elementosPainelesquerdo() {
        formatL1(esquerdo);
        filtro = new JLabel("Filtrar Assunto:  ");
        formatL3(filtro);
        selecFiltro = new JComboBox<String>();
        selecFiltro.setBackground(Color.WHITE);

        if(this.i == 1) {
            selecFiltro.addItem("Sem Filtro");
            selecFiltro.setBackground(Color.WHITE);

            List<String> assuntos = admin.getNomeAssuntosList();

            for (int i = 0; i < assuntos.size(); i++) {
                selecFiltro.addItem(assuntos.get(i));
            }
        }
        else if(i == 2){
            selecFiltro.addItem("Sem Filtro");
            selecFiltro.addItem(assuntoPassadoComoFiltro);
            selecFiltro.setSelectedIndex(1);
        }
        esquerdo.add(filtro);
        esquerdo.add(selecFiltro);
    }

    public void elementosPainelTable() {
        cabecalho = new JPanel(new GridLayout(1, 4));
        elementosPainelCabecalho();
        lista = new JPanel(new BorderLayout());
        formatL2(lista);
        elementosPainelLista();
        table.add(cabecalho, BorderLayout.NORTH);
        table.add(lista, BorderLayout.CENTER);

    }

    public void elementosPainelCabecalho() {
        ID = new JLabel("                           ID");
        formatL2(ID);
        assunto = new JLabel("                       Assunto");
        formatL2(assunto);
        tipo = new JLabel("                    Tipo");
        formatL2(tipo);
        pergunta = new JLabel("                 pergunta");
        formatL2(pergunta);
        cabecalho.add(ID);
        cabecalho.add(assunto);
        cabecalho.add(tipo);
        cabecalho.add(pergunta);
    }

    public void elementosPainelLista() {
        resultados = new ArrayList<Questao>();

        listaQuestoes = new JPanel(new GridLayout(resultados.size(), 1));
        listaQuestoes.setBackground(Color.WHITE);

        selecFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AtualizarLista();
            }
        });

        if (selecFiltro.getSelectedItem() == "Sem Filtro") {
            resultados = admin.getQuestoes();
        } else {
            resultados = admin.getQuestoesPorAssunto(selecFiltro.getSelectedItem() + "");
        }

        adicionaListaQuestoes();

        scroll = new JScrollPane(listaQuestoes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setMinimumSize(new Dimension(100, 686));
        scroll.setPreferredSize(new Dimension(100, 686));

        lista.add(scroll, BorderLayout.NORTH);
    }

    public void elementosPainelBotton() {
        sair = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
        sair.addMouseListener(new ListMouseListeners(1, sair, "Imagens/icons8-sair", this, admin));

        resposta = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-informações_White.png")));
        resposta.setOpaque(true);
        resposta.setBackground(new Color(210, 208, 208));
        resposta.addMouseListener(new ListMouseListeners(2, resposta, "Imagens/icons8-informações", this, admin));

        excluir = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-excluir_White.png")));
        excluir.setOpaque(true);
        excluir.setBackground(new Color(210, 208, 208));
        excluir.addMouseListener(new ListMouseListeners(3, excluir, "Imagens/icons8-excluir", this, admin));

        editar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-editar_White.png")));
        editar.setOpaque(true);
        editar.setBackground(new Color(210, 208, 208));
        editar.addMouseListener(new ListMouseListeners(4, editar, "Imagens/icons8-editar", this, admin));

        botton.add(sair);
        botton.add(resposta);
        botton.add(excluir);
        botton.add(editar);
    }

    public void formatL1(JLabel label) {
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255));
    }

    public void formatL2(JLabel label) {
        label.setOpaque(true);
        Color azulEscuro = new Color(0, 53, 101);
        label.setBackground(azulEscuro);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createBevelBorder(1, azulEscuro, azulEscuro, azulEscuro, Color.WHITE));
    }

    public void formatL3(JLabel label) {
        label.setOpaque(true);
        Color azulEscuro = new Color(0, 53, 101);
        label.setBackground(azulEscuro);
        label.setForeground(Color.WHITE);
    }

    public void formatL4(JPanel painel) {
        painel.setOpaque(true);
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
    }

    public void formatL1(JPanel painel) {
        painel.setBackground(new Color(0, 53, 105));
    }

    public void formatL2(JPanel painel) {
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createBevelBorder(1));
    }

    public void format(GridBagConstraints grid, int x, int y, int tam) {
        grid.gridx = x;
        grid.gridy = y;
        grid.gridwidth = tam;
    }

    public void adicionaListaQuestoes() {
        for (int i = 0; i < resultados.size(); i++) {
            JPanel painel = new JPanel(new GridLayout(1, 4));
            painel.add(new JLabel(" " + resultados.get(i).getID()));
            painel.add(new JLabel("  " + resultados.get(i).getAssunto()));
            painel.add(new JLabel("   " + resultados.get(i).getTipo()));
            painel.add(new JLabel("    " + resultados.get(i).getPergunta().substring(0,
                    resultados.get(i).getPergunta().length() <= 24 ?
                            resultados.get(i).getPergunta().length() : 22) + (resultados.get(i).getPergunta().length() <= 24? "":"...")));
            formatL4(painel);
            painel.addMouseListener(new ListMouseListeners(5, painel, "", this, admin));
            listaQuestoes.add(painel);
        }
    }

    public void AtualizarLista() {
        resultados.clear();

        if (selecFiltro.getSelectedItem() == "Sem Filtro") {
            resultados = admin.getQuestoes();
        } else {
            resultados = admin.getQuestoesPorAssunto(selecFiltro.getSelectedItem() + "");
        }
        listaQuestoes.removeAll();

        adicionaListaQuestoes();

        listaQuestoes.repaint();
        listaQuestoes.validate();

        listaQuestoes.getSize();
        scroll.repaint();
        scroll.revalidate();

        for(int i=0;i<3;i++) {
            ListMouseListenerss[i].block();
        }
        selecionarPainel(null);
    }

    public void selecionarPainel(JPanel painel){
        if(selecionado!=null) {
            selecionado.setBackground(new Color(255, 255, 255));
        }
        selecionado=painel;

        for(int i=0;i<3;i++) {
            ListMouseListenerss[i].block();
        }
    }

    public void deselecionarPainel(JPanel painel){
        selecionado=null;
        for(int i=0;i<3;i++) {
            ListMouseListenerss[i].block();
        }
    }

    public JPanel getSelecionado(){
        return selecionado;
    }

    public boolean isSelecionado(){
        if(selecionado!=null){
            return true;
        }
        return false;
    }

    public void saveMouseListener(ListMouseListeners ListMouseListeners){
        this.ListMouseListenerss[tamBuscaMouse]=ListMouseListeners;
        tamBuscaMouse++;
    }
}