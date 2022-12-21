package com;

import com.ListaQuestoes.Assunto;
import com.ListaQuestoes.ListQuestoes;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;
import com.ListaQuestoes.Questao.VouF;
import com.ListaQuestoes.Assuntos;
import com.Salvar.AssuntoSave;

import java.util.List;

public class Admin {

    private ListQuestoes listQuestoes;
    private Assuntos assuntos;

    public Admin(){ //Construtor
        listQuestoes = new ListQuestoes(); //estanceia o objeto ListQuestoes
        assuntos = new Assuntos(); //estancia o objeto assuntos
    }

    public Admin(ListQuestoes listQuestoes, Assuntos assuntos){
        this.listQuestoes = listQuestoes; //estanceia o objeto ListQuestoes
        this.assuntos = assuntos; //estancia o objeto assuntos
    }

    public void addQuestao(String pergunta, String assunto, String resposta){ //adiciona questoes abertas.
        Questao questao = new Aberta(pergunta, assunto, resposta);
        assuntos.addQuestao(assunto, questao);
        listQuestoes.add(questao);
    }

    public void addQuestao(String pergunta, String assunto, String[] alternativas, String corretas){ //adiciona questoes objetivas
        Questao questao = new Objetiva(pergunta, assunto, alternativas, corretas);
        assuntos.addQuestao(assunto, questao);
        listQuestoes.add(questao);
    }

    public void addQuestao(String pergunta, String assunto, boolean resposta){ //adiciona questoes de V ou F
        Questao questao = new VouF(pergunta, assunto, resposta);
        assuntos.addQuestao(assunto, questao);
        listQuestoes.add(questao);
    }
    public boolean editarAssunto(String nomeAnterior, String nomeNovo){

        return assuntos.editaAssunto(nomeAnterior, nomeNovo);
    }
    public void inputQuestao(Questao questao){ //adiciona objetos questoes.
        questao.setID();
        assuntos.addQuestao(questao.getAssunto(),questao);
        listQuestoes.add(questao);
    }

    public Assunto getAssunto(String assunto){
        return this.assuntos.getAssunto(assunto);
    }

    public boolean addQuestaoAssunto(String assunto, Questao questao){ //adiciona objetos questoes.
        return assuntos.inputQuestao(assunto,questao);
    }

    public List<AssuntoSave> getAssuntosSave(){
        return assuntos.getAssuntosSave();
    }

    public void inputAssunto(String nome,Assunto assunto){
        assuntos.inputAssunto(nome,assunto);
    }

    public boolean addAssunto(String assunto){ //Adiciona assunto
        return this.assuntos.addAssunto(assunto);
    }

    public List<String> getNomeAssuntosList(){ //pega uma lista com nomes de todos os assuntos
        return assuntos.getNomeAssuntos();
    }

    public List getQuestoesPorAssunto(String assunto){ //pega a lista de questoes de algum assunto;
        return this.assuntos.getQuestoesAssunto(assunto);
    }

    public List<Questao> getQuestoes(){
        return listQuestoes.getQuestoes();
    }

    public Questao getQuestao(long ID){ //pega uma questao, procura pelo o ID passado como parametro e retorna o objeto Questao.
        return listQuestoes.getQuestao(ID);
    }

    public String getQuestaoToString(long ID){
        return listQuestoes.getQuestaoToString(ID);
    }

    public List<Questao> search(String keyword){ //pesquisa uma por questoes que possuam a keyword.
        return listQuestoes.getQuestao(keyword);
    }

    public List<Questao> search(String keyword,String assunto){ //pesquisa por questoes de algum assunto que possuam a keyword
        return assuntos.getQuestao(keyword, assunto);
    }

    public int getNumQuestoes(){ //pegar numero de questoes cadastradas.
        return listQuestoes.getSizeList();
    }

    public boolean removerQuestao(String assunto, int ID){ //remove uma quetao de algum assunto olhando pelo ID.
        return assuntos.removeQuestao(assunto,ID);
    }

    public boolean removerQuestao(long ID){ //remove uma questao pelo ID.
        return listQuestoes.removeQuestao(ID,assuntos);
    }

    public void editarQuestao(int ID, String pergunta, String resposta){
        Aberta questao = (Aberta) listQuestoes.getQuestao(ID);
        questao.setPergunta(pergunta);
        questao.setResposta(resposta);
    }

    public void editarQuestao(int ID, String pergunta, String[] alternativas, String corretas){
        Objetiva questao = (Objetiva) listQuestoes.getQuestao(ID);
        questao.setPergunta(pergunta);
        questao.setResposta(alternativas,corretas);
    }

    public int getNumQuestoesPorAssunto(String assunto){
        return assuntos.getNumQuestoesPorAssunto(assunto);
    }

    public void editarQuestao(int ID, String pergunta, boolean resposta){
        VouF questao = (VouF) listQuestoes.getQuestao(ID);
        questao.setPergunta(pergunta);
        questao.setResposta(resposta);
    }

    public boolean removeAssunto(String assunto){
        return assuntos.removeAssunto(assunto);
    }

    public ListQuestoes getListQuestoesObjectClone(){
        return listQuestoes.clone();
    }

    public Assuntos getAssuntosObjectClone(){
        return assuntos.clone();
    }

    public Admin clone(){
        return new Admin(this.getListQuestoesObjectClone(),this.getAssuntosObjectClone());
    }
}