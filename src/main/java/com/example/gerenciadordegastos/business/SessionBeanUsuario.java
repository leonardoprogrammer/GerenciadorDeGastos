package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.UsuarioDAO;
import com.example.gerenciadordegastos.entity.Usuario;

import java.util.Calendar;
import java.util.List;

public class SessionBeanUsuario {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public long getGeneratorId() {
        return usuarioDAO.getGeneratorId();
    }

    public void registrarUsuario(Usuario usuario) {
        usuario.setId(getGeneratorId());
        usuario.setDtaAdd(Calendar.getInstance());
        usuarioDAO.registrarUsuario(usuario);
    }

    public void alterarUsuario(Usuario usuario) {
        usuario.setDtaAdd(Calendar.getInstance());
        usuarioDAO.alterarUsuario(usuario);
    }

    public void deletarUsuario(long id) {
        usuarioDAO.deletarUsuario(id);
    }

    public Usuario recuperarUsuarioPorId(long id) {
        return usuarioDAO.recuperarUsuarioPorId(id);
    }

    public List<Usuario> recuperarUsuarios() {
        return usuarioDAO.recuperarUsuarios();
    }
}
