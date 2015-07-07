/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.repository.PacienteRepository;
import br.com.javamagazine.clinicajm.util.Mensagem;
import br.com.javamagazine.clinicajm.util.Mensagem.TipoMensagem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rodrigo
 */
@Controller
@RequestMapping("/paciente")
public class PacienteController {

 @Autowired
 private PacienteRepository pacienteRepository;

 @RequestMapping(value="/cadastrar.do", method=RequestMethod.POST)
 public String cadastrar(Paciente paciente, Model model) {
   pacienteRepository.create(paciente);
   model.addAttribute("paciente", new Paciente());
   model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o paciente", 
    TipoMensagem.SUCESSO));
 
   return "cadastrarPaciente";
 }

 @RequestMapping(value="/listar.do", method=RequestMethod.GET)
 public String listar(Model model) {
   List<Paciente> pacientes = pacienteRepository.findAll();
   model.addAttribute("pacientes", pacientes);
 
   return "listarPacientes";
 }

 @RequestMapping(value="/excluir.do", method=RequestMethod.DELETE)
 public String excluir(Integer idPaciente, Model model) {
   pacienteRepository.remove(idPaciente);
   model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir o paciente", 
     TipoMensagem.SUCESSO));
 
//   return "forward:/paciente/listar.do";
   return "redirect:/paciente/listar.do";
 }
}