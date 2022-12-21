package com.Telas.TelaListarAssunto;

import com.Admin;
import com.ListaQuestoes.Assunto;
import com.ListaQuestoes.Questao.Questao;
import com.Telas.TelaBusca.TelaBusca;
import com.Telas.TelaPrincipal.TileListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TelaListarAssuntos extends JFrame {


    JPanel painelPrincipal;
    JPanel painelBaixo;
    JList listaAssuntos;
    JList listaQtds;
    JList listaDatas;
    Admin admin;
    JButton apagar;
    JButton info;
    JButton sair;
    JButton editar;
    JButton criarTxt;
    JPanel informacoes;
    JPanel informacoesPrincipal;

    GridBagConstraints posicao;
    static boolean toggle;

    public TelaListarAssuntos(Admin admin) {
        this.admin = admin;
        this.painelPrincipal = new JPanel(new GridBagLayout());
        this.painelBaixo = new JPanel(new GridLayout());

        DefaultListModel df = new DefaultListModel();
        DefaultListModel df2 = new DefaultListModel();
        DefaultListModel df3 = new DefaultListModel();

        for(String a : admin.getNomeAssuntosList()){
            df.addElement(a);
        }
        for(String a: contaNumeroDeQuestoes()){
            df2.addElement(a);
        }
        for(String a : listaAsDatas()){
            df3.addElement(a);
        }


        this.listaAssuntos = new JList(df);
        this.listaQtds = new JList(df2);
        this.listaDatas = new JList(df3);
        this.apagar = new JButton(new ImageIcon(getClass().getResource("icons8-excluir_Black.png")));
        this.info = new JButton(new ImageIcon(getClass().getResource("icons8-informacoes_Black.png")));
        this.editar = new JButton(new ImageIcon(getClass().getResource("icons8-editar_Black.png")));
        this.criarTxt = new JButton(new ImageIcon(getClass().getResource("gerar_txt_icon_black.png")));
        this.informacoes = new JPanel(new GridLayout(1, 4));
        this.informacoesPrincipal = new JPanel(new GridBagLayout());
        this.posicao = new GridBagConstraints();
        this.sair = new JButton(new ImageIcon(getClass().getResource("icons8-sair_Black.png")));

        apagar.setBorderPainted(false);
        apagar.setBackground(new Color(214, 214, 214));
        info.setBorderPainted(false);
        info.setBackground(new Color(214, 214, 214));
        sair.setBackground(new Color(214, 214, 214));
        sair.setBorderPainted(false);
        editar.setBorderPainted(false);
        editar.setBackground(new Color(214, 214, 214));
        criarTxt.setBackground(new Color(214, 214, 214));
        criarTxt.setBorderPainted(false);

        posicao.gridx = posicao.gridy = 0;
        posicao.fill = GridBagConstraints.BOTH;
        posicao.weightx = 1;
        posicao.weighty = 1;
        posicao.anchor = GridBagConstraints.NORTH;
        posicao.insets = new Insets(0, 1, 0, 0);

        painelPrincipal.setBackground(new Color(0, 65, 124));

        info.setMinimumSize(new Dimension(5, 5));
        apagar.setMinimumSize(new Dimension(5, 5));

        JLabel assunto = new JLabel("Assunto");
        JLabel nQuestoes = new JLabel("Nº de questões");
        JLabel data = new JLabel("Data");

        data.setForeground(new Color(250, 250, 250));
        data.setBackground(new Color(0, 65, 124));
        assunto.setForeground(new Color(250, 250, 250));
        assunto.setBackground(new Color(0, 65, 124));
        nQuestoes.setForeground(new Color(250, 250, 250));
        nQuestoes.setBackground(new Color(0, 65, 124));

        painelPrincipal.add(assunto, posicao);
        posicao.gridx = 1;
        painelPrincipal.add(nQuestoes, posicao);
        posicao.gridx = 2;
        painelPrincipal.add(data, posicao);
        posicao.gridx = 0;
        posicao.gridy = 1;
        posicao.weighty = 5;
        painelPrincipal.add(listaAssuntos, posicao);
        posicao.gridx = 1;
        painelPrincipal.add(listaQtds, posicao);
        posicao.gridx = 2;
        painelPrincipal.add(listaDatas, posicao);

        painelBaixo.add(apagar);
        painelBaixo.add(info);
        painelBaixo.add(sair);
        painelBaixo.add(editar);
        painelBaixo.add(criarTxt);
        posicao.gridx = posicao.gridy = 0;
        posicao.weightx = 1;
        posicao.weighty = 1;
        posicao.fill = GridBagConstraints.VERTICAL;



        listaAssuntos.setCellRenderer(new CellRendererAssuntos());
        listaQtds.setCellRenderer(new CellRendererAssuntos());
        listaDatas.setCellRenderer(new CellRendererAssuntos());

        listaAssuntos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listaQtds.setSelectedIndex(listaAssuntos.getSelectedIndex());
                listaDatas.setSelectedIndex(listaAssuntos.getSelectedIndex());
            }
        });

        listaQtds.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listaAssuntos.setSelectedIndex(listaQtds.getSelectedIndex());
                listaDatas.setSelectedIndex(listaQtds.getSelectedIndex());
            }
        });
        listaDatas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listaAssuntos.setSelectedIndex(listaDatas.getSelectedIndex());
                listaQtds.setSelectedIndex(listaDatas.getSelectedIndex());
            }
        });


        setLayout(new BorderLayout());

        add(painelPrincipal, BorderLayout.CENTER);
        add(painelBaixo, BorderLayout.SOUTH);

        apagar.addMouseListener(new ListenerBotoes(1, apagar, admin,listaAssuntos, listaQtds, listaDatas, this));
        info.addMouseListener(new ListenerBotoes(2, info, admin,listaAssuntos, listaQtds, listaDatas, this));
        sair.addMouseListener(new ListenerBotoes(3, sair, admin, listaAssuntos, listaQtds, listaDatas, this));
        editar.addMouseListener(new ListenerBotoes(4,editar, admin, listaAssuntos, listaQtds, listaDatas,this));
        criarTxt.addMouseListener(new ListenerBotoes(5, criarTxt, admin, listaAssuntos, listaQtds, listaDatas, this));
        setVisible(true);
        setSize(500, 300);
        setLocationRelativeTo(null);

    }

    public List<String> contaNumeroDeQuestoes() {

        List<String> listaNumeros = new ArrayList<String>();

        for (String nome : admin.getNomeAssuntosList()) {
            listaNumeros.add(" " + admin.getQuestoesPorAssunto(nome).size());

        }

        return listaNumeros;
    }
    public List<String> listaAsDatas(){

        List<String> listaDatas = new ArrayList<>();

        for(String assunto : admin.getNomeAssuntosList()){
            listaDatas.add(admin.getAssunto(assunto).getData());
        }

        return listaDatas;
    }



}

class CellRendererAssuntos extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {


        if (value != null)
            setText("" + value.toString());

        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(194, 194, 194)));
        if (isSelected) {
            setBackground(new Color(105, 184, 184));
            setForeground(new Color(255, 255, 255));
            setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        } else {

            setBackground(list.getBackground());
            setForeground(new Color(0, 0, 0));
            setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        }
        return this;
    }
}

class ListenerBotoes implements MouseListener {

    Admin admin;
    int id;
    JFrame janela;
    JList listaAssuntos;
    JList listaQts;
    JList listaDatas;
    JButton button;

    public ListenerBotoes(int id, JButton button, Admin admin, JList listaAssuntos, JList listaQuantidades, JList listaDatas, JFrame janela) {


        this.admin = admin;
        this.id = id;
        this.janela = janela;
        this.listaAssuntos = listaAssuntos;
        this.button = button;
        this.listaQts = listaQuantidades;
        this.listaDatas = listaDatas;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        button.setBackground(TileListener.clicado);

        if(id == 1){
            if(!listaAssuntos.isSelectionEmpty()) {
                admin.removeAssunto(listaAssuntos.getSelectedValue().toString());
                listaAssuntos.setListData(admin.getNomeAssuntosList().toArray());
                listaQts.setListData(contaNumeroDeQuestoes().toArray());
                listaDatas.setListData(listaAsDatas().toArray());
            }
        }
        else if (id == 2) {
            if(!listaAssuntos.isSelectionEmpty()) {
                TelaBusca tb = new TelaBusca(admin, listaAssuntos.getSelectedValue().toString());
            }
        } else if (id == 3) {
            janela.dispose();
        }
        else if(id == 4){
            if(!listaAssuntos.isSelectionEmpty()){
                EditarAssunto editarAssunto = new EditarAssunto(admin, listaAssuntos.getSelectedValue().toString(), listaAssuntos);
            }
        }
        else if(id == 5){
            CriarTxt criarTxt = new CriarTxt(admin, admin.getAssunto(listaAssuntos.getSelectedValue().toString()), listaAssuntos.getSelectedValue().toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button.setBackground(TileListener.clicado);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(TileListener.semClicar);

        if (id == 1) {
            button.setIcon(new ImageIcon(getClass().getResource("icons8-excluir_White.png")));
        } else if (id == 2) {
            button.setIcon(new ImageIcon(getClass().getResource("icons8-informacoes_White.png")));
        }
        else if(id == 3){
            button.setIcon(new ImageIcon(getClass().getResource("icons8-sair_White.png")));
        }
        else if(id == 4){
            button.setIcon(new ImageIcon(getClass().getResource("icons8-editar_White.png")));
        }
        else if(id == 5){
            button.setIcon(new ImageIcon(getClass().getResource("gerar_txt_icon_white.png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(new Color(214, 214, 214));

        if (id == 1) {
            button.setIcon(new ImageIcon(getClass().getResource("icons8-excluir_Black.png")));
        } else if (id == 2) {
            button.setIcon(new ImageIcon(getClass().getResource("icons8-informacoes_Black.png")));
        }
        else if(id == 3){
            button.setIcon(new ImageIcon(getClass().getResource("icons8-sair_Black.png")));
        }
        else if(id == 4){
            button.setIcon(new ImageIcon(getClass().getResource("icons8-editar_Black.png")));
        }
        else if(id == 5){
            button.setIcon(new ImageIcon(getClass().getResource("gerar_txt_icon_black.png")));
        }
    }



    public List<String> contaNumeroDeQuestoes() {

        List<String> listaNumeros = new ArrayList<String>();

        for (String nome : admin.getNomeAssuntosList()) {
            listaNumeros.add(" " + admin.getQuestoesPorAssunto(nome).size());

        }

        return listaNumeros;
    }
    public List<String> listaAsDatas(){

        List<String> listaDatas = new ArrayList<>();

        for(String assunto : admin.getNomeAssuntosList()){
            listaDatas.add(admin.getAssunto(assunto).getData());
        }

        return listaDatas;
    }

}

