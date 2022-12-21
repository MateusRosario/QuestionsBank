package com.Telas.TelaBusca;

import com.Admin;
import com.ListaQuestoes.Questao.Questao;
import com.Telas.TelaEditar.TelaEditarAberta;
import com.Telas.TelaEditar.TelaEditarObjetiva;
import com.Telas.TelaEditar.TelaEditarVouF;
import com.Telas.TelaInformacoes.TelaInformaçõesAberta;
import com.Telas.TelaInformacoes.TelaInformaçõesObjetiva;
import com.Telas.TelaInformacoes.TelaInformaçõesVouF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BuscaMouseListener implements MouseListener{

    private JLabel label;
    private String dir;
    private int ID;
    private TelaBusca telaBusca;
    private Admin admin;
    private JPanel painel;
    private boolean selecionado;

    public BuscaMouseListener(int ID, JLabel label, String dir, TelaBusca telaBusca, Admin admin) {
        this.ID = ID;
        this.label = label;
        this.dir = dir;
        this.telaBusca = telaBusca;
        this.admin = admin;
        switch (ID){
            case 2:
            case 3:
            case 4:
                telaBusca.saveMouseListener(this);
            break;
        }
    }

    public BuscaMouseListener(int ID, JPanel painel, String dir, TelaBusca telaBusca, Admin admin){
        this.ID=ID;
        this.painel=painel;
        this.dir = dir;
        this.telaBusca=telaBusca;
        this.admin=admin;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (ID) {
            case 0:
                label.setBackground(new Color(155, 0, 0));
                telaBusca.pesquisarAction();
                telaBusca.selecionarPainel(null);
                break;
            case 1:
                label.setBackground(new Color(155, 0, 0));
                TelaBusca.openWindow=false;
                System.out.println("fechou");
                telaBusca.dispose();
                break;
            case 2:
                if(telaBusca.isSelecionado()) {
                    label.setBackground(new Color(155, 0, 0));
                    if (telaBusca.getSelecionado() != null) {
                        int idQuestao = Integer.parseInt(((JLabel) telaBusca.getSelecionado().getComponent(0)).getText().trim());
                        Questao questao = admin.getQuestao(idQuestao);
                        if (questao.getTipo().equals("Aberta")) {
                            TelaInformaçõesAberta telaInformaçõesAberta = new TelaInformaçõesAberta(questao);
                        } else if (questao.getTipo().equals("VouF")) {
                            TelaInformaçõesVouF telaInformaçõesVouF = new TelaInformaçõesVouF(questao);
                        } else if (questao.getTipo().equals("Objetiva")) {
                            TelaInformaçõesObjetiva telaInformaçõesObjetiva = new TelaInformaçõesObjetiva(questao);
                        }
                    }
                }
                break;
            case 3:
                if(telaBusca.isSelecionado()) {
                    label.setBackground(new Color(155, 0, 0));
                    JPanel painelExcluir = telaBusca.getSelecionado();
                    int idPainel = Integer.parseInt(((JLabel) painelExcluir.getComponent(0)).getText().trim());
                    admin.removerQuestao(idPainel);
                    telaBusca.pesquisarAction();
                }
                break;
            case 4:
                if(telaBusca.isSelecionado()) {
                    label.setBackground(new Color(155, 0, 0));
                    if (telaBusca.getSelecionado() != null) {
                        int idQuestao = Integer.parseInt(((JLabel) telaBusca.getSelecionado().getComponent(0)).getText().trim());
                        Questao questao = admin.getQuestao(idQuestao);
                        if (questao.getTipo().equals("Aberta")) {
                            TelaEditarAberta telaEditarAberta = new TelaEditarAberta(questao, admin);
                        } else if (questao.getTipo().equals("VouF")) {
                            TelaEditarVouF telaEditarVouF = new TelaEditarVouF(questao, admin);
                        } else if (questao.getTipo().equals("Objetiva")) {
                            TelaEditarObjetiva telaEditarObjetiva = new TelaEditarObjetiva(questao, admin);
                        }
                    }
                }
                break;
            case 5:
                if(selecionado){
                    painel.setBackground(new Color(255, 255, 255));
                    selecionado=false;
                    telaBusca.deselecionarPainel(painel);
                }else {
                    painel.setBackground(new Color(105, 184, 184));
                    selecionado = true;
                    telaBusca.selecionarPainel(painel);
                }
                break;
            default:
                if(telaBusca.isSelecionado()) {
                    label.setBackground(new Color(155, 0, 0));
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
            case 0:
            case 1:
                label.setOpaque(true);
                label.setIcon(new ImageIcon(getClass().getResource(dir + "_White.png")));
                label.setBackground(new Color(177, 0, 0));
                break;
            case 5:
                Color color = new Color(255,255,255);
                if(painel.getBackground().equals(color)){
                    selecionado=false;
                }
                painel.setBackground(new Color(188, 180, 186));
                break;
            default:
                label.setOpaque(true);
                if(telaBusca.isSelecionado()){
                    label.setIcon(new ImageIcon(getClass().getResource(dir + "_White.png")));
                    label.setBackground(new Color(177, 0, 0));
                }else{
                    label.setBackground(new Color(210, 208, 208));
                }


            break;
        }

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        switch (ID){
            case 0:
            case 1:
                label.setIcon(new ImageIcon(getClass().getResource(dir + "_Black.png")));
                label.setBackground(Color.WHITE);
                label.setOpaque(false);
                break;
            case 5:
                if(selecionado){
                    painel.setBackground(new Color(105, 184, 184));
                }else{
                    painel.setBackground(new Color(255, 255, 255));
                }
                break;
            default:
                block();
                break;
        }
    }

    public void block(){
        if(telaBusca.isSelecionado()){
            label.setIcon(new ImageIcon(getClass().getResource(dir + "_Black.png")));
            label.setBackground(Color.WHITE);
            label.setOpaque(false);
        }else{
            label.setIcon(new ImageIcon(getClass().getResource(dir + "_White.png")));
            label.setBackground(new Color(210, 208, 208));
            label.setOpaque(true);
        }
    }
}
