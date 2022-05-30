package com.example.gerenciadordegastos.business;

import com.example.gerenciadordegastos.dao.RendaDAO;
import com.example.gerenciadordegastos.entity.Renda;

<<<<<<< Updated upstream
import java.util.Calendar;
=======
import java.sql.Timestamp;
>>>>>>> Stashed changes
import java.util.List;

public class SessionBeanRenda {

    private RendaDAO rendaDAO = new RendaDAO();

    public long getProximaSequencia(long idUsuario) {
        return rendaDAO.getProximaSequencia(idUsuario);
    }

    public void registrarRenda(Renda renda) {
<<<<<<< Updated upstream
        renda.setSequencia(getProximaSequencia(renda.getIdUsuario()));
        renda.setDtaAdd(Calendar.getInstance());
=======
        renda.setDtaAdd(new Timestamp(System.currentTimeMillis()));
>>>>>>> Stashed changes
        rendaDAO.registrarRenda(renda);
    }

    public void alterarRenda(Renda renda) {
<<<<<<< Updated upstream
        renda.setDtaAlt(Calendar.getInstance());
=======
        renda.setDtaAlt(new Timestamp(System.currentTimeMillis()));
>>>>>>> Stashed changes
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

    public List<Renda> recuperarRendasPorUsuario(long idUsuario) {
        return rendaDAO.recuperarRendasPorUsuario(idUsuario);
    }
}
