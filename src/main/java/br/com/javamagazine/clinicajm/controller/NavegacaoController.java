/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.domain.enumeration.Especialidade;
import br.com.javamagazine.clinicajm.repository.PacienteRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Rodrigo
 */
@Controller
public class NavegacaoController {
    
    @Autowired
    private  PacienteRepository pacienteRepository;
    
    @RequestMapping(value = "/preparaCadastroMedico.do")
    public String redirecionaCadastroMedico(Map<String, Object> map){
        map.put("especialidades", Especialidade.values());
        map.put("medico", new Medico());
        
        return "cadastrarMedico";
    }

    @RequestMapping(value = "/preparaCadastroPaciente.do")
    public String redirecionaCadastroPaciente(Map<String, Object> map){
        map.put("paciente", new Paciente());
        
        return "cadastrarPaciente";
    }
    
    @RequestMapping(value = "/preparaCadastroConsulta.do")
    public String redirecionaCadastroConsulta(Map<String, Object> map){
        map.put("especialidades", Especialidade.values());
        map.put("pacientes", pacienteRepository.findAll());
        map.put("consulta", new Consulta());
        
        return "cadastrarConsulta";
    }
    
    @RequestMapping(value = "/preparaCadastroAtencimento.do")
    public String redirecionaCadastroAtencimento(Map<String, Object> map){
        map.put("pacientes", pacienteRepository.findAll());
        
        return "listarConsultas";
    }
}
