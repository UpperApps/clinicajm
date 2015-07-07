/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.enumeration.Especialidade;
import br.com.javamagazine.clinicajm.repository.MedicoRepository;
import br.com.javamagazine.clinicajm.util.Mensagem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rodrigo
 */
@Controller
//A anota��o @RequestMapping, com o valor /medico, colocada em n�vel de classe, 
//indica que qualquer @RequestMapping presente nos m�todos da classe automaticamente 
//obriga que a URL de chamada tenha antes o prefixo /medico.
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
    public String cadastrar(Medico medico, Model model) {
        medicoRepository.create(medico);
        model.addAttribute("medico", new Medico());
        model.addAttribute("especialidades", Especialidade.values());
        model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o m�dico",
                Mensagem.TipoMensagem.SUCESSO));

        return "cadastrarMedico";
    }

    @RequestMapping(value = "/listar.do", method = RequestMethod.GET)
    public String listar(Model model) {
        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);

        return "listarMedicos";
    }

    @RequestMapping(value = "/excluir.do", method = RequestMethod.DELETE)
    public String excluir(Integer idMedico, Model model) {
        medicoRepository.remove(idMedico);
        model.addAttribute("mensagem", new Mensagem("Sucesso ao excluir m�dico!",
                Mensagem.TipoMensagem.SUCESSO));

        return "forward:/medico/listar.do";
    }

//    O m�todo listaPorEspecialidade() lista, atrav�s de uma requisi��o Ajax, 
//    todos os m�dicos de uma determinada especialidade. Observe que usamos a 
//    anota��o @ResponseBody para indicar que o retorno do m�todo deve ser convertido para JSON.
    @RequestMapping(value = "/listarPorEspecialidade.do", method = RequestMethod.GET)
    public @ResponseBody
    List<Medico> listarPorEspecialidade(Especialidade especialidade) {
        return medicoRepository.listaMedicosPorEspecialidade(especialidade);
    }
}
