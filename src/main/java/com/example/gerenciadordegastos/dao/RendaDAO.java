package com.example.gerenciadordegastos.dao;

import com.example.gerenciadordegastos.ConnectionFactory;
import com.example.gerenciadordegastos.entity.Renda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RendaDAO {

    public void registrarRenda(Renda renda) {
        StringBuilder query = new StringBuilder("INSERT INTO RENDA");
        query.append("(titulo, valor, data, descricao, dta_add)");
        query.append(" VALUES(?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, renda.getTitulo());
            pstm.setDouble(2, renda.getValor());
            pstm.setDate(3, (Date) renda.getData().getTime());
            pstm.setString(4, renda.getDescricao());
            pstm.setDate(5, (Date) renda.getDtaAdd().getTime());

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

    public void alterarRenda(Renda renda) {
        StringBuilder query = new StringBuilder("UPDATE RENDA SET");
        query.append(" titulo = ?, valor = ?, data = ?, descricao = ?, dta_add = ?");
        query.append(" WHERE id = ?");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, renda.getTitulo());
            pstm.setDouble(2, renda.getValor());
            pstm.setDate(3, (Date) renda.getData().getTime());
            pstm.setString(4, renda.getDescricao());
            pstm.setDate(5, (Date) renda.getDtaAdd().getTime());

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

    public void deletarRenda(long id) {
        String query = "DELETE FROM RENDA WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setLong(1, id);

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

    public List<Renda> recuperarTodasRendas() {
        String query = "SELECT * FROM RENDA";
        List<Renda> rendas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Renda renda = new Renda();

                renda.setId(rs.getLong("id"));
                renda.setTitulo(rs.getString("titulo"));
                renda.setValor(rs.getDouble("valor"));
                renda.getData().setTime(rs.getDate("data"));
                renda.setDescricao(rs.getString("descricao"));
                renda.getDtaAdd().setTime(rs.getDate("dta_add"));

                rendas.add(renda);
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
        return rendas;
    }

    public Renda recuperarRendaPorId(long id) {
        String query = "SELECT * FROM RENDA";
        Renda renda = new Renda();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                renda = new Renda();
                renda.setId(rs.getLong("id"));
                renda.setTitulo(rs.getString("titulo"));
                renda.setValor(rs.getDouble("valor"));
                renda.getData().setTime(rs.getDate("data"));
                renda.setDescricao(rs.getString("descricao"));
                renda.getDtaAdd().setTime(rs.getDate("dta_add"));
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
        return renda;
    }
}
