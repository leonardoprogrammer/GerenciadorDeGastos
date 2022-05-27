package com.example.gerenciadordegastos.dao;

import com.example.gerenciadordegastos.ConnectionFactory;
import com.example.gerenciadordegastos.entity.Gasto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    public long getGeneratorId() {
        String query = "SELECT GEN_ID(GEN_GASTO_ID, 1) FROM RDB$DATABASE";
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

    public long getProximaSequencia(long idUsuario) {
        String query = "SELECT sequencia FROM GASTO WHERE id_usuario = ? ORDER BY sequencia DESC";
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

    public void registrarGasto(Gasto gasto) {
        StringBuilder query = new StringBuilder("INSERT INTO GASTO");
        query.append("(id, titulo, valor, data, descricao, dta_add)");
        query.append(" VALUES(?, ?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setInt(1, (int) gasto.getId());
            pstm.setString(2, gasto.getTitulo());
            pstm.setDouble(3, gasto.getValor());
            pstm.setDate(4, null, gasto.getData());
            pstm.setString(5, gasto.getDescricao());
            pstm.setDate(6, null, gasto.getDtaAdd());

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

    public void alterarGasto(Gasto gasto) {
        StringBuilder query = new StringBuilder("UPDATE GASTO SET");
        query.append(" titulo = ?, valor = ?, data = ?, descricao = ?, dta_alt = ?");
        query.append(" WHERE id = ?");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, gasto.getTitulo());
            pstm.setDouble(2, gasto.getValor());
            pstm.setDate(3, null, gasto.getData());
            pstm.setString(4, gasto.getDescricao());
            pstm.setDate(5, null, gasto.getDtaAlt());
            pstm.setInt(6, (int) gasto.getId());

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

    public void deletarGasto(long id) {
        String query = "DELETE FROM GASTO WHERE id = ?";

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

    public List<Gasto> recuperarTodosGastos() {
        String query = "SELECT * FROM GASTO";
        List<Gasto> gastos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Gasto gasto = new Gasto();

                gasto.setId(rs.getInt("id"));
                gasto.setTitulo(rs.getString("titulo"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.getData().setTime(rs.getDate("data"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_add"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_alt"));

                gastos.add(gasto);
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
        return gastos;
    }

    public Gasto recuperarGastoPorId(long id) {
        String query = "SELECT * FROM GASTO WHERE id = ?";
        Gasto gasto = new Gasto();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setInt(1, (int) id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                gasto = new Gasto();
                gasto.setId(rs.getLong("id"));
                gasto.setTitulo(rs.getString("titulo"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.getData().setTime(rs.getDate("data"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_add"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_alt"));
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
        return gasto;
    }
}
