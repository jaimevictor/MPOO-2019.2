package br.ufrpe.revcare.duende.negocio;

import android.content.Context;

import br.ufrpe.revcare.duende.dominio.Duende;
import br.ufrpe.revcare.duende.persistencia.DuendeDAO;

public class DuendeServices {
    private DuendeDAO dao;

    public DuendeServices(Context context) {
        dao = new DuendeDAO(context);
    }

    public long cadastrar(Duende duende) throws Exception {
        Duende usuarioBD = dao.consultarEmail(duende.getEmail());
        if (usuarioBD != null) {
            throw new Exception("Email já cadastrado.");
        }
        return dao.cadastrar(duende);
    }
    public Duende consultarEmail(String email){
        return dao.consultarEmail(email);
    }
    public Duende consultarCpf(String cpf){
        return dao.consultarCpf(cpf);
    }
}
