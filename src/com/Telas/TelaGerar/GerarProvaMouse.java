package com.Telas.TelaGerar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GerarProvaMouse implements MouseListener {
    //Matheus Almeida

    int IdBotao;
    int escolhido;
    TelaGerarProva classe;
    JLabel botao;


    public GerarProvaMouse (TelaGerarProva x, int id, JLabel botao,int escolhido){
        this.botao=botao;
        this.classe=x;
        this.IdBotao=id;
        this.escolhido=escolhido;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (IdBotao){
            case 1:

                botao.setOpaque(true);
                botao.setBackground(new Color(177,0,0));
                botao.setForeground(Color.WHITE);
                classe.Nquestoes.setEnabled(true);
                classe.Ecolhido=this.escolhido;
                break;

            case 2:
                classe.setVisible(false);
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
        switch (IdBotao){
            case 1:
                botao.setOpaque(true);
                botao.setBackground(new Color(177,0,0));
                botao.setForeground(Color.WHITE);
                break;
            case 2:
                botao.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-sair-filled-50.png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        switch (IdBotao){
            case 1:
                botao.setBackground(Color.WHITE);

                botao.setForeground(Color.BLACK);
                break;
            case 2:

                botao.setIcon(new ImageIcon(getClass().getResource("Imagens/icons8-sair-50.png")));

        }

    }


}
