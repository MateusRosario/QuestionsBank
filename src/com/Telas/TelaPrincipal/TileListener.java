package com.Telas.TelaPrincipal;

import com.Telas.TelaBusca.TelaBusca;
import com.Telas.TelaCadastrarQuestao.TelaCadastrarQuestao;
import com.Telas.TelaCadastrarAssunto.TelaCadastrarAssunto;
import com.Admin;
import com.Telas.TelaGerar.TelaGerarProva;
import com.Telas.TelaGerarCsv.GerarCsv;
import com.Telas.TelaListarAssunto.TelaListarAssuntos;
import com.Telas.TelaListarQuestoes.TelaListarQuestoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileListener implements MouseListener {

    String id;
    JPanel content;
    JLabel rotulo;
    public static Color estadoPadrao = new Color(239, 239, 239);
    public static Color clicado = new Color(115, 0, 0);
    public static Color semClicar = new Color(177, 0, 0);

    //Objetos recebidos como parametro da classe TelaPrincipal
    Admin admin;
    TelaCadastrarQuestao telacadastrarQuestao;

    public TileListener(String id, JPanel tile, JLabel rotulo, Admin admin){
        this.admin = admin;
        this.id = id;
        this.content = tile;
        this.rotulo = rotulo;
    }

    public void mouseClicked(MouseEvent arg0) {

        //aqui coloca o que e pra acontecer no clique de cada botao, esta na mesma ordem deles
       if(id.equals("Cadastrar")){
           if(TelaCadastrarQuestao.openWindow==false) {
               telacadastrarQuestao = new TelaCadastrarQuestao(admin);
           }
       }
       else if(id.equals("Listar")){
           if(TelaListarQuestoes.openWindow==false) {
               TelaListarQuestoes telaListarQuestoes = new TelaListarQuestoes(admin);
           }
       }
        else if(id.equals("Listar Por Assunto")){

       }
        //"Cadastrar", "Listar", "Listar Por Assunto", "Buscar", "Cadastrar ", "Listar ", "Gerar Formulario", "Gerar Prova", "Gerar txt", "Enviar Email"};
        else if(id.equals("Buscar")){
           if(TelaBusca.openWindow==false) {
               TelaBusca telaBusca = new TelaBusca(admin);
           }
        }
        else if(id.equals("Cadastrar ")){
            TelaCadastrarAssunto telaCadastrarAssunto = new TelaCadastrarAssunto(admin);
        }
        else if(id.equals("Listar ")){
            TelaListarAssuntos telaListarAssuntos = new TelaListarAssuntos(admin);
        }
        else if(id.equals("Gerar Formulario")){
           GerarCsv gerarCsv = new GerarCsv(admin);
        }
        else if(id.equals("Gerar Prova")){
           TelaGerarProva telaGerarProva = new TelaGerarProva(admin);
        }
        else if(id.equals("Gerar txt")){

        }
        else if(id.equals("Enviar Email")){

        }


    }// evento q sera executado caso o mouse click no label

    public void mouseEntered(MouseEvent arg0) {
        content.setBackground(semClicar);
        rotulo.setForeground(Color.WHITE);

        rotulo.setIcon(new ImageIcon(getClass().getResource(id.trim().toLowerCase().replace(" ", "_") + "_icon_white.png")));

    }// evento q sera executado caso o mouse entre no label

    public void mouseExited(MouseEvent arg0) {
        content.setBackground(estadoPadrao);
        rotulo.setForeground(Color.BLACK);

        rotulo.setIcon(new ImageIcon(getClass().getResource(id.toLowerCase().trim().replace(" ", "_") +"_icon_black.png")));

    }// evento q sera executado caso o mouse saia do label

    public void mousePressed(MouseEvent arg0) {
        content.setBackground(clicado);
        rotulo.setForeground(Color.WHITE);

    }// evento q sera executado caso o mouse seja pressionado no label

    public void mouseReleased(MouseEvent arg0) {

    }// evento q sera executado caso o mouse seja largado no label
}
