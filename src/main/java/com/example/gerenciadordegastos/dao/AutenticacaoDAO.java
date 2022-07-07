package com.example.gerenciadordegastos.dao;

import com.example.gerenciadordegastos.ConnectionFactory;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutenticacaoDAO {

    public AutenticacaoVO verificarLogin(String username, String password) {
        String query = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
        AutenticacaoVO auth = new AutenticacaoVO();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setString(1, username);
            pstm.setString(2, password);

            rs = pstm.executeQuery();

            while (rs.next()) {
                auth = new AutenticacaoVO();
                auth.setIdRetorno(rs.getInt("id"));
                auth.setUsername(rs.getString("username"));
                auth.setPassword(rs.getString("password"));
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
        return auth;
    }

    public AutenticacaoVO verificarExistenciaUsername(String username) {
        String query = "SELECT * FROM USUARIO WHERE username = ?";
        AutenticacaoVO auth = new AutenticacaoVO();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setString(1, username);

            rs = pstm.executeQuery();

            while (rs.next()) {
                auth = new AutenticacaoVO();
                auth.setIdRetorno(rs.getInt("id"));
                auth.setUsername(rs.getString("username"));
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
        return auth;
    }

}
