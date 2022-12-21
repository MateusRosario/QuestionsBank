package com.Telas.TelaEditar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaEditarListeners implements MouseListener {

    private int IDTela;
    private int ID;
    private String dir;
    private JLabel label;
    private TelaEditarAberta telaEditarAberta;
    private TelaEditarVouF telaEditarVouF;
    private TelaEditarObjetiva telaEditarObjetiva;

    public TelaEditarListeners(int ID,String dir, JLabel label, TelaEditarAberta telaEditarAberta){
        this.IDTela=0;
        this.ID=ID;
        this.dir=dir;
        this.label=label;
        this.telaEditarAberta=telaEditarAberta;
    }

    public TelaEditarListeners(int ID,String dir, JLabel label, TelaEditarVouF telaEditarVouF){
        this.IDTela=1;
        this.ID=ID;
        this.dir=dir;
        this.label=label;
        this.telaEditarVouF=telaEditarVouF;
    }

    public TelaEditarListeners(int ID,String dir, JLabel label, TelaEditarObjetiva telaEditarObjetiva){
        this.IDTela=2;
        this.ID=ID;
        this.dir=dir;
        this.label=label;
        this.telaEditarObjetiva=telaEditarObjetiva;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        label.setBackground(new Color(155, 0, 0));
        switch (ID){
            case 1:
                switch (IDTela){
                    case 0:
                        telaEditarAberta.dispose();
                        break;
                    case 1:
                        telaEditarVouF.dispose();
                        break;
                    case 2:
                        telaEditarObjetiva.dispose();
                        break;
                }

                break;
            case 2:
                switch (IDTela){
                    case 0:
                        telaEditarAberta.editar();
                        break;
                    case 1:
                        telaEditarVouF.editar();
                        break;
                    case 2:
                        telaEditarObjetiva.editar();
                        break;
                }
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
        switch (ID){
            default:
                label.setIcon(new ImageIcon(getClass().getResource(dir + "_White.png")));
                label.setOpaque(true);
                label.setBackground(new Color(177, 0, 0));
             break;
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        switch (ID){
            default:
                label.setIcon(new ImageIcon(getClass().getResource(dir + "_Black.png")));
                label.setOpaque(false);
                break;
        }
    }
}
