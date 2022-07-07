package com.example.gerenciadordegastos.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Usuario {
    private long id;
    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Date nascimento;
    private Timestamp dtaAdd;
    private Timestamp dtaAlt;

    public Usuario() {

    }

    public Usuario(String username, String password, String nome, String email) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(long id, String username, String password, String nome, String cpf, String email, String telefone, Date nascimento, Timestamp dtaAdd, Timestamp dtaAlt) {
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Timestamp getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Timestamp dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Timestamp getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Timestamp dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
