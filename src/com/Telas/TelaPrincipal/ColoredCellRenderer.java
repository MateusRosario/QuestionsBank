package com.Telas.TelaPrincipal;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ColoredCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        setText((value == null) ? "" : value.toString());


        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
//        setIcon(new ImageIcon(getClass().getResource("/imagens/photo.png")));

        if (isSelected) {
            setBackground(new Color(9, 38, 77));
            setForeground(new Color(228, 244, 255));
            //setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
            setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.lightGray));
        }
        else {
            setBackground(list.getBackground());
            setForeground(new Color(198, 198, 198));
        }
        switch (index){
            case 0 : setIcon(new ImageIcon(getClass().getResource("questoes_icon_white.png"))); break;
            case 1 : setIcon(new ImageIcon(getClass().getResource("assuntos_icon_white.png"))); break;
            case 2 : setIcon(new ImageIcon(getClass().getResource("gerar_icon_white.png"))); break;
            case 3 : setIcon(new ImageIcon(getClass().getResource("enviar_icon_white.png"))); break;
        }



        setIconTextGap(5);

        return this;
    }

}