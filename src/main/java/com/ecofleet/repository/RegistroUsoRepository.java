package com.ecofleet.repository;

import com.ecofleet.model.RegistroUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RegistroUsoRepository extends JpaRepository<RegistroUso, Long> {
    List<RegistroUso> findByStatus(String status);

    @Query("SELECT COALESCE(SUM(r.distanciaKm),0) FROM RegistroUso r WHERE r.status = 'finalizado'")
    Double totalDistancia();

    @Query("SELECT COALESCE(SUM(r.litrosConsumidos),0) FROM RegistroUso r WHERE r.status = 'finalizado'")
    Double totalLitros();

    @Query("SELECT COALESCE(SUM(r.custoReais),0) FROM RegistroUso r WHERE r.status = 'finalizado'")
    Double totalCusto();

    @Query("SELECT COALESCE(SUM(r.co2Kg),0) FROM RegistroUso r WHERE r.status = 'finalizado'")
    Double totalCo2();
}
