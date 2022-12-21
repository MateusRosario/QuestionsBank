package com.Telas.TelaGerar.TelaGerarSelecionar;


import com.Admin;
import com.ListaQuestoes.Questao.Questao;
import com.Telas.TelaGerar.TelaGerarProva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.function.UnaryOperator;

public class TelaBuscaGERAR extends JFrame  {
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
    List<Questao> resultados;
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
    //}------------------------------

    //}--------------------------------------
    JPanel selecionado;
    //Variaveis
    int adicionar;
    int Cont;
   public boolean avancar=false;
    TelaGerarProva telaGerarProva;

    List Selecionadas = new ArrayList();

    public TelaBuscaGERAR(Admin admin, int adicionar, TelaGerarProva telaGerarProva) {

        this.admin = admin;
        this.adicionar=adicionar;
        this.Cont=0;
        this.telaGerarProva=telaGerarProva;

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
        elementosPaineldireito();
        //---------------------------------------
        areaPesquisa.add(esquerdo);
        areaPesquisa.add(direito);
    }

    public void elementosPainelesquerdo() {
        formatL1(esquerdo);
        filtro = new JLabel("Filtrar Assunto:  ");
        formatL3(filtro);
        selecFiltro = new JComboBox<String>();

        selecFiltro.addItem("Sem Filtro");
        selecFiltro.setBackground(Color.WHITE);

        List<String> assuntos = admin.getNomeAssuntosList();

        for (int i = 0; i < assuntos.size(); i++) {
            selecFiltro.addItem(assuntos.get(i));
        }

        esquerdo.add(filtro);
        esquerdo.add(selecFiltro);
    }

    public void elementosPaineldireito() {
        formatL1(direito);
        barraPesquisa = new JTextField(15);
        pesquisar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-pesquisar_Black.png")));
        formatL1(pesquisar);
        pesquisar.addMouseListener(new GerarBuscaMouseListener(0, pesquisar, "Imagens/icons8-pesquisar", this, admin));

        direito.add(barraPesquisa);
        direito.add(pesquisar);
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

        adicionaListaQuestoes();

        scroll = new JScrollPane(listaQuestoes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setMinimumSize(new Dimension(100, 686));
        scroll.setPreferredSize(new Dimension(100, 686));

        lista.add(scroll, BorderLayout.NORTH);
    }

    public void elementosPainelBotton() {
        sair = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-sair_Black.png")));
        sair.addMouseListener(new GerarBuscaMouseListener(1, sair, "Imagens/icons8-sair", this, admin));

        resposta = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-informações_Black.png")));
        resposta.addMouseListener(new GerarBuscaMouseListener(2, resposta, "Imagens/icons8-informações", this, admin));

        editar = new JLabel(new ImageIcon(getClass().getResource("Imagens/icons8-add_Black.png")));
        editar.addMouseListener(new GerarBuscaMouseListener(3, editar, "Imagens/icons8-add", this, admin));

        botton.add(sair);
        botton.add(resposta);
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
                            resultados.get(i).getPergunta().length() : 22) + "..."));
            formatL4(painel);
            painel.addMouseListener(new GerarBuscaMouseListener(5, painel, "", this, admin));
            listaQuestoes.add(painel);
        }
    }

    public void pesquisarAction() {
        resultados.clear();
        String textPesquisa = barraPesquisa.getText();

        if (selecFiltro.getSelectedItem() == "Sem Filtro") {
            resultados = admin.search(textPesquisa);
        } else {
            resultados = admin.search(textPesquisa, selecFiltro.getSelectedItem() + "");
        }
        listaQuestoes.removeAll();

        adicionaListaQuestoes();

        listaQuestoes.repaint();
        listaQuestoes.validate();

        listaQuestoes.getSize();
        scroll.repaint();
        scroll.revalidate();
    }

    public void selecionarPainel(JPanel painel){
        if(selecionado!=null) {
            selecionado.setBackground(new Color(255, 255, 255));
        }
        selecionado=painel;
    }

    public void deselecionarPainel(JPanel painel){
        selecionado=null;
    }

    public JPanel getSelecionado(){
        return selecionado;
    }

    public void BotaoDone(){
        editar.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-ok_Black.png")));
        editar.addMouseListener(new GerarBuscaMouseListener(4,editar,"Imagens/icons8-ok",this,admin));
    }

    public void AddNaSelecionadas(long questionID){
        Selecionadas.add(questionID);
    }

    public List ListaCompleta(){
        return this.Selecionadas;
    }

    public void avançar(){
        telaGerarProva.MudarParaEscolher();
    }
}