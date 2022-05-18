package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.RendaDAO;
import com.example.gerenciadordegastos.entity.Renda;

import java.util.List;

public class SessionBeanRenda {

    private RendaDAO rendaDAO;

    public void registrarRenda(Renda renda) {
        rendaDAO.registrarRenda(renda);
    }

    public void alterarRenda(Renda renda) {
        rendaDAO.alterarRenda(renda);
    }

    public void deletarVenda(long id) {
        rendaDAO.deletarRenda(id);
    }

    public List<Renda> recuperarRendas() {
        return rendaDAO.recuperarTodasRendas();
    }

    public Renda recuperarRendaPorId(long id) {
        return rendaDAO.recuperarRendaPorId(id);
    }
}
