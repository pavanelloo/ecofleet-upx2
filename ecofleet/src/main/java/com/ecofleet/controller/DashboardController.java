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
        long registrosAtivos = registroRepo.findByStatus("em_uso").size();

        Double totalKm = registroRepo.totalDistancia();
        Double totalLitros = registroRepo.totalLitros();
        Double totalCusto = registroRepo.totalCusto();
        Double totalCo2 = registroRepo.totalCo2();

        // Comparativo elétrico: ~6.5 km/kWh, R$ 0.80/kWh
        double kmEletrico = totalKm != null ? totalKm : 0;
        double kwhEletrico = kmEletrico / 6.5;
        double custoEletrico = kwhEletrico * 0.80;
        double economiaPotencial = (totalCusto != null ? totalCusto : 0) - custoEletrico;

        model.addAttribute("totalVeiculos", totalVeiculos);
        model.addAttribute("veiculosEmUso", veiculosEmUso);
        model.addAttribute("veiculosDisponiveis", veiculosDisponiveis);
        model.addAttribute("totalMotoristas", totalMotoristas);
        model.addAttribute("registrosAtivos", registrosAtivos);
        model.addAttribute("totalKm", totalKm != null ? String.format("%.1f", totalKm) : "0.0");
        model.addAttribute("totalLitros", totalLitros != null ? String.format("%.1f", totalLitros) : "0.0");
        model.addAttribute("totalCusto", totalCusto != null ? String.format("%.2f", totalCusto) : "0.00");
        model.addAttribute("totalCo2", totalCo2 != null ? String.format("%.1f", totalCo2) : "0.0");
        model.addAttribute("custoEletrico", String.format("%.2f", custoEletrico));
        model.addAttribute("economiaPotencial", String.format("%.2f", Math.max(0, economiaPotencial)));
        model.addAttribute("ultimosRegistros", registroRepo.findAll().stream().limit(5).toList());

        return "dashboard";
    }
}
