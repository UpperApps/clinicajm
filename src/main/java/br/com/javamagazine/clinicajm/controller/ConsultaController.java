/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.repository.ConsultaRepository;
import br.com.javamagazine.clinicajm.util.Mensagem;
import br.com.javamagazine.clinicajm.util.Mensagem.TipoMensagem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
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
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @RequestMapping(value = "/agendar.do", method = RequestMethod.POST)
    public String agendar(Consulta consulta, String data, String hora, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dataConsulta;
        try {
            dataConsulta = sdf.parse(data + " " + hora);
            consulta.setDataConsulta(dataConsulta);
            consultaRepository.create(consulta);
            model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar a consulta",
                    TipoMensagem.SUCESSO));
        } catch (ParseException e) {
            model.addAttribute("mensagem", new Mensagem("Erro ao fazer a conversão de data/hora. "
                    + "Observe os padrões a serem seguidos", TipoMensagem.ERRO));
        }

//        return "forward:/preparaCadastroConsulta.do";
        return "redirect:/preparaCadastroConsulta.do";
    }

    @RequestMapping(value = "/detalharConsulta.do", method = RequestMethod.GET)
    public String detalhar(Integer idConsulta, Model model) {
        Consulta consulta = consultaRepository.find(idConsulta);
        model.addAttribute("consulta", consulta);

        return "realizarAtendimento";
    }

    @RequestMapping(value = "/atender.do", method = RequestMethod.POST)
    public String gravarAtendimento(Consulta consulta, Model model) {
        consulta.setDataAtendimento(new Date());
        consultaRepository.edit(consulta);
        model.addAttribute("mensagem", new Mensagem("Sucesso ao cadastrar o atendimento",
                TipoMensagem.SUCESSO));

//        return "forward:/preparaCadastroAtendimento.do";
        return "redirect:/preparaCadastroAtendimento.do";
    }

    @RequestMapping(value = "/listarPorPaciente.do", method = RequestMethod.GET)
    public @ResponseBody
    List<Consulta> listarPorPaciente(Integer idPaciente) {
        return consultaRepository.listarPorPaciente(idPaciente);
    }
}