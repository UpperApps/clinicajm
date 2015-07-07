package br.com.javamagazine.clinicajm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Paciente implements Serializable {

    private static final long serialVersionUID = 3605331584324240290L;

    @Id
    @GeneratedValue(generator = "paciente_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "paciente_id_seq", sequenceName = "paciente_id_seq", allocationSize = 1)
    private Integer id;
    private String nome;

    //O uso da classe DateTimeFormat, que define com qual formato o Spring MVC 
    //deve enviar o dado em uma requisição vinda da JSP e direcionada para o controller.
    //O framework se encarrega de fazer a conversão automática de String para Date.
    @Column(name = "data_nascimento")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    // Getters and Setters
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    //Equals, Hashcode e ToString
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.dataNascimento);
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
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + '}';
    }
}
