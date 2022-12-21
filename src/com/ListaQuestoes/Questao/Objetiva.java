package com.ListaQuestoes.Questao;
public class Objetiva extends Questao{
    private String[] alternativas;
    private String corretas;

    public Objetiva(String pergunta, String assunto, String[] alternativas, String corretas) {
        //É passado para superclasse os parametros padroes de todas as questoes
        super(pergunta,assunto,"Objetiva");
        this.alternativas=alternativas;
        this.corretas=corretas;
    }

    public void setResposta(String[] alternativas, String corretas){
        this.alternativas=alternativas;
        this.corretas=corretas;
    }

    public String[] getAlternativas() {
        return this.alternativas;
    }

    public String getCorretas(){
        return this.corretas;
    }
}