package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.UsuarioDAO;
import com.example.gerenciadordegastos.entity.Usuario;

public class SessionBeanUsuario {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.registrarUsuario(usuario);
    }

    public Usuario recuperarUsuarioPorId(long id) {
        return usuarioDAO.recuperarUsuarioPorId(id);
    }
}
