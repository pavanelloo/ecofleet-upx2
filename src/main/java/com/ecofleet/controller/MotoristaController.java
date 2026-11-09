package com.ecofleet.controller;

import com.ecofleet.model.Motorista;
import com.ecofleet.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired private MotoristaRepository repo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motoristas", repo.findAll());
        model.addAttribute("motorista", new Motorista());
        return "motoristas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Motorista motorista) {
        repo.save(motorista);
        return "redirect:/motoristas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("motorista", repo.findById(id).orElseThrow());
        model.addAttribute("motoristas", repo.findAll());
        return "motoristas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/motoristas";
    }
}
