package com.Telas.TelaCadastrarQuestao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TelaCadastrarQuestaoListener implements MouseListener {
    CardLayout umcard;
    JPanel apanel;
    int operacao;
    ImageIcon volta;
    ImageIcon avanca;
    ImageIcon voltaS;
    ImageIcon avancaS;
    JLabel esteLabel;

    String diretorio;

    ImageIcon voltaP;
    ImageIcon avancaP;

    JComboBox acombo;

    JTextField textField;

    TelaCadastrarQuestao c;

    public static int status = 0;


    public TelaCadastrarQuestaoListener(JPanel outpanel, int Toperacao, String diretorio, JLabel TesteLabel, JComboBox Tacombo, TelaCadastrarQuestao c) {
        apanel = outpanel;
        umcard = (CardLayout) apanel.getLayout();
        operacao = Toperacao;
        this.diretorio=diretorio;
        esteLabel = TesteLabel;

        acombo = Tacombo;
        this.c = c;
    }

    public TelaCadastrarQuestaoListener(int operacao, JTextField textField) {
        this.textField = textField;
        this.operacao = operacao;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        switch (operacao) {
            case 0:
                if (textField.getText().equals("Digite a pergunta aqui ") || textField.getText().equals("Digite a resposta aqui ")) {
                    textField.setText("");
                }
                break;
            case 1:
                esteLabel.setBackground(new Color(155, 0, 0));

                if (status == 0) {
                    umcard.show(apanel, (String) acombo.getSelectedItem());
                    status++;
                } else {
                    if (status == 1) {
                        if (c.atualizaValidade()) {
                            c.cadastra();
                            umcard.show(apanel, "sucesso");
                            status++;
                        }
                    } else {
                        if (status == 2) {
                            status = 0;
                            c.cadastrarQuestao.dispose();
                            TelaCadastrarQuestao.openWindow=false;
                        }
                    }
                }
                break;
            case -1:
                esteLabel.setBackground(new Color(155, 0, 0));
                if (status == 2) {
                    return;
                }
                status--;
                if (status < 0) {
                    status = 0;
                }

                if (status == 0) {
                    umcard.first(apanel);
                } else {
                    umcard.show(apanel, (String) acombo.getSelectedItem());
                }
                break;
            case 2:
                esteLabel.setBackground(new Color(155, 0, 0));
                TelaCadastrarQuestao.openWindow=false;
                c.dispose();
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
        switch (operacao) {
            case 0:
                break;
            default:
                esteLabel.setIcon(new ImageIcon(getClass().getResource(diretorio + "_White.png")));
                esteLabel.setOpaque(true);
                esteLabel.setBackground(new Color(177, 0, 0));
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        switch (operacao) {
            case 0:
                break;
            default:
                esteLabel.setIcon(new ImageIcon(getClass().getResource(diretorio + "_Black.png")));
                esteLabel.setOpaque(false);
                break;
        }
    }
}