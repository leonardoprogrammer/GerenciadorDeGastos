package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.RendaDAO;
import com.example.gerenciadordegastos.model.entity.Renda;

import java.sql.Timestamp;
import java.util.List;

public class SessionBeanRenda {

    private RendaDAO rendaDAO = new RendaDAO();

    public long getProximaSequencia(long idUsuario) {
        return rendaDAO.getProximaSequencia(idUsuario);
    }

    public void registrarRenda(Renda renda) {
        renda.setDtaAdd(new Timestamp(System.currentTimeMillis()));
        rendaDAO.registrarRenda(renda);
    }

    public void alterarRenda(Renda renda) {
        renda.setDtaAlt(new Timestamp(System.currentTimeMillis()));
        rendaDAO.alterarRenda(renda);
    }

    public void deletarRenda(long id) {
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
