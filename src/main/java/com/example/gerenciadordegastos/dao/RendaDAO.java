package com.example.gerenciadordegastos.dao;

import com.example.gerenciadordegastos.ConnectionFactory;
import com.example.gerenciadordegastos.entity.Renda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RendaDAO {

    public long getProximaSequencia(long idUsuario) {
        String query = "SELECT sequencia FROM RENDA WHERE id_usuario = ? ORDER BY sequencia DESC";
        long ultSeq = 0;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) idUsuario);

            rs = pstm.executeQuery();

            while (rs.next()) {
                ultSeq = rs.getInt("sequencia");
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
        return ultSeq + 1;
    }

    public void registrarRenda(Renda renda) {
        StringBuilder query = new StringBuilder("INSERT INTO RENDA");
        query.append("(id_usuario, sequencia, titulo, valor, data, descricao, dta_add)");
        query.append(" VALUES(?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setInt(1, (int) renda.getIdUsuario());
            pstm.setInt(2, (int) renda.getSequencia());
            pstm.setString(3, renda.getTitulo());
            pstm.setDouble(4, renda.getValor());
            pstm.setDate(5, null, renda.getData());
            pstm.setString(6, renda.getDescricao());
            pstm.setDate(7, null, renda.getDtaAdd());

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
        query.append(" titulo = ?, valor = ?, data = ?, descricao = ?, dta_alt = ?");
        query.append(" WHERE id = ?");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, renda.getTitulo());
            pstm.setDouble(2, renda.getValor());
            pstm.setDate(3, null, renda.getData());
            pstm.setString(4, renda.getDescricao());
            pstm.setDate(5, null, renda.getDtaAdd());
            pstm.setInt(6, (int) renda.getId());

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

                renda.setId(rs.getInt("id"));
                renda.setIdUsuario(rs.getInt("id_usuario"));
                renda.setSequencia(rs.getInt("sequencia"));
                renda.setTitulo(rs.getString("titulo"));
                renda.setValor(rs.getDouble("valor"));
                renda.getData().setTime(rs.getDate("data"));
                renda.setDescricao(rs.getString("descricao"));
                renda.getDtaAdd().setTime(rs.getDate("dta_add"));
                renda.getDtaAlt().setTime(rs.getDate("dta_alt"));

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

    public List<Renda> recuperarRendasPorUsuario(long idUsuario) {
        String query = "SELECT * FROM RENDA WHERE id_usuario = ?";
        List<Renda> rendas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) idUsuario);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Renda renda = new Renda();

                renda.setId(rs.getInt("id"));
                renda.setIdUsuario(rs.getInt("id_usuario"));
                renda.setSequencia(rs.getInt("sequencia"));
                renda.setTitulo(rs.getString("titulo"));
                renda.setValor(rs.getDouble("valor"));
                renda.getData().setTime(rs.getDate("data"));
                renda.setDescricao(rs.getString("descricao"));
                renda.getDtaAdd().setTime(rs.getDate("dta_add"));
                renda.getDtaAlt().setTime(rs.getDate("dta_alt"));

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
        String query = "SELECT * FROM RENDA WHERE id = ?";
        Renda renda = new Renda();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                renda = new Renda();
                renda.setId(rs.getInt("id"));
                renda.setIdUsuario(rs.getInt("id_usuario"));
                renda.setSequencia(rs.getInt("sequencia"));
                renda.setTitulo(rs.getString("titulo"));
                renda.setValor(rs.getDouble("valor"));
                renda.getData().setTime(rs.getDate("data"));
                renda.setDescricao(rs.getString("descricao"));
                renda.getDtaAdd().setTime(rs.getDate("dta_add"));
                renda.getDtaAlt().setTime(rs.getDate("dta_alt"));
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
