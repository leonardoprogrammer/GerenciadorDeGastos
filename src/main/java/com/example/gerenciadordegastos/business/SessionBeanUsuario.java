package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.UsuarioDAO;
import com.example.gerenciadordegastos.model.entity.Usuario;

import java.sql.Timestamp;
import java.util.List;

public class SessionBeanUsuario {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public long getGeneratorId() {
        return usuarioDAO.getGeneratorId();
    }

    public void registrarUsuario(Usuario usuario) {
        usuario.setDtaAdd(new Timestamp(System.currentTimeMillis()));
        usuarioDAO.registrarUsuario(usuario);
    }

    public void alterarUsuario(Usuario usuario) {
        usuario.setDtaAlt(new Timestamp(System.currentTimeMillis()));
        usuarioDAO.alterarUsuario(usuario);
    }

    public void alterarSenha(Usuario usuario) {
        usuario.setDtaAlt(new Timestamp(System.currentTimeMillis()));
        usuarioDAO.alterarSenha(usuario);
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
