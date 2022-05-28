package com.example.gerenciadordegastos.dao;

import com.example.gerenciadordegastos.ConnectionFactory;
import com.example.gerenciadordegastos.entity.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public long getGeneratorId() {
        String query = "SELECT GEN_ID(GEN_USUARIO_ID, 1) FROM RDB$DATABASE";
        long id = 0;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                id = (Long) rs.getObject("GEN_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public void registrarUsuario(Usuario usuario) {
        StringBuilder query = new StringBuilder("INSERT INTO USUARIO");
        query.append("(username, password, nome, cpf, email, telefone, nascimento, dta_add)");
        query.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, usuario.getPassword());
            pstm.setString(3, usuario.getNome());
            pstm.setString(4, usuario.getCpf());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getTelefone());
            pstm.setDate(7, null, usuario.getNascimento());
            pstm.setDate(8, null, usuario.getDtaAdd());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void alterarUsuario(Usuario usuario) {
        StringBuilder query = new StringBuilder("UPDATE USUARIO SET");
        query.append(" username = ?, password = ?, nome = ?, cpf = ?, email = ?,");
        query.append(" telefone = ?, nascimento = ?, dta_alt = ?");
        query.append(" WHERE id = ?");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, usuario.getPassword());
            pstm.setString(3, usuario.getNome());
            pstm.setString(4, usuario.getCpf());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getTelefone());
            pstm.setDate(7, null, usuario.getNascimento());
            pstm.setDate(8, null, usuario.getDtaAlt());
            pstm.setInt(9, (int) usuario.getId());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deletarUsuario(long id) {
        String query = "DELETE FROM USUARIO WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) id);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Usuario recuperarUsuarioPorId(long id) {
        String query = "SELECT * FROM USUARIO WHERE id = ?";
        Usuario usuario = new Usuario();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.getNascimento().setTime(rs.getDate("nascimento"));
                usuario.getDtaAdd().setTime(rs.getDate("dta_add"));
                usuario.getDtaAlt().setTime(rs.getDate("dta_alt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public List<Usuario> recuperarUsuarios() {
        String query = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.getNascimento().setTime(rs.getDate("nascimento"));
                usuario.getDtaAdd().setTime(rs.getDate("dta_add"));
                usuario.getDtaAlt().setTime(rs.getDate("dta_alt"));

                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
}
