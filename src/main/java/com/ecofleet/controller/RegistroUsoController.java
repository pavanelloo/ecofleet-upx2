package com.ecofleet.controller;

import com.ecofleet.model.RegistroUso;
import com.ecofleet.model.Veiculo;
import com.ecofleet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/registros")
public class RegistroUsoController {

    @Autowired private RegistroUsoRepository registroRepo;
    @Autowired private VeiculoRepository veiculoRepo;
    @Autowired private MotoristaRepository motoristaRepo;

    private static final double PRECO_COMBUSTIVEL = 6.19;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("registros", registroRepo.findAll());
        model.addAttribute("registro", new RegistroUso());
        model.addAttribute("veiculos", veiculoRepo.findByStatus("disponivel"));
        model.addAttribute("motoristas", motoristaRepo.findAll());
        return "registros";
    }

    @PostMapping("/saida")
    public String registrarSaida(@RequestParam Long veiculoId,
                                  @RequestParam Long motoristaId,
                                  @RequestParam Double kmSaida,
                                  @RequestParam String destino) {
        Veiculo v = veiculoRepo.findById(veiculoId).orElseThrow();
        v.setStatus("em_uso");
        veiculoRepo.save(v);

        RegistroUso r = new RegistroUso();
        r.setVeiculo(v);
        r.setMotorista(motoristaRepo.findById(motoristaId).orElseThrow());
        r.setKmSaida(kmSaida);
        r.setDestino(destino);
        r.setHoraSaida(LocalDateTime.now());
        r.setStatus("em_uso");
        registroRepo.save(r);
        return "redirect:/registros";
    }

    @PostMapping("/retorno/{id}")
    public String registrarRetorno(@PathVariable Long id, @RequestParam Double kmRetorno) {
        RegistroUso r = registroRepo.findById(id).orElseThrow();
        r.setKmRetorno(kmRetorno);
        r.setHoraRetorno(LocalDateTime.now());
        r.setStatus("finalizado");
        r.calcular(PRECO_COMBUSTIVEL);

        Veiculo v = r.getVeiculo();
        v.setStatus("disponivel");
        veiculoRepo.save(v);
        registroRepo.save(r);
        return "redirect:/registros";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        RegistroUso r = registroRepo.findById(id).orElseThrow();
        if ("em_uso".equals(r.getStatus())) {
            Veiculo v = r.getVeiculo();
            v.setStatus("disponivel");
            veiculoRepo.save(v);
        }
        registroRepo.deleteById(id);
        return "redirect:/registros";
    }
}
