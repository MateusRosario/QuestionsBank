package com.ListaQuestoes.Questao;

public class VouF extends Questao {

    //A resposta da questao sera definida por um boleano

    boolean resposta;

    //Sera passado por parametro as caracteristicas da questao

    public VouF(String pergunta, String assunto,boolean resposta){

        //No super, serao passados os parametros que sao comum em todas as questoes;

        super(pergunta,assunto,"VouF");

        this.resposta=resposta;

    }

    public boolean getResposta(){//retorna a resposta.
        return resposta;
    }

    public void setResposta(boolean resposta){//modifica a resposta.
        this.resposta=resposta;
    }

}