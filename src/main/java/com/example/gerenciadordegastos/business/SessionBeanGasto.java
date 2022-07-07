package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.GastoDAO;
import com.example.gerenciadordegastos.model.entity.Gasto;

import java.sql.Timestamp;
import java.util.List;

public class SessionBeanGasto {

    private GastoDAO gastoDAO = new GastoDAO();

    public void registrarGasto(Gasto gasto) {
        gasto.setDtaAdd(new Timestamp(System.currentTimeMillis()));
        gastoDAO.registrarGasto(gasto);
    }

    public void alterarGasto(Gasto gasto) {
        gasto.setDtaAlt(new Timestamp(System.currentTimeMillis()));
        gastoDAO.alterarGasto(gasto);
    }

    public void deletarGasto(long id) {
        gastoDAO.deletarGasto(id);
    }

    public List<Gasto> recuperarTodosGastos() {
        return gastoDAO.recuperarTodosGastos();
    }

    public List<Gasto> recuperarGastosPorUsuario(long idUsuario) {
        return gastoDAO.recuperarGastosPorUsuario(idUsuario);
    }

    public Gasto recuperarGastoPorId(long id) {
        return gastoDAO.recuperarGastoPorId(id);
    }
}
