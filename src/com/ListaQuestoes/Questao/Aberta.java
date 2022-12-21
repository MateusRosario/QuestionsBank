package com.ListaQuestoes.Questao;
public class Aberta extends Questao{
    private String resposta; //Guarda a resposta da questão aberta.

    public Aberta(String pergunta,String assunto,String resposta){
        super(pergunta,assunto,"Aberta"); //passa este parametros para o construtor da superclasse.
        this.resposta=resposta;
    }

    public void setResposta(String resposta) { //modifica a resposta.
        this.resposta = resposta;
    }

    public String getResposta() { //retorna a resposta.
        return resposta;
    }
}
