/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.repository;

import br.com.javamagazine.clinicajm.domain.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rodrigo
 */
public class PacienteRepository extends AbstractRepository<Paciente> {

    @PersistenceContext(unitName = "clinicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteRepository() {
        super(Paciente.class);
    }
    
    @Transactional
    public void remove(Integer idPaciente){
        Paciente paciente = em.find(Paciente.class, idPaciente);
        em.remove(paciente);
    }
}
