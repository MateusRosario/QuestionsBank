package com.Salvar;

import java.io.Serializable;
import java.util.Date;

public class AssuntoSave implements Serializable {
    private String nome;
    private String data;
    private boolean ativo;

    public AssuntoSave(String nome, String data, boolean ativo) {
        this.nome = nome;
        this.data = data;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
