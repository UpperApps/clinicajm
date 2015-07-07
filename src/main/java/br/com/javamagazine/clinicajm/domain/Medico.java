/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.javamagazine.clinicajm.domain.enumeration.Especialidade;
import java.util.Objects;

/**
 *
 * @author Rodrigo
 */
@Entity
public class Medico implements Serializable {

    private static final long serialVersionUID = 714006989890638856L;

    @Id
    @GeneratedValue(generator = "medico_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "medico_id_seq", sequenceName = "medico_id_seq", allocationSize = 1)
    private Integer id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

 //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    //Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.especialidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.especialidade, other.especialidade)) {
            return false;
        }
        return true;
    }

    //ToString
    @Override
    public String toString() {
        return "Medico{" + "id=" + id + ", nome=" + nome + ", especialidade=" + especialidade + '}';
    }

}
