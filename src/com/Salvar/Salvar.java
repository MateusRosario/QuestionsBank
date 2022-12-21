package com.Salvar;

import java.awt.*;
import java.util.List;
import java.io.*;
import com.Admin;
import com.ListaQuestoes.Assunto;
import com.ListaQuestoes.ListQuestoes;
import com.ListaQuestoes.Questao.Questao;

public class Salvar {

	private  int ID;
	private Admin admin;
	File f =  new File("Questoes.txt");
	File A = new File("Assuntos.txt");

	public Salvar(Admin admin) {
		this.admin = admin;
	}

	public void Salvar(){
		SalvarAssuntos();
		SalvarQuestoes();
	}

	public void Input(){
		InputAssuntos();
		InputQuestoes();
	}

	public void SalvarAssuntos (){
		try {
			A.delete();

			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(A, true));

			List<AssuntoSave> assuntos = admin.getAssuntosSave();
			AssuntoSave assuntoSave;
			for (int i = 0; i < assuntos.size() ; i++) {
				assuntoSave = assuntos.get(i);
				obj.writeObject(assuntoSave);
			}
		}catch(Exception erro) {
			erro.getStackTrace();
		}finally {
		}
	}

	public void SalvarQuestoes () {
		ID = admin.getNumQuestoes();//saber a quantidade de perguntas
		try {
			f.delete();

			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(f,true));
			Questao questao;
			for(int i = 1; i <= ID+1; i++) {
				if (admin.getQuestao(i)== null) {
					continue;
				} else {
					questao = admin.getQuestao(i);
					obj.writeObject(questao);
				}
			}
		}catch(Exception erro) {
			erro.getStackTrace();
		}finally {
		}
	}

	public void InputAssuntos(){
		try {
			FileInputStream arq = new FileInputStream("Assuntos.txt");
			ObjectInputStream obj = new ObjectInputStream(arq);

			while(true) {
				AssuntoSave assuntoSave = null;
				assuntoSave = (AssuntoSave) obj.readObject();
				admin.inputAssunto(assuntoSave.getNome(), new Assunto(assuntoSave.getNome(), assuntoSave.getData(), assuntoSave.isAtivo()));
			}

		}catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void InputQuestoes (){
		try {
			FileInputStream arq = new FileInputStream("Questoes.txt");
			ObjectInputStream obj = new ObjectInputStream(arq);
			while(true) {
				Questao questao;
				questao = (Questao)obj.readObject();
				admin.inputQuestao(questao);
			}

		}catch (Exception e) {
			e.getStackTrace();
		}
	}
}
