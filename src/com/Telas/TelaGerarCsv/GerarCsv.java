package com.Telas.TelaGerarCsv;

import com.Admin;
import com.Salvar.AssuntoSave;

import javax.swing.*;
import java.io.FileWriter;
import java.util.List;

public class GerarCsv {
    FileWriter w;

    public GerarCsv(Admin admin){
        try {
            w = new FileWriter("Planilha_de_Assuntos_BDQuestoes.csv");
            addCabecalho();
            addRelatorio(admin);
            w.flush();
            w.close();
        }catch (Exception e){
            e.getStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Sua planilha foi gerada com sucesso!");
    }

    public void addCabecalho(){
        try {
            w.append("Assunto, Data da Criação, Ativo, Quant. de Questoes\n");
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void addRelatorio(Admin admin){
        List<AssuntoSave> assuntos = admin.getAssuntosSave();
        AssuntoSave assunto;
        for (int i = 0; i < assuntos.size() ; i++) {
            assunto= assuntos.get(i);
            addLine(assunto.getNome(),assunto.getData(), assunto.isAtivo()?"Ativo":"Excluido", "" + admin.getNumQuestoesPorAssunto(assunto.getNome()));
        }
    }

    public void addLine(String ... C1){
        try {
            for (int i = 0; i < C1.length; i++) {
                w.append(C1[i]);
                w.append(",");
            }
            w.append("\n");
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
