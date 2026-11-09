package com.ecofleet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "registros_uso")
public class RegistroUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    private Double kmSaida;
    private Double kmRetorno;
    private LocalDateTime horaSaida;
    private LocalDateTime horaRetorno;
    private String destino;
    private String status; // em_uso, finalizado

    // Calculados
    private Double distanciaKm;
    private Double litrosConsumidos;
    private Double custoReais;
    private Double co2Kg;

    public RegistroUso() {}

    public void calcular(double precoCombustivel) {
        if (kmRetorno != null && kmSaida != null && veiculo != null) {
            this.distanciaKm = kmRetorno - kmSaida;
            this.litrosConsumidos = distanciaKm / veiculo.getConsumoMedioKmL();
            this.custoReais = litrosConsumidos * precoCombustivel;
            // Fator CO2: gasolina=2.32, etanol=1.55, diesel=2.68 kg/L
            double fator = switch (veiculo.getCombustivel().toLowerCase()) {
                case "etanol" -> 1.55;
                case "diesel" -> 2.68;
                default -> 2.32;
            };
            this.co2Kg = litrosConsumidos * fator;
        }
    }

    public String getHoraSaidaFormatada() {
        if (horaSaida == null) return "-";
        return horaSaida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getHoraRetornoFormatada() {
        if (horaRetorno == null) return "-";
        return horaRetorno.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }

    public Double getKmSaida() { return kmSaida; }
    public void setKmSaida(Double kmSaida) { this.kmSaida = kmSaida; }

    public Double getKmRetorno() { return kmRetorno; }
    public void setKmRetorno(Double kmRetorno) { this.kmRetorno = kmRetorno; }

    public LocalDateTime getHoraSaida() { return horaSaida; }
    public void setHoraSaida(LocalDateTime horaSaida) { this.horaSaida = horaSaida; }

    public LocalDateTime getHoraRetorno() { return horaRetorno; }
    public void setHoraRetorno(LocalDateTime horaRetorno) { this.horaRetorno = horaRetorno; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(Double distanciaKm) { this.distanciaKm = distanciaKm; }

    public Double getLitrosConsumidos() { return litrosConsumidos; }
    public void setLitrosConsumidos(Double litrosConsumidos) { this.litrosConsumidos = litrosConsumidos; }

    public Double getCustoReais() { return custoReais; }
    public void setCustoReais(Double custoReais) { this.custoReais = custoReais; }

    public Double getCo2Kg() { return co2Kg; }
    public void setCo2Kg(Double co2Kg) { this.co2Kg = co2Kg; }
}
