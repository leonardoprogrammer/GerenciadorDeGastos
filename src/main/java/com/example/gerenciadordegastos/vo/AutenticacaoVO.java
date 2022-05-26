package com.example.gerenciadordegastos.vo;

public class AutenticacaoVO {
    private String username;
    private String password;
    private boolean sucess;
    private long idRetorno;
    private String msgRetorno;

    public AutenticacaoVO() {

    }

    public AutenticacaoVO(String username, String password, boolean sucess, long idRetorno, String msgRetorno) {
        this.username = username;
        this.password = password;
        this.sucess = sucess;
        this.idRetorno = idRetorno;
        this.msgRetorno = msgRetorno;
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

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public long getIdRetorno() {
        return idRetorno;
    }

    public void setIdRetorno(long idRetorno) {
        this.idRetorno = idRetorno;
    }

    public String getMsgRetorno() {
        return msgRetorno;
    }

    public void setMsgRetorno(String msgRetorno) {
        this.msgRetorno = msgRetorno;
    }
}
