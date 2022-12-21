package com;


import com.Gerar.Gerar;
import com.Salvar.Salvar;
import com.Telas.TelaCadastrarQuestao.TelaCadastrarQuestao;
import com.Telas.TelaPrincipal.TelaPrincipal;

public class Primaria {

    private Admin admin;
    private Gerar objGerar;
    private Salvar objSalvar;
    private TelaPrincipal telaPrincipal;

    public Primaria(){
        admin = new Admin();
        objGerar = new Gerar(admin);
        objSalvar = new Salvar(admin);
        objSalvar.Input();
        telaPrincipal = new TelaPrincipal(admin, objGerar, objSalvar);
    }

    public static void main(String[] args) {
        Primaria myProgam = new Primaria();
    }
}
