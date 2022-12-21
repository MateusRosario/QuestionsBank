package com.ListaQuestoes;

import com.ListaQuestoes.Questao.Questao;
import com.ListaQuestoes.Questao.Questao;

import java.util.*;

public class Assunto {

    private String nome;
    private String data;
    private List<Questao> listaDeQuestoes;
    private boolean ativo=true;

    public Assunto(String nome){

        this.nome = nome;

        Date data = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        this.data=c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        this.listaDeQuestoes = new ArrayList<>();

    }

    public Assunto(String nome, String data, boolean ativo){

        this.nome = nome;
        this.data = data;
        this.listaDeQuestoes = new ArrayList<>();
        this.ativo = ativo;
    }

    public List<Questao> getListaDeQuestoes() {
        return ((List) ((ArrayList<Questao>) listaDeQuestoes).clone());
    }

    public void setAtivoToFalse(){
        ativo=false;
    }

    public void setAtivoToTrue(){
        ativo=true;
    }

    public boolean getAtivo(){
        return ativo;
    }

    public boolean addQuestao(Questao questao){
        listaDeQuestoes.add(questao);
        return true;
    }

    public boolean removeQuestao(int id){
        for (Questao q: listaDeQuestoes) {
            if(q.getID() == id){
                listaDeQuestoes.remove(q);
                return true;
            }
        }
        return false;
    }

    public String getData(){
        return this.data;
    }

    public int getNumQuestoes(){
        return listaDeQuestoes.size();
    }
}