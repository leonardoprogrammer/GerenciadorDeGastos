package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.RendaDAO;
import com.example.gerenciadordegastos.entity.Renda;

import java.util.Calendar;
import java.util.List;

public class SessionBeanRenda {

    private RendaDAO rendaDAO;

    public long getProximaSequencia(long idUsuario) {
        return rendaDAO.getProximaSequencia(idUsuario);
    }

    public void registrarRenda(Renda renda) {
        renda.setSequencia(getProximaSequencia(renda.getIdUsuario()));
        renda.setDtaAdd(Calendar.getInstance());
        rendaDAO.registrarRenda(renda);
    }

    public void alterarRenda(Renda renda) {
        renda.setDtaAlt(Calendar.getInstance());
        rendaDAO.alterarRenda(renda);
    }

    public void deletarVenda(long id) {
        rendaDAO.deletarRenda(id);
    }

    public List<Renda> recuperarRendas() {
        return rendaDAO.recuperarTodasRendas();
    }

    public List<Renda> recuperarRendasPorUsuario(long idUsuario) {
        return rendaDAO.recuperarRendasPorUsuario(idUsuario);
    }

    public Renda recuperarRendaPorId(long id) {
        return rendaDAO.recuperarRendaPorId(id);
    }
}
