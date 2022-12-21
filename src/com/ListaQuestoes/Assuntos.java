package com.ListaQuestoes;

import com.ListaQuestoes.Questao.*;
import com.Salvar.AssuntoSave;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Assuntos {

    private HashMap<String, Assunto> osAssuntos;
    private HashMap<String, Assunto> osAssuntosDel;
    private List<String> assuntos;
    private List <String> assuntosDeletados;

    public Assuntos(){

        osAssuntos = new HashMap<>();
        osAssuntosDel = new HashMap<>();
        assuntosDeletados = new ArrayList<>();
        assuntos = new ArrayList<>();

    }

    public Assuntos(HashMap<String, Assunto> osAssuntos, HashMap<String, Assunto> osAssuntosDel, List<String> assuntosDeletados, List<String> assuntos){
        this.osAssuntos = osAssuntos;
        this.osAssuntosDel = osAssuntosDel;
        this.assuntosDeletados = assuntosDeletados;
        this.assuntos = assuntos;
    }

    /*
    Esse metodo ao adicionar a questao, checa se o assunto existe no HashMap
    Se sim, adiciona no respectivo assunto, tudo usando a string com o nome do assunto como chave
    Se nao, cria um novo assunto e insere a questao no hashmap com o identificador sendo o nome do assunto criado
     */
    public boolean addQuestao(String assunto,Questao questao){

        if(osAssuntos.containsKey(assunto)){
            osAssuntos.get(assunto).addQuestao(questao);
            return true;
        }else if(osAssuntosDel.containsKey(assunto)) {
            osAssuntosDel.get(assunto).addQuestao(questao);
            return true;
        }
        return false;
    }

    public List<String> getAssuntosDeletados() {
        return assuntosDeletados;
    }

    public boolean removeQuestao(String assunto, int id){
        if(osAssuntos.containsKey(assunto)){
            osAssuntos.get(assunto).removeQuestao(id);
            return true;
        }else if(osAssuntosDel.containsKey(assunto)){
            osAssuntosDel.get(assunto).removeQuestao(id);
        }
        return false;
    }

 public boolean inputQuestao(String assunto,Questao questao){

        if(osAssuntos.containsKey(assunto)){
            osAssuntos.get(assunto).addQuestao(questao);
            return true;
        }
        return false;
    }

    //Esse metodo simplesmente retorna um assunto dado seu nome

    public Assunto getAssunto(String nomeAssunto){
        return osAssuntos.get(nomeAssunto);
    }

    public Assunto getAssuntoInativo(String nomeAssunto){
        return osAssuntosDel.get(nomeAssunto);
    }

    //Dado o nome de um assunto, para criar sem ligacao com questoes, o metodo adiciona ele ao hashmap, caso o assunto ja exista ele retorna false

    public boolean addAssunto(String nomeAssunto){

        String nomeTemp = nomeAssunto.substring(0,1).toUpperCase().concat(nomeAssunto.substring(1).toLowerCase());

        if(!osAssuntosDel.containsKey(nomeTemp)) {

            if (!osAssuntos.containsKey(nomeTemp)) {
                osAssuntos.put(nomeTemp, new Assunto(nomeTemp));
                assuntos.add(nomeTemp);
                return true;
            }
        }else{
            Assunto novo;
            novo = osAssuntosDel.get(nomeTemp);
            osAssuntosDel.remove(nomeTemp);
            assuntosDeletados.remove(nomeTemp);

            novo.setAtivoToTrue();


            assuntos.add(nomeTemp);
            osAssuntos.put(nomeTemp, novo);
            return true;
        }
        return false;
    }

    //Esse metodo simplesmente remove o assunto cujo nome foi passado. Caso nao exista o assunto, retorna falso

    public boolean editaAssunto(String nomeAnterior, String novoNome){
        
        if(osAssuntos.containsKey(novoNome)){
            return false;
        }
        Assunto velhoAssunto = osAssuntos.remove(nomeAnterior);
        for(Questao q : velhoAssunto.getListaDeQuestoes()){
            q.setAssunto(novoNome);
        }
        osAssuntos.put(novoNome, velhoAssunto);
        
        assuntos.add(novoNome);
        assuntos.remove(nomeAnterior);
        
        return true;
    }
    public boolean removeAssunto(String nomeAssunto){
        if(osAssuntos.containsKey(nomeAssunto)){
            Assunto removido;
            removido = osAssuntos.get(nomeAssunto);
            osAssuntos.remove(nomeAssunto);
            assuntos.remove(nomeAssunto);

            removido.setAtivoToFalse();

            assuntosDeletados.add(nomeAssunto);
            osAssuntosDel.put(nomeAssunto, removido);
            return true;
        }
        return false;
    }

    //retorna lista com o nome de todos os assuntos cadastrados
    public List<String> getNomeAssuntos(){
        return assuntos;
    }

    //retorna lista com o nome de todos os assuntos deletados.
    public List<String> getNomeAssuntosDel(){
        return assuntosDeletados;
    }

    public List<Questao> getQuestoesAssunto(String nomeAssunto){
        if(osAssuntos.containsKey(nomeAssunto)){
            return getAssunto(nomeAssunto).getListaDeQuestoes();
        }else if(osAssuntosDel.containsKey(nomeAssunto)){
            return getAssuntoInativo(nomeAssunto).getListaDeQuestoes();
        }
        return null;
    }

    public List<Questao> getQuestao(String keyword, String assunto){

        List<Questao> listaBusca = new ArrayList<>();

        for (Questao q: getQuestoesAssunto(assunto)) {
            if(q.getPergunta().replace(" ","").toLowerCase().contains(keyword.replace(" ", "").toLowerCase())){

                listaBusca.add(q);

            }
        }
        return listaBusca;

    }

    public List<AssuntoSave> getAssuntosSave(){
        List<AssuntoSave> assuntos = new ArrayList<AssuntoSave>();
        Assunto assunto;
        String nome;
        for(int i=0; i<this.assuntos.size();i++){
            nome = this.assuntos.get(i);
            assunto = getAssunto(nome);
            assuntos.add(new AssuntoSave(nome,assunto.getData(),assunto.getAtivo()));
        }

       for (int i = 0; i < assuntosDeletados.size(); i++) {
            nome= this.assuntosDeletados.get(i);
            assunto = getAssuntoInativo(nome);
            assuntos.add(new AssuntoSave(nome,assunto.getData(),assunto.getAtivo()));
        }

        return assuntos;
    }

    public int getNumQuestoesPorAssunto(String assunto){
        if(osAssuntos.containsKey(assunto)){
            return osAssuntos.get(assunto).getNumQuestoes();
        }else if(osAssuntosDel.containsKey(assunto)){
            return osAssuntosDel.get(assunto).getNumQuestoes();
        }
        return 0;
    }

    public boolean inputAssunto(String nome, Assunto assunto){
        if(assunto.getAtivo()){
            if(!osAssuntos.containsKey(nome)){
                osAssuntos.put(nome,assunto);
                assuntos.add(nome);
                return true;
            }
            return false;
        }else{
            if(!osAssuntosDel.containsKey(nome)){
                osAssuntosDel.put(nome,assunto);
                assuntosDeletados.add(nome);
                return true;
            }
            return false;
        }
    }

    public HashMap getOsAssuntosObjectClone(){
        return (HashMap) osAssuntos.clone();
    }

    public HashMap getOsAssuntosDelObjectClone(){
        return (HashMap) osAssuntosDel.clone();
    }

    public List getAssuntosObjectClone(){
        return (List) ((ArrayList) assuntos).clone();
    }

    public List getAssuntosDeletadosObjectClone(){
        return (List) ((ArrayList) assuntosDeletados).clone();
    }

    public Assuntos clone(){
        return new Assuntos(getOsAssuntosObjectClone(), getOsAssuntosDelObjectClone(),getAssuntosObjectClone(),getAssuntosDeletadosObjectClone());
    }
}