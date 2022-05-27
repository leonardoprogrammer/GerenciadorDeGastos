package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.GastoDAO;
import com.example.gerenciadordegastos.entity.Gasto;

import java.util.Calendar;
import java.util.List;

public class SessionBeanGasto {

    private GastoDAO gastoDAO;

    public long getGeneratorId() {
        return gastoDAO.getGeneratorId();
    }

    public long getProximaSequencia(long idUsuario) {
        return gastoDAO.getProximaSequencia(idUsuario);
    }

    public void registrarGasto(Gasto gasto) {
        gasto.setId(getGeneratorId());
        gasto.setSequencia(getProximaSequencia(gasto.getIdUsuario()));
        gasto.setDtaAdd(Calendar.getInstance());
        gastoDAO.registrarGasto(gasto);
    }

    public void alterarGasto(Gasto gasto) {
        gasto.setDtaAlt(Calendar.getInstance());
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
