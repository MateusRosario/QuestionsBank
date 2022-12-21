package com.Telas.TelaCadastrarAssunto;

import com.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastrarAssunto extends JFrame {

    JPanel painel;
    JButton botao;
    JTextField textao;
    Admin admin;

    public TelaCadastrarAssunto(Admin admin) {
        painel = new JPanel(new GridBagLayout());
        botao = new JButton("Enviar");
        textao = new JTextField(20);
        this.admin = admin;
        GridBagConstraints posicao = new GridBagConstraints();
        posicao.gridx=0;
        posicao.gridy=0;
        posicao.anchor=GridBagConstraints.WEST;
        painel.add(new JLabel("Assunto:"),posicao);
        posicao.gridx=0;
        posicao.gridy=1;
        posicao.anchor=GridBagConstraints.WEST;
        posicao.insets = new Insets(10,0,10,10);
        painel.add(textao,posicao);

        posicao.gridx=0;
        posicao.gridy=4;
        posicao.anchor=GridBagConstraints.SOUTH;
        painel.add(botao, posicao);
        add(painel);
        Container myContainer = getContentPane();
        setSize(300, 200);
        this.setLocationRelativeTo(null);
        setVisible(true);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!textao.getText().equals("")) {
                    if (admin.addAssunto(textao.getText())) {
                        JOptionPane.showMessageDialog(null, "O assunto \"" + textao.getText() + "\" foi criado com sucesso!\"");
                    } else {
                        JOptionPane.showMessageDialog(null, "Esse assunto já existe!");
                    }
                }
            }
        });
    }

}


