/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.repository;

import br.com.javamagazine.clinicajm.domain.Consulta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rodrigo
 */
public class ConsultaRepository extends AbstractRepository<Consulta> {

    @PersistenceContext(unitName = "clinicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaRepository() {
        super(Consulta.class);
    }

    @SuppressWarnings("unchecked")
    public List<Consulta> listarPorPaciente(Integer idPaciente) {
        Query query = em.createQuery("Select c from Consulta c where c.paciente.id=:idPaciente order by c.dataConsulta");
        query.setParameter("idPaciente", idPaciente);

        return query.getResultList();
    }
}
