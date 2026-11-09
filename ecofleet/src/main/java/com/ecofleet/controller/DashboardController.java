package com.ecofleet.controller;

import com.ecofleet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private VeiculoRepository veiculoRepo;
    @Autowired private MotoristaRepository motoristaRepo;
    @Autowired private RegistroUsoRepository registroRepo;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        long totalVeiculos = veiculoRepo.count();
        long veiculosEmUso = veiculoRepo.findByStatus("em_uso").size();
        long veiculosDisponiveis = veiculoRepo.findByStatus("disponivel").size();
        long totalMotoristas = motoristaRepo.count();
        long totalRegistros = registroRepo.count();
        model.addAttribute("totalVeiculos", totalVeiculos);
        model.addAttribute("veiculosEmUso", veiculosEmUso);
        model.addAttribute("veiculosDisponiveis", veiculosDisponiveis);
        model.addAttribute("totalMotoristas", totalMotoristas);
        model.addAttribute("totalRegistros", totalRegistros);
        return "dashboard";
    }
}
