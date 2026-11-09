package com.ecofleet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modelo;
    private String tipo; // carro, moto
    private String combustivel; // gasolina, etanol, diesel
    private Double consumoMedioKmL;
    private Integer anoFabricacao;
    private String status; // disponivel, em_uso

    public Veiculo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    public Double getConsumoMedioKmL() { return consumoMedioKmL; }
    public void setConsumoMedioKmL(Double consumoMedioKmL) { this.consumoMedioKmL = consumoMedioKmL; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
