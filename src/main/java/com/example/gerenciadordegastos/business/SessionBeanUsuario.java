package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.UsuarioDAO;

public class SessionBeanUsuario {

    private UsuarioDAO usuarioDAO;

    public boolean checkLogin(String username, String password) {
        return usuarioDAO.checkLogin(username, password);
    }
}
