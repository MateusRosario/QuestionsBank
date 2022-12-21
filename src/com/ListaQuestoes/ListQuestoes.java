package com.ListaQuestoes;

import com.ListaQuestoes.Questao.Questao;

import java.util.ArrayList;
import java.util.List;

public class ListQuestoes {
    List<Questao> questoes = new ArrayList<Questao>();

    public ListQuestoes() {
    }

    public ListQuestoes(List<Questao> questoes){
        this.questoes=questoes;
    }

    public void add(Questao questao){
        questoes.add(questao);
    }

    public Questao getQuestao(long ID){
        for(int i=0; i<questoes.size() ; i++){
            if(questoes.get(i).getID()==ID){
                return questoes.get(i);
            }
        }
        return null;
    }

    public String getQuestaoToString(long ID){
        for(int i=0; i<questoes.size() ; i++){
            if(questoes.get(i).getID()==ID){
                return questoes.get(i).toString();
            }
        }
        return "";
    }

    public List<Questao> getQuestao(String keyWord){
        List<Questao> resultadoPesquisa = new ArrayList<Questao>();
        for(int i=0; i<questoes.size() ; i++){
            if(questoes.get(i).getPergunta().replace(" ","").toLowerCase().contains(keyWord.toLowerCase().replace(" ",""))){
                resultadoPesquisa.add(questoes.get(i));
            }
        }
        return resultadoPesquisa;
    }

    public boolean removeQuestao(long ID,Assuntos assuntos) {
        for(int i=0; i<questoes.size() ; i++){
            if(questoes.get(i).getID()==ID){
                assuntos.removeQuestao(questoes.get(i).getAssunto(),(int) ID);
                questoes.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSizeList(){
        return this.questoes.size();
    }

    public ArrayList<Questao> getQuestoes(){
        return (ArrayList<Questao>) ((ArrayList) questoes).clone();
    }

    public ListQuestoes clone(){
        return new ListQuestoes(this.getQuestoes());
    }
}