package com.ecofleet.controller;

import com.ecofleet.model.Veiculo;
import com.ecofleet.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired private VeiculoRepository repo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", repo.findAll());
        model.addAttribute("veiculo", new Veiculo());
        return "veiculos";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Veiculo veiculo) {
        if (veiculo.getStatus() == null || veiculo.getStatus().isEmpty()) {
            veiculo.setStatus("disponivel");
        }
        repo.save(veiculo);
        return "redirect:/veiculos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("veiculo", repo.findById(id).orElseThrow());
        model.addAttribute("veiculos", repo.findAll());
        return "veiculos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/veiculos";
    }
}
