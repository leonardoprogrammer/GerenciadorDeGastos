package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.AutenticacaoDAO;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;

public class SessionBeanAutenticacao {

    private AutenticacaoDAO loginDAO;

    public AutenticacaoVO checkLogin(String username, String password) {
        return loginDAO.verificarLogin(username, password);
    }
}
