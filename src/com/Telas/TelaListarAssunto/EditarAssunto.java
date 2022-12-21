package com.Telas.TelaListarAssunto;

import com.Admin;
import com.Telas.TelaPrincipal.TileListener;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarAssunto extends JFrame {


    JPanel painelPrincipal;
    JTextField editAssunto;
    JButton botaoConfirmar;

    public EditarAssunto(Admin admin, String assunto, JList lista){

        this.editAssunto = new JTextField(20);
        this.botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        botaoConfirmar.setBackground(TileListener.estadoPadrao);

        JLabel label =  new JLabel("Altere o nome do assunto");
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN + Font.BOLD, 15));

        add(label, BorderLayout.NORTH);
        add(editAssunto, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.SOUTH);

        editAssunto.setText(assunto);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(admin.editarAssunto(assunto, editAssunto.getText())){


                    lista.setListData(admin.getNomeAssuntosList().toArray());
                    lista.setSelectedIndex(admin.getNomeAssuntosList().size()-1);
                    JOptionPane.showMessageDialog(null,"Alterações salvas!");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "As alterações não puderam ser salvas!");
                }
            }
        });
        setVisible(true);
        setSize(300,150);
        setLocationRelativeTo(null);

    }
}
