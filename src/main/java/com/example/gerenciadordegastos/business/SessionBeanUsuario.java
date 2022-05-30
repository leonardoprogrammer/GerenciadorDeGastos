package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.UsuarioDAO;
import com.example.gerenciadordegastos.entity.Usuario;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class SessionBeanUsuario {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public long getGeneratorId() {
        return usuarioDAO.getGeneratorId();
    }

    public void registrarUsuario(Usuario usuario) {
<<<<<<< Updated upstream
        usuario.setDtaAdd(Calendar.getInstance());
=======
        usuario.setId(getGeneratorId());
        usuario.setDtaAdd(new Timestamp(System.currentTimeMillis()));
>>>>>>> Stashed changes
        usuarioDAO.registrarUsuario(usuario);
    }

    public void alterarUsuario(Usuario usuario) {
        usuario.setDtaAlt(new Timestamp(System.currentTimeMillis()));
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
