/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.repository;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.enumeration.Especialidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rodrigo
 */
public class MedicoRepository extends AbstractRepository<Medico> {

    @PersistenceContext(unitName = "clinicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicoRepository() {
        super(Medico.class);
    }

    //Consultas Personalizadas.
    @SuppressWarnings("unchecked")
    public List<Medico> listaMedicosPorEspecialidade(Especialidade especialidade) {
        Query query = em.createQuery("Select m from Medico m where m.especialidade=:especialidade order by m.nome");
        query.setParameter("especialidade", especialidade);

        return query.getResultList();
    }
    
    @Transactional
    public void remove(Integer idMedico){
        Medico medico = em.find(Medico.class, idMedico);
        em.remove(medico);
    }
}
