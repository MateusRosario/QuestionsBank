package com.Telas.TelaGerar.TelaGerarSelecionar;

import com.Admin;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Questao;
import com.Telas.TelaGerar.TelaGerarSelecionar.TelaBuscaGERAR;
import com.Telas.TelaInformacoes.TelaInformaçõesAberta;
import com.Telas.TelaInformacoes.TelaInformaçõesObjetiva;
import com.Telas.TelaInformacoes.TelaInformaçõesVouF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GerarBuscaMouseListener implements MouseListener{

    private JLabel label;
    private String dir;
    private int ID;
    private TelaBuscaGERAR telaBusca;
    private Admin admin;
    private JPanel painel;
    private boolean selecionado;

    public GerarBuscaMouseListener(int ID, JLabel label, String dir, TelaBuscaGERAR telaBusca, Admin admin) {
        this.ID = ID;
        this.label = label;
        this.dir = dir;
        this.telaBusca = telaBusca;
        this.admin = admin;
    }

    public GerarBuscaMouseListener(int ID, JPanel painel, String dir, TelaBuscaGERAR telaBusca, Admin admin){
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
                telaBusca.dispose();
                break;
            case 2:
                label.setBackground(new Color(155, 0, 0));
                if(telaBusca.getSelecionado()!=null) {
                    int idQuestao = Integer.parseInt(((JLabel) telaBusca.getSelecionado().getComponent(0)).getText().trim());
                    Questao questao = admin.getQuestao(idQuestao);
                    if (questao.getTipo() == "Aberta") {
                        TelaInformaçõesAberta telaInformaçõesAberta = new TelaInformaçõesAberta(questao);
                    }else if(questao.getTipo() == "VouF"){
                        TelaInformaçõesVouF telaInformaçõesVouF = new TelaInformaçõesVouF(questao);
                    }else if(questao.getTipo() == "Objetiva"){
                        TelaInformaçõesObjetiva telaInformaçõesObjetiva = new TelaInformaçõesObjetiva(questao);
                    }
                }
                break;
            case 3:
                telaBusca.Cont++;
                int questaoid=Integer.parseInt(((JLabel) telaBusca.getSelecionado().getComponent(0)).getText().trim());
                telaBusca.AddNaSelecionadas((long) questaoid);
                admin.removerQuestao(questaoid);
                telaBusca.pesquisarAction();
                if (telaBusca.Cont==telaBusca.adicionar){
                    telaBusca.BotaoDone();
                }
                break;
            case 4:
                telaBusca.avançar();
                telaBusca.dispose();

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
                label.setBackground(new Color(155, 0, 0));
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
            case 5:
                Color color = new Color(255,255,255);
                if(painel.getBackground().equals(color)){
                    selecionado=false;
                }
                painel.setBackground(new Color(188, 180, 186));
                break;
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
            case 5:
                if(selecionado){
                    painel.setBackground(new Color(105, 184, 184));
                }else{
                    painel.setBackground(new Color(255, 255, 255));
                }
                break;
            default:
                label.setIcon(new ImageIcon(getClass().getResource(dir + "_Black.png")));
                label.setBackground(Color.WHITE);
                label.setOpaque(false);
        }
    }
}
