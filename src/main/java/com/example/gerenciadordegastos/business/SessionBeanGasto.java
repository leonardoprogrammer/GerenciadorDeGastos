package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.GastoDAO;
import com.example.gerenciadordegastos.entity.Gasto;

import java.util.List;

public class SessionBeanGasto {

    private GastoDAO gastoDAO;

    public void registrarGasto(Gasto gasto) {
        gastoDAO.registrarGasto(gasto);
    }

    public void alterarGasto(Gasto gasto) {
        gastoDAO.alterarGasto(gasto);
    }

    public void deletarGasto(long id) {
        gastoDAO.deletarGasto(id);
    }

    public List<Gasto> recuperarTodosGastos() {
        return gastoDAO.recuperarTodosGastos();
    }

    public Gasto recuperarGastoPorId(long id) {
        return gastoDAO.recuperarGastoPorId(id);
    }
}
