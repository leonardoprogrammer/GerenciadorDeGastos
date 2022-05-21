package com.example.gerenciadordegastos.entity;

import java.util.Calendar;

public class Usuario {
    private long id;
    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Calendar nascimento;
    private Calendar dtaAdd;
    private Calendar dtaAlt;

    public Usuario() {

    }

    public Usuario(long id, String username, String password, String nome, String cpf, String email, String telefone, Calendar nascimento, Calendar dtaAdd, Calendar dtaAlt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.dtaAdd = dtaAdd;
        this.dtaAlt = dtaAlt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public Calendar getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Calendar dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Calendar getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Calendar dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
