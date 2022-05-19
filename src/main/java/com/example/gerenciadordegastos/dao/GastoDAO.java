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

    public void registrarGasto(Gasto gasto) {
        StringBuilder query = new StringBuilder("INSERT INTO GASTO");
        query.append("(titulo, valor, data, descricao, dta_add)");
        query.append(" VALUES(?, ?, ?, ?, ?)");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, gasto.getTitulo());
            pstm.setDouble(2, gasto.getValor());
            pstm.setDate(3, (Date) gasto.getData().getTime());
            pstm.setString(4, gasto.getDescricao());
            pstm.setDate(5, (Date) gasto.getDtaAdd().getTime());

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
        query.append(" titulo = ?, valor = ?, data = ?, descricao = ?, dta_add = ?");
        query.append(" WHERE id = ?");

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query.toString());

            pstm.setString(1, gasto.getTitulo());
            pstm.setDouble(2, gasto.getValor());
            pstm.setDate(3, (Date) gasto.getData().getTime());
            pstm.setString(4, gasto.getDescricao());
            pstm.setDate(5, (Date) gasto.getDtaAdd().getTime());

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

                gasto.setId(rs.getLong("id"));
                gasto.setTitulo(rs.getString("titulo"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.getData().setTime(rs.getDate("data"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_add"));

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
        String query = "SELECT * FROM GASTO";
        Gasto gasto = new Gasto();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = conn.prepareStatement(query);

            pstm.setLong(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                gasto = new Gasto();
                gasto.setId(rs.getLong("id"));
                gasto.setTitulo(rs.getString("titulo"));
                gasto.setValor(rs.getDouble("valor"));
                gasto.getData().setTime(rs.getDate("data"));
                gasto.setDescricao(rs.getString("descricao"));
                gasto.getDtaAdd().setTime(rs.getDate("dta_add"));
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
