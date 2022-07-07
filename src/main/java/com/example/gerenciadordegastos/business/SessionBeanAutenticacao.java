package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.AutenticacaoDAO;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;

public class SessionBeanAutenticacao {

    private AutenticacaoDAO authDAO = new AutenticacaoDAO();

    public AutenticacaoVO checkLogin(String username, String password) {
        AutenticacaoVO auth = authDAO.verificarLogin(username, password);
        if (auth.getIdRetorno() != 0) {
            auth.setSucess(true);
            auth.setMsgRetorno("Login encontrado");
        } else {
            auth.setSucess(false);
            auth.setMsgRetorno("Login não encontrado");
        }
        return auth;
    }

    public AutenticacaoVO checkUserExists(String username) {
        AutenticacaoVO auth = authDAO.verificarExistenciaUsername(username);
        if (auth.getIdRetorno() == 0) {
            auth.setSucess(true);
            auth.setMsgRetorno("Nome de usuário disponível");
        } else {
            auth.setSucess(false);
            auth.setMsgRetorno("Nome de usuário já em uso");
        }
        return auth;
    }
}
