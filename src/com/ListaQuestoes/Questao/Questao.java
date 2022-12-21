package com.ListaQuestoes.Questao;

import java.io.Serializable;

public abstract class Questao implements Serializable{
    private static long ultimoID=0;/*Está variavel estática guarda o ultimo ID utilizado em
    * todo o Sistema, assim, nunca havera mais de uma questão com o mesmo ID.*/

    private long ID; //Guarda o ID desta questão.
    private String pergunta; //Guarda a pergunta desta questão.
    private String tipo; //Guarda o Tipo desta questão.
    private String assunto; //Guarda o assunto da questão.

    /*não possui atributo resposta aqui, pois este atributo e os métodos que trabalham com o mesmo, possuem implementações
    * diferentes para cada tipo. Assim estes atributos e métodos estão implementados nas classes herdeiras Aberta, Objetiva e VouF.*/
    public static long getUltimoID(){
        return ultimoID;
    }

    public Questao(String pergunta, String assunto, String tipo){//Este construtor recebe estes parametros das classes herdeiras.
        this.pergunta=pergunta;
        this.assunto=assunto;
        this.ID=++ultimoID;
        this.tipo=tipo;
    }

    public String getPergunta(){ //retorna variavel pergunta.
        return this.pergunta;
    }

    public String getTipo() { //retorna tipo da questão.
        return tipo;
    }

    public long getID() { //retorna ID da questão.
        return ID;
    }

    public void setID(){
        this.ID=++ultimoID;
    }

    public String getAssunto() {//retorna o assunto da questão.
        return assunto;
    }

    public void setPergunta(String pergunta){ //modifica variavel pergunta.
        this.pergunta=pergunta;
    }

    public void setAssunto(String assunto) {
        /*modifica Assunto da questão, só deve ser utilizado se o objeto Assunto
        que guarda a questão também for modificado de acordo com o nescessario*/
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
