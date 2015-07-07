package br.com.javamagazine.clinicajm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta implements Serializable {

    private static final long serialVersionUID = 7064809078222302493L;

    @Id
    @GeneratedValue(generator = "consulta_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "consulta_id_seq", sequenceName = "consulta_id_seq", allocationSize = 1)
    private Integer id;
    private String sintomas;
    private String receita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico")
    private Medico medico = new Medico();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente = new Paciente();

    @Column(name = "data_consulta")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataConsulta;

    @Column(name = "data_atendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtendimento;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }
    //Equals, Hashcode e ToString
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.sintomas);
        hash = 71 * hash + Objects.hashCode(this.receita);
        hash = 71 * hash + Objects.hashCode(this.medico);
        hash = 71 * hash + Objects.hashCode(this.paciente);
        hash = 71 * hash + Objects.hashCode(this.dataConsulta);
        hash = 71 * hash + Objects.hashCode(this.dataAtendimento);
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sintomas, other.sintomas)) {
            return false;
        }
        if (!Objects.equals(this.receita, other.receita)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.dataConsulta, other.dataConsulta)) {
            return false;
        }
        if (!Objects.equals(this.dataAtendimento, other.dataAtendimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", sintomas=" + sintomas + ", receita=" + receita + ", medico=" + medico + ", paciente=" + paciente + ", dataConsulta=" + dataConsulta + ", dataAtendimento=" + dataAtendimento + '}';
    }

}
